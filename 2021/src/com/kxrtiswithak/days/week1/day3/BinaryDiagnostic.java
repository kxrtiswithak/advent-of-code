package com.kxrtiswithak.days.week1.day3;

import com.kxrtiswithak.util.FileToStringList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryDiagnostic {
    private static final String SAMPLE_PATH_NAME = "resources/day3/sample.txt";
    private static final String TEST_PATH_NAME = "resources/day3/test.txt";
    private static final List<String> SAMPLE_INPUT = FileToStringList.fileToList(SAMPLE_PATH_NAME);
    private static final List<String> TEST_INPUT = FileToStringList.fileToList(TEST_PATH_NAME);

    public static void main(String[] args) {
        System.out.println(partOne(SAMPLE_INPUT));
        System.out.println(partOne(TEST_INPUT));
        System.out.println(partTwo(SAMPLE_INPUT));
        System.out.println(partTwo(TEST_INPUT));
    }

    private static int partOne(List<String> input) {
        StringBuilder gammaRateBinary = new StringBuilder();
        StringBuilder epsilonRateBinary = new StringBuilder();
        int gammaRateDecimal;
        int epsilonRateDecimal;

        boolean[] oneBitCounts = getBitCounts(input);

        for (boolean oneBitCount : oneBitCounts) {
            if (oneBitCount) {
                gammaRateBinary.append(1);
                epsilonRateBinary.append(0);
            } else {
                gammaRateBinary.append(0);
                epsilonRateBinary.append(1);
            }
        }

        gammaRateDecimal = Integer.parseInt(gammaRateBinary.toString(), 2);
        epsilonRateDecimal = Integer.parseInt(epsilonRateBinary.toString(), 2);

        return epsilonRateDecimal * gammaRateDecimal;
    }


    private static int partTwo(List<String> input) {
        List<String> oxygenList = new ArrayList<>(input);
        List<String> co2List = new ArrayList<>(input);

        String oxygenBinary = getValidBinary(oxygenList, true);
        String co2Binary = getValidBinary(co2List, false);

        return Integer.parseInt(oxygenBinary, 2) * Integer.parseInt(co2Binary, 2);

    }

    private static String getValidBinary(List<String> binaryList, boolean isOxygen) {
        int column = 0;
        while (binaryList.size() > 1) {
            int ones = 0;
            int zeroes = 0;
            char winner;

            for (String binaryNumber : binaryList) {
                if (binaryNumber.charAt(column) == '0') {
                    zeroes++;
                } else {
                    ones++;
                }
            }

            if (isOxygen) {
                winner = ones >= zeroes ? '1' : '0';
            } else {
                winner = ones >= zeroes ? '0' : '1';
            }

            for (int row = binaryList.size() - 1; row >= 0; row--) {
                if (binaryList.get(row).charAt(column) != winner) binaryList.remove(row);
            }
            column++;
        }
        return binaryList.get(0);
    }

    private static boolean[] getBitCounts(List<String> input) {
        int[] oneBitCounts = new int[input.get(0).length()];
        boolean[] result = new boolean[input.get(0).length()];
        Arrays.fill(oneBitCounts, 0);

        for (String binaryNumber : input) {
            for (int j = 0; j < binaryNumber.length(); j++) {
                oneBitCounts[j] += Integer.parseInt(String.valueOf(binaryNumber.charAt(j)));
            }
        }

        for (int i = 0; i < oneBitCounts.length; i++) {
            result[i] = oneBitCounts[i] >= input.size() - oneBitCounts[i];
        }

        return result;
    }
}
