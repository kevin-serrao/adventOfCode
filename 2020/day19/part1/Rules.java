package day19.part1;

import java.util.*;

public class Rules {
    Hashtable<Integer, RuleSpec> rules;
    public Rules() {
        rules = new Hashtable<Integer, RuleSpec>();
    }

    public void addRule(String ruleString) {
        int ruleId = Integer.parseInt(ruleString.substring(0, ruleString.indexOf(":")));
        RuleSpec ruleSpec = processRuleSpec(ruleString.substring(ruleString.indexOf(":") + 2));
        System.out.println(ruleId + ": " + ruleSpec.print());
        rules.put(ruleId, ruleSpec);
    }

    private RuleSpec processRuleSpec(String ruleSpecString) {
        if (ruleSpecString.substring(0, 1).equals("\"")) {
            return new RuleSpec(null, ruleSpecString.substring(1, 2));
        } else {
            List<List<Integer>> childRuleIds = getChildRuleIds(ruleSpecString);
            return new RuleSpec(childRuleIds, null);
        }
    }

    private List<List<Integer>> getChildRuleIds(String ruleSpecString) {
        List<List<Integer>> childRuleIdsList = new ArrayList<List<Integer>>();
        String[] childRuleStrings = ruleSpecString.split("\\|");
        for (int i = 0; i < childRuleStrings.length; i++) {
            ArrayList<Integer> childRuleIds = new ArrayList<Integer>();
            String[] childRuleIdStrings = childRuleStrings[i].trim().split(" ");
            for (int j = 0; j < childRuleIdStrings.length; j++) {
                childRuleIds.add(Integer.parseInt(childRuleIdStrings[j].trim()));
            }
            childRuleIdsList.add(childRuleIds);
        }
        return childRuleIdsList;
    }

    public boolean isValid(String input) {
        RuleSpec startingRuleSpec = rules.get(0);
        return startingRuleSpec.isValid(input, rules);
    }
}


class RuleSpec {
    List<List<Integer>> childRuleIds;
    String literal;
    Set<String> matchingInputs;
    Set<String> impossibleInputs;

    public RuleSpec(List<List<Integer>> inputChildRuleIds, String inputLiteral) {
        childRuleIds = inputChildRuleIds;
        literal = inputLiteral;
        matchingInputs = new HashSet<String>();
        impossibleInputs = new HashSet<String>();
    }

    public String print() {
        if (literal != null) {
            return literal;
        } else {
            return childRuleIds.toString();
        }
    }

    // THIS IS THE REAL SHIT
    public boolean isValid(String input, Hashtable<Integer, RuleSpec> rules) {
        if (matchingInputs.contains(input)) {
            return true;
        }
        if (impossibleInputs.contains(input)) {
            return false;
        }
        // System.out.println(input +", " + this.print());
        if (literal != null) {
            return input.equals(literal);
        }
        for (int i = 0; i < input.length(); i++) {
            for (List<Integer> childRuleIdPair : childRuleIds ) {
                if (childRuleIdPair.size() == 1) {
                    if (rules.get(childRuleIdPair.get(0)).isValid(input, rules)) {
                        return true;
                    }
                    continue;
                }
                RuleSpec firstChildRuleSpec = rules.get(childRuleIdPair.get(0));
                RuleSpec secondChildRuleSpec = rules.get(childRuleIdPair.get(1));
                if (ruleSpecCanBeSplitAtIndex(i, input, firstChildRuleSpec, secondChildRuleSpec, rules)) {
                    // System.out.println("passed");
                    matchingInputs.add(input);
                    return true;
                }
            }
        }
        // System.out.println("did not pass");
        impossibleInputs.add(input);
        return false;
    }

    public static boolean ruleSpecCanBeSplitAtIndex(int index, String input, RuleSpec firstChildRuleSpec, RuleSpec secondChildRuleSpec, Hashtable<Integer, RuleSpec> rules) {
        return firstChildRuleSpec.isValid(input.substring(0, index), rules) && secondChildRuleSpec.isValid(input.substring(index), rules);
    }
}