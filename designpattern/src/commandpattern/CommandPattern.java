package commandpattern;

import java.util.Arrays;
import java.util.List;

public class CommandPattern {
    public static void main(String[] args) {
        List<Runnable> macro = Arrays.asList(
                ()-> System.out.print("Hello "),
                ()-> System.out.print("World! "),
                ()-> System.out.print("I am command pattern!")
        );
        macro.forEach(Runnable::run);
    }
}
