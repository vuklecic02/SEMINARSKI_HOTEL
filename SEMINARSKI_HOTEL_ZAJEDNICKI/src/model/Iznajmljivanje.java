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
public class Iznajmljivanje implements OpstiDomenskiObjekat{
    private int idIznajmljivanje;
    private double ukupnaCena;
    private Recepcioner recepcioner;
    private Osoba osoba;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, double ukupnaCena, Recepcioner recepcioner, Osoba osoba, List<StavkaIznajmljivanja> stavke) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupnaCena = ukupnaCena;
        this.recepcioner = recepcioner;
        this.osoba = osoba;
        this.stavke = stavke;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
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
        return "iznajmljivanje.idIznajmljivanje"+idIznajmljivanje;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
