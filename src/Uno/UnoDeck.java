package Uno;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class UnoDeck {

	private UnoCard[] cards;
	private int cardsInDeck;
	
	public UnoDeck() {
		cards = new UnoCard[108];	
	}
	public void reset() {
		UnoCard.Color[] colors = UnoCard.Color.values();
		cardsInDeck = 0;
		
		for(int i = 0; i <colors.length-1;i++) {
			UnoCard.Color color = colors[i];
			
			cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(0)); 
			
			for(int j = 1; j < 10; j++) {
				cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
				cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
			}
			
			UnoCard.Value[] values = new UnoCard.Value[] {UnoCard.Value.DessinerDeux, UnoCard.Value.Inverse, UnoCard.Value.Sauter};
			for (UnoCard.Value value : values) {
				cards[cardsInDeck++] = new UnoCard(color, value);
				cards[cardsInDeck++] = new UnoCard(color, value);
			}
		}
		UnoCard.Value[] values = new UnoCard.Value[] {UnoCard.Value.Libre, UnoCard.Value.Libre_Quatre};
		for (UnoCard.Value value : values) {
			for(int i = 0; i < 4; i++) {
			cards[cardsInDeck++] = new UnoCard(UnoCard.Color.Colore, value);
			}
		}
	}
	
	
	public void replaceDeckWith(ArrayList<UnoCard> cards) {
		this.cards = cards.toArray(new UnoCard[cards.size()]);
		this.cardsInDeck = this.cards.length;
	}
	
	
	public boolean isEmpty() {
		return cardsInDeck == 0;
	}
	
	public void shuffle() {
		int m = cards.length;
		Random random = new Random();
		
		for(int i = 0; i < cards.length; i++) {
			int randomValue = i + random.nextInt(m - 1);
			UnoCard randomCard = cards[randomValue];
			
			cards[randomValue] = cards[i];
			cards[i] = randomCard;
		}
	}
	
	
	public UnoCard drawCard() throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("xii, é necessário ter cartas na mesa para jogá-las");
		}
		return cards[-cardsInDeck];
	}
	
	public ImageIcon drawCardImage()throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("xii, é necessário ter cartas na mesa para jogá-las");
		}
		return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
	}
	
	
	public UnoCard[] drawCard(int m) {
		if (m < 0) {
			throw new IllegalArgumentException("xii, Não é possível lançar" + m + "cartas");
		}
		
		if (m > cardsInDeck) {
			throw new IllegalArgumentException("xii, Não é possível lançar" + m + "cartas");
		}
		
		UnoCard[] retorno = new UnoCard[m];
		for(int i = 0; i < m; i++) {
			retorno[i] = cards[--cardsInDeck];
		}
		return retorno;
	}
}

