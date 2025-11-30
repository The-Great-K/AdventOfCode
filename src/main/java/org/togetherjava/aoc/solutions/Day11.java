package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;
import org.togetherjava.aoc.core.utils.Tuple;

import java.util.*;
import java.util.stream.Stream;

// https://adventofcode.com/2024/day/11
@AdventDay(day = 11)
public class Day11 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        List<Long> numbers = Stream.of(input.getLines().getFirst().split(" ")).map(Long::parseLong).toList();
        List<Long> list = new ArrayList<>(numbers);

        for (int i = 0; i < 25; ++i) {
            blink(list);
        }

        return list.size();
    }

    public void blink(List<Long> list) {
        for (int i = list.size(); i-- > 0;) {
            long stone = list.get(i);
            if (stone == 0) {
                list.set(i, 1L);
            } else {
                String sStone = String.valueOf(stone);
                int length = sStone.length();
                if ((length & 1) == 0) {
                    // replaced by two stones
                    int midpoint = length / 2;
                    list.set(i, Long.parseLong(sStone.substring(0, midpoint)));
                    list.add(i + 1, Long.parseLong(sStone.substring(midpoint)));
                } else {
                    list.set(i, stone * 2024);
                }
            }
        }
    }

    // credit to @madimadica on discord, I couldn't get part 2 for the life of me
    @Override
    public Object part2(PuzzleInput input) {
        List<Long> numbers = Stream.of(input.getLines().getFirst().split(" ")).map(Long::parseLong).toList();
        long total = 0;
        for (int i = 0; i < numbers.size(); ++i) {
            total += blinkFast(numbers.get(i), 75);
        }

        return total;
    }

    private final Map<Tuple<Long, Integer>, Long> cache = new HashMap<>();

    public long blinkFast(Long stone, int remaining) {
        if (remaining == 0) {
            return  1;
        }
        Long cachedResult = cache.get(Tuple.of(stone, remaining));
        if (cachedResult != null) {
            return cachedResult;
        }
        long result = 0;
        if (stone == 0) {
            result = blinkFast(1L, remaining - 1);
        } else {
            String sStone = String.valueOf(stone);
            int length = sStone.length();
            if ((length & 1) == 0) {
                // replaced by two stones
                int midpoint = length / 2;
                result += blinkFast(Long.parseLong(sStone.substring(0, midpoint)), remaining - 1);
                result += blinkFast(Long.parseLong(sStone.substring(midpoint)), remaining - 1);
            } else {
                result += blinkFast(stone * 2024, remaining - 1);
            }
            cache.put(Tuple.of(stone, remaining), result);
        }
        return result;
    }
}
