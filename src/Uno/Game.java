package Uno;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game {
	private int currentPlayer;
	private String[] playersIds;
	
	private UnoDeck deck;
	private ArrayList<ArrayList<UnoCard>> playerHand;
	private ArrayList<UnoCard> stockPile;
	
	private UnoCard.Value validValue;
	private UnoCard.Color validColor;
	
	boolean gameDirection;
	
	public Game(String[] pids) {
		deck = new UnoDeck();
		deck.shuffle();
		stockPile = new ArrayList<UnoCard>();
		
		playersIds = pids;
		currentPlayer = 0;
		gameDirection = false;
		
		playerHand = new ArrayList<ArrayList<UnoCard>>();
		
		for (int i = 0; i < pids.length; i++) {
			ArrayList<UnoCard> hand = new ArrayList<UnoCard>(Arrays.asList(deck.drawCard(7)));
			playerHand.add(hand);
		}
	}
	
	
	public void start(Game game) {
		UnoCard card = deck.drawCard();
		validColor = card.getColor();
		validValue = card.getValue();
		
		if(card.getValue() == UnoCard.Value.Libre || card.getValue() == UnoCard.Value.DessinerDeux || card.getValue() == UnoCard.Value.Libre_Quatre){
			start(game);
		}
		
		if(card.getValue() == UnoCard.Value.Sauter) {
			JLabel message = new JLabel(playersIds[currentPlayer] + "was skipped");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);
			
			if(gameDirection == false) {
				currentPlayer = (currentPlayer + 1) % playersIds.length;
			}
			
			else if(gameDirection == true) {
				currentPlayer = (currentPlayer - 1) % playersIds.length;
				if(currentPlayer == -1) {
					currentPlayer = playersIds.length - 1;
				}
			}
		}
		if(card.getValue() == UnoCard.Value.Inverse) {
			JLabel message = new JLabel(playersIds[currentPlayer] + "The game direction has changed");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);
			gameDirection ^= true;
			currentPlayer = playersIds.length - 1;
		}
		
		stockPile.add(card);
		
	}
	
	
	public UnoCard getTopCard() {
		return new UnoCard(validColor, validValue);
	}	
	
	
	public ImageIcon getTopImageIcon() {
		return new ImageIcon(validColor + "-" + validValue + ".png");	
	}
	
	
	public boolean isGameOver(){
		for (String player : this.playersIds) {
			if (hasEmptyHand(player)) {
				return true;
			}
		return false;
		}
	}
	
	
	public String getCurrentPlayer() {
		return this.playersIds[this.currentPlayer];
	}
	
	
	public String getPreviousPlayer() {
		int index = this.currentPlayer - 1;
		if (index == -1) {
			index = playersIds.length - 1;
		}
		return playersIds[index];
	}
	
	
	public String[] getPlayers() {
		return playersIds;
	}
	
	
	public ArrayList<UnoCard> getPlayerHand(String pid){
		int index = Arrays.asList(playersIds).indexOf(pid);
		return playerHand.get(index);
	}
	
	
	public int getPlayerHandSize(String pid) {
		return getPlayerHand(pid).size();
	}
	
	public UnoCard getPlayerCard(String pid, int choice) {
		ArrayList<UnoCard> hand = getPlayerHand(pid);
		return hand.get(choice);
	}
	
	
	public boolean hasEmptyHand(String pid) {
		return getPlayerHand(pid).isEmpty();
	}
	
	
	public boolean validCardPlayer() {
		return card.getColor() == validColor  || card.getValue() == validValue;
	}
	
	public void checkPlayerTurn(String pid) throws InvalidPlayerException{
		if (this.playersIds[this.currentPlayer] != pid) {
			throw new InvalidPlayerException("Nasceu de 7 meses?\nNão é o turno do" + pid +"°");
		}
	}
	
	
	public void submitDraw(String pid) throws InvalidPlayerException{
		checkPlayerTurn(pid);
		
		if(deck.isEmpty()) {
			deck.replaceDeckWith(stockPile);
			deck.shuffle(); 
		}
		
		getPlayerHand(pid).add(deck.drawCard());
		if(gameDirection == false) {
			currentPlayer = (currentPlayer + 1) % playersIds.length;
		}
		else if(gameDirection == true) {
			currentPlayer = (currentPlayer - 1) % playersIds.length;
			if (currentPlayer == -1) {
				currentPlayer = playersIds.length - 1;
			}
		}
	}
	
	
	public void setCardColor(UnoCard.Color color) {
		validColor = color;	
	}
	
	
}

class InvalidPlayerTurnException extends Exception {
	String playerId;
	
	public InvalidPlayerTurnException(String message, String pid) {
		super(message);
		playerId = pid;
	}
	
	public String getPid() {
		return playerId
	}
}
