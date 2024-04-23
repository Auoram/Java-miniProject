
package tp_3.cabinetdoctor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class addDoct extends JFrame{
	static final String DB_URL = "jdbc:mysql://localhost";
        static final String USER = "root";
        static final String PASS = "*Mb2003.sql2024";

        static Connection conn = null;
        
	public addDoct() {
	  setTitle("Doctor management");
	  JPanel mainPanel = new JPanel(new BorderLayout());
	  JLabel label = new JLabel("Add New Doctors :", JLabel.CENTER);
	  JPanel textPanel = new JPanel(new BorderLayout());	
          JTextField nameD = new JTextField("Enter Doctor name: ");
          JTextField addD = new JTextField("Enter Doctor address: ");
          JTextField telD = new JTextField("Enter Doctor phone number: ");
          JTextField speD = new JTextField("Enter Doctor speciality: ");
          
      JButton addButton = new JButton("Add");
      JButton clearButton = new JButton("Clear");
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(addButton);
      buttonPanel.add(clearButton);
      addButton.addActionListener((ActionEvent e) -> {
          try
          {
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
              PreparedStatement stmt = conn.prepareStatement("INSERT INTO `CabinetDoct`.`Doctor` ( NomDoctor, Adresse,NumTélDoct,Spécialité) VALUES (?, ?, ?, ?)");
                stmt.setString(1, nameD.getText());
                stmt.setString(2, addD.getText());
                stmt.setInt(3, Integer.parseInt(telD.getText()));
                stmt.setString(4, speD.getText());
                
              int i=stmt.executeUpdate();
              if(i!=0)
              {
                  JFrame dialog = new JFrame("Add Doctors ... ");
                  JLabel l = new JLabel("-> Doctor added.");
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
        nameD.setText("");
        addD.setText("");
        telD.setText("");
        speD.setText("");
        textPanel.setVisible(true);
        buttonPanel.setVisible(true);
      }
      });

        textPanel.add(nameD);
        textPanel.add(addD);
        textPanel.add(telD);
        textPanel.add(speD);
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
