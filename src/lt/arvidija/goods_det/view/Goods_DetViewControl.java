package lt.arvidija.goods_det.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lt.arvidija.goods_det.MainGoodsApp;
import lt.arvidija.goods_det.model.GoodsDetail;
import lt.arvidija.goods_det.util.DateUtil;
/**
 * apsira�ome fxml faile esan�i� daikt� controllerius, eidami per GoodsDetail klas� (ji yra failo fxml modelis)
 */
public class Goods_DetViewControl {
	@FXML
    private TableView<GoodsDetail> goods_detTable;
    @FXML
    private TableColumn<GoodsDetail, Integer> gdsAutoIDColumn;
    @FXML
    private TableColumn<GoodsDetail, Integer> gdsIDColumn;
    @FXML
    private TableColumn<GoodsDetail, String> gdsNameColumn;
    @FXML
    private TableColumn<GoodsDetail, String> gdsDetDimColumn;
    @FXML
    private Label gdsNameLabel;
    @FXML
    private Label gdsDetDimLabel;
    @FXML
    private Label gdsNoteLabel;
    @FXML
    private Label gdsPriceLabel;
    @FXML
    private Label gdsDateWrLabel;
/*private ObservableList<GoodsDetail> goods_detData = FXCollections.observableArrayList();	*/

