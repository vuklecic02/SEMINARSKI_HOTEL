/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import kontroleri.RecepcionerKontroler;
import model.TerminDezurstva;

/**
 *
 * @author vuk
 */
public class RecepcionerForma extends javax.swing.JFrame {

    /**
     * Creates new form RecepcionerForma
     */
    public RecepcionerForma() {
        initComponents();
        setTitle("Recepcioneri");
        this.setLocationRelativeTo(null);
        
    }

    public JTable getjTableRecepcioneri() {
        return jTableRecepcioneri;
    }

    public void setjTableRecepcioneri(JTable jTableRecepcioneri) {
        this.jTableRecepcioneri = jTableRecepcioneri;
    }

    public JTextField getjTextFieldPretraga() {
        return jTextFieldPretraga;
    }

    public JComboBox<TerminDezurstva> getjComboBoxTerminDezurstva() {
        return jComboBoxTerminDezurstva;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPretraga = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTerminDezurstva = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRecepcioneri = new javax.swing.JTable();
        jButtonDetalji = new javax.swing.JButton();
        jButtonNazad = new javax.swing.JButton();
        jButtonPretrazi = new javax.swing.JButton();
        jButtonOcistiFilter = new javax.swing.JButton();
        jButtonDeaktiviraj = new javax.swing.JButton();
        jButtonAktivacija = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ime i prezime:");

        jLabel2.setText("Termin dežurstva:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTerminDezurstva, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTerminDezurstva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jTableRecepcioneri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableRecepcioneri);

        jButtonDetalji.setText("Detalji");

        jButtonNazad.setText("Nazad");

        jButtonPretrazi.setText("Pretraži");

        jButtonOcistiFilter.setText("Očisti filter");

        jButtonDeaktiviraj.setText("Deaktiviraj nalog");

        jButtonAktivacija.setText("Aktiviraj nalog");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButtonPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButtonOcistiFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDetalji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNazad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonDeaktiviraj)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButtonAktivacija, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonPretrazi)
                            .addComponent(jButtonOcistiFilter)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButtonDetalji)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNazad)
                        .addGap(21, 21, 21)
                        .addComponent(jButtonDeaktiviraj)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAktivacija)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAktivacija;
    private javax.swing.JButton jButtonDeaktiviraj;
    private javax.swing.JButton jButtonDetalji;
    private javax.swing.JButton jButtonNazad;
    private javax.swing.JButton jButtonOcistiFilter;
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JComboBox<TerminDezurstva> jComboBoxTerminDezurstva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRecepcioneri;
    private javax.swing.JTextField jTextFieldPretraga;
    // End of variables declaration//GEN-END:variables

    public void pretraziAddActionListener(ActionListener actionListener) {
        jButtonPretrazi.addActionListener(actionListener);
    }

    public void ocistiAddActionListener(ActionListener actionListener) {
        jButtonOcistiFilter.addActionListener(actionListener);
    }

    public void nazadAddActionListener(ActionListener actionListener) {
        jButtonNazad.addActionListener(actionListener);
    }

    public void detaljiAddActionListener(ActionListener actionListener) {
        jButtonDetalji.addActionListener(actionListener);
    }

    public void deaktivirajNalogAddActionListener(ActionListener actionListener) {
        jButtonDeaktiviraj.addActionListener(actionListener);
    }

    public void aktivirajNalogAddActionListener(ActionListener actionListener) {
        jButtonAktivacija.addActionListener(actionListener);
    }

}
