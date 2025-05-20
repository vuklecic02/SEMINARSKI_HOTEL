/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author vuk
 */
public class ZaposleniTermin implements OpstiDomenskiObjekat{
    private Recepcioner recepcioner;
    private TerminDezurstva terminDezurstva;
    private LocalDate datum;

    public ZaposleniTermin() {
    }

    public ZaposleniTermin(Recepcioner recepcioner, TerminDezurstva terminDezurstva, LocalDate datum) {
        this.recepcioner = recepcioner;
        this.terminDezurstva = terminDezurstva;
        this.datum = datum;
    }

    public Recepcioner getRecepcioner() {
        return recepcioner;
    }

    public void setRecepcioner(Recepcioner recepcioner) {
        this.recepcioner = recepcioner;
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return recepcioner + " " + terminDezurstva + " " + datum ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ZaposleniTermin other = (ZaposleniTermin) obj;
        return Objects.equals(this.datum, other.datum);
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "zaposlenitermin";
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
            String password=rs.getString("recepcioner.password");
            String email=rs.getString("recepcioner.email");
            Rola rola=Rola.izBazeString(rs.getString("recepcioner.rola"));
            int aktivan=rs.getInt("recepcioner.aktivan");
            Recepcioner r=new Recepcioner(idRecepcioner, ime, prezime, username, password, email,rola,aktivan);
            
            int idTerminDezurstva=rs.getInt("termindezurstva.idTerminDezurstva");
            String smena=rs.getString("termindezurstva.smena");
            TerminDezurstva td=new TerminDezurstva(idTerminDezurstva, smena);
            
            LocalDate datum=rs.getDate("zaposlenitermin.datum").toLocalDate();
            ZaposleniTermin zt=new ZaposleniTermin(r, td, datum);
            lista.add(zt);
        }        
        return lista; 
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "recepcioner, terminDezurstva, datum";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "?, ?, ?";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposlenitermin.recepcioner="+recepcioner+" AND zaposlenitermin.terminDezurstva="+terminDezurstva+" AND zaposlenitermin.datum='"+datum+"'";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiUslovPostoji() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiRazlicitPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void postaviVrednosti(PreparedStatement ps) throws Exception {
        ps.setLong(1, recepcioner.getIdRecepcioner());
        ps.setLong(2, terminDezurstva.getIdTerminDezurstva());
        ps.setDate(3, java.sql.Date.valueOf(datum));
    }
}
