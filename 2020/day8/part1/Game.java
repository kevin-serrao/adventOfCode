package day8.part1;

import java.util.*;


public class Game {
    List<Instruction> instructions;
    int acc;
    int instructionPointer;

    public Game() {
        instructions = new ArrayList<Instruction>();
        acc = 0;
        instructionPointer = 0;
    }
    public void addInstructionFromString(String instructionString) {
        String operationString = instructionString.split(" ")[0];
        String amountString = instructionString.split(" ")[1];
        int amount = Integer.parseInt(amountString);
        Instruction instruction = new Instruction(operationString, amount);
        instructions.add(instruction);
    }

    public void executeInstruction() {
        Instruction instruction = instructions.get(instructionPointer);
        if (instruction.operation == Operation.Acc) {
            acc += instruction.amount;
            instructionPointer += 1;
        } else if (instruction.operation == Operation.Jump) {
            instructionPointer += instruction.amount;
        } else {
            instructionPointer += 1;
        }
    }
}

enum Operation {
    Acc,
    Jump,
    NoOp,
}

class Instruction {
    // jmp, acc, nop
    Operation operation;
    int amount;
    public Instruction(String operationString, int inputAmount) {
        if (operationString.equals("jmp")) {
            operation = Operation.Jump;
        }else if (operationString.equals("acc")) {
            operation = Operation.Acc;
        } else if (operationString.equals("nop")) {
            operation = Operation.NoOp;
        } else {
            throw new Error("Invalid operation string");
        }
        amount = inputAmount;
    }
}
