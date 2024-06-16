package javahw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javahw4.Calculator.IAdder;
import javahw4.Calculator.IDivider;
import javahw4.Calculator.IFlipper;
import javahw4.Calculator.IMultiplier;
import javahw4.Calculator.ISubtractor;

/**
     * Focus of test :
     * 
     * - In Black Box Testing, we focus on the inputs and outputs of the software
     * 1. Adder, Flipper, Subtractor, Multiplier, Divider inputs with positive, negative, and zero values
     * 2. Speical case in Divider (dividing by zero)
     * 2.1 Divider with zero values
     * 2.1.1 top of zero value -> bottom can't be zero
     * 2.1.2 bottom of zero value -> Exception (Even if, top is zero value, arithmetic definition is not defined)
     * 3. Some test cases will be compressed into one test case
     * 3.1 Adder, Subtractor, Multiplier with zero value
     * 
     * - In White Box Testing, we focus on the internal structure of the software
     * 1. Statement Coverage
     * 2. Decision Coverage
     * 2.1 Adder, Subtractor, Flipper don't have decision structure
     * 2.2 Multiplier, Divider have decision structure
     * 3. Condition Coverage
     * 3.1 Multiplier and Divider have compound condition - So, others will be same as Statement Coverage
     * 4. D/C Coverage
     * 4.1 Same as 3.1. So, it will be same as Statement Coverage
     * 5. MC Coverage
     * 5.1 Same as 3.1. So, others will be same as Statement Coverage
     * 6. MC/DC Coverage
     * 6.1 Same as 3.1. So, others will be same as Statement Coverage
     */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class totalTest {
    private IAdder adder;
    private IFlipper flipper;
    private ISubtractor subtractor;
    private IMultiplier multiplier;
    private IDivider divider;

    @BeforeAll
    public void setUp(){
        adder = new Adder();
        flipper = new Flipper();
        subtractor = new Subtractor(adder, flipper);
        multiplier = new Multiplier(adder, flipper);
        divider = new Divider(adder, flipper);
    }
    /* Black Box Testing */

    /**
     * case 1 : two positive values
     * case 2 : two negative values
     * case 3 : one positive value and one negative value
     * case 4 : one positive value and zero value
     * case 5 : one negative value and zero value
     * case 6 : two zero values
     * - exposed case -
     * case 7 : invalid input (such as, String) ~ complie error
     */
    @Test
    public void adderTest(){
        Assertions.assertEquals(8, adder.add(3, 5));
        Assertions.assertEquals(-8, adder.add(-3, -5));
        Assertions.assertEquals(2, adder.add(3, -1));
        Assertions.assertEquals(3, adder.add(3, 0));
        Assertions.assertEquals(-5, adder.add(0, -5));
        Assertions.assertEquals(0, adder.add(0, 0));
        // Assertions.assertThrows(NumberFormatException.class, () -> adder.add(3, "5"));
    }
    /**
     * case 1 : two positive values
     * case 2 : two negative values
     * case 3 : one positive value and one negative value
     * case 4 : one positive value and zero value
     * case 5 : one negative value and zero value
     * case 6 : two zero values
     * case 7 : invalid input (such as, String)
     */
    @Test
    public void substractTest(){
        Assertions.assertEquals(-2, subtractor.subtract(3, 5));
        Assertions.assertEquals(2, subtractor.subtract(-3, -5));
        Assertions.assertEquals(4, subtractor.subtract(3, -1));
        Assertions.assertEquals(3, subtractor.subtract(3, 0));
        Assertions.assertEquals(-5, subtractor.subtract(0, 5));
        Assertions.assertEquals(0, subtractor.subtract(0, 0));
    }
    /**
     * case 1 : positive value
     * case 2 : negative value
     * case 3 : zero value
     * case 4 : invalid input (such as, String)
     */
    @Test
    public void flipperTest(){
        Assertions.assertEquals(-5, flipper.flip(5));
        Assertions.assertEquals(5, flipper.flip(-5));
        Assertions.assertEquals(0, flipper.flip(0));
    }
    /**
     * case 1 : two positive values
     * case 2 : two negative values
     * case 3 : one positive value and one negative value
     * case 4 : one valid value and zero value
     * case 5 : two zero values
     * case 6 : invalid input (such as, String)
     */
    @Test
    public void multiplierTest(){
        Assertions.assertEquals(15, multiplier.multiply(3, 5));
        Assertions.assertEquals(15, multiplier.multiply(-3, -5));
        Assertions.assertEquals(-15, multiplier.multiply(3, -5));
        Assertions.assertEquals(0, multiplier.multiply(3, 0));
        Assertions.assertEquals(0, multiplier.multiply(0, 5));
        Assertions.assertEquals(0, multiplier.multiply(0, 0));
    }
    /**
     * case 1 : two positive values
     * case 2 : two negative values
     * case 3 : one positive value and one negative value
     * case 4 : zero value at top
     * case 5 : zero value at bottom
     * case 6 : two zero values
     * case 7 : invalid input (such as, String)
     */
    @Test
    public void dividerTest(){
        int[][] dvCase = {{15,5},{-15,-5},{15,-5},{0,15},{15,0},{0,0}};
        for (int[] test : dvCase) {
            try{
                Assertions.assertEquals(test[0]/test[1],divider.divide(test[0], test[1]));
            }
            catch(ArithmeticException e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
    @Test
    public void invalidTest(){
        // System.in.toString();
        // Assertions.assertThrows(NumberFormatException.class, () -> adder.add(3, obj));
        // Assertions.assertThrows(NumberFormatException.class, () -> subtractor.subtract(3, "5"));
        // Assertions.assertThrows(NumberFormatException.class, () -> flipper.flip("5"));
        // Assertions.assertThrows(NumberFormatException.class, () -> multiplier.multiply(3, "5"));
        // Assertions.assertThrows(NumberFormatException.class, () -> divider.divide(3, "5"));
    }

    /* White Box Testing with statement coverage Test */
    @Test
    public void statementCoverage(){
        // 100 %
        Assertions.assertEquals(-5,flipper.flip(5));

        // 100 %
        Assertions.assertEquals(8,adder.add(3, 5));

        // 100 %
        Assertions.assertEquals(-2,subtractor.subtract(3, 5));

        // 5/7 = 71.4 %
        Assertions.assertEquals(15,multiplier.multiply(3, 5));

        // 6/11 = 54.5 %
        Assertions.assertEquals(0,divider.divide(3, 5));
    }
    //done

    /** Adder, Subtractor, Flipper don't need decision coverage test. */
    @Test
    public void decisionCoverage(){
        // 100 %
        Assertions.assertEquals(-15,multiplier.multiply(3, -5));
        Assertions.assertEquals(0,multiplier.multiply(3, 0));

        // 100 %
        Assertions.assertEquals(-1,divider.divide(-6, 5));
        Assertions.assertEquals(0,divider.divide(0, 5));
    }

    /** Adder, Subtractor, Flipper don't need condition coverage test. */
    @Test
    public void conditionCoverage(){
        // F T / T F - 100 %
        Assertions.assertEquals(15,multiplier.multiply(3, 5));
        Assertions.assertEquals(0,multiplier.multiply(3, 0));

        // T T F / F F T - 100 %
        Assertions.assertEquals(1,divider.divide(-6, -5));
        Assertions.assertEquals(0,divider.divide(-3, 5));
    }

    /** Adder, Subtractor, Flipper don't need DC coverage test. */
    @Test
    public void dcCoverage(){
        int[][] mulCase = {{3,-2},{3,2},{3,0},{0,5},{-3,2},{-3,-2}};
        for (int[] test : mulCase) {
            Assertions.assertEquals(test[0]*test[1],multiplier.multiply(test[0], test[1]));
        }

        int[][] dvCase = {{6,-2},{6,2},{6,0},{2,10},{-6,2},{-6,-2}};
        for (int[] test : dvCase) {
            try{
                int result = divider.divide(test[0], test[1]);
                Assertions.assertEquals(test[0]/test[1],result);
            }
            catch(ArithmeticException e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    /** Adder, Subtractor, Flipper don't need MC coverage test. */
    @Test
    public void mcCoverage(){
        // T X(always second will be true) / F T / F F - 75 %
        int[][] mulCase = {{3,-5},{3,-1},{3,0}};
        for (int[] test : mulCase) {
            Assertions.assertEquals(test[0]*test[1],multiplier.multiply(test[0], test[1]));
        }

        //1
        // 0,0 X,0
        //2
        // b <= |a|, b > |a|
        //3
        // -+, +- : true
        // ++, --, 0+, 0- : false
        // thrown case : 0,0 X,0 = 2
        // after the thrown exception pass,
        // way can be 2(less or same, more) X 4 ( -- -+ +- ++ ) + 2 ( 0+ 0- ) = 10
        int[][] dvCase = {
            // thrown
            {0,0},
            {5,0},

            // less or same
            {6,5},
            {6,-5},
            {-6,5},
            {-6,-5},

            // more
            {5,6},
            {5,-6},
            {-5,6},
            {-5,-6},
            // 0+
            {0,5},
            // 0-
            {0,-5},
        };
        for (int[] test : dvCase) {
            try{
                int result = divider.divide(test[0], test[1]);
                Assertions.assertEquals(test[0]/test[1],result);
            }
            catch(ArithmeticException e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    /** Adder, Subtractor, Flipper don't need MC/DC coverage test. */
    @Test
    public void mcDcCoverage(){
        int[][] mulCase = {{10,-2},{10,2},{10,0}};
        for (int[] test : mulCase) {
            Assertions.assertEquals(test[0]*test[1],multiplier.multiply(test[0], test[1]));
        }

        int[][] dvCase = {{10,-2},{10,2},{10,0},{0,0},{2,10},{-10,2},{10,-2}};
        for (int[] test : dvCase) {
            try{
                int result = divider.divide(test[0], test[1]);
                Assertions.assertEquals(test[0]/test[1],result);
            }
            catch(ArithmeticException e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
