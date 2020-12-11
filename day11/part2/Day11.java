package day11.part2;

import fileReader.FileReader;

public class Day11 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        SeatLayout seatLayout = new SeatLayout(fileContents);
        while (!seatLayout.isStable) {
            seatLayout.takeStep();
        }
        
    }    
}
