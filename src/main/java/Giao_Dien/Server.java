/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author nkiez
 */
public class Server extends javax.swing.JFrame {

    public Server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnListen = new javax.swing.JButton();
        BtnSent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtVBMaHoa = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtGiaiMa = new javax.swing.JTextArea();
        BtnGiaiMa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnListen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnListen.setText("Listen");
        BtnListen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListenActionPerformed(evt);
            }
        });
        getContentPane().add(BtnListen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 90, 40));

        BtnSent.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnSent.setText("Đếm và Gửi Số Lần Ký Tự Xuất Hiện Về");
        BtnSent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSentActionPerformed(evt);
            }
        });
        getContentPane().add(BtnSent, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 290, 40));

        TxtVBMaHoa.setEditable(false);
        TxtVBMaHoa.setColumns(20);
        TxtVBMaHoa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        TxtVBMaHoa.setRows(5);
        TxtVBMaHoa.setText("\nVăn Bản Nhận Được Từ Client");
        jScrollPane1.setViewportView(TxtVBMaHoa);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 390, 120));

        TxtGiaiMa.setEditable(false);
        TxtGiaiMa.setColumns(20);
        TxtGiaiMa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        TxtGiaiMa.setRows(5);
        TxtGiaiMa.setText("\n\nVăn Bản Sau Khi Được Giải Mã");
        jScrollPane2.setViewportView(TxtGiaiMa);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 390, 120));

        BtnGiaiMa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnGiaiMa.setText("Giải Mã");
        BtnGiaiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGiaiMaActionPerformed(evt);
            }
        });
        getContentPane().add(BtnGiaiMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 110, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Mã Hóa.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public int Key;
    public Socket client;
    private void BtnListenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListenActionPerformed
       //Nút Listen Để Mở Server Nhận Tin Từ Client
        try {
            //khai báo server
            ServerSocket server = new ServerSocket(6789);
            //<editor-fold desc=" Thread ">
            //Lệnh Thread Để Chương Trình Không Bị Treo
            Thread th = new Thread() {
                public void run() {
                    try {
                        while (true) {
                            client = server.accept();
                            JOptionPane.showMessageDialog(null,"Kết Nối Thành Công!");
                            DataInputStream input = new DataInputStream(client.getInputStream());

                            String data = input.readUTF();
                            Key = input.readInt();
                            JOptionPane.showMessageDialog(null, "Đã nhận được dữ liệu từ Client");
                            TxtVBMaHoa.setText(data);

                                                
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            };

            th.start();
            //</editor-fold>
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_BtnListenActionPerformed

    // <editor-fold desc="Giải Mã Caesar">  
    char mahoakt(char c, int k) {
        if (!Character.isLetter(c)) {
            return c;
        }
        return (char) ((((Character.toUpperCase(c) - 'A') + k) % 26 + 26) % 26 + 'A');
    }

    public String GiaiMa(String VB, int k) {
        String kq = "";
        for (int i = 0; i < VB.length(); i++) {
            kq += mahoakt(VB.charAt(i), k);
        }
        return kq;
    }
    // </editor-fold>  

    
    //<editor-fold desc=" Đếm Ký Tự ">
      public String DemKyTu(String str){
        int counter[] = new int[256];
        int len = str.length();
        String Tong = "";
        for (int i = 0; i < len; i++)
            counter[str.charAt(i)]++;
        char array[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
            array[i] = str.charAt(i);
            int flag = 0;
            String C="";
            for (int j = 0; j <= i; j++) {
                if (str.charAt(i) == array[j])
                    flag++;
            }
             if (flag == 1 && str.charAt(i) !=' ')                 
                 C = "\nSố lần xuất hiện của " + str.charAt(i)+ " trong chuỗi : " + counter[str.charAt(i)];
                 Tong = Tong + C ;               
        }        
        return Tong;
    }
    //</editor-fold>
      
      
    private void BtnSentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSentActionPerformed
        //Độ dài Ký Tự (Không Biết Đúng Hay Sai)      
        DemKyTu(TxtGiaiMa.getText());
        try {
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeUTF(DemKyTu(TxtGiaiMa.getText()));
            output.flush();
           
        } catch (Exception e) {
        }

    }//GEN-LAST:event_BtnSentActionPerformed

    private void BtnGiaiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGiaiMaActionPerformed
        TxtGiaiMa.setText(GiaiMa(TxtVBMaHoa.getText(), -Key));

    }//GEN-LAST:event_BtnGiaiMaActionPerformed

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGiaiMa;
    private javax.swing.JButton BtnListen;
    private javax.swing.JButton BtnSent;
    private javax.swing.JTextArea TxtGiaiMa;
    private javax.swing.JTextArea TxtVBMaHoa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
