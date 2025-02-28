/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Recepcioner;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.OpstiDomenskiObjekat;

/**
 *
 * @author vuk
 */
public class DBBroker {
    
    public List<OpstiDomenskiObjekat> citaj(OpstiDomenskiObjekat odo) throws Exception{
        
        List<OpstiDomenskiObjekat> list = new ArrayList<>();
        
        String query = "SELECT * FROM "+odo.vratiNazivTabele();
        PreparedStatement ps = DBKonekcija.getInstance().getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        list = odo.vratiListu(rs);
        
        ps.close();
        rs.close();
        
        System.out.println("Vracanje liste.");
        return list;
    }


//    public Recepcioner getRecepcioner(Recepcioner recepcioner) throws SQLException {
//        
//        try {
//            String query = "SELECT idRecepcioner, ime, prezime FROM recepcioner WHERE username=? AND sifra=?";
//            System.out.println("Upit: " + query);
//
//            PreparedStatement statement = DBKonekcija.getInstance().getConnection().prepareStatement(query);
//            statement.setString(1, recepcioner.getUsername().trim());
//            statement.setString(2, recepcioner.getSifra().trim());
//
//            ResultSet rs=statement.executeQuery();
//
//            if (rs.next()) {
//                recepcioner.setIdRecepcioner(rs.getInt("idRecepcioner"));
//                recepcioner.setIme(rs.getString("ime"));
//                recepcioner.setPrezime(rs.getString("prezime"));
//                System.out.println(recepcioner.getIdRecepcioner()+" "+recepcioner.getIme());
//            }
//            else 
//            {
//                System.out.println("Neispravan username ili password!");
//            }
//            
//            rs.close();
//            statement.close();
//            System.out.println("Uspesno ucitavanje objekta Recepcioner iz baze!");
//            return recepcioner;
//        } catch (SQLException ex) {
//            System.out.println("Objekat Recepcioner nije uspesno ucitan iz baze!");
//            ex.printStackTrace();
//            throw ex;
//        }        
//    }
    
    

    
}
