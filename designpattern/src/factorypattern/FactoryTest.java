package factorypattern;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class FactoryTest {
    public static void test(FactoryMethod factory){
        Stream.of("Circle", "Square", "Triangle",
                "Square", "Circle", "Circle", "Triangle")
                .map(factory::create).limit(7)
//                .forEach(consumer -> {consumer.draw();consumer.erase();});
                .peek(Shape::draw)
                .peek(Shape::erase).count();
    }
}
