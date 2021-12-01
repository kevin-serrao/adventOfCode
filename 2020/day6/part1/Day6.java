package day6.part1;

import fileReader.*;

public class Day6 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        int count = 0;
        GroupAnswers groupAnswers = new GroupAnswers();
        for (int i = 0; i < fileContents.length; i++) {
            String line = fileContents[i];
            if (line.trim().length() == 0) {
                count += groupAnswers.getTotalNumberOfUniqueValues();
                groupAnswers = new GroupAnswers();
            } else {
                groupAnswers.addAnswerToAnswers(line);
            }
        }
        count += groupAnswers.getTotalNumberOfUniqueValues();
        System.out.println(count);
    }
}