/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaIznajmljivanja;

/**
 *
 * @author vuk
 */
public class StavkeModelTabela extends AbstractTableModel {

    List<StavkaIznajmljivanja> lista=new ArrayList<>();
    String kolone[]={"rb","datum od","datum do","iznos","soba"};
    
    public StavkeModelTabela(List<StavkaIznajmljivanja> lista)
    {
        this.lista=lista;
    }

    public List<StavkaIznajmljivanja> getLista() {
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
        StavkaIznajmljivanja si=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return si.getRb();
            case 1:
                return si.getDatumOd();
            case 2:
                return si.getDatumDo();
            case 3:
                return si.getIznos();
            case 4:
                return si.getSoba().getTipSobe();
            default:
                return "na";    
        }         
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(StavkaIznajmljivanja si)
    {
        lista.add(si);
        fireTableDataChanged();
    }     
    
}
