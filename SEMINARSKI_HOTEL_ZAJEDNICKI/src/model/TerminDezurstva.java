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
public class TerminDezurstva implements OpstiDomenskiObjekat{
    private int idTerminDezurstva;
    private String komentar;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, String komentar) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.komentar = komentar;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    @Override
    public String vratiNazivTabele() {
        return "termindezurstva";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        
        return lista;    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "komentar";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+komentar+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "termindezurstva.idTerminDezurstva"+idTerminDezurstva;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "komentar='"+komentar+"'";
    }
    
    
}
