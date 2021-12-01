package day16.part2;

import java.util.*;

public class Rules {
    List<Rule> rules;
    Hashtable<String, Integer> columnIndexByFieldName;
    Set<String> takenFieldNames;

    public Rules() {
        rules = new ArrayList<Rule>();
        columnIndexByFieldName = new Hashtable<String, Integer>();
        takenFieldNames = new HashSet<String>();
    }

    public void addRule(String ruleString) {
        int colonIndex = ruleString.indexOf(":");
        String fieldName = ruleString.substring(0, colonIndex);
        int firstDashIndex = ruleString.indexOf("-");
        int firstRangeStart = Integer.parseInt(ruleString.substring(colonIndex + 1, firstDashIndex).trim());
        int orIndex = ruleString.indexOf("or", colonIndex);
        int firstRangeEnd = Integer.parseInt(ruleString.substring(firstDashIndex + 1, orIndex).trim());
        int secondDashIndex = ruleString.indexOf("-", firstDashIndex + 1);
        int secondRangeStart = Integer.parseInt(ruleString.substring(orIndex + 2, secondDashIndex).trim());
        int secondRangeEnd = Integer.parseInt(ruleString.substring(secondDashIndex + 1).trim());
        rules.add(new Rule(fieldName, firstRangeStart, firstRangeEnd, secondRangeStart, secondRangeEnd));
    }

    public boolean isInvalidValue(int value) {
        for (Rule rule: rules) {
            if (rule.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    public void configureFieldsToColumns(List<Ticket> validTickets) {
        int ticketSize = validTickets.get(0).values.size();
        Hashtable<Integer, List<Integer>> valuesByColumnIndex = getValuesByColumnIndex(validTickets);
        Hashtable<Integer, List<String>> possibleFieldNameByColumnIndex = getPossibleFieldNameByColumnIndex(valuesByColumnIndex, ticketSize);
        for (int i = 0; i < ticketSize; i++) {
            int column = findBestColumn(possibleFieldNameByColumnIndex, ticketSize);
            String fieldName = getBestName(possibleFieldNameByColumnIndex.get(column));
            setIndexByFieldName(column, fieldName);
            deletePossibleFieldNames(fieldName);
        }
    }

    private int findBestColumn(Hashtable<Integer, List<String>> possibleFieldNameByColumnIndex, int ticketSize) {
        for (int i =0; i< ticketSize; i++) {
            int numberOfValidFieldsLeft = 0;
            for (String fieldName: possibleFieldNameByColumnIndex.get(i)) {
                if (!takenFieldNames.contains(fieldName)) {
                    System.out.println(fieldName + " could be at: " + i);
                    numberOfValidFieldsLeft += 1;
                }
            }
            if (numberOfValidFieldsLeft == 1) {
                return i;
            }
        }
        throw new Error("Couldn't find best column index!");
    }

    private String getBestName(List<String> names) {
        for (String name: names) {
            if (!takenFieldNames.contains(name)) {
                return name;
            }
        }
        throw new Error("Couldn't find best name!");
    }

    private void setIndexByFieldName(int column, String fieldName) {
        columnIndexByFieldName.put(fieldName, column);
    }

    private void deletePossibleFieldNames(String fieldName) {
        takenFieldNames.add(fieldName);
    }

    private Hashtable<Integer, List<Integer>> getValuesByColumnIndex(List<Ticket> validTickets) {
        Hashtable<Integer, List<Integer>> valuesByColumnIndex = new Hashtable<Integer, List<Integer>>();
        for (Ticket validTicket: validTickets) {
            for (int i = 0; i < validTicket.values.size(); i++) {
                if (valuesByColumnIndex.get(i) == null) {
                    List<Integer> newList = new ArrayList<Integer>();
                    newList.add(validTicket.values.get(i));
                    valuesByColumnIndex.put(i, newList);
                } else {
                    valuesByColumnIndex.get(i).add(validTicket.values.get(i));
                }
            }
        }
        return valuesByColumnIndex;
    }

    private Hashtable<Integer, List<String>> getPossibleFieldNameByColumnIndex(Hashtable<Integer, List<Integer>> valuesByColumnIndex, int ticketLength) {
         Hashtable<Integer, List<String>> possibleFieldNameByColumnIndex = new Hashtable<Integer, List<String>>();
         for (int i = 0; i < ticketLength; i++) {
             for (Rule rule: rules) {
                if (couldBeField(rule, valuesByColumnIndex.get(i))) {
                    if (possibleFieldNameByColumnIndex.get(i) == null) {
                        List<String> newList = new ArrayList<String>();
                        newList.add(rule.fieldName);
                        possibleFieldNameByColumnIndex.put(i, newList);
                    } else {
                        possibleFieldNameByColumnIndex.get(i).add(rule.fieldName);
                    }
                }
             }
         }
         return possibleFieldNameByColumnIndex;
    }

    private boolean couldBeField(Rule rule, List<Integer> values) {
        for (int value: values) {
            if (!rule.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    public long getDepartureFieldProduct(Ticket myTicket) {
        long product = 1;
        for (Rule rule: rules) {
            if (rule.fieldName.contains("departure")) {
                product *= myTicket.values.get(columnIndexByFieldName.get(rule.fieldName));
            }
        }
        return product;
    }


    public void printRules() {
        for (Rule rule: rules) {
            rule.printRule();
        }
    }
}

class Rule {
    String fieldName;
    int range1Start;
    int range1End;
    int range2Start;
    int range2End;

    public Rule(String s, int rs1, int re1, int rs2, int re2) {
        fieldName = s;
        range1Start = rs1;
        range1End = re1;
        range2Start = rs2;
        range2End = re2;
    }

    public void printRule() {
        System.out.println(fieldName + ": " + range1Start + "-" + range1End + ", " + range2Start + "-" + range2End);
    }

    public boolean isValid(int value) {
        return (value <= range1End && value >= range1Start) || (value <= range2End && value >= range2Start);
    }
}
