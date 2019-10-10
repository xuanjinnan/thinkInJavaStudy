package examples.test;

public class TestParam {
	public final int size;
	public final int loops;
	public TestParam(int size, int loops) {
		super();
		this.size = size;
		this.loops = loops;
	}
	
	public static TestParam[] array(int... values){
		int size = values.length/2;
		TestParam[] result = new TestParam[size];
		int n = 0;
		for(int i = 0; i< size; i++){
			result[i] = new TestParam(values[n++], values[n++]);
		}
		return result;
	}
	
	public static TestParam[] array(String[] values){
		int[] vals = new int[values.length];
		for(int i = 0; i< vals.length; i++){
			vals[i] = Integer.decode(values[i]);
		}
		return array(vals);
	}
	
	public static void main(String[] args) {
		int n = 0;
		int[] values = {1,2,3};
		TestParam t = new TestParam(values [n++], values[n++]);
		System.out.println(t.size);
		System.out.println(t.loops);
	}
}
