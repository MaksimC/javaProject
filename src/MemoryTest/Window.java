package MemoryTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by emaktse on 06.01.2016.
 */
/*
public class Window extends Application {
    Stage window1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window1 = primaryStage;
    }

    public void introScreen(){
        //Player player = new Player();

        Label Name = new Label("Rapid Memory Test");
        Image img = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg2.jpg");
        ImageView imgView = new ImageView(img);
        Label Description = new Label("Today we have interesting and very complicated test.\n" +
                "You will be shown pictures. You need to remember it and on the next page \n" +
                "select only those pictures which you have been shown.\n" +
                "This is really complicated. Test has 10 levels, each new is more complex than previous");
        Button startTest = new Button("START TEST");
        startTest.setOnAction(e -> userInputScreen());

        VBox vbox = new VBox(40);
        vbox.getChildren().addAll(Name,Description,imgView, startTest);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 800);
        window1.setScene(scene);
        window1.setTitle("MEMORY TEST");
        window1.show();
    }
    public void userInputScreen(){
        // Scene 1 elements

        Label label1 = new Label("Please enter your name and age");
        TextField nameInput = new TextField();
        TextField ageInput = new TextField();
        nameInput.setPromptText("ENTER YOUR NAME HERE");
        ageInput.setPromptText("ENTER YOUR AGE HERE");
        Button buttonNext = new Button ("Next");
        buttonNext.setOnAction( e -> validateName(nameInput, ageInput));
        buttonNext.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            validateName(nameInput, ageInput);
        });

        // Scene 1 layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, nameInput, ageInput, buttonNext);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene (layout1, 1000, 800);

        window1.setScene(scene1);
        window1.setTitle("Memory test");

        window1.show();
    }

    //Name Validation method
    private void validateName(TextField input, TextField input1) {
        PlayerOld playerOld = new PlayerOld();
        errorPopUp error = new errorPopUp();
        String inputAge = input1.getText();
        String inputName = input.getText();
        if (inputName.length() == 0){
            error.errorPop2("Title", "Message");
        } else
            /*playerOld.*/ /*validateAge(inputAge, inputName);
    }

    // Age validation method
    private boolean validateAge(String inputAge, String inputName){
        errorPopUp error = new errorPopUp();
        try {
            int age = Integer.parseInt(inputAge);
            System.out.println("Name is " + inputName + ", Age is " + age);
            selectLevel(levelCounter);

            return true;
        } catch (NumberFormatException e) {
            error.errorPop("Title", "Message");
            return false;
        }
    }
    public void selectLevel (int levelCounter){
        correctImageCounter = 0;
        System.out.println("Level is "+levelCounter);
        Button[] levelButtonArray = new Button[levelCounter];
        for (int i = 0; i < levelCounter; i++) {
            Button button = new Button("Level " + (i+1));
            button.setOnAction(e ->
                    askImageScreen());
            levelButtonArray[i] = button;
        }

        VBox layout2 = new VBox(10);
        Label label2 = new Label("RAPID MEMORY TEST");
        layout2.getChildren().addAll(label2);
        for (int i = 0; i < levelButtonArray.length; i++) {
            layout2.getChildren().addAll(levelButtonArray[i]);
        }

        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2,1000, 800);

        window1.setScene(scene2);

        window1.setScene(scene2);
        window1.setTitle("Memory Game");
        window1.show();
    }
}
*/