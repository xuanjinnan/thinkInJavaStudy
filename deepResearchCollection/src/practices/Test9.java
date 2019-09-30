package practices;

import java.util.SortedSet;
import java.util.TreeSet;

import net.mindview.util.RandomGenerator;

public class Test9 {
	public static void main(String[] args) {
		SortedSet<String> sortedSet  = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		RandomGenerator.String s = new RandomGenerator.String(5); 
		for(int i = 0; i< 20; i++) {
			sortedSet.add(s.next());
		}
		System.out.println(sortedSet);
	}
}
