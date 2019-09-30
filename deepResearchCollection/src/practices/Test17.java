package practices;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import examples.MapEntry;
import net.mindview.util.Countries;

public class Test17<K,V> implements Map<K,V>{
	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();
	private EntrySet entries = new EntrySet();

	public V put(K key, V value) {
		V v = get(key);
		if(!keys.contains(key)) {
			keys.add(key);
			values.add(value);
		}else {
			values.set(keys.indexOf(key), value);
		}
		return v;
	}

	public V get(Object key) {
		return values.get(keys.indexOf(key));
	}

	private class EntrySet extends AbstractSet<Entry<K, V>>{

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K,V>>(){
				private int index = -1;
				@Override
				public boolean hasNext() {
					return index < size() -1;
				}

				@Override
				public Entry<K, V> next() {
					int i =  ++ index;
					return new MapEntry<K, V>(keys.get(i),values.get(i));
				}

				public void remove() {
					keys.remove(index);
					values.remove(index);
				}
			};

		}

		@Override
		public int size() {
			return keys.size();
		}

	}

	public Set<Entry<K, V>> entrySet() {
		return entries;
	}

	public void clear() {
		keys.clear();
		values.clear();
	}

	public boolean containsKey(Object key) {

		return keys.contains(key);
	}

	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	public boolean isEmpty() {
		return keys.size() == 0;
	}

	private class KeySet extends AbstractSet<K>{

		@Override
		public Iterator<K> iterator() {
			return new Iterator<K>() {
				private int index = -1;
				@Override
				public boolean hasNext() {
					return index < size() -1;
				}
				@Override
				public K next() {
					index ++ ;
					return keys.get(index);
				}
				public void remove() {
					keys.remove(index--);
				}
			};
		}

		@Override
		public int size() {
			return keys.size();
		}


	}
	public Set<K> keySet() {
		return new KeySet();
	}


	public void putAll(Map<? extends K, ? extends V> m) {
		Set<? extends K> keySet = m.keySet();
		for (K key : keySet) {
			if(!keys.contains(key)) {
				keys.add(key);
				values.add(m.get(key));
			}else {
				values.set(keys.indexOf(key), m.get(key));
			}
		}
	}

	public V remove(Object key) {
		keys.remove(key);
		V value = values.get(keys.indexOf(key));
		values.remove(value);
		return value;
	}

	public int size() {
		return keys.size();
	}

	public Collection<V> values() {
		return values;
	}

	@Override
	public String toString() {
		return entries.toString(); 
	}
	public static void main(String[] args) {
		Test17<String,String> m = new Test17<String,String>();
		m.putAll(Countries.capitals(15));
		System.out.println(m);
		System.out.println("m.get(\"BURUNDI\":" + m.get("BURUNDI")) ;
		System.out.println("m.entrySet():" + m.entrySet());
		System.out.println("m.keySet():" + m.keySet());
		System.out.println("m.values():" + m.values);
		System.out.println("Two different maps:");
		Test17<String,String> m2 = new Test17<String,String>();
		System.out.println("m.equals(m2):" + m.equals(m2));
		m2.putAll(Countries.capitals(15));
		System.out.println("Maps with same entries");
		System.out.println("m.equals(m2):" + m.equals(m2));
		m.clear();
		System.out.println("After m.clear,m.isEmpty():" + m.isEmpty() + ", m=" + m);
		m2.keySet().clear();
		System.out.println("After m2.clear,m2.isEmpty():" + m2.isEmpty() + ", m2=" + m2);
	}
}
