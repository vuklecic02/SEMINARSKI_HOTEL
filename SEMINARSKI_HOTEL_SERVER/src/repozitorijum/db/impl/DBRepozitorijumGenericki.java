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

/**
 *
 * @author vuk
 */
public class DBRepozitorijumGenericki implements DBRepozitorijum<OpstiDomenskiObjekat> {

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat param,String uslov) throws Exception {
       List<OpstiDomenskiObjekat> lista=new ArrayList<>();
       String upit="SELECT FROM "+param.vratiNazivTabele();
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



    
}
