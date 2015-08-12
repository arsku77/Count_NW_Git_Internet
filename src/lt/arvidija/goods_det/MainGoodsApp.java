package lt.arvidija.goods_det;

import lt.arvidija.connections.ConnectToBase;

//import java.util.Calendar;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;

import javafx.application.Application;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainGoodsApp extends Application {
    /**
     * parasome kintamàji -> sàrasà, susietà su modelio klase, kuris bus duomenu pagrindas
     * fxml failo tableview lentelei uzpildyti ir zemiau uzpildome si sàrasà duomenimis
     *  ObservableList -> yra klasë, kuri atvaizduoja duomenis ir leidzia juos koreguoti
     */
	// apsibreziam kolekcija - sarasa
   	private ObservableList<GoodsDetailModel> goods_detData = FXCollections.observableArrayList();	

   	// sàraso geteris -> i ji kreipiasi controleris, kai lentelei priskirtumw ta sarasa, 
   	// t.y goods_detTable.setItems(mainGoodsApp.getGoods_detData());
   	public ObservableList<GoodsDetailModel> getGoods_detData() {
    return goods_detData;
   		}

   	/**
     * konstruktoriuje sukuriame objektus (kreipimosi metu-> sie objektai aprasyti GoodsDetail klasëje, kuri yra modelis)  
 	* Add some sample data-> pridedam duomenis, uzpildydami duomenis GoodsDetail klasëje
 	* esancius daiktus: this.gdsAutoID = new SimpleIntegerProperty(gdsAutoID);
 	* pvz jei new GoodsDetail(2, 25, "Ruth", "Mueller") -> tai this.gdsAutoID 
 	* vienas reiskinys (o ju yra daug, pvz. helper, bean, name..)
 	* - value- bus IntegerProperty [value: 2]
 	*/

	public MainGoodsApp() {
	    ///////////////////////////////////////////////////////////////////////
	    ////*********duomenu uzkrovimo pradzia/////////////******************//
	    ///////////////////////////////////////////////////////////////////////
    	DataSource ds = null;
    	Connection conn = null;
    	ResultSet rs = null;
    	ds = ConnectToBase.getMySQLDataSource();
    	try {
            conn = ds.getConnection();
            PreparedStatement stmt=conn.prepareStatement(" Select * from PrDet order by PrPav");
    //   PreparedStatement stmt=con.prepareStatement(" Select auto_idprd, Kod, PrPav, Matmuo from PrDet order by PrPav");
       rs = stmt.executeQuery();//ivykdom queri
      // ResultSetMetaData rsMeta = rs.getMetaData();
       //int colCount = rsMeta.getColumnCount();
       while(rs.next()){
           goods_detData.add(new GoodsDetailModel(rs.getInt("auto_idprd"), rs.getInt("Kod"), 
        		   rs.getString("PrPav"), rs.getString("Matmuo"), rs.getString("pastab_apr"), 
        		   rs.getDouble("ParK"), rs.getDate("Date_Write")));
          // goods_detData.add(new GoodsDetail(rs.getInt("auto_idprd"), rs.getInt("Kod"), rs.getString("PrPav"), rs.getString("Matmuo")));
          	         }//rs.getDouble("ParK"), (LocalDate) rs.getObject("Date_Write")));
       /*
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
               } catch (SQLException e) {
        e.printStackTrace();
    }finally{
            try {
                if(rs != null) rs.close();
                //if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            	} catch (SQLException e) {
                e.printStackTrace();
            						}
    		}					
   }
    ///////////////////////////////////////////////////////////////////////
    ////*********duomenu uzkrovimo pabaiga/////////////******************//
    ///////////////////////////////////////////////////////////////////////
	/**
	 * pagrindinins paleidimas
	 */
    
	private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Prekës");
		this.primaryStage.getIcons().add(new Image("file:resources/images/Vizit.png"));//turi but mazo formato 20x20piks
        initRootLayout();//paleidzia pgr groba
        //rodytiGoods_DetOverview();
        showGoods_DetOverview();//paleidzia papildoma -> idetini groba
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGoodsApp.class.getResource("Goods_DetRoot.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the goods overview inside the root layout.
     */
    public void showGoods_DetOverview() {
        try {
            // Load GoodsDetail overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGoodsApp.class.getResource("Goods_DetView.fxml"));
            AnchorPane goods_DetOverview = (AnchorPane) loader.load();

            // Set GoodsDetail overview into the center of root layout.
            rootLayout.setCenter(goods_DetOverview);//komanda -> ideda papildoma groba i pagrindini
            // Give the controller access to the main app.
            Goods_DetViewControl controller = loader.getController();
            controller.setMainGoodsApp(this);//surisam  pagrindine klase su rodymo kontroleriu nes rootLayout yra aprasytas auksciau
        	} catch (IOException e) {				
            e.printStackTrace();
        }
    }
//*********************************************
    public void rodytiGoods_DetOverview() {
        try {
            // Load GoodsDetail overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGoodsApp.class.getResource("Goods_DetEditDialog.fxml"));
            AnchorPane goods_DetOverview = (AnchorPane) loader.load();
	        Stage dialogStageGDS = new Stage();
	        dialogStageGDS.setTitle("Edit Prekës");
	        dialogStageGDS.initModality(Modality.WINDOW_MODAL);
	        dialogStageGDS.initOwner(primaryStage);
	       // Scene scene = new Scene(page);
	      
            Goods_DetEditDialogControl controller = loader.getController();
            controller.setMainGoodsApp(this);//surisam  pagrindine klase su rodymo kontroleriu nes rootLayout yra aprasytas auksciau
            Scene scene = new Scene(goods_DetOverview);
            dialogStageGDS.setScene(scene);
            dialogStageGDS.showAndWait();

        	} catch (IOException e) {				
            e.printStackTrace();
        }
    }

//***************************
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the data as an observable list of GoodsDetail. 
     * @return
     */
	
	/**
	 * Opens a dialog to edit details for the specified goodsdetail. If the user
	 * clicks OK, the changes are saved into the provided goodsdetail object and
	 * true is returned.
	 * 
	 * @param goodsdetail the goodsdetail object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
    
	public boolean showGoodsDetailEditDialog(GoodsDetailModel goodsdetail) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainGoodsApp.class.getResource("Goods_DetEditDialog.fxml"));
	        AnchorPane goods_DetOverview = (AnchorPane) loader.load();
	        // Create the dialog Stage.
	        Stage dialogStageGDS = new Stage();
	        dialogStageGDS.setTitle("Edit prekæ");
	        dialogStageGDS.getIcons().add(new Image("file:resources/images/Vizit.png"));
	        dialogStageGDS.initModality(Modality.WINDOW_MODAL);
	        dialogStageGDS.initOwner(primaryStage);
	        Scene scene = new Scene(goods_DetOverview);
	        dialogStageGDS.setScene(scene);
	        // Set the person into the controller.
	        Goods_DetEditDialogControl controller = loader.getController();
	        controller.setDialogStage(dialogStageGDS);
	        controller.setGoodsDetailModel(goodsdetail);
			
	        // Show the dialog and wait until the user closes it
	        dialogStageGDS.showAndWait();

	        return controller.isOkClicked();
	        //return true;
			
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();

			Alert alertErrDelet = new Alert(AlertType.ERROR);
    		alertErrDelet.setTitle("Klaida paleidþiand Dialog");//().getScene().getWindow();
    		Stage stageErr = (Stage) alertErrDelet.getDialogPane().getScene().getWindow();
    		stageErr.getIcons().add(new Image("file:resources/images/Vizit.png"));
    		alertErrDelet.setHeaderText("Klaida paleidþiant Dialog koregavime");
    		alertErrDelet.setContentText(e.getMessage());
    		alertErrDelet.showAndWait();

			return false;
		}
	}

    
    public static void main(String[] args) {
        launch(args);
    }
}