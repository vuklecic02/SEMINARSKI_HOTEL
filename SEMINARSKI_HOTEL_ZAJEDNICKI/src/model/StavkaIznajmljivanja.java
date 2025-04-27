/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vuk
 */
public class StavkaIznajmljivanja implements OpstiDomenskiObjekat{
    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private int brojDana;
    private double iznos;
    private Soba soba;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, LocalDate datumOd, LocalDate datumDo, int brojDana, double iznos, Soba soba) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojDana = brojDana;
        this.iznos = iznos;
        this.soba = soba;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next())
        {
            int rb=rs.getInt("stavkaiznajmljivanja.rb");
            LocalDate datumOd=rs.getDate("stavkaiznajmljivanja.datumOd").toLocalDate();
            LocalDate datumDo=rs.getDate("stavkaiznajmljivanja.datumDo").toLocalDate();
            double iznos=rs.getDouble("stavkaiznajmljivanja.iznos");
            int brojDana=rs.getInt("stavkaiznajmljivanja.brojDana");
            
            int idSoba=rs.getInt("soba.idSoba");
            double cenaDan=rs.getDouble("soba.cenaDan");
            TipSobe tipSobe=TipSobe.izBazeString(rs.getString("soba.tipSobe"));
            Soba s=new Soba(idSoba, cenaDan, tipSobe);
            
            int idIznajmljivanje=rs.getInt("iznajmljivanje.idIznajmljivanje");
            Iznajmljivanje i=new Iznajmljivanje();
            i.setIdIznajmljivanje(idIznajmljivanje);
            
            StavkaIznajmljivanja si=new StavkaIznajmljivanja(i, rb, datumOd, datumDo, brojDana, iznos, s);
            lista.add(si);

        }         
        return lista;    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "iznajmljivanje,datumOd,datumDo,iznos,brojDana,soba";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return iznajmljivanje.getIdIznajmljivanje()+",'"+datumOd+"','"+datumDo+"',"+iznos+","+brojDana+","+soba.getIdSoba();
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "iznajmljivanje="+iznajmljivanje.getIdIznajmljivanje()+
                ", datumOd='"+datumOd+"', datumDo='"+datumDo+"', iznos="+iznos+", brojDana="+brojDana+", soba="+soba.getIdSoba();
    }

    @Override
    public String vratiUslovPostoji() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiRazlicitPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviVrednosti(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
