/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin.kontroler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import projekt.osoba.baza.BazaPearson;

/**
 *
 * @author Komputer
 */
@ManagedBean
@SessionScoped
public class adminKontroler {
    BazaPearson osoba=new BazaPearson();

    public BazaPearson getOsoba() {
        return osoba;
    }

    public void setOsoba(BazaPearson osoba) {
        this.osoba = osoba;
    }

    
    
}
