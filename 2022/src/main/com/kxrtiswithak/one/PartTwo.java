package com.kxrtiswithak.one;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class PartTwo {
  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/one/ActualPuzzleInput.txt"))) {
      List<Integer> allCalories = new ArrayList<>();
      int currentCalories = 0;
      int top3Calories = 0;

      while (fileScanner.hasNextLine()) {
        String calLine = fileScanner.nextLine();
        if (calLine.isEmpty()) {
          allCalories.add(currentCalories);
          currentCalories = 0;
        } else {
          currentCalories += Integer.parseInt(calLine);
        }
      }

      allCalories.sort(Collections.reverseOrder());
      for (int calories : allCalories.subList(0, 3)) {
        top3Calories += calories;
      }
      System.out.println(top3Calories);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
