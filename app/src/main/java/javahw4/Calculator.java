package javahw4;

public class Calculator {
    public interface IAdder{
        public int add(int a, int b);
    }
    public interface IFlipper{
        public int flip(int a);
    }
    public interface ISubtractor{
        public int subtract(int a, int b);
    }
    public interface IMultiplier{
        public int multiply(int a, int b);
    }
    public interface IDivider{
        public int divide(int a, int b) throws ArithmeticException;
    }
}