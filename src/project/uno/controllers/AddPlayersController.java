package project.uno.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPlayersController {
    @FXML private TextField playerName;
    @FXML private Label pidOneLabel;
    @FXML private Label pidTwoLabel;
    @FXML private Label pidThreeLabel;
    @FXML private Label pidFourLabel;
    public List<String> playerIds = new ArrayList<String>();

    public String[] getPids(){
        String[] pids = playerIds.toArray(new String[playerIds.size()]);
        return pids;
    }

    public void savePlayer(){

        if(playerIds.size() > 0 && playerIds.size() < 5){
            JLabel message = new JLabel("Jogador Salvo!");
            JOptionPane.showMessageDialog(null, message);
            playerName.setText("");
        }

        if(playerName.getText().isEmpty()){
            JLabel message = new JLabel("Por favor, insira um nome!");
            JOptionPane.showMessageDialog(null, message);
        }
        else{
            String name = playerName.getText().trim();
            playerIds.add(name);

            switch(playerIds.size()){
                case 1:
                    pidOneLabel.setText(playerIds.get(0));
                break;
                case 2:
                    pidOneLabel.setText(playerIds.get(0));
                    pidTwoLabel.setText(playerIds.get(1));
                break;
                case 3:
                    pidOneLabel.setText(playerIds.get(0));
                    pidTwoLabel.setText(playerIds.get(1));
                    pidThreeLabel.setText(playerIds.get(2));
                break;
                case 4:
                    pidOneLabel.setText(playerIds.get(0));
                    pidTwoLabel.setText(playerIds.get(1));
                    pidThreeLabel.setText(playerIds.get(2));
                    pidFourLabel.setText(playerIds.get(3));
                break;
            }

        }

        if(playerIds.size() == 5){
            playerIds.remove(4);
            JLabel message = new JLabel("Só pode haver 2-4 jogadores!");
            JOptionPane.showMessageDialog(null, message);
            playerName.setText("");
        }

    }

    public void doneButton(){
        if(playerIds.size() == 1 || playerIds.size() == 0){
            JLabel message = new JLabel("Deve haver 2-4 jogadores!");
            JOptionPane.showMessageDialog(null, message);
        }

        // FALTA FINALIZAR
        // this.dispose();
        // new GameStage(playerIds).setVisible(true);
    }
}
