package javahw4;

import javahw4.Calculator.IAdder;
import javahw4.Calculator.IFlipper;
import javahw4.Calculator.ISubtractor;

public class Subtractor implements ISubtractor{
    private IAdder adder;
    private IFlipper flipper;

    public Subtractor(IAdder adder, IFlipper flipper){
        this.adder = adder;
        this.flipper = flipper;
    }
    @Override
    public int subtract(int a, int b) {
        return adder.add(a, flipper.flip(b));
    }
    
}