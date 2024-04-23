package tp_3.cabinetdoctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateApp extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;

    public UpdateApp() {
        setTitle("Appointment management");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JTextField ida = new JTextField("Enter the ID of the Appointment to update: ");
        JPanel textPanel = new JPanel(new BorderLayout());
        JTextField datea = new JTextField("Enter Appointment new date: ");
        JTextField tca = new JTextField("Enter Appointment new type of consultation: ");

        JButton updateButton = new JButton("Update");
        JButton clearButton = new JButton("Clear");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        updateButton.addActionListener((ActionEvent e) -> {
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt1 = conn.prepareStatement("UPDATE `CabinetDoct`.`Appointment` SET Date_appoint = ?, TypeConsultation = ? WHERE idAppointment = ?");
                stmt1.setString(1, datea.getText());
                stmt1.setString(2, tca.getText());
                stmt1.setInt(3, Integer.parseInt(ida.getText()));
                int i = stmt1.executeUpdate();
                if (i != 0) {
                    JFrame dialog = new JFrame("Table Updated ... ");
                    JLabel l = new JLabel("-> Appointment Table Updated.");
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
                datea.setText("Enter Appointment new date: ");
                tca.setText("Enter Appointment new type of consultation: ");
                ida.setText("Enter the ID of the Appointment to update: ");
                textPanel.setVisible(false);
                buttonPanel.setVisible(false);
            }
        });
        textPanel.add(datea);
        textPanel.add(tca);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setVisible(false);
        buttonPanel.setVisible(false);

        JPanel SearchPanel = new JPanel(new BorderLayout());
        JLabel Searchlabel = new JLabel("Enter the ID of the Appointment to update: ", JLabel.CENTER);
        JLabel SePlabel = new JLabel("                                                     ", JLabel.CENTER);

        JButton SearchButton = new JButton("Search");
        SearchPanel.add(Searchlabel, BorderLayout.NORTH);
        SearchPanel.add(ida, BorderLayout.CENTER);
        SearchPanel.add(SearchButton, BorderLayout.EAST);
        SearchPanel.add(SePlabel, BorderLayout.SOUTH);

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `CabinetDoct`.`Appointment` WHERE idAppointment = ?");
                    stmt.setInt(1, Integer.parseInt(ida.getText()));
                    ResultSet res = stmt.executeQuery();
                    if (res.next()) {
                        textPanel.setVisible(true);
                        buttonPanel.setVisible(true);
                        ida.setText(String.valueOf(res.getInt("idAppointment")));
                        datea.setText(res.getString("Date_appoint"));
                        tca.setText(res.getString("TypeConsultation"));
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
