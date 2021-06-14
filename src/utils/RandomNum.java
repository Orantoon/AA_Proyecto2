package Utils;

import java.util.Random;

public class RandomNum {
    public RandomNum(){}

    public int getRandom(){
        int max = 99;
        return new Random().nextInt(max)+1;
    }
}