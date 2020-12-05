import java.util.*; 
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Day1 {
  public static void main(String[] args) {
    int[] expenses = new int[200];a
    try {
      File myObj = new File("input.txt");
      Scanner myReader = new Scanner(myObj);
      int i = 0; 
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        expenses[i] = Integer.parseInt(line.trim());
        i++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    Set<Integer> expenseSet = new HashSet<Integer>();
    for (int i = 0; i < 200; i++) {
        expenseSet.add(expenses[i]);
    }
    for (int i = 0; i < 200; i++) {
        int expense = expenses[i];
        int difference = 2020 - expense;
        if (expenseSet.contains(difference)) {
            System.out.println(difference * expense);
        }
    }
}
}