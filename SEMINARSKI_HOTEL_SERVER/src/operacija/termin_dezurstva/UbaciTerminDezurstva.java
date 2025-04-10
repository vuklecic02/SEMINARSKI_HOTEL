/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termin_dezurstva;

import model.OpstiDomenskiObjekat;
import model.TerminDezurstva;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class UbaciTerminDezurstva extends OpstaSistemskaOperacija {

    private TerminDezurstva td;

    public TerminDezurstva getTd() {
        return td;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        td=(TerminDezurstva)param;
        if(td.getSmena().isBlank() || td.getSmena().isEmpty())
        {
            throw new Exception("Sistem ne može da kreira termin dežurstva");
        }
        if(td.getSmena().matches("\\d{2}-\\d{2}"))
        {
            String[] sati=td.getSmena().split("-");
            int pocetak=Integer.parseInt(sati[0]);
            int kraj=Integer.parseInt(sati[1]);
            if(trajanjeSmena(pocetak,kraj)>10)
            {
                throw new Exception("Sistem ne može da kreira termin dežurstva");
            }
        }
        else
        {
            throw new Exception("Sistem ne može da kreira termin dežurstva");
        }
        if(dbbroker.daLiPostoji((OpstiDomenskiObjekat) param))
        {
            throw new Exception("Sistem ne može da kreira termin dežurstva");
        }        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        td=(TerminDezurstva)param;
        dbbroker.kreiraj(td);
    }
    
    private static int trajanjeSmena(int pocetak, int kraj) 
    {
        if(kraj>=pocetak)
            return kraj-pocetak;
        else
            return (24+kraj)-pocetak;
    }
    
}
