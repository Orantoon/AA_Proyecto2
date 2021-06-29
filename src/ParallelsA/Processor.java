package ParallelsA;

public class Processor {
    private Thread thread = new Thread();

    public boolean inUse(){ return thread.isAlive();}

    public Thread getThread() {
        return thread;
    }
}
