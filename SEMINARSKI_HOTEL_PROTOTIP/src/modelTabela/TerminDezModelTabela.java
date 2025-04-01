/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.TerminDezurstva;

/**
 *
 * @author vuk
 */
public class TerminDezModelTabela extends AbstractTableModel {

    List<TerminDezurstva> lista=new ArrayList<>();
    String kolone[]={"id","smena"};

    public List<TerminDezurstva> getLista() {
        return lista;
    }

    public void setLista(List<TerminDezurstva> lista) {
        this.lista = lista;
    }
    
    public TerminDezModelTabela(List<TerminDezurstva> lista)
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
        TerminDezurstva td = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return td.getIdTerminDezurstva();
            case 1:
                return td.getSmena();
            default:
                return "na";    
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(TerminDezurstva td)
    {
        lista.add(td);
        fireTableDataChanged();
    }
    
}
