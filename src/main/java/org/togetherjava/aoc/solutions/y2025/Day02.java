package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.core.utils.StringUtils;
import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AdventDay(day=2)
public class Day02 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        BigInteger solution = BigInteger.ZERO;
        long[] numberList = Arrays.stream(input.rawInput().split("[,-]"))
                .mapToLong(num -> Long.parseLong(num.replaceAll("\\D", ""))).toArray();

        for (int i = 0; i < numberList.length; i+=2) {
            long endRange = numberList[i+1];

            for (long j = numberList[i]; j < endRange; j++) {
                String jString = String.valueOf(j);
                if (jString.length() % 2 != 0) continue;

                if (jString.substring(0, jString.length() / 2).equals(jString.substring(jString.length() / 2)))
                    solution = solution.add(BigInteger.valueOf(j));
            }
        }

        return solution;
    }

    @Override
    public Object part2(PuzzleInput input) {
        BigInteger solution = BigInteger.ZERO;
        long[] numberList = Arrays.stream(input.rawInput().split("[,-]"))
                .mapToLong(num -> Long.parseLong(num.replaceAll("\\D", ""))).toArray();

        for (int i = 0; i < numberList.length; i+=2) {
            long endRange = numberList[i+1];

            for (long j = numberList[i]; j <= endRange; j++) {
                String jString = String.valueOf(j);

                outerLoop: for (int intervalCount = jString.length() + 1; intervalCount--> 2;) {
                    if (jString.length() % intervalCount != 0) continue;

                    int intervalLength = jString.length() / intervalCount;
                    String firstInterval = jString.substring(0, intervalLength);

                    for (int substrings = 1; substrings < intervalCount; substrings++) {

                        if (!jString.substring(intervalLength * substrings, intervalLength * (substrings + 1)).equals(firstInterval)) continue outerLoop;
                    }

                    solution = solution.add(BigInteger.valueOf(j));
                    break;
                }
            }
        }

        return solution;
    }
}
