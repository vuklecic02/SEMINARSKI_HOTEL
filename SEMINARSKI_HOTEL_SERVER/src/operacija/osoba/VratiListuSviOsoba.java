/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import java.util.ArrayList;
import java.util.List;
import model.Osoba;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviOsoba extends OpstaSistemskaOperacija {
    private List<Osoba> lista=new ArrayList<>();

    public List<Osoba> getLista() {
        return lista;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        String uslov=" JOIN mesto ON osoba.mesto=mesto.idMesta";
        lista=dbbroker.vratiSve(param, uslov);
        //lista=dbbroker.vratiOsobuSaMestom((Osoba)param);
    }
    
}
