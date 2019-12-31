package examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestReStoreFromSerializable {
	public static void main(String[] args) throws Exception, IOException {
		System.out.println(System.getProperty("java.class.path"));
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:\\学习\\thinkInJava_at_git\\javaIOSystem\\X.file"));
		Object readObject = in.readObject();
		System.out.println(readObject.getClass());
		in.close();
	}
}