    // buvo 
  //  private MainGoodsApp mainGoodsApp;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Goods_DetViewControl() {/*
        goods_detData.add(new GoodsDetail(1, 45, "Hans", "Muster"));
        goods_detData.add(new GoodsDetail(2, 25, "Ruth", "Mueller"));
        goods_detData.add(new GoodsDetail(3, 60, "Heinz", "Kurz"));
        goods_detData.add(new GoodsDetail(4, 44, "Cornelia", "Meier"));
        goods_detData.add(new GoodsDetail(5, 77, "Werner", "Meyer"));
        goods_detData.add(new GoodsDetail(6, 895, "Lydia", "Kunz"));
        goods_detData.add(new GoodsDetail(7, 263, "Anna", "Best"));
        goods_detData.add(new GoodsDetail(8, 5694, "Stefan", "Meier"));
        goods_detData.add(new GoodsDetail(9, 451, "Martin", "Mueller"));
								*/
    }
    /*private ObservableList<GoodsDetail> getGoods_detData() {
        return goods_detData;
    }*/
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param MainGoodsApp
     */
    /**�io kontrolerio susiejimui su fxml failu, para�ome publi�n� metod� (� j� kreipsis pagrindin�s klas�s MainGoodsApp 
    * metodas showGoods_DetOverview -> jis gaus atsakym� -> susiek mane (this) su tavimi (MainGoodsApp klase)
    * ir lentelei goods_detTable (ji yra apra�yta �iame kontroleryje) suteik �altin� -> per JavaFX klas� ObservableList, kurios
    * duomen� s�ra�as yra u�pildytas pagrindin�je klas�je MainGoodsApp
    */
    public void setMainGoodsApp(MainGoodsApp mainGoodsApp) {
        // Add observable list data to the table
       goods_detTable.setItems(mainGoodsApp.getGoods_detData());
       /* goods_detTable.setItems(getGoods_detData());*/
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    /** 
     * Initialize the GoodsDetail table with the two columns. gdsAutoIDColumn 
 	* inicijuojame fxml faile esan�i� daikt�, kuriuos auk��iau apsira��me, duomen� suteikim� jiems 
 	* �is metodas issi�aukia, kai tik jau b�na u�sikrov�s fxml failas (visi failo fxml laukai jau turi b�ti buv� inicijuoti
 	*/
 	@FXML
    private void initialize() {
 		gdsAutoIDColumn.setCellValueFactory(cellData -> cellData.getValue().gdsAutoIDProperty().asObject());
    	gdsIDColumn.setCellValueFactory(cellData -> cellData.getValue().gdsIDProperty().asObject());
        gdsNameColumn.setCellValueFactory(cellData -> cellData.getValue().gdsNameProperty());
        gdsDetDimColumn.setCellValueFactory(cellData -> cellData.getValue().gdsDetDimProperty());

        // Clear person details.
        GoodsDetailsInfo(null);
        // Listen for selection changes and show the person details when changed.
        goods_detTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> GoodsDetailsInfo(newValue));

    }

	/**
	 * Fills all text fields to show details about the goodsdetail.
	 * If the specified goodsdetail is null, all text fields are cleared.
	 * 
	 * @param goodsdetail the goodsdetail or null
	 */
    /**
     * �is metodas parodo papildom� informacij�, kai vaik��iojame po lentel�
     * �� metod� naudos interfeisas listeneris, �d�tas � �ios klas�s initialize metod�
     * @param goodsdetail
     */
	//****************listenerio reagavimo � eilu�i� vaik��iojim� pabaiga
 	//******metodas, kuris i�aukiamas vaik��iojant po eilutes -> jis labelius u�pildo reik�m�mis i� modelio
 	//***geteri� ir seteri�, kur perduodama �iam modeliui newValue i� listenerio
    private void GoodsDetailsInfo(GoodsDetail goodsdetail) {
		if (goodsdetail != null) {
			gdsNameLabel.setText(goodsdetail.getGdsName());
			gdsDetDimLabel.setText(goodsdetail.getGdsDetDim());
			gdsPriceLabel.setText(Double.toString(goodsdetail.getGdsPrice()));
			gdsNoteLabel.setText(goodsdetail.getGdsNote());
			gdsDateWrLabel.setText(DateUtil.format(goodsdetail.getGdsDateWr()));
		} else {
			gdsNameLabel.setText("");
			gdsDetDimLabel.setText("");
			gdsPriceLabel.setText("");
			gdsNoteLabel.setText("");
			gdsDateWrLabel.setText("");
		}
	}
	//********metodo, reagavusio � listener�, pabaiga
    //**************delete knopk�s reagavimas
    /**
     * Called when the user clicks on the delete button.
     */
       
    @FXML
    private void handleDeleteGoodsDetail() {//�is metodas �d�tas fxml faile � knopk�s delete veiksm� on action
        int selectedIndex = goods_detTable.getSelectionModel().getSelectedIndex();
          if (selectedIndex >= 0) {
    		Alert alertInfoDelet = new Alert(AlertType.INFORMATION);
    		alertInfoDelet.setTitle("Nepa�ym�ta nieko");
    		Stage stageErr = (Stage) alertInfoDelet.getDialogPane().getScene().getWindow();
    		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
    		alertInfoDelet.setHeaderText("Tarpin� informacija");
    		alertInfoDelet.setContentText("Id trinamas senas " + 
    		goods_detTable.getSelectionModel().selectedItemProperty().getValue().getGdsAutoID());
    		alertInfoDelet.showAndWait();
        	goods_detTable.getItems().remove(selectedIndex);//i�trinam lentel�s eilut�
        	} else {
           // Nothing selected.
        	    // Nothing selected
        		Alert alertErrDelet = new Alert(AlertType.ERROR);
        		alertErrDelet.setTitle("Nepa�ym�ta nieko");//().getScene().getWindow();
        		//alertErrDelet.getDialogPane().setStyle("�ia ra�omas stilius");  
        		// Get the Stage.
        		Stage stageErr = (Stage) alertErrDelet.getDialogPane().getScene().getWindow();
        		// Add a custom icon.
        		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
        		alertErrDelet.setHeaderText("Nepa�ym�tas duomuo");
        		alertErrDelet.setContentText("Pa�ym�kite eilut�, tada gal�site j� trinti!");
        		alertErrDelet.showAndWait();
 
 
        		/*  Dialogs.showWarningDialog(mainGoodsApp.getPrimaryStage(),
        	        "Please select a person in the table.",
        	        "No Person Selected", "No Selection");
*/
        }

    }
	
    
    
}