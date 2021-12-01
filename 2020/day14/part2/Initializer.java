package day14.part2;

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
    }

    private void processMemoryInstruction(String instructionString) {
        long initialLocation = Long.parseLong(instructionString.substring(4, instructionString.indexOf("]")));
        long value = Long.parseLong(instructionString.substring(instructionString.indexOf("=") + 2));
        List<Long> locations = applyMask(mask, initialLocation);
        for (long location: locations) {
            System.out.println(location);
            memoryTable.put(location, value);
        }
        System.out.println();
    }

    private List<Long> applyMask(String[] mask, long location) {
        List<Long> locations = new ArrayList<Long>();
        locations.add(location);
        for (int i = 0; i < 36; i++) {
            if (mask[i].equals("1")) {
                locations = addBitsAtIndex(i, locations);
            }
            if (mask[i].equals("X")) {
                locations = addLocationVariantsAtIndex(i, locations);
            }
        }
        return locations;
    }

    private List<Long> addBitsAtIndex(int i, List<Long> locations) {
        List<Long> newLocations = new ArrayList<Long>();
        for (long location: locations) {
            long newLocation = addBitAtIndex(i, location);
            newLocations.add(newLocation);
        }
        return newLocations;
    }

    private List<Long> addLocationVariantsAtIndex(int i, List<Long> locations) {
        List<Long> newLocations = new ArrayList<Long>();
        for (Long location: locations) {

            long newLocation = addBitAtIndex(i, location);
            newLocations.add(newLocation);

            long newLocationB = removeBitAtIndex(i, location);
            newLocations.add(newLocationB);
        }
        return newLocations;
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
