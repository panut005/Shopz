

import controller.ApplicationRootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class BarcodeScannerApplication extends Application {

    

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("home.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1365, 1054);
            primaryStage.setTitle("Camshier");
            primaryStage.setScene(scene);

            primaryStage.show();
        }catch (Exception ex){
           
        }
    }
}
