package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

// https://adventofcode.com/2024/day/7
@AdventDay(day = 7)
public class Day07 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        AtomicLong result = new AtomicLong();

        input.getLines().forEach(line -> {
            long expected = Long.parseLong(line.split(": ")[0]);

            long[] numbers = Arrays.stream(line.split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();

            for (int i = 0; i < (int) Math.pow(2.0, numbers.length - 1); i++) {
                long actual = numbers[0];
                char[] operations = ("0".repeat(numbers.length - 1) + Integer.toBinaryString(i))
                        .substring(Integer.toBinaryString(i).length()).toCharArray();

                for (int j = 0; j < operations.length; j++) {
                    if (operations[j] == '0') actual += numbers[j + 1];
                    else if (operations[j] == '1') actual *= numbers[j + 1];
                    else throw new IllegalArgumentException("Invalid operation: " + operations[j]);
                }

                if (actual == expected) {
                    result.getAndAdd(actual);
                    break;
                }
            }
        });

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        AtomicLong result = new AtomicLong();

        input.getLines().forEach(line -> {
            long expected = Long.parseLong(line.split(": ")[0]);

            long[] numbers = Arrays.stream(line.split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();

            for (int i = 0; i < (int) Math.pow(3.0, numbers.length - 1); i++) {
                long actual = numbers[0];
                char[] operations = ("0".repeat(numbers.length - 1) + Integer.toString(i, 3))
                        .substring(Integer.toString(i, 3).length()).toCharArray();

                for (int j = 0; j < operations.length; j++) {
                    if (operations[j] == '0') actual += numbers[j + 1];
                    else if (operations[j] == '1') actual *= numbers[j + 1];
                    else if (operations[j] == '2') actual = Long.parseLong("%d%d".formatted(actual, numbers[j + 1]));
                    else throw new IllegalArgumentException("Invalid operation: " + operations[j]);
                }

                if (actual == expected) {
                    result.getAndAdd(actual);
                    break;
                }
            }
        });

        return result;
    }
}
