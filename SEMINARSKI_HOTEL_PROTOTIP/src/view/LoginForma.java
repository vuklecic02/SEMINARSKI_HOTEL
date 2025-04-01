/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import glavniKontroler.GlavniKontroler;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Recepcioner;

/**
 *
 * @author vuk
 */
public class LoginForma extends javax.swing.JFrame {

    /**
     * Creates new form LoginForma
     */
    public LoginForma() {
        initComponents();
        setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public JButton getjButtonOdustani() {
        return jButtonOdustani;
    }

    public void setjButtonOdustani(JButton jButtonOdustani) {
        this.jButtonOdustani = jButtonOdustani;
    }

    public JButton getjButtonRegistrujSe() {
        return jButtonRegistrujSe;
    }

    public void setjButtonRegistrujSe(JButton jButtonRegistrujSe) {
        this.jButtonRegistrujSe = jButtonRegistrujSe;
    }

    public JButton getjButtonUlogujSe1() {
        return jButtonUlogujSe1;
    }

    public void setjButtonUlogujSe1(JButton jButtonUlogujSe1) {
        this.jButtonUlogujSe1 = jButtonUlogujSe1;
    }

    public JPasswordField getjPasswordFieldSifra() {
        return jPasswordFieldSifra;
    }

    public void setjPasswordFieldSifra(JPasswordField jPasswordFieldSifra) {
        this.jPasswordFieldSifra = jPasswordFieldSifra;
    }

    public JTextField getjTextFieldUsername() {
        return jTextFieldUsername;
    }

    public void setjTextFieldUsername(JTextField jTextFieldUsername) {
        this.jTextFieldUsername = jTextFieldUsername;
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
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jButtonOdustani = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonRegistrujSe = new javax.swing.JButton();
        jButtonUlogujSe1 = new javax.swing.JButton();
        jPasswordFieldSifra = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jTextFieldUsername.setText("vuklecic");

        jButtonOdustani.setText("Odustani");
        jButtonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdustaniActionPerformed(evt);
            }
        });

        jLabel3.setText("Nemate nalog?");

        jButtonRegistrujSe.setText("Registruj se");
        jButtonRegistrujSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrujSeActionPerformed(evt);
            }
        });

        jButtonUlogujSe1.setText("Prijavi se");
        jButtonUlogujSe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUlogujSe1ActionPerformed(evt);
            }
        });

        jPasswordFieldSifra.setText("vuk1234");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonRegistrujSe, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                .addComponent(jPasswordFieldSifra))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonUlogujSe1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOdustani, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordFieldSifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUlogujSe1)
                    .addComponent(jButtonOdustani))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonRegistrujSe))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrujSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrujSeActionPerformed
//        this.dispose();
//        GlavniKontroler.getInstance().otvoriFormuZaRegistraciju();
         
    }//GEN-LAST:event_jButtonRegistrujSeActionPerformed

    private void jButtonOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdustaniActionPerformed
//        int rezultat=JOptionPane.showConfirmDialog(null, "Da li odustajete?","Potvrda",JOptionPane.YES_NO_OPTION);
//        if(rezultat==JOptionPane.YES_OPTION)
//            this.dispose();
    }//GEN-LAST:event_jButtonOdustaniActionPerformed

    
    private void jButtonUlogujSe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUlogujSe1ActionPerformed
        /*try
        {
            String username=jTextFieldUsername.getText();
            String sifra=String.valueOf(jPasswordFieldSifra.getText());
            System.out.println(username+" "+sifra);
            Recepcioner recepcioner=Controller.getInstance().login(username,sifra);
            JOptionPane.showMessageDialog(this, recepcioner.getIme() + " logged in!", "Login", JOptionPane.INFORMATION_MESSAGE);            
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Login unsucessful!\n" + ex.getMessage(), "Login", JOptionPane.ERROR_MESSAGE);
        }*/                  
    }//GEN-LAST:event_jButtonUlogujSe1ActionPerformed
    
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonRegistrujSe;
    private javax.swing.JButton jButtonUlogujSe1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordFieldSifra;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

    public void loginAddActionListener(ActionListener actionListener) {
        jButtonUlogujSe1.addActionListener(actionListener);
    }

    public void odustaniAddActionListener(ActionListener actionListener) {
        jButtonOdustani.addActionListener(actionListener);
    }

    public void registrujSeAddActionListener(ActionListener actionListener) {
        jButtonRegistrujSe.addActionListener(actionListener);
    }
}
