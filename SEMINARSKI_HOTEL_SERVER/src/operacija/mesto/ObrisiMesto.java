/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.util.List;
import logika.Controller;
import model.Mesto;
import model.Osoba;
import operacija.OpstaSistemskaOperacija;
import operacija.osoba.VratiListuSviOsoba;

/**
 *
 * @author vuk
 */
public class ObrisiMesto extends OpstaSistemskaOperacija {

    private List<Osoba> listaOsoba;
    private Mesto mesto;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Mesto))
        {
            throw new Exception("Sistem ne može da obriše mesto");
        }
        listaOsoba=Controller.getInstance().vratiListuOsoba();
        mesto=(Mesto)param;
        for(Osoba o:listaOsoba)
        {
            if(o.getMesto().equals(mesto))
            {
                System.out.println("STRUKTURNO OGRANIČENJE");
                throw new Exception("Sistem ne može da obriše mesto");
            }
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        dbbroker.obrisi(param);
    }
    
}
