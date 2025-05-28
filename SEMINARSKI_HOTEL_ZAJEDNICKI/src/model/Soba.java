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
public class Soba implements OpstiDomenskiObjekat{
    private int idSoba;
    private double cenaDan;
    private TipSobe tipSobe;

    public Soba() {
    }

    public Soba(int idSoba, double cenaDan, TipSobe tipSobe) {
        this.idSoba = idSoba;
        this.cenaDan = cenaDan;
        this.tipSobe = tipSobe;
    }

    public int getIdSoba() {
        return idSoba;
    }

    public void setIdSoba(int idSoba) {
        this.idSoba = idSoba;
    }

    public double getCenaDan() {
        return cenaDan;
    }

    public void setCenaDan(double cenaDan) {
        this.cenaDan = cenaDan;
    }

    public TipSobe getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(TipSobe tipSobe) {
        this.tipSobe = tipSobe;
    }

    @Override
    public String toString() {
        return tipSobe + " - " +cenaDan+" rsd";
    }

    @Override
    public String vratiNazivTabele() {
        return "soba";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next())
        {
            int idSoba=rs.getInt("soba.idSoba");
            double cenaDan=rs.getDouble("soba.cenaDan");
            TipSobe tipSobe=TipSobe.izBazeString(rs.getString("soba.tipSobe"));
            Soba s=new Soba(idSoba, cenaDan, tipSobe);
            lista.add(s);
        }         
        return lista;    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "cenaDan,tipSobe";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return cenaDan+",'"+tipSobe.toString()+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "soba.idSoba="+idSoba;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
      return "cenaDan="+cenaDan+",tipSobe='"+tipSobe.toString()+"'";
    }

    @Override
    public String vratiUslovPostoji() {
        return "cenaDan="+cenaDan+" AND tipSobe='"+tipSobe.toString()+"'";
    }

    @Override
    public String vratiRazlicitPrimarniKljuc() {
        return "soba.idSoba<>"+idSoba;

    }

    @Override
    public void postaviVrednosti(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviID(long generatedId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
