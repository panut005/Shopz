package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//import controller.ApplicationRootController;

import java.io.IOException;

public class MemberHomeController {

    @FXML
    private Button buttonRegister,buttonList;

    @FXML
    public void handlebuttonRegister(ActionEvent event) throws IOException {
        buttonRegister= (Button) event.getSource();
        Stage stage = (Stage) buttonRegister.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }

    @FXML
    public void handlebuttonList(ActionEvent event) throws IOException {
        buttonList= (Button) event.getSource();
        Stage stage = (Stage) buttonList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MemberList.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }
}
