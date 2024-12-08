package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

// https://adventofcode.com/2024/day/3
@AdventDay(day = 3)
public class Day03 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");

        AtomicInteger result = new AtomicInteger(0);

        input.getLines().forEach(line -> {
            pattern.matcher(line).results().forEach(matchResultRaw -> {
                String matchResult = matchResultRaw.group();
                matchResult = matchResult.replaceAll("(mul\\(|\\))", "");
                result.addAndGet(Integer.parseInt(matchResult.split(",")[0]) * Integer.parseInt(matchResult.split(",")[1]));
            });
        });

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))");

        AtomicInteger result = new AtomicInteger(0);
        AtomicBoolean compute = new AtomicBoolean(true);

        input.getLines().forEach(line -> {
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
