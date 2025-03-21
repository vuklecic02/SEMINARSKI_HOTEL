/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum.db;

import baza.DBKonekcija;
import java.sql.SQLException;
import repozitorijum.Repozitorijum;

/**
 *
 * @author vuk
 * @param <T>
 */
public interface DBRepozitorijum<T> extends Repozitorijum<T> {
    default public void connect() {
        DBKonekcija.getInstance().getConnection();
    }

    default public void disconnect() throws SQLException {
        DBKonekcija.getInstance().getConnection().close();
    }

    default public void commit() throws SQLException {
       DBKonekcija.getInstance().getConnection().commit();
    }

    default public void rollback() throws SQLException {
        DBKonekcija.getInstance().getConnection().rollback();
    }
}
