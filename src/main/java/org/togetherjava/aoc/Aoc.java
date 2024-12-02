package org.togetherjava.aoc;

import org.togetherjava.aoc.api.AbstractDay;
import org.togetherjava.aoc.solutions.Day01;

public class Aoc {
    public static void main(String[] args) {
        String sessionCookie = System.getenv("session_cookie");
        if(sessionCookie == null) {
            throw new IllegalStateException("You must set your session_cookie environment variable in your gradle settings.");
        }

        AbstractDay day = new Day01();
        System.out.println("#".repeat(50));
        System.out.printf("Part 1 solution: %s%n", day.part1Solution());
        System.out.printf("Part 2 solution: %s%n", day.part2Solution());
        System.out.println("#".repeat(50));
    }
}
