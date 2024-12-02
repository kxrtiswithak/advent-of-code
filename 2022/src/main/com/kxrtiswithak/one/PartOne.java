package com.kxrtiswithak.one;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartOne {
  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/one/ActualPuzzleInput.txt"))) {
      int biggestCalories = 0;
      int currentCalories = 0;
      while (fileScanner.hasNextLine()) {
        String calLine = fileScanner.nextLine();
        if (calLine.isEmpty()) {
          if (currentCalories > biggestCalories) {
            biggestCalories = currentCalories;
          }
          currentCalories = 0;
        } else {
          currentCalories += Integer.parseInt(calLine);
        }
      }

      System.out.println(biggestCalories);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
