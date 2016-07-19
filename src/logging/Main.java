package logging;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

public class Main {

    public static final FractionNumberOperation util = new FractionNumberOperationImpl();

    public static void main(String[] args) {
        LoggedArrayCollection<FractionNumber> fractionNumbers = new LoggedArrayCollection<>();

        fractionNumbers.setArray(new FractionNumber[]{new FractionNumberImpl(2,5), new FractionNumberImpl(1,5)});

        fractionNumbers.add(new FractionNumberImpl(1, 10));
        fractionNumbers.add(new FractionNumberImpl(2, 5));
        fractionNumbers.add(new FractionNumberImpl(3, 6));
        fractionNumbers.add(new FractionNumberImpl(10, 10));


        FractionNumber sumResult = new FractionNumberImpl(0, 1);

        for (FractionNumber number: fractionNumbers){
            sumResult = util.add(sumResult, number);
        }

        System.out.println(sumResult.toStringValue());

        fractionNumbers.setArray(null);
    }
}
