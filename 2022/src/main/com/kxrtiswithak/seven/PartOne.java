package com.kxrtiswithak.seven;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartOne {

  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/seven/ActualPuzzleInput.txt"))) {
      List<String> dir = new ArrayList<>();
      Map<String, Integer> dirSizes = new HashMap<>();
      int spaceToDelete = 0;
      while (fileScanner.hasNextLine()) {
        String terminalLine = fileScanner.nextLine();
        String[] terminalParts = terminalLine.split(" ");
        
        if (terminalParts[0].equals("$")) {
          if (terminalParts[1].equals("cd")) {
            if (terminalParts[2].equals("/")) {
              dir.clear();
              dir.add(terminalParts[2]);
            } else if (terminalParts[2].equals("..")) {
              dir.remove(dir.size() - 1);
            } else {
              dir.add(terminalParts[2]);
            }
          } else if (terminalParts[1].equals("ls")) {
            dirSizes.put(dir.get(dir.size() - 1), 0);
          }
        } else if (!terminalParts[0].equals("dir")) {
          int fileSize = Integer.parseInt(terminalParts[0]);
          for (String folderName : dir) {
            dirSizes.computeIfPresent(folderName, (key, folderSize) -> folderSize + fileSize);
          }
        }
      }
      
      for (Map.Entry<String, Integer> set : dirSizes.entrySet()) {
        if (set.getValue() <= 100000) {
          spaceToDelete += set.getValue();
        }
      }

      System.out.println(spaceToDelete);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
