package practices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Test30 {
	static Random rand = new Random();
	
	public static ArrayList<Integer> createFullArrayList(int size){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < size; i++)
			list.add(rand.nextInt(size));
		return list;
	}
	
	public static LinkedList<Integer> createFullLinkedList(int size){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < size; i++)
			list.add(rand.nextInt(size));
		return list;
	}
	
	public static long timeArrayListSort(ArrayList<Integer> list) {
		long start = System.nanoTime();
		Collections.sort(list);
		long stop = System.nanoTime();
		return stop - start;
	}
	
	public static long timeLinkedListSort(LinkedList<Integer> list) {
		long start = System.nanoTime();
		Collections.sort(list);
		long stop = System.nanoTime();
		return stop - start;
	}
	
	public static long meanSortTimeArrayList(int size,int reps) {
		long time = 0;
		for(int i = 0; i < reps; i++) {
			ArrayList<Integer> aList = createFullArrayList(size);
			long sortTime = timeArrayListSort(aList);
			time += sortTime;
		}
		return time/reps;
	}
	
	public static long meanSortTimeLinkedList(int size,int reps) {
		long time = 0;
		for(int i = 0; i < reps; i++) {
			LinkedList<Integer> aList = createFullLinkedList(size);
			long sortTime = timeLinkedListSort(aList);
			time += sortTime;
		}
		return time/reps;
	}
	
	public static void main(String[] args) {
		System.out.println("Collections.sort() times (nanosecs):");
		System.out.println();
		System.out.println(" Means of 10000 sorts");
		System.out.println();
		System.out.println("List of 10 elements");
		System.out.println("LinkedList:" + meanSortTimeLinkedList(10, 10000));
		System.out.println("ArrayList:" + meanSortTimeArrayList(10, 10000));
		System.out.println();
		System.out.println("List of 100 elements:");
		System.out.println("LinkedList:" + meanSortTimeLinkedList(100, 10000));
		System.out.println("ArrayList:" + meanSortTimeArrayList(100, 10000));
		System.out.println();
		System.out.println("List of 1000 elements:");
		System.out.println("LinkedList:" + meanSortTimeLinkedList(1000, 10000));
		System.out.println("ArrayList:" + meanSortTimeArrayList(1000, 10000));
	}
}
