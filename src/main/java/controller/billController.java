package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.Product;
import static controller.ApplicationRootController.arrayListStatic;
import java.io.IOException;
import java.util.ArrayList;

public class billController {
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn Item,Price,Quantity,Amount,ID;
    @FXML
    Button buttonBack;

    @FXML
    Label total;
    @FXML
    public void initialize(){
        createTable();

    }

    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }


    public void createTable(){
       // ID.setCellValueFactory(new PropertyValueFactory<Product,String>("id"));
        Item.setCellValueFactory(new PropertyValueFactory<Product,String>("item"));
       // Price.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
      //  Quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        Amount.setCellValueFactory(new PropertyValueFactory<Product,Double>("amount"));
       // ID.setStyle("-fx-alignment: CENTER;");
        Item.setStyle("-fx-alignment: CENTER;");
//        Price.setStyle("-fx-alignment: center-right;");
//        Quantity.setStyle("-fx-alignment: center-right;");
        Amount.setStyle("-fx-alignment: center-right;");
        ArrayList<Item> arrayList=arrayListStatic;
        showTable(arrayList);
        tableView.setEditable(true);
    }

    void showTable(ArrayList<Item> arrayList){
        tableView.setItems(addData(arrayList));
        double t=0;
        for (Item i:arrayList){
            t=t+i.getAmount();
        }
        total.setText("total price : "+String.format("%,.2f",t)+" baht");
    }

    public ObservableList<Item> addData(ArrayList<Item> data){
        ObservableList<Item> temp= FXCollections.observableArrayList();
        for (Item i:data){
            temp.add(i);
        }
        return temp;
    }
}
