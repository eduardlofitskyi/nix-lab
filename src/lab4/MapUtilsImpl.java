package lab4;

import interfaces.task4.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class MapUtilsImpl implements MapUtils{
    @Override
    public Map<String, Integer> findThrees(String s) {

        if (s == null) throw new NullPointerException();

        Map<String, Integer> dictionary = new HashMap<>();

        String str = null;

        for (int i = 0; i < s.length() - 2; i++){
            try {
                str = findWord(s.substring(i));
                if (dictionary.containsKey(str)){
                    int amount = dictionary.get(str) + 1;
                    dictionary.put(str,amount);
                } else {
                    dictionary.put(str, 1);
                }
            } catch (TooShortWordException e) {
                //NOP
            }
        }

        return dictionary;
    }

    private String findWord(String substring) throws TooShortWordException {
        char[] chars = substring.toCharArray();
        int counter = 0;
        for (int i = 0; i < 3; i++){
            int ascii = (int) chars[i];
            if ((ascii > 47 && ascii < 58) || (ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)){
                counter++;
            }
        }
        if (counter == 3) return substring.substring(0, 3);
        else throw new TooShortWordException();
    }



    private class TooShortWordException extends Exception{
        public TooShortWordException() {
            super();
        }
    }
}
