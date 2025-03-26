/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import komunikacijaZajednicki.Odgovor;
import komunikacijaZajednicki.Posiljalac;
import komunikacijaZajednicki.Primalac;
import komunikacijaZajednicki.Zahtev;
import logika.Controller;
import model.Recepcioner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vuk
 */
public class Server extends Thread{
    boolean kraj =false;
    ServerSocket serverSocket;
    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti=new ArrayList<>();
    }

    @Override
    public void run() {
        try 
        {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server ceka klijente...");
            while(!kraj){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Klijent: "+clientSocket + " se konektovao.");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(clientSocket);
                okz.start();
            }
        } catch (IOException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void stopServer()
    {
        kraj=true;
        try {
            for(ObradaKlijentskihZahteva k:klijenti)
            {
                k.prekiniNit();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
