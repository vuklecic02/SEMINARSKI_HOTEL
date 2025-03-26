/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.List;
import repozitorijum.Repozitorijum;
import repozitorijum.db.DBRepozitorijum;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

/**
 *
 * @author vuk
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repozitorijum dbbroker;

    public ApstraktnaGenerickaOperacija() {
        this.dbbroker = new DBRepozitorijumGenericki();
    }
    
    protected abstract void preduslovi(Object param) throws Exception;
    protected abstract void izvrsiOperaciju(Object param,String kljuc) throws Exception;
    
    private void zapocniTrans() throws Exception
    {
        ((DBRepozitorijum)dbbroker).connect();
    }
    
    private void izvrsiTrans() throws Exception
    {
        ((DBRepozitorijum)dbbroker).commit();
    }
    
    private void ponistiTrans() throws Exception
    {
        ((DBRepozitorijum)dbbroker).rollback();
    }
    
    private void ugasiKonekciju() throws Exception
    {
        ((DBRepozitorijum)dbbroker).disconnect();
    }
    
    public final void izvrsi(Object param,String kljuc) throws Exception
    {
        try
        {
            preduslovi(param);
            zapocniTrans();
            izvrsiOperaciju(param, kljuc);
            izvrsiTrans();    
        }
        catch(Exception e)
        {
            ponistiTrans();
            throw e;
        }
    }
}
