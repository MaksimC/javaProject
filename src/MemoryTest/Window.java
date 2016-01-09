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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by emaktse on 07.01.2016.
 */
public class Window  /*extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        introScreen();
    }*/
{
    Test test = new Test();
    Scene scene1, scene2, scene3, scene4, scene5;
    Image[] allImageArray = test.createImageArray();
    int levelFactor;
    static String name;
    static String age;

    public void introScreen(){
        Stage window2 = new Stage();
        Label Name = new Label("Rapid Memory Test");
        Name.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        Image img = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg2.jpg");
        ImageView imgView = new ImageView(img);
        Label Description = new Label("Today we have interesting and very complicated test.\n" +
                "You will be shown pictures. You need to remember it and on the next page \n" +
                "select only those pictures which you have been shown.\n" +
                "Test has 10 levels, each new is more complex than previous");
        Description.setTextAlignment(TextAlignment.CENTER);
        Description.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));
        Button startTest = new Button("START TEST");
        startTest.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        startTest.setOnAction(e -> {
            window2.close();
            userInputScreen();
        });

        VBox vbox = new VBox(40);
        vbox.getChildren().addAll(Name,Description,imgView, startTest);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 800);
        window2.setScene(scene);
        window2.setTitle("RAPID MEMORY TEST");
        window2.show();
    }

    public void userInputScreen(){
        Stage window3 = new Stage();

        Label label1 = new Label("Please enter your name and age");
        label1.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label name = new Label("NAME:     ");
        name.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 12));
        Label age = new Label("AGE:     ");
        age.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 12));
        TextField nameInput = new TextField();
        TextField ageInput = new TextField();
        nameInput.setPromptText("ENTER YOUR NAME HERE");
        ageInput.setPromptText("ENTER YOUR AGE HERE");
        Button buttonNext = new Button ("Next");
        buttonNext.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        buttonNext.setOnAction( e ->{
            validateName(nameInput, ageInput, window3);
        });
        buttonNext.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            validateName(nameInput, ageInput, window3);
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(name, 0, 0);
        grid.add(age, 0, 1);
        grid.add(nameInput, 1, 0);
        grid.add(ageInput, 1, 1);

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(label1, grid, buttonNext);
        vbox.setAlignment(Pos.CENTER);
        scene1 = new Scene (vbox, 1000, 800);

        window3.setScene(scene1);
        window3.show();

    }

   /* public void handle(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            nimedlist.add(nimiTekst.getText());
            nimiTekst.setText(null);
        }
    }*/

    private void validateName(TextField nameInput, TextField ageInput, Stage window3) {
        errorPopUp error = new errorPopUp();
        age = ageInput.getText();
        name = nameInput.getText();
        if (name.length() == 0){
            error.errorPop2("Title", "Message");
        } else
            validateAge(age, name, window3);
    }

    private boolean validateAge(String Age, String name, Stage window3){
        errorPopUp error = new errorPopUp();
        try {
            int age = Integer.parseInt(Age);
            System.out.println("Name is " + name + ", Age is " + age);
            Database database = new Database();
            database.addUserToDB(name, age);
            database.sulgeYhendus();
            window3.close();
            selectLevelScreen(1);
            return true;
        } catch (NumberFormatException e) {
            error.errorPop("Title", "Message");
            return false;
        }
    }

    public void selectLevelScreen (int levelAccessCounter){
        Stage window4 = new Stage();
        Database database = new Database();
        database.uuendaKasutajaAndmeid(name, (levelAccessCounter*10));

        System.out.println("Level access is "+levelAccessCounter);
        Button[] levelButtonArray = new Button[levelAccessCounter];
        for (int i = 0; i < levelAccessCounter; i++) {
            Button button = new Button("Level " + (i+1));
            button.setId(String.valueOf(i));
            button.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
            button.setOnAction(e ->{
                String buttonID = button.getId();
                System.out.println("button ID is: " + buttonID);
                int currentLevel = (Integer.parseInt(buttonID)+1);
                System.out.println("current level indicator is: " + currentLevel);
                askImageScreen(currentLevel);
                window4.close();
            });
            levelButtonArray[i] = button;
        }

        VBox layout2 = new VBox(30);
        Label label2 = new Label("RAPID MEMORY TEST");
        label2.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        layout2.getChildren().addAll(label2);
        for (int i = 0; i < levelButtonArray.length; i++) {
            layout2.getChildren().addAll(levelButtonArray[i]);
        }

        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2,1000, 800);

        window4.setScene(scene2);

        window4.setScene(scene2);
        window4.show();
    }

    public void askImageScreen(int currentLevel) {

        levelFactor = (currentLevel*2);
        Stage window5 = new Stage();

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

        }
        Label name = new Label("RAPID MEMORY TEST");
        name.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label instruction = new Label("Try to rememeber displayed pictures.");
        instruction.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        instruction.setTextAlignment(TextAlignment.CENTER);
        Button nextButton = new Button("NEXT");
        nextButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        nextButton.setOnAction(e -> {checkResultScreen(listOfImageAsk, levelFactor);
            window5.close();
        });
        nextButton.setOnKeyPressed(e -> {
            checkResultScreen(listOfImageAsk, levelFactor);
            window5.close();
        });

        VBox vbox = new VBox(40);
        vbox.getChildren().addAll(name, instruction, group, nextButton);
        vbox.setAlignment(Pos.CENTER);

        scene3 = new Scene(vbox, 800, 600);
        window5.setScene(scene3);
        window5.show();
    }

    public void checkResultScreen(String [] listOfImageAsk, int levelFactor){
        Stage window5 = new Stage();

        VBox vbox = new VBox(30);
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        Image imgYes = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\yes.gif");
        Image imgNo = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\no.gif");

        Label name = new Label("RAPID MEMORY TEST");
        name.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label instruction = new Label("Click on the pictures that you have remembered.\n" +
                "You need to click all of the pictures shown on the previous page\n" +
                "If you make a mistake, you will need to try this level again");
        instruction.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        instruction.setTextAlignment(TextAlignment.CENTER);

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
            reactOnMouseClick(grid, listOfImageAsk, imgYes, imgNo, imageViewSourceRef, window5, levelFactor);
        });

        grid.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(name, instruction, grid);
        vbox.setAlignment(Pos.CENTER);

        scene4 = new Scene(vbox, 1000, 800);
        window5.setScene(scene4);
        window5.show();
    }

    public void reactOnMouseClick (GridPane grid, String[] listOfImageAsk, Image imgYes, Image imgNo, ImageView imageViewSourceRef, Stage window5,int levelFactor){
        String id = imageViewSourceRef.getId();
        System.out.println(id);
        int columnIndex = grid.getColumnIndex(imageViewSourceRef);
        int rowIndex = grid.getRowIndex(imageViewSourceRef);
        test.testLogic(grid, imgNo, imgYes, columnIndex, rowIndex, listOfImageAsk, id, window5, levelFactor);
    }


    public void gameCompleteScreen (int correctImageCounter,int accessLevelCounter, int currentLevel){
        Test test = new Test();
        Stage window6 = new Stage();

        window6.setTitle("RAPID MEMORY TEST");

        Button tryLevelAgain = new Button("Try level again");
        tryLevelAgain.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        tryLevelAgain.setOnAction( e -> {
            window6.close();
            selectLevelScreen(accessLevelCounter);});
        tryLevelAgain.setOnKeyPressed(event -> {

            window6.close();
            System.out.println(event.getCode());
            selectLevelScreen(accessLevelCounter);
        });

        Button restartTest = new Button("Restart Game");
        restartTest.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        restartTest.setOnAction( e -> {
            window6.close();
            test.restartGame();
        });

        Button topTen = new Button("Top Mnemonics");
        topTen.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        topTen.setOnAction( e -> {
            Database database = new Database();
            database.extractDBtoHashMap();
            database.sulgeYhendus();
            //window6.close();
        });

        Label label = new Label("RAPID MEMORY TEST");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        System.out.println("name is " + name);
        Label resultLow = new Label ("RESULT:\n" +"\n"+
                name + ", Your memory works on " +(accessLevelCounter-1)*10+ "%. \n" +
                "You are not recommended to engage in intellectual work.\n" +
                "Try to do physical labor. Or train your memory. There are plenty ways to do it.");
        resultLow.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        resultLow.setTextAlignment(TextAlignment.CENTER);

        Label resultMedium = new Label ("RESULT:\n" +"\n"+
                name + ", Your memory works on " +(accessLevelCounter-1)*10+ "%. \n" +
                "You are recommended to do intellectual work.\n" +
                "But remember, there is some room for improvement.");
        resultMedium.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        resultMedium.setTextAlignment(TextAlignment.CENTER);

        Label resultHigh = new Label ("RESULT:\n" +"\n"+
                name +", Your memory works on " +(accessLevelCounter-1)*10+ "%. \n" +
                "You are recommended to do intellectual work.\n" +
                "You are genius, no comments needed!");
        resultHigh.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        resultHigh.setTextAlignment(TextAlignment.CENTER);

        Label amountOfRightImg = new Label("You have selected " +correctImageCounter+ " pictures correctly!");
        amountOfRightImg.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));
        amountOfRightImg.setTextAlignment(TextAlignment.CENTER);
        Image imgSimpson = new Image("file:C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\mozg.jpg");
        ImageView imageViewSimpson = new ImageView(imgSimpson);

        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, imageViewSimpson);
        if (accessLevelCounter < 3) {
            vbox.getChildren().addAll(resultLow);
            System.out.println("Level counter is <3");
        } else if (3 <= accessLevelCounter && accessLevelCounter < 7 ) {
            vbox.getChildren().addAll(resultMedium);
            System.out.println("level counter is <7");
        } else if (7 <= accessLevelCounter &&  accessLevelCounter <= 10) {vbox.getChildren().addAll(resultHigh);
            System.out.println("level counter is <10");
        }
        vbox.getChildren().addAll(amountOfRightImg, tryLevelAgain, restartTest, topTen);
        scene5 = new Scene(vbox, 1000, 800);
        window6.setScene(scene5);
        window6.show();
    }

    public void topTenScreen(){
        Stage window7 = new Stage();


        window7.show();

    }

}


