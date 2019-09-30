package examples;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class SetType{
	int i;
	public SetType(int i) {
		this.i = i;
	}
	public boolean equals(Object o) {
		return o instanceof SetType ? i == ((SetType)o).i : false;
	}
	public String toString() {
		return Integer.valueOf(i).toString();
	}
}
class HashType extends SetType{
	public HashType(int i) {
		super(i);
	}
	@Override
	public int hashCode() {
		return i;
	}
}
class TreeType extends SetType implements Comparable<SetType>{
	public TreeType(int i) {
		super(i);
	}
	@Override
	public int compareTo(SetType o) {
		return o.i < i ? -1 : (o.i == i ? 0 : 1);
	}
} 
public class TypeForSets {
	public static <T> Set<T> fill(Set<T> set , Class<T> c){
		for(int i = 0; i < 10; i++) {
			try {
				set.add(c.getConstructor(int.class).newInstance(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return set;
	}
	public static <T> void test(Set<T> set,Class<T> c) {
		fill(set,c);
		fill(set,c);
		fill(set,c);
		System.out.println(set);
	}
	public static void main(String[] args) {
		test(new HashSet<HashType>(),HashType.class);
		test(new LinkedHashSet<HashType>(),HashType.class);
		test(new TreeSet<TreeType>(),TreeType.class);
		//don't work
		test(new HashSet<SetType>(),SetType.class);
		test(new LinkedHashSet<SetType>(),SetType.class);
		test(new HashSet<TreeType>(),TreeType.class);
		test(new LinkedHashSet<TreeType>(),TreeType.class);
		//excute exception
		try {
			test(new TreeSet<SetType>(),SetType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			test(new TreeSet<HashType>(),HashType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
