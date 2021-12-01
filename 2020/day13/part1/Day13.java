package day13.part1;

import java.util.*;

import fileReader.FileReader;

public class Day13 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        int readyTime = Integer.parseInt(fileContents[0]);
        int waitTime = 0;
        String[] busIds = fileContents[1].split(",");
        List<Integer> busCadences = new ArrayList<Integer>();
        for (int i = 0; i < busIds.length; i++) {
            if (busIds[i].equals("x")) {
                continue;
            } else {
                busCadences.add(Integer.parseInt(busIds[i]));
            }
        }
        boolean found = false;
        while (!found) {
            for (int busCadence: busCadences) {
                if ((readyTime + waitTime) % busCadence == 0) {
                    found = true;
                    System.out.println(busCadence * waitTime);
                }
            }
            waitTime++;
        }
    }    
}
