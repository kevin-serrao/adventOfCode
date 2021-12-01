package day25.part1;

import fileReader.FileReader;

public class Day25 {
    static int SUBJECT_NUMBER = 7;
    static int DIVISOR = 20201227;
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        long doorKey = Long.parseLong(fileContents[0]);
        long cardKey = Long.parseLong(fileContents[1]);
        long doorLoopSize = getLoopSize(doorKey, SUBJECT_NUMBER);
        System.out.println(doorLoopSize);
        long cardLoopSize = getLoopSize(cardKey, SUBJECT_NUMBER);
        System.out.println(cardLoopSize);
        long encryptionKey = encrypt(cardLoopSize, doorKey);
        System.out.println(encryptionKey);
    }

    public static long getLoopSize(long publicKey, long subjectNumber) {
        long testLoopSize = 1;
        long value = 1;
        while (true) {
            value *= subjectNumber;
            value = value % DIVISOR;
            if (value == publicKey) {
                return testLoopSize;
            }
            testLoopSize++;
        }
    }

    public static long encrypt(long loopSize, long subjectNumber) {
        long value = 1;
        for (int i = 0; i < loopSize; i++) {
            value *= subjectNumber;
            value = value % DIVISOR;
        }
        return value;
    }
}
