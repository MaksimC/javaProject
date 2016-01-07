package MemoryTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by emaktse on 07.01.2016.
 */
public class Window extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        window1 = primaryStage;
        introScreen();
    }

    Test test = new Test();

    Stage window1;
    Scene scene1, scene2, scene3, scene4, scene5;

    Image[] allImageArray = test.createImageArray();


    public void introScreen(){

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
        errorPopUp error = new errorPopUp();
        String inputAge = input1.getText();
        String inputName = input.getText();
        if (inputName.length() == 0){
            error.errorPop2("Title", "Message");
        } else
            validateAge(inputAge, inputName);
    }

    // Age validation method
    private boolean validateAge(String inputAge, String inputName){
        errorPopUp error = new errorPopUp();
        try {
            int age = Integer.parseInt(inputAge);
            System.out.println("Name is " + inputName + ", Age is " + age);
            selectLevel(1, 2, window1);

            return true;
        } catch (NumberFormatException e) {
            error.errorPop("Title", "Message");
            return false;
        }
    }

    public void selectLevel (int levelCounter, int levelFactor, Stage primaryStage){
        window1 = primaryStage;
        System.out.println("Level is "+levelCounter);
        Button[] levelButtonArray = new Button[levelCounter];
        for (int i = 0; i < levelCounter; i++) {
            Button button = new Button("Level " + (i+1));
            button.setOnAction(e ->
                    askImageScreen(levelFactor));
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



    public void askImageScreen(int levelFactor) {

        Integer [] randomImageNumber = test.chooseRandomPictureNumbers();
        String [] listOfImageAsk = new String [levelFactor];

        Group group = new Group();
        for (int i = 0; i < levelFactor; i++) {
            Image image = allImageArray[randomImageNumber[i]];
            ImageView imageAsk = new ImageView(image);
            imageAsk.setId(randomImageNumber[i]+".gif");
            imageAsk.setX(i*83);
            group.getChildren().add(imageAsk);
            listOfImageAsk [i] = (randomImageNumber[i]+".gif");
            System.out.println("image ID is " + imageAsk.getId());
            System.out.println("saved ID is " + listOfImageAsk[i]);
        }

        Label instruction = new Label("RULES TO BE HERE");
        Button nextButton = new Button("NEXT");

        BorderPane layout3;
        layout3 = new BorderPane();

        layout3.setCenter(group);
        layout3.setTop(instruction);
        layout3.setBottom(nextButton);
        layout3.setAlignment(instruction, Pos.TOP_CENTER);
        layout3.setAlignment(nextButton, Pos.BASELINE_CENTER);
        nextButton.setOnAction(e -> checkResultScreen(listOfImageAsk));
        nextButton.setOnKeyPressed(e -> checkResultScreen(listOfImageAsk));

        scene3 = new Scene(layout3, 800, 600);
        window1.setScene(scene3);

    }

    public void checkResultScreen(String [] listOfImageAsk){


        VBox vbox = new VBox(40);
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        Image imgYes = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\yes.gif");
        Image imgNo = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\no.gif");

        int i =0;
        for (int j = 0; j < 7; j++) {
            for (int k = 0; k < 7; k++) {
                Image image = allImageArray[i];
                ImageView imageCheck = new ImageView(image);
                imageCheck.setId(i+".gif");
                grid.add(imageCheck, k, j);
                i++;
            }
        }

        grid.setOnMouseClicked(e ->{
            ImageView imageViewSourceRef = (ImageView) e.getTarget();
            reactOnMouseClick(grid, listOfImageAsk, imgYes, imgNo, imageViewSourceRef, window1);
        });

        Button tryAgain = new Button("Try again");
        grid.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(grid, tryAgain);
        vbox.setAlignment(Pos.CENTER);

        scene4 = new Scene(vbox, 1000, 800);
        window1.setScene(scene4);
    }
    public void reactOnMouseClick (GridPane grid, String[] listOfImageAsk, Image imgYes, Image imgNo, ImageView imageViewSourceRef, Stage window1){

        String id = imageViewSourceRef.getId();
        System.out.println(id);
        int columnIndex = grid.getColumnIndex(imageViewSourceRef);
        int rowIndex = grid.getRowIndex(imageViewSourceRef);
        test.testLogic(grid, imgNo, imgYes, columnIndex, rowIndex, listOfImageAsk, id, window1);
    }

    public void gameCompleteScreen (int correctImageCounter,int levelCounter, Stage primaryStage){
        window1 = primaryStage;
        Label label = new Label("RAPID MEMORY TEST");
        Label result = new Label ("RESULT:\n" +
                "Your memory works on " +(levelCounter-1)*10+ "%. \n" +
                "You are not recommended to engage in intellectual work.\n" +
                "Try to do physical labor. Or train your memory. There are plenty ways to do it.");
        Label amountOfRightImg = new Label("You have selected " +correctImageCounter+ " pictures correctly!");
        Image imgSimpson = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg.jpg");
        ImageView imageViewSimpson = new ImageView(imgSimpson);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, imageViewSimpson,result,amountOfRightImg);
        scene5 = new Scene(vbox, 1000, 800);
        window1.setScene(scene5);
        window1.show();

    }

}


