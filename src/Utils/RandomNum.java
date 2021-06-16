package Utils;

import java.util.Random;

public class RandomNum {
    public RandomNum(){}

    public int getRandom(int max){
        max -= 1;
        return new Random().nextInt(max)+1;
    }
}