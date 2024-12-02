package com.kxrtiswithak.four;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PartTwo {
  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/four/ActualPuzzleInput.txt"))) {
      int overlappingPairs = 0;
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] pair = line.split(",");
        List<String> pairSplit = new ArrayList<>();
        Collections.addAll(pairSplit, pair[0].split("-"));
        Collections.addAll(pairSplit, pair[1].split("-"));
        List<Integer> pairSplitInt = pairSplit.stream()
            .map(s -> Integer.parseInt(s))
            .collect(Collectors.toList());

        boolean one = pairSplitInt.get(0) >= pairSplitInt.get(2) && pairSplitInt.get(0) <= pairSplitInt.get(3);
        boolean two = pairSplitInt.get(1) >= pairSplitInt.get(2) && pairSplitInt.get(1) <= pairSplitInt.get(3);
        boolean three = pairSplitInt.get(2) >= pairSplitInt.get(0) && pairSplitInt.get(2) <= pairSplitInt.get(1);
        boolean four = pairSplitInt.get(3) >= pairSplitInt.get(0) && pairSplitInt.get(3) <= pairSplitInt.get(1);

        if (one || two || three || four) {
          overlappingPairs++;
        }
      }
      System.out.println(overlappingPairs);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

