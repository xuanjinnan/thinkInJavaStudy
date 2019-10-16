package practices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import examples.test.Test;
import examples.test.TestParam;
import examples.test.Tester;
import net.mindview.util.CountingIntegerList;

public class FastTraversalLinkedList<E> {
	private LinkedList<E> lList = new LinkedList<E>();
	private ArrayList<E> aList = new ArrayList<E>();
	private ArrayList<E> toArrayList(LinkedList<E> lList){
		aList.clear();
		aList.addAll(lList);
		lList.clear();
		return aList;
	}
	private LinkedList<E> toLinkedList(ArrayList<E> aList){
		lList.clear();
		lList.addAll(aList);
		aList.clear();
		return lList;
	}
	public int size() {
		return aList.size() < lList.size() ? lList.size() : aList.size();
	}
	public void clear() {
		if(aList.size() < lList.size())
			lList.clear();
		else
			toLinkedList(aList).clear();
	}
	public void add(E e) {
		if(aList.size() < lList.size())
			lList.add(e);
		else
			toLinkedList(aList).add(e);
	}
	public void add(int i ,E e) {
		if(aList.size() < lList.size())
			lList.add(i,e);
		else
			toLinkedList(aList).add(i,e);
	}
	public boolean addAll(Collection<? extends E> c) {
		if(aList.size() < lList.size())
			return lList.addAll(c);
		else
			return toLinkedList(aList).addAll(c);
	}
	public void addFirst(E e) {
		if(aList.size() < lList.size())
			lList.addFirst(e);
		else
			toLinkedList(aList).addFirst(e);
	}
	public void addLast(E e) {
		if(aList.size() < lList.size())
			lList.addLast(e);
		else
			toLinkedList(aList).addLast(e);
	}
	
	public E set(int i, E e) {
		if(lList.size() < aList.size())
			return aList.set(i, e);
		else
			return toArrayList(lList).set(i, e);
	}
	
	public E remove(int i) {
		if(aList.size() < lList.size())
			return lList.remove(i);
		else
			return toLinkedList(aList).remove(i);
	}
	public E removeFirst() {
		if(aList.size() < lList.size())
			return lList.removeFirst();
		else
			return toLinkedList(aList).removeFirst();
	}
	public E removeLast() {
		if(aList.size() < lList.size())
			return lList.removeLast();
		else
			return toLinkedList(aList).removeLast();
	}
	public E get(int i) {
		if(lList.size() < aList.size())
			return aList.get(i);
		else
			return toArrayList(lList).get(i);
	}
	public Iterator<E> iterator(){
		if(aList.size() < lList.size())
			return aList.iterator();
		else
			return toLinkedList(aList).iterator();
	}
	public ListIterator<E> listIterator(){
		if(aList.size() < lList.size())
			return aList.listIterator();
		else
			return toLinkedList(aList).listIterator();
	}
	public ListIterator<E> listIterator(int i){
		if(aList.size() < lList.size())
			return aList.listIterator(i);
		else
			return toLinkedList(aList).listIterator(i);
	}
	public String toString() {
		return aList.size() > lList.size() ? aList.toString() : 
			lList.size() == 0 ? aList.toString() : lList.toString();
	}
	
	static class ListTester extends Tester<FastTraversalLinkedList<Integer>>{

		public ListTester(FastTraversalLinkedList<Integer> container,
				List<Test<FastTraversalLinkedList<Integer>>> tests) {
			super(container, tests);
		}
		public static void run(FastTraversalLinkedList<Integer> list,List<Test<FastTraversalLinkedList<Integer>>> tests) {
			new ListTester(list,tests).timedTest();
		}
	}

	public static void main(String[] args) {
		Tester.defaultParams = TestParam.array(10,5000,100,5000,1000,1000,10000,200);
		if(args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		ListTester.run(new FastTraversalLinkedList<Integer>(),ListPerformance33.tests);
		Tester.fieldWidth = 12;
		Tester<FastTraversalLinkedList<Integer>>  qTest = new Tester<FastTraversalLinkedList<Integer>>(new FastTraversalLinkedList<Integer>(), ListPerformance33.qTests);
		qTest.setHeadline("Queue tests");
		qTest.timedTest();
	}
}

class ListPerformance33 {
	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<FastTraversalLinkedList<Integer>>> tests =
		new ArrayList<Test<FastTraversalLinkedList<Integer>>>();
	static List<Test<FastTraversalLinkedList<Integer>>> qTests =
		new ArrayList<Test<FastTraversalLinkedList<Integer>>>();
	static {
		tests.add(new Test<FastTraversalLinkedList<Integer>>("add") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
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
		tests.add(new Test<FastTraversalLinkedList<Integer>>("get") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.get(rand.nextInt(listSize));
				return loops;
			}	
		});		
		tests.add(new Test<FastTraversalLinkedList<Integer>>("set") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.set(rand.nextInt(listSize), 47);
				return loops;
			}
		});		
		tests.add(new Test<FastTraversalLinkedList<Integer>>("iteradd") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size()/2;
				ListIterator<Integer> it = list.listIterator(half);
				for(int i = 0; i < LOOPS; i++)
					it.add(47);
				return LOOPS;
			}	
		});		
		tests.add(new Test<FastTraversalLinkedList<Integer>>("insert") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				for(int i = 0; i < loops; i++)
					list.add(5, 47); // Minimize random-access cost
				return loops;
			}
		});		
		tests.add(new Test<FastTraversalLinkedList<Integer>>("remove") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 5)
						list.remove(5); // Minimize random-access cost
				} 
				return loops * size;		
			}
		});		
		// Tests for queue behavior:
		qTests.add(new Test<FastTraversalLinkedList<Integer>>("addFirst") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
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
		qTests.add(new Test<FastTraversalLinkedList<Integer>>("addLast") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addLast(47);
				}
				return loops * size;
			}
		});		
		qTests.add(new Test<FastTraversalLinkedList<Integer>>("rmFirst") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
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
		qTests.add(new Test<FastTraversalLinkedList<Integer>>("rmLast") {
			public int test(FastTraversalLinkedList<Integer> list, TestParam tp) {
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
	}
}
