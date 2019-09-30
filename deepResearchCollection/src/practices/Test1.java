package practices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import examples.Countries;

public class Test1 {
	public static void main(String[] args) {
		List<String> arrayList = initArrayList(new ArrayList<String>());
		List<String> linkedList = initLinkedList(new LinkedList<String>());
		printCollectionSortShuffle(arrayList, linkedList, 3);
		List<String> arrayList2 = initArrayList2(new ArrayList<String>());
		List<String> linkedList2 = initLinkedList2(new LinkedList<String>());
		printCollectionSortShuffle(arrayList2, linkedList2, 3);
	}
	private static void printCollectionSortShuffle(List<String> arrayList,List<String> linkedList,int shuffleTimes) {
		Collections.sort(arrayList);
		Collections.sort(linkedList);
		System.out.println("arrayList:" + arrayList);
		System.out.println("linkedList:" + linkedList);
		for(int i = 0; i < shuffleTimes; i++) {
			Collections.shuffle(arrayList);
			Collections.shuffle(linkedList);
			System.out.println("arrayList:" + arrayList);
			System.out.println("linkedList:" + linkedList);
		}
	}
	private static List<String> initArrayList(List<String> arrayList){
		for( int i = 0; i < Countries.DATA.length; i++) {
			arrayList.add(Countries.DATA[i][0]);
		}
		return arrayList;
	} 
	private static List<String> initLinkedList(List<String> linkedList){
		for( int i = 0; i < Countries.DATA.length; i++) {
			linkedList.add(Countries.DATA[i][1]);
		}
		return linkedList;
	} 
	private static List<String> initArrayList2(List<String> arrayList){
		for( int i = 0; i < 10; i++) {
			arrayList.add(Countries.DATA[i][0]);
		}
		return arrayList;
	} 
	private static List<String> initLinkedList2(List<String> linkedList){
		for( int i = 0; i < 10; i++) {
			linkedList.add(Countries.DATA[i][1]);
		}
		return linkedList;
	} 
}
