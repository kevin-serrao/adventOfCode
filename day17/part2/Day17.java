package day17.part2;

import java.util.*;

import fileReader.FileReader;

public class Day17 {
    public static void main(String[] args) {
        Hashtable<Key, List<Integer>> activePoints = new Hashtable<Key, List<Integer>>();
        String[] fileContents = FileReader.processFile();
        for (int i = 0; i < fileContents.length; i++) {
            String line = fileContents[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.substring(j, j + 1).equals("#")) {
                    Key key = new Key(i, j, 0, 0);
                    List<Integer> coords = List.of(i, j, 0, 0);
                    activePoints.put(key, coords);
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(activePoints.size());
            activePoints = step(activePoints);
        }
        System.out.println(activePoints.size());
    }

    public static Hashtable<Key, List<Integer>> step(Hashtable<Key, List<Integer>> activePoints) {
        Collection<List<Integer>> coords = activePoints.values();
        Hashtable<Key, List<Integer>> newActivePoints = new Hashtable<Key, List<Integer>>();
        for (List<Integer> coord: coords) {
            if (shouldBeActiveNextTurn(coord, activePoints)) {
                Key key = Key.fromCoordinates(coord);
                newActivePoints.put(key, coord);
            }
            List<List<Integer>> neighbors = getNeighbors(coord);
            for (List<Integer> neighbor: neighbors) {
                if (shouldBeActiveNextTurn(neighbor, activePoints)) {
                    Key key = Key.fromCoordinates(neighbor);
                    newActivePoints.put(key, neighbor);
                }
            }
        }
        return newActivePoints;
    }

    public static boolean shouldBeActiveNextTurn(List<Integer> coord, Hashtable<Key, List<Integer>> activePoints) {
        Key homeKey = Key.fromCoordinates(coord);
        boolean isActive = false;
        if (activePoints.containsKey(homeKey)) {
            isActive = true;
        }

        int activeNeighborsCount = 0;
        List<List<Integer>> neighbors = getNeighbors(coord);
        for (List<Integer> neighbor: neighbors) {
            Key neighborKey = Key.fromCoordinates(neighbor);
            if (activePoints.containsKey(neighborKey)) {
                activeNeighborsCount++;
            }
        }
        if ((isActive && activeNeighborsCount == 2) || activeNeighborsCount == 3) {
           return true;
        }
        return false;
    }

    public static List<List<Integer>> getNeighbors(List<Integer> coord) {
        int x = coord.get(0);
        int y = coord.get(1);
        int z = coord.get(2);
        int w = coord.get(3);
        List<List<Integer>> neighbors = new ArrayList<List<Integer>>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (i != 0 || j != 0 || k != 0 || l != 0) {
                            List<Integer> newList = List.of(x + i, y + j, z + k, w + l);
                            neighbors.add(newList);
                        }
                    }
                }
            }
        }
        return neighbors;
    }
}