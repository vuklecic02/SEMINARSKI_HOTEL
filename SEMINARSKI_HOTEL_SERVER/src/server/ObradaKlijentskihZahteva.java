/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacijaZajednicki.Posiljalac;
import komunikacijaZajednicki.Primalac;
import komunikacijaZajednicki.Zahtev;
import komunikacijaZajednicki.Odgovor;
import logika.Controller;
import model.Iznajmljivanje;
import model.Mesto;
import model.Osoba;
import model.Recepcioner;
import model.Soba;
import model.StavkaIznajmljivanja;
import model.TerminDezurstva;
import model.ZaposleniTermin;
/**
 *
 * @author vuk
 */
public class ObradaKlijentskihZahteva extends Thread{
    private final Socket clientSocket;
    private final Posiljalac posiljalac;
    private final Primalac primalac;
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
            Mesto m;
            Soba s;
            Osoba o;
            List<ZaposleniTermin> ztLista;
            ZaposleniTermin zt;
            Map<Boolean, Recepcioner> mapa;
            Iznajmljivanje i;
            List<StavkaIznajmljivanja> stavke;
            int idIzn;
            try{
            switch(zahtev.getOperacija()){
                case LOGIN: 
                    try
                    {
                        System.out.println("Operacija login");
                        r = (Recepcioner) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().login(r));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case KREIRAJ_RECEPCIONER:
                    try
                    {
                        System.out.println("Operacija kreiraj recepcionera");
                        r = (Recepcioner) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajRecepcionera(r));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case UCITAJ_RECEPCIONERE:
                    System.out.println("Operacija učitaj recepcionere");
                    odgovor.setRezultat(Controller.getInstance().vratiListuRecepcionera());
                    break;
                case UCITAJ_RECEPCIONERE_FILTER:
                    System.out.println("Operacija učitaj recepcionere-filter");
                    r=(Recepcioner) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuRecepcionera(r));
                    break;                    
                case UCITAJ_TERMINE_DEZURSTAVA:
                    System.out.println("Operacija učitaj termine dežurstava");
                    odgovor.setRezultat(Controller.getInstance().vratiListuTerminaDezurstava());
                    break;
                case UCITAJ_TERMINE_DEZURSTAVA_FILTER:
                    System.out.println("Operacija učitaj termine dežurstava-filter");
                    td=(TerminDezurstva) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuTerminDez(td));
                    break;
                case KREIRAJ_TERMIN_DEZURSTVA:
                    try
                    {
                        System.out.println("Operacija kreiraj termin dežurstva");
                        td=(TerminDezurstva) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajTerminDezurstva(td));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case OBRISI_TERMIN_DEZURSTVA:
                    try
                    {
                        System.out.println("Operacija obriši termin dežurstva");
                        td=(TerminDezurstva) zahtev.getArgument();
                        Controller.getInstance().obrisiTerminDezurstva(td);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    } 
                    break;
                case PROMENI_TERMIN_DEZURSTVA:
                    try
                    {
                        System.out.println("Operacija promeni termin dežurstva");
                        td=(TerminDezurstva) zahtev.getArgument();
                        Controller.getInstance().promeniTerminDezurstva(td);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    } 
                    break;
                case PROMENI_RECEPCIONERA:
                    try
                    {
                        System.out.println("Operacija promeni recepcionera");
                        r=(Recepcioner) zahtev.getArgument();
                        Controller.getInstance().promeniRecepcionera(r);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case UCITAJ_MESTA:
                    System.out.println("Operacija učitaj mesta");
                    odgovor.setRezultat(Controller.getInstance().vratiListuMesta());
                    break;
                case KREIRAJ_MESTO:
                    try
                    {
                        System.out.println("Operacija kreiraj mesto");
                        m = (Mesto) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajMesto(m));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case OBRISI_MESTO:
                    try
                    {
                        System.out.println("Operacija obriši mesto");
                        m=(Mesto) zahtev.getArgument();
                        Controller.getInstance().obrisiMesto(m);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case UCITAJ_MESTA_FILTER:
                    System.out.println("Operacija učitaj mesta-filter");
                    m=(Mesto) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuMesto(m));
                    break;
                case UCITAJ_SOBE:
                    System.out.println("Operacija učitaj sobe");
                    odgovor.setRezultat(Controller.getInstance().vratiListuSoba());
                    break;
                case KREIRAJ_SOBU:
                    try
                    {
                        System.out.println("Operacija kreiraj sobu");
                        s = (Soba) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajSobu(s));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case OBRISI_SOBU:
                    try
                    {
                        System.out.println("Operacija obriši sobu");
                        s=(Soba) zahtev.getArgument();
                        Controller.getInstance().obrisiSobu(s);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case UCITAJ_SOBE_FILTER:
                    System.out.println("Operacija učitaj sobe-filter");
                    s=(Soba) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuSoba(s));
                    break;
                case UCITAJ_OSOBE:
                    System.out.println("Operacija učitaj osobe");
                    odgovor.setRezultat(Controller.getInstance().vratiListuOsoba());
                    break;
                case KREIRAJ_OSOBU:
                    try
                    {
                        System.out.println("Operacija kreiraj osobu");
                        o=(Osoba) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajOsobu(o));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case OBRISI_OSOBU:
                    try
                    {
                        System.out.println("Operacija obriši osobu");
                        o=(Osoba) zahtev.getArgument();
                        Controller.getInstance().obrisiOsobu(o);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    } 
                    break;
                case UCITAJ_OSOBE_FILTER:
                    System.out.println("Operacija učitaj osobe-filter");
                    o=(Osoba) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiFilterListuOsoba(o));
                    break;
                case PROMENI_OSOBU:
                    try
                    {
                        System.out.println("Operacija promeni osobu");
                        o=(Osoba) zahtev.getArgument();
                        Controller.getInstance().promeniOsobu(o);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case KREIRAJ_ZAPOSLENI_TERMIN:
                    try
                    {
                        System.out.println("Operacija kreiraj zaposleni-termin");
                        ztLista=(List<ZaposleniTermin>) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajZaposleniTermin(ztLista));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case UCITAJ_ZAPOSLENI_TERMIN:
                    System.out.println("Operacija učitaj zaposleni-termin");
                    zt=(ZaposleniTermin) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiListuZaposleniTermin(zt));
                    break;
                case UCITAJ_IZNAJMLJIVANJA:
                    System.out.println("Operacija učitaj iznajmljivanja");
                    odgovor.setRezultat(Controller.getInstance().vratiListuIznajmljivanja());
                    break;
                case UCITAJ_STAVKE_IZNAJMLJIVANJA:
                    System.out.println("Operacija učitaj stavke iznajmljivanja");
                    idIzn=(int) zahtev.getArgument();
                    odgovor.setRezultat(Controller.getInstance().vratiListuStavkiIznajmljivanja(idIzn));
                    break;
                case DEAKTIVIRAJ_NALOG:
                    try
                    {
                        System.out.println("Operacija deaktiviraj nalog");
                        mapa=(Map<Boolean, Recepcioner>) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().deaktivirajNalog(mapa));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case KREIRAJ_IZNAJMLJIVANJE:
                    try
                    {
                        System.out.println("Operacija kreiraj iznajmljivanje");
                        i= (Iznajmljivanje) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajIznajmljivanje(i));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;
                case KREIRAJ_STAVKE_IZNAJMLJIVANJA:
                    try
                    {
                        System.out.println("Operacija kreiraj stavke iznajmljivanja");
                        stavke= (List<StavkaIznajmljivanja>) zahtev.getArgument();
                        odgovor.setRezultat(Controller.getInstance().kreirajStavkeIznajmljivanja(stavke));
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    }
                    break;  
                case OBRISI_IZNAJMLJIVANJE:
                    try
                    {
                        System.out.println("Operacija obriši iznajmljivanje");
                        i=(Iznajmljivanje) zahtev.getArgument();
                        Controller.getInstance().obrisiIznajmljivanje(i);
                        odgovor.setRezultat(null);
                    }
                    catch(Exception ex)
                    {
                        odgovor.setException(ex);
                    } 
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
