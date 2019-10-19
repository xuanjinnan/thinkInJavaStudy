package practices;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import examples.CountingMapData;
import examples.test.Test;
import examples.test.TestParam;
import examples.test.Tester;
import net.mindview.util.CountingGenerator;

public class Test38 {

	static List<Test<Map<Integer,String>>> tests = 
			new ArrayList<Test<Map<Integer,String>>>();
		static CountingGenerator.String cgs = new CountingGenerator.String(5);
		static {
			tests.add(new Test<Map<Integer,String>>("put") {
				public int test(Map<Integer,String> map, TestParam tp) {
					int loops = tp.loops;
					int size = tp.size;
					for(int i = 0; i < loops; i++) {
						map.clear();
						for(int j = 0; j < size; j++) 
							map.put(j, cgs.next());
					}
					return loops * size;
				}
			});
			tests.add(new Test<Map<Integer,String>>("get") {
				public int test(Map<Integer,String> map, TestParam tp) {
					int loops = tp.loops;
					int span = tp.size * 2;
					for(int i = 0; i < loops; i++)
						for(int j = 0; j < span; j++)
							map.get(j);
					return loops * span;
				}
			});
			tests.add(new Test<Map<Integer,String>>("iterate") {
				public int test(Map<Integer,String> map, TestParam tp) {
					int loops = tp.loops * 10;
					for(int i = 0; i < loops; i++) {
						Iterator it = map.entrySet().iterator();
						while(it.hasNext()) it.next();
					}
					return loops * map.size();
				}
			});
		}
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<Integer,String> map0 = new HashMap<Integer,String>();
		System.out.println("map0" + map0);
		map0.putAll(new CountingMapData(16));
		System.out.println("map0" + map0);
		Map<Integer,String> map1 = new HashMap<Integer,String>(64);
		map1.putAll(map0);
		System.out.println("map1" + map1);
		Map<Integer,String> map2 = new HashMap<Integer,String>(1028);
		map2.putAll(map0);
		System.out.println("map2" + map2);
		
		Tester.defaultParams = TestParam.array(10, 1000, 100, 1000, 1000, 1000);
		Tester.run(map0, tests);
		Tester.run(map1, tests);
		Tester.run(map2, tests);
	}
}
