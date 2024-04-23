
package tp_3.cabinetdoctor;

import javax.swing.*;
import java.sql.*;

public class CreatePat {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    static Statement stmt = null;
    public static Savepoint savepoint;
    public CreatePat(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql= "CREATE TABLE IF NOT EXISTS `CabinetDoct`.`Patient` (" +
            "  `idPatient` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `NomComplet` VARCHAR(45) NULL,\n" +
            "  `Adresse` VARCHAR(45) NULL,\n" +
            "  `NumTél` INT NULL,\n" +
            "  `Age` INT NULL,\n" +
            "  `Doctor_idDoctor` INT NOT NULL,\n" +
            "  PRIMARY KEY (`idPatient`),\n" +
            "  UNIQUE INDEX `idPatient_UNIQUE` (`idPatient` ASC) VISIBLE,\n" +
            "  INDEX `fk_Patient_Doctor_idx` (`Doctor_idDoctor` ASC) VISIBLE,\n" +
            "  CONSTRAINT `fk_Patient_Doctor`\n" +
            "    FOREIGN KEY (`Doctor_idDoctor`)\n" +
            "    REFERENCES `CabinetDoct`.`Doctor` (`idDoctor`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION)";
            
            stmt.executeUpdate(sql);

         conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public static void showPatientTableCreationDialog() {
        SwingUtilities.invokeLater(() -> {
            JFrame dialog = new JFrame("Table creation ...");
            JLabel l = new JLabel("-> Patient Table Created."); 
            dialog.add(l);
            dialog.setSize(300, 100);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }
}
