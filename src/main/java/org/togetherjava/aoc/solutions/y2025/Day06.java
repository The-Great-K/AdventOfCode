package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.core.math.matrix.Matrix;
import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

import java.math.BigInteger;

@AdventDay(day=6)
public class Day06 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        long result = 0;

        Matrix<String> cephalopodMath = new Matrix<>(input.getLines().size(), input.getLines().getFirst().split("\\s+").length);
        for (int y = 0; y < input.getLines().size(); y++) {
            String[] line = input.getLines().get(y).strip().split("\\s+");
            for (int x = 0; x < line.length; x++) {
                cephalopodMath.set(y, x, line[x]);
            }
        }

        for (int x = 0; x < cephalopodMath.getCols(); x++) {
            long currentProblemSolution = Long.parseLong(cephalopodMath.get(0, x));

            if (cephalopodMath.get(cephalopodMath.getRows() - 1, x).equals("+")) {
                for (int y = 1; y < cephalopodMath.getRows() - 1; y++) {
                    currentProblemSolution += Long.parseLong(cephalopodMath.get(y, x));
                }
            } else if (cephalopodMath.get(cephalopodMath.getRows() - 1, x).equals("*")) {
                for (int y = 1; y < cephalopodMath.getRows() - 1; y++) {
                    currentProblemSolution *= Long.parseLong(cephalopodMath.get(y, x));
                }
            } else throw new IllegalStateException("Invalid operation!");

            result += currentProblemSolution;
        }

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        long result = 0;

        Matrix<Character> cephalopodMath = input.toCharMatrix();
        char currentOperation = ' ';

        long currentProblemSolution = 0;
        for (int x = 0; x < cephalopodMath.getCols(); x++) {
            StringBuilder currentNumberString = new StringBuilder();
            if (!cephalopodMath.get(cephalopodMath.getRows() - 1, x).equals(' ')) {
                result += currentProblemSolution;

                currentOperation = cephalopodMath.get(cephalopodMath.getRows() - 1, x);
                currentProblemSolution = 0;
            }

            for (int y = 0; y < cephalopodMath.getRows() - 1; y++) {
                if (cephalopodMath.get(y, x) != ' ') currentNumberString.append(cephalopodMath.get(y, x));
            }

            if (currentNumberString.isEmpty()) continue;

            if (currentOperation == '+') {
                currentProblemSolution += Long.parseLong(currentNumberString.toString());
            } else if (currentOperation == '*') {
                if (currentProblemSolution == 0) currentProblemSolution = 1;
                currentProblemSolution *= Long.parseLong(currentNumberString.toString());
            } else throw new IllegalStateException("Invalid operation!");
        }

        result += currentProblemSolution;

        return result;
    }
}
