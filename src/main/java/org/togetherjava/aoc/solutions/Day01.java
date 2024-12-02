package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.api.AbstractDay;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Day01 extends AbstractDay {
	List<Integer> list1 = new ArrayList<>();
	List<Integer> list2 = new ArrayList<>();

	public Day01() {
		super(2024, 1);
	}

	@Override
	public Object part1Solution() {
		int result = 0;

		getInput().asStream().forEach(line -> {
			list1.add(Integer.parseInt(line.split(" {3}")[0]));
			list2.add(Integer.parseInt(line.split(" {3}")[1]));
		});

		Collections.sort(list1);
		Collections.sort(list2);

		for (int i = 0; i < list1.size(); i++) {
			result += (Math.abs(list1.get(i) - list2.get(i)));
		}

		return result;
	}

	@Override
	public Object part2Solution() {
		return ;
	}
}
