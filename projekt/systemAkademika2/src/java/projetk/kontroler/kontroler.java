/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetk.kontroler;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import projekt.messages.messageKontroler;
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
    private messageKontroler message;
    
    private BazaPearson osoba;
    private List<BazaPearson> listaOsob = new ArrayList<>();
    //Zmienne !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    public kontroler() {
        this.message = new messageKontroler();
        osoba=new BazaPearson();
    }
    
    //Funkcje obslugi zdarzen bazy ###########################
    
    //dodaje osobe do bazy
    public String dodajOsobe()
    {
        request.createOsoba(osoba);
        return null;
    }
    
    //tworzy liste wszystkich osob z bazy
    public String wszystkieOsoby()
    {
        listaOsob=request.allPeople();
        return null;
    }
    
    public String osobaPoId(int id)
    {
        osoba=request.findID(id);
        return null;
    }
    
    public BazaPearson osobaPoLogin(String log)
    {
        listaOsob=request.findLog(log);
        if(listaOsob.isEmpty())
        {
            message.logwanieError();
            return null;
        }
        BazaPearson temp=listaOsob.get(0);
        return temp;
    }
    
    //Funkcje obslugi zdarzen bazy ###########################
    
    
    //funkcje przejscia &&&&&&&&&&&&&&&&&&&&&&&&
    
    
    
    public String logowanie()
    {
        BazaPearson temp=osobaPoLogin(osoba.getLogin());
        if (temp!=null&&temp.getLogin().equals(osoba.getLogin())&&temp.getHaslo().equals(osoba.getHaslo()))
        {
            osoba=temp;
            System.out.println("TAK!!!!!!!!!!!!!!!!!!!!  "+temp.getImie());
            return "test.xhtml";
        }
        else
        {
            message.logwanieError();
        }
        return null;
    }
    
    //funkcje przejscia &&&&&&&&&&&&&&&&&&&&&&&&
    
    
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
