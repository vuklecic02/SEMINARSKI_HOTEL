/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import java.util.ArrayList;
import java.util.List;
import model.Recepcioner;
import operacija.OpstaSistemskaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

/**
 *
 * @author vuk
 */
public class VratiFilterListuRecepcioner extends OpstaSistemskaOperacija {

    //private Recepcioner recepcioner;
    private List<Recepcioner> lista=new ArrayList<>();
    public List<Recepcioner> getLista() {
        return lista;
    }    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        Recepcioner r=(Recepcioner) param;
        String uslov=" WHERE ime LIKE '%"+r.getIme()+"%' OR prezime LIKE '%"+r.getPrezime()+"%' "
                + "OR CONCAT(ime, ' ', prezime) LIKE '%"+(r.getIme()+" "+r.getPrezime()).trim()+"%'";
        lista=dbbroker.vratiSve(param, uslov);
    }
    
}
