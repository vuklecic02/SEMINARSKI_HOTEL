/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import java.util.List;
import model.Recepcioner;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vuk
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    private Recepcioner recepcioner;

    public Recepcioner getRecepcioner() {
        return recepcioner;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Recepcioner> sviRecepcioneri=dbbroker.vratiSve((Recepcioner) param, null);
        System.out.println(sviRecepcioneri);
        
        for(Recepcioner r:sviRecepcioneri)
        {
            if(r.equals((Recepcioner)param))
            {
                recepcioner=r;
                return;
            }
        }
        recepcioner=null;
    }
    
}
