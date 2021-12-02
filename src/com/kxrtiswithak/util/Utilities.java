package com.kxrtiswithak.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utilities {
    public static int[] fileToArray(String pathName) {
        List<String> fileInput = null;

        try {
            fileInput = Files.readAllLines(Paths.get(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert fileInput != null;
        return fileInput.stream().mapToInt(Integer::parseInt).toArray();
    }
}
