package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

// https://adventofcode.com/2024/day/7
@AdventDay(day = 7)
public class Day07 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        input.getLines().forEach(line -> {
            int expected = Integer.parseInt(line.split(": ")[0]);
        });

        return null;
    }

    @Override
    public Object part2(PuzzleInput input) {
        return null;
    }
}
