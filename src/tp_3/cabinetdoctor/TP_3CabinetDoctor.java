package tp_3.cabinetdoctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;

public class TP_3CabinetDoctor extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel authenticationPanel;

    public TP_3CabinetDoctor() {
        setTitle("Authentication");
        setSize(500, 600);

        JPanel authenticationPanel = new JPanel();
        authenticationPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Enter your username:");
        authenticationPanel.add(usernameLabel);

        usernameField = new JTextField();
        authenticationPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Enter your password:");
        authenticationPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        authenticationPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        authenticationPanel.add(loginButton);

        getContentPane().add(authenticationPanel, BorderLayout.CENTER);
        

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            if (authenticate(username, password)== true) {
                Welcome page = new Welcome();
                getContentPane().add(page, BorderLayout.CENTER);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        }
    }
    
    public static boolean authenticate(String username, String password) {

    String DB_URL = "jdbc:mysql://localhost";
    String USER = "root";
    String PASS = "*Mb2003.sql2024";

    Scanner scanner = new Scanner(System.in);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            CreateDB db = new CreateDB();
            CreateUserTab.CreateAdmUserTab();
            CreateUserTab.AddAdmUser();

            String sql = "SELECT * FROM `CabinetDoct`.`AdmUser` WHERE Login=? AND Password=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) return true;
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TP_3CabinetDoctor());
    }
}

