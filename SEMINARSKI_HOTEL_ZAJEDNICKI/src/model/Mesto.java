/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuk
 */
public class Mesto implements OpstiDomenskiObjekat{
    private int idMesta;
    private String naziv;
    private Drzava drzava;

    public Mesto() {
    }

    public Mesto(int idMesta, String naziv, Drzava drzava) {
        this.idMesta = idMesta;
        this.naziv = naziv;
        this.drzava=drzava;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
   

    @Override
    public String toString() {
        return naziv+", "+String.valueOf(drzava).replace("_", " ");
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        
        return lista;    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,drzava";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"', '"+String.valueOf(drzava).replace("_", " ")+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesta"+idMesta;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"', drzava='"+String.valueOf(drzava)+"'";
    }

    @Override
    public String vratiUslovPostoji() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
