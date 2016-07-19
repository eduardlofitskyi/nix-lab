package lab4;

import interfaces.task4.CollectionUtils;
import interfaces.task4.MapUtils;

import java.util.*;

public class Main {

    public static CollectionUtils utils = new CollectionUtilsImpl();
    public static MapUtils mapUtils = new MapUtilsImpl();

    public static void main(String[] args) {
        Collection<Integer> collection1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collection<Integer> collection2 = new ArrayList<>(Arrays.asList(3, 4, 5, 4, 3));

        List<Integer> union = utils.union(collection1, collection2);
        List<Integer> intersection = utils.intersection(collection1, collection2);
        Set<Integer> intersectionWithoutDuplicate = utils.intersectionWithoutDuplicate(collection1, collection2);
        Collection<Integer> difference = utils.difference(collection1, collection2);

        System.out.println("UNION:");
        union.forEach(System.out::println);

        System.out.println("INTERSECTION:");
        intersection.forEach(System.out::println);

        System.out.println("INTERSECTION WITHOUT DUPLICATE:");
        intersectionWithoutDuplicate.forEach(System.out::println);

        System.out.println("DIFFERENCE:");
        difference.forEach(System.out::println);


        System.out.println("\nDICTIONARY:");
        Map<String, Integer> map = mapUtils.findThrees("abs, 12345, 12, 12345, asddd_iii!iiii_AAAA");
        map.forEach((k,v) -> System.out.printf("[%s]: %d time(s)\n", k, v));

    }
}
