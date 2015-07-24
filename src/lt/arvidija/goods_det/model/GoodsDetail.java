package lt.arvidija.goods_det.model;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Model class for a GoodsDetail.
 *
 * @author Marco Jakob (perdarë Arûnas Skuèas)
 */
public class GoodsDetail {
	/**
	*apsiraðome kintamuosius -> laukus, kurie bus fxml faile
	*/
    private final IntegerProperty gdsAutoID;// auto id
    private final IntegerProperty gdsID;// kodas

    private final StringProperty gdsName;//prekes detalios pvd
    private final StringProperty gdsDetDim;//matmuo
    
    private final StringProperty gdsNote;//pastaba
    private final SimpleDoubleProperty gdsPrice;//kaina
    private final ObjectProperty<LocalDate> gdsDateWr;//prekes irasymo data

    /**
     * Default constructor. -> jo gali ir nebûti, nes pagal tylëjimà ir taip jo vertës nulinës
     */
   /* public GoodsDetail() {
        this(0, 0, null, null);
    }*/

    /**
     * Constructor with some initial data.
     * 
     * @param gdsName
     * @param gdsDetDim
     *
     * konstruktoriuje inicijuojame -> sukuriame objektus
     */
    public GoodsDetail(int gdsAutoID, int gdsID, String gdsName, String gdsDetDim, 
    		String gdsNote, double gdsPrice, Date gdsDateWr) {
        this.gdsAutoID = new SimpleIntegerProperty(gdsAutoID);
        this.gdsID = new SimpleIntegerProperty(gdsID);
    	this.gdsName = new SimpleStringProperty(gdsName);
        this.gdsDetDim = new SimpleStringProperty(gdsDetDim);
        this.gdsNote = new SimpleStringProperty(gdsNote);
        this.gdsPrice = new SimpleDoubleProperty(gdsPrice);//gdsDateWr.getYear()
        this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(gdsDateWr.toLocalDate().getYear(), gdsDateWr.toLocalDate().getMonth(), gdsDateWr.toLocalDate().getDayOfMonth()));//int year = date.getYear();
       // this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));//int year = date.getYear();

        /**
         *  Some initial dummy data, just for convenient testing.
         *  Padarome fiktyvius fiksuotus duomenis
         */
        //this.gdsNote = new SimpleStringProperty("Apar==yrrþðymas");
        //this.gdsPrice = new SimpleDoubleProperty(1234);
        //this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }
    /**
    * geteriai, seteriai (skirti objekto savybëms nuskaityti - get, ir savybëms pakeisti - set)
    * jie bûtini
    * ir propertys
    */
    
    public int getGdsAutoID() {
        return gdsAutoID.get();
    }
    public void setGdsAutoID(int gdsAutoID) {
        this.gdsAutoID.set(gdsAutoID);
    }
    public IntegerProperty gdsAutoIDProperty() {
        return gdsAutoID;
    }

    public int getGdsID() {
        return gdsID.get();
    }
    public void setGdsID(int gdsID) {
        this.gdsID.set(gdsID);
    }
    public IntegerProperty gdsIDProperty() {
        return gdsID;
    }
   
    public String getGdsName() {
        return gdsName.get();
    }
    public void setGdsName(String gdsName) {
        this.gdsName.set(gdsName);
    }
    public StringProperty gdsNameProperty() {
        return gdsName;
    }

    public String getGdsDetDim() {
        return gdsDetDim.get();
    }
    public void setGdsDetDim(String gdsDetDim) {
        this.gdsDetDim.set(gdsDetDim);
    }
    public StringProperty gdsDetDimProperty() {
        return gdsDetDim;
    }
    
    public String getGdsNote() {
        return gdsNote.get();
    }
    public void setGdsNote(String gdsNote) {
        this.gdsNote.set(gdsNote);
    }
    public StringProperty gdsNoteProperty() {
        return gdsNote;
    }
  
    
    public double getGdsPrice() {
        return gdsPrice.get();
    }
    public void setGdsPrice(double gdsPrice) {
        this.gdsPrice.set(gdsPrice);
    }
    public SimpleDoubleProperty gdsPriceProperty() {
        return gdsPrice;
    }

    public LocalDate getGdsDateWr() {
        return gdsDateWr.get();
    }
    public void setGdsDateWr(LocalDate gdsDateWr) {
        this.gdsDateWr.set(gdsDateWr);
    }
    public ObjectProperty<LocalDate> gdsDateWrProperty() {
        return gdsDateWr;
    }
}