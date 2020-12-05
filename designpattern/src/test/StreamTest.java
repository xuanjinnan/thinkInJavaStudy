package test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class A{
    @Override
    public String toString() {
        System.out.println("A");
        return "A";
    }
}
class B{
    @Override
    public String toString() {
        System.out.println("B");
        return "B";
    }
}
class C{
    @Override
    public String toString() {
        System.out.println("C");
        return "C";
    }
}
interface FactoryMethod{
    Object create(String type);
}
class FactoryMethod1 implements FactoryMethod{

    @Override
    public Object create(String type) {
            switch (type){
                case "a":return new A();
                case "b":return new B();
                case "c":return new C();
                default:
                    System.out.println("error");
                    return "error";
            }
    }
}
public class StreamTest {

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new FactoryMethod1();
        Stream<String> a = Stream.of("a", "b", "c");
        a.map(factoryMethod::create).peek(Object::toString).count();
        System.out.println(a);
       /* a.forEach(n -> {
            System.out.println(n);
        });*/
        /*List<String> collect = a.collect(Collectors.toList());
        System.out.println(collect);*/
        
    }
}
