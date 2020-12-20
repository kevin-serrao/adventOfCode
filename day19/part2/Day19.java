package day19.part2;

import fileReader.FileReader;

public class Day19 {

    static int FIRST_INPUT_RULE_END = 136;
    static int FIRST_INPUT_INPUT_BEGIN = 137;
    static int SECOND_INPUT_RULE_END = 31;
    static int SECOND_INPUT_INPUT_BEGIN = 32;
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
        RuleSpec rule42 = rules.rules.get(42);
        RuleSpec rule8 = rules.rules.get(8);
        RuleSpec rule11 = rules.rules.get(11);
        System.out.println(rule42.matchingInputs.toString());
        System.out.println(rule8.matchingInputs.toString());
        System.out.println(rule11.matchingInputs.toString());
        System.out.println(total);
    }
}
