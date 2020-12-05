package proxypattern;
interface StateBase{
    void f();
    void h();
    void g();
    void changeImp(StateBase imp);
}
class State implements StateBase{
    StateBase stateBase;

    public State(StateBase stateBase) {
        this.stateBase = stateBase;
    }

    @Override
    public void f() {
        stateBase.f();
    }

    @Override
    public void h() {
        stateBase.h();
    }

    @Override
    public void g() {
        stateBase.g();
    }

    @Override
    public void changeImp(StateBase imp) {
        stateBase = imp;
    }
}
class Implementation1 implements StateBase{

    @Override
    public void f() {
        System.out.println("Implementation1.f()");
    }

    @Override
    public void h() {
        System.out.println("Implementation1.h()");
    }

    @Override
    public void g() {
        System.out.println("Implementation1.h()");
    }

    @Override
    public void changeImp(StateBase imp) {

    }
}
class Implementation2 implements StateBase{

    @Override
    public void f() {
        System.out.println("Implementation2.f()");
    }

    @Override
    public void h() {
        System.out.println("Implementation2.h()");
    }

    @Override
    public void g() {
        System.out.println("Implementation2.h()");
    }

    @Override
    public void changeImp(StateBase imp) {

    }
}
public class StateDemo {
    static void test(StateBase stateBase){
        stateBase.f();
        stateBase.h();
        stateBase.g();
    }
    public static void main(String[] args) {
        StateBase state = new State(new Implementation1());
        test(state);
        state.changeImp(new Implementation2());
        test(state);
    }
}
