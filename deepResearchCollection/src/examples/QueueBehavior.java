package examples;

import java.util.LinkedList;
import java.util.Queue;

import net.mindview.util.Generator;

public class QueueBehavior {
	private static int count = 10;
	static <T> void test(Queue<T> queue,Generator<T> gen) {
		for(int i = 0; i< count; i++) {
			queue.offer(gen.next());
		}
		while(queue.peek() != null) {
			System.out.print(queue.remove() + " ");
		}
		System.out.println();
	}
	
	static class Gen implements Generator<String>{
		String[] s  = "one two three four five six seven eight nine ten".split(" ");
		int index;
		public String next() {
			return s[index++];
		}
	}
	
	public static void main(String[] args) {
		/*test(new LinkedList<String>(),new Gen());
		test(new PriorityQueue<String>(),new Gen());
		test(new ArrayBlockingQueue<String>(count),new Gen());
		test(new ConcurrentLinkedQueue<String>(),new Gen());
		test(new LinkedBlockingQueue<String>(),new Gen());
		test(new PriorityBlockingQueue<String>(),new Gen());
		*/
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.offer("a");
		linkedList.offer("b");
		linkedList.offer("c");
		linkedList.offer("d");
		System.out.println(linkedList);
		String peek = linkedList.peek();
		System.out.println(peek);
		System.out.println(linkedList);
	}
}
