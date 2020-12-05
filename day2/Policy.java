public class Policy {
 int pos1;
 int pos2;
 String character;

 public Policy(String policySubstr) {
    String posSubStr = policySubstr.split(" ")[0];
    String newCharacter = policySubstr.split(" ")[1].trim();
    String pos1Str = posSubStr.split("-")[0].trim();
    int newPos1 = Integer.parseInt(pos1Str);
    String pos2Str = posSubStr.split("-")[1].trim();
    int newPos2 = Integer.parseInt(pos2Str);
    pos1 = newPos1 - 1;
    pos2 = newPos2 - 1;
    character = newCharacter;
 }   
 public boolean isLegalPassword(String password) {
    boolean isAtPos1 = password.charAt(pos1) == character.charAt(0);
    
    boolean isAtPos2 = password.charAt(pos2) == character.charAt(0);
    if ((isAtPos1 || isAtPos2)  && !(isAtPos1 && isAtPos2)) {
        return true;
    }
     return false;
 }

}