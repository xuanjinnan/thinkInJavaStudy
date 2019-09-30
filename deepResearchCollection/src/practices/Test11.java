package practices;

import java.util.PriorityQueue;
import java.util.Random;

class Test11Content implements Comparable<Test11Content>{
	Integer i;
	private static Random ran = new Random();
	public Test11Content() {
		i = ran.nextInt(100);
	}
	@Override
	public int compareTo(Test11Content o) {
		return (i > o.i) ? +1 : ((i == o.i) ? 0 : -1); 
	}
	
	@Override
	public String toString() {
		return "Test11Content [i=" + i + "]";
	}
}
public class Test11{
	public static void main(String[] args) {
		PriorityQueue<Test11Content> priorityQueue = new PriorityQueue<Test11Content>();
		priorityQueue.add(new Test11Content());
		priorityQueue.add(new Test11Content());
		priorityQueue.add(new Test11Content());
		priorityQueue.add(new Test11Content());
		while(!priorityQueue.isEmpty()) {
			System.out.println(priorityQueue.poll());
		}
	}
}
