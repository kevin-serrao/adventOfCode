package day12.part2;

import fileReader.FileReader;

public class Day12 {
    public static void main (String[] args) {
        String[] fileContents = FileReader.processFile();
        Ship ship = new Ship();
        for (int i = 0; i < fileContents.length; i ++) {
            Instruction instruction = new Instruction(fileContents[i]);
            System.out.println(ship.x);
            System.out.println(ship.y); 
            ship.processInstruction(instruction);
        }
        System.out.println(ship.x);
        System.out.println(ship.y);
    }
}
