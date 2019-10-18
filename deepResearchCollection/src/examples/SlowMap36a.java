package examples;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
					return entryList.get(++index);
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
		if(oldValue != null) {
			Iterator<MapEntry<K, V>> it = entryList.iterator();
			while(it.hasNext()) {
				MapEntry<K, V> me = it.next();
				if(me.getKey().equals(key)) 
					me.setValue(value);
			}
		}else
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
	
}
class MapEntryKeyComp<K,V> implements Comparator<MapEntry<K,V>>{
	@Override
	public int compare(MapEntry<K, V> o1, MapEntry<K, V> o2) {
		return o1.getKey().hashCode() - o2.getKey().hashCode();
	}
}
