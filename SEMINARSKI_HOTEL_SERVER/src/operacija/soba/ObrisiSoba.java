/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.soba;

import model.Soba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class ObrisiSoba extends OpstaSistemskaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Soba))
        {
            throw new Exception("Sistem ne može da obriše termin dežurstva");
        }          
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        dbbroker.obrisi(param);
    }
    
}

