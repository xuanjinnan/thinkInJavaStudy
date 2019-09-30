package practices;

import java.util.PriorityQueue;
import java.util.Random;

class IntegerTest implements Comparable<IntegerTest> {
	Random r = new Random();
	Integer i = r.nextInt(100);
	public int compareTo(IntegerTest arg) {
		int d = this.i - arg.i;
		return d < 0 ? -1 : d == 0 ? 0 : 1;
	}
	public String toString() {
		return Integer.toString(i);
	}
}

public class Ex11 {
	public static void main(String[] args) {
		PriorityQueue<IntegerTest> pt = 
		new PriorityQueue<IntegerTest>();
		for(int i = 0; i < 20; i++) {
			pt.add(new IntegerTest());
		}
		int size = pt.size();
		for(int i = 0; i < size; i++) {
			System.out.print(pt.poll() + " ");
		}
	}
}