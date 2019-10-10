package examples.test;

import examples.test.TestParam;

public abstract class Test<C> {

	String name;

	public Test(String name) {
		super();
		this.name = name;
	}
	
	abstract int test(C container,TestParam tp);
}
