/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import java.util.ArrayList;
import java.util.List;
import model.Iznajmljivanje;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class VratiListuSviIznajmljivanje extends OpstaSistemskaOperacija{

    private List<Iznajmljivanje> lista=new ArrayList<>();

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        String uslov="JOIN recepcioner ON iznajmljivanje.recepcioner=recepcioner.idRecepcioner JOIN "
                + "osoba ON iznajmljivanje.osoba=osoba.idOsoba JOIN mesto ON osoba.mesto=mesto.idMesta";
        lista=dbbroker.vratiSve(param, uslov);
    }
    
}
