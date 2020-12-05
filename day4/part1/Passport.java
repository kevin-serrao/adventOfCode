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
    //  System.out.println(passportString);
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
        }
        if (key.equals("iyr")) {
            issueYear = value;
        }
        if (key.equals("eyr")) {
            expirationYear = value;
        }
        if (key.equals("hgt")) {
            height = value;
        }
        if (key.equals("hcl")) {
            hairColor = value;
        }
        if (key.equals("ecl")) {
            eyeColor = value;
        }
        if (key.equals("pid")) {
            passportID = value;
        }
        if (key.equals("cid")) {
            countryID = value;
        }
    }
  }
public Boolean isValidPassport() {
    return (birthYear != null 
    && issueYear != null
    && expirationYear != null 
    && height != null 
    && hairColor != null 
    && eyeColor != null 
    && passportID != null);
}

}