package lab3;

import interfaces.task3.StringDiv;
import interfaces.task3.StringUtils;

public class Main {
    public static void main(String[] args) {
        StringUtils util = new StringUtilImpl();
        StringDiv strDivider = new StringDivImpl();

        try {
            System.out.println(util.invert("123abc_"));
            System.out.println(util.compareWords("123aabc_", "13bb_"));
            System.out.println(util.parseDouble("123.4a 3.432abc_"));

            System.out.println(strDivider.div("353.8_43ad", "15.25 77.94"));

            Thread.sleep(500);

            System.out.println(strDivider.div("353.8_43ad", "aasd 17.84"));
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            System.err.println("Cause: " + e.getMessage());
            System.err.println("Class: " + e.getStackTrace()[0].getClassName());
            System.err.println("Method: "+ e.getStackTrace()[0].getMethodName());
            System.err.println("Line of code: "+ e.getStackTrace()[0].getLineNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
