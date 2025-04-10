/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcioner;

import java.util.List;
import model.Recepcioner;
import operacija.OpstaSistemskaOperacija;

/**
 *
 * @author vuk
 */
public class PrijaviRecepcioner extends OpstaSistemskaOperacija {

    private Recepcioner recepcioner;

    public Recepcioner getRecepcioner() {
        return recepcioner;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        recepcioner=(Recepcioner)param;
        if(recepcioner.getUsername().isEmpty() || recepcioner.getPassword().isEmpty())
        {
            throw new Exception("Sistem ne može da prijavi korisnika");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {

         String uslov="username=? AND password=?";
         String username=((Recepcioner)param).getUsername();
         String password=((Recepcioner)param).getPassword();
         recepcioner=(Recepcioner) dbbroker.vratiJednog((Recepcioner)param, uslov, username,password);
    }
    
}
