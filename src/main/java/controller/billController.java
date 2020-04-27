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
import model.Voucher;

import static controller.ApplicationRootController.arrayListStatic;

import static controller.VoucherController.arrayListVocher;
import java.io.IOException;
import java.util.ArrayList;

public class billController {


    ItemSaleDB itemSaleDB=new ItemSaleDB();

    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn Item,Price,Quantity,Amount,ID;
    @FXML
    Button buttonBack,pay,redeem;
    @FXML
    TextField code;
    double t=0;
    @FXML
    Label total,grandtotal,vat,discount;
    @FXML
    public void initialize(){
        createTable();
        discount.setVisible(false);

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
        for (Item i:arrayList){
            t=t+i.getAmount();
        }
        total.setText("Total: "+String.format("%,.2f",(t*93/100))+" baht");
        vat.setText("Vat (7%): "+String.format("%,.2f",t*7/100)+" baht");
        grandtotal.setText("Grand Total: "+String.format("%,.2f",t)+" baht");

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
        arrayListStatic=null;
        pay= (Button) event.getSource();
        Stage stage = (Stage)pay.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }



    @FXML
    public void handleRedeem(ActionEvent event) throws IOException {
        int s =0;
        for (Voucher voucher : arrayListVocher) {
            if(code.getText().equals(voucher.getCodename())){
                discount.setVisible(true);
                discount.setText("discount: "+voucher.getDiscount());
                grandtotal.setText("Grand Total: "+String.format("%,.2f",t-Integer.parseInt(voucher.getDiscount()))+" baht");
                voucher.setLimit(voucher.getLimit()-1);
                s++;
                break;
            }
        }
        if (s==0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "This Redeem code is not found.",ButtonType.OK);
            alert.showAndWait();
        }

    }


}
