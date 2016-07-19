package lab4;

import interfaces.task4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtilsImpl implements CollectionUtils{

    @Override
    public List<Integer> union(Collection<Integer> collection1, Collection<Integer> collection2) {

        if (collection1 == null || collection2 == null) throw new NullPointerException();

        List<Integer> list = new ArrayList<>(collection1);
        list.addAll(collection2);

        return list;
    }

    @Override
    public List<Integer> intersection(Collection<Integer> collection1, Collection<Integer> collection2) {

        if (collection1 == null || collection2 == null) throw new NullPointerException();

        List<Integer> list = new ArrayList<>();

        Set<Integer> set = new HashSet<>(collection1);

        for (Integer outerInt: set){
            list.addAll(collection2.stream()
                    .filter(outerInt::equals)
                    .collect(Collectors.toList()));
        }

        set = new HashSet<>(collection2);

        for (Integer outerInt: set){
            list.addAll(collection1.stream()
                    .filter(outerInt::equals)
                    .collect(Collectors.toList()));
        }

        return list;
    }

    @Override
    public Set<Integer> intersectionWithoutDuplicate(Collection<Integer> collection1, Collection<Integer> collection2) {

        if (collection1 == null || collection2 == null) throw new NullPointerException();

        Set<Integer> set = new HashSet<>();

        for (Integer outerInt: collection1){
            set.addAll(collection2.stream()
                    .filter(outerInt::equals)
                    .collect(Collectors.toSet()));
        }

        return set;
    }

    @Override
    public Collection<Integer> difference(Collection<Integer> collection1, Collection<Integer> collection2) {

        if (collection1 == null || collection2 == null) throw new NullPointerException();

        Collection<Integer> subCollection = new ArrayList<>(collection1);
        subCollection.removeAll(collection2);

        return subCollection;

    }
}
