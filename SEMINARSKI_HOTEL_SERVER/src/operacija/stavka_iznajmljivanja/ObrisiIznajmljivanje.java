/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavka_iznajmljivanja;

import model.Iznajmljivanje;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class ObrisiIznajmljivanje extends OpstaSistemskaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Iznajmljivanje))
        {
            throw new Exception("Sistem ne može da obriše inajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        dbbroker.obrisi(param);
    }
    
}
