package practices;

import java.util.Arrays;

import examples.SimpleHashMap;
import practices.util.TextFile;

public class Test19 {
	public static void main(String[] args) {
		SimpleHashMap<String,Integer> map = new SimpleHashMap<String,Integer>();
		String read = TextFile.read("E:\\学习\\framePractice\\seventeenth_deepResearchCollection\\src\\practices\\Test12.java");
		String[] words = read.split("[\\W+]");
		System.out.println(Arrays.toString(words));
		for(String str : words) {
			Integer a = map.containsKey(str) ? map.put(str, map.get(str) + new Integer(1)) : map.put(str, 1);
		}
		System.out.println(map);
	}
}
