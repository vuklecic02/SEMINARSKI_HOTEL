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
public class TerminDezurstva implements OpstiDomenskiObjekat{
    private int idTerminDezurstva;
    private String smena;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, String smena) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.smena = smena;
    }

    @Override
    public String toString() {
        return smena;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getSmena() {
        return smena;
    }

    public void setSmena(String smena) {
        this.smena = smena;
    }

    @Override
    public String vratiNazivTabele() {
        return "termindezurstva";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next())
        {
            int idTerminDezurstva=rs.getInt("termindezurstva.idTerminDezurstva");
            String smena=rs.getString("termindezurstva.smena");
            TerminDezurstva td=new TerminDezurstva(idTerminDezurstva, smena);
            lista.add(td);
        }
        return lista;   
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "smena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+smena+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "termindezurstva.idTerminDezurstva="+idTerminDezurstva;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "smena='"+smena+"'";
    }

    @Override
    public String vratiUslovPostoji() {
        return "smena='"+smena+"'";
    }

    @Override
    public String vratiRazlicitPrimarniKljuc() {
        return "termindezurstva.idTerminDezurstva<>"+idTerminDezurstva;
    }

    @Override
    public void postaviVrednosti(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
