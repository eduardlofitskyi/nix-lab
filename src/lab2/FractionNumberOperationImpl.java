package lab2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

public class FractionNumberOperationImpl implements FractionNumberOperation{

    @Override
    public FractionNumber add(FractionNumber fractionNumber1, FractionNumber fractionNumber2) {

        FractionNumber newFractionNumber = new FractionNumberImpl();
        int lcm = lcm(fractionNumber1.getDivisor(), fractionNumber2.getDivisor());

        newFractionNumber.setDividend((fractionNumber1.getDividend() * (lcm / fractionNumber1.getDivisor()))
                + (fractionNumber2.getDividend() * (lcm / fractionNumber2.getDivisor())));
        newFractionNumber.setDivisor(lcm);

        return newFractionNumber;
    }

    @Override
    public FractionNumber sub(FractionNumber fractionNumber1, FractionNumber fractionNumber2) {

        FractionNumber newFractionNumber = new FractionNumberImpl();
        int lcm = lcm(fractionNumber1.getDivisor(), fractionNumber2.getDivisor());

        newFractionNumber.setDividend((fractionNumber1.getDividend() * (lcm / fractionNumber1.getDivisor()))
                - (fractionNumber2.getDividend() * (lcm / fractionNumber2.getDivisor())));
        newFractionNumber.setDivisor(lcm);

        return newFractionNumber;
    }

    @Override
    public FractionNumber mul(FractionNumber fractionNumber1, FractionNumber fractionNumber2) {

        FractionNumber newFractionNumber = new FractionNumberImpl();

        newFractionNumber.setDividend(fractionNumber1.getDividend() * fractionNumber2.getDividend());
        newFractionNumber.setDivisor(fractionNumber1.getDivisor() * fractionNumber2.getDivisor());

        return newFractionNumber;
    }

    @Override
    public FractionNumber div(FractionNumber fractionNumber1, FractionNumber fractionNumber2) {

        if (fractionNumber2.getDividend() == 0) throw new ArithmeticException("Cannot divide zero");

        FractionNumber newFractionNumber = new FractionNumberImpl();

        newFractionNumber.setDividend(fractionNumber1.getDividend() * fractionNumber2.getDivisor());
        newFractionNumber.setDivisor(fractionNumber1.getDivisor() * fractionNumber2.getDividend());

        return newFractionNumber;
    }

    private int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b){
        return  (a * b) / gcd(a, b);
    }
}
