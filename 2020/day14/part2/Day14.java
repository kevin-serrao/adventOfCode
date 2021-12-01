package day14.part2;

import fileReader.FileReader;

public class Day14 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Initializer initializer = new Initializer();
        for (int i = 0; i < fileContents.length; i++) {
            initializer.processInstruction(fileContents[i]);
        }
        System.out.println(initializer.getTotal());
    }
}
