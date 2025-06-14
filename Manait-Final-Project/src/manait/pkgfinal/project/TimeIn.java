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

/**
 *
 * @author yuanb
 */
public class TimeIn extends javax.swing.JFrame {

    LocalDateTime now = LocalDateTime.now();
    String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private boolean verify = false;

    public TimeIn() {
        initComponents();
    }

    void LogIn(String EmployeeID) {
        DB_Connection.init();
        Connection c = DB_Connection.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM employeeinfo Where employeeid = ?");
            ps.setString(1, EmployeeID);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Wrong Employee ID!");
            } else {
                ps = c.prepareStatement("SELECT * FROM employeeinfo Where employeeid = ?");
                ps.setString(1, EmployeeID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String Name = rs.getString("name");
                    String Department = rs.getString("department");

                    ps = c.prepareStatement("Insert into attendancelog (date, name, department, emplyoeeid, remarks) VALUES (?,?,?,?,?);");
                    ps.setString(1, formatted);
                    ps.setString(2, Name);
                    ps.setString(3, Department);
                    ps.setString(4, EmployeeID);
                    ps.setString(5, "In");
                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated < 0) {
                        JOptionPane.showMessageDialog(null, "ERROR: Please try again!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Log In successfully!");
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void LogOut(String EmployeeID) {
        DB_Connection.init();
        Connection c = DB_Connection.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM employeeinfo Where employeeid = ?");
            ps.setString(1, EmployeeID);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Wrong Employee ID!");
            } else {
                ps = c.prepareStatement("SELECT * FROM employeeinfo Where employeeid = ?");
                ps.setString(1, EmployeeID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String Name = rs.getString("name");
                    String Department = rs.getString("department");

                    ps = c.prepareStatement("Insert into attendancelog (date, name, department, emplyoeeid, remarks) VALUES (?,?,?,?,?);");
                    ps.setString(1, formatted);
                    ps.setString(2, Name);
                    ps.setString(3, Department);
                    ps.setString(4, EmployeeID);
                    ps.setString(5, "Out");
                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated < 0) {
                        JOptionPane.showMessageDialog(null, "ERROR: Please try again!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Log Out successfully!");
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void CEO(String Id) {
        DB_Connection.init();
        Connection c = DB_Connection.getConnection();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM accounts Where employeeid = ?");
            ps.setString(1, Id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String Name = rs.getString("department");

                if (Name.equals("CEO")) {
                    verify = true;
                    LogIn open = new LogIn(Id);
                    open.setVisible(true);
                    this.dispose();
                } else if (Name.equals("HR")) {
                    verify = true;
                    LogIn(Id);
                    LogIn open = new LogIn(Id);
                    open.setVisible(true);
                    this.dispose();
                }
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
        EmployID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(355, 250));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("ATTENDANCE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 260, -1));
        getContentPane().add(EmployID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 230, -1));

        jLabel2.setText("Employee ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jButton1.setText("Log In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jButton2.setText("Log Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String log = EmployID.getText().trim();
        CEO(log);
        if (log.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Incorrect ID!");
            return;
        } else {
            if (verify == false) {
                LogIn(log);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String log = EmployID.getText().trim();
        if (log.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Incorrect ID!");
            return;
        } else {
            LogOut(log);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TimeIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimeIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimeIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimeIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmployID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
