package project.uno.controllers;

import project.uno.main.Uno;

public class MenuController {
    
    public void playGame() throws Exception{
        Uno.addPlayersScreen();
    }

    public void exitGame(){
        System.exit(0);
    }

    public void buttonTest(){

    }
}
