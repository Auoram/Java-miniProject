
package tp_3.cabinetdoctor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class addApp extends JFrame{
	static final String DB_URL = "jdbc:mysql://localhost";
        static final String USER = "root";
        static final String PASS = "*Mb2003.sql2024";

        static Connection conn = null;
        
	public addApp () {
	  setTitle("Appointment management");
	  JPanel mainPanel = new JPanel(new BorderLayout());
	  JLabel label = new JLabel("Add New Patients :", JLabel.CENTER);
	  JPanel textPanel = new JPanel(new BorderLayout());	
          JTextField datea = new JTextField("Enter appointment date ( yyyy-mm-dd ) : ");
          JTextField tca = new JTextField("Enter appointment type of consultation ( checking,diagnosis,... ) : ");
          JTextField idp = new JTextField("Enter appointment's patient id : ");
          JTextField idD = new JTextField("Enter appointment's doctor id: ");
          
      JButton addButton = new JButton("Add");
      JButton clearButton = new JButton("Clear");
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(addButton);
      buttonPanel.add(clearButton);
      addButton.addActionListener((ActionEvent e) -> {
          try
          {
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
              PreparedStatement stmt = conn.prepareStatement("INSERT INTO `CabinetDoct`.`Appointment` (Date_appoint,TypeConsultation,Patient_idPatient,Doctor_idDoctor) VALUES (?, ?, ? ,?)");
                stmt.setString(1, datea.getText());
                stmt.setString(2, tca.getText());
                stmt.setInt(3, Integer.parseInt(idp.getText()));
                stmt.setInt(4, Integer.parseInt(idD.getText()));
              int i=stmt.executeUpdate();
              if(i!=0)
              {
                  JFrame dialog = new JFrame("Add Appointments ... ");
                  JLabel l = new JLabel("-> Appointment added.");
                  dialog.add(l);
                  dialog.setSize(300, 100);
                  dialog.setLocationRelativeTo(null);
                  dialog.setVisible(true);
              }
              conn.close();
          }  catch (Exception eE) {
              System.out.println(eE);
          }
          });
      clearButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        datea.setText("");
        tca.setText("");
        idp.setText("");
        idD.setText("");
        textPanel.setVisible(true);
        buttonPanel.setVisible(true);
      }
      });

        textPanel.add(datea);
        textPanel.add(tca);
        textPanel.add(idp);
        textPanel.add(idD);
      textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
      
      add(mainPanel, BorderLayout.CENTER);
	  mainPanel.add(label, BorderLayout.NORTH);
	  mainPanel.add(textPanel, BorderLayout.CENTER);
	  mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	  
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  setSize(500, 300);
	  setVisible(true); 
	}
}
