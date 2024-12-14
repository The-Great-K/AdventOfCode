package org.togetherjava.aoc.solutions;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.*;

// https://adventofcode.com/2024/day/9
@AdventDay(day = 9)
public class Day09 implements PuzzleSolution {
    @Override
    public Object part1(PuzzleInput input) {
        long result = 0L;

        LinkedList<Integer> diskList = new LinkedList<>();

        int id = 0;
        boolean isFile = true;
        for (char c : input.rawInput().toCharArray()) {
            if (Character.isWhitespace(c)) continue;
            for (byte i = 0; i < Integer.parseInt(String.valueOf(c)); i++) {
                diskList.add(isFile ? id : null);
            }

            if (isFile) id++;
            isFile = !isFile;
        }

        int firstFree = diskList.indexOf(null);
        while (true) {
            while (diskList.getLast() == null) diskList.removeLast();
            if (firstFree == diskList.size() - 1) break;

            diskList.set(firstFree, diskList.removeLast());
            firstFree = diskList.indexOf(null);
            if (firstFree == -1) break;
        }

        for (int i = 0; i < diskList.size(); i++) result += ((long) i * diskList.get(i));

        return result;
    }

    @Override
    public Object part2(PuzzleInput input) {
        long result = 0L;

        String line = input.rawInput();
        List<Info> diskList = new ArrayList<>();

        boolean isFile = true;
        int id = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isWhitespace(line.toCharArray()[i])) continue;
            int length = Integer.parseInt(String.valueOf(line.toCharArray()[i]));
            if (isFile) {
                diskList.add(new Info(true, length, id));
                id++;
            } else diskList.add(new Info(false, length, -1));
            isFile = !isFile;
        }

        int currentFileId = 0;
        Map<Integer, Info> fileLookup = new HashMap<>();
        for (Info file : diskList.stream().filter(Info::isFile).toList()) {
            fileLookup.put(file.id(), file);
            currentFileId = file.id();
        }

        while (currentFileId > 0) {
            Info biggestFile = fileLookup.get(currentFileId);
            int originalLoc = diskList.indexOf(biggestFile);
            var expectedSpace = biggestFile.size;
            for (int i = 0; i < originalLoc; ++i) {
                var current = diskList.get(i);
                if (!current.isFile && current.size >= expectedSpace) {
                    diskList.set(i, biggestFile);
                    diskList.set(originalLoc, new Info(false, expectedSpace, -1));
                    if (current.size > expectedSpace) {
                        diskList.add(i + 1, new Info(false, current.size - expectedSpace, -1));
                    }
                    break;
                }
            }
            currentFileId--;

            for (int i = 0; i < diskList.size() - 1; ++i) {
                var current = diskList.get(i);
                var next = diskList.get(i + 1);
                if (!current.isFile && !next.isFile) {
                    int totalSpace = current.size + next.size;
                    diskList.set(i, new Info(false, totalSpace, -1));
                    diskList.remove(i + 1);
                    i--;
                }
            }
        }

        int index = 0;
        for (Info info : diskList) {
            if (info.isFile) {
                for (int j = 0; j < info.size; ++j) {
                    result += ((long) index * info.id);
                    index++;
                }
            } else {
                index += info.size;
            }
        }
        return result;
    }

    private record Info(boolean isFile, int size, int id) {}
}
