import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Passport {
 public String birthYear;
 public String issueYear;
 public String expirationYear;
 public String height;
 public String hairColor;
 public String eyeColor;
 public String passportID;
 public String countryID;

//  "byr:1985 eyr:2021 iyr:2011 hgt:175cm pid:163069444 hcl:#18171d"
 public Passport(String passportString) {
    String[] passPortKeyValueArray = passportString.split(" ");
    for (int i = 0; i < passPortKeyValueArray.length; i++) {
        String keyValueString = passPortKeyValueArray[i];
        if (keyValueString.trim().length() == 0) {
            continue;
        }
        String key = keyValueString.split(":")[0].trim();
        String value = keyValueString.split(":")[1].trim();
        if (key.equals("byr")) {
            birthYear = value;
            System.out.println(keyValueString);
            System.out.println(birthYearIsValid());
        }
        if (key.equals("iyr")) {
            issueYear = value;
            System.out.println(keyValueString);
            System.out.println(issueYearIsValid());
        }
        if (key.equals("eyr")) {
            expirationYear = value;
            System.out.println(keyValueString);
            System.out.println(expirationYearIsValid());
        }
        if (key.equals("hgt")) {
            height = value;
            System.out.println(keyValueString);
            System.out.println(heightIsValid());
        }
        if (key.equals("hcl")) {
            hairColor = value;
            System.out.println(keyValueString);
            System.out.println(hairColorIsValid());
        }
        if (key.equals("ecl")) {
            eyeColor = value;
            System.out.println(keyValueString);
            System.out.println(eyeColorIsValid());
        }
        if (key.equals("pid")) {
            passportID = value;
            System.out.println(keyValueString);
            System.out.println(passportIDIsValid());
        }
        if (key.equals("cid")) {
            countryID = value;
        }
    }
    // if (!isValidPassport()) {
    //     System.out.println(passportString);
    //     System.out.println(issueYearIsValid());
    //     System.out.println(expirationYearIsValid());
    //     System.out.println(heightIsValid());
    //     System.out.println(hairColorIsValid());
    //     System.out.println(eyeColorIsValid());
    //     System.out.println(passportIDIsValid());
    // }
  }
public boolean isValidPassport() {
    return birthYearIsValid() 
    && issueYearIsValid() 
    && expirationYearIsValid() 
    && heightIsValid() 
    && hairColorIsValid()
    && eyeColorIsValid()
    && passportIDIsValid();
}

private boolean birthYearIsValid() {
    if (birthYear == null) {
        return false;
    }
    int birthYearInt = Integer.parseInt(birthYear);
    return birthYearInt >= 1920 && birthYearInt <= 2002;
}

private boolean issueYearIsValid() {
    if (issueYear == null) {
        return false;
    }
    int issueYearInt = Integer.parseInt(issueYear);
    return issueYearInt >= 2010 && issueYearInt <= 2020;
}

private boolean expirationYearIsValid() {
    if (expirationYear == null) {
        return false;
    }
    int expirationYearInt = Integer.parseInt(expirationYear);
    return expirationYearInt >= 2020 && expirationYearInt <= 2030;
}

private boolean heightIsValid() {
    if (height == null || height.length() < 3) {
        return false;
    }
    String unit = height.substring(height.length() - 2);
    int value = Integer.parseInt(height.substring(0, height.length() -2));
    if (unit.equals("cm")) {
        return value >= 150 && value <= 193;
    } else if (unit.equals("in")) {
        return value >= 59 && value <= 76;
    }
    return false;
}

private boolean hairColorIsValid() {
    if (hairColor == null) {
        return false;
    }
    Set<String> validDigitSet = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    if (!hairColor.substring(0,1).equals("#")) {
        return false;
    }
    if (hairColor.length() != 7) {
        return false;
    }
    for (int i = 1; i< 7; i++) {
        String digit = hairColor.substring(i, i+1);
        if (!validDigitSet.contains(digit)) {
            return false;
        }
    }
    return true;
}

private boolean eyeColorIsValid() {
    if (eyeColor == null) {
        return false;
    }
    Set<String> validEyeColorCodes = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
    return validEyeColorCodes.contains(eyeColor);
}

private boolean passportIDIsValid() {
    Set<String> validDigitSet = new HashSet<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    if (passportID == null) {
        return false;
    }
    if (passportID.length() != 9) {
        return false;
    }
    for (int i = 0; i < 9; i++) {
        if (!validDigitSet.contains(passportID.substring(i, i + 1))) {
            return false;
        } 
    }
    return true;
}

}