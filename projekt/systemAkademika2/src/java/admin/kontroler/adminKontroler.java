/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin.kontroler;

import java.awt.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import org.primefaces.event.SelectEvent;
import projekt.osoba.baza.BazaPearson;

/**
 *
 * @author Komputer
 */
@ManagedBean
@SessionScoped
public class adminKontroler {
    BazaPearson osoba=new BazaPearson();
    private boolean edit;
    BazaPearson osoba2=new BazaPearson();

        
    public adminKontroler() {
        edit=false;
    }

    public void Edition()
    {
        if(edit==false)
            edit=true;
        else
            edit=false;
        
    }
    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
    public BazaPearson getOsoba() {
        return osoba;
    }

    public void setOsoba(BazaPearson osoba) {
        this.osoba = osoba;
    }

    public BazaPearson getOsoba2() {
        return osoba2;
    }

    public void setOsoba2(BazaPearson osoba2) {
        this.osoba2 = osoba2;
    }
    
    

    public String zapisz()
    {
        //osoba2.setImie(im);
        return"success";
    }
    
    public String kolor()
    {
        if(edit)
        {
            edit=false;
            return "red";
        }
        else
        {
            edit=true;
            return "green";
        }
    }
}
