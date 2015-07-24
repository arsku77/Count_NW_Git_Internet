package lt.arvidija.connections;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public final class ConnectMysql {
	
    public static DataSource getMySQLDataSource() {
        // Properties props = new Properties();
         //FileInputStream fis = null;
         MysqlDataSource mysqlDS = null;
        
             //fis = new FileInputStream("db.properties");
            // props.load(fis);
             mysqlDS = new MysqlDataSource();
             mysqlDS.setURL("jdbc:mysql://localhost:3306/arvidija");
             mysqlDS.setUser("root");
             mysqlDS.setPassword("196968");
         
         return mysqlDS;
     }

}
