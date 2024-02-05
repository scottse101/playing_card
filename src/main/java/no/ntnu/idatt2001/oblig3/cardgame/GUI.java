package no.ntnu.idatt2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2001.oblig3.cardgame.cards.DeckOfCards;
import no.ntnu.idatt2001.oblig3.cardgame.cards.HandOfCards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GUI extends Application {
  private final DeckOfCards deck = new DeckOfCards();
  private HandOfCards hand = deck.dealHand(5);
  private HBox displayHand = new HBox();
  private final AnchorPane centerPane = new AnchorPane();
  private final BorderPane main = new BorderPane();
  private final TextField sumOfCardsField = new TextField();
  private final TextField cardsOfHeartsField = new TextField();
  private final TextField queenOfSpadesInHandField = new TextField();
  private final TextField handIsFlushField = new TextField();

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
      Group root = new Group();
      GridPane root1 = new GridPane();
      root1.setHgap(10);
      root1.setVgap(20);


    for (int i = 0; i < 5; i++) {
      StackPane cardPlaceholder = createEmptyCardPlaceholder(100, 200);
      root1.add(cardPlaceholder, i, 0);
    }

    //icon
    Image icon = new Image(new FileInputStream("src/main/resources/spades/S12.PNG"));
    primaryStage.getIcons().add(icon);

    //button style
    String buttonStyle = "-fx-font-size: 16;" +
            "-fx-font-family: 'Times New Roman';" +
            "-fx-background-color: #4f120c; " +
            "-fx-background-radius: 10px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-border-color: #000000;" +
            "-fx-border-radius: 10px";

    //check button layout
    Button checkButton = new Button("Check button");
    checkButton.setStyle(buttonStyle);
    checkButton.setLayoutX(50);
    checkButton.setLayoutY(100);
    root.getChildren().addAll(checkButton);

    //check button action
    checkButton.setOnAction(event -> {
      int sum = hand.sumOfCardsOnHand();
      System.out.println(sum);
      checkHand(); // call checkHand method after dealing new hand
    });

    //deal button layout
    Button dealButton = new Button("Deal button");
    dealButton.setStyle(buttonStyle);
    dealButton.setLayoutX(50);
    dealButton.setLayoutY(50);
    root.getChildren().addAll(dealButton);

    GridPane cardGrid = new GridPane();

    //deal button action
    dealButton.setOnAction(event -> {
      hand = deck.dealHand(5);
      for (int i = 0; i < 5; i++) {
        StackPane cardPlaceholder = null;
        try {
          cardPlaceholder = createCard(hand.getHand().get(i).getImageLink());
          cardGrid.add(cardPlaceholder, i, 0);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    });

    root1.getChildren().add(cardGrid);

    GridPane centerPane = new GridPane();
    centerPane.setAlignment(Pos.CENTER);
    centerPane.setPadding(new Insets(50));
    centerPane.getChildren().add(cardGrid);

    // Set the constraints for the cardGrid
    GridPane.setConstraints(cardGrid, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
    GridPane.setMargin(cardGrid, new Insets(0, 50, 0, 50)); // Set margin to achieve desired positioning

    HBox buttons = new HBox(5);
    buttons.getChildren().addAll(dealButton, checkButton);

    HBox checkFields = new HBox(5);

    //labels
    VBox labels = new VBox(11);
    String labelStyle = "-fx-font-family: 'Times New Roman'; -fx-font-size: 14";

    Label sumOfCardsLabel = new Label("Sum of cards:");
    sumOfCardsLabel.setStyle(labelStyle);
    Label cardOfSpadesLabel = new Label("All cards of hearts:");
    cardOfSpadesLabel.setStyle(labelStyle);
    Label queenOfSpadesLabel = new Label("Queen of spades in hand?");
    queenOfSpadesLabel.setStyle(labelStyle);
    Label handIsFlushLabel = new Label("Flush?");
    handIsFlushLabel.setStyle(labelStyle);

    labels.getChildren().addAll(
            sumOfCardsLabel,
            cardOfSpadesLabel,
            queenOfSpadesLabel,
            handIsFlushLabel);

    //fields
    VBox fields = new VBox(5);
    sumOfCardsField.setText("<No info>");
    sumOfCardsField.setEditable(false);

    cardsOfHeartsField.setText("<No info>");
    cardsOfHeartsField.setEditable(false);

    queenOfSpadesInHandField.setText("<No info>");
    queenOfSpadesInHandField.setEditable(false);

    handIsFlushField.setText("<No info>");
    handIsFlushField.setEditable(false);

    fields.getChildren().addAll(
            sumOfCardsField,
            cardsOfHeartsField,
            queenOfSpadesInHandField,
            handIsFlushField);

    checkFields.getChildren().addAll(labels,
            fields);

    //anchor pane
    AnchorPane bottomPane = new AnchorPane();
    bottomPane.setPrefHeight(200);
    bottomPane.getChildren().addAll(buttons, checkFields);
    AnchorPane.setLeftAnchor(buttons, 150.0);
    AnchorPane.setTopAnchor(buttons, 10.0);
    AnchorPane.setRightAnchor(checkFields, 170.0);
    AnchorPane.setTopAnchor(checkFields, 10.0);


    //stack pane
    StackPane leftPane = new StackPane();
    leftPane.setPrefWidth(150);

    StackPane rightPane = new StackPane();
    rightPane.setPrefWidth(150);

    StackPane topPane = new StackPane();
    topPane.setPrefHeight(150);

    //center pane
    main.setCenter(centerPane);
    main.centerProperty().get().setStyle(
            "-fx-background-color:#703911;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-border-color: #4b1912;" +
                    "-fx-background-radius: 15px;" +
                    "-fx-border-width: 10px;"
    );
    main.setBottom(bottomPane);
    main.setLeft(leftPane);
    main.setRight(rightPane);
    main.setTop(topPane);

    //scene
    Scene scene = new Scene(main);

    //stage
    primaryStage.setTitle("Playing Card Game");
    primaryStage.setScene(scene);
    primaryStage.setHeight(800);
    primaryStage.setWidth(1300);
    primaryStage.show();
  }

  private void checkHand() {
    sumOfCardsField.setText(String.valueOf(hand.sumOfCardsOnHand()));
    cardsOfHeartsField.setText(String.valueOf(hand.getAllHeartsCards()));
    queenOfSpadesInHandField.setText(String.valueOf(hand.isQueenOfSpadesOnHand() ? "Yes" : "No"));
    handIsFlushField.setText(String.valueOf(hand.isHandFlush() ? "Yes" : "No"));
  }


  private StackPane createCard(String imageLink) throws FileNotFoundException {
    // Create a stack pane to hold the card image, text, and other nodes
    StackPane cardPlaceholder = new StackPane();
    cardPlaceholder.setPrefSize(200, 300);
    cardPlaceholder.setStyle("-fx-background-color: white;");

    // Create an image view for the center image
    ImageView centerImageView = new ImageView(new Image(new FileInputStream(imageLink)));
    centerImageView.setPreserveRatio(true);
    centerImageView.setFitWidth(cardPlaceholder.getPrefWidth());
    centerImageView.setFitHeight(cardPlaceholder.getPrefHeight());

    // Set the padding of the stack pane to zero
    cardPlaceholder.setPadding(Insets.EMPTY);

    cardPlaceholder.getChildren().addAll(centerImageView);
    StackPane.setAlignment(centerImageView, Pos.CENTER);

    return cardPlaceholder;
  }

  private StackPane createEmptyCardPlaceholder(double width, double height) {
    // Create a stack pane to hold the card image, text, and other nodes
    StackPane cardPlaceholder = new StackPane();
    cardPlaceholder.setPrefSize(150, 200);
    cardPlaceholder.setStyle("-fx-background-color: white;");

    // Create an image view for the center image;

    // Set the padding of the stack pane to zero
    cardPlaceholder.setPadding(Insets.EMPTY);

    return cardPlaceholder;
  }

    private void resetCardsOnTable() {
    centerPane.getChildren().removeAll();
    centerPane.getChildren().add(displayHand);
    AnchorPane.setBottomAnchor(displayHand, 10.0);
    AnchorPane.setLeftAnchor(displayHand, 70.0);
    AnchorPane.setRightAnchor(displayHand, 70.0);
    }

}

