package no.ntnu.idatt2001.oblig3.cardgame.cards;

import java.util.*;

public class DeckOfCards {
  private final char[] suits = {'S', 'H', 'D', 'C'};
  private final int[] faces = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
  private static List<PlayingCard> cards;


  //Constructor
  public DeckOfCards(){
    cards = new ArrayList<>();
    for (char suit : suits) {
      for (int face : faces) {
        cards.add(new PlayingCard(suit, face));
      }
    }
  }


  public PlayingCard dealPlayingCard(int i) throws IllegalArgumentException {
    if (cards.isEmpty()) {
      throw new IllegalStateException("The card deck is empty");
    }
    return cards.remove(0);
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public static HandOfCards dealHand(int n) {
    if (n < 1) {
      throw new IllegalArgumentException("The number must 1 or higher!");
    }
    Random random = new Random();
    List<PlayingCard> hand = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int number = random.nextInt(52);
      PlayingCard card = cards.get(number);
      hand.add(card);
    }
    return new HandOfCards(hand);
  }

  public int size() {
    return cards.size();
  }

}
