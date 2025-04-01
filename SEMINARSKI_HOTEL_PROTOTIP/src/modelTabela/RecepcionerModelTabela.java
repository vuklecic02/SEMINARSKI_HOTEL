/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Recepcioner;

/**
 *
 * @author vuk
 */
public class RecepcionerModelTabela extends AbstractTableModel {

    private List<Recepcioner> lista=new ArrayList<>();
    String kolone[] = {"id", "ime","prezime", "username","email"};

    public List<Recepcioner> getLista() {
        return lista;
    }

    public void setLista(List<Recepcioner> lista) {
        this.lista = lista;
    }

    public RecepcionerModelTabela(List<Recepcioner> lista)
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
        Recepcioner r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdRecepcioner();
            case 1:
                return r.getIme();
            case 2:
                return r.getPrezime();
            case 3:
                return r.getUsername();
            case 4:
                return r.getEmail();
            default:
                return "na";
        }        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(Recepcioner r)
    {
        lista.add(r);
        fireTableDataChanged();
    }
    
    
}
