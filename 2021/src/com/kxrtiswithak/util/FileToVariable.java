package com.kxrtiswithak.util;

import java.util.List;

public class FileToVariable {
    public static int[] fileToIntArray(String pathName) {
        List<String> fileAsList = FileToStringList.fileToList(pathName);
        return fileAsList.stream().mapToInt(Integer::parseInt).toArray();
    }

    public static String[][] fileTo2dArray(String pathname) {
        List<String> fileAsList = FileToStringList.fileToList(pathname);
        String[][] string2dArray = new String[fileAsList.size()][2];

        for (int i = 0; i < fileAsList.size(); i++) {
            string2dArray[i][0] = fileAsList.get(i).split(" ")[0];
            string2dArray[i][1] = fileAsList.get(i).split(" ")[1];
        }

        return string2dArray;
    }
}
