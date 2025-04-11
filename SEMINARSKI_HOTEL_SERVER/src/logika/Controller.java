/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import java.util.ArrayList;
import java.util.List;
import model.Mesto;
import model.OpstiDomenskiObjekat;
import model.Osoba;
import model.Recepcioner;
import model.Soba;
import model.TerminDezurstva;
import operacija.mesto.KreirajMesto;
import operacija.mesto.ObrisiMesto;
import operacija.mesto.VratiListuMesto;
import operacija.mesto.VratiListuSviMesto;
import operacija.osoba.KreirajOsoba;
import operacija.osoba.ObrisiOsoba;
import operacija.osoba.PromeniOsoba;
import operacija.osoba.VratiListuOsoba;
import operacija.osoba.VratiListuSviOsoba;
import operacija.recepcioner.KreirajRecepcioner;
import operacija.recepcioner.PrijaviRecepcioner;
import operacija.recepcioner.PromeniRecepcioner;
import operacija.recepcioner.VratiListuRecepcioner;
import operacija.recepcioner.VratiListuSviRecepcioner;
import operacija.soba.KreirajSoba;
import operacija.soba.ObrisiSoba;
import operacija.soba.VratiListuSoba;
import operacija.soba.VratiListuSviSoba;
import operacija.termin_dezurstva.ObrisiTerminDezurstva;
import operacija.termin_dezurstva.PromeniTerminDezurstva;
import operacija.termin_dezurstva.UbaciTerminDezurstva;
import operacija.termin_dezurstva.VratiListuTerminDezurstva;
import operacija.termin_dezurstva.VratiListuSviTerminDezurstva;

/**
 *
 * @author vuk
 */
public class Controller {
    
    private static Controller instance;
    private Recepcioner ulogovaniRecepcioner;
    private Recepcioner kreiraniRecepcioner;
    private TerminDezurstva kreiraniTerminDez;
    private Mesto kreiranoMesto;
    private Soba kreiranaSoba;
    private Osoba kreiranaOsoba;
    
    private Controller(){
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Recepcioner login(Recepcioner recepcioner) throws Exception {
        PrijaviRecepcioner operacija=new PrijaviRecepcioner();
        operacija.izvrsi(recepcioner);
        ulogovaniRecepcioner=operacija.getRecepcioner();
        return ulogovaniRecepcioner;

    }    

    public Recepcioner kreirajRecepcionera(Recepcioner recepcioner) throws Exception {
        KreirajRecepcioner operacija=new KreirajRecepcioner();
        operacija.izvrsi(recepcioner);
        kreiraniRecepcioner=operacija.getRecepcioner();
        return kreiraniRecepcioner;
    }

    public List<Recepcioner> vratiListuRecepcionera() throws Exception {
        VratiListuSviRecepcioner operacija=new VratiListuSviRecepcioner();
        operacija.izvrsi(new Recepcioner());
        return operacija.getLista();
    }

    public List<Recepcioner> vratiFilterListuRecepcionera(Recepcioner recepcioner) throws Exception {
        VratiListuRecepcioner operacija=new VratiListuRecepcioner();
        operacija.izvrsi(recepcioner);
        return operacija.getLista();
    }

    public List<TerminDezurstva> vratiListuTerminaDezurstava() throws Exception {
        VratiListuSviTerminDezurstva operacija=new VratiListuSviTerminDezurstva();
        operacija.izvrsi(new TerminDezurstva());
        return operacija.getLista();
    }

    public List<TerminDezurstva> vratiFilterListuTerminDez(TerminDezurstva td) throws Exception {
        VratiListuTerminDezurstva operacija=new VratiListuTerminDezurstva();
        operacija.izvrsi(td);
        return operacija.getLista();
    }

    public TerminDezurstva kreirajTerminDezurstva(TerminDezurstva td) throws Exception {
        UbaciTerminDezurstva operacija=new UbaciTerminDezurstva();
        operacija.izvrsi(td);
        kreiraniTerminDez=operacija.getTd();
        return kreiraniTerminDez;
    }

    public void obrisiTerminDezurstva(TerminDezurstva td) throws Exception {
        ObrisiTerminDezurstva operacija=new ObrisiTerminDezurstva();
        operacija.izvrsi(td);   
    }

    public void promeniTerminDezurstva(TerminDezurstva td) throws Exception {
        PromeniTerminDezurstva operacija=new PromeniTerminDezurstva();
        operacija.izvrsi(td);
    }

    public void promeniRecepcionera(Recepcioner r) throws Exception {
        PromeniRecepcioner operacija=new PromeniRecepcioner();
        operacija.izvrsi(r);
    }

    public List<Mesto> vratiListuMesta() throws Exception {
        VratiListuSviMesto operacija=new VratiListuSviMesto();
        operacija.izvrsi(new Mesto());
        return operacija.getLista();
    }

    public Mesto kreirajMesto(Mesto m) throws Exception {
        KreirajMesto operacija=new KreirajMesto();
        operacija.izvrsi(m);
        kreiranoMesto=operacija.getMesto();
        return kreiranoMesto;
    }

    public void obrisiMesto(Mesto m) throws Exception {
        ObrisiMesto operacija=new ObrisiMesto();
        operacija.izvrsi(m); 
    }

    public List<Mesto> vratiFilterListuMesto(Mesto m) throws Exception {
        VratiListuMesto operacija=new VratiListuMesto();
        operacija.izvrsi(m);
        return operacija.getLista();
    }

    public List<Soba> vratiListuSoba() throws Exception {
        VratiListuSviSoba operacija=new VratiListuSviSoba();
        operacija.izvrsi(new Soba());
        return operacija.getLista();
    }

    public Soba kreirajSobu(Soba s) throws Exception{
        KreirajSoba operacija=new KreirajSoba();
        operacija.izvrsi(s);
        kreiranaSoba=operacija.getSoba();
        return kreiranaSoba;
    }

    public void obrisiSobu(Soba s) throws Exception {
        ObrisiSoba operacija=new ObrisiSoba();
        operacija.izvrsi(s);
    }

    public List<Soba> vratiFilterListuSoba(Soba s) throws Exception {
        var operacija=new VratiListuSoba();
        operacija.izvrsi(s);
        return operacija.getLista();
    }

    public List<Osoba> vratiListuOsoba() throws Exception {
        VratiListuSviOsoba operacija=new VratiListuSviOsoba();
        operacija.izvrsi(new Osoba());
        return operacija.getLista();
    }

    public Osoba kreirajOsobu(Osoba o) throws Exception {
        KreirajOsoba operacija=new KreirajOsoba();
        operacija.izvrsi(o);
        kreiranaOsoba=operacija.getO();
        return kreiranaOsoba;
    }

    public void obrisiOsobu(Osoba o) throws Exception {
        ObrisiOsoba operacija=new ObrisiOsoba();
        operacija.izvrsi(o);
    }

    public List<Osoba> vratiFilterListuOsoba(Osoba o) throws Exception {
        VratiListuOsoba operacija=new VratiListuOsoba();
        operacija.izvrsi(o);
        return operacija.getLista();
    }

    public void promeniOsobu(Osoba o) throws Exception {
        PromeniOsoba operacija=new PromeniOsoba();
        operacija.izvrsi(o);
    }
}
