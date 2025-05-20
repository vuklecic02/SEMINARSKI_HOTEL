/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author vuk
 */
public enum Rola {
    
    ADMIN, KORISNIK;

    @Override
    public String toString() {
        return name();
    }
    
    public static Rola izBazeString(String dbVrednost) {
        return Rola.valueOf(dbVrednost);
    }     
    
    
}
