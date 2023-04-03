package project.uno.controllers;

import javafx.fxml.FXML;
import project.uno.cards.UnoCard;

public class PickColorFrame {
	private UnoCard.Color wildColor = null;
	boolean allow = false;
	PopUp popup;
	
	public PickColorFrame() {
		
	}
	
	public PickColorFrame(PopUp pop) {
		this.popup = pop;
	}
	
	public UnoCard.Color choseColor(UnoCard card){
		if(card.getColor() == UnoCard.Color.Wild) {
			
		}
	}
}
