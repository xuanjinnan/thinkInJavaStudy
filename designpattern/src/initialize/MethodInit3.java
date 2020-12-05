package initialize;

// housekeeping/MethodInit3.java

public class MethodInit3 {
    int i = f();
    int j = g(i); // Illegal forward reference

    int f() {
        return 11;
    }

    int g(int n) {
        return n * 10;
    }

    public static void main(String[] args) {
        MethodInit3 methodInit3 = new MethodInit3();
        System.out.println(methodInit3.j);
    }
}

