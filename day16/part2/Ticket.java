package day16.part2;

import java.util.*;

public class Ticket {
    List<Integer> values;

    public Ticket() {
        values = new ArrayList<Integer>();
    }

    public static Ticket fromString(String ticketString) {
        Ticket ticket = new Ticket();
        String[] valueArray = ticketString.split(",");
        for (int i =0; i < valueArray.length; i++) {
            ticket.values.add(Integer.parseInt(valueArray[i]));
        }   
        return ticket;
    }

    public List<Integer> getInvalidValues(Rules rules) {
        List<Integer> returnList = new ArrayList<Integer>();
        for (int value: values) {
            if (rules.isInvalidValue(value)) {
                returnList.add(value);
            }
        }
        return returnList;
    }
}
