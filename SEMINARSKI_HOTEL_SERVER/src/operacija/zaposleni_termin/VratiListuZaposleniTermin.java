/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zaposleni_termin;

import java.util.List;
import model.ZaposleniTermin;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuZaposleniTermin extends OpstaSistemskaOperacija {

    private List<ZaposleniTermin> lista;

    public List<ZaposleniTermin> getLista() {
        return lista;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        ZaposleniTermin zt=(ZaposleniTermin)param;
        String uslov="JOIN recepcioner ON zaposlenitermin.recepcioner=recepcioner.idRecepcioner \n" +
        "JOIN termindezurstva ON zaposlenitermin.terminDezurstva=termindezurstva.idTerminDezurstva\n" +
        " WHERE zaposlenitermin.recepcioner="+zt.getRecepcioner().getIdRecepcioner();
        lista=dbbroker.vratiSve(param, uslov);
    }
    
}
