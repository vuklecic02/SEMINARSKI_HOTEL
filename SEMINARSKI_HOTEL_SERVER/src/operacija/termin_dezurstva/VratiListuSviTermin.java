/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_dezurstva;

import java.util.ArrayList;
import java.util.List;
import model.TerminDezurstva;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviTermin extends OpstaSistemskaOperacija {

    private List<TerminDezurstva> lista=new ArrayList<>();

    public List<TerminDezurstva> getLista() {
        return lista;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        lista=dbbroker.vratiSve(param, null);
    }
    
}
