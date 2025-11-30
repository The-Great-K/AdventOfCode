package org.togetherjava.aoc.solutions;


import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;
import org.togetherjava.aoc.core.utils.Coordinate;
import org.togetherjava.aoc.core.utils.Direction;
import org.togetherjava.aoc.core.utils.Matrix2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// https://adventofcode.com/2024/day/12
@AdventDay(day = 12)
public class Day12 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        AtomicInteger result = new AtomicInteger(0);

        Set<Coordinate> occupiedSpace = new HashSet<>();

        Matrix2D<Character> map = input.toMatrix();

        map.forEach((x, y, c) -> {
            if (!occupiedSpace.contains(new Coordinate(x, y))) {
                Set<Coordinate> plot = getPlot(map, x, y, c);
                result.addAndGet(getArea(plot) * getPerimeter(plot));
                occupiedSpace.addAll(plot);
            }
        });

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        input = PuzzleInput.of("""
                EEEEE
                EXXXX
                EEEEE
                EXXXX
                EEEEE""");

        AtomicInteger result = new AtomicInteger(0);

        Set<Coordinate> occupiedSpace = new HashSet<>();

        Matrix2D<Character> map = input.toMatrix();

//        map.forEach((x, y, c) -> {
//            if (!occupiedSpace.contains(new Coordinate(x, y))) {
//                Set<Coordinate> plot = getPlot(map, x, y, c);
//                result.addAndGet(getArea(plot) * getSides(plot, getCorners(plot)));
//                occupiedSpace.addAll(plot);
//            }
//        });

        return getCorners(getPlot(map, 0, 0, 'E'));
    }

    private Set<Coordinate> getPlot(Matrix2D<Character> map, int x, int y, char type) {
        Set<Coordinate> plot = new HashSet<>();
        Set<Coordinate> uncheckedTiles = new HashSet<>();
        uncheckedTiles.add(new Coordinate(x, y));
        plot.add(new Coordinate(x, y));

        boolean morePlotRemaining = true;
        while (morePlotRemaining) {
            morePlotRemaining = false;
            Set<Coordinate> addQueue = new HashSet<>();
            for (Coordinate tile : uncheckedTiles) {
                for (Direction direction : Direction.CARDINAL) {
                    if (plot.contains(tile.move(direction))) continue;
                    try {
                        if (map.get(tile.move(direction)).equals(type)) {
                            addQueue.add(tile.move(direction));
                            morePlotRemaining = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException _) {}
                }
            }
            plot.addAll(addQueue);
            uncheckedTiles.clear();
            uncheckedTiles.addAll(addQueue);
        }

        return plot;
    }

    private int getArea(Set<Coordinate> plot) {
        return plot.size();
    }

    private int getPerimeter(Set<Coordinate> plot) {
        int perimeter = 0;

        for (Coordinate tile : plot) {
            for (Direction direction : Direction.CARDINAL) {
                if (!plot.contains(tile.move(direction))) perimeter++;
            }
        }

        return perimeter;
    }

    private Set<Coordinate> getCorners(Set<Coordinate> plot) {
        Set<Coordinate> corners = new HashSet<>();
        for (Coordinate tile : plot) {
            Direction direction = Direction.NORTH;
            boolean turnRight = true;
            List<Direction> exposedSides = new ArrayList<>();
            for (int i = 0; i < Direction.CARDINAL.length; i++) {
                if (!plot.contains(tile.move(direction))) {
                    exposedSides.add(direction);
                } else if (!exposedSides.isEmpty()) {
                    if (!plot.contains(tile.move(direction.opposite()))) {
                        direction = direction.opposite();
                        exposedSides.add(direction);
                        turnRight = false;
                    } else break;
                }
                direction = turnRight ? direction.right() : direction.left();
            }
            if (exposedSides.size() >= 2) corners.add(tile);
        }
        return corners;
    }

    private int getSides(Set<Coordinate> plot, Set<Coordinate> corners) {
        if (plot.size() == 1) return 4;

        int sides = 0;

        Set<Coordinate> visitedCorners = new HashSet<>();

        for (Coordinate corner : corners) {
            if (visitedCorners.contains(corner)) continue;
            Coordinate currentPos = new Coordinate(corner);
            Direction direction = null;

            for (Direction potentialDirection : Direction.CARDINAL) {
                if (plot.contains(corner.move(direction))) direction = potentialDirection;
            }

            if (direction == null) throw new RuntimeException("No possible direction");

            while (true) {
                if (!plot.contains(currentPos.move(direction))) {
                    sides++;
                }
            }
        }

        return sides;
    }
}
