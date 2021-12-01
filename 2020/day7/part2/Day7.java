package day7.part2;

import java.util.*;

import fileReader.*;

public class Day7 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Hashtable<String, BagType> bagTypeRegistry = new Hashtable<String, BagType>();
        for (int i = 0; i < fileContents.length; i++) {
            String line = fileContents[i];
            BagType bagType = new BagType(line);
            bagTypeRegistry.put(bagType.name, bagType);
        };
        BagType bt = bagTypeRegistry.get("shiny gold");
        System.out.println(bt);
        System.out.println(bt.getMinimumNumberOfBagsInside(bagTypeRegistry));
    }
}