package practices;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import net.mindview.util.RandomGenerator;

class DoubleStringb implements Comparable<DoubleStringb>{
	private String a;
	private String b;
	public DoubleStringb(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString() {
		return "[a:" + a + ",b:" + b +"]";
	}
	@Override
	public int compareTo(DoubleStringb o) {
		return a.compareToIgnoreCase(o.a);
	}
	public static class ComparatorB implements Comparator<DoubleStringb>{

		@Override
		public int compare(DoubleStringb o1, DoubleStringb o2) {
			return o1.b.compareToIgnoreCase(o2.b);
		}
		
	}
}
public class Ex42Search {
	public static void main(String[] args) {
		RandomGenerator.String rgs = new RandomGenerator.String(4);
		RandomGenerator.Integer rgi = new RandomGenerator.Integer(100);
		Set<DoubleStringb> set = new HashSet<DoubleStringb>();
		Map<DoubleStringb,Integer> map = new HashMap<DoubleStringb,Integer>();
		for(int i = 0; i< 4; i++){
			String next = rgs.next();
			String next1 = rgs.next();
			set.add(new DoubleStringb(next,next1));
			map.put(new DoubleStringb(next,next1),rgi.next());
		}
		System.out.println("set:" + set);
		System.out.println("map:" + map);
		Set<DoubleStringb> treeSet = new TreeSet<DoubleStringb>(set);
		Map<DoubleStringb,Integer> treeMap = new TreeMap<DoubleStringb,Integer>(map);
		System.out.println("treeSet:" + treeSet);
		System.out.println("treeMap:" + treeMap);
		System.out.println("------------------");
		DoubleStringb.ComparatorB com = new DoubleStringb.ComparatorB();
		Set<DoubleStringb> treeSet1 = new TreeSet<DoubleStringb>(com);
		Map<DoubleStringb,Integer> treeMap1 = new TreeMap<DoubleStringb,Integer>(com);
		treeSet1.addAll(treeSet);
		treeMap1.putAll(treeMap);
		System.out.println("treeSet1:" + treeSet1);
		System.out.println("treeMap1:" + treeMap1);
		
		
	}
}
