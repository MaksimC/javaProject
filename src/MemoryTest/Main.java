package MemoryTest;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by emaktse on 09.01.2016.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Window window = new Window();
        window.introScreen();
    }
}
