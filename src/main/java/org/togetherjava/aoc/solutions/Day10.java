package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;
import org.togetherjava.aoc.core.utils.Coordinate;
import org.togetherjava.aoc.core.utils.Direction;
import org.togetherjava.aoc.core.utils.Matrix2D;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// https://adventofcode.com/2024/day/10
@AdventDay(day = 10)
public class Day10 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        AtomicInteger result = new AtomicInteger(0);

        Matrix2D<Integer> map = input.toIntegerMatrix();

        map.forEach((x, y, _) -> result.addAndGet(getTrailheadScore(map, new Coordinate(x, y))));

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        AtomicInteger result = new AtomicInteger(0);

        Matrix2D<Integer> map = input.toIntegerMatrix();

        map.forEach((x, y, _) -> result.addAndGet(getTrailheadRating(map, new Coordinate(x, y))));

        return result;
    }

    private int getTrailheadScore(Matrix2D<Integer> map, Coordinate start) {
        if (map.get(start) != 0) return 0;

        Set<Coordinate> trails = new HashSet<>(Set.of(start));

        for (int i = 1; i < 10; i++) {
            Set<Coordinate> removalQueue = new HashSet<>();
            Set<Coordinate> addQueue = new HashSet<>();
            for (Coordinate trail : trails) {
                Set<Coordinate> newTrails = new HashSet<>();
                for (Direction direction : Direction.CARDINAL) {
                    try {
                        if (map.get(trail.move(direction)).equals(i)) newTrails.add(trail.move(direction));
                    } catch (ArrayIndexOutOfBoundsException _) {}
                }
                removalQueue.add(trail);
                addQueue.addAll(newTrails);
            }
            trails.removeAll(removalQueue);
            trails.addAll(addQueue);
        }

        return trails.size();
    }

    private int getTrailheadRating(Matrix2D<Integer> map, Coordinate start) {
        if (map.get(start) != 0) return 0;

        List<Coordinate> trails = new LinkedList<>(List.of(start));

        for (int i = 1; i < 10; i++) {
            List<Coordinate> removalQueue = new ArrayList<>();
            List<Coordinate> addQueue = new ArrayList<>();
            for (Coordinate trail : trails) {
                Set<Coordinate> newTrails = new HashSet<>();
                for (Direction direction : Direction.CARDINAL) {
                    try {
                        if (map.get(trail.move(direction)).equals(i)) newTrails.add(trail.move(direction));
                    } catch (ArrayIndexOutOfBoundsException _) {}
                }
                removalQueue.add(trail);
                addQueue.addAll(newTrails);
            }
            removalQueue.forEach(trails::remove);
            trails.addAll(addQueue);
        }

        return trails.size();
    }
}
