package MemoryTest;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by emaktse on 01.11.2015.
 */
public class errorPopUp {
    public void errorPop (String title, String message) {

        // Set window/stage
        Stage window1 = new Stage();

        window1.initModality(Modality.APPLICATION_MODAL);
        window1.setTitle(title);
        window1.setWidth(300);
        window1.setHeight(200);

        // Scene1 elements
        Label label1 = new Label();
        label1.setText("Please enter correct age!");
        Button button1 = new Button("OK");
        button1.setOnAction(e -> window1.close());

        // Scene1 layout
        VBox layout1 = new VBox(30);
        layout1.getChildren().addAll(label1, button1);
        layout1.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout1);

        window1.setScene(scene1);
        window1.setTitle("ERROR!");
        window1.showAndWait();

    }

    public void errorPop2 (String title, String message) {

        // Set window/stage
        Stage window1 = new Stage();

        window1.initModality(Modality.APPLICATION_MODAL);
        window1.setTitle(title);
        window1.setWidth(300);
        window1.setHeight(200);

        // Scene1 elements
        Label label1 = new Label();
        label1.setText("Please enter name!");
        Button button1 = new Button("OK");
        button1.setOnAction(e -> window1.close());

        // Scene1 layout
        VBox layout1 = new VBox(30);
        layout1.getChildren().addAll(label1, button1);
        layout1.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout1);

        window1.setScene(scene1);
        window1.setTitle("ERROR!");
        window1.showAndWait();

    }

}


















