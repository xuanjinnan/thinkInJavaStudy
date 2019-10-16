package practices;

import java.util.ArrayList;

class IntegerContainer{
	private int size = 0;
	private Integer[] sArray = new Integer[size];

	public void add(Integer element) {
		size += 1;
		Integer[] temp = new Integer[size];
		for(int i = 0; i < sArray.length; i++)
			temp[i] = sArray[i];
		temp[size - 1] = element;
		sArray = temp;
	}

	public Integer get(int index) {
		if( -1 < index && index < size)
			return sArray[index];
		else
			throw new ArrayIndexOutOfBoundsException(index);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sArray.length; i++)
			sb.append(sArray[i] + " ");
		return sb.toString();
	}
}
class IntegerContainer2{
	private int size = 1000;
	private int index = 0;
	private Integer[] sArray = new Integer[size];
	// Can add only Integer:
	public void add(Integer s) {
		if(index < size) sArray[index++] = s;
	}
	// get() returns only Integer :
	public Integer get(int i) {
		if(-1 < i && i < size)
			return sArray[i];
		else
			throw new ArrayIndexOutOfBoundsException(i);
	}
	public String toInteger() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< sArray.length; i++)
			sb.append(sArray[i] + " ");
		return sb.toString();
	}
}

public class Test32 {
	static long addTimeTest(IntegerContainer sc, int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.add(47);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long getTimeTest(IntegerContainer sc,int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.get(i);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long addTimeTest(ArrayList<Integer> sc, int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.add(47);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long getTimeTest(ArrayList<Integer> sc,int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.get(i);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long addTimeTest(IntegerContainer2 sc, int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.add(47);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long getTimeTest(IntegerContainer2 sc,int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.get(i);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	public static void main(Integer[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		IntegerContainer sc = new IntegerContainer();
		System.out.println("Add times (nanoseconds):");
		System.out.println("Mean of 1000:");
		System.out.println("ArrayList add():" + addTimeTest(list, 1000));
		System.out.println("IntegerContainer add():" + addTimeTest(sc, 1000));
		System.out.println("Mean of 10000:");
		System.out.println("ArrayList add():" + addTimeTest(list, 10000));
		System.out.println("IntegerContainer add():" + addTimeTest(sc, 10000));
		System.out.println();
		System.out.println("Get times (nanoseconds):");
		System.out.println("Mean of 10000:");
		System.out.println("ArrayList get():" + getTimeTest(list, 10000));
		System.out.println("IntegerCoantiner get():" + getTimeTest(sc, 10000));
		System.out.println();
		System.out.println("Using alternate fixed size (1000) IntegerContainer2");
		System.out.println("eliminates resizing and copying:");
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		IntegerContainer2 sc2 = new IntegerContainer2();
		System.out.println("Add times (nanoseconds)");
		System.out.println("Mean of 1000:");
		System.out.println("ArrayList add():" + addTimeTest(list2, 1000));
		System.out.println("IntegerContainer2 add():" + addTimeTest(sc2, 1000));
		System.out.println();
		System.out.println("Get times (nanoseconds):");
		System.out.println("Mean of 1000:");
		System.out.println("ArrayList get():" + getTimeTest(list2, 1000));
		System.out.println("IntegerContainer2 get():" + getTimeTest(sc2, 1000));
		
	}
}
