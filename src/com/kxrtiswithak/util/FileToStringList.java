package com.kxrtiswithak.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileToStringList {
    public static List<String> fileToList(String pathName) {
        List<String> fileAsList = null;

        try {
            fileAsList = Files.readAllLines(Paths.get(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileAsList;
    }
}
