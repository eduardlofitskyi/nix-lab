package lab5;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;
import interfaces.task5.ArrayCollection;


public class Main {

    public static final FractionNumberOperation util = new FractionNumberOperationImpl();

    public static void main(String[] args) {
        ArrayCollection<FractionNumber> fractionNumbers = new ArrayCollectionImpl<>();

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
    }
}
