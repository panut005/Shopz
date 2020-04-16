package controller;

import ConnectDataBase.CustomerDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//import static Controller.LoginController.*;

public class RegisterController {

    @FXML
    Button buttonBack;

    @FXML
    Button registerBtn;

    @FXML
    TextField firstnameFill;

    @FXML
    TextField lastnameFill;

    @FXML
    TextField addressFill;

    @FXML
    TextField tel_numberFill;

    @FXML
    Label label;

    @FXML
    TextField districtField;

    @FXML
    TextField provinceField;

    @FXML
    TextField zipcodeField;

    @FXML
    void initialize(){
        tel_numberFill.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tel_numberFill.setStyle("");
            }
        });
        addressFill.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addressFill.setStyle("");
            }
        });
        districtField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                districtField.setStyle("");
            }
        });
        provinceField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                provinceField.setStyle("");
            }
        });
        zipcodeField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                zipcodeField.setStyle("");
            }
        });
        firstnameFill.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                firstnameFill.setStyle("");
            }
        });
        lastnameFill.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lastnameFill.setStyle("");
            }
        });

    }

    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));

        stage.show();
        }

    @FXML
    protected void handleRegisterButtonAction(ActionEvent e) throws SQLException {
        String firstname = firstnameFill.getText();
        String lastname = lastnameFill.getText();
        String address = addressFill.getText();
        String district = districtField.getText();
        String province = provinceField.getText();
        String zipcode = zipcodeField.getText();
        String tel_number = tel_numberFill.getText();

        if(!StringUtils.isBlank(firstname)
        &&!StringUtils.isBlank(lastname)&&!StringUtils.isBlank(address)&&!StringUtils.isBlank(district)&&
        !StringUtils.isBlank(province)&&!StringUtils.isBlank(zipcode)&&!StringUtils.isBlank(tel_number)){
            ArrayList<String> arrs = new ArrayList<>();
            arrs.add(firstname);
            arrs.add(lastname);
            arrs.add(address);
            arrs.add(district);
            arrs.add(province);
            arrs.add(zipcode);
            arrs.add(tel_number);
            boolean haveSign =false;
            boolean addrHaveSign = false;
            for (int i = 0 ;i<arrs.size();i++) {
                String str = arrs.get(i);
                int factAllNumber ;
                for (int j = 0; j < str.length(); j++) {
                    factAllNumber =0;
                    if(i!=4) {
                        if(i!=5) {
                            if(i==2||i==3){
                                if ( ((str.charAt(j) > 47) && (str.charAt(j) < 65)) || ((str.charAt(j) > 90) && (str.charAt(j) < 97)) || (str.charAt(j) > 122)) {
                                    haveSign = true;
                                    Alert alert = new Alert(Alert.AlertType.WARNING,
                                            "Input have special sign, please check again", ButtonType.OK);
                                    alert.showAndWait();
                                    if(i==2){
                                        firstnameFill.setStyle("-fx-border-color: red");
                                    }else{
                                        lastnameFill.setStyle("-fx-border-color: red");;
                                    }
                                    break;
                                }
                            }
                        }
                        if(i==5){ //tel num
                            if((str.charAt(j) < 48) ||(str.charAt(j) > 57)){
                                haveSign = true;
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Input have special sign, please check again", ButtonType.OK);
                                alert.showAndWait();
                                tel_numberFill.setStyle("-fx-border-color: red");
                                break;
                            }
                        }
                    }
                    if(i==4){

                        if((((str.charAt(j) == ')') || (str.charAt(j) == '('))  || (str.charAt(j) == '.') || (str.charAt(j) == '/') || (str.charAt(j) == '\\') || (str.charAt(j) == ' '))
                            || ((str.charAt(j)  >= 48) && (str.charAt(j) <= 57)) || ((str.charAt(j) > 64) && (str.charAt(j) <= 90)) || ((str.charAt(j) >= 97) && (str.charAt(j) <= 122))) {
                            if (((str.charAt(j) >= 48) && (str.charAt(j)  <= 57)) || str.charAt(j)  == ' ') {
                                factAllNumber++;
                            }
                        }else{
                            addrHaveSign = true;
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "address can not have any sign or all number(example: !,@,#,$,%,^,&,*,-,+ ", ButtonType.OK);
                            alert.showAndWait();
                            addressFill.setStyle("-fx-border-color: red");
                            break;
                        }
                        if(factAllNumber== str.length()){
                                addrHaveSign = true;
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "address can not have any sign or all number(example: !,@,#,$,%,^,&,*,-,+ ", ButtonType.OK);
                                alert.showAndWait();
                                break;

                        }
                    }
                }
                if(haveSign || addrHaveSign){
                    break;
                }
            }
//            if(!haveSign && !addrHaveSign){
//                if((CustomerDB.checkUsername(userID))){
//
//                    Alert alert = new Alert(Alert.AlertType.WARNING,
//                            "Username has been used", ButtonType.OK);
//                    alert.showAndWait();
//                    userFill.setStyle("-fx-border-color: red");;
//
//                }else if(CustomerDB.checkTelephoneNo(tel_number)){
//                    Alert alert = new Alert(Alert.AlertType.WARNING,
//                            "Telephone number  has been used", ButtonType.OK);
//                    alert.showAndWait();
//                    tel_numberFill.setStyle("-fx-border-color: red");
//                }
//                else if((passID.equals(passID) ) ){
//                    CustomerDB.register(userID, passID, firstname, lastname, address, tel_number);
//                    navigateTo("/app.fxml", e, 1365, 1054);
//                }
//            }

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "please input in text field", ButtonType.OK);
            alert.showAndWait();
        }

    }
    public void navigateTo(String name, ActionEvent event, int width, int height){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(name));

        try {
            stage.setScene(new Scene((Parent) loader.load(), width, height));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
