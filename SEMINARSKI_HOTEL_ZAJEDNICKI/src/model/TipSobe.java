/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author vuk
 */
public enum TipSobe {
    JEDNOKREVETNA, DVOKREVETNA, TROKREVETNA, ČETVOROKREVETNA, VIP_DVOKREVETNA;
    
    @Override
    public String toString() {
        return name().replace("_", " ");
    }
    
    public static TipSobe izBazeString(String dbVrednost) {
        return TipSobe.valueOf(dbVrednost.replace(" ", "_"));
    }    
}
