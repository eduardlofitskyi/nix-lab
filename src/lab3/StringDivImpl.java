package lab3;

import interfaces.task3.StringDiv;

public class StringDivImpl implements StringDiv{

    //private StringUtilImpl util = new StringUtilImpl();

    @Override
    public double div(String dividendStr, String divisorStr) {
        double dividend;
        double divisor;
        try {
            dividend = Double.parseDouble(dividendStr);
            divisor = Double.parseDouble(divisorStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        if (divisor == 0) throw new ArithmeticException("Cannot be divided by zero");

        return dividend / divisor;
    }
}
