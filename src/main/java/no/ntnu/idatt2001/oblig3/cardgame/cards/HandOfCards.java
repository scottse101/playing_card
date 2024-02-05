package no.ntnu.idatt2001.oblig3.cardgame.cards;

import java.util.List;

public class HandOfCards {
  private static List<PlayingCard> hand;

  public HandOfCards(List<PlayingCard> hand) {
    if (hand == null) {
      throw new IllegalArgumentException("Card on hands can not be null!");
    }
    if (hand.isEmpty()) {
      throw new IllegalArgumentException("Card on hands can not be empty!");
    }
    this.hand = hand;

    for (PlayingCard card : hand) {
       char suit = card.getSuit();
       int face = card.getFace();
       boolean pictureCard = false;
       String suitString = "";
       String faceString = "";
      if (suit == 'S') {
        suitString = "spades";
      } else if (suit == 'H') {
        suitString = "hearts";
      } else if (suit == 'D') {
        suitString = "diamonds";
      } else if (suit == 'C') {
        suitString = "clubs";
      }
      card.setImageLink("src/main/resources/" + suitString + "/" + suit + face + ".png");
    }
  }

  private int rankToValue(char rankChar) {
    switch (rankChar) {
      case 'A':
        return 1;
      case 'K':
        return 13;
      case 'Q':
        return 12;
      case 'J':
        return 11;
      case 'T':
        return 10;
      default:
        return Character.getNumericValue(rankChar);
    }
  }


  public List<PlayingCard> getHand() {
    return hand;
  }

  public boolean isHandFlush() {
    return hand.stream()
            .map(PlayingCard::getSuit)
            .distinct()
            .toList().size() == 1;
  }

  public boolean isQueenOfSpadesOnHand() {
    return hand.stream()
            .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  public static int sumOfCardsOnHand() {
    return hand.stream()
            .map(PlayingCard::getFace)
            .reduce(0, Integer::sum);
  }

  public String getAllHeartsCards() {
    List<PlayingCard> hearts = hand.stream()
            .filter(card -> card.getSuit() == 'H')
            .toList();
    return hearts.isEmpty() ? "No hearts" : hearts.stream()
            .map(card -> card.getAsString() + " ")
            .reduce("", String::concat);
  }
}
