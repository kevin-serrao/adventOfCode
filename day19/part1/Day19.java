package day19.part1;

import fileReader.FileReader;

public class Day19 {

    static int FIRST_INPUT_RULE_END = 136;
    static int FIRST_INPUT_INPUT_BEGIN = 137;
    static int SECOND_INPUT_RULE_END = 7;
    static int SECOND_INPUT_INPUT_BEGIN = 8;
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Rules rules = new Rules();
        for (int i = 0; i < FIRST_INPUT_RULE_END; i++) {
            rules.addRule(fileContents[i]);
        }
        int total = 0;
        for (int i = FIRST_INPUT_INPUT_BEGIN; i < fileContents.length; i++) {
            if (rules.isValid(fileContents[i])) {
                System.out.println("found a good input");
                total++;
            } else {
                System.out.println("Found a bad input");
            }
        }
        System.out.println(total);
    }
}
