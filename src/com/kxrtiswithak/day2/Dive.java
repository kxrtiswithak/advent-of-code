package com.kxrtiswithak.day2;

import com.kxrtiswithak.util.FileToVariable;

public class Dive {
    private static final String SAMPLE_PATH_NAME = "resources/day2/sample.txt";
    private static final String TEST_PATH_NAME = "resources/day2/test.txt";
    private static final String[][] SAMPLE_INPUT = FileToVariable.fileTo2dArray(SAMPLE_PATH_NAME);
    private static final String[][] TEST_INPUT = FileToVariable.fileTo2dArray(TEST_PATH_NAME);

    public static void main(String[] args) {
        System.out.println(partOne(SAMPLE_INPUT));
        System.out.println(partOne(TEST_INPUT));
        System.out.println(partTwo(SAMPLE_INPUT));
        System.out.println(partTwo(TEST_INPUT));
    }

    private static int partOne(String[][] directionsArr) {
        int forward = 0;
        int depth = 0;

        for (String[] direction : directionsArr) {
            switch (direction[0]) {
                case "forward":
                    forward += Integer.parseInt(direction[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(direction[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(direction[1]);
                    break;
                default:
                    break;
            }
        }

        return forward * depth;
    }

    private static int partTwo(String[][] directionsArr) {
        int aim = 0;
        int horizontal = 0;
        int depth = 0;

        for (String[] direction : directionsArr) {
            switch (direction[0]) {
                case "forward":
                    horizontal += Integer.parseInt(direction[1]);
                    depth += Integer.parseInt(direction[1]) * aim;
                    break;
                case "up":
                    aim -= Integer.parseInt(direction[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(direction[1]);
                    break;
                default:
                    break;
            }
        }

        return horizontal * depth;
    }
}
