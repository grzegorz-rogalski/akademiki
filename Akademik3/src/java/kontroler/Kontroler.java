/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import logika.DBkontroler.CrankyEventKontroler;
import logika.DBkontroler.EventKontroler;
import logika.DBkontroler.FurnishingsKontroler;
import logika.DBkontroler.PersonKontroler;
import logika.DBkontroler.RoomKontroler;
import logika.entity.Furnishings;
import logika.entity.Person;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author grzeorz
 */
@ManagedBean
@SessionScoped
public class Kontroler implements Serializable{
    @ManagedProperty(value="#{personkontroler}")
    PersonKontroler personKontroler;
    
    @ManagedProperty(value="#{roomkontroler}")
    RoomKontroler roomKontroler;
    
    @ManagedProperty(value="#{furnishingskontroler}")
    FurnishingsKontroler furnishingsKontroler;
    
    @ManagedProperty(value="#{eventkontroler}")
    EventKontroler eventKontroler;
    
    @ManagedProperty(value="#{crankyeventkontroler}")
    CrankyEventKontroler crankyEventKontroler;
    
    SiteController siteController = new SiteController();
    
    public String logIn()
    {
        if(personKontroler.logIn())
        {
            System.out.println("Logowanie trwa dla "+personKontroler.getPerson().getStatus());
            return siteController.logIn(personKontroler.getPerson().getStatus());
        }   
        else
            return "home";
    }
    
    public String logOut()
    {
        personKontroler.setPerson(new Person());
        
        return "home";
    }
    
    public String registration()
    {
        if(personKontroler.createNew(roomKontroler.manager.allRooms().size()))
            return "home";
        else
            return "home";
    }
    
    public void updateAllPeople()
    {
        for(int i = 0; i < personKontroler.getPersonList().size(); i++)
        {
            personKontroler.manager.update(personKontroler.getPersonList().get(i));
        }
        siteController.changeEdit();
    }
    
    public void updateAllEvents()
    {
        for(int i = 0; i < eventKontroler.getEventList().size(); i++)
        {
            eventKontroler.manager.update(eventKontroler.getEventList().get(i));
        }
        siteController.changeEdit();
    }
    
        public void updateAllCrankyEvents()
    {
        for(int i = 0; i < crankyEventKontroler.getEventList().size(); i++)
        {
            crankyEventKontroler.manager.update(crankyEventKontroler.getEventList().get(i));
        }
        siteController.changeEdit();
    }
        
    public void onFurnishingsDrop(DragDropEvent ddEvent) {
        Furnishings temp = ((Furnishings) ddEvent.getData());
  
        roomKontroler.getRoom().getFurnishings().add(temp);
        furnishingsKontroler.getFurnishingsList().remove(temp);
    }
    
    public void addRoom()
    {
        roomKontroler.createNewRoom();
    }
    
    public void addfurnishings()
    {
        furnishingsKontroler.newFurnishings();
    }
    
    public void setPersonKontroler(PersonKontroler personKontroler) {
        this.personKontroler = personKontroler;
    }

    public void setRoomKontroler(RoomKontroler roomKontroler) {
        this.roomKontroler = roomKontroler;
    }

    public SiteController getSiteController() {
        return siteController;
    }

    public void setSiteController(SiteController siteController) {
        this.siteController = siteController;
    }

    public FurnishingsKontroler getFurnishingsKontroler() {
        return furnishingsKontroler;
    }

    public void setFurnishingsKontroler(FurnishingsKontroler furnishingsKontroler) {
        this.furnishingsKontroler = furnishingsKontroler;
    }

    public EventKontroler getEventKontroler() {
        return eventKontroler;
    }

    public void setEventKontroler(EventKontroler eventKontroler) {
        this.eventKontroler = eventKontroler;
    }

    public CrankyEventKontroler getCrankyEventKontroler() {
        return crankyEventKontroler;
    }

    public void setCrankyEventKontroler(CrankyEventKontroler crankyEventKontroler) {
        this.crankyEventKontroler = crankyEventKontroler;
    }
    
    
    
}

