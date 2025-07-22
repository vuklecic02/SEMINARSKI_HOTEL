/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import model.Osoba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class ObrisiOsoba extends OpstaSistemskaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Osoba))
        {
            throw new Exception("Sistem ne može da obriše osobu");
        }        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        dbbroker.obrisi(param);
    }
    
}
