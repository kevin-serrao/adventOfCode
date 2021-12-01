import java.util.*; 



public class Day4 {
    public static void main(String[] args) {
        String[] fileContents = FileReader.processFile();
        int count = 0;
        String passportString = "";
        for (int i = 0; i < fileContents.length; i++) {
            String line = fileContents[i];
            if (line.trim().length() == 0) {
                Passport passport = new Passport(passportString);
                if (passport.isValidPassport()) {
                    count++;
                }
                passportString = "";
            } else {
                passportString += " " + line;
            }
        }
        Passport passport = new Passport(passportString);
            if (passport.isValidPassport()) {
                count++;
            }
        System.out.println(count);
    }
}

