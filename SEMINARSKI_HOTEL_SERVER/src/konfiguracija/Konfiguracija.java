/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vuk
 */
public class Konfiguracija {
    
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija=new Properties();
        try {
            konfiguracija.load(new FileInputStream("D:\\FON Cetvrta godina\\Projektovanje softvera\\Projekat - Hotel\\SEMINARSKI_HOTEL_SERVER\\config\\dbconfig.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static Konfiguracija getInstance() {
        if(instance==null)
            instance=new Konfiguracija();
        return instance;
    }
    
    public String getProperty(String key)
    {
        return konfiguracija.getProperty(key, "N/A");
    }
    
    public void setProperty(String key, String value)
    {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene()
    {
        try {
            konfiguracija.store(new FileOutputStream("D:\\FON Cetvrta godina\\Projektovanje softvera\\Projekat - Hotel\\SEMINARSKI_HOTEL_SERVER\\config\\dbconfig.properties"), null);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
}
