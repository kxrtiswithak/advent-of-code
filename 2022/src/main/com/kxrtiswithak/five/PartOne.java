package com.kxrtiswithak.five;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class PartOne {
  public static void main(String[] args) {
    try (Scanner fileScanner = new Scanner(Paths.get("src/resources/five/ActualPuzzleInput.txt"))) {
      List<Stack<Character>> stacks = new ArrayList<>();
      boolean isFirstLine = true;
      boolean isThereMoreStacks = true;
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        if (isThereMoreStacks) {
          if (isFirstLine) {
            isFirstLine = false;
            int stackNum = (line.length() + 1) / 4;
            for (int i = 0; i < stackNum; i++) {
              stacks.add(new Stack<>());
            }
          }

          int stackCount = 0;
          int whitespaceCount = 0;
          for (int i = 0; i < line.length(); i++) {
            if (Character.isWhitespace(line.charAt(i))) {
              whitespaceCount++;
              if (whitespaceCount % 4 == 0) {
                stackCount++;
                whitespaceCount = 0;
              }
            } else if (line.charAt(i) == '[') {
              stacks.get(stackCount).insertElementAt(line.charAt(++i), 0);
              whitespaceCount = 0;
              stackCount++;
            } else if (line.charAt(i) == ']') {
              i++;
            } else if (Character.isDigit(line.charAt(i))) {
              isThereMoreStacks = false;
//              System.out.println(Arrays.toString(stacks.toArray()));
            }
          }
        } else {
          if (!line.isEmpty()) {
            int[] instructions = Arrays.stream(line.split("\\s+\\D+|^\\D+"))
                .filter(instruction -> !instruction.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();

            for (int i = 0; i < instructions[0]; i++) {
              Character crate = stacks.get(instructions[1] - 1).pop();
              stacks.get(instructions[2] - 1).push(crate);
//              System.out.println(Arrays.toString(stacks.toArray()));
            }

          }
        }
      }
      StringBuilder topOfStacks = new StringBuilder();
      for (Stack<Character> stack : stacks) {
        topOfStacks.append(stack.peek());
      }
      System.out.println(topOfStacks);
//      System.out.println(Arrays.toString(stacks.toArray()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

