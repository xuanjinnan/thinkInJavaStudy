package engine;

import java.util.stream.IntStream;

abstract class ApplicationFramework{
    public ApplicationFramework() {
        templateMethod();
    }

    abstract void customize1();

    abstract void customize2();

    //private means automatically final
    private void templateMethod(){
        IntStream.range(0, 5).forEach( n -> {
            customize1();
            customize2();
        });
    }
}

// Create a new application;
class MyApp extends ApplicationFramework{

    @Override
    void customize1() {
        System.out.print("hello");
    }

    @Override
    void customize2() {
        System.out.println(" world !");
    }
}

public class TemplateMethod {
    public static void main(String[] args) {
        new MyApp();
    }
}
