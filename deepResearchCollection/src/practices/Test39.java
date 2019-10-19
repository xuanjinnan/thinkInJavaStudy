package practices;


import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import examples.Countries;
import examples.MapEntry;
import net.mindview.util.MapData;
import net.mindview.util.RandomGenerator;
class Prime{
	public static int firstPrimeAfter(int n	){
		for(int i = n + 1; i > n; i++){
			int factors = 0;
			for(int j = 1; j < i; j++){
				if(i % j == 0)
					factors ++;
			}
			if(factors < 2)
				return i;
		}
		return 0;
	}
}

class SimpleHashMapTest39<K, V> extends AbstractMap<K, V> {
	//choose a prime number for the hash table size, to achieve a uniform distribution
	static final int SIZE = 997;
	private int capacity = SIZE;
	static final float FACTOR = 0.75f;
	//you can't have a physical array of generics,but you upcast to one
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];
	
	public V put(K key,V value) {
		if((float)size()/buckets.length > FACTOR)
			rehash();
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K,V>>();
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K,V> pair = new MapEntry<K,V>(key,value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while(it.hasNext()) {
			MapEntry<K, V> ipair = it.next();
			if(ipair.getKey().equals(key)) {//collision
				oldValue = ipair.getValue();
				it.set(pair);//replace old with new
				found = true;
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

	@SuppressWarnings("unchecked")
	private void rehash(){
		capacity = Prime.firstPrimeAfter(buckets.length *2);
		LinkedList<MapEntry<K, V>>[] oldBuckets = buckets;
		buckets = new LinkedList[capacity];
		for (LinkedList<MapEntry<K, V>> oldBucket : oldBuckets) {
			if(oldBucket != null){
				for (MapEntry<K, V> pair : oldBucket) {
					this.put(pair.getKey(), pair.getValue());
				}
			}
		}
	}
	public int capacity(){
		return capacity;
	}
	public static void main(String[] args) {
		RandomGenerator.Integer rgi = new RandomGenerator.Integer(10000);
		System.out.println("Testing map m of <String,String> : the basics:");
		SimpleHashMapTest39<String,String> m0 = new SimpleHashMapTest39<String,String>();
		m0.putAll(Countries.capitals(10));
		System.out.println("m:" + m0);
		System.out.println("m.get(\"CHAD\"):" + m0.get("CHAD"));
		System.out.println("m.capacity():" + m0.capacity());
		SimpleHashMapTest39<Integer,Integer> m1 = new SimpleHashMapTest39<Integer,Integer>();
		m1.putAll(MapData.map(rgi, rgi,996));
		System.out.println("after m1.putAll(MapData.map(rgi, rgi,996))");
		System.out.println("m1:" + m1);
		System.out.println("m1.size()= " + m1.size());
		System.out.println("m1.capacity() = " + m1.capacity());
		m1.putAll(MapData.map(rgi, rgi,2000));
		System.out.println("after m1.putAll(MapData.map(rgi, rgi,2000))");
		System.out.println("m1.size()= " + m1.size());
		System.out.println("m1.capacity() = " + m1.capacity());
		m1.putAll(MapData.map(rgi, rgi,1500));
		System.out.println("after m1.putAll(MapData.map(rgi, rgi,1500))");
		System.out.println("m1.size()= " + m1.size());
		System.out.println("m1.capacity() = " + m1.capacity());
	}
}

public class Test39 {
	public static void main(String[] args) {
		
	}
}
