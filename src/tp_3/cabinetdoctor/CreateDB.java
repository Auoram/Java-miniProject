
package tp_3.cabinetdoctor;

import java.sql.*;

public class CreateDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    static Statement stmt = null;
    
    public CreateDB (){
 
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE Database IF NOT EXISTS CabinetDoct");
        
         conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        }
}