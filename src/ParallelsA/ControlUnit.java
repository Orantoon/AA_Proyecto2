package ParallelsA;

public class ControlUnit {
    private Memory mem = new Memory();

    public void executeInstruction(){
        Processor P = mem.getFreeProcessor();
        if (P != null){
            Thread t = P.getThread();
            t = new Thread(() ->{
                System.out.println("Here");
            });
        }
    }
}
