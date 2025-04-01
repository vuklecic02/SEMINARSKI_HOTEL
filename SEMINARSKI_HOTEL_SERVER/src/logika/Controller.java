/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import java.util.ArrayList;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.Recepcioner;
import model.TerminDezurstva;
import operacija.recepcioner.KreirajRecepcioner;
import operacija.recepcioner.LoginOperacija;
import operacija.recepcioner.VratiFilterListuRecepcioner;
import operacija.recepcioner.VratiListuSviRecepcioner;
import operacija.termin_dezurstva.KreirajTerminDezurstva;
import operacija.termin_dezurstva.VratiFilterListuTerminDez;
import operacija.termin_dezurstva.VratiListuSviTermin;

/**
 *
 * @author vuk
 */
public class Controller {
    
    private static Controller instance;
    private Recepcioner ulogovaniRecepcioner;
    private Recepcioner kreiraniRecepcioner;
    private TerminDezurstva kreiraniTerminDez;
    
    private Controller(){
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Recepcioner login(Recepcioner recepcioner) throws Exception {
        LoginOperacija operacija=new LoginOperacija();
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
        VratiFilterListuRecepcioner operacija=new VratiFilterListuRecepcioner();
        operacija.izvrsi(recepcioner);
        return operacija.getLista();
    }

    public List<TerminDezurstva> vratiListuTerminaDezurstava() throws Exception {
        VratiListuSviTermin operacija=new VratiListuSviTermin();
        operacija.izvrsi(new TerminDezurstva());
        return operacija.getLista();
    }

    public Object vratiFilterListuTerminDez(TerminDezurstva td) throws Exception {
        VratiFilterListuTerminDez operacija=new VratiFilterListuTerminDez();
        operacija.izvrsi(td);
        return operacija.getLista();
    }

    public Object kreirajTerminDezurstva(TerminDezurstva td) throws Exception {
        KreirajTerminDezurstva operacija=new KreirajTerminDezurstva();
        operacija.izvrsi(td);
        kreiraniTerminDez=operacija.getTd();
        return kreiraniTerminDez;
    }
}
