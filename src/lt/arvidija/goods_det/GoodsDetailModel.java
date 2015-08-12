package lt.arvidija.goods_det;

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
 * @author Marco Jakob (perdare Arunas Skucas)
 */
public class GoodsDetailModel {
	/**
	*apsirasome kintamuosius -> laukus, kurie bus fxml faile
	*/
    private final IntegerProperty gdsAutoID;// auto id
    private final IntegerProperty gdsID;// kodas

    private final StringProperty gdsName;//prekes detalios pvd
    private final StringProperty gdsDetDim;//matmuo
    
    private final StringProperty gdsNote;//pastaba
    private final SimpleDoubleProperty gdsPrice;//kaina
    private final ObjectProperty<LocalDate> gdsDateWr;//prekes irasymo data

    /**
     * Default constructor. -> jo prireikia, kai pridedame naujas vertes -> tusciam objektui susikurti
     */
    public GoodsDetailModel() {
        this(0, 0, "Default gds name", "Default gds dimension", null, 0.0, null);
    }

	/**
	 * Default constructor.
	 */
	
    /**
     * Constructor with some initial data.
     * 
     * @param gdsName
     * @param gdsDetDim
     *
     * konstruktoriuje inicijuojame -> sukuriame objektus
     */
    public GoodsDetailModel(int gdsAutoID, int gdsID, String gdsName, String gdsDetDim, 
    		String gdsNote, double gdsPrice, Date gdsDateWr) {
        this.gdsAutoID = new SimpleIntegerProperty(gdsAutoID);
        this.gdsID = new SimpleIntegerProperty(gdsID);
    	this.gdsName = new SimpleStringProperty(gdsName);
        this.gdsDetDim = new SimpleStringProperty(gdsDetDim);
        this.gdsNote = new SimpleStringProperty(gdsNote);
        this.gdsPrice = new SimpleDoubleProperty(gdsPrice);//gdsDateWr.getYear()

        if (gdsDateWr == null) {
        		this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        		} else {
        		this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(gdsDateWr.toLocalDate().getYear(), gdsDateWr.toLocalDate().getMonth(), gdsDateWr.toLocalDate().getDayOfMonth()));//int year = date.getYear();
        		}
        //this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));//int year = date.getYear();

        /**
         *  Some initial dummy data, just for convenient testing.
         *  Padarome fiktyvius fiksuotus duomenis
         */
        //this.gdsNote = new SimpleStringProperty("Apar==yrrzsymas");
        //this.gdsPrice = new SimpleDoubleProperty(1234);
        //this.gdsDateWr = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }
    /**
    * geteriai, seteriai (skirti objekto savybems nuskaityti - get, ir savybems pakeisti - set)
    * jie butini -> kitaip nesukuria objekto su new 
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
/////////////////////////////////////////////////////
    public int getGdsID() {
        return gdsID.get();
    }
    public void setGdsID(int gdsID) {
        this.gdsID.set(gdsID);
    }
    public IntegerProperty gdsIDProperty() {
        return gdsID;
    }
/////////////////////////////////////////////////////
    public String getGdsName() {
        return gdsName.get();
    }
    public void setGdsName(String gdsName) {
        this.gdsName.set(gdsName);
    }
    public StringProperty gdsNameProperty() {
        return gdsName;
    }
/////////////////////////////////////////////////////
    public String getGdsDetDim() {
        return gdsDetDim.get();
    }
    public void setGdsDetDim(String gdsDetDim) {
        this.gdsDetDim.set(gdsDetDim);
    }
    public StringProperty gdsDetDimProperty() {
        return gdsDetDim;
    }
/////////////////////////////////////////////////////
    public double getGdsPrice() {
        return gdsPrice.get();
    }
    public void setGdsPrice(double gdsPrice) {
        this.gdsPrice.set(gdsPrice);
    }
    public SimpleDoubleProperty gdsPriceProperty() {
        return gdsPrice;
    }
/////////////////////////////////////////////////////
    public LocalDate getGdsDateWr() {
        return gdsDateWr.get();
    }
    public void setGdsDateWr(LocalDate gdsDateWr) {
        this.gdsDateWr.set(gdsDateWr);
    }
    public ObjectProperty<LocalDate> gdsDateWrProperty() {
        return gdsDateWr;
    }
/////////////////////////////////////////////////////
    public String getGdsNote() {
    	return gdsNote.get();
    }
    public void setGdsNote(String gdsNote) {
    	this.gdsNote.set(gdsNote);
    }
    public StringProperty gdsNoteProperty() {
    	return gdsNote;
    }
/////////////////////////////////////////////////////
}