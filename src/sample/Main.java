package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    TextField artistField;
    TextField titleField;
    Label lyricsLabel;
    Button searchButton;

    @Override
    public void start(Stage stage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            stage.setTitle("Music Lyrics Search");

         artistField = (TextField) root.lookup("#artistField");
         titleField = (TextField) root.lookup("#titleField");
         lyricsLabel = (Label) root.lookup("#lyricsLabel");
        searchButton = (Button) root.lookup("#searchButton");
        lyricsLabel.setFont(new Font(10));

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String artist = artistField.getText();
                String title = titleField.getText();


                artist = artist.replace(' ','+');
                title = title.replace(' ','+');

                Request r = new Request("https://api.lyrics.ovh/v1/"+artist+'/'+title);
                try {
                    lyricsLabel.setText(r.sendRequest());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

            stage.setScene(new Scene(root, 600, 900));
            stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
