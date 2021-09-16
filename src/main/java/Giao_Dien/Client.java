/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Client extends javax.swing.JFrame {

    public Client() {
        initComponents();         
    }    

    // <editor-fold desc="Mã Hóa Caesar">  
    char mahoakt(char c,int k){
        if (!Character.isLetter(c)) {
            return c;
        }
        return (char) ((((Character.toUpperCase(c)-'A')+k)%26+26)%26+'A');
    }
    
    public String MaHoa(String VB, int k) {
       String kq="";
        for (int i = 0; i < VB.length(); i++) {
            kq+=mahoakt(VB.charAt(i), k);
        }
        return kq;        
    }
    // </editor-fold>  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLBKEY = new javax.swing.JLabel();
        JLBVANBAN = new javax.swing.JLabel();
        JLBSauMH = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtVanBan = new javax.swing.JTextArea();
        TxtKey = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtSauMH = new javax.swing.JTextArea();
        BtnMaHoa = new javax.swing.JButton();
        BtnGui = new javax.swing.JButton();
        BtnNhan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLBKEY.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        JLBKEY.setText("Key");
        jPanel1.add(JLBKEY, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 260, 40));

        JLBVANBAN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        JLBVANBAN.setText("Văn Bản Cần Mã Hóa");
        jPanel1.add(JLBVANBAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 260, 40));

        JLBSauMH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        JLBSauMH.setText("Văn Bản Sau Khi Mã Hóa");
        jPanel1.add(JLBSauMH, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 260, 40));

        TxtVanBan.setColumns(20);
        TxtVanBan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtVanBan.setRows(5);
        TxtVanBan.setBorder(null);
        jScrollPane1.setViewportView(TxtVanBan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 380, 130));

        TxtKey.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel1.add(TxtKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 370, 50));

        TxtSauMH.setColumns(20);
        TxtSauMH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtSauMH.setRows(5);
        TxtSauMH.setBorder(null);
        jScrollPane2.setViewportView(TxtSauMH);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 380, 130));

        BtnMaHoa.setText("Mã Hóa");
        BtnMaHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaHoaActionPerformed(evt);
            }
        });
        jPanel1.add(BtnMaHoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        BtnGui.setText("Gửi");
        BtnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuiActionPerformed(evt);
            }
        });
        jPanel1.add(BtnGui, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 70, -1));

        BtnNhan.setText("Nhận số ký tự");
        BtnNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNhanActionPerformed(evt);
            }
        });
        jPanel1.add(BtnNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnMaHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaHoaActionPerformed
       TxtSauMH.setText( MaHoa(TxtVanBan.getText(), Integer.parseInt(TxtKey.getText())));
    }//GEN-LAST:event_BtnMaHoaActionPerformed
    Socket client;
    private void BtnGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuiActionPerformed
        //Gửi Văn Bản Đã Mã Hóa
        try { 
           client = new Socket("localhost", 6789);
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            
            output.writeUTF(TxtSauMH.getText());
            output.writeInt(Integer.parseInt(TxtKey.getText()));
            output.flush();
            
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_BtnGuiActionPerformed

    private void BtnNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNhanActionPerformed
        try {
            DataInputStream input = new DataInputStream(client.getInputStream());
            JOptionPane.showMessageDialog(null, "Độ Dài Ban Đầu Của Ký Tự Là :"+input.readInt());
        } catch (Exception e) {
        }
      
    }//GEN-LAST:event_BtnNhanActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGui;
    private javax.swing.JButton BtnMaHoa;
    private javax.swing.JButton BtnNhan;
    private javax.swing.JLabel JLBKEY;
    private javax.swing.JLabel JLBSauMH;
    private javax.swing.JLabel JLBVANBAN;
    private javax.swing.JTextField TxtKey;
    private javax.swing.JTextArea TxtSauMH;
    private javax.swing.JTextArea TxtVanBan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
