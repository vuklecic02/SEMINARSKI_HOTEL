/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import model.OpstiDomenskiObjekat;
import model.Recepcioner;
import operacija.OpstaSistemskaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

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
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat) param))
        {
            throw new Exception("Vec postoji slog u bazi!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        
        recepcioner=(Recepcioner)param;
        dbbroker.kreiraj(recepcioner);
        return;
    }
    
}
