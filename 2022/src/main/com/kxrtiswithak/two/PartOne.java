package com.kxrtiswithak.two;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartOne {

  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/two/ActualPuzzleInput.txt"))) {
      int totalScore = 0;
      while (fileScanner.hasNextLine()) {
        String[] stratGuide = fileScanner.nextLine().split(" ");
        String opponent = stratGuide[0];
        String me = stratGuide[1];

        /*
        * A X - Rock - 1
        * B Y - Paper - 2
        * C Z - Scissors - 3
        *
        * loss - 0
        * draw - 3
        * win - 6
        * */
        switch(opponent) {
          case "A":
            if (me.equals("X")) {
              totalScore += 4;
            } else if (me.equals("Y")) {
              totalScore += 8;
            } else {
              totalScore += 3;
            }
            break;
          case "B":
            if (me.equals("X")) {
              totalScore += 1;
            } else if (me.equals("Y")) {
              totalScore += 5;
            } else {
              totalScore += 9;
            }
            break;
          case "C":
            if (me.equals("X")) {
              totalScore += 7;
            } else if (me.equals("Y")) {
              totalScore += 2;
            } else {
              totalScore += 6;
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
