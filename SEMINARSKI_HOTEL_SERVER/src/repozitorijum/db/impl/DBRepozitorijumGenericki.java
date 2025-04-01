/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db.impl;

import baza.DBKonekcija;
import java.util.ArrayList;
import java.util.List;
import model.OpstiDomenskiObjekat;
import repozitorijum.db.DBRepozitorijum;
import java.sql.*;
import model.Recepcioner;

/**
 *
 * @author vuk
 */
public class DBRepozitorijumGenericki implements DBRepozitorijum<OpstiDomenskiObjekat> {

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat param,String uslov) throws Exception {
       List<OpstiDomenskiObjekat> lista=new ArrayList<>();
       String upit="SELECT * FROM "+param.vratiNazivTabele();
       if(uslov!=null)
       {
           upit+=" ";
           upit+=uslov;
       }
       System.out.println(upit);
       PreparedStatement ps = DBKonekcija.getInstance().getConnection().prepareStatement(upit);
       ResultSet rs=ps.executeQuery();
       lista=param.vratiListu(rs);
       rs.close();
       ps.close();
        
       return lista;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve() {

         List<OpstiDomenskiObjekat> lista=new ArrayList<>();
         return lista;
    }

    @Override
    public void kreiraj(OpstiDomenskiObjekat param) throws Exception {
        try
        {
            String upit="INSERT INTO "+param.vratiNazivTabele()+"("+param.vratiKoloneZaUbacivanje()+")"+" VALUES"+"("+param.vratiVrednostiZaUbacivanje()+")";
            System.out.println(upit);
            PreparedStatement ps=DBKonekcija.getInstance().getConnection().prepareStatement(upit);
            ps.executeUpdate();
            ps.close();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void izmeni(OpstiDomenskiObjekat param) throws Exception {
        try
        {
            String upit = "UPDATE "+param.vratiNazivTabele()+ " SET "+param.vratiVrednostiZaIzmenu()+ " WHERE "+param.vratiPrimarniKljuc();
            System.out.println(upit);
            Statement st=DBKonekcija.getInstance().getConnection().createStatement();
            st.executeQuery(upit);
            st.close();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void obrisi(OpstiDomenskiObjekat param) throws Exception {
        try
        {
           String upit = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
           System.out.println(upit);
           PreparedStatement ps = DBKonekcija.getInstance().getConnection().prepareStatement(upit);
           ps.executeUpdate();
           ps.close();         
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean daLiPostoji(OpstiDomenskiObjekat param)
    {
        try {
            ResultSet rs;
            String upit = "SELECT * FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiUslovPostoji();
            System.out.println(upit);
            PreparedStatement ps = DBKonekcija.getInstance().getConnection().prepareStatement(upit);
            rs = ps.executeQuery();
            boolean postoji = rs.next();
            if (postoji) {
                System.out.println("U bazi postoji slog " + param.vratiNazivTabele());
                return true;
            }

            System.out.println("U bazi ne postoji slog " + param.vratiNazivTabele());
            return false;
        } catch (SQLException ex) {

            System.out.println("Neuspesna provera postojanja sloga");
            return false;
        }        
    }

    @Override
    public OpstiDomenskiObjekat vratiJednog(OpstiDomenskiObjekat param, String uslov,Object... vrednosti) throws Exception
    {
        OpstiDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + param.vratiNazivTabele() + " WHERE " + uslov;
        System.out.println(upit);
        PreparedStatement ps = DBKonekcija.getInstance().getConnection().prepareStatement(upit);
        
        // Postavljanje vrednosti u upit
        for (int i = 0; i < vrednosti.length; i++) 
        {
            ps.setObject(i + 1, vrednosti[i]);
        }

        ResultSet rs = ps.executeQuery();
        List<OpstiDomenskiObjekat> lista = param.vratiListu(rs);        
        if (!lista.isEmpty()) 
        {
            objekat = lista.get(0);
        }

        rs.close();
        ps.close();

        return objekat;        
    }
    
}
