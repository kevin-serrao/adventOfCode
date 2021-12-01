package day22.part2;

import fileReader.FileReader;

public class Day22 {
    public static void main(String[] args) {
        Game game = new Game();
        String[] fileContents = FileReader.processFile();
        int i = 1;
        for (; !fileContents[i].equals(""); i++) {
            game.addCardToPlayer1sHand(Integer.parseInt(fileContents[i]));
        }
        i += 2;
        for (; i < fileContents.length; i++) {
            game.addCardToPlayer2sHand(Integer.parseInt(fileContents[i]));
        }
        while (game.isNotOver()) {
            System.out.println(game.player1Hand);
            System.out.println(game.player2Hand);
            game.step();
        }
        System.out.println(game.winningScore());
    }   
}
