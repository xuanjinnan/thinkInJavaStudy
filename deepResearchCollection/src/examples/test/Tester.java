package examples.test;

import java.util.List;

public class Tester<C> {
	public static int fieldWidth = 8;
	public static TestParam[] defaultParams = TestParam.array(10,5000,100,5000,1000,5000,10000,5000);
	protected C initialize(int size){
		return container;
	}
	protected C container;
	private String headline = "";
	private List<Test<C>> tests;
	private static String stringField() {
		return "%" + fieldWidth + "s";
	}
	private static String numberField() {
		return "%" + fieldWidth + "d";
	}
	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private TestParam[] paramList = defaultParams;
	public Tester(C container,List<Test<C>> tests) {
		this.container = container;
		this.tests = tests;
		if(container != null)
			headline = container.getClass().getSimpleName();
	}
	public Tester(C container,List<Test<C>> tests,TestParam[] paramList) {
		this(container, tests);
		this.paramList = paramList;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	//Generic method s for convenience
	public static <C> void run(C cntr,List<Test<C>> tests) {
		new Tester<C>(cntr,tests).timedTest();
	}
	private void displayHeader() {
		// calculate width and pad with '-'
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headline.length() -1;
		StringBuilder head = new StringBuilder(width);
		for(int i = 0; i < dashLength/2; i++) {
			head.append('-');
		}
		head.append(' ');
		head.append(headline);
		head.append(' ');
		for(int i = 0; i < dashLength/2; i++)
			head.append('-');
		System.out.println(head);
		//print column headers
		System.out.format(sizeField, "size");
		for(Test test : tests)
			System.out.format(stringField(), test.name);
		System.out.println();
		
	}
	public void timedTest() {
		displayHeader();
		for(TestParam param : paramList) {
			System.out.format(sizeField,param.size);
			for(Test<C> test : tests) {
				C kontainer = initialize(param.size);
				long start = System.nanoTime();
				int reps = test.test(kontainer, param);
				long duration = System.nanoTime() - start;
				long timePerRep = duration/reps;
				System.out.format(numberField(), timePerRep);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		String format = "%8d";
		int i = 1234567891;
		System.out.format(format, i);
	}
}
