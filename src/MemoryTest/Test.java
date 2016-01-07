package MemoryTest;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

        /*String [] listOfImageAsk = new String [levelFactor];

        for (int j = 0; i < listOfImageAsk.length; j++) {
            listOfImageAsk [j] = (randomImageNumber[j]+".gif");
            System.out.println(listOfImageAsk[j]);
        }*/


        return randomImageNumber;
    }

    public void gameLogic(GridPane grid, Image imgNo, Image imgYes, int columnIndex,int rowIndex,String [] listOfImageAsk, String id, Stage window1){
        MainOld mainOld = new MainOld();
        Stage window2 = new Stage();
        GameComplete gameComplete = new GameComplete();
        if(ifTheRightPictureClicked(id, listOfImageAsk)){
            grid.add(new ImageView(imgYes), columnIndex, rowIndex);
            correctImageCounter = correctImageCounter + 1;
            System.out.println("Correct image counter is "+correctImageCounter);
            System.out.println("Level Factor is " +levelFactor);
            if (correctImageCounter == levelFactor){
                System.out.println("Go to levelSuccess");
                window1.close();
                levelSuccess(correctImageCounter);
            }
        }else System.out.println("go to wrongImageClick");
       // wrongImageClicked(grid, imgNo, columnIndex, rowIndex);
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
        MainOld mainOld = new MainOld();
        Stage window2 = new Stage();
        levelCounter = levelCounter + 1;
        levelFactor = levelFactor + 2;
        if (levelCounter < 4)
            mainOld.selectLevel(levelCounter, levelFactor, window2);
        else mainOld.gameCompleteScreen(correctImageCounter, levelCounter, window2);
        System.out.println("Level Factor is "+levelFactor);
        //this.correctImageCounter =0;

    }

    public void wrongImageClicked (GridPane grid, Image imgNo, int columnIndex, int rowIndex){
        grid.add(new ImageView(imgNo), columnIndex, rowIndex);
        System.out.println("method wrongImageClick");
        pause();
    }
    public void pause () {
        MainOld mainOld = new MainOld();
        Stage window2 = new Stage();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            mainOld.gameCompleteScreen(correctImageCounter, levelCounter, window2);
        }
        mainOld.gameCompleteScreen(correctImageCounter,levelCounter, window2);
    }
}
