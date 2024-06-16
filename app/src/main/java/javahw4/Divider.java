package javahw4;

import javahw4.Calculator.IDivider;
import javahw4.Calculator.IFlipper;
import javahw4.Calculator.IAdder;

public class Divider implements IDivider{
    private IAdder adder;
    private IFlipper flipper;

    // in white box testing, structure will show the results in quotient of a and b
    // then, how about the mod of a and b?
    // this should be defined exactly in the specification

    public Divider(IAdder adder, IFlipper flipper){
        this.adder = adder;
        this.flipper = flipper;
    }
    @Override
    public int divide(int a, int b) throws ArithmeticException {
        // 0,0 X,0
        if (b == 0) {
            if (a == 0) throw new ArithmeticException("Indeterminate form");
            else throw new ArithmeticException("Division by zero");
        }
        // X, -
        if(b < 0){
            a = flipper.flip(a);
            b = flipper.flip(b);
        }
        int result = 0;
        // if b is 0, return 0
        // if b is bigger than a, return 0
        // if b is positive, add 1 to result b times
        int times = Math.abs(b);
        // b <= |a|, b > |a|
        while(times <= Math.abs(a)){
            result = adder.add(result, 1);
            times = adder.add(times, b);
        }
        // now on this, b can't be 0
        // -+, +- : true
        // ++, --, 0+, 0- : false
        if((a < 0 && b > 0)||(a > 0 && b < 0)){
            result = flipper.flip(result);
        }
        return result;
    }
}
