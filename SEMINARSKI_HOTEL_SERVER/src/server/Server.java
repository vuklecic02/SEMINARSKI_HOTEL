/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import logika.Controller;
import model.Recepcioner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vuk
 */
public class Server {
    
    public void startServer() {
        try 
        {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server ceka klijente...");
            while(true){
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
}
