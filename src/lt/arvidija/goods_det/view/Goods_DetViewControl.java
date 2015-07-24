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
 * apsiraðome fxml faile esanèiø daiktø controllerius, eidami per GoodsDetail klasæ (ji yra failo fxml modelis)
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
    /**ðio kontrolerio susiejimui su fxml failu, paraðome publiènà metodà (á já kreipsis pagrindinës klasës MainGoodsApp 
    * metodas showGoods_DetOverview -> jis gaus atsakymà -> susiek mane (this) su tavimi (MainGoodsApp klase)
    * ir lentelei goods_detTable (ji yra apraðyta ðiame kontroleryje) suteik ðaltiná -> per JavaFX klasæ ObservableList, kurios
    * duomenø sàraðas yra uþpildytas pagrindinëje klasëje MainGoodsApp
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
 	* inicijuojame fxml faile esanèiø daiktø, kuriuos aukðèiau apsiraðëme, duomenø suteikimà jiems 
 	* ðis metodas issiðaukia, kai tik jau bûna uþsikrovæs fxml failas (visi failo fxml laukai jau turi bûti buvæ inicijuoti
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
     * ðis metodas parodo papildomà informacijà, kai vaikðèiojame po lentelæ
     * ðá metodà naudos interfeisas listeneris, ádëtas á ðios klasës initialize metodà
     * @param goodsdetail
     */
	//****************listenerio reagavimo á eiluèiø vaikðèiojimà pabaiga
 	//******metodas, kuris iðaukiamas vaikðèiojant po eilutes -> jis labelius uþpildo reikðmëmis ið modelio
 	//***geteriø ir seteriø, kur perduodama ðiam modeliui newValue ið listenerio
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
	//********metodo, reagavusio á listenerá, pabaiga
    //**************delete knopkës reagavimas
    /**
     * Called when the user clicks on the delete button.
     */
       
    @FXML
    private void handleDeleteGoodsDetail() {//ðis metodas ádëtas fxml faile á knopkës delete veiksmà on action
        int selectedIndex = goods_detTable.getSelectionModel().getSelectedIndex();
          if (selectedIndex >= 0) {
    		Alert alertInfoDelet = new Alert(AlertType.INFORMATION);
    		alertInfoDelet.setTitle("Nepaþymëta nieko");
    		Stage stageErr = (Stage) alertInfoDelet.getDialogPane().getScene().getWindow();
    		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
    		alertInfoDelet.setHeaderText("Tarpinë informacija");
    		alertInfoDelet.setContentText("Id trinamas senas " + 
    		goods_detTable.getSelectionModel().selectedItemProperty().getValue().getGdsAutoID());
    		alertInfoDelet.showAndWait();
        	goods_detTable.getItems().remove(selectedIndex);//iðtrinam lentelës eilutæ
        	} else {
           // Nothing selected.
        	    // Nothing selected
        		Alert alertErrDelet = new Alert(AlertType.ERROR);
        		alertErrDelet.setTitle("Nepaþymëta nieko");//().getScene().getWindow();
        		//alertErrDelet.getDialogPane().setStyle("èia raðomas stilius");  
        		// Get the Stage.
        		Stage stageErr = (Stage) alertErrDelet.getDialogPane().getScene().getWindow();
        		// Add a custom icon.
        		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
        		alertErrDelet.setHeaderText("Nepaþymëtas duomuo");
        		alertErrDelet.setContentText("Paþymëkite eilutæ, tada galësite jà trinti!");
        		alertErrDelet.showAndWait();
 
 
        		/*  Dialogs.showWarningDialog(mainGoodsApp.getPrimaryStage(),
        	        "Please select a person in the table.",
        	        "No Person Selected", "No Selection");
*/
        }

    }
	
    
    
}