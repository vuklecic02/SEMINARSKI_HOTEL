/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import model.Mesto;
import model.OpstiDomenskiObjekat;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class KreirajMesto extends OpstaSistemskaOperacija {
    private Mesto mesto;

    public Mesto getMesto() {
        return mesto;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Mesto))
        {
            throw new Exception("Sistem ne može da kreira mesto");
        }
        mesto=(Mesto) param;
        if(mesto.getNaziv().isEmpty() || mesto.getDrzava()==null)
        {
            throw new Exception("Sistem ne može da kreira mesto");
        }        
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat) param))
        {
            throw new Exception("Sistem ne može da kreira mesto");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        mesto=(Mesto)param;
        dbbroker.kreiraj(mesto);
    }
    
}
