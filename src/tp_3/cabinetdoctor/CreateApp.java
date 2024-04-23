
package tp_3.cabinetdoctor;

import javax.swing.*;
import java.sql.*;

public class CreateApp {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    static Statement stmt = null;
    public static Savepoint savepoint;
    
    public CreateApp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql= "CREATE TABLE IF NOT EXISTS `cabinetdoct`.`Appointment` (\n" +
            "  `idAppointment` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `Date_appoint` DATE NULL,\n" +
            "  `TypeConsultation` VARCHAR(45) NULL,\n" +
            "  `Patient_idPatient` INT NOT NULL,\n" +
            "  `Doctor_idDoctor` INT NOT NULL,\n" +
            "  PRIMARY KEY (`idAppointment`),\n" +
            "  INDEX `fk_Appointment_Patient1_idx` (`Patient_idPatient` ASC) VISIBLE,\n" +
            "  INDEX `fk_Appointment_Doctor1_idx` (`Doctor_idDoctor` ASC) VISIBLE,\n" +
            "  CONSTRAINT `fk_Appointment_Patient1`\n" +
            "    FOREIGN KEY (`Patient_idPatient`)\n" +
            "    REFERENCES `cabinetdoct`.`Patient` (`idPatient`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `fk_Appointment_Doctor1`\n" +
            "    FOREIGN KEY (`Doctor_idDoctor`)\n" +
            "    REFERENCES `cabinetdoct`.`Doctor` (`idDoctor`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION)";
            
            stmt.executeUpdate(sql);

         conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public static void showAppTableCreationDialog() {
        SwingUtilities.invokeLater(() -> {
            JFrame dialog = new JFrame("Table creation ...");
            JLabel l = new JLabel("-> Appointment Table Created."); 
            dialog.add(l);
            dialog.setSize(300, 100);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }
}
