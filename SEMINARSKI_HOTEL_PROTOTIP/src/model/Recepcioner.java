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
public class Recepcioner implements OpstiDomenskiObjekat{
    private int idRecepcioner;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Recepcioner() {
    }

    public Recepcioner(int idRecepcioner, String ime, String prezime, String username, String password) {
        this.idRecepcioner = idRecepcioner;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }
    

    public int getIdRecepcioner() {
        return idRecepcioner;
    }

    public void setIdRecepcioner(int idRecepcioner) {
        this.idRecepcioner = idRecepcioner;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "recepcioner";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        
        return lista;    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,username,password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+username+"','"+password+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "recepcioner.idRecepcioner"+idRecepcioner;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', username='"+username+"', password='"+password+"'";
    }
    
    
    
}
