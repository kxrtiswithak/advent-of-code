package com.kxrtiswithak.days.week1.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileToBingoData {
    private final String pathName;
    private List<String> fileAsList;

    public FileToBingoData(String pathName) {
        this.pathName = pathName;

        initialise();
    }

    private void initialise() {
        try {
            this.fileAsList = Files.readAllLines(Paths.get(this.pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getNumbers() {
        return Arrays.stream(this.fileAsList.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public int[][][] getBoards() {
        int line = 2;
        int noOfBoards = (fileAsList.size() - 1) / 6;
        int[][][] boardsList = new int[noOfBoards][5][5];
        while (line < this.fileAsList.size() - 1) {
            int[][] board = new int[5][5];
            int row = 0;
            while (row < 5) {
                int col = 0;
                for (int number : Arrays.stream(this.fileAsList.get(line).trim().split("\\s+")).mapToInt(Integer::parseInt).toArray()) {
                    board[row][col] = number;
                    col++;
                }
                row++;
                line++;
            }
            boardsList.add(board);
            line++;
        }
        return boardsList;
    }
}
