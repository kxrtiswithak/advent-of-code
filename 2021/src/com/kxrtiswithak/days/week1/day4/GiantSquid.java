package com.kxrtiswithak.days.week1.day4;

public class GiantSquid {

    private static final String SAMPLE_PATH_NAME = "resources/day4/sample.txt";
//    private static final String TEST_PATH_NAME = "resources/day4/test.txt";

    public static void main(String[] args) {
        FileToBingoData sampleBingoData = new FileToBingoData(SAMPLE_PATH_NAME);
//        FileToBingoData testBingoData = new FileToBingoData(TEST_PATH_NAME);

        int[] sampleInputNumbers = sampleBingoData.getNumbers();
//        String[] testInputNumbers = testBingoData.getNumbers();
        int[][][] sampleInputBoards = sampleBingoData.getBoards();
//        List<HashMap<String, Boolean>> testInputBoards = testBingoData.getBoards();

        System.out.println(partOne(sampleInputNumbers, sampleInputBoards));

    }

    private static int partOne(int[] numbers, int[][][] boardsList) {
    }
}
