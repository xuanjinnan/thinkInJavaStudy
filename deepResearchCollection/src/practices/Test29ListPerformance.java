package practices;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import examples.test2.Tester;
import examples.test2.Test;
import examples.test2.TestParam;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

public class Test29ListPerformance {
	static Random rand = new Random();
	static RandomGenerator.String randString = new RandomGenerator.String();
	static int reps = 1000;
	static List<Test<List<String>>> tests = new ArrayList<Test<List<String>>>();
	static List<Test<LinkedList<String>>> qtests = new ArrayList<Test<LinkedList<String>>>();
	static {
		//static block start
		tests.add(new Test<List<String>>("add") {
			@Override
			public
			int test(List<String> list, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < listSize; j++)
						list.add(randString.next());
				}
				return loops * listSize;
			}
		});
		tests.add(new Test<List<String>>("get") {
			@Override
			public
			int test(List<String> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.get(rand.nextInt(listSize));
				return loops;
			}
		});
		tests.add(new Test<List<String>>("set"){

			@Override
			public
			int test(List<String> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.set(rand.nextInt(listSize), "hi");
				return loops;
			}
		});
		tests.add(new Test<List<String>>("iteradd") {
			@Override
			public
			int test(List<String> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size() / 2;
				ListIterator<String> it = list.listIterator(half);
				for(int i = 0; i< LOOPS; i++) 
					it.add("hi");
				return LOOPS;
			}
		});
		tests.add(new Test<List<String>>("insert") {
			@Override
			public
			int test(List<String> list, TestParam tp) {
				int loops = tp.loops;
				for(int i = 0; i< loops; i++)
					list.add(5,"hi");
				return loops;
			}
		});
		tests.add(new Test<List<String>>("remove") {
			@Override
			public
			int test(List<String> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
					while(list.size() > 5) {
						list.remove(5);
					}
				}
				return loops * size;
			}
		});
		// Tests for queue behavior
		qtests.add(new Test<LinkedList<String>>("addFirst") {

			@Override
			public
			int test(LinkedList<String> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addFirst("hi");
				}
				return loops * size;
			}
		});
		qtests.add(new Test<LinkedList<String>>("addLast") {
			@Override
			public
			int test(LinkedList<String> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i< loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addLast("hi");
				}
				return loops * size;
			}
		});
		qtests.add(new Test<LinkedList<String>>("rmFirst") {
			@Override
			public
			int test(LinkedList<String> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
					while(list.size() > 0)
						list.removeFirst();
				}
				return loops * size;
			}
		});
		qtests.add(new Test<LinkedList<String>>("rmLast") {
			@Override
			public
			int test(LinkedList<String> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
					while(list.size() > 0)
						list.removeLast();
				}
				return loops * size;
			}
		});	
		//static block end
	}
	static class ListTester extends Tester<List<String>>{

		public ListTester(List<String> container, List<Test<List<String>>> tests) {
			super(container, tests);
		}
		//Fill to the appropriate size before each test:
		@Override
		protected List<String> initialize(int size) {
			container.clear();
			container.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
			return container;
		}
		// Convenience method
		public static void run(List<String> list,List<Test<List<String>>> tests) {
			new ListTester(list,tests).timedTest();
		}
		public static void main(String[] args) {
			if(args.length > 0)
				Tester.defaultParams = TestParam.array(args);
			// Can only do thes two tests on an array
			Tester<List<String>> arrayTest = new Tester<List<String>>(null,tests.subList(1, 3)) {
				// This will be called before each test. It prduces a non-resizeable array-backed list:
				@Override
				protected List<String> initialize(int size) {
					String[] ia = Generated.array(String.class, new CountingGenerator.String(), size);
					return Arrays.asList(ia);
				}
			};
			arrayTest.setHeadline("Array as List");
			arrayTest.timedTest();
			Tester.defaultParams = TestParam.array(10,5000,100,5000,1000,1000,10000,200);
			if(args.length > 0)
				Tester.defaultParams =  TestParam.array(args);
			ListTester.run(new ArrayList<String>(), tests);
			ListTester.run(new LinkedList<String>(), tests);
			ListTester.run(new Vector<String>(), tests);
			Tester.fieldWidth = 12;
			Tester<LinkedList<String>> qTest = new Tester<LinkedList<String>>(new LinkedList<String>(),qtests);
			qTest.setHeadline("Queue tests");
			qTest.timedTest();
		}
		
	}
}
