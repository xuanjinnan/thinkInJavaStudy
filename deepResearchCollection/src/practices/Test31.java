package practices;

import java.util.ArrayList;

class StringContainer{
	private int size = 0;
	private String[] sArray = new String[size];

	public void add(String element) {
		size += 1;
		String[] temp = new String[size];
		for(int i = 0; i < sArray.length; i++)
			temp[i] = sArray[i];
		temp[size - 1] = element;
		sArray = temp;
	}

	public String get(int index) {
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
class StringContainer2{
	private int size = 1000;
	private int index = 0;
	private String[] sArray = new String[size];
	// Can add only String:
	public void add(String s) {
		if(index < size) sArray[index++] = s;
	}
	// get() returns only String :
	public String get(int i) {
		if(-1 < i && i < size)
			return sArray[i];
		else
			throw new ArrayIndexOutOfBoundsException(i);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< sArray.length; i++)
			sb.append(sArray[i] + " ");
		return sb.toString();
	}
}

public class Test31 {
	static long addTimeTest(StringContainer sc, int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.add("hi");
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long getTimeTest(StringContainer sc,int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.get(i);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long addTimeTest(ArrayList<String> sc, int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.add("hi");
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long getTimeTest(ArrayList<String> sc,int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.get(i);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long addTimeTest(StringContainer2 sc, int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.add("hi");
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	static long getTimeTest(StringContainer2 sc,int reps) {
		long start = System.nanoTime();
		for(int i = 0; i< reps; i++)
			sc.get(i);
		long stop = System.nanoTime();
		return (stop - start) / reps;
	}
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		StringContainer sc = new StringContainer();
		System.out.println("Add times (nanoseconds):");
		System.out.println("Mean of 1000:");
		System.out.println("ArrayList add():" + addTimeTest(list, 1000));
		System.out.println("StringContainer add():" + addTimeTest(sc, 1000));
		System.out.println("Mean of 10000:");
		System.out.println("ArrayList add():" + addTimeTest(list, 10000));
		System.out.println("StringContainer add():" + addTimeTest(sc, 10000));
		System.out.println();
		System.out.println("Get times (nanoseconds):");
		System.out.println("Mean of 10000:");
		System.out.println("ArrayList get():" + getTimeTest(list, 10000));
		System.out.println("StringCoantiner get():" + getTimeTest(sc, 10000));
		System.out.println();
		System.out.println("Using alternate fixed size (1000) StringContainer2");
		System.out.println("eliminates resizing and copying:");
		ArrayList<String> list2 = new ArrayList<String>();
		StringContainer2 sc2 = new StringContainer2();
		System.out.println("Add times (nanoseconds)");
		System.out.println("Mean of 1000:");
		System.out.println("ArrayList add():" + addTimeTest(list2, 1000));
		System.out.println("StringContainer2 add():" + addTimeTest(sc2, 1000));
		System.out.println();
		System.out.println("Get times (nanoseconds):");
		System.out.println("Mean of 1000:");
		System.out.println("ArrayList get():" + getTimeTest(list2, 1000));
		System.out.println("StringContainer2 get():" + getTimeTest(sc2, 1000));
		
	}
}
