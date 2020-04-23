package controller;


import javafx.beans.value.ObservableValue;
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
import model.Voucher;

import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;

public class VoucherController {
    @FXML
    Button buttonBack,save;
    @FXML
    DatePicker datePicker;
    LocalDate d;
    @FXML
    ChoiceBox<String> box = new ChoiceBox<String>();

    @FXML
    TextField name,discount,limit;
    static ArrayList<Voucher> arrayListVocher=new ArrayList<>();

    @FXML
    TableView tableView;
    @FXML
    TableColumn Codename,Discount,Limit,Date;
    String discountS ;


    @FXML
    public void initialize(){
        datePicker = new DatePicker(LocalDate.now());
        box.getItems().add("baht");
        box.getItems().add("Percent");
        box.getSelectionModel().select(0);
        box.getSelectionModel().selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> changebox(newValue));

        createTable();

    }
    public void changebox(String event){

    }


    public void createTable(){
        Codename.setCellValueFactory(new PropertyValueFactory<Voucher,String>("codename"));
        Discount.setCellValueFactory(new PropertyValueFactory<Voucher,String>("discount"));
        Limit.setCellValueFactory(new PropertyValueFactory<Voucher,Integer>("limit"));
        Date.setCellValueFactory(new PropertyValueFactory<Voucher,LocalDate>("localDate"));
        Codename.setStyle("-fx-alignment: CENTER;");
        Date.setStyle("-fx-alignment: center-right;");
        Discount.setStyle("-fx-alignment: center-right;");
        Limit.setStyle("-fx-alignment: center-right;");
        discount.setStyle("-fx-alignment: center-right;");
        limit.setStyle("-fx-alignment: center-right;");

        showTable(arrayListVocher);
    }
    void showTable(ArrayList<Voucher> arrayList){
        tableView.setItems(addData(arrayList));
    }

    public ObservableList<Voucher> addData(ArrayList<Voucher> data){
        ObservableList<Voucher> temp= FXCollections.observableArrayList();
        for (Voucher i:data){
            temp.add(i);
        }
        return temp;
    }
    @FXML
    public void setDate(ActionEvent event) throws IOException{

        d = datePicker.getValue();

    }
    @FXML
    public void handlebuttonSave(ActionEvent event) throws IOException {

        String nameCode = name.getText();
        if(box.getValue().equals("baht")){
            discountS = discount.getText();
        }else {
            discountS =discount.getText()+"%";
        }


        int l=Integer.parseInt(limit.getText());
        System.out.println("Selected date: " + d);

        arrayListVocher.add(new Voucher(nameCode,discountS,l,d));
        showTable(arrayListVocher);


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
