package day21.part1;

import java.util.*;

import fileReader.FileReader;

public class Day21 {
    public static void main(String[] args) {
        List<String> fileContents = FileReader.processFileAsList();
        AllergenDefinition allergenDefinition = new AllergenDefinition();
        for (String line: fileContents) {
            allergenDefinition.processLine(line);
        }
        int count = 0;
        while (allergenDefinition.hasUnmappedAllergens() && count < 10) {
            allergenDefinition.numberOfUsesByUnconfirmedIngredient = new Hashtable<String, Integer>();
            for (String line: fileContents) {
                allergenDefinition.processLine(line);
            }
            count++;
        }
        System.out.println(allergenDefinition.sumUsesOfNonAllergenicIngredients());
    }
}
