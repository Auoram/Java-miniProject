
package tp_3.cabinetdoctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateDoct extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;

    public UpdateDoct() {
        setTitle("Doctor management");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JTextField idD = new JTextField("Enter the ID of the doctor to update: ");
        JPanel textPanel = new JPanel(new BorderLayout());
        JTextField nameD = new JTextField("Enter Doctor new name: ");
        JTextField addD = new JTextField("Enter Doctor new address: ");
        JTextField telD = new JTextField("Enter Doctor new phone number: ");
        JTextField speD = new JTextField("Enter Doctor new speciality: ");

        JButton updateButton = new JButton("Update");
        JButton clearButton = new JButton("Clear");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        updateButton.addActionListener((ActionEvent e) -> {
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt1 = conn.prepareStatement("UPDATE `CabinetDoct`.`Doctor` SET NomDoctor = ?, Adresse = ?, NumTélDoct = ?, Spécialité = ? WHERE idDoctor = ?");
                stmt1.setString(1, nameD.getText());
                stmt1.setString(2, addD.getText());
                stmt1.setInt(3, Integer.parseInt(telD.getText()));
                stmt1.setString(4, speD.getText());
                stmt1.setInt(5, Integer.parseInt(idD.getText()));
                int i = stmt1.executeUpdate();
                if (i != 0) {
                    JFrame dialog = new JFrame("Table Updated ... ");
                    JLabel l = new JLabel("-> Doctor Table Updated.");
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
                nameD.setText("Enter Doctor new name: ");
                addD.setText("Enter Doctor new address: ");
                telD.setText("Enter Doctor new phone number: ");
                speD.setText("Enter Doctor new speciality: ");
                idD.setText("Enter the ID of the doctor to update: ");
                textPanel.setVisible(false);
                buttonPanel.setVisible(false);
            }
        });
        textPanel.add(nameD);
        textPanel.add(addD);
        textPanel.add(telD);
        textPanel.add(speD);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setVisible(false);
        buttonPanel.setVisible(false);

        JPanel SearchPanel = new JPanel(new BorderLayout());
        JLabel Searchlabel = new JLabel("Enter the ID of the doctor to update: ", JLabel.CENTER);
        JLabel SePlabel = new JLabel("                                                     ", JLabel.CENTER);

        JButton SearchButton = new JButton("Search");
        SearchPanel.add(Searchlabel, BorderLayout.NORTH);
        SearchPanel.add(idD, BorderLayout.CENTER);
        SearchPanel.add(SearchButton, BorderLayout.EAST);
        SearchPanel.add(SePlabel, BorderLayout.SOUTH);

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `CabinetDoct`.`Doctor` WHERE idDoctor = ?");
                    stmt.setInt(1, Integer.parseInt(idD.getText()));
                    ResultSet res = stmt.executeQuery();
                    if (res.next()) {
                        textPanel.setVisible(true);
                        buttonPanel.setVisible(true);
                        idD.setText(String.valueOf(res.getInt("idDoctor")));
                        nameD.setText(res.getString("NomDoctor"));
                        addD.setText(res.getString("Adresse"));
                        telD.setText(String.valueOf(res.getInt("NumTélDoct")));
                        speD.setText(res.getString("Spécialité"));
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

