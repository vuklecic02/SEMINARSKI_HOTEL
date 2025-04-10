/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.soba;

import model.Mesto;
import model.OpstiDomenskiObjekat;
import model.Soba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class KreirajSoba extends OpstaSistemskaOperacija {

    private Soba soba;

    public Soba getSoba() {
        return soba;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Soba))
        {
            throw new Exception("Sistem ne može da kreira sobu");
        }
        soba=(Soba)param;
        if(soba.getTipSobe()==null || soba.getCenaDan()<=0)
        {
            throw new Exception("Sistem ne može da kreira sobu");
        }
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat) param))
        {
            throw new Exception("Sistem ne može da kreira sobu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        soba=(Soba)param;
        dbbroker.kreiraj(soba);
    }
    
}
