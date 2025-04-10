/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import model.Mesto;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class ObrisiMesto extends OpstaSistemskaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Mesto))
        {
            throw new Exception("Sistem ne može da obriše termin dežurstva");
        }        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        dbbroker.obrisi(param);
    }
    
}
