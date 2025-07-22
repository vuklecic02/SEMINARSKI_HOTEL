/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import java.util.ArrayList;
import java.util.List;
import model.Iznajmljivanje;
import model.OpstiDomenskiObjekat;
import model.StavkaIznajmljivanja;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviIznajmljivanje extends OpstaSistemskaOperacija{

    private List<Iznajmljivanje> listaIznajmljivanja=new ArrayList<>();
    private List<StavkaIznajmljivanja> listaStavki=new ArrayList<>();

    public List<Iznajmljivanje> getLista() {
        return listaIznajmljivanja;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        String uslov="JOIN recepcioner ON iznajmljivanje.recepcioner=recepcioner.idRecepcioner JOIN "
                + "osoba ON iznajmljivanje.osoba=osoba.idOsoba JOIN mesto ON osoba.mesto=mesto.idMesta";
        
        List<OpstiDomenskiObjekat> odoLista=dbbroker.vratiSve(param, uslov);
        
        
        for (OpstiDomenskiObjekat odo : odoLista) {
            Iznajmljivanje i = (Iznajmljivanje) odo;
            List<StavkaIznajmljivanja> stavke = vratiStavkeZaIznajmljivanje(i.getIdIznajmljivanje(), i);
            i.setStavke(stavke);
            listaIznajmljivanja.add(i);
        }        
    }

    private List<StavkaIznajmljivanja> vratiStavkeZaIznajmljivanje(int idIznajmljivanje, Iznajmljivanje i) throws Exception {
        String uslov="JOIN soba ON stavkaiznajmljivanja.soba=soba.idSoba "
                + "JOIN iznajmljivanje ON stavkaiznajmljivanja.iznajmljivanje=iznajmljivanje.idIznajmljivanje"
                + " WHERE iznajmljivanje="+idIznajmljivanje;
        
        List<OpstiDomenskiObjekat> odoLista = dbbroker.vratiSve(new StavkaIznajmljivanja(), uslov);

        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : odoLista) {
            StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) odo;
            stavka.setIznajmljivanje(i);
            stavke.add(stavka);
        }
        return stavke;
        
    }
    
}
