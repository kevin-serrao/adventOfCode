package day19.part2;

import java.util.*;

public class Rules {
    Hashtable<Integer, RuleSpec> rules;
    public Rules() {
        rules = new Hashtable<Integer, RuleSpec>();
    }

    public void addRule(String ruleString) {
        int ruleId = Integer.parseInt(ruleString.substring(0, ruleString.indexOf(":")));
        RuleSpec ruleSpec = processRuleSpec(ruleString.substring(ruleString.indexOf(":") + 2), ruleId);
        rules.put(ruleId, ruleSpec);
    }

    private RuleSpec processRuleSpec(String ruleSpecString, int ruleId) {
        if (ruleSpecString.substring(0, 1).equals("\"")) {
            return new RuleSpec(null, ruleSpecString.substring(1, 2), ruleId);
        } else {
            List<List<Integer>> childRuleIds = getChildRuleIds(ruleSpecString);
            return new RuleSpec(childRuleIds, null, ruleId);
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
    int id;
    List<List<Integer>> childRuleIds;
    String literal;
    Set<String> matchingInputs;
    Set<String> impossibleInputs;

    public RuleSpec(List<List<Integer>> inputChildRuleIds, String inputLiteral, int inputId) {
        childRuleIds = inputChildRuleIds;
        literal = inputLiteral;
        matchingInputs = new HashSet<String>();
        impossibleInputs = new HashSet<String>();
        id = inputId;
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
        if (id == 11) {
            return handleSpecialElevenCase(input, rules);
        }
        if (id == 8) {
            return handleSpecialEightCase(input, rules);
        }
        if (literal != null) {
            return input.equals(literal);
        }
        for (int i = 0; i < input.length(); i++) {
            for (List<Integer> childRuleIdPair : childRuleIds ) {
                if (childRuleIdPair.size() == 1) {
                    if (rules.get(childRuleIdPair.get(0)).isValid(input, rules)) {
                        matchingInputs.add(input);
                        return true;
                    }
                    continue;
                }
                RuleSpec firstChildRuleSpec = rules.get(childRuleIdPair.get(0));
                RuleSpec secondChildRuleSpec = rules.get(childRuleIdPair.get(1));
                if (ruleSpecCanBeSplitAtIndex(i, input, firstChildRuleSpec, secondChildRuleSpec, rules)) {
                    matchingInputs.add(input);
                    return true;
                }
            }
        }
        impossibleInputs.add(input);
        return false;
    }

    private boolean handleSpecialElevenCase(String input, Hashtable<Integer, RuleSpec> rules) {
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j < input.length(); j++) {
                RuleSpec firstChildRuleSpec = rules.get(42);
                RuleSpec secondChildRuleSpec = rules.get(31);
                if (firstChildRuleSpec.isValid(input.substring(0, i), rules) && this.isValid(input.substring(i, j), rules) && secondChildRuleSpec.isValid(input.substring(j), rules)) {
                    matchingInputs.add(input);
                    return true;
                }
                if (ruleSpecCanBeSplitAtIndex(i, input, firstChildRuleSpec, secondChildRuleSpec, rules)) {
                    matchingInputs.add(input);
                    return true;
                }
            }
        }
        impossibleInputs.add(input);
        return false;
    }

    private boolean handleSpecialEightCase(String input, Hashtable<Integer, RuleSpec> rules) {
        for (int i = 1; i < input.length(); i++) {
            for (List<Integer> childRuleIdPair : childRuleIds ) {
                if (childRuleIdPair.size() == 1) {
                    if (rules.get(childRuleIdPair.get(0)).isValid(input, rules)) {
                        matchingInputs.add(input);
                        return true;
                    }
                    continue;
                }
                RuleSpec firstChildRuleSpec = rules.get(childRuleIdPair.get(0));
                RuleSpec secondChildRuleSpec = rules.get(childRuleIdPair.get(1));
                if (ruleSpecCanBeSplitAtIndex(i, input, firstChildRuleSpec, secondChildRuleSpec, rules)) {
                    matchingInputs.add(input);
                    return true;
                }
            }
        }
        impossibleInputs.add(input);
        return false;
    }

    private static boolean ruleSpecCanBeSplitAtIndex(int index, String input, RuleSpec firstChildRuleSpec, RuleSpec secondChildRuleSpec, Hashtable<Integer, RuleSpec> rules) {
        boolean a = firstChildRuleSpec.isValid(input.substring(0, index), rules);
        boolean b = secondChildRuleSpec.isValid(input.substring(index), rules);
        return a && b;
    }
}