package day22.part2;

import java.util.*;

public class Game {
    Queue<Integer> player1Hand;
    Queue<Integer> player2Hand;
    boolean player1Won;
    Set<Game> playedGames;

    public Game() {
        player1Hand = new LinkedList<Integer>();
        player2Hand = new LinkedList<Integer>();
        player1Won = false;
        playedGames = new HashSet<Game>();
    }

    public int hashCode() {
        Queue<Integer> player1HandCopy = new LinkedList<Integer>(player1Hand);
        Queue<Integer> player2HandCopy = new LinkedList<Integer>(player2Hand);
        String codeString = "player1: ";
        while (player1HandCopy.size() > 0) {
            codeString += player1HandCopy.poll() +", ";
        }
        codeString += "player2: ";
        while (player2HandCopy.size() > 0) {
            codeString += player2HandCopy.poll() + ", ";
        }
        System.out.println(codeString);
        return codeString.hashCode();
    }

    public boolean equals(Game game) {
        return player1Hand.equals(game.player1Hand) && player2Hand.equals(game.player2Hand);
    }

    public void addCardToPlayer1sHand(int card) {
        player1Hand.add(card);
    }

    public void addCardToPlayer2sHand(int card) {
        player2Hand.add(card);
    }

    public boolean isNotOver() {
        if (player1Hand.size() == 0) {
            return false;
        } else if (player2Hand.size() == 0) {
            player1Won = true;
            return false;
        }
        return true;
    }

    public void step() {
        if (playedGames.contains(this)) {
            player2Hand.clear();
            return;
        }
        playedGames.add(this);

        int player1Card = player1Hand.poll();
        int player2Card = player2Hand.poll();
        if (shouldRecurse(player1Card, player2Card)) {
            Queue<Integer> newPlayer1Hand = getNewHand(player1Hand, player1Card);
            Queue<Integer> newPlayer2Hand = getNewHand(player2Hand, player2Card);
            Game game = new Game();
            game.player1Hand = newPlayer1Hand;
            game.player2Hand = newPlayer2Hand;
            while (game.isNotOver()) {
                game.step();
            }
            if (game.player1Won) {
                player1Hand.add(player1Card);
                player1Hand.add(player2Card);
            } else {
                player2Hand.add(player2Card);
                player2Hand.add(player1Card);
            }
        } else {
            if (player1Card > player2Card) {
                player1Hand.add(player1Card);
                player1Hand.add(player2Card);
            } else {
                player2Hand.add(player2Card);
                player2Hand.add(player1Card);
            }
        }
    }

    private boolean shouldRecurse(int player1Card, int player2Card) {
        return player1Card <= player1Hand.size() && player2Card <= player2Hand.size();
    }



    private Queue<Integer> getNewHand(Queue<Integer> hand, int size) {
        Queue<Integer> handCopy = new LinkedList<Integer>(hand);
        Queue<Integer> newHand = new LinkedList<Integer>();
        while (newHand.size() < size) {
            int val = handCopy.poll();
            newHand.add(val);
        }
        return newHand;
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
