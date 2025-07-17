/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return naziv+", "+drzava.toString();
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next())
        {
            int idMesta=rs.getInt("mesto.idMesta");
            String naziv=rs.getString("mesto.naziv");
            Drzava drzava=Drzava.izBazeString(rs.getString("mesto.drzava"));
            Mesto m=new Mesto(idMesta, naziv, drzava);
            lista.add(m);
        }        
        return lista;    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,drzava";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"', '"+drzava.toString()+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesta="+idMesta;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"', drzava='"+drzava.toString()+"'";
    }

    @Override
    public String vratiUslovPostoji() {
        return "naziv='"+naziv+"' AND drzava='"+drzava.toString()+"'";
    }

    @Override
    public String vratiRazlicitPrimarniKljuc() {
        return "mesto.idMesta<>"+idMesta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mesto other = (Mesto) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return this.drzava == other.drzava;
    }

    @Override
    public void postaviVrednosti(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviID(long generatedId) {
        this.idMesta=(int) generatedId;
    }

    @Override
    public String vratiUpitZaUbacivanjeVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
