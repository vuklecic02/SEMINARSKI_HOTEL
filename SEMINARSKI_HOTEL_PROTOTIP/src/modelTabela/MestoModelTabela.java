/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Mesto;

/**
 *
 * @author vuk
 */
public class MestoModelTabela extends AbstractTableModel {
    List<Mesto> lista=new ArrayList<>();
    String kolone[]={"id","naziv","drzava"};
    
    public MestoModelTabela(List<Mesto> lista)
    {
        this.lista=lista;
    }

    public List<Mesto> getLista() {
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
        Mesto m = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getIdMesta();
            case 1:
                return m.getNaziv();
            case 2:
                return m.getDrzava();
            default:
                return "na";    
        }        
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(Mesto m)
    {
        lista.add(m);
        fireTableDataChanged();
    }    
    
}
