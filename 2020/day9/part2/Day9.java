package day9.part2;

import fileReader.*;

public class Day9 {
    public static void main(String[] args) {
        int targetNumber = 26134589;
        String[] fileContents = FileReader.processFile();
        long[] numbers = new long[1000];
        for (int i =0; i < 1000; i++){
            numbers[i] = Long.parseLong(fileContents[i]);
        }
        long[][] dp = new long[1000][1000];
        dp[0][0] = numbers[0];
// 44, 44 + 17, 44 + 17 + 23...
// null, 17, 17 + 23, 17 + 23 + 42...
// null, null, 23, 23 + 42...
// 42
// 25
// 26
// 6
// 45
// 30
// 8
// 15
// 39
        for (int i = 0; i < 1000; i++) {
            dp[i][i] = numbers[i];
            for (int j = i + 1; j < 1000; j++) {
                dp[i][j] = dp[i][j-1] + numbers[j];
                if (dp[i][j] == targetNumber) {
                    getSmallestAndBiggestFromList(numbers, i, j);
                }
            }
        }
    }
    private static void getSmallestAndBiggestFromList(long[] numbers, int startIndex, int endIndex) {
        long max = 0;
        long min = numbers[startIndex];
        for (int i = startIndex; i < endIndex + 1; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            } if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        System.out.println(min);
        System.out.println(max);
        System.err.println(min + max);
    }
}
