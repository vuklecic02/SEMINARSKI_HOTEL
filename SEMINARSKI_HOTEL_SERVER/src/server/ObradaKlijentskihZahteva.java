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
import model.TerminDezurstva;
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
            TerminDezurstva td;
            try{
            switch(zahtev.getOperacija()){
                case LOGIN: 
                    System.out.println("Operacija login");
                    r = (Recepcioner) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().login(r));
                    break;
                case KREIRAJ_RECEPCIONER:
                    System.out.println("Operacija kreiraj recepcionera");
                    r = (Recepcioner) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().kreirajRecepcionera(r));
                    break;
                case UCITAJ_RECEPCIONERE:
                    System.out.println("Operacija ucitaj recepcionere");
                    odgovor.setRezultat(Controller.getInstance().vratiListuRecepcionera());
                    break;
                case UCITAJ_RECEPCIONERE_FILTER:
                    System.out.println("Operacija ucitaj recepcionere-filter");
                    r=(Recepcioner) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuRecepcionera(r));
                    break;                    
                case UCITAJ_TERMINE_DEZURSTAVA:
                    System.out.println("Operacija učitaj termine dežurstava");
                    odgovor.setRezultat(Controller.getInstance().vratiListuTerminaDezurstava());
                    break;
                case UCITAJ_TERMINE_DEZURSTAVA_FILTER:
                    System.out.println("Operacija ucitaj termine dežurstava-filter");
                    td=(TerminDezurstva) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuTerminDez(td));
                    break;
                case KREIRAJ_TERMIN_DEZURSTVA:
                    System.out.println("Operacija kreiraj termin dežurstva");
                    td=(TerminDezurstva) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().kreirajTerminDezurstva(td));
                    break;
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
