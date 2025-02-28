/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author vuk
 */
public class Zahtev implements Serializable{
    
    private Operacija operacija;
    private Object argument;

    public Zahtev() {
    }
    
    public Zahtev(Operacija operacija) {
        this.operacija = operacija;
    }

    public Zahtev(Operacija operacija, Object argument) {
        this.operacija = operacija;
        this.argument = argument;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return operacija + " " + argument;
    }
    
    
    
}
