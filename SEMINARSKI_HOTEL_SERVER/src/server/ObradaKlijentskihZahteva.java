/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacijaZajednicki.Posiljalac;
import komunikacijaZajednicki.Primalac;
import komunikacijaZajednicki.Zahtev;
import komunikacijaZajednicki.Odgovor;
import logika.Controller;
import model.Recepcioner;
/**
 *
 * @author vuk
 */
public class ObradaKlijentskihZahteva extends Thread{
    private Socket clientSocket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    boolean kraj=false;
    
    public ObradaKlijentskihZahteva(Socket clientSocket) {
        this.clientSocket=clientSocket;
        posiljalac = new Posiljalac(clientSocket);
        primalac = new Primalac(clientSocket);
    }

    @Override
    public void run() {
        while(!kraj){
        try {
            Zahtev zahtev = (Zahtev) primalac.prima();
            Odgovor odgovor = new Odgovor();
            Recepcioner r;
            try{
            switch(zahtev.getOperacija()){
                case LOGIN: 
                    System.out.println("Operacija login");
                    r = (Recepcioner) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().login(r));
                    break;
//                case REGISTRUJ:
//                    System.out.println("Operacija registruj");
//                    r = (Recepcioner) zahtev.getArgument();
//                    odgovor.setRezultat(Controller.getInstance().registruj(r));
//                    break;
//                case Operation.EDIT_INSTRUKTOR:
//                    System.out.println("Operacija izmeni instruktor");
//                    Instruktor ins = (Instruktor) request.getArgument();
//                    response.setResult(Controller.getInstance().izmeniInstruktor(ins));
//                    break;
//                case Operation.DELETE_INSTRUKTOR:
//                    System.out.println("Operacija obrisi instruktor");
//                    Instruktor inst = (Instruktor) request.getArgument();
//                    response.setResult(Controller.getInstance().obrisiInstruktor(inst));
//                    break;
            }
            }catch(Exception ex){
                odgovor.setException(ex);
            }
            posiljalac.salje(odgovor);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
        
    }  
    
    public void prekiniNit()
    {
        kraj=true;
        try {
            clientSocket.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
