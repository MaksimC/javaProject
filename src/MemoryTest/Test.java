package MemoryTest;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


/**
 * Created by emaktse on 06.01.2016.
 */


public class Test {
    Image [] allImages = new Image[49];
    static int levelFactor = 2;
    static int levelCounter = 1;
    int correctImageCounter = 0;

    public  Image [] createImageArray() {

        String filelocation;
        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            Image img = new Image("file:" + filelocation);
            allImages[i] = img;
        }
        return allImages;
    }

    public Integer [] chooseRandomPictureNumbers(){
        Integer[] randomImageNumber = new Integer[49];
        for (int j = 0; j < randomImageNumber.length; j++) {
            randomImageNumber[j] = j;
        }
        Collections.shuffle(Arrays.asList((randomImageNumber)));

        return randomImageNumber;
    }

    public void testLogic(GridPane grid, Image imgNo, Image imgYes, int columnIndex,int rowIndex,String [] listOfImageAsk, String id, Stage window5){
        Window window = new Window();
        Stage window2 = new Stage();
        if(ifTheRightPictureClicked(id, listOfImageAsk)){
            grid.add(new ImageView(imgYes), columnIndex, rowIndex);
            correctImageCounter = correctImageCounter + 1;
            System.out.println("Correct image counter is "+correctImageCounter);
            System.out.println("Level Factor is " +levelFactor);
            if (correctImageCounter == levelFactor){
                System.out.println("Go to levelSuccess");
                window5.close();
                levelSuccess(correctImageCounter);
            }
        }else wrongImageClicked(grid, imgNo, columnIndex, rowIndex, window5);
    }

    public boolean ifTheRightPictureClicked (String id, String [] listOfImageAsk) {
        for (int i = 0; i < listOfImageAsk.length; i++) {
            if(id.equals(listOfImageAsk[i])){
                System.out.println("TRUE");
                return true;
            }
        }
        System.out.println("FALSE");
        return false;
    }

    public void levelSuccess(int correctImageCounter) {
        System.out.println("Method levelSuccess");
        Window window = new Window();

        levelCounter = levelCounter + 1;
        levelFactor = levelFactor + 2;
        if (levelCounter < 10)
            window.selectLevelScreen(levelCounter, levelFactor);
        else window.gameCompleteScreen(correctImageCounter, levelCounter, levelFactor);
        System.out.println("Level Factor is "+levelFactor);
        //this.correctImageCounter =0;

    }

    public void wrongImageClicked (GridPane grid, Image imgNo, int columnIndex, int rowIndex, Stage window5){
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> grid.add(new ImageView(imgNo), columnIndex, rowIndex)));
        timeline.play();
        //grid.add(new ImageView(imgNo), columnIndex, rowIndex);
        window5.close();
        System.out.println("method wrongImageClick");
        pause();
    }
    public void pause () {
        Window window = new Window();
        Stage window1 = new Stage();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            window.gameCompleteScreen(correctImageCounter, levelCounter, levelFactor);
        }
        window.gameCompleteScreen(correctImageCounter,levelCounter, levelFactor);
    }
}
