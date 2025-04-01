/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_dezurstva;

import model.OpstiDomenskiObjekat;
import model.TerminDezurstva;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class KreirajTerminDezurstva extends OpstaSistemskaOperacija {

    private TerminDezurstva td;

    public TerminDezurstva getTd() {
        return td;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat) param))
        {
            throw new Exception("Vec postoji slog u bazi!");
        }        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        td=(TerminDezurstva)param;
        dbbroker.kreiraj(td);
        return;
    }
    
}
