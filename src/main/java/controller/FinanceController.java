package controller;

import ConnectDataBase.ItemSaleDB;
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
import model.Product;

import java.io.IOException;
import java.util.ArrayList;



public class FinanceController {
    @FXML
    Button buttonBack;
    @FXML
    ChoiceBox<String> boxM = new ChoiceBox<String>();
    @FXML
    ChoiceBox<String> boxY = new ChoiceBox<String>();

    @FXML
    ChoiceBox<String> box = new ChoiceBox<String>();
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn Item,Quantity,Amount,ID;
    @FXML
    Label total;

    ItemSaleDB itemSaleDB =new ItemSaleDB();

    @FXML
    public void initialize(){
        addBox();
        createTable();
        boxM.getSelectionModel().selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> changebox(newValue));
        boxM.getSelectionModel().select(0);
        boxY.getItems().add("2020");
        boxY.getSelectionModel().select(0);
        box.getSelectionModel().select(0);
        box.getSelectionModel().selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> changebox(newValue));

    }


    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerhome.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }
    public void createTable(){
        ID.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
        Item.setCellValueFactory(new PropertyValueFactory<Item,String>("item"));
        Quantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
        Amount.setCellValueFactory(new PropertyValueFactory<Item,Double>("amount"));
        ID.setStyle("-fx-alignment: CENTER;");
        Item.setStyle("-fx-alignment: CENTER;");
        Quantity.setStyle("-fx-alignment: center-right;");
        Amount.setStyle("-fx-alignment: center-right;");
        ArrayList<Item> arrayList=itemSaleDB.getAllItem();
        showTable(arrayList);
    }
    private void addBox(){
        boxM.getItems().add("01");
        boxM.getItems().add("02");
        boxM.getItems().add("03");
        boxM.getItems().add("04");
        boxM.getItems().add("05");
        boxM.getItems().add("06");
        boxM.getItems().add("07");
        boxM.getItems().add("08");
        boxM.getItems().add("09");
        boxM.getItems().add("10");
        boxM.getItems().add("11");
        boxM.getItems().add("12");
        box.getItems().add("All");
        box.getItems().add("Sport");
        box.getItems().add("Clothing");
        box.getItems().add("Appliance");
        box.getItems().add("Drug");
        box.getItems().add("Food");
        box.getItems().add("Beverage");
        box.getItems().add("Stationary");
        box.getItems().add("Other");
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




    public void changebox(String event){
        if(event.equals("01")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("01")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("02")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("02")){
                    temp.add(item);
                }
            }if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("03")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("03")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("04")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();

            for (model.Item item : arrayList) {
                if (item.getMonth().equals("04")){
                    temp.add(item);
                }
            }
            temp=checksame(temp);
            showTable(temp);
        }else if(event.equals("05")){
            tableView.getItems().clear();

            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("05")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("06")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("06")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("07")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("07")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("08")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("08")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if(event.equals("09")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("09")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if (event.equals("10")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("10")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if (event.equals("11")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("11")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }else if (event.equals("12")){
            tableView.getItems().clear();
            ArrayList<Item> temp=new ArrayList<>();
            ArrayList<Item> arrayList=itemSaleDB.getAllItem();
            for (model.Item item : arrayList) {
                if (item.getMonth().equals("12")){
                    temp.add(item);
                }
            }
            if(box.getValue().equals("All")){
                showTable(temp);
            }else if(box.getValue().equals("Sport")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Sport")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Clothing")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Clothing")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            } else if(box.getValue().equals("Appliance")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Appliance")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Drug")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Drug")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Food")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Food")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Beverage")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Beverage")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }
            else if(box.getValue().equals("Stationary")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Stationary")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }else if(box.getValue().equals("Other")){
                for (model.Item item : temp) {
                    if (item.getType().equals("Other")){
                        temp.remove(item);
                    }
                }
                showTable(temp);
            }

        }
    }

    public ArrayList<Item> checksame(ArrayList<Item> data) {
        ArrayList<Item> nosame =new ArrayList<>();

        for (Item datum : data) {
           if (notsame(nosame,datum.getItem())){
               nosame.add(datum);
           }else {
               nosame=addsame(nosame,datum);
           }
        }

        for (model.Item item : nosame) {
            System.out.println(item.getItem());
        }
        return nosame;
    }

    public Boolean notsame(ArrayList<Item> arrayList,String name){
        for (Item s : arrayList) {
            if(s.getItem().equals(name)){

                return false;
            }
        }
        return true;
    }

    public ArrayList<Item> addsame(ArrayList<Item> arrayList,Item string){
        for (model.Item item : arrayList) {
            if(item.getItem().equals(string.getItem())){
                item.setQuantity(item.getQuantity()+string.getQuantity());
                item.setAmount(item.getPrice()*item.getQuantity());
                break;
            }
        }

        return arrayList;
    }
}
