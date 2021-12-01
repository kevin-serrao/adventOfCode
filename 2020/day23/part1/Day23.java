package day23.part1;

import java.util.*;

import fileReader.FileReader;

public class Day23 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        List<String> cups = new ArrayList<String>();
        for (int i = 0; i < fileContents[0].length(); i++) {
            cups.add(fileContents[0].substring(i, i + 1));
        }
        int currentCupIndex = 0;
        for (int i = 0; i < 100; i++) {
            String currentCup = cups.get(currentCupIndex);
            List<String> untouchedCups = new ArrayList<String>();
            for (int j = currentCupIndex; j < currentCupIndex + cups.size(); j++) {
                int index = j % cups.size();
                if (index != (currentCupIndex + 1) % cups.size() && index != (currentCupIndex + 2) % cups.size() && index != (currentCupIndex + 3) % cups.size()) {
                    untouchedCups.add(cups.get(index));
                }
            }
            LinkedList<String> removedCups = new LinkedList<String>();
            for (int j = currentCupIndex + 1; j < currentCupIndex + 4; j++) {
                removedCups.add(cups.get(j % cups.size()));
            }
            String destinationCup = getDestinationCup(untouchedCups, currentCup);
            int destinationCupIndex = untouchedCups.indexOf(destinationCup);
            System.out.println("cups: " + cups);
            System.out.println("current: " + currentCup);
            System.out.println("pick up: " + removedCups);
            System.out.println("remaining: " + untouchedCups);
            System.out.println("destination: " + destinationCup);
            List<String> tempCups = new ArrayList<String>();
            for (int j = 0; j < untouchedCups.size(); j++) {
                tempCups.add(untouchedCups.get(j));
                if (j == destinationCupIndex) {
                    tempCups.add(removedCups.poll());
                    tempCups.add(removedCups.poll());
                    tempCups.add(removedCups.poll());
                } 
            }
            cups = tempCups;
            currentCupIndex = cups.indexOf(currentCup) + 1 < cups.size() ? cups.indexOf(currentCup) + 1 : 0;
            System.out.println(cups);
        }
    } 


    static String getDestinationCup(List<String> cups, String currentCup) {
        int cupValue = Integer.parseInt(currentCup);
        String destinationCup = cupValue > 1 ? String.valueOf(cupValue - 1) : "9";
        while (!cups.contains(destinationCup)) {
            cupValue = cupValue > 1 ? cupValue - 1 : 10;
            destinationCup = String.valueOf(cupValue - 1);
        }
        return destinationCup;
    }
}
