module com.example.hellofx {
  requires javafx.controls;
  requires javafx.fxml;


  opens no.ntnu.idatt2001.oblig3.cardgame to javafx.fxml;
  exports no.ntnu.idatt2001.oblig3.cardgame;
  exports no.ntnu.idatt2001.oblig3.cardgame.cards;
  opens no.ntnu.idatt2001.oblig3.cardgame.cards to javafx.fxml;
}