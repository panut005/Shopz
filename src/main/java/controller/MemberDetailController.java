package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Customer;

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


    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                firstnameFill.setText(data.getFirstname().toString());
                lastnameFill.setText(data.getLastname().toString());
                addressFill.setText(data.getAddress().toString());
                tel_numberFill.setText(data.getTel_number().toString());
                districtField.setText(data.getDistrict().toString());
                provinceField.setText(data.getProvince().toString());
                zipcodeField.setText(data.getZipcode().toString());

            }
        });
    }

    public void setData(Customer data){
        this.data = data;
    }
}
