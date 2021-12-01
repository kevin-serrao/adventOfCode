package day6.part2;

import java.util.*;

public class GroupAnswers {
    List<String> answers;

    public GroupAnswers () {
        answers = new ArrayList<String>();
    }

    public void addAnswerToAnswers(String answer) {
        answers.add(answer);
    }

    public int getTotalNumberOfUniqueValues() {
        Set<String> valueSet = new HashSet<String>();
        for (String answer: answers) {
            List<String> valueListForAnswer = getValuesInAnswer(answer);
            for (String value: valueListForAnswer) {
                valueSet.add(value);
            }
        }
        return valueSet.size();
    }

    public int getTotalNumberOfSharedValues() {
        List<HashSet<String>> valueSets = new ArrayList<HashSet<String>> ();
        for (String answer: answers) {
            HashSet<String> valueSetForAnswer = new HashSet<String>(getValuesInAnswer(answer));
            valueSets.add(valueSetForAnswer);
        }
        int count = 0;
        String answer = answers.get(0);
        for (int i = 0; i < answer.length(); i++) {
            String c = answer.substring(i, i + 1);
            boolean isShared = true;
            for (HashSet<String> valueSet: valueSets) {
                if (!valueSet.contains(c)) {
                    isShared = false;
                }
            }
            if (isShared) {
                count++;
            }
        }
        return count;
    }

    private List<String> getValuesInAnswer(String answer) {
        List<String> valueList = new ArrayList<String>();
        for (int i = 0; i < answer.length(); i++) {
            String c = answer.substring(i, i + 1);
            valueList.add(c);
        }
        return valueList;
    }
}
