package controller;

import ConnectDataBase.CustomerDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;

public class MemberDetailController {

    private Customer data ;
    @FXML
    private TextField firstnameFill;

    @FXML
    private TextField lastnameFill;

    @FXML
    private TextField addressFill;

    @FXML
    private TextField tel_numberFill;

    @FXML
    private TextField districtField;

    @FXML
    private TextField provinceField;

    @FXML
    private TextField zipcodeField;
    @FXML
    private Button editBtn,backBtn;
    CustomerDB customerDB=new CustomerDB();

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                firstnameFill.setText(data.getFirstname().toString());
                editBtn.getStyleClass().add("btn-success");
                lastnameFill.setText(data.getLastname().toString());
                addressFill.setText(data.getAddress().toString());
                tel_numberFill.setText(data.getTel_number().toString());
                districtField.setText(data.getDistrict().toString());
                provinceField.setText(data.getProvince().toString());
                zipcodeField.setText(data.getZipcode().toString());

            }
        });
    }
    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        backBtn= (Button) event.getSource();
        Stage stage = (Stage)backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MemberList.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }

    public void setData(Customer data){
        this.data = data;
    }

    @FXML
    public void handleeditBtn(ActionEvent event) throws IOException {
        String f=firstnameFill.getText();
        String l=lastnameFill.getText();
        String a =addressFill.getText();
        String t =tel_numberFill.getText();
        String d = districtField.getText();
        String p = provinceField.getText();
        String z = zipcodeField.getText();

        Customer customer=new Customer(a,d,p,z,f,l,t);
        customerDB.update(customer);
        editBtn= (Button) event.getSource();
        Stage stage = (Stage)editBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MemberList.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }
}
