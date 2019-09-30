package practices;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test5CountingMapData extends AbstractMap<Integer, String>{

		private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W S Y Z".split(" ");
		public Test5CountingMapData() {
		}
		private static class Entry implements Map.Entry<Integer, String>{
			int index;
			Entry(int index){
				this.index = index;
			}
			@Override
			public Integer getKey() {
				return index;
			}

			@Override
			public String getValue() {
				return chars[index % chars.length] + chars[index / chars.length];
			}

			@Override
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
			@Override
			public int hashCode() {
				return Integer.valueOf(index).hashCode();
			}
		}
		
		private  class EntrySet extends AbstractSet<Map.Entry<Integer, String>>{
			private int size;
			EntrySet(int size){this.size = size;}
			@Override
			public Iterator<java.util.Map.Entry<Integer, String>> iterator() {
				return new Iterator<java.util.Map.Entry<Integer, String>>() {
					Entry entry = new Entry(-1);
					@Override
					public boolean hasNext() {
						return entry.index < size -1;
					}

					@Override
					public java.util.Map.Entry<Integer, String> next() {
						entry.index++;
						return entry;
					}
					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}

			@Override
			public int size() {
				return size;
			}
			
		}
		public Set<Map.Entry<Integer, String>> entrySet() {
			/*Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<Map.Entry<Integer,String>>();
			for(int i = 0; i < size; i++) {
				entries.add(new  Entry(i));
			}
			return entries;*/
			return new EntrySet(chars.length);
		}
		private static Map<Integer,String> select(int size){
			return new Test5CountingMapData() {
				@Override
				public Set<java.util.Map.Entry<Integer, String>> entrySet() {
					return new EntrySet(size);
				}
				
			};
		}
		public static void main(String[] args) {
			System.out.println(new Test5CountingMapData());
			System.out.println(select(5));
		}

	}
