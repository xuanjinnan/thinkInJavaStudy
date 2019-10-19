package practices;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import examples.Countries;
import examples.MapEntry;

class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	//choose a prime number for the hash table size, to achieve a uniform distribution
	static final int SIZE = 997;
	//you can't have a physical array of generics,but you upcast to one
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];
	
	public V put(K key,V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K,V>>();
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K,V> pair = new MapEntry<K,V>(key,value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		int probes = 0;
		while(it.hasNext()) {
			MapEntry<K, V> ipair = it.next();
			probes++;
			if(ipair.getKey().equals(key)) {//collision
				oldValue = ipair.getValue();
				it.set(pair);//replace old with new
				found = true;
				/*System.out.println("Collision at " + 
						ipair + ": " + probes + " probe" 
						+ ((probes == 1) ? "" : "s") +
						" needed");*/
				break;
			}
		}
		if(!found) {
			buckets[index].add(pair);
		}
		return oldValue;
	}
	
	public V get(Object key) {
		int index = Math.abs(key.hashCode())%SIZE;
		if(buckets[index] == null) return null;
		for(MapEntry<K,V> ipair : buckets[index]) {
			if(ipair.getKey().equals(key)) {
				return ipair.getValue();
			}
		}
		return null;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K,V>>();
		for(LinkedList<MapEntry<K, V>> bucket : buckets) {
			if(bucket == null) continue;
			for(MapEntry<K,V> mpair : bucket)
				set.add(mpair);
		}
		return set;
	}
}
public class Test21 {
	public static void main(String[] args) {
		SimpleHashMap<String,String> m = new SimpleHashMap<String,String>();
		m.putAll(Countries.capitals(10));
		System.out.println(m);
		m.put("EGYPT", "Berlin?");
		m.put("EGYPT", "Cairo");
		System.out.println(m);
		m.putAll(Countries.capitals(10));
	}
}
