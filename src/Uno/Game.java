package Uno;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

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
}
