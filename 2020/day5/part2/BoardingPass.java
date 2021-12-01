package day5.part2;

public class BoardingPass {
    int seatRow;
    int seatColumn;

//  "byr:1985 eyr:2021 iyr:2011 hgt:175cm pid:163069444 hcl:#18171d"
 public BoardingPass(String boardingPassString) {
    String rowString = boardingPassString.substring(0, 7);
    seatRow = getSeatRowFromRowString(rowString);
    String columnString = boardingPassString.substring(7);
    seatColumn = getSeatColumnFromColumnString(columnString);

  }

    // FBFBFBF
    // 0 + 32 + 0 + 8 + 0 + 2 + 0
    // FFFFFFF
    // 0 + 0 + 0 + 0 + 0 + 0 + 0
    // BBBBBBB
    // 64 + 32 + 16 + 8 + 4 + 2 + 1
  private int getSeatRowFromRowString(String rowString) {
      int row = 0;
      for (int i = 0; i < 7; i++) {
          int coordinateMagnitude = (int) Math.pow(2, 6 - i);
          boolean isInFirstHalf = rowString.substring(i, i + 1).equals("F");
          if (!isInFirstHalf) {
              row += coordinateMagnitude;
          }
      }
      return row;
  }

  private int getSeatColumnFromColumnString(String columnString) {
    int column = 0;
    for (int i = 0; i < 3; i++) {
        int coordinateMagnitude = (int) Math.pow(2, 2 - i);
        boolean isOnLeft = columnString.substring(i, i + 1).equals("L");
        if (!isOnLeft) {
            column += coordinateMagnitude;
        }
    }
    return column;
  }

  public int getSeatId() {
      return (seatRow * 8) + seatColumn;
  }
}