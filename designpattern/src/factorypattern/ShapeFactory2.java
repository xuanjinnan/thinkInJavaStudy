package factorypattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ShapeFactory2 implements FactoryMethod {
    Map<String, Constructor> facotories = new HashMap<>();
    static Constructor load(String id){
        System.out.println("loading " + id);
        try {
            return Class.forName("factorypattern." +id).getConstructor();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new BadShapeCreation(id);
        }
    }
    @Override
    public Shape create(String type) {

        try {
            return (Shape) facotories.computeIfAbsent(type,ShapeFactory2::load).newInstance();
        } catch (InstantiationException |IllegalAccessException |InvocationTargetException e) {
            throw new BadShapeCreation(type);
        }
    }

    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory2());
    }
}
