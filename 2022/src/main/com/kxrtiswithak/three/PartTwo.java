package com.kxrtiswithak.three;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartTwo {

  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/three/ActualPuzzleInput.txt"))) {
      int prioritiesSum = 0;
      while (fileScanner.hasNextLine()) {
        String[] elfGroup = {fileScanner.nextLine(), fileScanner.nextLine(), fileScanner.nextLine()};

        outerLoop:
        for (char elfOne : elfGroup[0].toCharArray()) {
          for (char elfTwo : elfGroup[1].toCharArray()) {
            for (char elfThree : elfGroup[2].toCharArray()) {
              if (elfOne == elfTwo && elfTwo == elfThree) {
                if (Character.isUpperCase(elfOne)) {
                  prioritiesSum += elfOne - 38;
                } else {
                  prioritiesSum += elfOne - 96;
                }
                break outerLoop;
              }
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

