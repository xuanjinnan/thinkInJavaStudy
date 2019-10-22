package examples;

import java.util.LinkedList;
import java.util.Stack;

enum Month{
	JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JUNLY,AUGUEST,SEPTEMBER,OCTOBER,NOVEMBER
}
public class Stacks {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		for(Month m : Month.values())
			stack.push(m.toString());
		System.out.println("stack= " + stack);
		//Treating a stack as a vector:
		stack.add("NEW YEAR");
		System.out.println("stack= " + stack);
		System.out.println("element 5 = "+ stack.elementAt(5));
		System.out.println("poping elemnets");
		while(!stack.empty())
			System.out.print(stack.pop() + " ");
		System.out.println();
		//Using a LinkedList as a Stack
		LinkedList<String> lstack = new LinkedList<String>();
		for(Month m : Month.values())
			lstack.addFirst(m.toString());
		while(!lstack.isEmpty())
			System.out.print(lstack.removeFirst() + " ");
		System.out.println();
		//Using the Stack class from the Holding Your Objcets Chapter:
		net.mindview.util.Stack<String> stack1 = new net.mindview.util.Stack<String>();
		for(Month m : Month.values())
			stack1.push(m.toString());
		while(!stack1.empty())
			System.out.print(stack1.pop() + " ");
		
		
	}
}
