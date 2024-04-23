
package tp_3.cabinetdoctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deleteDoct extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost";
    static final String USER = "root";
    static final String PASS = "*Mb2003.sql2024";

    static Connection conn = null;
    private static final long serialVersionUID = 1L;
    String[] columns = new String[]{"Doctor Id", "Name", "Address", "Phone Number", "Speciality"};
    String data[][] = new String[8][5];

    JTable table = new JTable(data, columns);
    JPanel panel = new JPanel(new BorderLayout());
    JButton deleteBtn = new JButton("Delete");

    public deleteDoct() {

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setSize(700, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Doctor management");
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
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int row = selectedRows[i];
                String doctorId = model.getValueAt(row, 0).toString();
                
                PreparedStatement deleteAppointmentsStatement = conn.prepareStatement("DELETE FROM `CabinetDoct`.`Appointment` WHERE Patient_idPatient IN (SELECT idPatient FROM `CabinetDoct`.`Patient` WHERE Doctor_idDoctor = ?)");
                deleteAppointmentsStatement.setString(1, doctorId);
                deleteAppointmentsStatement.executeUpdate();
                
                PreparedStatement deletePatientsStatement = conn.prepareStatement("DELETE FROM `CabinetDoct`.`Patient` WHERE Doctor_idDoctor = ?");
                deletePatientsStatement.setString(1, doctorId);
                deletePatientsStatement.executeUpdate();
                
                PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM `CabinetDoct`.`Doctor` WHERE idDoctor = ?");
                deleteStatement.setString(1, doctorId);
                deleteStatement.executeUpdate();
                
                model.removeRow(row);
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

