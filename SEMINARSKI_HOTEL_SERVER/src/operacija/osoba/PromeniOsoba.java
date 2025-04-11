/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import model.OpstiDomenskiObjekat;
import model.Osoba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class PromeniOsoba extends OpstaSistemskaOperacija {
    private Osoba o;

    public Osoba getO() {
        return o;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Osoba))
        {
            throw new Exception("Sistem ne može da zapamti osobu");
        }
        o=(Osoba)param;
        
        if(!validacija(o))
        {
            throw new Exception("Sistem ne može da zapamti osobu");
        }
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat)param))
        {
            throw new Exception("Sistem ne može da zapamti osobu");
        }        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        dbbroker.izmeni(param);
    }
    
    private boolean validacija(Osoba o) {
        if(o.getIme().isEmpty() || o.getPrezime().isEmpty() || o.getTelefon().isEmpty() || o.getBrLicneIsprave().isEmpty()
                || o.getMesto()==null)
        {
            return false;
        }
        if(!o.getIme().matches("^[a-zA-Z ]+$"))
        {
            return false;
        }
        if(!o.getPrezime().matches("^[a-zA-Z ]+$"))
        {
            return false;
        }
        if(!o.getTelefon().matches("\\+?[0-9]{9,15}"))
        {
            return false;
        }
        return true;
    }    
    
}
