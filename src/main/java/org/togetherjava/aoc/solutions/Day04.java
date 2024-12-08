package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

// https://adventofcode.com/2024/day/4
@AdventDay(day = 4)
public class Day04 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {

        int result = 0;

        Character[][] charInput = input.toGrid();
        for (int y = 0; y < charInput.length; y++) {
            for (int x = 0; x < charInput[y].length; x++) result += checkSurroundings(charInput, x, y);
        }

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        int result = 0;

        Character[][] charInput = input.toGrid();
        for (int y = 0; y < charInput.length; y++) {
            for (int x = 0; x < charInput[y].length; x++) result += checkForXShape(charInput, x, y) ? 1 : 0;
        }

        return result;
    }

    private boolean checkForXShape(Character[][] input, int x, int y) {
        try {
            if (input[y][x] == 'M' && input[y + 1][x + 1] == 'A' && input[y + 2][x + 2] == 'S') {
                if (input[y + 2][x] == 'M' && input[y][x + 2] == 'S') return true;
                else if (input[y + 2][x] == 'S' && input[y][x + 2] == 'M') return true;
            } else if (input[y][x] == 'S' && input[y + 1][x + 1] == 'A' && input[y + 2][x + 2] == 'M') {
                if (input[y + 2][x] == 'M' && input[y][x + 2] == 'S') return true;
                else if (input[y + 2][x] == 'S' && input[y][x + 2] == 'M') return true;
            }
        } catch (ArrayIndexOutOfBoundsException _) {}
        return false;
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
        UP(0, -1),
        UP_RIGHT(1, -1),
        RIGHT(1, 0),
        DOWN_RIGHT(1, 1),
        DOWN(0, 1),
        DOWN_LEFT(-1, 1),
        LEFT(-1, 0),
        UP_LEFT(-1, -1);

        public final int xIncrease, yIncrease;

        Direction(int xIncrease, int yIncrease) {
            this.xIncrease = xIncrease;
            this.yIncrease = yIncrease;
        }
    }
}
