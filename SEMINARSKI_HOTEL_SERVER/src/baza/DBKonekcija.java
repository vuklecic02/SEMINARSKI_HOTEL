/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vuk
 */
public class DBKonekcija {
    private static DBKonekcija instance;
    private Connection connection;
    private DBKonekcija(){
        String url = "jdbc:mysql://localhost:3306/seminarski_hotel_prototip";
        try {
            connection = DriverManager.getConnection(url,"root","");
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
