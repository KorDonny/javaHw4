package javahw4;

import javahw4.Calculator.IAdder;
import javahw4.Calculator.IFlipper;
import javahw4.Calculator.IMultiplier;


public class Multiplier implements IMultiplier{
    private IAdder adder;
    private IFlipper flipper;

    public Multiplier(IAdder adder, IFlipper flipper){
        this.adder = adder;
        this.flipper = flipper;
    }
    /**
     * perspective of b
     * b < 0 -> flip a and b and add a to result b times
     * b = 0 -> supress if body and for body
     * b > 0 -> add a to result b times
     */
    @Override
    public int multiply(int a, int b) {
        // if b is negative, flip a and b
        if(b < 0){
            a = flipper.flip(a);
            b = flipper.flip(b);
        }
        int result = 0;
        // if b is 0, return 0
        // if it's not, add a to result b times
        for(int i = 0; i < b; i++){
            result = adder.add(result, a);
        }
        return result;
    }
}