package day18.part1;

import java.util.*;

import fileReader.FileReader;

public class Day18 {
    public static void main(String[] args) {
        List<String> fileContents = FileReader.processFileAsList();
        long total = 0;
        for (String line: fileContents) {
            System.out.println(line);
            System.out.println(evaluateExpression(line));
            total += evaluateExpression(line);
        }
        System.out.println(total);
    }

    public static long evaluateExpression(String expression) {
        int pointer = 0;
        long total;
        if (expression.substring(0, 1).equals("(")) {
            int closingParenIndex = getClosingParenIndex(pointer, expression);
            total = evaluateExpression(expression.substring(pointer + 1, closingParenIndex));
            pointer = closingParenIndex + 2;
        } else {
            total = Integer.parseInt(expression.substring(0, 1));
            pointer += 2;
        }
        String operation = "+";
        while (pointer < expression.length()) {
            if (expression.substring(pointer, pointer + 1).equals("(")) {
                int closingParenIndex = getClosingParenIndex(pointer, expression);
                long expressionValue = evaluateExpression(expression.substring(pointer + 1, closingParenIndex));
                if (operation.equals("+")) {
                    total += expressionValue;
                } else {
                    total *= expressionValue;
                }
                pointer = closingParenIndex + 2;
            } else if (expression.substring(pointer, pointer + 1).equals("*")) {
                operation = "*";
                pointer += 2;
            } else if (expression.substring(pointer, pointer + 1).equals("+")) {
                operation = "+";
                pointer += 2;
            }
            else {
                int value = Integer.parseInt(expression.substring(pointer, pointer + 1));
                if (operation.equals("+")) {
                    total += value;
                } else {
                    total *= value;
                }
                pointer += 2;
            }
        }
        return total;
    }

    public static int getClosingParenIndex(int pointer, String expression) {
        int balance = 1;
        int closingParenIndex = pointer;
        while (balance > 0) {
            closingParenIndex++;
            if (expression.substring(closingParenIndex, closingParenIndex + 1).equals("(")) {
                balance++;
            } if (expression.substring(closingParenIndex, closingParenIndex + 1).equals(")")) {
                balance--;
            }
        }
        return closingParenIndex;
    }
}
