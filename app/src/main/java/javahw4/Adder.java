package javahw4;

public class Adder implements Calculator.IAdder{
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
