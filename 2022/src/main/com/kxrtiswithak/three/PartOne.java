package com.kxrtiswithak.three;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartOne {

  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/three/ActualPuzzleInput.txt"))) {
      int prioritiesSum = 0;
      while (fileScanner.hasNextLine()) {
        String rucksack = fileScanner.nextLine();
        final int mid = rucksack.length() / 2;
        String[] compartments = {rucksack.substring(0, mid), rucksack.substring(mid)};

        outerLoop:
        for (char first : compartments[0].toCharArray()) {
          for (char second : compartments[1].toCharArray()) {
            if (first == second) {
              if (Character.isUpperCase(first)) {
                prioritiesSum += first - 38;
              } else {
                prioritiesSum += first - 96;
              }
              break outerLoop;
            }
          }
        }
      }
      System.out.println(prioritiesSum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
