/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author vuk
 * @param <T>
 */
public interface Repozitorijum<T> {
    List<T> vratiSve(T param,String uslov) throws Exception;
    List<T> vratiSve();
    void kreiraj(T param) throws Exception;
    void izmeni(T param ) throws Exception;
    void obrisi(T param) throws Exception;
    boolean daLiPostoji(T param);
    T vratiJednog(T param,String uslov,Object... vrednosti) throws Exception;
    
    
}
