package day8.part2;

import java.util.*;

import fileReader.*;

public class Day8 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        Game game = new Game();
        for (int i = 0; i < fileContents.length; i++) {
            game.addInstructionFromString(fileContents[i]);
        }
        game.playUntilDone();
        Set<Integer> instructionsToLookAt = game.instructionPointerSet;
        for (Iterator<Integer> it = instructionsToLookAt.iterator(); it.hasNext(); ) {
            int instructionPointer = it.next();
            Game newGame = new Game();
            for (int i = 0; i < fileContents.length; i++) {
                newGame.addInstructionFromString(fileContents[i]);
            }
            Instruction instruction = newGame.instructions.get(instructionPointer);
            if (instruction.operation == Operation.Jump) {
                instruction.operation = Operation.NoOp;
            }
            else if (instruction.operation == Operation.NoOp) {
                instruction.operation = Operation.Jump;
            }
            newGame.playUntilDone();
            if (newGame.instructionPointer == newGame.instructions.size()) {
                System.out.println("GOT IT!");
                System.out.println(newGame.acc);
            }
        }
    }
}