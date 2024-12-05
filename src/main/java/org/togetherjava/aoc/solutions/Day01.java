package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// https://adventofcode.com/2024/day/1
public class Day01 extends AbstractDay {
	List<Integer> left = new ArrayList<>();
	List<Integer> right = new ArrayList<>();

	public Day01() {
		super(2024, 1);

		getInput().asStream().forEach(line -> {
			left.add(Integer.parseInt(line.split(" {3}")[0]));
			right.add(Integer.parseInt(line.split(" {3}")[1]));
		});
	}

	@Override
	public Object part1Solution() {
		int result = 0;

		Collections.sort(left);
		Collections.sort(right);

		for (int i = 0; i < left.size(); i++) {
			result += (Math.abs(left.get(i) - right.get(i)));
		}

		return result;
	}

	@Override
	public Object part2Solution() {
		int result = 0;

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
