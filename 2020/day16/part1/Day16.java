package day16.part1;

import java.util.*;

import fileReader.FileReader;

public class Day16 {

    static int END_OF_RULES_INDEX = 20;
    static int MY_TICKET_INDEX = 22;
    static int OTHER_TICKETS_STARTING_INDEX = 25;
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Rules rules = new Rules();
        for (int i = 0; i < END_OF_RULES_INDEX; i++) {
            rules.addRule(fileContents[i]);
        }
        rules.printRules();
        int errorRate = 0;

        for (int i = OTHER_TICKETS_STARTING_INDEX; i < fileContents.length; i++) {
            Ticket ticket = Ticket.fromString(fileContents[i]);
            List<Integer> invalidValues = ticket.getInvalidValues(rules);
            for (int invalidValue: invalidValues) {
                System.out.println(invalidValue + " is invalid");
                errorRate += invalidValue;
            }
        }
        System.out.println(errorRate);
    }
}
