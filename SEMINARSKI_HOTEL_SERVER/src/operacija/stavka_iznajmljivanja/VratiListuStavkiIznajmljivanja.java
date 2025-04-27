/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavka_iznajmljivanja;

import java.util.ArrayList;
import java.util.List;
import model.StavkaIznajmljivanja;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuStavkiIznajmljivanja extends OpstaSistemskaOperacija {

    List<StavkaIznajmljivanja> lista;

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        String uslov="JOIN soba ON stavkaiznajmljivanja.soba=soba.idSoba "
                + "JOIN iznajmljivanje ON stavkaiznajmljivanja.iznajmljivanje=iznajmljivanje.idIznajmljivanje"
                + " WHERE iznajmljivanje="+(int)param;
        lista=dbbroker.vratiSve(new StavkaIznajmljivanja(), uslov);
    }
    
}
