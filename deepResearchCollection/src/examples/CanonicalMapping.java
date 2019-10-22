package examples;

import java.util.WeakHashMap;

class Element{
	private String ident;

	public Element(String ident) {
		super();
		this.ident = ident;
	}

	@Override
	public String toString() {
		return ident;
	}

	@Override
	public int hashCode() {
		return ident.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		return true;
	}
	
	protected void finalize() {
		System.out.println("Finalizing" + getClass().getSimpleName() + " " + ident);
	}
}
class Key extends Element{

	public Key(String ident) {
		super(ident);
	}
	
}
class Value extends Element{

	public Value(String ident) {
		super(ident);
	}
	
}
public class CanonicalMapping {
	public static void main(String[] args) {
		int size = 1000;
		Key[] keys = new Key[size];
		WeakHashMap<Key,Value> map = new WeakHashMap<Key,Value>();
		for(int i = 0; i < size; i++) {
			Key k = new Key(Integer.toString(i));
			Value v = new Value(Integer.toString(i));
			if(i % 3 == 0)
				keys[i] = k;
			map.put(k, v);
		}
		System.gc();
	}
}
