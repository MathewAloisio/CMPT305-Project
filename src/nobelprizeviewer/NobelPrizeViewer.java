/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprizeviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author raloi
 */
public class NobelPrizeViewer extends Application {
    @Override
    public void start(Stage pPrimaryStage) {
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 300, 250);
        
        pPrimaryStage.setTitle("Nobel prize viewer");
        pPrimaryStage.setScene(scene);
        pPrimaryStage.show();
    }

    /**
     * @param pArgs the command line arguments
     */
    public static void main(String[] pArgs) {
        launch(pArgs);
    }
    
}
