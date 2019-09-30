package practices;

import java.util.Map;
import java.util.Properties;

import examples.CountingMapData;

public class Test14 {
	public static void printKeys(Map<Object,Object> map) {
		System.out.println("Size = " + map.size());
		System.out.println("Keys: ");
		System.out.print(map.keySet());
	}
	public static void test(Map<Object,Object> map) {
		System.out.println(map.getClass().getSimpleName());
		map.putAll(new CountingMapData(25));
		//has Set behavior for keys
		map.putAll(new CountingMapData(25));
		System.out.println(map);
		//producing a Collection of the values
		System.out.println("Values:");
		System.out.print(map.values());
		System.out.println(map);
		System.out.println("map.containsKey(11): " + map.containsKey(11));
		System.out.println("map.get(11): " + map.get(11));
		System.out.println("map.containsValue(\"F0\"): " + map.containsValue("F0"));
		Object key = map.keySet().iterator().next();
		System.out.println("First key in map: " + key);
		map.remove(key);
		printKeys(map);
		map.clear();
		System.out.println("map.isEmpty(): " + map.isEmpty());
		map.putAll(new CountingMapData(25));
		//operations on the Set change the Map;
		map.keySet().removeAll(map.keySet());
		System.out.println("map.isEmpty(): " + map.isEmpty());
	}
	public static void main(String[] args) {
		test(new Properties());
	}
}
