package javahw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javahw4.Calculator.IAdder;
import javahw4.Calculator.IFlipper;
import javahw4.Calculator.IMultiplier;

public class MultiplierTest{
    private IMultiplier multiplier;

    @BeforeEach
    public void setUp(){
        IAdder adder = Mockito.mock(IAdder.class);
        IFlipper flipper = Mockito.mock(IFlipper.class);

        Mockito.when(flipper.flip(-2)).thenReturn(2);
        Mockito.when(flipper.flip(3)).thenReturn(-3);

        Mockito.when(adder.add(0, 3)).thenReturn(3);
        Mockito.when(adder.add(3, 3)).thenReturn(6);
        Mockito.when(adder.add(0,-3)).thenReturn(-3);
        Mockito.when(adder.add(-3, -3)).thenReturn(-6);
        multiplier = new Multiplier(adder, flipper);
    }
    @Test
    public void multiplyTest1(){
        Assertions.assertEquals(6, multiplier.multiply(3,2));
    }
    @Test
    public void multiplyTest2(){
        Assertions.assertEquals(-6, multiplier.multiply(3,-2));
    }
}