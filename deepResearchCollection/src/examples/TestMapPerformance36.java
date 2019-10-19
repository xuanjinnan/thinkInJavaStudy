package examples;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

import examples.test.Test;
import examples.test.TestParam;
import examples.test.Tester;

public class TestMapPerformance36{
	static List<Test<Map<Integer,Integer>>> tests = 
			new ArrayList<Test<Map<Integer,Integer>>>();
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
		if(args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		else
			Tester.defaultParams = TestParam.array(10,100,50,50,100,20);
		System.out.println("Tesing SlowMap36a");
		SlowMap36a<String,String> m = new SlowMap36a<String,String>();
		m.putAll(Countries.capitals(15));
		System.out.println("m:" + m);
		System.out.println("m.get(\"BURUNDI\"):" + m.get("BURUNDI"));
		System.out.println("m.entrySet():" + m.entrySet());
		System.out.println("m.keySet():" + m.keySet());
		System.out.println("m.values()=" + m.values());
		System.out.println("Two different maps");
		SlowMap36a<String,String> m2 = new SlowMap36a<String,String>();
		System.out.println("m.equals(m2):" + m.equals(m2));
		m2.putAll(Countries.capitals(15));
		System.out.println("Maps with same entries:");
		System.out.println("m.equals(m2):" + m.equals(m2));
		m.clear();
		System.out.println("After m.clear(),m.isEmpty():" + m.isEmpty() + ",m = " + m);
		m2.keySet().clear();
		System.out.println("After m2.keySet().clear(),m2.isEmpty():" + m2.isEmpty() + ", m2 = " + m2);
		System.out.println();
		System.out.println("Testing SlowMap36b");
		SlowMap36b<String,String> m3 = new SlowMap36b<String,String>();
		m3.putAll(Countries.capitals(15));
		System.out.println("m3:" + m3);
		System.out.println("m3.get(\"BURUNDI\"):" + m3.get("BURUNDI"));
		System.out.println("m3.entrySet():" + m3.entrySet());
		System.out.println("m3.hashEntryList:" + m3.harshEntry());
		System.out.println("m3.entryList:" + m3.entryList.size());
		m3.clear();
		System.out.println("After m3.clear(),m3.isEmpty:" + m3.isEmpty() + "m3:" + m3);
		System.out.println(m3.keySet());
		m3.keySet().clear();
		System.out.println("After m3.keySet().clear(),m3.isEmpty():" + m3.isEmpty() + " m3:" + m3);
		System.out.println();
		System.out.println("Comparative  time tests");
		Tester.run(new SlowMap<Integer,Integer>(), tests);
		Tester.run(new SlowMap36a<Integer,Integer>(), tests);
		Tester.run(new SlowMap36b<Integer,Integer>(), tests);
		Tester.run(new HashMap<Integer,Integer>(), tests);
		Tester.run(new TreeMap<Integer,Integer>(), tests);
		Tester.run(new LinkedHashMap<Integer,Integer>(), tests);
		Tester.run(new IdentityHashMap<Integer,Integer>(), tests);
		Tester.run(new WeakHashMap<Integer,Integer>(), tests);
		Tester.run(new Hashtable<Integer,Integer>(), tests);
		SlowMap36a<Integer, Integer> map = new SlowMap36a<Integer, Integer>();
		map.put(1, 1);
		map.put(1, 2);
		System.out.println(map);
		System.out.println(map.get(1));
	}
}
class SlowMap36a<K, V> extends AbstractMap<K, V> {
	protected List<MapEntry<K,V>> entryList = new ArrayList<MapEntry<K,V>>();
	private EntrySet entries = new EntrySet();
	private class EntrySet extends AbstractSet<Entry<K,V>>{
		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K,V>>(){
				private int index = -1;
				@Override
				public boolean hasNext() {
					return index < size() - 1;
				}
				@Override
				public Entry<K, V> next() {
					int i = ++index;
					return entryList.get(i);
				}
				public void remove() {
					entryList.remove(index--);
				}
			};
		}

		@Override
		public int size() {
			return entryList.size();
		}
	}
	public V put(K key,V value) {
		V oldValue = get(key);// The old value or null
		Iterator<MapEntry<K, V>> it = entryList.iterator();
		while(it.hasNext()) {
			MapEntry<K, V> me = it.next();
			if(me.getKey().equals(key)) 
				me.setValue(value);
		}
		entryList.add(new MapEntry<K,V>(key,value));
		return oldValue;
	}
	public V get(Object key) { //key is type Object,not K
		Iterator<MapEntry<K, V>> it = entryList.iterator();
		while(it.hasNext()) {
			MapEntry<K,V> me = it.next();
			if(me.getKey().equals(key))
				return me.getValue();
		}
		return null;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		return entries;
	}

}

class SlowMap36b<K,V> extends SlowMap36a<K,V>{
	// To allow search for key via Collections.binarySearch(List<T>,T,Comparator):
	private List<MapEntry<Integer,V>> hashEntryList = new ArrayList<MapEntry<Integer,V>>();
	public List<MapEntry<Integer,V>> harshEntry(){
		return hashEntryList;
	}
	// To allow sorting
	private final MapEntryKeyComp<K,V> comp1 = new MapEntryKeyComp<K,V>();
	private final MapEntryKeyComp<Integer,V> comp2 = new MapEntryKeyComp<Integer,V>();
	// Very slow put() with 2 sorts:
	@Override
	public V put(K key, V value) {
		V oldValue = get(key);
		Iterator<MapEntry<K, V>> it = entryList.iterator();
		while(it.hasNext()) {
			MapEntry<K,V> me = it.next();
			if(me.getKey().equals(key))
				me.setValue(value);
		}
		entryList.add(new MapEntry<K,V>(key,value));
		hashEntryList.add(new MapEntry<Integer,V>(key.hashCode(),value));
		//Sort (uses hashCode()) after adding
		Collections.sort(entryList, comp1);
		Collections.sort(hashEntryList,comp2);
		return oldValue;
	}
	// Faster get() with sorting done in put:
	@Override
	public V get(Object key) {
		// Same key,same hashCOde via MapEntryKeyComp<K,V>:
		MapEntry<Integer,V> sub = new MapEntry<Integer,V>(key.hashCode(),null);
		int i = Collections.binarySearch(hashEntryList, sub,comp2);
		if(i > 0)
			return hashEntryList.get(i).getValue();
		return null;
	}

}
class MapEntryKeyComp<K,V> implements Comparator<MapEntry<K,V>>{
	@Override
	public int compare(MapEntry<K, V> o1, MapEntry<K, V> o2) {
		int hashCode1 = o1.getKey().hashCode();
		int hashCode2 = o2.getKey().hashCode();
		return hashCode1 > hashCode2 ? 1 : hashCode1 < hashCode2 ? -1 : 0;
	}
}


