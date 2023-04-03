package project.uno.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Uno extends Application{

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("UNO - Edição Vasco");
        setPalco(stage);
        menuScreen();
    }

    public static void setPalco(Stage palco){
        stage = palco;
    }

    public static void menuScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader(Uno.class.getResource("initial_screen.fxml"));
        Pane menuScreen = loader.load();
        Scene scene = new Scene(menuScreen);
        stage.setScene(scene);
        stage.show();
    }

    public static void addPlayersScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader(Uno.class.getResource("add_players.fxml"));
        Pane menuScreen = loader.load();
        Scene scene = new Scene(menuScreen);
        stage.setScene(scene);
        stage.show();
    }

    public static void gameStage() throws Exception{
        FXMLLoader loader = new FXMLLoader(Uno.class.getResource("game_stage.fxml"));
        Pane menuScreen = loader.load();
        Scene scene = new Scene(menuScreen);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
    }
    
}
