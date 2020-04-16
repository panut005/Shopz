package controller;
import ConnectDataBase.CustomerDB;
import ConnectDataBase.ItemSaleDB;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Item;
import model.Product;
import ConnectDataBase.ProductDataBase;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class ApplicationRootController implements Initializable{

    @FXML
    private Button buttonBack,Submit,Delete,addBth,submitBth,findMember,register;
    @FXML
    private TableView<Item> tableView;
    @FXML
    private  TableColumn Item,Price,Quantity,Amount,ID;
    @FXML
    private Text total,nameCus;
    @FXML
    private TextField QuantityTF,search,findCus;
    private ProductDataBase productDataBase =new ProductDataBase();
    ArrayList<Item> arrayList=new ArrayList<>();
    static ArrayList<Item> arrayListStatic=new ArrayList<>();
    CustomerDB customerDB=new CustomerDB();
    Customer customer=null;

    //webcam---------------------------------------------------------------------------------

    public AnchorPane webcamContainerAnchorPane;
    public ListView<String> barcodeListView;
    private Stage parentStage;
    public Stage getParentStage() {
        return parentStage;
    }
    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }
    ObservableList<BarcodeFormat> options = FXCollections.observableArrayList();
    private Webcam defaultWebcam = null;
    private WebcamPanel defaultWebcamPanel = null;
    private final SwingNode defaultWebcamPanelNode = new SwingNode();
    Writer writer = new MultiFormatWriter();
    private Runnable barcodeScannerRunnable = null;
// ----------------------------------------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultWebcam = Webcam.getDefault();
        defaultWebcam.setViewSize(WebcamResolution.QVGA.getSize());
        defaultWebcamPanel = new WebcamPanel(defaultWebcam, true);
        creatDefaultWebcamPanel(defaultWebcamPanelNode);
        init();
        defaultWebcam.close();
        createTable();
    }

    private void creatDefaultWebcamPanel(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            if(defaultWebcamPanel != null) {
                swingNode.setContent(defaultWebcamPanel);
            }
        });
    }

    public void init(){
        initUI();
        barcodeScannerRunnable = getBarcodeReaderThread();
        new Thread(barcodeScannerRunnable).start();
    }

    private void initUI() {
        Platform.runLater(() -> {
            webcamContainerAnchorPane.getChildren().clear();
            webcamContainerAnchorPane.getChildren().add(defaultWebcamPanelNode);
            setRenderBarcodeTypeList();
        });

    }

    private void setRenderBarcodeTypeList() {
        for(BarcodeFormat barcodeFormat : BarcodeFormat.values()){
            options.add(barcodeFormat);
        }
       // cmbBarcodeType.setItems(options);
    }



    private void openBarcodeModalWindow(BufferedImage result) {
        WritableImage image = SwingFXUtils.toFXImage(result, null);
        Stage stage = new Stage();
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(10);
        imageView.setLayoutY(10);
        imageView.setStyle("-fx-border-color: black");
        AnchorPane parent = new AnchorPane(imageView);
        parent.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6)");
        parent.setPrefWidth(image.getWidth() + 20);
        parent.setPrefHeight(image.getHeight() + 20);
        stage.setScene(new Scene(parent));
        stage.setTitle("Generated Barcode");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(parentStage);
        stage.show();
    }

    private Runnable getBarcodeReaderThread() {
        return () -> {
            BufferedImage image = null;
            Reader reader = new MultiFormatReader();
            Result lastResult = null;
            if(defaultWebcam != null){
                while(defaultWebcam.isOpen()){
                    if((image = defaultWebcam.getImage()) == null){
                        continue;
                    }
                    try {
                        LuminanceSource source = new BufferedImageLuminanceSource(image);
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                        final Result result = reader.decode(bitmap);
                        if(result != null && result.getText() != null) {
                            if(lastResult == null) {

                                Platform.runLater(() -> barcodeListView.getItems().add(result.getText()));
                                Platform.runLater(() -> addbarcode(result.getText()));
                            }else if(lastResult.getText()!= null && !lastResult.getText().equals(result.getText())){

                                Platform.runLater(() -> barcodeListView.getItems().add(result.getText()));
                                Platform.runLater(() -> addbarcode(result.getText()));
                            }
                        }
                        lastResult = result;
                    } catch (NotFoundException notEx){

                    }catch (Exception ex) {
                    }
                }
            }
        };
    }


    public void onClose() {
        defaultWebcam.close();

    }

    public void createTable(){
        ID.setCellValueFactory(new PropertyValueFactory<Product,String>("id"));
        Item.setCellValueFactory(new PropertyValueFactory<Product,String>("item"));
        Price.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
        Quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        Amount.setCellValueFactory(new PropertyValueFactory<Product,Double>("amount"));
        ID.setStyle("-fx-alignment: CENTER;");
        Item.setStyle("-fx-alignment: CENTER;");
        Price.setStyle("-fx-alignment: center-right;");
        Quantity.setStyle("-fx-alignment: center-right;");
        Amount.setStyle("-fx-alignment: center-right;");
        arrayList.add(new Item("-","-",0,0,0,getMonth(),getYear()));
        showTable(arrayList);
        tableView.setEditable(true);
    }

    public ObservableList<Item> addData(ArrayList<Item> data){
        ObservableList<Item> temp= FXCollections.observableArrayList();
        for (Item i:data){
            temp.add(i);
        }
        return temp;
    }
    void showTable(ArrayList<Item> arrayList){
        tableView.setItems(addData(arrayList));
        double t=0;
        for (Item i:arrayList){
            t=t+i.getAmount();
        }
        total.setText("total price : "+String.format("%,.2f",t)+" baht");
    }
    @FXML
    public void handlebuttonAdd(ActionEvent event) throws IOException{

        String s= search.getText();
        int q = Integer.parseInt(QuantityTF.getText());
        Product product =productDataBase.getProduct(s);
        if(arrayList.get(0).getItem().equals("-")){
            arrayList.remove(0);
        }
        double p =product.getPrice();
        double a =p*q;
        arrayList.add(new Item(s,product.getName(),p,q,a,getMonth(),getYear()));
        showTable(arrayList);
        search.clear();

    }

    public void addbarcode(String s){

        int q = 1;
        Product product =productDataBase.getProduct(s);
        if(arrayList.get(0).getItem().equals("-")){
            arrayList.remove(0);
        }
        double p =product.getPrice();
        double a =p*q;

        arrayList.add(new Item(s,product.getName(),p,q,a,getMonth(),getYear()));
        showTable(arrayList);

    }

    @FXML
    public void deletHandle(ActionEvent event)throws Exception{
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        arrayList.remove(selectedItem);
        tableView.getItems().remove(selectedItem);
        showTable(arrayList);
    }

    @FXML
    public void handlesubmit(ActionEvent event) throws IOException {

        arrayListStatic=arrayList;

        submitBth= (Button) event.getSource();
        Stage stage = (Stage)submitBth.getScene().getWindow();
        onClose();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bill.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }


    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));

        onClose();
        stage.setScene(new Scene((Parent) loader.load()));

        stage.show();
    }

    @FXML
    public void handlebuttonfindMember(ActionEvent event) throws IOException {
        String f=findCus.getText();
        if(!f.isEmpty()){
            customer=customerDB.getCustomer(f);
            nameCus.setText("Hello "+customer.getFirstname());
            findCus.setVisible(false);
            findMember.setVisible(false);
        }
    }

    public String getYear(){
        String y="";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date date = new Date();
        y=formatter.format(date);
        return y;
    }
    public String getMonth(){
        String m="";
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date date = new Date();
        m=formatter.format(date);
        return m;
    }

}