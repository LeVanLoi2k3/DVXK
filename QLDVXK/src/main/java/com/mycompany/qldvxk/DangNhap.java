/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qldvxk;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author LOI
 */
public class DangNhap extends javax.swing.JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private UserManager userManager = new UserManager(); // Use your UserManager class
    
    /**
     * Creates new form DangNhap
     */
    public DangNhap() {
        setTitle("Đăng Nhập");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        initiComponents();
    }
    
    private void initiComponents() {
         // Tạo panel đăng nhập
        JPanel loginPanel = createLoginPanel();

        getContentPane().add(loginPanel, BorderLayout.CENTER);
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);
        panel.setBorder(null);

        // Thiết lập hình nền
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\LOI\\Documents\\NetBeansProjects\\QLDVXK\\src\\main\\java\\com\\mycompany\\qldvxk\\xekhach.jpg"));
        background.setBounds(0, 0, getWidth(), getHeight());
        panel.add(background);

        // Tiêu đề
        JLabel titleLabel = new JLabel("DỊCH VỤ QUẢN LÝ XE KHÁCH", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(0, 50, getWidth(), 30);
        background.add(titleLabel);

        // Panel chứa các trường nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(null);
        inputPanel.setOpaque(false);
        inputPanel.setBounds(50, 100, 600, 300);
        background.add(inputPanel);

        // Tên đăng nhập
        JTextField usernameField = new JTextField(15);
        JLabel usernameLabel = new JLabel("Tên Đăng Nhập:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(usernameLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(usernameField, gbc);

        // Mật khẩu
        JPasswordField passwordField = new JPasswordField(15);
        JLabel passwordLabel = new JLabel("Mật Khẩu:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(passwordLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(passwordField, gbc);

        // Nút đăng nhập
        JButton loginButton = new JButton("Đăng Nhập");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(60, 179, 113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 30));

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (userManager.authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                new GiaoDien().setVisible(true); // Open your main application window
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        });

        // Đặt nút đăng nhập trên cùng một hàng
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(5, 5, 5, 5);
        gbcButton.gridx = 1;
        gbcButton.gridy = 2;
        gbcButton.anchor = GridBagConstraints.CENTER;
        inputPanel.add(loginButton, gbcButton);

        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
