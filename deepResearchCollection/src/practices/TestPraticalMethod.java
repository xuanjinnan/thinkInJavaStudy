package practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class TestPraticalMethod {
	public static void main(String[] args) {
		List<String> list0 = new ArrayList<String>();
		list0.add("a");
		list0.add("b");
		list0.add("c");
		List<String> list1 = new ArrayList<String>();
		Collections.addAll(list1, new String[list0.size()]);
		//List<String> list1 = new ArrayList<String>(Arrays.asList(new String[list0.size()]));
		Collections.copy(list1, list0);
		System.out.println(list1);
		Collections.swap(list1, 0, 1);
		System.out.println(list1);
		Collections.rotate(list1, 1);
		System.out.println(list1);
		Collections.fill(list1, "d");
		System.out.println(list1);
		List<Integer> nCopies = Collections.nCopies(4, 1);
		System.out.println(nCopies);
		System.out.println(Collections.disjoint(list1, list0));
		int frequency = Collections.frequency(nCopies, 1);
		System.out.println(frequency);
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
		Integer min = Collections.min(list2);
		System.out.println(min);
		List<Object> emptyList = Collections.emptyList();
		Map<Object, Object> emptyMap = Collections.emptyMap();
		Set<Object> emptySet = Collections.emptySet();
		Set<String> singleton = Collections.singleton("a");
		//singleton.add("b");
		List<String> singletonList = Collections.singletonList("b");
		//singletonList.add("c");
		Map<Integer, String> singletonMap = Collections.singletonMap(1, "one");
		//singletonMap.put(2, "two");
		System.out.println(list0);
		Enumeration<String> enumeration = Collections.enumeration(list0);
		System.out.println(enumeration.nextElement());
		ArrayList<String> list3 = Collections.list(enumeration);
		System.out.println(list3);
		int binarySearch = Collections.binarySearch(list3, "b");
		System.out.println(binarySearch);
		String max = Collections.max(list3);
		System.out.println(max);
	}
}
