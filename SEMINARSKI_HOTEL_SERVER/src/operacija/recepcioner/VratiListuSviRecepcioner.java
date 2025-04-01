/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import java.util.ArrayList;
import java.util.List;
import model.Recepcioner;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviRecepcioner extends OpstaSistemskaOperacija {

    private List<Recepcioner> lista=new ArrayList<>();

    public List<Recepcioner> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        lista=dbbroker.vratiSve(param,null);
    }
    
}
