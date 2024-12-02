package com.kxrtiswithak.six;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartTwo {

  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/six/ActualPuzzleInput.txt"))) {
      String dataStream = fileScanner.nextLine();
      
      int marker = 0;
      for (int i = 0; i < dataStream.length(); i++) {
        String markerChecker = dataStream.substring(i, i + 14);
        
        if (isStringDistinct(markerChecker)) {
          marker = i + 14;
          break;
        }
      }
      System.out.println(marker);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static boolean isStringDistinct(String str) {
    for (int i = 0; i < str.length(); i++) {
      for (int j = i + 1; j < str.length(); j++) {
        if (str.charAt(i) == str.charAt(j)) {
          return false;
        }
      }
    }
    return true;
  }
}
