package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.api.AbstractDay;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

// https://adventofcode.com/2024/day/3
public class Day03 extends AbstractDay {
    public Day03() {
        super(2024, 3);
    }

    @Override
    public Object part1Solution() {
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");

        AtomicInteger result = new AtomicInteger(0);

        getInput().asStream().forEach(line -> {
            pattern.matcher(line).results().forEach(matchResultRaw -> {
                String matchResult = matchResultRaw.group();
                matchResult = matchResult.replaceAll("(mul\\(|\\))", "");
                result.addAndGet(Integer.parseInt(matchResult.split(",")[0]) * Integer.parseInt(matchResult.split(",")[1]));
            });
        });

        return result;
    }

    @Override
    public Object part2Solution() {
        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))");

        AtomicInteger result = new AtomicInteger(0);
        AtomicBoolean compute = new AtomicBoolean(true);

        getInput().asStream().forEach(line -> {
            pattern.matcher(line).results().forEach(matchResultRaw -> {
                String matchResult = matchResultRaw.group();
                if (matchResult.equals("do()")) {
                    compute.set(true);
                } else if (matchResult.equals("don't()")) {
                    compute.set(false);
                } else if (compute.get()) {
                    matchResult = matchResult.replaceAll("(mul\\(|\\))", "");
                    result.addAndGet(Integer.parseInt(matchResult.split(",")[0]) * Integer.parseInt(matchResult.split(",")[1]));
                }
            });
        });

        return result;
    }
}
