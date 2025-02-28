/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uicontroller;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import model.Recepcioner;

/**
 *
 * @author vuk
 */
public class Controller {
    
    private static Controller instance;
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    
    private Controller() throws Exception {
        socket = new Socket("127.0.0.1", 9000);
        primalac = new Primalac(socket);
        posiljalac = new Posiljalac(socket);
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            try {
                instance = new Controller();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }    

    public Recepcioner login(String username, String password) throws Exception{
        Recepcioner recepcioner=new Recepcioner(username, password);        
        Zahtev zahtev=new Zahtev(Operacija.LOGIN, recepcioner);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Recepcioner) odgovor.getRezultat();
        }
        throw odgovor.getException();        
    }

    public Recepcioner registruj(Recepcioner recepcioner) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.REGISTRUJ, recepcioner);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Recepcioner) odgovor.getRezultat();
        }
        throw odgovor.getException();        
    }
}
