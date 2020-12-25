package day23.part2;

import java.util.*;

import fileReader.FileReader;

public class Day23 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Hashtable<Integer, Cup> cupByLabel = new Hashtable<Integer, Cup>();
        int firstLabel = Integer.parseInt(fileContents[0].substring(0, 1));
        Cup firstCup = new Cup(firstLabel);
        cupByLabel.put(firstLabel, firstCup);
        Cup previousCup = firstCup;
        for (int i = 1; i < fileContents[0].length(); i++) {
            int label = Integer.parseInt(fileContents[0].substring(i, i + 1));
            Cup cup = new Cup(label);
            previousCup.nextCup = cup;
            cupByLabel.put(label, cup);
            cup.nextCup = firstCup;
            previousCup = cup;
        }
        for (int i = 10; i < 1000001; i++) {
            Cup cup = new Cup(i);
            previousCup.nextCup = cup;
            cupByLabel.put(i, cup);
            cup.nextCup = firstCup;
            previousCup = cup;
        }


        Cup currentCup = firstCup;
        for (int i = 0; i < 10000000; i++) {
            Cup removedCup = currentCup.nextCup;
            currentCup.nextCup = removedCup.nextCup.nextCup.nextCup;
            Cup destinationCup = cupByLabel.get(getDestinationCupLabel(removedCup, currentCup.label));
            removedCup.nextCup.nextCup.nextCup = destinationCup.nextCup;
            destinationCup.nextCup = removedCup;
            currentCup = currentCup.nextCup;
        }
        System.out.println("Answer: ");
        Cup oneCup = cupByLabel.get(1);
        System.out.println(oneCup.nextCup.label * oneCup.nextCup.nextCup.label);
    }



    static int getDestinationCupLabel(Cup removedCup, int currentCupLabel) {

        int destinationCupLabel = currentCupLabel > 1 ? currentCupLabel - 1 : 1000000;
        while (removedCup.label == destinationCupLabel ||
        removedCup.nextCup.label == destinationCupLabel ||
        removedCup.nextCup.nextCup.label == destinationCupLabel ) {
            destinationCupLabel = destinationCupLabel > 1 ? destinationCupLabel - 1 : 1000000;
        }
        return destinationCupLabel;
    }
}

class Cup {
    int label;
    Cup nextCup;
    public Cup(int newLabel) {
        label = newLabel;
    }
}