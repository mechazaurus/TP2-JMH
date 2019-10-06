package fr.polytechtours.javaperformance.jmh.sort;

import fr.polytechtours.javaperformance.jmh.sort.Sort;

import java.util.Collections;
import java.util.List;

public final class JavaSort implements Sort {

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {
        Collections.sort(list);
        return list;
    }

}
