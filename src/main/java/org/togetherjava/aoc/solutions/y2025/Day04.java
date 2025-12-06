package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.core.math.Direction;
import org.togetherjava.aoc.core.math.matrix.Matrix;
import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

@AdventDay(day=4)
public class Day04 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        int accessiblePaper = 0;
        int adjacentPapers = 0;
        Matrix<Character> wall = input.toCharMatrix();

        for (int y = 0; y < wall.getRows(); y++) {
            for (int x = 0; x < wall.getCols(); x++) {
                adjacentPapers = numOfAdjacentPapers(wall, x, y);

                if (adjacentPapers < 4 && adjacentPapers != -1) accessiblePaper++;
            }
        }

        return accessiblePaper;
    }

    @Override
    public Object part2(PuzzleInput input) {
        int removedPaper = 0;
        int removedPaperOnLastInstance = 0;
        int adjacentPapers = 0;
        Matrix<Character> wall = input.toCharMatrix();

        do {
            removedPaperOnLastInstance = 0;
            for (int y = 0; y < wall.getRows(); y++) {
                for (int x = 0; x < wall.getCols(); x++) {
                    adjacentPapers = numOfAdjacentPapers(wall, x, y);

                    if (adjacentPapers < 4 && adjacentPapers != -1) {
                        wall.set(x, y, '.');
                        removedPaper++;
                        removedPaperOnLastInstance++;
                    }
                }
            }
        } while (removedPaperOnLastInstance != 0);

        return removedPaper;
    }

    private int numOfAdjacentPapers(Matrix<Character> wall, int x, int y) {
        int adjacentPapers = 0;

        if (wall.get(x, y) == '.') return -1;

        for (Direction direction : Direction.values()) {
            if (x + direction.getX() < 0 || x + direction.getX() >= wall.getRows()
                    || y + direction.getY() < 0 || y + direction.getY() >= wall.getCols()) continue;
            if (wall.get(x + direction.getX(), y + direction.getY()) == '@') adjacentPapers++;
        }

        return adjacentPapers;
    }
}
