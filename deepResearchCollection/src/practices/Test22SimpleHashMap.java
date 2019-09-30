package practices;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import examples.Countries;
import examples.MapEntry;

public class Test22SimpleHashMap<K, V> extends AbstractMap<K, V> {
	static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<Entry<K,V>>[] buckets = new LinkedList[SIZE]; 
	public V put(K key,V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode())%SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<Entry<K,V>>();
		LinkedList<Entry<K, V>> bucket = buckets[index];
		boolean found = false;
		ListIterator<Entry<K, V>> it = bucket.listIterator();
		MapEntry<K,V> pair = new MapEntry<K,V>(key,value);
		while(it.hasNext()) {
			Entry<K, V> ipair = it.next();
			if(ipair.getKey().equals(key)) {
				it.set(pair);//replace old for with new
			}
			oldValue = ipair.getValue();
			found = true;
			break;
		}
		if(!found) {
			bucket.add(pair);
		}
		return oldValue;
	}

	public V get(Object key) {
		int index = Math.abs(key.hashCode())%SIZE;
		if(buckets[index] == null) return null;
		for(Entry<K,V> pair :buckets[index]) {
			if(pair.getKey().equals(key)) {
				return pair.getValue();
			}
		}
		return null;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K,V>> set = new HashSet<Entry<K,V>>();
		for(LinkedList<Entry<K,V>> bucket : buckets) {
			if(bucket == null) 
				continue;
			for (Entry<K, V> pair : bucket) {
				set.add(pair);
			}
		}
		return set;
	}

	@Override
	public void clear() {
		for(LinkedList<Entry<K,V>> bucket : buckets) {
			if(bucket != null)
				bucket.clear();
		}
	}

	@Override
	public V remove(Object key) {
		V value = null;
		int index = Math.abs(key.hashCode())%SIZE;
		LinkedList<Entry<K, V>> bucket = buckets[index];
		if(bucket == null)
			return null;
		ListIterator<Entry<K, V>> it = bucket.listIterator();
		while(it.hasNext()) {
			Entry<K, V> pair = it.next();
			if(pair.getKey().equals(key)) {
				value = pair.getValue();
				it.remove();
			}
		}
		return value;
	}
	
	public static void main(String[] args) {
		Test22SimpleHashMap<String,String> map = new Test22SimpleHashMap<String,String>();
		map.putAll(Countries.capitals(4));
		System.out.println(map);
		System.out.println(map.get("ANGOLA"));
		System.out.println(map.remove("ANGOLA"));
		System.out.println(map.get("ANGOLA"));
		System.out.println(map);
		map.clear();
		System.out.println(map);
	}
}
