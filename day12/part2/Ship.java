package day12.part2;



public class Ship {
    int x;
    int y;
    int[] wayPointCoordinates = {10, 1};

    static CardinalDirection[] cardinalDirections = {
         CardinalDirection.North,
         CardinalDirection.East,
         CardinalDirection.South,
         CardinalDirection.West
        };

    public Ship() {
        x = 0;
        y = 0;
    }
    public void processInstruction(Instruction instruction) {
        if (instruction.code.equals("L")) {
            rotateWaypointLeft(instruction.magnitude);
        }
        else if (instruction.code.equals("R")) {
            rotateWaypointRight(instruction.magnitude);
        } else {
            if (instruction.code.equals("N")) {
                wayPointCoordinates[1] += instruction.magnitude;
            } else if (instruction.code.equals("S")) {
                wayPointCoordinates[1] -= instruction.magnitude;
            } else if (instruction.code.equals("E")) {
                wayPointCoordinates[0] += instruction.magnitude;
            } else if (instruction.code.equals("W")) {
                wayPointCoordinates[0] -= instruction.magnitude;
            } else {
                moveShip(instruction.magnitude, wayPointCoordinates);
            }
        }
    }
    
    private void moveShip(int magnitude, int[] wayPointCoordinates) {
        int difX = magnitude * wayPointCoordinates[0];
        int difY = magnitude * wayPointCoordinates[1];
        x += difX;
        y += difY;
    }

    private void rotateWaypointRight(int magnitude) {
        int trueMagnitude = magnitude / 90;
        for (int i = 0; i < trueMagnitude; i++) {
            rotateWaypointRightNinety();
        }
    }

    private void rotateWaypointRightNinety() {
        int tempX = wayPointCoordinates[0];
        int tempY = wayPointCoordinates[1];
        wayPointCoordinates[0] = tempY;
        wayPointCoordinates[1] = -tempX;
    }

    private void rotateWaypointLeft(int magnitude) {
        int trueMagnitude = magnitude / 90;
        for (int i = 0; i < trueMagnitude; i++) {
            rotateWaypointLeftNinety();
        }
    }
    private void rotateWaypointLeftNinety() {
        int tempX = wayPointCoordinates[0];
        int tempY = wayPointCoordinates[1];
        wayPointCoordinates[0] = -tempY;
        wayPointCoordinates[1] = tempX;
    }
}


enum CardinalDirection {
    North,
    South,
    East,
    West
}

// [10, 4]
// R90
// [4, -10]
// R90
// [-10, -4]
// R90
// [-4 ,10]