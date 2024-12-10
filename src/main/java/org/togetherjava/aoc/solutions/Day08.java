package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;
import org.togetherjava.aoc.core.utils.Coordinate;
import org.togetherjava.aoc.core.utils.Matrix2D;

import java.util.*;

// https://adventofcode.com/2024/day/8
@AdventDay(day = 8)
public class Day08 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        Map<Character, List<Coordinate>> antennas = getAntennas(input.toMatrix());
        Set<Coordinate> antinodes = new HashSet<>();

        antennas.keySet().forEach(antenna -> {
            List<Coordinate> antennaList = antennas.get(antenna);
            for (Coordinate i : antennaList) {
                for (Coordinate j : antennaList) {
                    int x = i.getX() + 2 * (j.getX() - i.getX());
                    int y = i.getY() + 2 * (j.getY() - i.getY());
                    Coordinate coordinate = new Coordinate(x, y);
                    if (input.toMatrix().isInBounds(coordinate)
                        && !coordinate.equals(i)
                        && !coordinate.equals(j)) antinodes.add(coordinate);
                }
            }
        });

        return antinodes.size();
    }

    @Override
    public Object part2(PuzzleInput input) {
        Map<Character, List<Coordinate>> antennas = getAntennas(input.toMatrix());
        Set<Coordinate> antinodes = new HashSet<>();

        antennas.keySet().forEach(antenna -> {
            List<Coordinate> antennaList = antennas.get(antenna);
            for (Coordinate i : antennaList) {
                for (Coordinate j : antennaList) {
                    antinodes.addAll(getEveryAntinodeInLine(i, j, input.toMatrix()));
                    antennas.keySet().forEach(antennaType -> antinodes.addAll(antennas.get(antennaType)));
                }
            }
        });

        return antinodes.size();
    }

    private Map<Character, List<Coordinate>> getAntennas(Matrix2D<Character> map) {
        Map<Character, List<Coordinate>> antennas = new HashMap<>();

        map.forEach((x, y, antenna) -> {
            if (!antenna.equals('.')) {
                antennas.putIfAbsent(antenna, new ArrayList<>());
                antennas.get(antenna).add(new Coordinate(x, y));
            }
        });

        return antennas;
    }

    private Set<Coordinate> getEveryAntinodeInLine(Coordinate point1, Coordinate point2, Matrix2D<Character> map) {
        Set<Coordinate> antinodes = new HashSet<>();

        if (point1.equals(point2)) return antinodes;

        int xVector = point2.getX() - point1.getX();
        int yVector = point2.getY() - point1.getY();

        int x = point1.getX() + 2 * xVector;
        int y = point1.getY() + 2 * yVector;
        while (true) {
            Coordinate coordinate = new Coordinate(x, y);
            if (!map.isInBounds(coordinate)) break;
            if (!coordinate.equals(point1) && !coordinate.equals(point2)) antinodes.add(coordinate);

            x += xVector;
            y += yVector;
        }

        return antinodes;
    }
}
