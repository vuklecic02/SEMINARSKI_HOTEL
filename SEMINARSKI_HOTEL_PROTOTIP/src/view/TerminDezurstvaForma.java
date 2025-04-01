/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;


/**
 *
 * @author vuk
 */
public class TerminDezurstvaForma extends javax.swing.JFrame {

    /**
     * Creates new form TerminDezurstvaForma
     */
    public TerminDezurstvaForma() {
        initComponents();
        setTitle("Termini dežurstva");
        this.setLocationRelativeTo(null);        
    }

    public JButton getjButtonDetalji() {
        return jButtonDetalji;
    }

    public JButton getjButtonKreiraj() {
        return jButtonKreiraj;
    }

    public JButton getjButtonOcisti() {
        return jButtonOcisti;
    }

    public JButton getjButtonPretrazi() {
        return jButtonPretrazi;
    }

    public JTextField getjTextFieldPretraga() {
        return jTextFieldPretraga;
    }

    public JButton getjButtonNazad() {
        return jButtonNazad;
    }

    public JTable getjTableTerminDez() {
        return jTableTerminDez;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTerminDez = new javax.swing.JTable();
        jButtonDetalji = new javax.swing.JButton();
        jButtonNazad = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonPretrazi = new javax.swing.JButton();
        jButtonOcisti = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPretraga = new javax.swing.JTextField();
        jButtonKreiraj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableTerminDez.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id", "smena"
            }
        ));
        jScrollPane1.setViewportView(jTableTerminDez);

        jButtonDetalji.setText("Detalji");

        jButtonNazad.setText("Nazad");

        jButtonPretrazi.setText("Pretraži");

        jButtonOcisti.setText("Očisti filter");

        jLabel1.setText("Smena:");

        jTextFieldPretraga.setText("HH-HH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPretraga)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 94, Short.MAX_VALUE)
                        .addComponent(jButtonOcisti, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPretrazi)
                    .addComponent(jButtonOcisti))
                .addContainerGap())
        );

        jButtonKreiraj.setText("Kreiraj");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonKreiraj, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonDetalji))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonDetalji)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNazad)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKreiraj))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalji;
    private javax.swing.JButton jButtonKreiraj;
    private javax.swing.JButton jButtonNazad;
    private javax.swing.JButton jButtonOcisti;
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTerminDez;
    private javax.swing.JTextField jTextFieldPretraga;
    // End of variables declaration//GEN-END:variables

    public void ocistiAddActionListener(ActionListener actionListener) {
        jButtonOcisti.addActionListener(actionListener);
    }

    public void nazadAddActionListener(ActionListener actionListener) {
        jButtonNazad.addActionListener(actionListener);
    }

    public void pretraziAddActionListener(ActionListener actionListener) {
        jButtonPretrazi.addActionListener(actionListener);
    }

    public void kreirajAddActionListener(ActionListener actionListener) {
        jButtonKreiraj.addActionListener(actionListener);
    }
}
