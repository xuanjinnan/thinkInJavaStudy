package practices;

import java.util.Arrays;

import examples.SlowMap;
import practices.util.TextFile;

public class Test15 {
	public static void main(String[] args) {
		SlowMap<String,Integer> map = new SlowMap<String,Integer>();
		String read = TextFile.read("E:\\学习\\framePractice\\seventeenth_deepResearchCollection\\src\\practices\\Test12.java");
		String[] words = read.split("[\\W+]");
		System.out.println(Arrays.toString(words));
		for(String str : words) {
			Integer a = map.containsKey(str) ? map.put(str, map.get(str) + new Integer(1)) : map.put(str, 1);
		}
		System.out.println(map);
	}
}
