/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class KreirajIznajmljivanje extends OpstaSistemskaOperacija {

    private Iznajmljivanje iznajmljivanje;

    public Iznajmljivanje getIznajmljvanje() {
        return iznajmljivanje;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Iznajmljivanje))
        {
            throw new Exception("Sistem ne može da kreira iznajmljivanje");
        }       
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        iznajmljivanje=(Iznajmljivanje)param;
        dbbroker.kreiraj(iznajmljivanje);
        
        for(StavkaIznajmljivanja stavka : iznajmljivanje.getStavke())
        {
            stavka.setIznajmljivanje(iznajmljivanje);
        }
        
        dbbroker.kreirajVise(iznajmljivanje.getStavke());
    }
    
    
}
