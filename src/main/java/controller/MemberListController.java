package controller;

import javafx.collections.FXCollections;

import ConnectDataBase.CustomerDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Customer;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class MemberListController {

    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer,String> ID,name,tel;
    @FXML
    private TableColumn<Customer, Button> show;
    private CustomerDB customerDB =new CustomerDB();

    @FXML
    public void initialize(){
//        ID.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
//        ID.setStyle("-fx-alignment: CENTER;");
        name.setCellValueFactory(new PropertyValueFactory<Customer,String>("firstname"));
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
}
