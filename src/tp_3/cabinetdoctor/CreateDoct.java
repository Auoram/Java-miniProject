
package tp_3.cabinetdoctor;

import javax.swing.*;
import java.sql.*;

public class CreateDoct extends JFrame{
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    static Statement stmt = null;
    public static Savepoint savepoint;
    
    public  CreateDoct(){
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql= "CREATE TABLE IF NOT EXISTS `CabinetDoct`.`Doctor` " +
                       "(idDoctor INT NOT NULL AUTO_INCREMENT, " +
                       " NomDoctor VARCHAR(45), " + 
                       " Adresse VARCHAR(45), " + 
                       " NumTélDoct INT, " + 
                       "Spécialité VARCHAR(45),"+
                       " PRIMARY KEY (idDoctor))";
            
            stmt.executeUpdate(sql);
         conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public static void showDoctorTableCreationDialog() {
        SwingUtilities.invokeLater(() -> {
            JFrame dialog = new JFrame("Table creation ...");
            JLabel l = new JLabel("-> Doctor Table Created."); 
            dialog.add(l);
            dialog.setSize(300, 100);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }
}
