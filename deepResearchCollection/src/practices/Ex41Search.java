package practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.mindview.util.RandomGenerator;

class DoubleString implements Comparable<DoubleString>{
	private String a;
	private String b;
	public DoubleString(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString() {
		return "[a:" + a + ",b:" + b +"]";
	}
	@Override
	public int compareTo(DoubleString o) {
		return a.compareToIgnoreCase(o.a);
	}
	public static class ComparatorB implements Comparator<DoubleString>{

		@Override
		public int compare(DoubleString o1, DoubleString o2) {
			return o1.b.compareToIgnoreCase(o2.b);
		}
		
	}
}
public class Ex41Search {
	public static void main(String[] args) {
		RandomGenerator.String rgs = new RandomGenerator.String();
		DoubleString[] dsa = new DoubleString[10];
		List<DoubleString> list = new ArrayList<DoubleString>();
		for(int i = 0; i< dsa.length; i++){
			String next = rgs.next();
			String next1 = rgs.next();
			dsa[i] = new DoubleString(next,next1);
			list.add(new DoubleString(next,next1));
		}
		System.out.println("dsa:" + Arrays.toString(dsa));
		System.out.println("list:" + list);
		Collections.sort(list);
		Arrays.sort(dsa);
		System.out.println("dsa:" + Arrays.toString(dsa));
		System.out.println("list:" + list);
		System.out.println("------------------");
		DoubleString.ComparatorB com = new DoubleString.ComparatorB();
		Collections.sort(list,com);
		Arrays.sort(dsa,com);
		System.out.println("dsa:" + Arrays.toString(dsa));
		System.out.println("list:" + list);
		
	}
}
