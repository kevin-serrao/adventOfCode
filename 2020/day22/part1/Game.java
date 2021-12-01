package day22.part1;

import java.util.*;

public class Game {
    Queue<Integer> player1Hand;
    Queue<Integer> player2Hand;
    public Game() {
        player1Hand = new LinkedList<Integer>();
        player2Hand = new LinkedList<Integer>();
    }

    public void addCardToPlayer1sHand(int card) {
        player1Hand.add(card);
    }

    public void addCardToPlayer2sHand(int card) {
        player2Hand.add(card);
    }

    public boolean isNotOver() {
        return player1Hand.size() > 0 && player2Hand.size() > 0;
    }

    public void step() {
        int player1Card = player1Hand.poll();
        int player2Card = player2Hand.poll();
        if (player1Card > player2Card) {
            player1Hand.add(player1Card);
            player1Hand.add(player2Card);
        } else {
            player2Hand.add(player2Card);
            player2Hand.add(player1Card);
        }
    }

    public int winningScore() {
        Queue<Integer> winningHand = getWinningHand();
        int multiplier = winningHand.size();
        int total = 0;
        while (winningHand.size() > 0) {
            total += multiplier * winningHand.poll();
            multiplier--;
        }
        return total;
    }

    private Queue<Integer> getWinningHand() {
        return player1Hand.size() > 0 ? player1Hand : player2Hand;
    }
}
