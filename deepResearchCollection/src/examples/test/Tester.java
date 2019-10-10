package examples.test;

public class Tester<C> {
	public static int fieldWidth = 8;
	public static TestParam[] defaultParams = TestParam.array(10,5000,100,5000,1000,5000,10000,5000);
	protected C initialize(int size){
		return container;
	}
	protected C container;
	private String deadline = "";
}
