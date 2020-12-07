package day7.part1;

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
        BagTypeWithParents bt = new BagTypeWithParents("shiny gold", bagTypeRegistry);
        Set<String> ancestorNames = new HashSet<String>();
        addBagAndParentsToSet(bt, ancestorNames);
        System.out.println(ancestorNames.size());
    }

    static private void addBagAndParentsToSet(BagTypeWithParents bt, Set<String> ancestorNames) {
        ancestorNames.add(bt.name);
        if (bt.parents != null) {
            for (BagTypeWithParents parent: bt.parents) {
                addBagAndParentsToSet(parent, ancestorNames);
            }
        }
    }
}