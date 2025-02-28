/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuk
 */
public class Recepcioner implements OpstiDomenskiObjekat, Serializable{
    private int idRecepcioner;
    private String ime;
    private String prezime;
    private String username;
    private String sifra;
    private String email;

    public Recepcioner() {
    }
    
    public Recepcioner(String username,String sifra) {
        this.username=username;
        this.sifra=sifra;
    }

    public Recepcioner(int idRecepcioner, String ime, String prezime, String username, String sifra, String email) {
        this.idRecepcioner = idRecepcioner;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.sifra = sifra;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
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
        while(rs.next())
        {
            int idRecepcioner=rs.getInt("recepcioner.idRecepcioner");
            String ime=rs.getString("recepcioner.ime");
            String prezime=rs.getString("recepcioner.prezime");
            String username=rs.getString("recepcioner.username");
            String sifra=rs.getString("recepcioner.sifra");
            String email=rs.getString("recepcioner.email");
            Recepcioner r=new Recepcioner(idRecepcioner, ime, prezime, username, sifra, email);
            lista.add(r);
        }
        return lista;    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,username,password,email";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+username+"','"+sifra+"','"+email+"'";
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
        return "ime='"+ime+"', prezime='"+prezime+"', username='"+username+"', password='"+sifra+"', email='"+email+"'";
    }
    
    
    
}
