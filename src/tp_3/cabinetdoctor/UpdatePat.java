
package tp_3.cabinetdoctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdatePat extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;

    public UpdatePat() {
        setTitle("Patient management");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JTextField idp = new JTextField("Enter the ID of the patient to update: ");
        JPanel textPanel = new JPanel(new BorderLayout());
        JTextField namep = new JTextField("Enter patient new name: ");
        JTextField addp = new JTextField("Enter patient new address: ");
        JTextField telp = new JTextField("Enter patient new phone number: ");
        JTextField ap = new JTextField("Enter patient new age: ");
        JTextField idD = new JTextField("Enter patient new doctor id: ");

        JButton updateButton = new JButton("Update");
        JButton clearButton = new JButton("Clear");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        updateButton.addActionListener((ActionEvent e) -> {
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt1 = conn.prepareStatement("UPDATE `CabinetDoct`.`Patient` SET NomComplet = ?, Adresse = ?, NumTél = ?, Age = ?, Doctor_idDoctor = ? WHERE idPatient = ?");
                stmt1.setString(1, namep.getText());
                stmt1.setString(2, addp.getText());
                stmt1.setInt(3, Integer.parseInt(telp.getText()));
                stmt1.setInt(4, Integer.parseInt(ap.getText()));
                stmt1.setInt(5, Integer.parseInt(idD.getText()));
                stmt1.setInt(6, Integer.parseInt(idp.getText()));
                int i = stmt1.executeUpdate();
                if (i != 0) {
                    JFrame dialog = new JFrame("Table Updated ... ");
                    JLabel l = new JLabel("-> Patient Table Updated.");
                    dialog.add(l);
                    dialog.setSize(400, 100);
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
                conn.close();
            } catch (Exception eE) {
                System.out.println(eE);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                namep.setText("Enter Doctor new name: ");
                addp.setText("Enter Doctor new address: ");
                telp.setText("Enter Doctor new phone number: ");
                ap.setText("Enter Doctor new age: ");
                idD.setText("Enter Doctor new doctor id: ");
                idp.setText("Enter the ID of the patient to update: ");
                textPanel.setVisible(false);
                buttonPanel.setVisible(false);
            }
        });
        textPanel.add(namep);
        textPanel.add(addp);
        textPanel.add(telp);
        textPanel.add(ap);
        textPanel.add(idD);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setVisible(false);
        buttonPanel.setVisible(false);

        JPanel SearchPanel = new JPanel(new BorderLayout());
        JLabel Searchlabel = new JLabel("Enter the ID of the patient to update: ", JLabel.CENTER);
        JLabel SePlabel = new JLabel("                                                     ", JLabel.CENTER);

        JButton SearchButton = new JButton("Search");
        SearchPanel.add(Searchlabel, BorderLayout.NORTH);
        SearchPanel.add(idp, BorderLayout.CENTER);
        SearchPanel.add(SearchButton, BorderLayout.EAST);
        SearchPanel.add(SePlabel, BorderLayout.SOUTH);

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `CabinetDoct`.`Patient` WHERE idPatient = ?");
                    stmt.setInt(1, Integer.parseInt(idp.getText()));
                    ResultSet res = stmt.executeQuery();
                    if (res.next()) {
                        textPanel.setVisible(true);
                        buttonPanel.setVisible(true);
                        idp.setText(String.valueOf(res.getInt("idPatient")));
                        namep.setText(res.getString("NomComplet"));
                        addp.setText(res.getString("Adresse"));
                        telp.setText(String.valueOf(res.getInt("NumTél")));
                        ap.setText(String.valueOf(res.getInt("Age")));
                        idD.setText(String.valueOf(res.getInt("Doctor_idDoctor")));
                    } else {
                        JFrame dialog = new JFrame("Error ... ");
                        JLabel l = new JLabel("->  Not Found");
                        dialog.add(l);
                        dialog.setSize(400, 100);
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
                    conn.close();
                } catch (Exception eE) {
                    System.out.println(eE);
                }
            }
        });

        mainPanel.add(SearchPanel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setVisible(true);

    }
}