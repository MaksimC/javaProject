package MemoryTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 * Created by emaktse on 01.11.2015.
 */
public class main extends Application {
    Stage window1;
    Scene scene1, scene2, scene3;

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
        Label label1 = new Label("Please enter your name and age");
        TextField nameInput = new TextField();
        TextField ageInput = new TextField();
        nameInput.setPromptText("ENTER YOUR NAME HERE");
        ageInput.setPromptText("ENTER YOUR AGE HERE");
        Button buttonNext = new Button ("Next");
        buttonNext.setOnAction( e -> validateName(nameInput, ageInput));

        // Scene 1 layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, nameInput, ageInput, buttonNext);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene (layout1, 800, 600);

        window1.setScene(scene1);
        window1.setTitle("Memory test");

        window1.show();
    }

    //Name Validation method
    private void validateName(TextField input, TextField input1) {
        String inputAge = input1.getText();
        String inputName = input.getText();
        if (inputName.length() == 0){
            errorPopUp.errorPop2("Title", "Message");
        } else
            validateAge(inputAge, inputName);
    }

    // Age validation method
    private boolean validateAge(String inputAge, String inputName){
        try {
            int age = Integer.parseInt(inputAge);
            System.out.println("Name is " + inputName + ", Age is " + age);
            gameStart();

            return true;
        } catch (NumberFormatException e) {
            errorPopUp.errorPop("Title", "Message");
            return false;
        }
    }

    public void gameStart() {
        // Scene 2 elements
        Label label2 = new Label("Press start to begin test");
        Button buttonStart = new Button("start");
        buttonStart.setOnAction(e -> array());
        /* Label label3 = new Label(ageInput.getText());*/

        //Scene 2 layout
        VBox layout2 = new VBox(30);
        layout2.getChildren().addAll(label2, buttonStart);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2,800, 600);
        window1.setScene(scene2);

        window1.setScene(scene2);
        window1.setTitle("Memory Game");
        window1.show();
    }
    public void array() {
        Image[] allImages = new Image[49];
        String filelocation = new String();

        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            allImages[i] = new Image("file:"+filelocation);
        }

        //scene3 elements
        ImageView Picture1 = new ImageView();
        Picture1.setImage(allImages[48]);

        //Scene 3 layout
        BorderPane layout3 = new BorderPane();
        layout3.setCenter(Picture1);
        scene3 = new Scene(layout3, 800, 600);

        window1.setScene(scene3);
        window1.show();
//Change repo to SSH
    }
}

