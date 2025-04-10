/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.soba;

import java.util.ArrayList;
import java.util.List;
import model.Soba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviSoba extends OpstaSistemskaOperacija {

    List<Soba> lista=new ArrayList<>();

    public List<Soba> getLista() {
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
