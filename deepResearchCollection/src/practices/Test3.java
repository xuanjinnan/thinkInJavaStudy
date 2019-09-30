package practices;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import examples.Countries;

public class Test3 {
	private static Map<String, String> capitals = Countries.capitals(3);
	public static void main(String[] args) {
		Set<Map<String,String>> hs = new HashSet<Map<String, String>>();
		Set<Map<String,String>> ls = new HashSet<Map<String, String>>();
		Set<Map<String,String>> ts = new HashSet<Map<String, String>>();
		for(int i = 0; i < 3; i++) {
			addSet(hs);
			addSet(ls);
			addSet(ts);
		}
		System.out.println("hs:" + hs);
		System.out.println("ls" + ls);
		System.out.println("ts:" + ts);
	}
	public static Set<Map<String, String>> addSet(Set<Map<String,String>> set) {
		set.add(capitals);
		return set;
	}
}
