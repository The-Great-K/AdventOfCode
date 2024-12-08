package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.*;

// https://adventofcode.com/2024/day/5
@AdventDay(day = 5)
public class Day05 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        int result = 0;

        List<String> lines = input.getLines();
        Map<Integer, Set<Integer>> order = new HashMap<>();

        boolean readingOrder = false;

        for (String line : lines) {
            if (!readingOrder) {
                if (!line.matches("\\d+\\|\\d+")) {
                    readingOrder = true;
                    continue;
                }

                int[] parsedLine = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();

                if (!order.containsKey(parsedLine[0])) order.put(parsedLine[0], new HashSet<>());
                order.get(parsedLine[0]).add(parsedLine[1]);
            } else {
                boolean passed = true;
                int[] parsedLine = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                for (int i = 0; i < parsedLine.length - 1; i++) {
                    if (!order.get(parsedLine[i]).contains(parsedLine[i + 1])) {
                        passed = false;
                        break;
                    }
                }
                if (passed) result += parsedLine[parsedLine.length / 2];
            }
        }

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        int result = 0;

        List<String> lines = input.getLines();
        Map<Integer, Set<Integer>> order = new HashMap<>();

        List<String> faultyLines = new ArrayList<>();

        boolean readingOrder = true;

        for (String line : lines) {
            if (readingOrder) {
                if (!line.matches("\\d+\\|\\d+")) {
                    readingOrder = false;
                    continue;
                }

                int[] parsedLine = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();

                if (!order.containsKey(parsedLine[0])) order.put(parsedLine[0], new HashSet<>());
                order.get(parsedLine[0]).add(parsedLine[1]);
            } else {
                int[] parsedLine = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                for (int i = 0; i < parsedLine.length - 1; i++) {
                    if (!order.get(parsedLine[i]).contains(parsedLine[i + 1])) {
                        faultyLines.add(line);
                        break;
                    }
                }
            }
        }
        for (String line : faultyLines) {
            int[] parsedLine = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            List<Integer> orderedLine = new LinkedList<>();

            for (int i : parsedLine) {
                if (orderedLine.isEmpty()) {
                    orderedLine.add(i);
                    continue;
                }
                int selectedIndex = orderedLine.size();
                for (int j = orderedLine.size() - 1; j >= 0; j--) {
                    if (!order.get(orderedLine.get(j)).contains(i)) selectedIndex--;
                }
                orderedLine.add(selectedIndex, i);
            }
            result += orderedLine.get(orderedLine.size() / 2);
        }
        return result;
    }
}
