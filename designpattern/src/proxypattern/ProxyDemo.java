package proxypattern;

interface ProxyBase{

    void f();

    void g();

    void h();
}

class Proxy implements ProxyBase{
    private ProxyBase implementation;

    public Proxy() {
        this.implementation = new Implementation();
    }

    @Override
    public void f() {
        implementation.f();
    }

    @Override
    public void g() {
        implementation.g();
    }

    @Override
    public void h() {
        implementation.h();
    }
}
class Implementation implements ProxyBase{

    @Override
    public void f() {
        System.out.println("Implementation.f()");
    }

    @Override
    public void g() {
        System.out.println("Implementation.g()");
    }

    @Override
    public void h() {
        System.out.println("Implementation.h()");
    }
}
public class ProxyDemo {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.f();
        proxy.g();
        proxy.h();
    }
}
