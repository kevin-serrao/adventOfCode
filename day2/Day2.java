import java.util.*; 
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class Day2 {
  public static void main(String[] args) {
    Policy[] policies = new Policy[1000];
    System.out.println(policies[0]);
    String[] passwords = new String[1000];
    try {
      File myObj = new File("input.txt");
      Scanner myReader = new Scanner(myObj);
      int i = 0; 
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        policies[i] = new Policy(line.split(":")[0]);
        passwords[i] = line.split(":")[1].trim();
        i++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    int numberOfLegalPasswords = 0;
    for (int i = 0; i < 1000; i++) {
        Policy policy = policies[i];
        String password = passwords[i];
        if (policy.isLegalPassword(password)) {
            numberOfLegalPasswords++;
        }
    }
    System.out.println(numberOfLegalPasswords);
}
}

