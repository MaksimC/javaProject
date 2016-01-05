/*package MemoryTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by emaktse on 04.01.2016.
 */
/*
public class Playfield extends Application{
    Image[] allImages = new Image[49];
    Stage window1;
    int counter = 0;
    Scene scene, scene1, scene2, scene3, scene4;


    public Playfield (){
        System.out.println("START PLAYFIELD");
        start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //window1 = primaryStage;
        createImageArray();
        introScreen();
    }

    public void createImageArray(){
        String filelocation;
        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            Image img = new Image("file:"+filelocation);
            allImages[i] = img;
        }
        introScreen();
        System.out.println("all image array ready");
    }

    public void introScreen(){
        Player player = new Player();

        Label Name = new Label("Rapid Memory Test");
        Image img = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg2.jpg");
        ImageView imgView = new ImageView(img);
        Label Description = new Label("Today we have interesting and very complicated test." +
                "You will be shown pictures. You need to remember it and on the next page " +
                "select only those pictures which you have been shown" +
                "this is really complicated. Test has 10 levels, each new is more complex than previous");
        Button startTest = new Button("START TEST");
        startTest.setOnAction(e -> player.userInputScreen());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(Name,imgView, startTest);
        Scene scene = new Scene(vbox, 1000, 800);
        window1.setScene(scene);
        window1.setTitle("MEMORY TEST");
        window1.show();
    }
    public void levelSelectionScreen (){
        Button[] levelButtonArray = new Button[10];
        for (int i = 1; i < 10; i++) {
            Button button = new Button("Level" + i);
            button.setOnAction(e ->{askScreen();});
            levelButtonArray[i] = button;
        }
        Stage window1;
    }
    public void askScreen(){
        Integer[] randomImageNumber = new Integer[49];
        for (int i = 0; i < randomImageNumber.length; i++) {
            randomImageNumber[i] = i;
        }
        Collections.shuffle(Arrays.asList((randomImageNumber)));

        String [] listOfImageAsk = new String [5];

        Group group = new Group();
        for (int i = 0; i < 5; i++) {
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

        VBox vbox = new VBox();
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
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
        vbox.getChildren().addAll(grid, tryAgain);

        scene4 = new Scene(vbox, 800, 600);
        window1.setScene(scene4);
    }
    public void reactOnMouseClick (GridPane grid, String[] listOfImageAsk, Image imgYes, Image imgNo, ImageView imageViewSourceRef){

        String id = imageViewSourceRef.getId();
        System.out.println(id);
        int columnIndex = grid.getColumnIndex(imageViewSourceRef);
        int rowIndex = grid.getRowIndex(imageViewSourceRef);

        if(ifTheRightPictureClicked(id, listOfImageAsk)){
            grid.add(new ImageView(imgYes), columnIndex, rowIndex);
            counter = counter + 1;
            System.out.println(counter);
            if (counter == 5){
                gameSuccess();
            }
        } else   gameover(grid, imgNo, columnIndex, rowIndex);

    }

    public boolean ifTheRightPictureClicked (String id, String [] listOfImageAsk) {
        for (int i = 0; i < listOfImageAsk.length; i++) {
            if(id.equals(listOfImageAsk[i])){
                return true;
            }
        }return false;
    }
    public void gameover (GridPane grid, Image imgNo, int columnIndex, int rowIndex){
        grid.add(new ImageView(imgNo), columnIndex, rowIndex);
        System.out.println("gameover method");
    }
    public void gameSuccess (){

        System.out.println("level UP!");
        Label label = new Label("RAPID MEMORY TEST");
        Label result = new Label ("RESULT:" +
                "Your memory works on 10%. " +
                "You are not recommended to engage in intellectual work." +
                "Try to do physical labor. Or train your memory. There are plenty ways to do it.");
        Label amountOfRightImg = new Label("You have selected all pictures correctly!");
        Image imgSimpson = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg.jpg");
        ImageView imageViewSimpson = new ImageView(imgSimpson);

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(label, imageViewSimpson,result,amountOfRightImg);
        scene1 = new Scene(vbox, 1000, 800);


    }
}
*/