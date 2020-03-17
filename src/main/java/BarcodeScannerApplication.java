

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
           // ApplicationRootController controller = fxmlLoader.getController();
            Scene scene = new Scene(root, 1365, 1054);
            primaryStage.setTitle("Camshier");
            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);
//            primaryStage.setOnCloseRequest(event -> {
//                if (controller != null) {
//                    controller.onClose();
//                }
//            });
//            controller.init();
//            controller.setParentStage(primaryStage);
            primaryStage.show();
        }catch (Exception ex){
           
        }
    }
}
