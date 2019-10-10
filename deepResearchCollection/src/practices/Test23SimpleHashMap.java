package practices;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import examples.Countries;
import examples.MapEntry;

public class Test23SimpleHashMap<K,V> implements Map<K, V> {
	//Choose a prime number for the hash table size, to achieve a uniform distribution;
	static final int SIZE = 997;
	LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];
	@Override
	public int size() {
		int result = 0;
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if(bucket != null)
				result += bucket.size();
		}
		return result;
	}
	//Three methods to help with proper iteration by EntrySet iterator();
	private int firstNonEmptyBucket(){
		if(buckets.length < 1)
			return -1;
		for(int j = 0; j < buckets.length; j++)
			if(buckets[j] !=null)
				return j;
		return -1;
	}
	private int start(int i){
		int first = this.firstNonEmptyBucket();
		if(i < first)
			return -1;
		if(i == first)
			return 0;
		int result = 0;
		for(int j = first; j < i; j++){
			if(buckets[j] != null)
				result += buckets[j].size();
		}
		return result;
	}
	private int end(int i){
		int first = this.firstNonEmptyBucket();
		if(i < first)
			return -1;
		int index = this.start(i) + ((buckets[i] == null) ? 0 : buckets[i].size());
		return index;
	}
	private EntrySet entries = new EntrySet();
	private KetSet keys = new KetSet();
	private class EntrySet extends AbstractSet<Entry<K,V>>{

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K,V>>(){
				private int index = -1;
				@Override
				public boolean hasNext() {
					return index < Test23SimpleHashMap.this.size() -1;
				}

				@Override
				public Entry<K, V> next() {
					++index;
					for(int j = 0; j < SIZE; j++){
						if(start(j) <= index && index <= end(j)){
							System.out.println("finded");
							System.out.println("Test23SimpleHashMap.EntrySet.iterator().new Iterator() {...}.next() " + index);
							System.out.println("start(j)" + start(j));
							return new MapEntry<K,V>(
									buckets[j].get(index - start(j)).getKey(),
									buckets[j].get(index - start(j)).getValue());
						}
					}
					return null;
				}
				public void remove(){
					for(int j = 0; j < SIZE; j++)
						if(start(j) <= index && index <= end(j))
							buckets[j].remove(index - start(j));

				}

			};
		}

		@Override
		public int size() {
			return Test23SimpleHashMap.this.size();
		}

	}
	private class KetSet extends AbstractSet<K>{

		@Override
		public Iterator<K> iterator() {
			return new Iterator<K>(){
				private int index = -1;
				@Override
				public boolean hasNext() {
					return index < Test23SimpleHashMap.this.size() -1;
				}

				@Override
				public K next() {
					++index;
					for(int j = 0; j < SIZE; j++){
						if(start(j) <= index && index <= end(j))
							return buckets[j].get(index - start(j)).getKey(); 
					}
					return null;
				}
				public void remove(){
					for(int j = 0; j < SIZE; j++){
						if(start(j) <= index && index <= end(j))
							buckets[j].remove(index - start(j));
					}
					index--;
				}

			};
		}

		@Override
		public int size() {
			return Test23SimpleHashMap.this.size();
		}

	}
	@Override
	public boolean isEmpty() {
		return this.size() ==0;
	}

	@Override
	public boolean containsKey(Object key) {
		int index = Math.abs(key.hashCode())%SIZE;
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		if(bucket == null) 
			return false;
		for(MapEntry<K,V> pair : bucket){
			if(pair.getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		for(int j = 0; j < SIZE; j++){
			LinkedList<MapEntry<K, V>> bucket = buckets[j];
			for(MapEntry<K,V> pair : bucket){
				if(pair.getValue().equals(value))
					return true;
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode())%SIZE;
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		if(bucket == null)
			return null;
		for(MapEntry<K,V> pair : bucket){
			if(pair.getKey().equals(key))
				return pair.getValue();
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode())%SIZE;
		if(buckets[index] == null){
			buckets[index] = new LinkedList<MapEntry<K,V>>();
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		MapEntry<K,V> pair = new MapEntry<K,V>(key,value);
		boolean found = false;
		while(it.hasNext()){
			MapEntry<K,V> ipair = it.next();
			if(ipair.getKey().equals(key)){
				oldValue = ipair.getValue();
				it.set(pair);
				found = true;
				break;
			}
		}
		if(!found)
			bucket.add(pair);
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		V v = null;
		if(this.get(key) != null){
			int index = Math.abs(key.hashCode())%SIZE;
			for(MapEntry<K,V> pair: buckets[index])
				if(pair.getKey().equals(key)){
					v = pair.getValue();
					int i = buckets[index].indexOf(pair);
					buckets[index].remove(i);
					break;
				}
					
		}
		return v;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(Entry<? extends K,? extends V> pair : m.entrySet()){
			this.put(pair.getKey(), pair.getValue());
		}
	}

	@Override
	public void clear() {
		for(int j = 0; j < SIZE; j++){
			if(buckets[j] != null)
				buckets[j].clear();
		}
	}

	@Override
	public Set<K> keySet() {
		return keys;
	}

	@Override
	public Collection<V> values() {
		Set<V> values = new HashSet<V>();
		for(int j = 0; j < SIZE; j++){
			LinkedList<MapEntry<K, V>> bucket = buckets[j];
			if(bucket != null){
				for(MapEntry<K,V> pair : bucket){
					values.add(pair.getValue());
				}
			}
		}
		return values;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return entries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Test23SimpleHashMap)
			if(this.entrySet().equals(((Test23SimpleHashMap<K,V>)obj).entrySet()))
					return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.entrySet().hashCode();
	}
	public static void main(String[] args) {
		Test23SimpleHashMap<String,String> map =
				new Test23SimpleHashMap<String,String>();
			map.putAll(Countries.capitals(3));
			System.out.println("map = " + map);
			/*System.out.println("map.entrySet(): " + map.entrySet());
			System.out.println("map.keySet(): " + map.keySet());
			System.out.println("map.values() = " + map.values());
			System.out.println("map.isEmpty(): " + map.isEmpty());
			System.out.println("map.containsKey(\"ALGERIA\"): " + map.containsKey("ALGERIA"));
			System.out.println("map.containsValue(\"Algiers\"): " + map.containsValue("Algiers"));
			System.out.println("map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
			System.out.println("map.remove(\"ALGERIA\"): " + map.remove("ALGERIA"));
			System.out.println("After map.remove(\"ALGERIA\"), map.containsKey(\"ALGERIA\"): " +
				map.containsKey("ALGERIA"));
			System.out.println(" and map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
			System.out.println(" and map: = " + map);	
			map.clear();
			System.out.println("After map.clear(), map = " + map);
			System.out.println(" and map.isEmpty(): " + map.isEmpty());
			map.putAll(Countries.capitals(3));
			System.out.println("After map.putAll(Countries.capitals(3)), map = " + map);
			Test23SimpleHashMap<String,String> map2 = 
				new Test23SimpleHashMap<String,String>();
			map2.putAll(Countries.capitals(4));
			System.out.println("After map2.putAll(Countries.capitals(4)), map2 = " + map2);
			System.out.println(" and map.equals(map2): " + map.equals(map2));
			map2.remove("BOTSWANA");	
			System.out.println("After map2.remove(\"BOTSWANT\"), map.equals(map2): " + map.equals(map2));
			map.entrySet().clear();
			System.out.println("After map.entrySet().clear, map = " + map);
			map.putAll(Countries.capitals(3));
			System.out.println("After map.putAll(Countries.capitals(3)), map = " + map);
			map.keySet().clear();
			System.out.println("After map.keySet().clear(), map = " + map);*/
	}
}
