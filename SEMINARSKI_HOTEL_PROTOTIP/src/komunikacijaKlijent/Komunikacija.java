/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacijaKlijent;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacijaZajednicki.Odgovor;
import komunikacijaZajednicki.Operacija;
import komunikacijaZajednicki.Posiljalac;
import komunikacijaZajednicki.Primalac;
import komunikacijaZajednicki.Zahtev;
import model.Mesto;
import model.Osoba;
import model.Recepcioner;
import model.Soba;
import model.TerminDezurstva;

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

    public Recepcioner kreirajRecepcionera(Recepcioner recepcioner) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.KREIRAJ_RECEPCIONER, recepcioner);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Recepcioner) odgovor.getRezultat();
        }
        throw odgovor.getException();         
    }

    public List<Recepcioner> vratiListuRecepcionera() throws Exception {
        List<Recepcioner> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RECEPCIONERE);
        posiljalac.salje(zahtev);
        Odgovor odgovor = (Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Recepcioner>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();                 
    }

    public List<Recepcioner> vratiFilterListuRecepcionera(Recepcioner recepcioner) throws Exception {
        List<Recepcioner> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RECEPCIONERE_FILTER,recepcioner);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Recepcioner>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();         
    }

    public List<TerminDezurstva> vratiListuTerminaDezurstava() throws Exception {
        List<TerminDezurstva> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TERMINE_DEZURSTAVA);
        posiljalac.salje(zahtev);
        Odgovor odgovor = (Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<TerminDezurstva>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();        
    }

    public List<TerminDezurstva> vratiFilterListuTerminDez(TerminDezurstva td) throws Exception {
        List<TerminDezurstva> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TERMINE_DEZURSTAVA_FILTER,td);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<TerminDezurstva>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();         
    }

    public TerminDezurstva kreirajTerminDezurstva(TerminDezurstva td) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.KREIRAJ_TERMIN_DEZURSTVA, td);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (TerminDezurstva) odgovor.getRezultat();
        }
        throw odgovor.getException();        
    }

    public void obrisiTerminDezurstva(TerminDezurstva selektovani) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_TERMIN_DEZURSTVA, selektovani);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNO BRISANJE");
                return;
            }
        }
        throw odgovor.getException();          
    }

    public void promeniTerminDezurstva(TerminDezurstva td) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.PROMENI_TERMIN_DEZURSTVA, td);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNA IZMENA");
                return;
            }
        }
        throw odgovor.getException();           
    }

    public void promeniRecepcionera(Recepcioner selektovani) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.PROMENI_RECEPCIONERA, selektovani);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNA IZMENA");
                return;
            }
        }
        throw odgovor.getException();         
    }

    public List<Mesto> vratiListuMesta() throws Exception {
        List<Mesto> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA);
        posiljalac.salje(zahtev);
        Odgovor odgovor = (Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Mesto>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();          
    }

    public Mesto kreirajMesto(Mesto m) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.KREIRAJ_MESTO, m);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Mesto) odgovor.getRezultat();
        }
        throw odgovor.getException();     
    }

    public void obrisiMesto(Mesto selektovano) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_MESTO, selektovano);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNO BRISANJE");
                return;
            }
        }
        throw odgovor.getException();     
    }

    public List<Mesto> vratiFilterListuMesto(Mesto m) throws Exception {
        List<Mesto> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA_FILTER,m);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Mesto>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();        
    }

    public List<Soba> vratiListuSoba() throws Exception {
        List<Soba>lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SOBE);
        posiljalac.salje(zahtev);
        Odgovor odgovor = (Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Soba>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException();     
    }

    public Soba kreirajSobu(Soba s) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.KREIRAJ_SOBU, s);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Soba) odgovor.getRezultat();
        }
        throw odgovor.getException(); 
    }

    public void obrisiSobu(Soba selektovana) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_SOBU, selektovana);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNO BRISANJE");
                return;
            }
        }
        throw odgovor.getException();
    }

    public List<Soba> vratiFilterListuSoba(Soba s) throws Exception {
        List<Soba> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SOBE_FILTER,s);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Soba>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException(); 
    }

    public List<Osoba> vratiListuOsoba() throws Exception {
        List<Osoba>lista;
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_OSOBE);
        posiljalac.salje(zahtev);
        Odgovor odgovor = (Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Osoba>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException(); 
    }

    public Osoba kreirajOsobu(Osoba osoba) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.KREIRAJ_OSOBU, osoba);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            return (Osoba) odgovor.getRezultat();
        }
        throw odgovor.getException(); 
    }

    public void obrisiOsobu(Osoba selektovani) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_OSOBU, selektovani);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNO BRISANJE");
                return;
            }
        }
        throw odgovor.getException(); 
    }

    public List<Osoba> vratiFilterListuOsoba(Osoba o) throws Exception {
        List<Osoba> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_OSOBE_FILTER,o);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            lista = (List<Osoba>) odgovor.getRezultat();
            return lista;
        }
        throw odgovor.getException(); 
    }

    public void promeniOsobu(Osoba selektovani) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.PROMENI_OSOBU, selektovani);
        posiljalac.salje(zahtev);
        Odgovor odgovor=(Odgovor) primalac.prima();
        if (odgovor.getException() == null) {
            if(odgovor.getRezultat()==null)
            {
                System.out.println("USPESNA IZMENA");
                return;
            }
        }
        throw odgovor.getException(); 
    }
       

    
}
