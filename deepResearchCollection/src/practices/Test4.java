package practices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import practices.util.TextFile;

public class Test4 {
	public static List<String> getwords(String path){
		return new TextFile(path," ");
	}
	public static Set<String> getSetWords(String path){
		String read = TextFile.read(path);
		String[] split = read.split("\\W+");
		return new HashSet<String>(Arrays.asList(split));
	}
	public static void main(String[] args) {
		String path = "E:\\学习\\framePractice\\seventeenth_deepResearchCollection\\src\\practices\\Test1.java";
		List<String> getwords = getwords(path);
		System.out.println(getwords);
		Set<String> setWords = getSetWords(path);
		System.out.println(setWords);
	}
}
