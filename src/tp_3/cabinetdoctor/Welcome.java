
package tp_3.cabinetdoctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Welcome extends JPanel {

    
    public Welcome() {
        
     JFrame frame = new JFrame("Cabinet doctor management");
		      JPanel panel = new JPanel();
		      JMenuBar menu = new JMenuBar();
		      JMenu Doct = new JMenu("Manage Doctors");
		      JMenu Pat = new JMenu("Manage Patients");
		      JMenu App = new JMenu("Manage Appointment");
		      JMenuItem cpt = new JMenuItem("Create patient table");
                      cpt.addActionListener((ActionEvent e) -> {
                          CreatePat p1 = new CreatePat();
                          CreatePat.showPatientTableCreationDialog();
                      });
		      JMenuItem ap = new JMenuItem("Add patients");
                      ap.addActionListener((ActionEvent e) -> {
                          addPat p2 = new addPat();
                          p2.setLocationRelativeTo(null);
                          p2.setVisible(true);
                      });
		      JMenuItem inpf = new JMenuItem("Insert new patient into a file");
                      inpf.addActionListener((ActionEvent e) -> {
                          ExportPat p3 = new ExportPat();
                          p3.setLocationRelativeTo(null);
                          p3.setVisible(true);
                      });
                      JMenuItem up = new JMenuItem("Update patients");
                      up.addActionListener((ActionEvent e) -> {
                          UpdatePat p4 = new UpdatePat();
                          p4.setLocationRelativeTo(null);
                          p4.setVisible(true);
                      });
                      JMenuItem deP = new JMenuItem("Delete patients");
                      deP.addActionListener((ActionEvent e) -> {
                          deletePat p5 = new deletePat();
                          p5.setLocationRelativeTo(null);
                          p5.setVisible(true);
                      });
                      
		      JMenuItem cdt = new JMenuItem("Create Doctor table");	
                      cdt.addActionListener((ActionEvent e) -> {
                          CreateDoct d1 = new CreateDoct();
                          CreateDoct.showDoctorTableCreationDialog();
                      });
		      JMenuItem ad = new JMenuItem("Add Doctors");
                      ad.addActionListener((ActionEvent e) -> {
                          addDoct d2 = new addDoct();
                          d2.setLocationRelativeTo(null);
                          d2.setVisible(true);
                      });
		      JMenuItem indf = new JMenuItem("Insert new doctor into a file");
                      indf.addActionListener((ActionEvent e) -> {
                          ExportDoct d3 = new ExportDoct();
                          d3.setLocationRelativeTo(null);
                          d3.setVisible(true);
                      });
                      JMenuItem ud = new JMenuItem("Update doctors");
                      ud.addActionListener((ActionEvent e) -> {
                          UpdateDoct d4 = new UpdateDoct();
                          d4.setLocationRelativeTo(null);
                          d4.setVisible(true);
                      });
                      JMenuItem deD = new JMenuItem("Delete doctors");
                      deD.addActionListener((ActionEvent e) -> {
                          deleteDoct d5 = new deleteDoct();
                          d5.setLocationRelativeTo(null);
                          d5.setVisible(true);
                      });
                      
		      JMenuItem cat = new JMenuItem("Create Appointment table");
                      cat.addActionListener((ActionEvent e) -> {
                          CreateApp a1 = new CreateApp();
                          CreateApp.showAppTableCreationDialog();
                      });
		      JMenuItem aa = new JMenuItem("Add Appointments");
                      aa.addActionListener((ActionEvent e) -> {
                          addApp a2 = new addApp();
                          a2.setLocationRelativeTo(null);
                          a2.setVisible(true);
                      });
		      JMenuItem inaf = new JMenuItem("Insert new Appointment into a file");
                      inaf.addActionListener((ActionEvent e) -> {
                          ExportApp a3 = new ExportApp();
                          a3.setLocationRelativeTo(null);
                          a3.setVisible(true);
                      });
                      JMenuItem ua = new JMenuItem("Update Appointments");
                      ua.addActionListener((ActionEvent e) -> {
                          UpdateApp a4 = new UpdateApp();
                          a4.setLocationRelativeTo(null);
                          a4.setVisible(true);
                      });
                      JMenuItem deA = new JMenuItem("Delete Appointments");
                      deA.addActionListener((ActionEvent e) -> {
                          deleteApp a5 = new deleteApp();
                          a5.setLocationRelativeTo(null);
                          a5.setVisible(true);
                      });
		      Doct.add(cdt);
		      Doct.add(ad);
		      Doct.add(indf);
		      Doct.add(ud);
                      Doct.add(deD);
		      Pat.add(cpt);
		      Pat.add(ap);
		      Pat.add(inpf);
		      Pat.add(up);
                      Pat.add(deP);
                      App.add(cat);
		      App.add(aa);
		      App.add(inaf);
		      App.add(ua);
                      App.add(deA);
		      menu.add(Doct);
		      menu.add(Pat);
		      menu.add(App);
		      frame.add(menu, BorderLayout.NORTH);
                      Color brightBlue = new Color(102, 204, 255);
                      panel.setBackground(brightBlue);
                      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                      String[] phrases = {
                          "   ",
                          "   ",
                          "Java application",
                          "   ",
                          "to manage a cabinet-doctor database",
                          "   ",
                          "for the mini-project",
                      };
                      for (String phrase : phrases) {
                      JLabel label = new JLabel(phrase);
                      label.setForeground(Color.WHITE);
                      label.setAlignmentX(Component.CENTER_ALIGNMENT);
                      label.setFont(new Font("Arial", Font.BOLD, 20));
                      panel.add(label);
                      }
                      frame.add(panel, BorderLayout.CENTER);
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  frame.setSize(500, 600);
			  frame.setLocationRelativeTo(null);
			  frame.setVisible(true); 

	}
 }