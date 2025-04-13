/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zaposleni_termin;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.ZaposleniTermin;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class KreirajZaposleniTermin extends OpstaSistemskaOperacija {

    private List<ZaposleniTermin> zt;

    public List<ZaposleniTermin> getZt() {
        return zt;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof List))
        {
            throw new Exception("Sistem ne može da kreira zaposleni-termin");
        }
        if(!validacija(param))
        {
            System.out.println("Greška u validaciji");
            throw new Exception("Sistem ne može da kreira zaposleni-termin");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        zt=(List<ZaposleniTermin>)param;
        dbbroker.kreirajVise(zt);
    }
    
    private boolean validacija(Object param) {
        List<?> rawList = (List<?>) param;
        if (rawList.isEmpty()) {
            return false;
        }

        List<ZaposleniTermin> lista;
        try {
            lista = (List<ZaposleniTermin>) param;
        } catch (ClassCastException e) {
            return false;
        }

        Set<String> kombinacijeRecepcionerDatum = new HashSet<>();
        LocalDate danas = LocalDate.now();

        for (ZaposleniTermin zt : lista) {
            if (zt == null 
                || zt.getRecepcioner() == null 
                || zt.getRecepcioner().getIdRecepcioner() == 0
                || zt.getTerminDezurstva() == null 
                || zt.getTerminDezurstva().getIdTerminDezurstva() == 0
                || zt.getDatum() == null) 
            {
                return false;
            }

            if (!zt.getDatum().isAfter(danas)) {
                return false;
            }

            String kombinacija = zt.getRecepcioner().getIdRecepcioner() + "|" + zt.getDatum();
            if (kombinacijeRecepcionerDatum.contains(kombinacija)) {
                return false;
            }

            kombinacijeRecepcionerDatum.add(kombinacija);
        }

        return true;
    }    
    
}
