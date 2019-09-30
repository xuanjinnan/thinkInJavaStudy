package examples;

public class DequeTest {
	static void fillTest(Deque<Integer> deque) {
		for(int i = 20; i < 27; i++) {
			deque.addFirst(i);
		}
		for(int i = 50; i < 55; i++) {
			deque.addLast(i);
		}
	}
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		fillTest(deque);
		System.out.println(deque);
		while(deque.size() != 0) {
			System.out.println(deque.removeFirst());
		}
		fillTest(deque);
		System.out.println(deque);
		while(deque.size() != 0) {
			System.out.println(deque.removeLast());
		}
	}
}
