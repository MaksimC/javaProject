package MemoryTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by emaktse on 01.11.2015.
 */
public class main extends Application {
    Stage window1;
    Scene scene1, scene2;

    public static void main () throws Exception {

        // define which methods will be used in program
        // setupStage
        // setupScene1
        // setupScene2
        // setupScene3
        // fillScene1
        // clickFunctions
        // Check result
        // Draw pictures
        // setupStage();
        // GIT TEST comment!!!
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window1 = primaryStage;

        // Scene 1 elements
        Label label1 = new Label("Please enter you age");
        TextField ageInsert = new TextField();
        ageInsert.setPromptText("ENTER YOUR AGE HERE");
        Button button1 = new Button ("Next");

        // Scene 1 layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, ageInsert, button1);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene (layout1, 800, 600);
        button1.setOnAction( e -> validateAge(ageInsert, ageInsert.getText()));

        // Scene 2 elements
        Label label2 = new Label("Press start to begin test");
        Button button2 = new Button("start");
        button2.setOnAction(e -> errorPopUp.errorPop("Title", "Message"));
        Label label3 = new Label(ageInsert.getText());

        //set layout for scene2
        VBox layout2 = new VBox(30);
        layout2.getChildren().addAll(label2, button2, label3);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2,800, 600);

        window1.setScene(scene1);
        window1.setTitle("Memory test");

        window1.show();
    }
    // Age validation method
    private boolean validateAge(TextField input, String message){
        try {
            int inputAge = Integer.parseInt(input.getText());
            window1.setScene(scene2);
            return true;
        } catch (NumberFormatException e) {
            errorPopUp.errorPop("Title", "Message");
            return false;
        }
    }
}

