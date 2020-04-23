package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import controller.ApplicationRootController;

import java.io.IOException;

public class managerhomeController {

    @FXML
    private Button buttonStock,buttonFinance,buttonMember,buttonBack,buttonVoucher;

    @FXML
    public void handlebuttonStock(ActionEvent event) throws IOException {
        buttonStock= (Button) event.getSource();
        Stage stage = (Stage) buttonStock.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemlist.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }

    @FXML
    public void handlebuttonMember(ActionEvent event) throws IOException {
        buttonMember= (Button) event.getSource();
        Stage stage = (Stage) buttonMember.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MemberHome.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }
    @FXML
    public void handlebuttonFinance(ActionEvent event) throws IOException {
        buttonFinance= (Button) event.getSource();
        Stage stage = (Stage) buttonFinance.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/finance.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }


    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }

    @FXML
    public void handlebuttonVoucher(ActionEvent event) throws IOException {
        buttonVoucher= (Button) event.getSource();
        Stage stage = (Stage) buttonVoucher.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Voucher.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }

}
