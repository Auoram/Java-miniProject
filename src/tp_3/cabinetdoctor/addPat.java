
package tp_3.cabinetdoctor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class addPat extends JFrame{
	static final String DB_URL = "jdbc:mysql://localhost";
        static final String USER = "root";
        static final String PASS = "*Mb2003.sql2024";

        static Connection conn = null;
        
	public addPat() {
	  setTitle("Patient management");
	  JPanel mainPanel = new JPanel(new BorderLayout());
	  JLabel label = new JLabel("Add New Patients :", JLabel.CENTER);
	  JPanel textPanel = new JPanel(new BorderLayout());	
          JTextField namep = new JTextField("Enter Patient name: ");
          JTextField addp = new JTextField("Enter Patient address: ");
          JTextField telp = new JTextField("Enter Patient phone number: ");
          JTextField ap = new JTextField("Enter Patient age: ");
          JTextField idD = new JTextField("Enter Patient's doctor id: ");
          
      JButton addButton = new JButton("Add");
      JButton clearButton = new JButton("Clear");
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(addButton);
      buttonPanel.add(clearButton);
      addButton.addActionListener((ActionEvent e) -> {
          try
          {
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
              PreparedStatement stmt = conn.prepareStatement("INSERT INTO `CabinetDoct`.`Patient` (NomComplet, Adresse,NumTél,Age,Doctor_idDoctor) VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, namep.getText());
                stmt.setString(2, addp.getText());
                stmt.setInt(3, Integer.parseInt(telp.getText()));
                stmt.setInt(4, Integer.parseInt(ap.getText()));
                stmt.setInt(5, Integer.parseInt(idD.getText()));
              int i=stmt.executeUpdate();
              if(i!=0)
              {
                  JFrame dialog = new JFrame("Add Patients ... ");
                  JLabel l = new JLabel("-> Patient added.");
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
        namep.setText("");
        addp.setText("");
        telp.setText("");
        ap.setText("");
        idD.setText("");
        textPanel.setVisible(true);
        buttonPanel.setVisible(true);
      }
      });

        textPanel.add(namep);
        textPanel.add(addp);
        textPanel.add(telp);
        textPanel.add(ap);
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
