package day5.part2;
import fileReader.*;
public class Day5 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        boolean[] isSeatIdHere = new boolean[843];
        for (int i = 0; i < fileContents.length; i++) {
            String line = fileContents[i];
            BoardingPass boardingPass = new BoardingPass(line);
            int seatId = boardingPass.getSeatId();
            isSeatIdHere[seatId] = true;
        }
        boolean hasSeenFirstSeat = false;
        for (int i = 0; i < 843; i ++) {
            if (hasSeenFirstSeat && !isSeatIdHere[i]) {
                System.out.println(i);
            }
            if (isSeatIdHere[i]) {
                hasSeenFirstSeat = true;
            }
        }
    }
}