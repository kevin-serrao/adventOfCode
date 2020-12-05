public class HillRow {
 public int[] isTreeList = new int[31];

 public HillRow(String rowString) {
    for (int i = 0; i < 31; i++) {
        if (rowString.charAt(i) == "#".charAt(0)) {
            isTreeList[i] = 1;
        } else {
            isTreeList[i] = 0;
        }
    }
}
public Boolean isThereATreeRightInThisSpot(int spot) {
    return isTreeList[spot % 31] == 1;
}

}