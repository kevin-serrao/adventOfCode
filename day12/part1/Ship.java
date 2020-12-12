package day12.part1;



public class Ship {
    int x;
    int y;
    CardinalDirection direction;
    static CardinalDirection[] cardinalDirections = {
         CardinalDirection.North,
         CardinalDirection.East,
         CardinalDirection.South,
         CardinalDirection.West
        };
    public Ship() {
        x = 0;
        y = 0;
        direction = CardinalDirection.East;
    }
    public void processInstruction(Instruction instruction) {
        if (instruction.code.equals("L") || instruction.code.equals("R")) {
            turnShip(instruction);
        } else {
            if (instruction.code.equals("N")) {
                moveShip(CardinalDirection.North, instruction.magnitude);
            } else if (instruction.code.equals("S")) {
                moveShip(CardinalDirection.South, instruction.magnitude);
            } else if (instruction.code.equals("E")) {
                moveShip(CardinalDirection.East, instruction.magnitude);
            } else if (instruction.code.equals("W")) {
                moveShip(CardinalDirection.West, instruction.magnitude);
            } else {
                moveShip(direction, instruction.magnitude);
            }
        }
    }
    
    private void moveShip(CardinalDirection direction, int magnitude) {
        if (direction == CardinalDirection.North) {
            y += magnitude;
        }
        if (direction == CardinalDirection.South) {
            y -= magnitude;
        }
        if (direction == CardinalDirection.West) {
            x -= magnitude;
        }
        if (direction == CardinalDirection.East) {
            x += magnitude;
        }
    }

    private void turnShip(Instruction instruction) {
        if (instruction.code.equals("L")) {
            turnShipLeft(instruction.magnitude);
        } else {
            turnShipRight(instruction.magnitude);
        }
    }

    private void turnShipLeft(int magnitude) {
        int trueMagnitude = magnitude / 90;
        int currIndexInCardinalDirectionArray = 0;
        for (int i = 0; i < cardinalDirections.length; i++) {
            if (cardinalDirections[i] == direction) {
                currIndexInCardinalDirectionArray = i;
            }
        }
        currIndexInCardinalDirectionArray -= trueMagnitude;
        while (currIndexInCardinalDirectionArray < 0) {
            currIndexInCardinalDirectionArray += 4;
        }
        direction = cardinalDirections[currIndexInCardinalDirectionArray];
    }

    private void turnShipRight(int magnitude) {
        int trueMagnitude = magnitude / 90;
        int currIndexInCardinalDirectionArray = 0;
        for (int i = 0; i < cardinalDirections.length; i++) {
            if (cardinalDirections[i] == direction) {
                currIndexInCardinalDirectionArray = i;
            }
        }
        currIndexInCardinalDirectionArray += trueMagnitude;
        direction = cardinalDirections[currIndexInCardinalDirectionArray % 4];
    }
}


enum CardinalDirection {
    North,
    South,
    East,
    West
}