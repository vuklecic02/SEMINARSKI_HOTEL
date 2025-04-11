/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import java.util.ArrayList;
import java.util.List;
import model.Mesto;
import model.Osoba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuOsoba extends OpstaSistemskaOperacija {

    private List<Osoba> lista=new ArrayList<>();
    
    public List<Osoba> getLista() {
        return lista;
    } 
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        Osoba o=(Osoba)param;
        String uslov=" JOIN mesto ON osoba.mesto=mesto.idMesta WHERE 1=1";
        if (o.getMesto().getDrzava() != null) {
            uslov += " AND mesto.drzava = '" + o.getMesto().getDrzava().toString() + "'";
        }

        if (o.getMesto().getNaziv()!= null) {
            uslov += " AND mesto.naziv='" + o.getMesto().getNaziv() + "'";
        }  
        lista=dbbroker.vratiSve(param, uslov);
    }
    
}
