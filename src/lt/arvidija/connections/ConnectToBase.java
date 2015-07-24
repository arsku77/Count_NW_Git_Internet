package lt.arvidija.connections;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import oracle.jdbc.pool.OracleDataSource;
/**
 * Connektoriu naudosime: vienas prisijungimas - vienai formai
 * atidarom formà su Load -> prisijungiam mysql,
 * uzdarom -> atsijungiam 
 * @author arunas
  */
public final class ConnectToBase {
	
    public static DataSource getMySQLDataSource() {
         //Properties props = new Properties();
        // FileInputStream fis = null;
         MysqlDataSource mysqlDS = null;
         	//try {
            // fis = new FileInputStream("resources/settings_nw/login.txt");
            // props.load(fis);//gauname duomenis is txt failiuko
             mysqlDS = new MysqlDataSource();
/*             mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
             mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
             mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
*/
             mysqlDS.setURL("jdbc:mysql://localhost:3306/arvidija");
             mysqlDS.setUser("root");
             mysqlDS.setPassword("196968");

             
         		/*} catch (IOException e) {
             e.printStackTrace();
         							}*/

         return mysqlDS;
     }
/*
    public static DataSource getOracleDataSource(){
        Properties props = new Properties();
        FileInputStream fis = null;
        OracleDataSource oracleDS = null;
        try {
            fis = new FileInputStream("resources/connect/db.properties");
            props.load(fis);
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
*/
    
    
    
}
