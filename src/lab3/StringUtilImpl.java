package lab3;

import interfaces.task3.StringUtils;

import java.util.*;

public class StringUtilImpl implements StringUtils{

    @Override
    public String invert(String s) {

        if (s == null) return "";

        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();

        for (int i = s.length() - 1; i >= 0; i--){
            builder.append(chars[i]);
        }

        return builder.toString();
    }

    @Override
    public String compareWords(String s, String s1) {

        if (s == null || s1 == null) throw new NullPointerException();

        Set<Character> s1CharSet = new HashSet<>();
        Set<Character> resultSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();

        for(char c: s1.toCharArray()){
            s1CharSet.add(c);
        }

        for (char c: s.toCharArray()){
            if (!s1CharSet.contains(c)) resultSet.add(c);
        }

        resultSet.forEach(builder::append);

        return builder.toString();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public double parseDouble(String s) {
        if (s == null) throw new NullPointerException();

        int counter = -1;
        boolean delimiterMet = false;
        boolean exponentMet = false;
        boolean signExponentMet = false;
        for (char c: s.toCharArray()){
            int ascii = (int) c;

            if (!exponentMet) {
                if (delimiterMet) {
                    if (ascii > 47 && ascii < 58) counter++;
                    else if (ascii == 101) {
                        exponentMet = true;
                        counter++;
                    }
                    else break;
                } else if (ascii == 46) {
                    delimiterMet = true;
                    counter++;
                } else if (ascii > 47 && ascii < 58) counter++;
                else if (ascii == 101) {
                    exponentMet = true;
                    counter++;
                }
                else break;
            } else {
                if (signExponentMet){
                    if (ascii > 47 && ascii < 58) counter++;
                    else break;
                }else {
                    if (ascii == 43 || ascii == 45 || (ascii > 47 && ascii < 58)){
                        signExponentMet = true;
                        counter++;
                    }else {
                        throw new IllegalArgumentException("Incorrect exponent", new NumberFormatException());
                    }
                }
            }
        }

        if (counter == -1) throw new IllegalArgumentException("String doesn't contain double", new NumberFormatException());

        return Double.parseDouble(s.substring(0, counter + 1));
    }
}
