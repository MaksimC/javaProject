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

/**
 * Created by emaktse on 01.11.2015.
 */
public class main extends Application {
    Stage window1;
    Scene scene1, scene2, scene3, scene4;
    Image[] allImages = new Image[49];

    public static void main () throws Exception {

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
        buttonNext.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            validateName(nameInput, ageInput);
        });

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
            testStart();

            return true;
        } catch (NumberFormatException e) {
            errorPopUp.errorPop("Title", "Message");
            return false;
        }
    }

    public void testStart() {
        // Scene 2 elements
        Label label2 = new Label("Press start to begin test");
        Button buttonStart = new Button("start");
        buttonStart.setOnAction(e -> array());
        buttonStart.setOnKeyPressed(event -> array());
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


    public void array () {
        String filelocation;
        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            Image img = new Image("file:"+filelocation);
            allImages[i] = img;
        }

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
                /*imageCheck.setOnMouseClicked(e ->{
                    ImageView imageViewSourceRef = (ImageView) e.getSource();
                    String id = imageViewSourceRef.getId();
                    System.out.println(id);
                    int columnIndex = grid.getColumnIndex(imageViewSourceRef);
                    int rowIndex = grid.getRowIndex(imageViewSourceRef);
                    for (int l = 0; l < 5; l++) {
                        if (id.equals(listOfImageAsk[l])) {
                            grid.add(imageViewYes, columnIndex, rowIndex);
                            System.out.println(id + "  fuckoff");
                        } /*else {
                            grid.add(imageViewNo, columnIndex, rowIndex);
                        }
                    }
                });*/
                grid.add(imageCheck, k, j);
                i++;
            }
        }

        grid.setOnMouseClicked(e ->{
            ImageView imageViewSourceRef = (ImageView) e.getTarget();
            String id = imageViewSourceRef.getId();
            System.out.println(id);
            int columnIndex = grid.getColumnIndex(imageViewSourceRef);
            int rowIndex = grid.getRowIndex(imageViewSourceRef);


            if(ifTheRightPictureClicked(id, listOfImageAsk)){
                grid.add(new ImageView(imgYes), columnIndex, rowIndex);
                System.out.println(id + "  fuckoff");
                int counter =0;
                counter = counter + 1;
            } else grid.add(new ImageView(imgNo), columnIndex, rowIndex);
            gameover();

        });

        Button tryAgain = new Button("Try again");
        vbox.getChildren().addAll(grid, tryAgain);

        scene4 = new Scene(vbox, 800, 600);
        window1.setScene(scene4);
    }

    public static boolean ifTheRightPictureClicked (String id, String [] listOfImageAsk) {
        for (int i = 0; i < listOfImageAsk.length; i++) {
            if(id.equals(listOfImageAsk[i])){
                return true;
            }
        }return false;
    }
    public void gameover (){
        System.out.println("gameover method");
    }
}

