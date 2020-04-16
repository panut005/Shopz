package controller;

import ConnectDataBase.ItemSaleDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.Product;
import static controller.ApplicationRootController.arrayListStatic;
import java.io.IOException;
import java.util.ArrayList;

public class billController {


    ItemSaleDB itemSaleDB=new ItemSaleDB();

    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn Item,Price,Quantity,Amount,ID;
    @FXML
    Button buttonBack,pay;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }


    public void createTable(){
        Item.setCellValueFactory(new PropertyValueFactory<Product,String>("item"));
        Amount.setCellValueFactory(new PropertyValueFactory<Product,Double>("amount"));
        Item.setStyle("-fx-alignment: CENTER;");
        Amount.setStyle("-fx-alignment: center-right;");
        ArrayList<Item> arrayList=arrayListStatic;
        showTable(arrayList);
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

    @FXML
    public void handlepay(ActionEvent event) throws IOException {
        ArrayList<Item> arrayList= arrayListStatic;
       System.out.println(arrayList.size());
        for (Item item : arrayList) {
            System.out.println(item);
            itemSaleDB.addItemToDB(item);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Complete",ButtonType.OK);
        alert.showAndWait();
        pay= (Button) event.getSource();
        Stage stage = (Stage)pay.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }

}
