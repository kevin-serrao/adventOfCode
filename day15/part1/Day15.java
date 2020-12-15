package day15.part1;

import fileReader.FileReader;
import java.util.*;

public class Day15 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Hashtable<Integer, Integer> lastTurnSpokenByNumber = new Hashtable<Integer, Integer>();
        int nextNumber = 0;
        int previousNumber;
        for (int i = 0; i < 30000000; i++) {
            previousNumber = nextNumber;
            if (i < fileContents.length) {
                previousNumber = Integer.parseInt(fileContents[i]);
            } else {
                if (lastTurnSpokenByNumber.containsKey(previousNumber)) {
                    nextNumber = i - lastTurnSpokenByNumber.get(previousNumber);
                } else {
                    nextNumber = 0;
                }
            }
            lastTurnSpokenByNumber.put(previousNumber, i);
            if (i % 1000 == 0) {
                System.out.println(i);
                System.out.println(previousNumber);
            }
            if (i == 29999999) {
                System.out.println(i);
                System.out.println(previousNumber);
            }
        }
    }
}
