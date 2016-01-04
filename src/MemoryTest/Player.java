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
 * Created by emaktse on 04.01.2016.
 */
public class Player extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void userInputScreen(Stage primaryStage){
        Stage window1;
        Scene scene1, scene2, scene3, scene4;
        window1 = primaryStage;

        // Scene 1 elements
        Label label1 = new Label("Please enter your name and age");
        TextField nameInput = new TextField();
        TextField ageInput = new TextField();
        nameInput.setPromptText("ENTER YOUR NAME HERE");
        ageInput.setPromptText("ENTER YOUR AGE HERE");
        Button buttonNext = new Button ("Next");
        buttonNext.setOnAction( e -> Player.validateName(nameInput, ageInput));
        buttonNext.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            Player.validateName(nameInput, ageInput);
        });

        // Scene 1 layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, nameInput, ageInput, buttonNext);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 800, 600);

        window1.setScene(scene1);
        window1.setTitle("Memory test");

        window1.show();

    }

    //Name Validation method
    public static void validateName(TextField input, TextField input1) {
        String inputAge = input1.getText();
        String inputName = input.getText();
        if (inputName.length() == 0){
            errorPopUp.errorPop2("Title", "Message");
        } else
            validateAge(inputAge, inputName);
    }

    // Age validation method
    public static boolean validateAge(String inputAge, String inputName){
        try {
            int age = Integer.parseInt(inputAge);
            System.out.println("Name is " + inputName + ", Age is " + age);
            Game.levelSelectionScreen();

            return true;
        } catch (NumberFormatException e) {
            errorPopUp.errorPop("Title", "Message");
            return false;
        }
    }


}
