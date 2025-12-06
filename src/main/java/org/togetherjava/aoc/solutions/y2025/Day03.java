package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

@AdventDay(day=3)
public class Day03 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        int solution = 0;

        int firstDigit, secondDigit;

        for (String line : input.getLines()) {
            firstDigit = 0;
            secondDigit = 0;

            for (int i = 0; i < line.length() - 1; i++) {
                char c = line.charAt(i);
                int indexValue = Integer.parseInt(String.valueOf(c));

                if (indexValue > firstDigit) {
                    firstDigit = indexValue;
                    secondDigit = 0;
                } else if (indexValue > secondDigit) secondDigit = indexValue;
            }
            secondDigit = Math.max(secondDigit, Integer.parseInt(String.valueOf(line.charAt(line.length()-1))));

            System.out.println(firstDigit * 10 + secondDigit);

            solution += firstDigit * 10 + secondDigit;
        }

        return solution;
    }

    @Override
    public Object part2(PuzzleInput input) {
        long solution = 0;

        Deque<Integer> digits = new ArrayDeque<>();

        for (String line : input.getLines()) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                int indexValue = Integer.parseInt(String.valueOf(c));

                if (digits.isEmpty()) {
                    digits.push(indexValue);
                    continue;
                }

                while (!digits.isEmpty() && digits.peek() < indexValue && digits.size() > 12 - (line.length() - i)) digits.pop();

                if (digits.size() < 12) digits.push(indexValue);
            }
            int place = 0;
            while (!digits.isEmpty()) solution += (long) (digits.pop() * Math.pow(10, place++));
        }

        return solution;
    }
}
