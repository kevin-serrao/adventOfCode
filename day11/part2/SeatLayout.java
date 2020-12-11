package day11.part2;

public class SeatLayout {
    Spot[][] spots;
    int width;
    int length;
    boolean isStable;
    public SeatLayout(String[] fileContents) {
        isStable = false;
        width = fileContents[0].length();
        length = fileContents.length;
        spots = new Spot[length][width];
        for (int i = 0; i < length; i++) {
            String s = fileContents[i];
            for (int j = 0; j < width; j++) {
                String c = s.substring(j, j + 1);
                if (c.equals(".")) {
                    spots[i][j] = new Spot(i, j, false, false);
                } else {
                    spots[i][j] = new Spot(i, j, true, false);
                }
            }
        }
    }

    public void takeStep() {
        Spot[][] newSpots = new Spot[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                Spot spot = spots[i][j];
                int count = countOccupiedAdjacentSpots(i, j, spots);
                System.out.println(count + ", " + spot.isOccupied);
                if (!spot.isSeat) {
                    newSpots[i][j] = spot;
                } else if (count >= 5 && spot.isOccupied) {
                    newSpots[i][j] = new Spot(i, j, true, false);
                } else if (count == 0 && !spot.isOccupied) {
                    newSpots[i][j] = new Spot(i, j, true, true);
                } else {
                    newSpots[i][j] = spot;
                }
            }
        }
        if (spotsAreUnchanged(newSpots)) {
            isStable = true;
            System.out.println(getNumberOfOccupiedSeats(newSpots));
        }
        spots = newSpots;
    }

    public int getNumberOfOccupiedSeats(Spot[][] newSpots) {
        int total = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                total += newSpots[i][j].isOccupied ? 1 : 0;
            }
        }
        return total;
    }

    private boolean spotsAreUnchanged(Spot[][] newSpots) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (spots[i][j].isOccupied != newSpots[i][j].isOccupied) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countOccupiedAdjacentSpots(int x, int y, Spot[][] spots) {
        int total = 0;
        for (int i = -1; i < 2; i++) {
            for (int j =-1; j < 2; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX < 0 || newX == length || newY < 0 || newY == width || (newX == x && newY == y)) {
                    continue;
                }
                 while (!spots[newX][newY].isSeat) {
                    newX = newX + i;
                    newY = newY + j;
                    if (newX < 0 || newX == length || newY < 0 || newY == width || (newX == x && newY == y)) {
                        break;
                    }
                }
                if (newX < 0 || newX == length || newY < 0 || newY == width || (newX == x && newY == y)) {
                    continue;
                }
                Spot testSpot = spots[newX][newY];
                total += testSpot.isOccupied ? 1 : 0;
            }
        }
        return total;
    }
}

class Spot {
    boolean isSeat;
    boolean isOccupied;
    int x;
    int y;
    public Spot (int inputx, int inputy, boolean inputisSeat, boolean inputIsOccupied) {
        x = inputx;
        y = inputy;
        isSeat = inputisSeat;
        isOccupied = inputIsOccupied;
    }
}