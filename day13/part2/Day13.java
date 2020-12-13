package day13.part2;

import java.util.*;

import fileReader.FileReader;

public class Day13 {
    public static void main(String[] args) {
        // ANS 77
        // x % 7 == 0
        // (x + 1) % 13 == 0
        // x % 13 == 12 == 13 - 1

        // 13r + 12 == x
        // 7k = x
        // 13r = x + 1
        // 13r - 1 = 7k
        // k = (13r - 1) / 7 


        // (x + 4) % 59 == 0

        String[] fileContents = FileReader.processFile();
        String[] busIds = fileContents[1].split(",");
        List<Long> busCadences = new ArrayList<Long>();
        List<Integer> busCadenceOffsets = new ArrayList<Integer>();
        for (int i = 0; i < busIds.length; i++) {
            if (busIds[i].equals("x")) {
                continue;
            } else {
                busCadences.add(Long.parseLong(busIds[i]));
                busCadenceOffsets.add(i);
            }
        }
        boolean found = false;
        long increment = busCadences.get(0);
        long ans = busCadences.get(0);
        int testValIndex = 1;
        int testOffsetIndex = 1;
        while (!found) {
            long testVal = busCadences.get(testValIndex);
            int testOffset = busCadenceOffsets.get(testOffsetIndex);

            // long prevVal = busCadences.get(testValIndex - 1);
            // int prevValOffset = busCadenceOffsets.get(testOffsetIndex - 1);

            if ((ans + testOffset) % testVal == 0) {
                testValIndex++;
                testOffsetIndex++;
                increment *= testVal;
                if (testValIndex == busCadences.size()) {
                    found = true;
                }
                System.out.println(ans);
            } else {
                ans += increment;
            }
        }
        // int test = 77;
        // while (test < 10000) {
        //     if (test % 13 == 12) {
        //         System.out.println(test);
        //     }
        //     test += 7;
        // }
    }    
}
