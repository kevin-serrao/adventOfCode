// package day5.part1;

// import java.io.File;  // Import the File class
// import java.io.FileNotFoundException;  // Import this class to handle errors
// import java.util.Scanner; // Import the Scanner class to read text files

// public class FileReader {
//     public static String[] processFile() {
//         try {
//             File myObj = new File("input.txt");
//             Scanner myReader = new Scanner(myObj);
//             int  i = 0;
//             while (myReader.hasNextLine()) {
//               i++;
//               myReader.nextLine();
//             }
//             myReader.close();
//             String[] ret = new String[i];
//             int j = 0;
//             Scanner myReader2 = new Scanner(myObj);
//             while (myReader2.hasNextLine()) {
//                 String line = myReader2.nextLine();
//                 ret[j] = line;
//                 j++;
//               }
//             myReader2.close();
//             return ret;
//           } catch (FileNotFoundException e) {
//             System.out.println("An error occurred.");
//             e.printStackTrace();
//           }
//           return new String[1];
//     }
// }
