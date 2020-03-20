package controller;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.Product;
import ConnectDataBase.ProductDataBase;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Tanuj on 2/12/17.
 */
public class ApplicationRootController implements Initializable{

    @FXML
    private Button buttonBack,Submit,Delete,add;
    @FXML
    private TableView<Item> tableView;
    @FXML
    private  TableColumn Item,Price,Quantity,Amount;
    @FXML
    private Text total;
    @FXML
    private TextField QuantityTF,search;

    private ProductDataBase productDataBase =new ProductDataBase();
    ArrayList<Item> arrayList=new ArrayList<>();
    /** UI Controls **/
    public AnchorPane mainContainer;
    public AnchorPane webcamContainerAnchorPane;
    public ListView<String> barcodeListView;
    public TextField txtBarcodeNumber;
    public ComboBox<BarcodeFormat> cmbBarcodeType;
    public Button btnGenerate;
    public Label lblStatus;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultWebcam = Webcam.getDefault();
        defaultWebcam.setViewSize(WebcamResolution.QVGA.getSize());
        defaultWebcamPanel = new WebcamPanel(defaultWebcam, true);
        creatDefaultWebcamPanel(defaultWebcamPanelNode);
        init();
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
        btnGenerate.setOnAction(event -> generateBarcode());

    }

    private void setRenderBarcodeTypeList() {
        for(BarcodeFormat barcodeFormat : BarcodeFormat.values()){
            options.add(barcodeFormat);
        }
        cmbBarcodeType.setItems(options);
    }

    private void generateBarcode() {
        Task<BufferedImage> barcodeWriterTask = new Task<BufferedImage>() {
            @Override
            protected BufferedImage call() throws Exception {
                String contents = txtBarcodeNumber.getText().trim();
                BarcodeFormat format = cmbBarcodeType.getSelectionModel().getSelectedItem();
                int width = 400;
                int height = 300;
                if(contents == null || (contents != null && contents.isEmpty()) || format == null){
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please enter valid content and barcode format!");
                        alert.setResult(ButtonType.CLOSE);
                        alert.initStyle(StageStyle.UNDECORATED);
                        alert.show();
                    });
                    return null;
                }
                try {
                    if(format == BarcodeFormat.QR_CODE){
                        height = 400;
                    }
                    BufferedImage image = MatrixToImageWriter.toBufferedImage(writer.encode(contents, format, width, height));
                    return image;
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Cannot generate barcode reason : " + e.getMessage());
                        alert.setResult(ButtonType.CLOSE);
                        alert.initStyle(StageStyle.UNDECORATED);
                        alert.show();
                    });
                    return null;
                }
            }
        };

        barcodeWriterTask.setOnSucceeded(event -> {
            BufferedImage result = barcodeWriterTask.getValue();
            if(result != null){
                openBarcodeModalWindow(result);
            }
        });

        new Thread(barcodeWriterTask).start();
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
    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        onClose();
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }

    public void onClose() {
        defaultWebcam.close();

    }

    public void createTable(){
        Item.setCellValueFactory(new PropertyValueFactory<Product,String>("item"));
        Price.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
        Quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        Amount.setCellValueFactory(new PropertyValueFactory<Product,Double>("amount"));
        Item.setStyle("-fx-alignment: CENTER;");
        Price.setStyle("-fx-alignment: center-right;");
        Quantity.setStyle("-fx-alignment: center-right;");
        Amount.setStyle("-fx-alignment: center-right;");
        arrayList.add(new Item("-",0,0,0));
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
        arrayList.add(new Item(product.getName(),p,q,a));
        showTable(arrayList);

    }public void addbarcode(String s){

        int q = 1;
        Product product =productDataBase.getProduct(s);
        if(arrayList.get(0).getItem().equals("-")){
            arrayList.remove(0);
        }
        double p =product.getPrice();
        double a =p*q;

        arrayList.add(new Item(product.getName(),p,q,a));
        showTable(arrayList);

    }

    @FXML
    public void deletHandle(ActionEvent event)throws Exception{
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        arrayList.remove(selectedItem);
        tableView.getItems().remove(selectedItem);
        showTable(arrayList);
    }

}