package MemoryTest;

import javafx.beans.binding.StringBinding;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by emaktse on 05.12.2015.
 */
public class imageData {


    public static void array() {
        Image[] allImages = new Image[49];
        String filelocation = new String();

        for (int i = 0; i < 49; i++) {

            filelocation = "C:\\Users\\emaktse\\Documents\\HITSA\\GIT Repository\\javaProject\\Images library\\" + i + ".gif";
            System.out.println("URL is " + filelocation);
            allImages[i] = new Image("file:"+filelocation);
        }

        Stage stage2 = new Stage();
        Scene scene3;

        //scene3 elements
        ImageView Picture1 = new ImageView();
        Picture1.setImage(allImages[48]);

        //layout
        BorderPane layout3 = new BorderPane();
        layout3.setCenter(Picture1);
        scene3 = new Scene(layout3, 450, 250);

        stage2.setScene(scene3);
        stage2.show();

    }
}
