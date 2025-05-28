/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavka_iznajmljivanja;

import java.util.List;
import model.StavkaIznajmljivanja;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class KreirajStavkeIznajmljivanja extends OpstaSistemskaOperacija {

    private List<StavkaIznajmljivanja> stavke;

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof List))
        {
            throw new Exception("Sistem ne može da kreira stavke iznajmljivanja");
        }    
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        stavke=(List<StavkaIznajmljivanja>) param;
        dbbroker.kreirajVise(stavke);
    }
    
}
