package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.api.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://adventofcode.com/2024/day/2
public class Day02 extends AbstractDay {
    public Day02() {
        super(2024, 2);
    }

    @Override
    public Object part1Solution() {
        return getInput().asStream().filter(line -> {
            List<String> levelStrings = Arrays.asList(line.split(" "));
            List<Integer> levels = levelStrings.stream().mapToInt(Integer::parseInt).boxed().toList();

            boolean increasing = levels.get(0) < levels.get(1);

            for (int i = 1; i < levels.size(); i++) {
                int difference = levels.get(i) - levels.get(i - 1);

                if (increasing && !(difference <= 3 && difference > 0)) return false;
                else if (!increasing && !(difference >= -3 && difference < 0)) return false;
            }
            return true;
        }).count();
    }

    @Override
    public Object part2Solution() {
        return getInput().asStream().filter(line -> {
            List<String> levelStrings = Arrays.asList(line.split(" "));
            List<Integer> levels = levelStrings.stream().mapToInt(Integer::parseInt).boxed().toList();

            boolean increasing = levels.get(0) < levels.get(1);

            for (int i = 1; i < levels.size(); i++) {
                int difference = levels.get(i) - levels.get(i - 1);

                if (increasing && !(difference <= 3 && difference > 0)) return dampen(levels);
                else if (!increasing && !(difference >= -3 && difference < 0)) return dampen(levels);
            }
            return true;
        }).count();
    }

    private boolean dampen(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            boolean passed = true;

            List<Integer> dampenedLevels = new ArrayList<>(levels);
            dampenedLevels.remove(i);

            boolean increasing = dampenedLevels.get(0) < dampenedLevels.get(1);

            for (int j = 1; j < dampenedLevels.size(); j++) {
                int difference = dampenedLevels.get(j) - dampenedLevels.get(j - 1);

                if (increasing && !(difference <= 3 && difference > 0)) passed = false;
                else if (!increasing && !(difference >= -3 && difference < 0)) passed = false;
            }
            if (passed) return true;
        }
        return false;
    }
}
