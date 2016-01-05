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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by emaktse on 01.11.2015.
 */
public class MainOld extends Application {
    Stage window1;
    Scene scene1, scene2, scene3, scene4, scene5;
    Image[] allImages = new Image[49];
    int correctImageCounter = 0;
    int levelCounter = 1;
    int levelFactor = 2;


    public MainOld(){
        System.out.println("method MainOld");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window1 = primaryStage;
        createImageArray();
        introScreen();
    }
    public void createImageArray() {
        String filelocation;
        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            Image img = new Image("file:" + filelocation);
            allImages[i] = img;
        }
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
            /*playerOld.*/validateAge(inputAge, inputName);
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
        VBox layout2 = new VBox(10);
        Label label2 = new Label("RAPID MEMORY TEST");
        layout2.getChildren().addAll(label2);
        Button[] levelButtonArray = new Button[levelCounter];
        for (int i = 0; i < levelCounter; i++) {
            Button button = new Button("Level " + (i+1));
            button.setOnAction(e ->
                    askImageScreen());
            levelButtonArray[i] = button;
        }


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


    public void askImageScreen() {

        Integer[] randomImageNumber = new Integer[49];
        for (int i = 0; i < randomImageNumber.length; i++) {
            randomImageNumber[i] = i;
        }
        Collections.shuffle(Arrays.asList((randomImageNumber)));

        String [] listOfImageAsk = new String [levelFactor];

        Group group = new Group();
        for (int i = 0; i < listOfImageAsk.length; i++) {
            Image image = allImages[randomImageNumber[i]];
            ImageView imageAsk = new ImageView(image);
            imageAsk.setId(randomImageNumber[i]+".gif");
            listOfImageAsk [i] = (randomImageNumber[i]+".gif");
            imageAsk.setX(i*83);
            group.getChildren().add(imageAsk);
            System.out.println(listOfImageAsk[i]);
        }

        //scene3 elements

        Label instruction = new Label("RULES TO BE HERE");
        Button nextButton = new Button("NEXT");

        BorderPane layout3 = new BorderPane();

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
        ImageView imageViewYes = new ImageView(imgYes);
        Image imgNo = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\no.gif");
        ImageView imageViewNo = new ImageView(imgNo);


        int i =0;
        for (int j = 0; j < 7; j++) {
            for (int k = 0; k < 7; k++) {
                Image image = allImages[i];
                ImageView imageCheck = new ImageView(image);
                imageCheck.setId(i+".gif");
                grid.add(imageCheck, k, j);
                i++;
            }
        }

        grid.setOnMouseClicked(e ->{
            ImageView imageViewSourceRef = (ImageView) e.getTarget();
            reactOnMouseClick(grid, listOfImageAsk, imgYes, imgNo, imageViewSourceRef);
        });

        Button tryAgain = new Button("Try again");
        grid.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(grid, tryAgain);
        vbox.setAlignment(Pos.CENTER);

        scene4 = new Scene(vbox, 1000, 800);
        window1.setScene(scene4);
    }
    public void reactOnMouseClick (GridPane grid, String[] listOfImageAsk, Image imgYes, Image imgNo, ImageView imageViewSourceRef){
        System.out.println("Level Factor is "+levelFactor);
        String id = imageViewSourceRef.getId();
        System.out.println(id);
        int columnIndex = grid.getColumnIndex(imageViewSourceRef);
        int rowIndex = grid.getRowIndex(imageViewSourceRef);

        if(ifTheRightPictureClicked(id, listOfImageAsk)){
            grid.add(new ImageView(imgYes), columnIndex, rowIndex);
            correctImageCounter = correctImageCounter + 1;
            System.out.println("Correct image counter is "+correctImageCounter);
            if (correctImageCounter == levelFactor){
                levelSuccess();
            }
        } else   wrongImageClicked(grid, imgNo, columnIndex, rowIndex);
    }

    public boolean ifTheRightPictureClicked (String id, String [] listOfImageAsk) {
        for (int i = 0; i < listOfImageAsk.length; i++) {
            if(id.equals(listOfImageAsk[i])){
                return true;
            }
        }return false;
    }


    public void levelSuccess() {
        levelCounter = levelCounter + 1;
        levelFactor = levelFactor + 2;
        if (levelCounter < 11)
            selectLevel(levelCounter);
        else gameComplete();
    }

    public void wrongImageClicked (GridPane grid, Image imgNo, int columnIndex, int rowIndex){
        grid.add(new ImageView(imgNo), columnIndex, rowIndex);
        System.out.println("gameover method");
        pause();
    }

    public void pause () {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        gameComplete();
    }

    public void gameComplete (){
        System.out.println("level UP!");
        Label label = new Label("RAPID MEMORY TEST");
        Label result = new Label ("RESULT:\n" +
                "Your memory works on " +levelCounter*10+ "%. \n" +
                "You are not recommended to engage in intellectual work.\n" +
                "Try to do physical labor. Or train your memory. There are plenty ways to do it.");
        Label amountOfRightImg = new Label("You have selected" +correctImageCounter+ "pictures correctly!");
        Image imgSimpson = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg.jpg");
        ImageView imageViewSimpson = new ImageView(imgSimpson);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, imageViewSimpson,result,amountOfRightImg);
        scene5 = new Scene(vbox, 1000, 800);
        window1.setScene(scene5);
    }
}

