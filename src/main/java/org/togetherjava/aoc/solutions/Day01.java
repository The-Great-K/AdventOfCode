package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://adventofcode.com/2024/day/1
@AdventDay(day = 1)
public class Day01 implements PuzzleSolution {
	public Object part1(PuzzleInput input) {
		int result = 0;
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();

		input.getLines().forEach(line -> {
			try {
				left.add(Integer.parseInt(line.split(" {3}")[0]));
				right.add(Integer.parseInt(line.split(" {3}")[1]));
			} catch (NumberFormatException _) { System.out.println(line); }
		});

		Collections.sort(left);
		Collections.sort(right);

		for (int i = 0; i < left.size(); i++) {
			result += (Math.abs(left.get(i) - right.get(i)));
		}

		return result;
	}

	public Object part2(PuzzleInput input) {
		int result = 0;
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();

		input.getLines().forEach(line -> {
			try {
				left.add(Integer.parseInt(line.split(" {3}")[0]));
				right.add(Integer.parseInt(line.split(" {3}")[1]));
			} catch (NumberFormatException _) { System.out.println(line); }
		});

		for (int i : left) {
			if (!right.contains(i)) continue;

			int count = 0;
			for (int j : right) {
				if (j == i) count++;
			}

			result += i * count;
		}

		return result;
	}
}
