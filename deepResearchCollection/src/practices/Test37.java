package practices;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

import examples.MapEntry;
import examples.test.Test;
import examples.test.TestParam;
import examples.test.Tester;
import net.mindview.util.Countries;

class SimpleHashMapb<K, V> extends AbstractMap<K, V> {
	//choose a prime number for the hash table size, to achieve a uniform distribution
	static final int SIZE = 997;
	//you can't have a physical array of generics,but you upcast to one
	@SuppressWarnings("unchecked")
	ArrayList<MapEntry<K,V>>[] buckets = new ArrayList[SIZE];
	
	public V put(K key,V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new ArrayList<MapEntry<K,V>>();
		ArrayList<MapEntry<K, V>> bucket = buckets[index];
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
		for(ArrayList<MapEntry<K, V>> bucket : buckets) {
			if(bucket == null) continue;
			for(MapEntry<K,V> mpair : bucket)
				set.add(mpair);
		}
		return set;
	}

	public static void main(String[] args) {
		SimpleHashMap<String,String> m = new SimpleHashMap<String,String>();
		m.putAll(Countries.capitals(15));
		System.out.println(m);
		System.out.println(m.get("BENIN"));
		System.out.println(m.entrySet());
	}
}

public class Test37 {
	static List<Test<Map<Integer,Integer>>> tests = new ArrayList<Test<Map<Integer,Integer>>>();
	static {
		tests.add(new Test<Map<Integer,Integer>>("put") {
			public int test(Map<Integer,Integer> map, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					map.clear();
					for(int j = 0; j < size; j++) 
						map.put(j, j);
				}
				return loops * size;
			}
		});
		tests.add(new Test<Map<Integer,Integer>>("get") {
			public int test(Map<Integer,Integer> map, TestParam tp) {
				int loops = tp.loops;
				int span = tp.size * 2;
				for(int i = 0; i < loops; i++)
					for(int j = 0; j < span; j++)
						map.get(j);
				return loops * span;
			}
		});
		tests.add(new Test<Map<Integer,Integer>>("iterate") {
			public int test(Map<Integer,Integer> map, TestParam tp) {
				int loops = tp.loops * 10;
				for(int i = 0; i < loops; i++) {
					Iterator it = map.entrySet().iterator();
					while(it.hasNext()) it.next();
				}
				return loops * map.size();
			}
		});
	}
	public static void main(String[] args) {
		Tester.defaultParams = TestParam.array(10,1000,100,1000,1000,100);
		Tester.run(new SimpleHashMap<Integer,Integer>(), tests);
		Tester.run(new SimpleHashMapb<Integer,Integer>(), tests);
		Tester.run(new TreeMap<Integer,Integer>(), tests);
		Tester.run(new HashMap<Integer,Integer>(), tests);
		Tester.run(new LinkedHashMap<Integer,Integer>(), tests);
		Tester.run(new IdentityHashMap<Integer,Integer>(), tests);
		Tester.run(new WeakHashMap<Integer,Integer>(), tests);
		Tester.run(new Hashtable<Integer,Integer>(), tests);
	}
}
