package examples.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import net.mindview.util.CountingGenerator;
import net.mindview.util.CountingIntegerList;
import net.mindview.util.Generated;

public class ListPerformance {
	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qtests = new ArrayList<Test<LinkedList<Integer>>>();
	static {
		//static block start
		tests.add(new Test<List<Integer>>("add") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < listSize; j++)
						list.add(j);
				}
				return loops * listSize;
			}
		});
		tests.add(new Test<List<Integer>>("get") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.get(rand.nextInt(listSize));
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("set"){

			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.set(rand.nextInt(listSize), 47);
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("iteradd") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size() / 2;
				ListIterator<Integer> it = list.listIterator(half);
				for(int i = 0; i< LOOPS; i++) 
					it.add(47);
				return LOOPS;
			}
		});
		tests.add(new Test<List<Integer>>("insert") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				for(int i = 0; i< loops; i++)
					list.add(5,47);
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("remove") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 5) {
						list.remove(5);
					}
				}
				return loops * size;
			}
		});
		// Tests for queue behavior
		qtests.add(new Test<LinkedList<Integer>>("addFirst") {

			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addFirst(47);
				}
				return loops * size;
			}
		});
		qtests.add(new Test<LinkedList<Integer>>("addLast") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i< loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addLast(47);
				}
				return loops * size;
			}
		});
		qtests.add(new Test<LinkedList<Integer>>("rmFirst") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 0)
						list.removeFirst();
				}
				return loops * size;
			}
		});
		qtests.add(new Test<LinkedList<Integer>>("rmLast") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 0)
						list.removeLast();
				}
				return loops * size;
			}
		});	
		//static block end
	}
	static class ListTester extends Tester<List<Integer>>{

		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}
		//Fill to the appropriate size before each test:
		@Override
		protected List<Integer> initialize(int size) {
			container.clear();
			container.addAll(new CountingIntegerList(size));
			return container;
		}
		// Convenience method
		public static void run(List<Integer> list,List<Test<List<Integer>>> tests) {
			new ListTester(list,tests).timedTest();
		}
		public static void main(String[] args) {
			if(args.length > 0)
				Tester.defaultParams = TestParam.array(args);
			// Can only do thes two tests on an array
			Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null,tests.subList(1, 3)) {
				// This will be called before each test. It prduces a non-resizeable array-backed list:
				@Override
				protected List<Integer> initialize(int size) {
					Integer[] ia = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
					return Arrays.asList(ia);
				}
			};
			arrayTest.setHeadline("Array as List");
			arrayTest.timedTest();
			Tester.defaultParams = TestParam.array(10,5000,100,5000,1000,1000,10000,200);
			if(args.length > 0)
				Tester.defaultParams =  TestParam.array(args);
			ListTester.run(new ArrayList<Integer>(), tests);
			ListTester.run(new LinkedList<Integer>(), tests);
			ListTester.run(new Vector<Integer>(), tests);
			Tester.fieldWidth = 12;
			Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(new LinkedList<Integer>(),qtests);
			qTest.setHeadline("Queue tests");
			qTest.timedTest();
		}
		
	}
}
