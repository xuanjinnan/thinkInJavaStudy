package examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class ReadOnly {
	static Collection<String> data = new ArrayList<String>(Countries.names(6));
	public static void main(String[] args) {
		List<String> c = Collections.unmodifiableList(new ArrayList<String>(data));
		System.out.println(c);
		//c.add("b");
		ListIterator<String> lit = c.listIterator();
		System.out.println(lit.next());
		//lit.add("b");
		Set<String> s = Collections.unmodifiableSet(new HashSet<String>(data));
		System.out.println(s);
		//s.add("b");
		SortedSet<String> ss = Collections.unmodifiableSortedSet(new TreeSet<String>(data));
		System.out.println(ss);
		Map<String, String> m = Collections.unmodifiableMap(new HashMap<String,String>(Countries.capitals(6)));
		System.out.println(m);
		//m.put("Ralph", "Howdy!");
		SortedMap<String, String> sm = Collections.unmodifiableSortedMap(new TreeMap<String,String>(Countries.capitals(6)));
	}
}
