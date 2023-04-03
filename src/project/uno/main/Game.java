package project.uno.main;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import project.uno.cards.UnoCard;
import project.uno.cards.UnoDeck;
import project.uno.data_structures.MyException;

public class Game {
    private int currentPlayer;
    private String[] playerIds;

    private UnoDeck deck;
    private ArrayList<ArrayList<UnoCard>> playerHand;
    private ArrayList<UnoCard> stockPile;

    private UnoCard.Color validColor;
    private UnoCard.Value validValue;

    boolean gameDirection;

    public Game(String[] pids){
        deck = new UnoDeck();
        deck.reset();
        deck.shuffle();
        stockPile = new ArrayList<UnoCard>();

        playerIds = pids;
        currentPlayer = 0;
        gameDirection = false;

        playerHand = new ArrayList<ArrayList<UnoCard>>();

        for(int i=0;i<pids.length;i++){
            ArrayList<UnoCard> hand = new ArrayList<UnoCard>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }

    public void start(Game game){
        UnoCard card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if(card.getValue() == UnoCard.Value.Wild){
            start(game);
        }
        
        if(card.getValue() == UnoCard.Value.WildFour || card.getValue() == UnoCard.Value.DrawTwo){
            start(game);
        }
        
        if(card.getValue() == UnoCard.Value.Block){
            JLabel message = new JLabel(playerIds[currentPlayer] + " foi bloqueado");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);

            if(!gameDirection){
                currentPlayer = (currentPlayer+1) % playerIds.length;
            }
            else{
                currentPlayer = (currentPlayer-1) % playerIds.length;
                if(currentPlayer == -1){
                    currentPlayer = playerIds.length - 1;
                }
            }
        }

        if(card.getValue() == UnoCard.Value.Reverse){
            JLabel message = new JLabel(playerIds[currentPlayer] + " a direção do jogo foi mudada");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);
            gameDirection ^= true;
            currentPlayer = playerIds.length - 1;
        }

        stockPile.add(card);
    }

    public UnoCard getTopCard(){
        return new UnoCard(validColor, validValue);
    }

    public ImageIcon getTopCardImage(){
        return new ImageIcon(validColor + "-" + validValue + ".png");
    }

    public boolean isGameOver(){
        for(String player : this.playerIds){
            if(hasEmptyHand(player)){
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayer(){
        return this.playerIds[this.currentPlayer];
    }

    public String getPreviousPlayer(){
        int index = this.currentPlayer - 1;
        if(index == -1){
            index = playerIds.length - 1;
        }
        return this.playerIds[index];
    }

    public String[] getPlayers(){
        return this.playerIds;
    }

    public ArrayList<UnoCard> getPlayerHand(String pid){
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid){
        return getPlayerHand(pid).size();
    }

    public UnoCard getPlayerCard(String pid, int choice){
        ArrayList<UnoCard> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    public boolean hasEmptyHand(String pid){
        return getPlayerHand(pid).isEmpty();
    }

    public boolean validCardPlay(UnoCard card){
        return card.getColor() == validColor || card.getValue() == validValue;
    }

    public void checkPlayerTurn(String pid){
        if(this.playerIds[this.currentPlayer] != pid){
            throw new MyException("Este não é o seu turno " + pid);
        }
    }

    public void submitDraw(String pid){
        checkPlayerTurn(pid);

        if(deck.isEmpty()){
            deck = new UnoDeck(); //SUBSTITUIR ESTA LINHA POR UM METODO QUE FALTA IMPLEMENTAR
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
        if(!gameDirection){
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }
        else{
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if(currentPlayer == -1){
                currentPlayer = playerIds.length - 1;
            }
        }
    }

    public void setCardColor(UnoCard.Color color){
        this.validColor = color;
    }

    public void submitPlayerCards(String pid,UnoCard card,UnoCard.Color declaredColor){
        checkPlayerTurn(pid);
        ArrayList<UnoCard> pHand = getPlayerHand(pid);
        
        if(!validCardPlay(card)){
            if(card.getColor() == UnoCard.Color.Wild){
                validColor = card.getColor();
                validValue = card.getValue();
            }

            if(card.getColor() != validColor){
                JLabel message = new JLabel("jogada inválida, esperava cor: " + validColor);
                message.setFont(new Font("Arial",Font.BOLD,48));
                JOptionPane.showMessageDialog(null, message);
                throw new MyException("jogada inválida, esperava cor: " + validColor);
            }
            else if(card.getValue() != validValue){
                JLabel message = new JLabel("jogada inválida, esperava valor: " + validValue);
                message.setFont(new Font("Arial",Font.BOLD,48));
                JOptionPane.showMessageDialog(null, message);
                throw new MyException("jogada inválida, esperava valor: " + validValue);
            }
        }

        pHand.remove(card);
        if(hasEmptyHand(this.playerIds[currentPlayer])){
            JLabel message = new JLabel(this.playerIds[currentPlayer] + " ganhou o jogo!");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);
            System.exit(0);
        }

        validColor = card.getColor();
        validValue = card.getValue();
        stockPile.add(card);

        if(!gameDirection){
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }
        else{
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if(currentPlayer == -1){
                currentPlayer = playerIds.length - 1;
            }
        }

        if(card.getColor() == UnoCard.Color.Wild){
            validColor = declaredColor;
        }

        if(card.getValue() == UnoCard.Value.DrawTwo){
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " puxou 2 cartas!");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);
        }

        if(card.getValue() == UnoCard.Value.WildFour){
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " puxou 4 cartas!");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);
        }

        if(card.getValue() == UnoCard.Value.Block){
            JLabel message = new JLabel(this.playerIds[currentPlayer] + " foi bloqueado!");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);

            if(!gameDirection){
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }
            else{
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if(currentPlayer == -1){
                    currentPlayer = playerIds.length - 1;
                }
            }
        }

        if(card.getValue() == UnoCard.Value.Reverse){
            JLabel message = new JLabel(pid + " mudou a direção do jogo!");
            message.setFont(new Font("Arial",Font.BOLD,48));
            JOptionPane.showMessageDialog(null, message);

            gameDirection ^= true;
            if(gameDirection){
                currentPlayer = (currentPlayer - 2) % playerIds.length;
                if(currentPlayer == -1){
                    currentPlayer = playerIds.length - 1;
                }

                if(currentPlayer == -2){
                    currentPlayer = playerIds.length - 2;
                }
            }
            else{
                currentPlayer = (currentPlayer + 2) % playerIds.length;
            }
        }
    }

    
}
