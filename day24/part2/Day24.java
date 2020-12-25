package day24.part2;

import java.util.*;

import fileReader.FileReader;

public class Day24 {
    public static void main(String[] args) {
        HashSet<String> isFlippedSet = new HashSet<String>();
        List<String> fileContents = FileReader.processFileAsList();
        for (String line: fileContents) {
            int x = getXCoord(line);
            int y = getYCoord(line);
            String key = x + "," + y;
            System.out.println(key);
            if (isFlippedSet.contains(key)) {
                isFlippedSet.remove(key);
            } else {
                isFlippedSet.add(key);
            }
        }
        for (int i =0; i < 100; i++) {
            Iterator<String> keys = isFlippedSet.iterator();
            HashSet<String> newSet = new HashSet<String>(isFlippedSet);
            while (keys.hasNext()) {
                String key = keys.next();
                List<String> neighbors = getNeighbors(key);
                int numberOfFlippedNeighbors = getNumberOfFlippedNeighbors(isFlippedSet, neighbors);
                if (numberOfFlippedNeighbors == 0 || numberOfFlippedNeighbors > 2 ) {
                    newSet.remove(key);
                }
                for (String neighbor: neighbors) {
                    flipIfNecessary(neighbor, isFlippedSet, newSet);
                }
            }
            isFlippedSet = newSet;
        }
        System.out.println(isFlippedSet.size());
    }

    public static void flipIfNecessary(String key, HashSet<String> isFlippedSet, HashSet<String> newSet) {
        List<String> neighbors = getNeighbors(key);
        int numberOfFlippedNeighbors = getNumberOfFlippedNeighbors(isFlippedSet, neighbors);
        if (numberOfFlippedNeighbors == 2 && !isFlippedSet.contains(key)) {
            newSet.add(key);
        }
    }

// (2, 0), (1, 1), (-1, 1), (-2, 0), (-1, -1), (1, -1) 
    private static List<String> getNeighbors(String key) {
        List<String> neighbors = new ArrayList<String>();
        int x = Integer.parseInt(key.substring(0, key.indexOf(",")));
        int y = Integer.parseInt(key.substring(key.indexOf(",") + 1));
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 && j != 0) {
                    int newY = y + j;
                    int newX = x + i;
                    String neighbor = newX + "," + newY;
                    neighbors.add(neighbor);
                }
            }
        }
        neighbors.add(x + 2 + "," + y);
        neighbors.add(x - 2 + "," + y);
        return neighbors;
    }

    private static int getNumberOfFlippedNeighbors(Set<String> isFlippedSet, List<String> neighbors) {
        int total = 0;
        for (String neighbor: neighbors) {
            if (isFlippedSet.contains(neighbor)) {
                total++;
            }
        }
        return total;
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