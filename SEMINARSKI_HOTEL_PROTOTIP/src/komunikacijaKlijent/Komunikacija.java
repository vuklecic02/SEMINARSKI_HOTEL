/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacijaKlijent;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacijaZajednicki.Odgovor;
import komunikacijaZajednicki.Operacija;
import komunikacijaZajednicki.Posiljalac;
import komunikacijaZajednicki.Primalac;
import komunikacijaZajednicki.Zahtev;
import model.Recepcioner;

/**
 *
 * @author vuk
 */
public class Komunikacija {
    private Socket soket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
        try {
            soket = new Socket("127.0.0.1", 9000);
            primalac = new Primalac(soket);
            posiljalac = new Posiljalac(soket);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public Recepcioner login(String username, String sifra) throws Exception {
        Recepcioner recepcioner=new Recepcioner(username, sifra);        
        Zahtev zahtev=new Zahtev(Operacija.LOGIN, recepcioner);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Recepcioner) odgovor.getRezultat();
        }
        throw odgovor.getException();          
    }
       

    
}
