/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Iznajmljivanje;

/**
 *
 * @author vuk
 */
public class IznajmljivanjeModelTabela extends AbstractTableModel {
    List<Iznajmljivanje> lista=new ArrayList<>();
    String kolone[]={"id","recepcioner","gost","ukupna cena"};
    
    public IznajmljivanjeModelTabela(List<Iznajmljivanje> lista)
    {
        this.lista=lista;
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanje();
            case 1:
                return i.getRecepcioner().getIme()+" "+i.getRecepcioner().getPrezime();
            case 2:
                return i.getOsoba().getIme()+" "+i.getOsoba().getPrezime();
            case 3:
                return i.getUkupnaCena();
            default:
                return "na";    
        }        
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(Iznajmljivanje i)
    {
        lista.add(i);
        fireTableDataChanged();
    }     
}
