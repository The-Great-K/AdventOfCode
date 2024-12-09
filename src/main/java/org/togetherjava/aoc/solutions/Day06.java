package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;
import org.togetherjava.aoc.core.utils.Coordinate;
import org.togetherjava.aoc.core.utils.Direction;
import org.togetherjava.aoc.core.utils.Matrix2D;

import java.util.*;

// https://adventofcode.com/2024/day/6
@AdventDay(day = 6)
public class Day06 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        Matrix2D<Character> map = input.toMatrix();

        return getVisitedTiles(findStart(input), map).size();
    }

    @Override
    public Object part2(PuzzleInput input) {
        Coordinate start = findStart(input);
        Matrix2D<Character> map = input.toMatrix();

        return getVisitedTiles(start, map)
                .stream()
                .filter(coordinate -> !start.equals(coordinate))
                .filter(block -> {
                    Coordinate currentPos = start;
                    Direction direction = Direction.NORTH;

                    Set<Coordinate> corners = new HashSet<>();
                    Coordinate lastCorner = null;

                    for (;;) {
                        currentPos = currentPos.move(direction);

                        if (!map.isInBounds(currentPos)) return false;

                        if (map.get(currentPos).equals('#') || currentPos.equals(block)) {
                            currentPos = currentPos.move(direction, -1);
                            if (corners.contains(currentPos)) return true;

                            direction = direction.right();

                            if (lastCorner != null) corners.add(lastCorner);
                            lastCorner = currentPos;
                        }
                    }
                }).count();
    }

    private Set<Coordinate> getVisitedTiles(Coordinate start, Matrix2D<Character> map) {
        Coordinate currentPos = start;

        Set<Coordinate> visitedTiles = new HashSet<>();
        Direction direction = Direction.NORTH;

        visitedTiles.add(currentPos);
        while (true) {
            currentPos = currentPos.move(direction);

            if (!map.isInBounds(currentPos)) break;

            if (map.get(currentPos).equals('#')) {
                currentPos = currentPos.move(direction, -1);
                direction = direction.right();
            }
            visitedTiles.add(currentPos);
        }

        return visitedTiles;
    }

    private Coordinate findStart(PuzzleInput input) {
        for (int i = 0; i < input.getLines().size(); i++) {
            for (int j = 0; j < input.getLines().get(i).length(); j++) {
                if (input.getLines().get(i).charAt(j) == '^') return new Coordinate(j, i);
            }
        }
        throw new IllegalCallerException("Could not find a starting point");
    }
}
