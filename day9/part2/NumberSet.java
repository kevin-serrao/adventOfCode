package day9.part2;

import java.util.*;

public class NumberSet {
    List<Integer> numbers;

    public NumberSet() {
        numbers = new ArrayList<Integer>();
    }

    public void addNumberToNumberSet(String numberString) {
        numbers.add(Integer.parseInt(numberString));
    }

    public void cutFirstNumberOut() {
        numbers = numbers.subList(1, 25);
    }

    public boolean isLegalNextNumber(int number) {
        List<Integer> sums = getSums();
        if (sums.contains(number)) {
            return true;
        }
        return false;
    }

    public List<Integer> getSums() {
        List<Integer> sums = new ArrayList<Integer>();
        for (int numberA: numbers) {
            for (int numberB: numbers) {
                if (numberA != numberB && !sums.contains(numberA + numberB)) {
                    sums.add(numberA + numberB);
                }
            }   
        }
        return sums;
    }
}
