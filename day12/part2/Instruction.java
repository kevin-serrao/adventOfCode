package day12.part2;

public class Instruction {
    String code;
    int magnitude;
    public Instruction(String instructionString) {
        code = instructionString.substring(0, 1);
        String magnitudeString = instructionString.substring(1);
        magnitude = Integer.parseInt(magnitudeString);
    }
}
