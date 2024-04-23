
package tp_3.cabinetdoctor;

import java.sql.*;

public class CreateUserTab {

    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    static Statement stmt = null;

    
    public static void CreateAdmUserTab (){
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String  sql="CREATE TABLE IF NOT EXISTS `CabinetDoct`.`AdmUser` (\n" +
            "  `idU` INT NOT NULL,\n" +
            "  `Login` VARCHAR(45) NULL,\n" +
            "  `Password` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`idU`))";
            
            stmt.executeUpdate(sql);

         conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        }
    
    public static void AddAdmUser(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "INSERT IGNORE INTO `CabinetDoct`.`AdmUser` (idU, Login, Password)" +
                   "VALUES (1, 'Manager', 'Hello123')";		
         stmt.executeUpdate(sql);
         
         sql = "INSERT IGNORE INTO `CabinetDoct`.`AdmUser` (idU, Login, Password)" +
                   "VALUES (2, 'Editor', 'Hi456')";
         stmt.executeUpdate(sql);
         conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}