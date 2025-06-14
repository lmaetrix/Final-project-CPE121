/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manait.pkgfinal.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yuanb
 */
public class HRFront extends javax.swing.JFrame {

    LocalDateTime now = LocalDateTime.now();
    String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    DefaultTableModel model;
    private boolean verify = false;

    public HRFront() {
        initComponents();
        model = (DefaultTableModel) Tables.getModel();
        model.setRowCount(0);
        Table();
    }

    private void SaveData(String name, String department, String contact, String employmentdate, String employeeid) {
        DB_Connection.init();
        try {
            Connection c = DB_Connection.getConnection();

            // 🔍 Step 1: Check if username already exists
            PreparedStatement checkUser = c.prepareStatement("SELECT * FROM employeeinfo WHERE employeeid = ?");
            checkUser.setString(1, employeeid);
            ResultSet rs = checkUser.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee ID already exists! Please choose another one.");
                return;
            } else {

                PreparedStatement insertAccount = c.prepareStatement(
                        "INSERT INTO employeeinfo (name, department, contact, employmentdate, employeeid ) VALUES (?, ?, ?, ?, ?)"
                );
                insertAccount.setString(1, name);
                insertAccount.setString(2, department);
                insertAccount.setString(3, contact);
                insertAccount.setString(4, formatted);
                insertAccount.setString(5, employeeid);
                insertAccount.executeUpdate();

                JOptionPane.showMessageDialog(null, "Account Successfully Created");
                verify = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // for debugging
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private void Account(String employeeid, String password, String name, String department) {
        DB_Connection.init();
        try {
            Connection c = DB_Connection.getConnection();
            PreparedStatement checkUser = c.prepareStatement("SELECT * FROM accounts WHERE employeeid = ?");
            checkUser.setString(1, employeeid);
            ResultSet rs = checkUser.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee ID already exists! Please choose another one.");
                return;
            } else {

                PreparedStatement insertAccount = c.prepareStatement(
                        "INSERT INTO accounts (employeeid, pasword, name, department) VALUES (?, ?, ?, ?)"
                );
                insertAccount.setString(1, employeeid);
                insertAccount.setString(2, password);
                insertAccount.setString(3, name);
                insertAccount.setString(4, department);
                insertAccount.executeUpdate();

                JOptionPane.showMessageDialog(null, "Account Successfully Created");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // for debugging
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    void Table() {
        model.setRowCount(0);
        DB_Connection.init();
        Connection c = DB_Connection.getConnection();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM employeeinfo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString("employeeid"), rs.getString("name"),
                    rs.getString("department"), rs.getString("contact"), rs.getString("employmentdate")});

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            Logger.getLogger(HRFront.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Updateinfo(String name, String department, String contact, String Id) {
        DB_Connection.init();
        try {

            Connection c = DB_Connection.getConnection();
            model.setRowCount(0);
            PreparedStatement ps = c.prepareStatement("UPDATE employeeinfo set name = ?, department = ?, contact = ? WHERE employeeid = ?;");
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, contact);
            ps.setString(4, Id);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
            } else {
                JOptionPane.showMessageDialog(null, "No record found.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // for debugging
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    void DeleteAccount(String Id) {
        DB_Connection.init();
        Connection c = DB_Connection.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM employeeinfo WHERE employeeid = ?");
            ps.setString(1, Id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Record Deleted.");

            } else {
                JOptionPane.showMessageDialog(null, "No record found.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tables = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        getID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        getName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        getDept = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        getContact = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(950, 550));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("HR DEPARTMENT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 335, -1));

        Tables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Employee id", "Name", "Department", "Contact", "Employment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tables);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 640, -1));

        jLabel2.setText("Employee Id");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        getContentPane().add(getID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 220, -1));

        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        getContentPane().add(getName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 220, -1));

        jLabel4.setText("Department");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        getDept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CEO", "HR", "Regular" }));
        getContentPane().add(getDept, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 220, -1));

        jLabel5.setText("Contact");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));
        getContentPane().add(getContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 220, -1));

        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jButton3.setText("Log Out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String EmID = getID.getText();
        String Ename = getName.getText();
        String Econt = getContact.getText();
        String EDept = getDept.getSelectedItem().toString();
        if (EmID.equals("") || Ename.equals("") || Econt.equals("") || EDept.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill the form completely!");
        } else if (EDept.equals("HR") || EDept.equals("CEO") || EDept.equals("MANAGER")) {
            String Password = JOptionPane.showInputDialog("Create Password");
            String Password2 = JOptionPane.showInputDialog("Confirm Password");
            if (Password.equals(Password2)) {
                SaveData(Ename, EDept, Econt, formatted, EmID);
                if (verify == true) {
                    Account(EmID, Password, Ename, EDept);
                }
                Table();

            } else {
                JOptionPane.showMessageDialog(null, "Password did not match!");
                return;
            }
        } else {
            SaveData(Ename, EDept, Econt, formatted, EmID);
            Table();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablesMouseClicked
        if (evt.getClickCount() == 2) {
            int selected = Tables.getSelectedRow();
            if (selected != -1) {
                String getIDs = Tables.getValueAt(selected, 0).toString();
                String name = Tables.getValueAt(selected, 1).toString();
                String getcont = Tables.getValueAt(selected, 3).toString();

                getID.setText(getIDs);
                getName.setText(name);
                getContact.setText(getcont);

            }
        }
    }//GEN-LAST:event_TablesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String EmID = getID.getText();
        String Ename = getName.getText();
        String Econt = getContact.getText();
        String EDept = getDept.getSelectedItem().toString();
        if (EmID.equals("") || Ename.equals("") || Econt.equals("") || EDept.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill the form completely!");
        } else {
            Updateinfo(Ename, EDept, Econt, EmID);
            Table();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TimeIn open = new TimeIn();
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String EmID = getID.getText();
        String EDept = getDept.getSelectedItem().toString();
        if (EmID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill the form completely!");
        } else {
            DeleteAccount(EmID);
            Table();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HRFront.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HRFront.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HRFront.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HRFront.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HRFront().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tables;
    private javax.swing.JTextField getContact;
    private javax.swing.JComboBox<String> getDept;
    private javax.swing.JTextField getID;
    private javax.swing.JTextField getName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
