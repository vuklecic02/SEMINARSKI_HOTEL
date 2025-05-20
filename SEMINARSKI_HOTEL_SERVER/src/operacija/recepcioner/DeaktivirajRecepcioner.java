/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import java.util.Map;
import model.OpstiDomenskiObjekat;
import model.Recepcioner;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class DeaktivirajRecepcioner extends OpstaSistemskaOperacija {

    private boolean aktivacija;

    public boolean isAktivacija() {
        return aktivacija;
    }

    public void setAktivacija(boolean aktivacija) {
        this.aktivacija = aktivacija;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        Map<Boolean, Recepcioner> mapa=(Map<Boolean, Recepcioner>) param;
        String uslov;
        if(mapa.containsKey(Boolean.TRUE))
        {
            uslov="aktivan="+false;
            aktivacija=dbbroker.izmeniAktivaciju(mapa.get(Boolean.TRUE), uslov);
        }
        else
        {
            uslov="aktivan="+true;
            aktivacija=dbbroker.izmeniAktivaciju(mapa.get(Boolean.FALSE), uslov);
        }
        
        
    }
    
}
