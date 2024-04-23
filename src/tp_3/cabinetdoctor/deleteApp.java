
package tp_3.cabinetdoctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deleteApp extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    private static final long serialVersionUID = 1L;
    String[] columns = new String[]{"Appointment Id", "Date", "Type of consultation", "Patient Id", "Doctor Id"};
    String data[][] = new String[8][5];

    JTable table = new JTable(data, columns);
    JPanel panel = new JPanel(new BorderLayout());
    JButton deleteBtn = new JButton("Delete");

    public deleteApp() {

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM `CabinetDoct`.`Appointment`";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);
            int size = 0;
            while (res.next()) size++;
            res = stm.executeQuery(query);
            table.setSize(size, 5);
            int i = 0;
            String data[][] = new String[size][5];
            while (res.next()) {
                String Ida = res.getString("idAppointment");
                String datea = res.getString("Date_appoint");
                String tca = res.getString("TypeConsultation");
                String idp = res.getString("Patient_idPatient");
                String idd = res.getString("Doctor_idDoctor");
                data[i][0] = Ida + "";
                data[i][1] = datea;
                data[i][2] = tca + "";
                data[i][3] = idp;
                data[i][4] = idd + "";
                i++;
            }
            DefaultTableModel model = new DefaultTableModel(data, columns);
            table.setModel(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setSize(700, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Appointment management");
        panel.add(deleteBtn, BorderLayout.SOUTH);
        panel.add(new JScrollPane(table), BorderLayout.NORTH);
        add(panel);
        setVisible(true);
        deleteBtn.addActionListener(new DeleteListener(table,conn));
    }

    public class DeleteListener implements ActionListener {
    private final JTable table;
    private final Connection conn;

    public DeleteListener(JTable table, Connection conn) {
        this.table = table;
        this.conn = conn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            try {
                PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM `CabinetDoct`.`Appointment` WHERE idAppointment = ?");
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    String doctorId = model.getValueAt(row, 0).toString();
                    deleteStatement.setString(1, doctorId);
                    deleteStatement.executeUpdate();
                    model.removeRow(row);
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to delete row from database.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }
}
}
