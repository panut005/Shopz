package controller;


import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.io.IOException;

public class VoucherController {
    @FXML
    Button buttonBack,save;
    @FXML
    DatePicker datePicker;
    LocalDate date;
    @FXML
    ChoiceBox<String> box = new ChoiceBox<String>();

    @FXML
    TextField name,discount;

    @FXML
    public void initialize(){
        datePicker = new DatePicker(LocalDate.now());
        box.getItems().add("baht");
        box.getItems().add("Percent");
        box.getSelectionModel().select(0);
        box.getSelectionModel().selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> changebox(newValue));

    }
    public void changebox(String event){

    }


    @FXML
    public void setDate(ActionEvent event) throws IOException{

        date = datePicker.getValue();

    }
    @FXML
    public void handlebuttonSave(ActionEvent event) throws IOException {

        String nameCode = name.getText();
        String discountS = discount.getText();
        System.out.println("Selected date: " + date);


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
