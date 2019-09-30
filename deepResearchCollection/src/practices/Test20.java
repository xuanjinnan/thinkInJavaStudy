package practices;

import examples.Countries;
import examples.SimpleHashMap;

public class Test20 {
	public static void main(String[] args) {
		SimpleHashMap<String,String> m = new SimpleHashMap<String,String>();
		m.putAll(Countries.capitals(10));
		System.out.println(m);
		m.put("EGYPT", "Berlin?");
		m.put("EGYPT", "Cairo");
		System.out.println(m);
		m.putAll(Countries.capitals(10));
	}
}
