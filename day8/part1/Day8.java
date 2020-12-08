package day8.part1;

import java.util.*;

import fileReader.*;

public class Day8 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Game game = new Game();
        for (int i = 0; i < fileContents.length; i++) {
            game.addInstructionFromString(fileContents[i]);
        }
        Set<Integer> instructionPointerSet = new HashSet<Integer>();
        int instructionPointer = game.instructionPointer;
        while (!instructionPointerSet.contains(instructionPointer)) {
            instructionPointerSet.add(instructionPointer);
            game.executeInstruction();
            instructionPointer = game.instructionPointer;
        }
        System.out.println(game.acc);
    }
}