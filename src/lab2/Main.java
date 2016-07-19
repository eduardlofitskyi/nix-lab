package lab2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

public class Main {

    public static final FractionNumberOperation operation = new FractionNumberOperationImpl();

    public static void main(String[] args) {
        FractionNumber number1 = new FractionNumberImpl(1, 2);
        FractionNumber number2 = new FractionNumberImpl(2, 3);
        FractionNumber number3 = new FractionNumberImpl(2, 3);

        FractionNumber mulResult = operation.mul(number1, number2);

        System.out.printf("(%s) * (%s) = %s\n",
                number1.toStringValue(), number2.toStringValue(), mulResult.toStringValue());

        FractionNumber divResult = operation.div(number1, number2);

        System.out.printf("(%s) / (%s) = %s\n",
                number1.toStringValue(), number2.toStringValue(), divResult.toStringValue());

        FractionNumber addResult = operation.add(number1, number3);

        System.out.printf("(%s) + (%s) = %s\n",
                number1.toStringValue(), number3.toStringValue(), addResult.toStringValue());

        FractionNumber subResult = operation.sub(number1, number3);

        System.out.printf("(%s) - (%s) = %s\n",
                number1.toStringValue(), number3.toStringValue(), subResult.toStringValue());
    }
}
