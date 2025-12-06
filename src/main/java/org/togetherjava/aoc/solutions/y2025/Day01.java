package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

@AdventDay(day=1)
public class Day01 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        int currentDialLocation = 50;
        int timesHitZero = 0;

        for (String instruction : input.getLines()) {
            if (instruction.startsWith("L")) {
                currentDialLocation -= Integer.parseInt(instruction.replaceAll("L", ""));
            } else if (instruction.startsWith("R")) {
                currentDialLocation += Integer.parseInt(instruction.replaceAll("R", ""));
            }
            currentDialLocation = ((currentDialLocation % 100) + 100) % 100;
            if (currentDialLocation == 0) timesHitZero++;
        }

        return timesHitZero;
    }

    @Override
    public Object part2(PuzzleInput input) {
        int originalDialLocation;
        int currentDialLocation = 50;
        int ticksToMove = 0;
        int timesHitZero = 0;

        for (String instruction : input.getLines()) {
            if (instruction.startsWith("L")) {
                ticksToMove = -Integer.parseInt(instruction.replaceAll("L", ""));
                timesHitZero += ticksToMove / -100;
                ticksToMove %= -100;
            } else if (instruction.startsWith("R")) {
                ticksToMove = Integer.parseInt(instruction.replaceAll("R", ""));
                timesHitZero += ticksToMove / 100;
                ticksToMove %= 100;
            }
            originalDialLocation = currentDialLocation;
            currentDialLocation += ticksToMove;
            currentDialLocation = ((currentDialLocation % 100) + 100) % 100;
            if (ticksToMove == 0 || originalDialLocation + ticksToMove != currentDialLocation) timesHitZero++;
        }

        return timesHitZero;
    }
}
