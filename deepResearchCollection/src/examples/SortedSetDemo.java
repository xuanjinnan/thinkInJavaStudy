package examples;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetDemo {
	public static void main(String[] args) {
		SortedSet<String> sortedSet = new TreeSet<String>();
		Collections.addAll(sortedSet, "one two three four five six seven eight ".split(" "));
		System.out.println(sortedSet);
		
		String low = sortedSet.first();
		String last = sortedSet.last();
		System.out.println(low);
		System.out.println(last);
		Iterator<String> it = sortedSet.iterator();
		for(int i = 0; i <= 6; i++) {
			System.out.println("i:" + i);
			if(i == 3) {
				low = it.next();
				System.out.println( "for low:" + low);
			}
			if(i == 6) {
				last = it.next();
				System.out.println("for last:" + last);
			} else {
				String next = it.next();
				System.out.println("for next:" + next);
			}
		}
		System.out.println("low:" + low);
		System.out.println("last:" + last);
		System.out.println(sortedSet);
		System.out.println(sortedSet.subSet(low, last));
		System.out.println(sortedSet.headSet(last));
		System.out.println(sortedSet.tailSet(low));
		System.out.println(sortedSet.tailSet(last));
	}
}
