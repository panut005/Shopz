package controller;

import javafx.collections.FXCollections;

import ConnectDataBase.CustomerDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.Customer;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class MemberListController {
    @FXML
    Button buttonBack ;
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer,String> ID,name,tel,lastname;
    @FXML
    private TableColumn<Customer, Button> show;
    private CustomerDB customerDB =new CustomerDB();

    @FXML
    public void initialize(){
//        ID.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
//        ID.setStyle("-fx-alignment: CENTER;");
        name.setCellValueFactory(new PropertyValueFactory<Customer,String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<Customer,String>("lastname"));
        tel.setCellValueFactory(new PropertyValueFactory<Customer,String>("tel_number"));
        show.setCellValueFactory(new PropertyValueFactory<Customer,Button>("show"));
        showTable();
        tableView.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<Customer> addData(ArrayList<Customer> data){
        ObservableList<Customer> temp= FXCollections.observableArrayList();
        for (Customer i:data){
            temp.add(i);
        }
        return temp;
    }
    void showTable(){
        tableView.setItems(addData(customerDB.getAllCustomer()));
    }


    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerhome.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }
}
