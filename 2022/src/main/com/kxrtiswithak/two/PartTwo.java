package com.kxrtiswithak.two;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartTwo {

  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/two/ActualPuzzleInput.txt"))) {
      int totalScore = 0;
      while (fileScanner.hasNextLine()) {
        String[] stratGuide = fileScanner.nextLine().split(" ");
        String opponent = stratGuide[0];
        String me = stratGuide[1];

        /*
        * A - Rock - 1
        * B - Paper - 2
        * C - Scissors - 3
        *
        * loss X - 0
        * draw Y - 3
        * win Z - 6
        * */
        switch(me) {
          case "X":
            if (opponent.equals("A")) {
              totalScore += 3;
            } else if (opponent.equals("B")) {
              totalScore += 1;
            } else {
              totalScore += 2;
            }
            break;
          case "Y":
            totalScore += 3;
            if (opponent.equals("A")) {
              totalScore += 1;
            } else if (opponent.equals("B")) {
              totalScore += 2;
            } else {
              totalScore += 3;
            }
            break;
          case "Z":
            totalScore += 6;
            if (opponent.equals("A")) {
              totalScore += 2;
            } else if (opponent.equals("B")) {
              totalScore += 3;
            } else {
              totalScore += 1;
            }
            break;
        }
      }
      System.out.println(totalScore);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
