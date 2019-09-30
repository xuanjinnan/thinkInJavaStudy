package practices;

public class Test8 {
	public static void main(String[] args) {
		SList<String> sl = new SList<String>();
		System.out.println(sl);
		SListIterator<String> slIter = sl.iterator();
		System.out.println("inserting \"hi\"");
		slIter.insert("hi");
		System.out.println(sl);
		System.out.println("inserting \"there\"");
		slIter.insert("there");
		System.out.println(sl);
		System.out.println("inserting \"sweet\"");
		slIter.insert("sweet");
		System.out.println(sl);
		System.out.println("inserting \"pie\"");
		slIter.insert("pie");
		System.out.println(sl);
		
		SListIterator<String> slIter2 = sl.iterator();
		System.out.println("remove \"hi\"");
		slIter2.remove();
		System.out.println(sl);
		System.out.println("insert \"hello\"");
		slIter2.insert("hello");
		System.out.println(sl);
		System.out.println("remove \"there\"");
		slIter2.remove();
		System.out.println(sl);
		System.out.println("remove \"sweet\"");
		slIter2.remove();
		System.out.println(sl);
		System.out.println("remove \"hello\"");
		
		SListIterator<String> slIter3 = sl.iterator();
		slIter3.remove();
		System.out.println(sl);
	}
}

class Link<T>{
	T t;
	Link<T> next;
	public Link(Link<T> l,T t) {
		this.next = l;
		this.t = t;
	}
	public Link(T t) {
		this(null,t);
	}
	public String toString() {
		if(t == null) return "null";
		return t.toString();
	}
}
class SListIterator<T>{
	Link<T> current;
	SListIterator(Link<T> link){
		current = link;
	}
	public boolean hasNext() {
		return current.next !=null;
	}
	public Link<T> next(){
		current = current.next;
		return current;
	}
	public void insert(T t) {
		current.next = new Link<T>(current.next,t);
		current = current.next;
	}
	public void remove() {
		if(current.next != null) {
			current.next = current.next.next;
		}
	}
}
class SList<T>{
	private Link<T> headLink = new Link<T>(null);
	SListIterator<T> iterator(){
		return new SListIterator<T>(headLink);
	}
	public String toString() {
		if(headLink.next == null) return "SList:[]";
		StringBuilder sb = new StringBuilder();
		sb.append("SList: [");
		SListIterator<T> it = this.iterator();
		while(it.hasNext()) {
			sb.append(it.next() + (it.hasNext() ? ",": ""));
		}
		sb.append("]");
		return sb.toString();
	}
}