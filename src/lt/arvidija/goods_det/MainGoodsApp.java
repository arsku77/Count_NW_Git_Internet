package lt.arvidija.goods_det;

import lt.arvidija.connections.ConnectMysql;
import lt.arvidija.goods_det.model.GoodsDetail;
import lt.arvidija.goods_det.view.Goods_DetViewControl;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class MainGoodsApp extends Application {
/**
 * paraðome kintamàjá -> sàraðà, susietà su modelio klase, kuris bus duomenø pagrindas
 * fxml failo tableview lentelei uþpildyti ir þemiau uþpildome ðá sàraðà duomenimis
 *  ObservableList -> yra klasë, kuri atvaizduoja duomenis ir leidþia juos koreguoti
 */
	private ObservableList<GoodsDetail> goods_detData = FXCollections.observableArrayList();	
	/**
    * konstruktoriuje sukuriame objektus (kreipimosi metu-> ðie objektai apraðyti GoodsDetail klasëje, kuri yra modelis)  
	* Add some sample data-> pridedam duomenis, uþpildydami duomenis GoodsDetail klasëje
	* esanèius daiktus: this.gdsAutoID = new SimpleIntegerProperty(gdsAutoID);
	* pvz jei new GoodsDetail(2, 25, "Ruth", "Mueller") -> tai this.gdsAutoID 
	* vienas reiðkinys (o jø yra daug, pvz. helper, bean, name..)
	* - value- bus IntegerProperty [value: 2]
	*/
    public MainGoodsApp() {
    	DataSource ds = null;
    	Connection conn = null;
    	ResultSet rs = null;
    	ds = ConnectMysql.getMySQLDataSource();
    	try {
            conn = ds.getConnection();
            PreparedStatement stmt=conn.prepareStatement(" Select * from PrDet order by PrPav");
    //   PreparedStatement stmt=con.prepareStatement(" Select auto_idprd, Kod, PrPav, Matmuo from PrDet order by PrPav");
       rs = stmt.executeQuery();//ávykdom querá
      // ResultSetMetaData rsMeta = rs.getMetaData();
       //int colCount = rsMeta.getColumnCount();
       while(rs.next()){
           goods_detData.add(new GoodsDetail(rs.getInt("auto_idprd"), rs.getInt("Kod"), 
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
    public ObservableList<GoodsDetail> getGoods_detData() {
        return goods_detData;
    }
	/**
	 * verciu gavimas
	 */
    
	    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Prekës");
		this.primaryStage.getIcons().add(new Image("file:resources/images/Vizit.png"));//turi bût maþo formato 20x20piks
//		this.primaryStage.getIcons().add(new Image("file:resources/images/Vizit.ico"));
        initRootLayout();//paleidzia pgr groba
        showGoods_DetOverview();//paleidzia papildoma -> idetini groba
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGoodsApp.class.getResource("view/Goods_DetRoot.fxml"));
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
            loader.setLocation(MainGoodsApp.class.getResource("view/Goods_DetView.fxml"));
            AnchorPane goods_DetOverview = (AnchorPane) loader.load();

            // Set GoodsDetail overview into the center of root layout.
            rootLayout.setCenter(goods_DetOverview);//komanda -> ideda papildoma groba i pagrindini
            // Give the controller access to the main app.
            Goods_DetViewControl controller = loader.getController();
            controller.setMainGoodsApp(this);//surisam  pagrindinæ klasæ su rodymo kontroleriu nes rootLayout yra aprasytas auksciau
        	} catch (IOException e) {				
            e.printStackTrace();
        }
    }

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

    
    public static void main(String[] args) {
        launch(args);
    }
}