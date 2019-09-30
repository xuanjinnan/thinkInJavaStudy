package practices;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import practices.util.TextFile;

public class Test13 {
	public static void main(String[] args) {
		Map<String,Integer> map = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER);
		String read = TextFile.read("E:\\学习\\framePractice\\seventeenth_deepResearchCollection\\src\\practices\\Test12.java");
		String[] words = read.split("[\\W+]");
		System.out.println(Arrays.toString(words));
		for(String str : words) {
			Integer a = map.containsKey(str) ? map.put(str, map.get(str) + new Integer(1)) : map.put(str, 1);
		}
		System.out.println(map);
	}
}
