package tp_3.cabinetdoctor;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

class ExportDoct extends JFrame {
        static final String DB_URL = "jdbc:mysql://localhost";
        static final String USER = "root";
        static final String PASS = "*Mb2003.sql2024";

        static Connection conn = null;
	private static final long serialVersionUID = 1L;
	String[] columns = new String[] {"Doctor Id", "Name", "Address", "Phone Number", "Speciality"};
	String data[][] = new String[8][5];

        JTable table = new JTable(data, columns);
        JPanel panel = new JPanel(new BorderLayout());
        JButton btn = new JButton("Export");

  public ExportDoct(){
	
	    try 
	    {
	       conn = DriverManager.getConnection(DB_URL, USER, PASS);     
	       String query = "SELECT * FROM `CabinetDoct`.`Doctor`";
	       Statement stm = conn.createStatement();
	       ResultSet res = stm.executeQuery(query);
	       int size = 0;
	       while (res.next()) size++;
	       res = stm.executeQuery(query);
	       table.setSize(size, 5);
	       int i = 0;
		   String data[][] = new String[size][5];
	       while (res.next()) {
	    	  String IdD = res.getString("idDoctor");
	          String name = res.getString("NomDoctor");
	          String address = res.getString("Adresse");
	          String tel = res.getString("NumTélDoct");
	          String specialite = res.getString("Spécialité");
	          data[i][0] = IdD + "";
	          data[i][1] = name;
	          data[i][2] = address + "";
	          data[i][3] = tel;
	          data[i][4] = specialite + "";
	          i++;
	        }
	       DefaultTableModel model = new DefaultTableModel(data, columns);
	       table.setModel(model);
	       table.setShowGrid(true);
	       table.setShowVerticalLines(true);
	       conn.close();
	      } catch(SQLException e) {
	        e.printStackTrace();
	      }  
	  
    setSize(700,200);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setTitle("Doctor management");
    panel.add(btn, BorderLayout.SOUTH);
    panel.add(new JScrollPane(table), BorderLayout.NORTH);
    add(panel);
    setVisible(true);
    btn.addActionListener(new MyListener());
  }

  public void export(JTable table, File file){
    try
    {
      TableModel m = table.getModel();
      FileWriter fw = new FileWriter(file);

      for(int i = 0; i < m.getColumnCount(); i++){
        fw.write(m.getColumnName(i) + "\t");
      }

      fw.write("\n");

      for(int i=0; i < m.getRowCount(); i++) {
        for(int j=0; j < m.getColumnCount(); j++) {
          fw.write(m.getValueAt(i,j)+"\t");
        }
        fw.write("\n");
      }

      fw.close();
    }
    catch(IOException e){ System.out.println(e); }
  }


  class MyListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == btn){
           JFileChooser fchoose = new JFileChooser();
           int option = fchoose.showSaveDialog(ExportDoct.this);
           if(option == JFileChooser.APPROVE_OPTION){
             String name = fchoose.getSelectedFile().getName(); 
             String path = fchoose.getSelectedFile().getParentFile().getPath();
             String file = path + "\\" + name + ".xls"; 
             export(table, new File(file));
           }
         }
      }
  }
}

