package MemoryTest;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by emaktse on 09.01.2016.
 *
 * Small program for testing rapid memory.
 * Standard medical test includes 5 pictures. Let's see how far we can go using mnemonic approach :)
 *
 */
public class Main extends Application {
    @Override

    /**
     * Kicking off the starting screen
     * */

    public void start(Stage primaryStage) throws Exception {
        Window window = new Window();
        window.introScreen();
    }
}
