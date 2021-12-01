package day9.part1;

import fileReader.*;

public class Day9 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        NumberSet numberSet = new NumberSet();
        int i;
        for (i = 0; i < 25; i++) {
            numberSet.addNumberToNumberSet(fileContents[i]);
            System.out.println(numberSet.getSums());
        }
        // i == 25
        System.out.println(i);
        int number = Integer.parseInt(fileContents[i]);
        while (numberSet.isLegalNextNumber(number)) {
            System.out.println(numberSet.numbers.size());
            numberSet.cutFirstNumberOut();
            numberSet.addNumberToNumberSet(fileContents[i]);
            i++;    
            number = Integer.parseInt(fileContents[i]);
        }
        System.out.println(number);
    }
}
