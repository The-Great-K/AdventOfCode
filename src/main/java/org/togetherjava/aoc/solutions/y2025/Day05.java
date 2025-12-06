package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.core.utils.Interval;
import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

import java.util.*;

@AdventDay(day=5)
public class Day05 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        int freshIngredientIds = 0;

        boolean readingRanges = true;
        List<Interval<Long, Long>> ranges = new ArrayList<>();

        for (String line : input.getLines()) {
            if (line.isEmpty()) {
                readingRanges = false;
                continue;
            }

            if (readingRanges) {
                ranges.add(Interval.of(Long.parseLong(line.split("-")[0]), Long.parseLong(line.split("-")[1])));
                continue;
            }

            for (Interval<Long, Long> range : ranges) {
                if (range.contains(Long.parseLong(line))) {
                    freshIngredientIds++;
                    break;
                }
            }
        }

        return freshIngredientIds;
    }

    // solution "borrowed" from https://leetcode.com/problems/merge-intervals/
    @Override
    public Object part2(PuzzleInput input) {
        long possibleFreshIds = 0;

        List<Interval<Long, Long>> ranges = new ArrayList<>();
        List<long[]> arrayRanges = new ArrayList<>();

        for (String line : input.getLines()) {
            if (line.isEmpty()) break;

            ranges.add(Interval.of(Long.parseLong(line.split("-")[0]), Long.parseLong(line.split("-")[1])));
        }

        ranges.sort(Comparator.comparingLong(Interval::getA));

        ranges.forEach(interval -> arrayRanges.add(new long[] { interval.getA(), interval.getB() }));

        List<long[]> mergedRanges = new ArrayList<>();
        long[] previous = arrayRanges.getFirst();

        for (int i = 1; i < arrayRanges.size(); i++) {
            long[] current = arrayRanges.get(i);

            if (current[0] <= previous[1]) {
                previous[1] = Math.max(previous[1], current[1]);
            } else {
                mergedRanges.add(previous);
                previous = current;
            }
        }
        mergedRanges.add(previous);

        for (long[] range : mergedRanges) {
            possibleFreshIds += (range[1] + 1 - range[0]);
        }

        return possibleFreshIds;
    }
}
