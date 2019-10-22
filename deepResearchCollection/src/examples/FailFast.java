package examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFast {
	public static void main(String[] args) {
		/*Collection<String> c = new ArrayList<String>();
		Iterator<String> it = c.iterator();
		System.out.println(1);
		c.add("An object");
		System.out.println(2);
		String next = it.next();
		System.out.println(3);*/
		Collection<String> c1 = new CopyOnWriteArrayList<String>();
		c1.add("init");
		Iterator<String> it1 = c1.iterator();
		System.out.println(1);
		c1.add("An object");
		System.out.println(2);
		while(it1.hasNext()) {
			System.out.println(it1.next());
		}
		System.out.println(3);
	}
}
