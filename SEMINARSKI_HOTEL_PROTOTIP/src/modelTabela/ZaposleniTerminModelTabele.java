/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ZaposleniTermin;

/**
 *
 * @author vuk
 */
public class ZaposleniTerminModelTabele extends AbstractTableModel {

    List<ZaposleniTermin> lista=new ArrayList<>();
    String kolone[]={"ime","prezime","smena","datum"};

    public List<ZaposleniTermin> getLista() {
        return lista;
    }

    public void setLista(List<ZaposleniTermin> lista) {
        this.lista = lista;
    }
    
    public ZaposleniTerminModelTabele(List<ZaposleniTermin> lista)
    {
        this.lista=lista;
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
        ZaposleniTermin zt = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zt.getRecepcioner().getIme();
            case 1:
                return zt.getRecepcioner().getPrezime();
            case 2:
                return zt.getTerminDezurstva().getSmena();
            case 3:
                return zt.getDatum();
            default:
                return "na";    
        }        
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(ZaposleniTermin zt)
    {
        lista.add(zt);
        fireTableDataChanged();
    }    
    
}
