package proxypattern;

import onjava.Nap;

interface Status{
    void run();
}
abstract class StatusMachine{
    protected Status currentStatus;
    protected abstract boolean changeStatus();
    protected void runAll(){
        while(changeStatus()){
            currentStatus.run();
        }
    }
}
class Wash implements Status{

    @Override
    public void run() {
        System.out.println("Wash");
        new Nap(0.5);
    }
}
class Spin implements Status{

    @Override
    public void run() {
        System.out.println("spin");
        new Nap(0.5);
    }
}
class Rinse implements Status{

    @Override
    public void run() {
        System.out.println("Rinse");
        new Nap(0.5);
    }
}
class Washer extends StatusMachine{
    private int i = 0;
    private Status[] statuses = {
      new Wash(),new Spin(),new Rinse(),new Spin()
    };

    @Override
    protected boolean changeStatus() {
        if( i < statuses.length){
            currentStatus = statuses[i++];
            return true;
        }
        return false;
    }

    public Washer() {
        runAll();
    }
}
public class StateMachineDemo {
    public static void main(String[] args) {
        new Washer();
    }
}
