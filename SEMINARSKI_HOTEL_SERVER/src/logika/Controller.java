/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.Recepcioner;

/**
 *
 * @author vuk
 */
public class Controller {
    
    private static Controller instance;
    private DBBroker dbbr;
    
    private Controller(){
        dbbr = new DBBroker();
    }

    public static Controller getInstance() {
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Recepcioner login(Recepcioner recepcioner) throws Exception {
      List<OpstiDomenskiObjekat> listaOdo = dbbr.citaj(recepcioner);
      List<Recepcioner> listaRecepcionera = new ArrayList<>();
      for(OpstiDomenskiObjekat odo: listaOdo){
          listaRecepcionera.add((Recepcioner) odo);
      }
      
      for(Recepcioner r : listaRecepcionera){
          if(r.getUsername().equals(recepcioner.getUsername()) && r.getPassword().equals(recepcioner.getPassword())){
              return r;
          }
      }
      throw new Exception("Instruktor sa unetim kredencijalima ne postoji.");
    }    

    public Recepcioner registruj(Recepcioner r) throws Exception {
        if(dbbr.kreiraj(r)){
            return r;
        }
        else
        {
            throw new Exception("Korisnik nije dobro unetio podatke.");
        }
        
    }
}
