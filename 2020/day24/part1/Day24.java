package day24.part1;

import java.util.*;

import fileReader.FileReader;

public class Day24 {
    public static void main(String[] args) {
        HashSet<String> isFlippedSet = new HashSet<String>();
        List<String> fileContents = FileReader.processFileAsList();
        for (String line: fileContents) {
            int x = getXCoord(line);
            int y = getYCoord(line);
            String key = x + ", " + y;
            System.out.println(x + ", " + y);
            if (isFlippedSet.contains(key)) {
                isFlippedSet.remove(key);
            } else {
                isFlippedSet.add(key);
            }
        }
        System.out.println(isFlippedSet.size());
    }
    private static int getXCoord(String line) {
        int x = 0;
        int multiplier = 2;
        for (int i = 0; i < line.length(); i++) {
            if (line.substring(i, i+1).equals("n") || line.substring(i, i+1).equals("s")) {
                multiplier = 1;
            } else if (line.substring(i, i+1).equals("e")) {
                x += multiplier;
                multiplier = 2;
            } else if (line.substring(i, i+1).equals("w")) {
                x -= multiplier;
                multiplier = 2;
            }
        }
        return x;
    }

    private static int getYCoord(String line) {
        int y = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.substring(i, i+1).equals("n")) {
                y++;
            } else if (line.substring(i, i+1).equals("s")) {
                y--;
            } 
        }
        return y;
    }
}