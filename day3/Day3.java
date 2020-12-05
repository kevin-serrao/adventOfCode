import java.util.*; 
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class Day3 {
  public static void main(String[] args) {
    int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
    HillRow[] hillRows = new HillRow[323];

    try {
      File myObj = new File("input.txt");
      Scanner myReader = new Scanner(myObj);
      int  i = 0;
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        HillRow hillRow = new HillRow(line);
        hillRows[i] = hillRow;
        i++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    int hitCountProduct = 1;
    for (int slopeIndex = 0; slopeIndex < slopes.length; slopeIndex++) {
        int[] slope = slopes[slopeIndex];
        int x = 0;
        int thisSlopesHitCount = 0;
        for (int y = 0; y < 323; y += slope[1]) {
            HillRow hillRow = hillRows[y];
            if (hillRow.isThereATreeRightInThisSpot(x)) {
                thisSlopesHitCount++;
            };
            x += slope[0];
        }
        hitCountProduct *= thisSlopesHitCount;
    }
    System.out.println(hitCountProduct);
}
}

