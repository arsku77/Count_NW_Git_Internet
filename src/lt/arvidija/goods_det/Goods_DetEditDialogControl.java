package lt.arvidija.goods_det;

//import ch.makery.address.model.Person;
//import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//import org.controlsfx.dialog.Dialogs;


/**
 * Dialog to edit details of a goodsdetail.
 * 
 * @author Marco Jakob
 */
public class Goods_DetEditDialogControl {
//apsibreziam textinius laukus
    @FXML
    private TextField gdsAutoIDFieldEd;
    @FXML
    private TextField gdsIDFieldEd;
    @FXML
    private TextField gdsNameFieldEd;
    @FXML
    private TextField gdsDetDimFieldEd;
    @FXML
    private TextField gdsPriceFieldEd;
    @FXML
    private TextField gdsDateWrFieldEd;
    @FXML
    private TextField gdsNoteFieldEd;


    private Stage dialogStageGoodsEdit;
    private GoodsDetailModel goodsdetail;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    public void setMainGoodsApp(MainGoodsApp mainGoodsApp) {
    
        // Add observable list data to the table
    		// goods_detTable.setItems(mainGoodsApp.getGoods_detData());
    	 
       /* goods_detTable.setItems(getGoods_detData());*/
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStageGoodsEdit
     */
    public void setDialogStage(Stage dialogStageGoodsEdit) {
        this.dialogStageGoodsEdit = dialogStageGoodsEdit;
    }

    /**
     * Sets the goodsdetail to be edited in the dialog.
     * 
     * @param goodsdetail
     */

    public void setGoodsDetailModel(GoodsDetailModel goodsdetail) {
        this.goodsdetail = goodsdetail;
        gdsAutoIDFieldEd.setText(Integer.toString(goodsdetail.getGdsAutoID()));
        gdsIDFieldEd.setText(Integer.toString(goodsdetail.getGdsID()));
        gdsNameFieldEd.setText(goodsdetail.getGdsName());
        gdsDetDimFieldEd.setText(goodsdetail.getGdsDetDim());
        gdsPriceFieldEd.setText(Double.toString(goodsdetail.getGdsPrice()));
        gdsNoteFieldEd.setText(goodsdetail.getGdsNote());
        gdsDateWrFieldEd.setText(DateUtil.format(goodsdetail.getGdsDateWr()));
        gdsDateWrFieldEd.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {//kai paspaudziam ok, jei nera klaidu, t.y. isInputValid true -> pakeiciam modelio ojekto savybes, t.y. reiksmes su seteriais
        if (isInputValid()) {//is parasytu lauku reiksmiu pakeiciame modelio reiksmes -> jomis bus uzpildyta lentele
        	goodsdetail.setGdsAutoID(Integer.parseInt(gdsAutoIDFieldEd.getText()));
            goodsdetail.setGdsID(Integer.parseInt(gdsIDFieldEd.getText()));
        	goodsdetail.setGdsName(gdsNameFieldEd.getText());
            goodsdetail.setGdsDetDim(gdsDetDimFieldEd.getText());
            goodsdetail.setGdsPrice(Double.parseDouble(gdsPriceFieldEd.getText()));
            goodsdetail.setGdsNote(gdsNoteFieldEd.getText());
            goodsdetail.setGdsDateWr(DateUtil.parse(gdsDateWrFieldEd.getText()));

            okClicked = true;
            dialogStageGoodsEdit.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStageGoodsEdit.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
/////////////////////////////////////////////////////////////////////////////////////////        
        if (gdsAutoIDFieldEd.getText() == null || gdsAutoIDFieldEd.getText().length() == 0) {
        	errorMessage += "Nëra reikðmës auto kode!\n"; 
        } else {
        	try {
        		Integer.parseInt(gdsAutoIDFieldEd.getText());
        	} catch (NumberFormatException e) {
        		errorMessage += 
        				"Yra reikðmë auto kode, bet ji netinkama (Tai turi bûti skaièius be kablelio)!\n"; 
        									}
        }
/////////////////////////////////////////////////////////////////////////////////////////        
        if (gdsIDFieldEd.getText() == null || gdsIDFieldEd.getText().length() == 0) {
            errorMessage += "Nëra reikðmës kode!\n"; 
        } else {
            try {
                Integer.parseInt(gdsIDFieldEd.getText());
            } catch (NumberFormatException e) {
                errorMessage += 
                		"Yra reikðmë kode, bet ji netinkama (Tai turi bûti skaièius be kablelio)!\n"; 
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////
        if (gdsNameFieldEd.getText() == null || gdsNameFieldEd.getText().length() == 0) {
            errorMessage += "Nëra reikðmës pavadinime!\n"; 
        }
        if (gdsDetDimFieldEd.getText() == null || gdsDetDimFieldEd.getText().length() == 0) {
            errorMessage += "Nëra reikðmës matmenyse!\n"; 
        }

        if (gdsPriceFieldEd.getText() == null || gdsPriceFieldEd.getText().length() == 0) {
            errorMessage += "Nëra reikðmës kainoje!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(gdsPriceFieldEd.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Yra reikðmë kainoje, bet ji netinkama (Tai turi bûti skaièius)!\n"; 
            }
        }

        if (gdsNoteFieldEd.getText() == null || gdsNoteFieldEd.getText().length() == 0) {
            errorMessage += "Nëra reikðmës pastaboje!\n"; 
        }

        if (gdsDateWrFieldEd.getText() == null || gdsDateWrFieldEd.getText().length() == 0) {
            errorMessage += "Nëra reikðmës datoje!\n";
        } else {
            if (!DateUtil.validDate(gdsDateWrFieldEd.getText())) {
                errorMessage += 
                		"Yra reikðmë datoje, bet neteisinga. Ji turi bûti data formatu dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
    		Alert alertErrUpdate = new Alert(AlertType.ERROR);
    		alertErrUpdate.setTitle("Neteisingi duomenys");//().getScene().getWindow();
    		//alertErrUpdate.getDialogPane().setStyle("cia rasomas stilius");  
    		// Get the Stage.
    		Stage stageErr = (Stage) alertErrUpdate.getDialogPane().getScene().getWindow();
    		// Add a custom icon.
    		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
    		alertErrUpdate.setHeaderText("Pataisykite duomenis");
    		alertErrUpdate.setContentText(errorMessage);
    		alertErrUpdate.showAndWait();

            return false;
        }
    }

}