package project.uno.controllers;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.uno.cards.UnoCard;
import project.uno.main.Game;

public class GameStageController {

    @FXML private Button btt1;
    @FXML private Button btt2;
    @FXML private Button btt3;
    @FXML private Button btt4;
    @FXML private Button btt5;
    @FXML private Button btt6;
    @FXML private Button btt7;
    @FXML private Button btt8;
    @FXML private Button btt9;
    @FXML private Button btt10;
    @FXML private Button btt11;
    @FXML private Button btt12;
    @FXML private Button btt13;
    @FXML private Button btt14;
    @FXML private Button btt15;

    @FXML private Label playerName;
    @FXML private Button cardBack;
    @FXML private Button pileCard;

    private AddPlayersController addPlayers = new AddPlayersController();
    private List<String> temp;
    private String[] pids;
    private Game game;
    private List<Button> cardButtons = new ArrayList<Button>();
    private List<String> cardIds;
    private List<Image> cardImages = new ArrayList<Image>();
    PopUp window;

    @FXML
    public void initialize(){
        temp = AddPlayersController.playerIds;
        pids = temp.toArray(new String[temp.size()]);
        game = new Game(pids);
        populateArrayList();
        game.start(game);
        setPidName();
        Image img = new Image(getClass().getResource("/PNGs/small/" + game.getTopCard().toString().replace(" ", "_") + ".png").toExternalForm());
        ImageView vw = new ImageView(img);
        pileCard.setGraphic(vw);
        setButtonIcons();
    }

    public void setButtonIcons(){
        // Transforma o array de cartas em uma única String e os separa por ','
        String listString = game.getPlayerHand(game.getCurrentPlayer()).stream().map(Object::toString).collect(Collectors.joining(","));
        String[] cardNames = listString.split(",");
        cardIds = new ArrayList<String>(Arrays.asList(cardNames));

        //Colocando a imagem da carta caso esteja na mão do jogador
        for(int i=0;i<cardIds.size();i++){
            cardImages.add(new Image(getClass().getResource("/PNGs/small/"+ cardIds.get(i).replace(" ", "_") + ".png").toExternalForm()));
            ImageView view = new ImageView(cardImages.get(i));
            cardButtons.get(i).setGraphic(view);
        }

        //Retirando a imagem caso não esteja na mão do jogador
        for(int j=cardIds.size();j<cardButtons.size();j++){
            cardButtons.get(j).setGraphic(null);
        }
    }

    public void populateArrayList(){
        cardButtons.add(btt1);
        cardButtons.add(btt2);
        cardButtons.add(btt3);
        cardButtons.add(btt4);
        cardButtons.add(btt5);
        cardButtons.add(btt6);
        cardButtons.add(btt7);
        cardButtons.add(btt8);
        cardButtons.add(btt9);
        cardButtons.add(btt10);
        cardButtons.add(btt11);
        cardButtons.add(btt12);
        cardButtons.add(btt13);
        cardButtons.add(btt14);
        cardButtons.add(btt15);
    }

    public void setPidName(){
        String currentPlayer = game.getCurrentPlayer();
        playerName.setText(currentPlayer);
    }

    public void setPidName(String currentPlayer){
        playerName.setText(currentPlayer);
    }

    public void drawCardBtt(){
        JLabel message = new JLabel(game.getCurrentPlayer() + " puxou uma carta!");
        message.setFont(new Font("Arial",Font.BOLD,48));
        JOptionPane.showMessageDialog(null, message);

        try {
            game.submitDraw(game.getCurrentPlayer());
        } catch (Exception e) {
            Logger.getLogger(GameStageController.class.getName()).log(Level.SEVERE,null,e);
        }

        this.setPidName(game.getCurrentPlayer());
        this.setButtonIcons();
    }
    
    public void cardOne() {
    	if(cardIds.get(0) != null) {
    		int index = 0;
            
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            JOptionPane.showMessageDialog(null, declaredColor);
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardTwo() {
    	if(cardIds.get(1) != null) {
    		int index = 1;
	        List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
	        UnoCard.Color declaredColor = playerHand.get(index).getColor();
	        
	        if(declaredColor != null) {
	        	try {
	                game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
	            } catch (Exception e) {
	                Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
	            }
	        }
    	}
    	
    }
    
    public void cardThree() {
    	if(cardIds.get(2) != null) {
    		int index = 2;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardFour() {
    	if(cardIds.get(3) != null) {
    		int index = 3;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardFive() {
    	if(cardIds.get(4) != null) {
    		int index = 4;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardSix() {
    	if(cardIds.get(5) != null) {
    		int index = 6;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardSeven() {
    	if(cardIds.get(6) != null) {
    		int index = 5;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardEight() {
    	if(cardIds.get(7) != null) {
    		int index = 7;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardNine() {
    	if(cardIds.get(8) != null) {
    		int index = 8;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardTen() {
    	if(cardIds.get(9) != null) {
    		int index = 9;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardEleven() {
    	if(cardIds.get(10) != null) {
    		int index = 10;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardTwelve() {
    	if(cardIds.get(11) != null) {
    		int index = 11;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardThirteen() {
    	if(cardIds.get(12) != null) {
    		int index = 12;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardFourteen() {
    	if(cardIds.get(13) != null) {
    		int index = 13;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
    public void cardFifteen() {
    	if(cardIds.get(14) != null) {
    		int index = 14;
            List<UnoCard> playerHand = game.getPlayerHand(game.getCurrentPlayer());
            UnoCard.Color declaredColor = playerHand.get(index).getColor();
            
            if(declaredColor != null) {
            	try {
                    game.submitPlayerCards(game.getCurrentPlayer(), playerHand.get(index), declaredColor);
                } catch (Exception e) {
                    Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE,null,e);
                }
            }
    	}
    }
    
}
