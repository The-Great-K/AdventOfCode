package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.api.AbstractDay;

import java.util.HashSet;
import java.util.Set;

// https://adventofcode.com/2024/day/4
public class Day04 extends AbstractDay {
    public Day04() {
        super(2024, 4);
    }

    @Override
    public Object part1Solution() {
        int result = 0;

        Character[][] input = getInput().as2DArray();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) result += checkSurroundings(input, x, y);
        }

        return result;
    }

    @Override
    public Object part2Solution() {
        return null;
    }

    private int checkSurroundings(Character[][] input, int x, int y) {
        int wordCounter = 0;
        for (Direction direction : Direction.values()) {
            try {
                for (int i = 0; i < "XMAS".length(); i++) {
                    if (input[y + direction.yIncrease * i][x + direction.xIncrease * i] == "XMAS".toCharArray()[i]) {
                        if (input[y + direction.yIncrease * i][x + direction.xIncrease * i] == 'S') wordCounter++;
                    } else break;
                }
            } catch (ArrayIndexOutOfBoundsException _) {}
        }
        return wordCounter;
    }

    private enum Direction {
        UP(0, 1),
        UP_RIGHT(1, 1),
        RIGHT(1, 0),
        DOWN_RIGHT(1, -1),
        DOWN(0, -1),
        DOWN_LEFT(-1, -1),
        LEFT(-1, 0),
        UP_LEFT(-1, 1);

        private final int xIncrease, yIncrease;

        Direction(int xIncrease, int yIncrease) {
            this.xIncrease = xIncrease;
            this.yIncrease = yIncrease;
        }
    }
}
