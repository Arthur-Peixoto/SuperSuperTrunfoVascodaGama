package project.uno.cards;

import javax.swing.ImageIcon;

import project.uno.data_structures.MyException;
import project.uno.data_structures.MyStackArray;

public class UnoDeck {
    private MyStackArray<UnoCard> deck;

    public UnoDeck(){
        this.deck = new MyStackArray<UnoCard>(108);
    }

    public void reset(){
        UnoCard.Color[] colors = UnoCard.Color.values();

        for(int i=0;i<colors.length-1;i++){
            UnoCard.Color color = colors[i];
            deck.push(new UnoCard(color, UnoCard.Value.getValue(0))); //ADICIONAR A CARTA 0

            //ADICIONAR DUAS DE CADA UMA DAS OUTRAS CARTAS
            for(int j=1;j<10;j++){
                deck.push(new UnoCard(color, UnoCard.Value.getValue(j))); 
                deck.push(new UnoCard(color, UnoCard.Value.getValue(j)));
            }

            //ADICIONAR AS CARTAS REVERSO,BLOQUEIO E +2
            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.DrawTwo,UnoCard.Value.Block,UnoCard.Value.Reverse};
            for(UnoCard.Value value : values){
                deck.push(new UnoCard(color, value)); 
                deck.push(new UnoCard(color, value));
            }

        }

        // ADICIONAR AS CARTAS CORINGA E +4
        UnoCard.Value[] wilds = new UnoCard.Value[]{UnoCard.Value.Wild,UnoCard.Value.WildFour};
        for(UnoCard.Value wild : wilds){
            for(int m=0;m<4;m++){
                deck.push(new UnoCard(UnoCard.Color.Wild, wild));
            }
        }
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    public void shuffle(){
        deck.shuffle();
    }

    public UnoCard drawCard(){
        if(isEmpty()){
            throw new MyException("O baralho está vazio");
        }
        return deck.pop();
    }

    public ImageIcon drawCardImageIcon(){
        if(isEmpty()){
            throw new MyException("o baralho está vazio");
        }
        return new ImageIcon(deck.peek().toString()+".png");
    }

    public UnoCard[] drawCard(int n){
        if(n < 0){
            throw new MyException("deve puxar um valor positivo de cartas");
        }
        else if(n > deck.size()){
            throw new MyException("não pode puxar todas as cartas");
        }

        UnoCard[] ret = new UnoCard[n];
        for(int i=0;i<n;i++){
            ret[i] = deck.pop();
        }

        return ret;
    }
}
