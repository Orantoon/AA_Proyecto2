package ParallelsA;

import GameLogic.Game;

import java.awt.*;
import java.io.IOException;

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
