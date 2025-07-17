/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import model.OpstiDomenskiObjekat;
import model.Recepcioner;
import operacija.OpstaSistemskaOperacija;
import repozitorijum.db.impl.DBRepozitorijumImplementacija;

/**
 *
 * @author vuk
 */
public class KreirajRecepcioner extends OpstaSistemskaOperacija {
    private Recepcioner recepcioner;

    public Recepcioner getRecepcioner() {
        return recepcioner;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Recepcioner))
        {
            throw new Exception("Sistem ne može da kreira korisnika");
        }
        recepcioner=(Recepcioner)param;

        if(recepcioner.getIme().isEmpty() || recepcioner.getPrezime().isEmpty() || recepcioner.getUsername().isEmpty() ||
                recepcioner.getEmail().isEmpty() || recepcioner.getPassword().isEmpty() )
        {
            throw new Exception("Sistem ne može da kreira korisnika");
        }  
        if(recepcioner.getPassword().length()<8 || recepcioner.getPassword().contains(" ") || 
                !recepcioner.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.com$") || recepcioner.getUsername().length()>15)
        {
            throw new Exception("Sistem ne može da kreira korisnika");
        }
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat) param))
        {
            throw new Exception("Sistem ne može da kreira korisnika");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {       
        recepcioner=(Recepcioner)param;
        dbbroker.kreiraj(recepcioner);
    }
    
}
