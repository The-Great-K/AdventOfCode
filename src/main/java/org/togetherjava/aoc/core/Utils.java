package org.togetherjava.aoc.core;

public class Utils {
    public static int trimLeadingZeros(String input) {
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c != '0') {
                return Integer.parseInt(input.substring(i));
            }
        }
        return 0;
    }
}
