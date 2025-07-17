/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuk
 */
public class Iznajmljivanje implements OpstiDomenskiObjekat{
    private int idIznajmljivanje;
    private double ukupnaCena;
    private Recepcioner recepcioner;
    private Osoba osoba;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, double ukupnaCena, Recepcioner recepcioner, Osoba osoba){
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupnaCena = ukupnaCena;
        this.recepcioner = recepcioner;
        this.osoba = osoba;
        
    }

    public Iznajmljivanje( double ukupnaCena, Recepcioner recepcioner, Osoba osoba){
        this.ukupnaCena = ukupnaCena;
        this.recepcioner = recepcioner;
        this.osoba = osoba;
        
    }    


    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Recepcioner getRecepcioner() {
        return recepcioner;
    }

    public void setRecepcioner(Recepcioner recepcioner) {
        this.recepcioner = recepcioner;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    @Override
    public String toString() {
        return ukupnaCena + " " + recepcioner + " " + osoba;
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next())
        {
            int idIznajmljivanje=rs.getInt("iznajmljivanje.idIznajmljivanje");
            double ukupnaCena=rs.getDouble("iznajmljivanje.ukupnaCena");
            
            int idRecepcioner=rs.getInt("recepcioner.idRecepcioner");
            String ime=rs.getString("recepcioner.ime");
            String prezime=rs.getString("recepcioner.prezime");
            String username=rs.getString("recepcioner.username");
            String password=rs.getString("recepcioner.password");
            String email=rs.getString("recepcioner.email");
            Rola rola=Rola.izBazeString(rs.getString("recepcioner.rola"));
            int aktivan=rs.getInt("recepcioner.aktivan");
            Recepcioner r=new Recepcioner(idRecepcioner, ime, prezime, username, password, email,rola,aktivan);
            
            int idOsoba=rs.getInt("osoba.idOsoba");
            String imeOsoba=rs.getString("osoba.ime");
            String prezimeOsoba=rs.getString("osoba.prezime");
            String telefon=rs.getString("osoba.telefon");
            String brLicneIsprave=rs.getString("osoba.brLicneIsprave");
            
            int idMesta=rs.getInt("mesto.idMesta");
            String naziv=rs.getString("mesto.naziv");
            Drzava drzava=Drzava.izBazeString(rs.getString("mesto.drzava"));
            Mesto mesto=new Mesto(idMesta, naziv, drzava);
            Osoba o=new Osoba(idOsoba, imeOsoba, prezimeOsoba, telefon, brLicneIsprave, mesto);
            
            Iznajmljivanje i=new Iznajmljivanje(idIznajmljivanje, ukupnaCena, r, o);
            lista.add(i);
        }        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupnaCena,recepcioner,osoba";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return ukupnaCena+","+recepcioner.getIdRecepcioner()+","+osoba.getIdOsoba();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje="+idIznajmljivanje;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ukupnaCena="+ukupnaCena+", recepcioner="+recepcioner.getIdRecepcioner()+", osoba="+osoba.getIdOsoba();
    }

    @Override
    public String vratiUslovPostoji() {
        return null;
    }

    @Override
    public String vratiRazlicitPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje<>"+idIznajmljivanje;
    }

    @Override
    public void postaviVrednosti(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviID(long generatedId) {
        this.idIznajmljivanje=(int) generatedId;
    }

    @Override
    public String vratiUpitZaUbacivanjeVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
