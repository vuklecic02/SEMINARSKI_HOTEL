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
public class Osoba implements OpstiDomenskiObjekat{
    private int idOsoba;
    private String ime;
    private String prezime;
    private String telefon;
    private String brLicneIsprave;
    private Mesto mesto;

    public Osoba() {
    }

    public Osoba(int idOsoba, String ime, String prezime, String telefon, String brLicneIsprave, Mesto mesto) {
        this.idOsoba = idOsoba;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.brLicneIsprave = brLicneIsprave;
        this.mesto = mesto;
    }

    public int getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getBrLicneIsprave() {
        return brLicneIsprave;
    }

    public void setBrLicneIsprave(String brLicneIsprave) {
        this.brLicneIsprave = brLicneIsprave;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " " + brLicneIsprave;
    }

    @Override
    public String vratiNazivTabele() {
        return "osoba";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        
        return lista;    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,telefon,brLicneIsprave,mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+telefon+"','"+brLicneIsprave+"',"+mesto.getIdMesta();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "osoba.idOsoba"+idOsoba;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', telefon='"+telefon+"', brLicneIsprave='"+brLicneIsprave+"', mesto="+mesto.getIdMesta();
    }

    @Override
    public String vratiUslovPostoji() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
