package day10.part1;

import java.util.*;
import fileReader.*;

public class Day10 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        int[] numbers = new int[fileContents.length];
        for (int i =0; i < fileContents.length; i++){
            numbers[i] = Integer.parseInt(fileContents[i]);
        }
        Arrays.sort(numbers);
        int numberOfThrees = 0;
        int numberOfOnes = 0;
        int currentJoltage = 0;
        System.out.println(numbers);
        for (int i = 0; i < numbers.length; i++) {
            int difference = numbers[i] - currentJoltage;
            currentJoltage = numbers[i];
            System.out.println(difference);
            if (difference == 1) {
                numberOfOnes++;
            } if (difference == 3) {
                numberOfThrees++;
            }
        }
        System.out.println(numberOfOnes * (numberOfThrees + 1));
    }
}