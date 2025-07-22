/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.soba;

import model.Soba;
import operacija.OpstaSistemskaOperacija;
import java.sql.SQLException;

/**
 *
 * @author vuk
 */
public class ObrisiSoba extends OpstaSistemskaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Soba))
        {
            throw new Exception("Sistem ne može da obriše termin dežurstva");
        }          
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        try
        {
            dbbroker.obrisi(param);
        }
        catch (SQLException ex) 
        {
            if (ex instanceof java.sql.SQLIntegrityConstraintViolationException) 
            {
                throw new Exception("Nije moguće obrisati sobu jer postoje aktivne rezervacije/iznajmljivanja vezane za nju. "
                        + "Prvo obrišite sve stavke iznajmljivanja za ovu sobu.", ex);
            } 
            else 
            {
                throw new Exception("Greška prilikom brisanja sobe: " + ex.getMessage(), ex);
            }
        }
        
    }
    
}

