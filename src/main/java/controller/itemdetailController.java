package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;
import java.io.IOException;
import java.text.Format;
import java.util.ArrayList;

import ConnectDataBase.ProductDataBase;

import static controller.itemlistController.PRODUCT_ID;

public class itemdetailController {

    @FXML
    private Button buttonBack;
    @FXML
    private Label id,name,price,quantity;
    private String stringID;
    private Product product=null;
    private ProductDataBase productDataBase =new ProductDataBase();
    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }

    @FXML
    public void initialize(){
        System.out.println(PRODUCT_ID);
        System.out.println(stringID);
        stringID=PRODUCT_ID;
        System.out.println("test: "+stringID);
        ArrayList<Product> products=productDataBase.getAllProduct();
        for (Product i:products){
            if (i.getId().equals(stringID));
            product=i;
        }
        System.out.println("test:"+product);
        product=productDataBase.getProduct(PRODUCT_ID+"");
        id.setText("Product No:   "+product.getId());
        name.setText("Product Name: "+product.getName());
        price.setText("Price:   "+ String.format("%.2f", product.getPrice())+"Baht.");
        quantity.setText("Quantity: "+product.getQuantity());

    }

    public void productSetting(String s){
           stringID=s;
    }


}
