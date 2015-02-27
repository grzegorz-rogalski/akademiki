/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetk.kontroler;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import projekt.osoba.baza.BazaPearson;
import projekt.osoba.baza.OsobaSes;

/**
 *
 * @author Komputer
 */
@ManagedBean
@SessionScoped
public class kontroler {
    //Zmienne !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @EJB
    private OsobaSes request;
    
    private BazaPearson osoba;
    private List<BazaPearson> listaOsob = new ArrayList<>();
    //Zmienne !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    public kontroler() {
        osoba=new BazaPearson();
    }
    
    //Funkcje obslugi zdarzen ###########################
    public String dodajOsobe()
    {
        request.createOsoba(osoba);
        return null;
    }
    
    public String wszystkieOsoby()
    {
        listaOsob=request.allPeople();
        return null;
    }
    
    
    //Funkcje obslugi zdarzen ###########################
    
    //GETERY SETERY @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public BazaPearson getOsoba() {
        return osoba;
    }

    public void setOsoba(BazaPearson osoba) {
        this.osoba = osoba;
    }

    public List<BazaPearson> getListaOsob() {
        return listaOsob;
    }

    public void setListaOsob(List<BazaPearson> listaOsob) {
        this.listaOsob = listaOsob;
    }
    //GETERY SETERY @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
}
