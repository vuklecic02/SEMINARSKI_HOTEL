/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.util.ArrayList;
import java.util.List;
import model.Mesto;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviMesto extends OpstaSistemskaOperacija {
    private List<Mesto> lista=new ArrayList<>();

    public List<Mesto> getLista() {
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
