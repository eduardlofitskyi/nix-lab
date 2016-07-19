package logging;

import interfaces.task2.FractionNumber;

public class FractionNumberImpl implements FractionNumber{

    private int dividend;
    private int divisor;

    public FractionNumberImpl() {
    }

    public FractionNumberImpl(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    @Override
    public int getDividend() {
        return this.dividend;
    }

    @Override
    public void setDivisor(int divisor) {

        if (divisor == 0) throw new IllegalArgumentException("Divisor cannot be zero");

        this.divisor = divisor;
    }

    @Override
    public int getDivisor() {
        return this.divisor;
    }

    @Override
    public double value() {
        return (double) this.dividend / this.divisor;
    }

    @Override
    public String toStringValue() {
        return this.dividend + "/" + this.divisor;
    }
}
