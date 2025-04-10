/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Soba;

/**
 *
 * @author vuk
 */
public class SobaModelTabela extends AbstractTableModel {
    List<Soba> lista=new ArrayList<>();
    String kolone[]={"id","cena dan","tip sobe"};
    
    public SobaModelTabela(List<Soba> lista)
    {
        this.lista=lista;
    }

    public List<Soba> getLista() {
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
        Soba s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIdSoba();
            case 1:
                return s.getCenaDan();
            case 2:
                return s.getTipSobe();
            default:
                return "na";    
        }        
    }
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(Soba s)
    {
        lista.add(s);
        fireTableDataChanged();
    }    
    
}
