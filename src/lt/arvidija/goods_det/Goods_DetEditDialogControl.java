package lt.arvidija.goods_det;

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


    private Stage dialogStage;
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
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the goodsdetail to be edited in the dialog.
     * 
     * @param goodsdetail
     */
    public void setGoodsDetailsEdit(GoodsDetailModel goodsdetail) {
        this.goodsdetail = goodsdetail;
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
    private void handleOk() {
        if (isInputValid()) {
            goodsdetail.setGdsID(Integer.parseInt(gdsIDFieldEd.getText()));
        	goodsdetail.setGdsName(gdsNameFieldEd.getText());
            goodsdetail.setGdsDetDim(gdsDetDimFieldEd.getText());
            goodsdetail.setGdsPrice(Double.parseDouble(gdsPriceFieldEd.getText()));
            goodsdetail.setGdsNote(gdsNoteFieldEd.getText());
            goodsdetail.setGdsDateWr(DateUtil.parse(gdsDateWrFieldEd.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
/////////////////////////////////////////////////////////////////////////////////////////        
        if (gdsIDFieldEd.getText() == null || gdsIDFieldEd.getText().length() == 0) {
            errorMessage += "N�ra reik�m�s kode!\n"; 
        } else {
            try {
                Integer.parseInt(gdsIDFieldEd.getText());
            } catch (NumberFormatException e) {
                errorMessage += 
                		"Yra reik�m� kode, bet ji netinkama (Tai turi b�ti skai�ius be kablelio)!\n"; 
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////
        if (gdsNameFieldEd.getText() == null || gdsNameFieldEd.getText().length() == 0) {
            errorMessage += "N�ra reik�m�s pavadinime!\n"; 
        }
        if (gdsDetDimFieldEd.getText() == null || gdsDetDimFieldEd.getText().length() == 0) {
            errorMessage += "N�ra reik�m�s matmenyse!\n"; 
        }

        if (gdsPriceFieldEd.getText() == null || gdsPriceFieldEd.getText().length() == 0) {
            errorMessage += "N�ra reik�m�s kainoje!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(gdsPriceFieldEd.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Yra reik�m� kainoje, bet ji netinkama (Tai turi b�ti skai�ius)!\n"; 
            }
        }

        if (gdsNoteFieldEd.getText() == null || gdsNoteFieldEd.getText().length() == 0) {
            errorMessage += "N�ra reik�m�s pastaboje!\n"; 
        }

        if (gdsDateWrFieldEd.getText() == null || gdsDateWrFieldEd.getText().length() == 0) {
            errorMessage += "N�ra reik�m�s datoje!\n";
        } else {
            if (!DateUtil.validDate(gdsDateWrFieldEd.getText())) {
                errorMessage += 
                		"Yra reik�m� datoje, bet neteisinga. Ji turi b�ti data formatu dd.mm.yyyy!\n";
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