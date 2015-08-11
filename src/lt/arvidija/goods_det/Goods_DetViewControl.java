package lt.arvidija.goods_det;
import java.util.Optional;

//import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * apsirasome fxml faile esanciu daiktu controllerius, eidami per GoodsDetail klase (ji yra failo fxml modelis)
 */
public class Goods_DetViewControl {
	@FXML
    private TableView<GoodsDetailModel> goods_detTable;
    @FXML
    private TableColumn<GoodsDetailModel, Integer> gdsAutoIDColumn;
    @FXML
    private TableColumn<GoodsDetailModel, Integer> gdsIDColumn;
    @FXML
    private TableColumn<GoodsDetailModel, String> gdsNameColumn;
    @FXML
    private TableColumn<GoodsDetailModel, String> gdsDetDimColumn;
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
    @FXML
    private TextField gdsNameField;
    @FXML
    private TextField gdsDetDimField;
    @FXML
    private TextField gdsNoteField;
    @FXML
    private TextField gdsPriceField;
    @FXML
    private TextField gdsDateWrField;
    
/*private ObservableList<GoodsDetail> goods_detData = FXCollections.observableArrayList();	*/

    // buvo 
   private MainGoodsApp mainGoodsApp;//reikalingas kai add new dialog langas iskyla
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
    /**sio kontrolerio susiejimui su fxml failu, parasome publicna metoda (i ji kreipsis pagrindines klases MainGoodsApp 
    * metodas showGoods_DetOverview -> jis gaus atsakyma -> susiek mane (this) su tavimi (MainGoodsApp klase)
    * ir lentelei goods_detTable (ji yra aprasyta siame kontroleryje) suteik saltini -> per JavaFX klase ObservableList, kurios
    * duomenu sarasas yra uzpildytas pagrindineje klaseje MainGoodsApp
    */
    public void setMainGoodsApp(MainGoodsApp mainGoodsApp) {
    	this.mainGoodsApp = mainGoodsApp;
    
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
 	* inicijuojame fxml faile esanciu daiktu, kuriuos auksciau apsiraseme, duomenu suteikima jiems 
 	* sis metodas issisaukia, kai tik jau buna uzsikroves fxml failas (visi failo fxml laukai jau turi buti buve inicijuoti
 	*/
 	@FXML
    private void initialize() {//tai duomenys detaliai lentelei
 		gdsAutoIDColumn.setCellValueFactory(cellData -> cellData.getValue().gdsAutoIDProperty().asObject());
    	gdsIDColumn.setCellValueFactory(cellData -> cellData.getValue().gdsIDProperty().asObject());
        gdsNameColumn.setCellValueFactory(cellData -> cellData.getValue().gdsNameProperty());
        gdsDetDimColumn.setCellValueFactory(cellData -> cellData.getValue().gdsDetDimProperty());

        // Clear person details.
        setGoodsDetailsInfo(null);
        // Listen for selection changes and show the person details when changed.
        goods_detTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setGoodsDetailsInfo(newValue));

    }

	/**
	 * Fills all text fields to show details about the goodsdetail.
	 * If the specified goodsdetail is null, all text fields are cleared.
	 * 
	 * @param goodsdetail the goodsdetail or null
	 */
    /**
     * sis metodas parodo papildoma informacija, kai vaiksciojame po lentele
     * si metoda naudos interfeisas listeneris, idetas i sios klases initialize metoda
     * @param goodsdetail
     */
 	////////////////////////////////////////////////////////////////////////////
	//****************listenerio reagavimo i eiluciu vaiksciojima pabaiga*****//
 	////////////////////////////////////////////////////////////////////////////
 	
 	////////////////////////////////////////////////////////////////////////////
 	//******metodas labelius uzpildo reiksmemis is modelio geteriu ir seteriu,// 
 	//*******kur perduodama siam modeliui newValue is listenerio*****///////////
 	//*******jis-> isaukiamas vaiksciojant po eilutes****************///////////  
 	////////////////////////////////////////////////////////////////////////////
    private void setGoodsDetailsInfo(GoodsDetailModel goodsdetail) {
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
    //////////////////////////////////////////////////////////
	//********metodo, reagavusio i listeneri, pabaiga*****////
    //////////////////////////////////////////////////////////
    /**
     * Called when the user clicks on the delete button.
     */
    /////////////////////////////////////////////////////
    //***************delete knopkes paspaudimas********//
    /////////////////////////////////////////////////////
    @FXML
    private void handleDeleteGoodsDetail() {//sis metodas idetas fxml faile i knopkes delete veiksma on action
        int selectedIndex = goods_detTable.getSelectionModel().getSelectedIndex();
          if (selectedIndex >= 0) {
        	  //******trynimo dialoginis panelis
    		Alert alertInfoDelet = new Alert(AlertType.CONFIRMATION);
    		alertInfoDelet.setTitle("Ar tikrai trinti");
    		Stage stageErr = (Stage) alertInfoDelet.getDialogPane().getScene().getWindow();
    		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
    		alertInfoDelet.setHeaderText("Ar tikrai trinti");
    		alertInfoDelet.setContentText("Bus iðtrinta prekë " + 
    		goods_detTable.getSelectionModel().selectedItemProperty().getValue().getGdsName());//parodom pavadinimà
    		Optional<ButtonType> result = alertInfoDelet.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    // ... user chose OK
    			
            	goods_detTable.getItems().remove(selectedIndex);//istrinam lenteles eilute
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    		}
    		//alertInfoDelet.showAndWait();
        	//goods_detTable.getItems().remove(selectedIndex);//istrinam lenteles eilute
        	} else {
           // Nothing selected.
        	    // Nothing selected
        		Alert alertErrDelet = new Alert(AlertType.ERROR);
        		alertErrDelet.setTitle("Nepazymeta nieko");//().getScene().getWindow();
        		//alertErrDelet.getDialogPane().setStyle("cia rasomas stilius");  
        		// Get the Stage.
        		Stage stageErr = (Stage) alertErrDelet.getDialogPane().getScene().getWindow();
        		// Add a custom icon.
        		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
        		alertErrDelet.setHeaderText("Nepazymetas duomuo");
        		alertErrDelet.setContentText("Pazymekite eilute, tada galesite ja trinti!");
        		alertErrDelet.showAndWait();
        		/*  Dialogs.showWarningDialog(mainGoodsApp.getPrimaryStage(),
        	        "Please select a person in the table.",
        	        "No Person Selected", "No Selection");
*/
        }

    }
    ///////////////////////////////////////////////////////
    //****************delete knopkes pabaiga*************//
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    //****************new knopkes paspaudimas**********////
    ///////////////////////////////////////////////////////
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    /*
    @FXML
    private void handleNewGoodsDetail() {
    	GoodsDetailModel tempGoodsDetailModel = new GoodsDetailModel();
        mainGoodsApp.showGoodsDetailEditDialog(tempGoodsDetailModel);
        boolean okClicked = mainGoodsApp.showGoodsDetailEditDialog(tempGoodsDetailModel);
        if (okClicked) {
        	mainGoodsApp.getGoods_detData().add(tempGoodsDetailModel);
        }
    }*/
    ///////////////////////////////////////////////////////
    //****************new knopkes paspaudimas pabaiga**////
    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////
    //************edit knopkes paspaudimas*************////
    ///////////////////////////////////////////////////////
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    /*
    @FXML
    private void handleEditPerson() {
        GoodsDetailModel selectedGoodsDetailModel = goods_detTable.getSelectionModel().getSelectedItem();
        if (selectedGoodsDetailModel != null) {
            boolean okClicked = mainGoodsApp.showGoodsDetailEditDialog(selectedGoodsDetailModel);
            if (okClicked) {
            	setGoodsDetailsInfo(selectedGoodsDetailModel);
            }

        } else {
            // Nothing selected.
    		Alert alertErrDelet = new Alert(AlertType.ERROR);
    		alertErrDelet.setTitle("Nepazymeta nieko");//().getScene().getWindow();
    		Stage stageErr = (Stage) alertErrDelet.getDialogPane().getScene().getWindow();
    		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
    		alertErrDelet.setHeaderText("Nepazymetas duomuo");
    		alertErrDelet.setContentText("Pazymekite eilute, tada galesite ja redaguoti!");
    		alertErrDelet.showAndWait();
        }
    }*/
    ///////////////////////////////////////////////////////
    //************edit knopkes paspaudimas pabaiga*****////
    ///////////////////////////////////////////////////////

}