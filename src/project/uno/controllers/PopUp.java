package project.uno.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.uno.cards.UnoCard;
import project.uno.main.Game;

public class PopUp {

    @FXML private Button cardLabel; 

    private String cardImage = "";
    private Game game;
    private List<UnoCard> playerHand;
    private int choice;
    private List<Button> cardButtons;
    private GameStageController gameStage;
    private Button topCardButton;
    UnoCard.Color declaredColor;

    public PopUp(){}

    public PopUp(String cardName, Game game, int index, List<Button> cardButtons, GameStageController gameStage, Button topCardButton){
        cardImage = cardName;
        this.game = game;
        playerHand = game.getPlayerHand(game.getCurrentPlayer());
        choice = index;
        this.cardButtons = cardButtons;
        Image imag = new Image("/images/PNGs/small/" + cardImage + ".png");
        ImageView vw = new ImageView(imag);
        cardLabel.setGraphic(vw);
        this.gameStage = gameStage;
        this.topCardButton = topCardButton;
    }

    public void useCard(){
        PickColorFrame pickColor = new PickColorFrame(this);
        declaredColor = pickColor.choseColor(playerHand.get(choice));

        if(declaredColor != null){
            try {
                game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(choice), declaredColor);
            } catch (Exception e) {
                Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
            }

            if(declaredColor != UnoCard.Color.Wild){
                gameStage.setPidName(game.getCurrentPlayer());
                gameStage.setButtonIcons();
                Image imag = new Image("/images/PNGs/small/" + cardImage + ".png");
                ImageView vw = new ImageView(imag);
                topCardButton.setGraphic(vw);
            }
        }
    }

    public void cancel(){
    	
    }
}
