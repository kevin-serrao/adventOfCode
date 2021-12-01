package day16.part1;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    List<Rule> rules;

    public Rules() {
        rules = new ArrayList<Rule>();
    }

    public void addRule(String ruleString) {
        int colonIndex = ruleString.indexOf(":");
        String fieldName = ruleString.substring(0, colonIndex);
        int firstDashIndex = ruleString.indexOf("-");
        int firstRangeStart = Integer.parseInt(ruleString.substring(colonIndex + 1, firstDashIndex).trim());
        int orIndex = ruleString.indexOf("or", colonIndex);
        int firstRangeEnd = Integer.parseInt(ruleString.substring(firstDashIndex + 1, orIndex).trim());
        rules.add(new Rule(fieldName, firstRangeStart, firstRangeEnd));
        int secondDashIndex = ruleString.indexOf("-", firstDashIndex + 1);
        int secondRangeStart = Integer.parseInt(ruleString.substring(orIndex + 2, secondDashIndex).trim());
        int secondRangeEnd = Integer.parseInt(ruleString.substring(secondDashIndex + 1).trim());
        rules.add(new Rule(fieldName, secondRangeStart, secondRangeEnd));
    }

    public boolean isInvalidValue(int value) {
        for (Rule rule: rules) {
            if (value <= rule.rangeEnd && value >= rule.rangeStart) {
                return false;
            }
        }
        return true;
    }


    public void printRules() {
        for (Rule rule: rules) {
            rule.printRule();
        }
    }
}

class Rule {
    String fieldName;
    int rangeStart;
    int rangeEnd;

    public Rule(String s, int rs, int re) {
        fieldName = s;
        rangeStart = rs;
        rangeEnd = re;
    }

    public void printRule() {
        System.out.println(fieldName + ": " + rangeStart + "-" + rangeEnd);
    }
}
