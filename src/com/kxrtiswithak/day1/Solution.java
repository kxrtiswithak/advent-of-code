package com.kxrtiswithak.day1;

import com.kxrtiswithak.util.Utilities;

public class Solution {
    private static final String SAMPLE_PATH_NAME = "resources/day1/sample.txt";
    private static final String TEST_PATH_NAME = "resources/day1/test.txt";
    private static final int[] SAMPLE_INPUT = Utilities.fileToArray(SAMPLE_PATH_NAME);
    private static final int[] TEST_INPUT = Utilities.fileToArray(TEST_PATH_NAME);

    public static void main(String[] args) {
        System.out.println(partOne(SAMPLE_INPUT));
        System.out.println(partOne(TEST_INPUT));
        System.out.println(partTwo(SAMPLE_INPUT));
        System.out.println(partTwo(TEST_INPUT));
    }



    private static int partOne(int[] input) {
        int count = 0;

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] < input[i + 1]) count++;
        }

        return count;
    }

    private static int partTwo(int[] input) {
        int count = 0;

        for (int i = 0; i < input.length - 3; i++) {
            int slidingWindow1 = input[i] + input[i + 1] + input[i + 2];
            int slidingWindow2 = input[i + 1] + input[i + 2] + input[i + 3];

            if (slidingWindow1 < slidingWindow2) count++;
        }

        return count;
    }

}
