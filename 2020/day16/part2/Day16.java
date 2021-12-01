package day16.part2;

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

        Ticket myTicket = Ticket.fromString(fileContents[MY_TICKET_INDEX]);

        List<Ticket> validTickets = new ArrayList<Ticket>();
        for (int i = OTHER_TICKETS_STARTING_INDEX; i < fileContents.length; i++) {
            Ticket ticket = Ticket.fromString(fileContents[i]);
            List<Integer> invalidValues = ticket.getInvalidValues(rules);
            if (invalidValues.size() == 0) {
                validTickets.add(ticket);
            }
        }
        rules.configureFieldsToColumns(validTickets);
        System.out.println(rules.getDepartureFieldProduct(myTicket));
    }
}
