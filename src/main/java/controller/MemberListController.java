package controller;

import javafx.collections.FXCollections;

import ConnectDataBase.CustomerDB;
import ConnectDataBase.ProductDataBase;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Customer;
import model.Product;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class MemberListController {

    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn ID,name,tel,view;
    private CustomerDB customerDB =new CustomerDB();

    @FXML
    public void initialize(){
//        ID.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
//        ID.setStyle("-fx-alignment: CENTER;");
        name.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        tel.setCellValueFactory(new PropertyValueFactory<Customer,String>("tel"));
        showTable();
        customerTableView.setEditable(true);
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
        customerTableView.setItems(addData(customerDB.getAllCustomer()));
    }
}
