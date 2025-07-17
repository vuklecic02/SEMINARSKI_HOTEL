/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
/**
 *
 * @author vuk
 */
public class DBKonekcija {
    
    private static DBKonekcija instance;
    private Connection connection;
    
    private DBKonekcija(){
        
//        String url = Konfiguracija.getInstance().getProperty("url");
//        String username = Konfiguracija.getInstance().getProperty("username");
//        String password = Konfiguracija.getInstance().getProperty("password");
//        try {
//            if(connection==null || connection.isClosed())
//            {
//                connection = DriverManager.getConnection(url, username, password);
//                connection.setAutoCommit(false);
//            }
//
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DBKonekcija.class.getName()).log(Level.SEVERE, null, ex);
//        }        
            
        String url = "jdbc:mysql://localhost:3306/seminarski_hotel_prototip";
        try {
            connection = DriverManager.getConnection(url,"root","");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBKonekcija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBKonekcija getInstance() {
        if(instance==null)
            instance = new DBKonekcija();
        return instance;
    }    
}
