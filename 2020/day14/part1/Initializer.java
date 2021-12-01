package day14.part1;

import java.util.*;

public class Initializer {
    Hashtable<Long, Long> memoryTable;
    String[] mask;

    public Initializer() {
        mask = new String[36];
        memoryTable = new Hashtable<Long, Long>();
    }

    public void processInstruction(String instructionString) {
        if (isMaskInstruction(instructionString)) {
            updateMask(instructionString.substring(7));
        } else {
            processMemoryInstruction(instructionString);
        }
    }

    private boolean isMaskInstruction(String instructionString) {
        return instructionString.substring(0, 4).equals("mask");
    }

    private void updateMask(String maskString) {
        for (int i = 0; i < 36; i++) {
            mask[35 - i] = maskString.substring(i, i + 1);
        }
        // System.out.println(Arrays.toString(mask));
    }

    private void processMemoryInstruction(String instructionString) {
        long location = Long.parseLong(instructionString.substring(4, instructionString.indexOf("]")));
        long value = Long.parseLong(instructionString.substring(instructionString.indexOf("=") + 2));
        value = applyMask(mask, value);
        memoryTable.put(location, value);
    }

    private long applyMask(String[] mask, long value) {
        System.out.println("before mask: " + value);
        for (int i = 0; i < 36; i++) {
            if (mask[i].equals("1")) {
                value = addBitAtIndex(i, value);
            } if (mask[i].equals("0")) {
                value = removeBitAtIndex(i, value);
            }
        }
        System.out.println("after mask: " + value);
        return value;
    }

    private long addBitAtIndex(int index, long value) {
        long adder = 1;
        long temp = value;
        for (int i = 0; i < index; i++) {
            temp = temp / 2;
            adder *= 2;
        }
        if (temp % 2 == 1) {
            return value;
        }
        return value + adder;
    }

    private long removeBitAtIndex(int index, long value) {
        long subtractor = 1;
        long temp = value;
        for (int i = 0; i < index; i++) {
            temp = temp / 2;
            subtractor *= 2;
        }
        if (temp % 2 == 0) {
            return value;
        }
        return value - subtractor;
    }

    public long getTotal() {
        long sum = 0;
        Enumeration<Long> keys = memoryTable.keys();
        while (keys.hasMoreElements()) {
            long key = keys.nextElement();
            long value = memoryTable.get(key);
            sum += value;
        }
        return sum;
    }
}
