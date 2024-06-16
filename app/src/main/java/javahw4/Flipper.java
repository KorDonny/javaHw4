package javahw4;

public class Flipper implements Calculator.IFlipper{
    @Override
    public int flip(int a) {
        return -a;
    }
}
