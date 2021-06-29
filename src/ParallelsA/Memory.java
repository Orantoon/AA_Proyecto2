package ParallelsA;

public class Memory {
    private Processor[] P = new Processor[4];

    public Memory(){
        for (int i = 0; i < 4; i++){
            P[i] = new Processor();
        }
    }

    public Processor getFreeProcessor(){
        for (Processor p: P){
            if (!p.inUse())
                return p;
        } return null;
    }
}
