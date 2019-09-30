package practices;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test12 {
	static void testMap(Map<String,String> map) {
		map.put("sky", "blue");
		map.put("grass", "green");
		map.put("ocean", "dancing");
		map.put("tree", "tall");
		map.put("earth", "brown");
		map.put("sun", "warm");
		map.put("extra", "object");
		System.out.println(map);
	}
	public static void main(String[] args) {
		Map<String,String> map = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
		Map<String,String> map1 = new LinkedHashMap<String,String>();
		testMap(map);
		testMap(map1);
		
	}

}
