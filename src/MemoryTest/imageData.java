package MemoryTest;

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
    //Image [] imageArray = new Image[49];


    public static void loadImages (){
        Stage stage2 = new Stage();

        Scene scene3;
        Image img;
        //scene3 elements
        ImageView imgPic = new ImageView();

        //layout
        VBox layout3 = new VBox();
        layout3.getChildren().addAll(imgPic);
        scene3 = new Scene(layout3);

        stage2.setScene(scene3);
        stage2.show();
    }
}
