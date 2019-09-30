package examples;

public class AssociativeArray<K,V> {
	private Object[][] pairs;
	private int index;
	public AssociativeArray(int length) {
		pairs = new Object[length][2];
	}
	public void put(K key,V value) {
		if(index > pairs.length-1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		pairs[index] = new Object[] {key,value};
		index ++;
	}
	@SuppressWarnings("unchecked")
	public V get(K key) {
		for(int i = 0; i < pairs.length; i++) {
			if(pairs[i][0].equals(key)) {
				return (V) pairs[i][1];
			}
		}
		return null;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < pairs.length; i++) {
			sb.append(pairs[i][0]);
			sb.append(":");
			sb.append(pairs[i][1]);
			if(i < pairs.length -1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		AssociativeArray<String,String> map = new AssociativeArray<>(6);
		map.put("sky", "blue");
		map.put("grass", "green");
		map.put("ocean", "dancing");
		map.put("tree", "tall");
		map.put("earth", "brown");
		map.put("sun", "warm");
		try {
			map.put("extra", "object");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("too many objects!");
		}
		System.out.println(map);
		System.out.println(map.get("sky"));
	}
}
