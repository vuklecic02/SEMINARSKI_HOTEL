/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacijaKlijent.Komunikacija;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import model.TipSobe;
import modelTabela.IznajmljivanjeModelTabela;
import modelTabela.MestoModelTabela;
import modelTabela.StavkeModelTabela;
import view.IznajmljivanjeForma;

/**
 *
 * @author vuk
 */
public class IznajmljivanjeKontroler {
    
    private final IznajmljivanjeForma izf;
    
    public IznajmljivanjeKontroler(IznajmljivanjeForma izf)
    {
        this.izf=izf;
        popuniTabelu();
        popuniComboBox();
        addActionListener();
        addMouseListener();
    }

    public IznajmljivanjeForma getIzf() {
        return izf;
    }

    private void popuniTabelu() {
        try {
            List<Iznajmljivanje> lista=Komunikacija.getInstance().vratiListuIznajmljivanja();
            System.out.println("Broj iznajmljivanja iz baze: " + lista.size());
            IznajmljivanjeModelTabela imt=new IznajmljivanjeModelTabela(lista);
            izf.getjTableIznajmljivanje().setModel(imt);
            izf.getjTableIznajmljivanje().setFillsViewportHeight(true);
            
            List<StavkaIznajmljivanja> stavke=new ArrayList<>();
            System.out.println("Broj stavki iznajmljivanja iz baze: " + stavke.size());
            StavkeModelTabela smt=new StavkeModelTabela(stavke);
            izf.getjTableStavke().setModel(smt);
            izf.getjTableStavke().setFillsViewportHeight(true);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(izf, ex.getMessage(), "Tabela mesta", JOptionPane.ERROR_MESSAGE);
        }     
    }

    private void popuniComboBox() {
        try {
            List<TipSobe> sobe = new ArrayList<>(List.of(TipSobe.values()));
            sobe.sort(Comparator.comparing(TipSobe::toString));
            for(TipSobe ts:sobe)
            {
                izf.getjComboBoxSobe().addItem(ts);
            }
             izf.getjComboBoxSobe().setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(izf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }    
    }

    public void otvoriFormu() {
        izf.setVisible(true);
    }

    private void addActionListener() {
        
    }

    private void addMouseListener() {
        izf.getjTableIznajmljivanje().addMouseListener(new MouseAdapter(){
           @Override
           public void mouseClicked(MouseEvent e)
           {
               int red=izf.getjTableIznajmljivanje().getSelectedRow();
               if(red!=-1)
               {
                   try {
                       IznajmljivanjeModelTabela imt=(IznajmljivanjeModelTabela) izf.getjTableIznajmljivanje().getModel();
                       Iznajmljivanje i=imt.getLista().get(red);
                       List<StavkaIznajmljivanja> stavke=Komunikacija.getInstance().vratiStavke(i.getIdIznajmljivanje());
                       StavkeModelTabela smt=new StavkeModelTabela(stavke);
                       izf.getjTableStavke().setModel(smt);
                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(izf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                   }
               }
           }
        });
    }
    
    
    
}
