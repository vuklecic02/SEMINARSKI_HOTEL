/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glavniKontroler;

import kontroleri.GlavnaFormaKontroler;
import kontroleri.LoginKontroler;
import kontroleri.RecepcionerKontroler;
import kontroleri.RecepcionerNalogKontroler;
import kontroleri.RegistracijaKontroler;
import kontroleri.TerminDezurstvaKontroler;
import kontroleri.TerminDezurstvaKreirajKontroler;
import model.Recepcioner;
import view.GlavnaForma;
import view.LoginForma;
import view.RecepcionerForma;
import view.RecepcionerNalogForma;
import view.RegistracijaForma;
import view.TerminDezurstvaForma;
import view.TerminDezurstvaKreirajForma;

/**
 *
 * @author vuk
 */
public class GlavniKontroler {
    private static GlavniKontroler instance;
    private LoginKontroler lk;
    private RegistracijaKontroler rk;
    private GlavnaFormaKontroler gfk;
    private Recepcioner ulogovaniRecepcioner;
    private RecepcionerKontroler reck;
    private RecepcionerNalogKontroler rnk;
    private TerminDezurstvaKontroler tdk;
    private TerminDezurstvaKreirajKontroler tdkk;

    public void setUlogovaniRecepcioner(Recepcioner ulogovaniRecepcioner) {
        this.ulogovaniRecepcioner = ulogovaniRecepcioner;
    }

    public Recepcioner getUlogovaniRecepcioner() {
        return ulogovaniRecepcioner;
    }

    private GlavniKontroler() {
    }

    public static GlavniKontroler getInstance() {
        if(instance==null)
            instance=new GlavniKontroler();
        return instance;
    }

    public void otvoriLoginFormu() {
        lk=new LoginKontroler(new LoginForma());
        lk.otvoriFormu();
    }

    public void otvoriFormuZaRegistraciju() {
        rk=new RegistracijaKontroler(new RegistracijaForma());
        rk.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        gfk=new GlavnaFormaKontroler(new GlavnaForma());
        gfk.otvoriFormu();
    }

    public void otvoriRecepcionerFormu() {
        reck=new RecepcionerKontroler(new RecepcionerForma());
        reck.otvoriFormu();
    }

    public void otvoriRecepcionerNalogFormu(Recepcioner recepcionerSelektovani, Recepcioner recepcionerUlogovani) {
        rnk=new RecepcionerNalogKontroler(new RecepcionerNalogForma(reck.getRf(),true,recepcionerSelektovani,recepcionerUlogovani));
        rnk.otvoriFormu();
    }

    public void otvoriTerminDezurstvaFormu() {
        tdk=new TerminDezurstvaKontroler(new TerminDezurstvaForma());
        tdk.otvoriFormu();

    }

    public void otvoriTerminDezKreirajFormu() {
        tdkk=new TerminDezurstvaKreirajKontroler(new TerminDezurstvaKreirajForma(tdk.getTdf(), true,tdk));
        tdkk.otvoriFormu();
    }
    
}
