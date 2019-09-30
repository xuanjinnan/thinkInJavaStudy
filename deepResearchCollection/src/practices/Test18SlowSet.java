package practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test18SlowSet<E> implements Set<E>{
	List<E> elements = new ArrayList<E>();
	@Override
	public boolean add(E e) {
		if(!elements.contains(e)) {
			elements.add(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return elements.addAll(c);
	}
	@Override
	public void clear() {
		elements.clear();
	}
	@Override
	public boolean contains(Object o) {
		return elements.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return elements.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return elements.remove(o);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		int n = size();
		for(Object o : c) elements.remove(o);
		if(n == size()) return true;
		return false;
	}
	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		T[] array = elements.toArray(a);
		return array;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	@Override
	public int hashCode() {
		int result = 0;
		for(int i = 0; i < size(); i++) {
			result += elements.get(i).hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Test18SlowSet) {
			if(elements.size() == ((Test18SlowSet<?>)obj).size()) {
				int count = 0;
				for(int i = 0; i< size(); i++) {
					if(elements.get(i).equals(((Test18SlowSet<?>)obj).elements.get(i))) {
						count++;
					}
				}
				if(count == size())
					return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		System.out.println("Test18SlowSet.toString()");
		if(size() == 0) return "[]";
		StringBuilder s = new StringBuilder();
		s.append("[");
		for (E e : elements) {
			s.append(e).append(","); 
		}
		s.deleteCharAt(s.length()-1);
		s.append("]");
		return s.toString();
	}

	public static void main(String[] args) {
		Test18SlowSet<String> ss = new Test18SlowSet<String>();
		ss.add("hi");
		System.out.println(ss);
		ss.add("there");
		System.out.println(ss);
		List<String> list = Arrays.asList("you","cutie","pie");
		ss.addAll(list);
		System.out.println(ss);
		System.out.println("ss.size():" + ss.size());
		System.out.println("ss.contains(\"you\"):" + ss.contains("you"));
		System.out.println("ss.contains(\"me\"):" + ss.contains("me"));
		System.out.println("ss.containsAll(list):" + ss.containsAll(list));
		
		Test18SlowSet<String> ss2 = new Test18SlowSet<String>();
		System.out.println("ss2:" + ss2);
		System.out.println("ss.containsAll(ss2):" + ss.containsAll(ss2));
		System.out.println("ss2.containsAll(ss):" + ss2.containsAll(ss));
		ss2.add("you");
		ss2.add("cutie");
		ss.removeAll(ss2);
		System.out.println("ss:" + ss);
		System.out.println("ss.hashCode():" + ss.hashCode());
		List<String> list2 = Arrays.asList("hi","there","pie");
		System.out.println("ss2:" + ss2);
		ss2.remove("you");
		System.out.println(ss2);
		System.out.println("ss2.isEmpty():" + ss2.isEmpty());
		ss2.clear();
		System.out.println("ss2.isEmpty():" + ss2.isEmpty());
		ss2.addAll(list2);
		System.out.println("ss2:" + ss2);
		System.out.println("ss.equals(ss2):" + ss.equals(ss2));
		String[] sa = new String[3];
		System.out.println("ss.toArray(sa):" + Arrays.toString(ss.toArray(sa)));
		for(int i = 0; i < sa.length; i++) System.out.print(sa[i] + " ");
		
		
	}

}
