package day10.part2;

import java.util.*;
import fileReader.*;

public class Day10 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Integer[] numbersArray = new Integer[fileContents.length];
        for (int i =0; i < fileContents.length; i++){
            numbersArray[i] = Integer.parseInt(fileContents[i]);
        }
        Arrays.sort(numbersArray);
        int maxJoltage = numbersArray[numbersArray.length - 1] + 3;
        List<Integer> numbers = Arrays.asList(numbersArray);
        System.out.println(numbers);
        long[] dp = new long[numbers.size()];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        dp[4] = 7;
        dp[5] = 7;
        dp[6] = 14;
        dp[7] = 28;

        // 1,2,3; 1,3; 2,3; 3, 
        for (int i = 4; i < numbers.size(); i++) {
            if (numbers.get(i) - numbers.get(i - 1) == 3) {
                dp[i] = dp[i - 1];
            } else if (numbers.get(i) - numbers.get(i - 2) > 3) {
                dp[i] = dp[i - 1];
            } else if (numbers.get(i) - numbers.get(i - 3) > 3) {
                dp[i] = dp[i - 2] * 2;
            } else if (numbers.get(i) - numbers.get(i - 4) > 4) {
                dp[i] = dp[i - 3] * 4;
            } else {
                dp[i] = dp[i - 4] * 7;
            }
           System.out.println(numbers.get(i) + ", " + dp[i]);
        }
        System.out.println(dp[numbers.size() - 1]); 
    }

    public static int countPossibilities(int maxJoltage, List<Integer> numbers) {
        int total = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (isRemovableAtIndex(i, numbers)) {
                List<Integer> firstSublist = numbers.subList(0, i);
                List<Integer> secondSublist = numbers.subList(i + 1, numbers.size());
                List<Integer> newList = new ArrayList<Integer>(firstSublist);
                newList.addAll(secondSublist);
                total += countPossibilities(maxJoltage, newList);
            }
        }
        return total;
    }
    public static boolean isRemovableAtIndex(int i, List<Integer> numbers) {
        if (i == 0) {
            return numbers.get(1) < 4;
        }
        return numbers.get(i) - numbers.get(i-1) < 2 && numbers.get(i+1) - numbers.get(i) < 2;
    }
}