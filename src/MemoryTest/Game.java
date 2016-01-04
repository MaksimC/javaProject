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
 * Created by emaktse on 04.01.2016.
 */
public class Game extends Application{

    Stage window1;
    Scene scene1, scene2, scene3, scene4;
    Image[] allImages = new Image[49];
    int counter = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    public void createImageArray(){
        String filelocation;
        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            Image img = new Image("file:"+filelocation);
            allImages[i] = img;
        }
    }

    public void introScreen(Stage primaryStage){
        window1 = primaryStage;

        Label Name = new Label("Rapid Memory Test");
        Image img = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg2.jpg");
        ImageView imgView = new ImageView(img);
        Label Description = new Label("Today we have interesting and very complicated test." +
                "You will be shown pictures. You need to remember it and on the next page " +
                "select only those pictures which you have been shown" +
                "this is really complicated. Test has 10 levels, each new is more complex than previous");
        Button startTest = new Button("START TEST");
        startTest.setOnAction(e -> Player.userInputScreen(primaryStage));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(Name,imgView, startTest);
        Scene scene = new Scene(vbox, 1000, 800);
        window1.setScene(scene);
        window1.setTitle("MEMORY TEST");
        window1.show();
    }

    public static void levelSelectionScreen (){
        Button[] levelButtonArray = new Button[10];
        for (int i = 1; i < 10; i++) {
            Button button = new Button("Level" + i);
            button.setOnAction(e ->{askScreen();});
                levelButtonArray[i] = button;
            }
        Stage window1;


        }

    public static void askScreen(){

    }

}
