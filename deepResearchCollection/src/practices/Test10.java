package practices;

import java.util.Iterator;
import java.util.LinkedList;

class SortedSet10<T> extends LinkedList<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int compare(T t1,T t2) {
		int c = t1.hashCode() - t2.hashCode();
		return (c < 0) ? -1 : ((c == 0) ? 0 : 1);
	}
	
	public boolean add(T t) {
		
		if(!this.contains(t)) {
			Iterator<T> it = this.iterator();
			int index = 0;
			while(it.hasNext()) {
				if(compare(it.next(),t) < 1)
					index ++;
			}
			this.add(index, t);
			return true;
		}
		return false;
	}
}

class T{}

public class Test10{
	public static void main(String[] args) {
		SortedSet10<T> ss = new SortedSet10<T>();
		ss.add(new T());
		ss.add(new T());
		ss.add(new T());
		ss.add(new T());
		ss.add(new T());
		System.out.println(ss);
		SortedSet10<Integer> ss2 = new SortedSet10<Integer>();
		ss2.add(6);
		ss2.add(8);
		ss2.add(2);
		ss2.add(4);
		ss2.add(2);
		ss2.add(8);
		System.out.println(ss2);
		SortedSet10<String> ss3 = new SortedSet10<String>();
		ss3.add("three");
		ss3.add("three");
		ss3.add("seven");
		ss3.add("hi");
		ss3.add("two");
		ss3.add("one");
		System.out.println(ss3);
	}
}