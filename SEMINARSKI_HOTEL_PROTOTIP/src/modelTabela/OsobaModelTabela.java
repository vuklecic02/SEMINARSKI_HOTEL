/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Osoba;

/**
 *
 * @author vuk
 */
public class OsobaModelTabela extends AbstractTableModel {

    List<Osoba> lista=new ArrayList<>();
    String kolone[]={"id","ime","prezime","telefon","mesto"};
    
    public OsobaModelTabela(List<Osoba> lista)
    {
        this.lista=lista;
    }

    public List<Osoba> getLista() {
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
        Osoba o = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getIdOsoba();
            case 1:
                return o.getIme();
            case 2:
                return o.getPrezime();
            case 3:
                return o.getTelefon();
            case 4:
                return o.getMesto().getNaziv();
            default:
                return "na";    
        }         
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajElement(Osoba o)
    {
        lista.add(o);
        fireTableDataChanged();
    } 
    
}
