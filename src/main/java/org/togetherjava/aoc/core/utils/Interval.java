package org.togetherjava.aoc.core.utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Interval<A extends Number, B extends Number> extends Tuple<A, B> {
    private final boolean lowerIncluded;
    private final boolean upperIncluded;

    public Interval(A a, B b, boolean lowerIncluded, boolean upperIncluded) {
        super(a, b);

        if (!(a.doubleValue() <= b.doubleValue())) throw new IllegalArgumentException("Lower bound must be less than upper bound");

        this.lowerIncluded = lowerIncluded;
        this.upperIncluded = upperIncluded;
    }

    public Interval(A a, B b) {
        this(a, b, true, true);
    }

    public static <A extends Number, B extends Number> Interval<A, B> of(A a, B b, boolean lowerIncluded, boolean upperIncluded) {
        return new Interval<>(a, b, lowerIncluded, upperIncluded);
    }

    public static <A extends Number, B extends Number> Interval<A, B> of(A a, B b) {
        return new Interval<>(a, b);
    }

    public boolean contains(Number num) {
        boolean lowerBoundCheck = lowerIncluded ? a.doubleValue() <= num.doubleValue() : a.doubleValue() < num.doubleValue();
        boolean upperBoundCheck = upperIncluded ? num.doubleValue() <= b.doubleValue() : num.doubleValue() < b.doubleValue();

        return lowerBoundCheck && upperBoundCheck;
    }

    public Number getLowerBound() {
        return getA();
    }

    public Number getUpperBound() {
        return getB();
    }

    public List<Long> getDomain() {
        List<Long> domain = new ArrayList<>();
        long currentNum = lowerIncluded ? a.longValue() : a.longValue() + 1;

        while (upperIncluded ? b.longValue() >= currentNum : b.longValue() > currentNum) {
            domain.add(currentNum);
            currentNum++;
        }

        return domain;
    }
}
