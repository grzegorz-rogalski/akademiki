/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;
import logika.DBkontroler.CrankyEventKontroler;
import logika.DBkontroler.EventKontroler;
import logika.DBkontroler.FurnishingsKontroler;
import logika.DBkontroler.FurnishingsNameKontroler;
import logika.DBkontroler.PersonKontroler;
import logika.DBkontroler.RoomChangeKontroler;
import logika.DBkontroler.RoomKontroler;
import logika.entity.Furnishings;
import logika.entity.Person;
import logika.entity.Room;


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
    
    @ManagedProperty(value="#{furnishingsnamekontroler}")
    FurnishingsNameKontroler furnishingsNameKontroler;
    
    @ManagedProperty(value="#{roomchangekontroler}")
    RoomChangeKontroler roomChangeKontroler;
    
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
        if(!findFreeBed().getNumber().equals("-1") && personKontroler.createNewPerson())
        {
            if(personKontroler.getPerson().getStatus().equals("Mieszkaniec"))
            {
                personKontroler.getPerson().setRoom(findFreeBed());
                roomKontroler.setRoom(findFreeBed());
                roomKontroler.getRoom().getPeople().add(personKontroler.getPerson());
                roomKontroler.getRoom().setNumberOfFreeBeds(roomKontroler.getRoom().getNumberOfFreeBeds()-1);
                roomKontroler.editRoom();
            }
        }
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
    
    public void createfurnishingsForFoomList()
    {
        roomKontroler.setRoom(new Room());
        for(int i = 0; i < furnishingsNameKontroler.getList().size(); i++)
        {
            Furnishings temp = new Furnishings();
            temp.setName(furnishingsNameKontroler.getList().get(i));
            roomKontroler.getRoom().getFurnishings().add(temp);
        }
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
    
    public String addRoom()
    {
        roomKontroler.createNewRoom();
        return "administratorRoom";
    }
    
    public void addfurnishings()
    {
        furnishingsKontroler.newFurnishings();
    }
    
    public Room findFreeBed()
    {
        if(personKontroler.getPerson().getSex().equals("m"))
        {
            List <Room> a = new ArrayList<>();
            a = roomKontroler.manager.findFreeBedRoom("he");
            if(a.isEmpty())
            {
                a = roomKontroler.manager.findFreeBedRoom("free");
                if(!a.isEmpty())
                {
                    return a.get(0);
                }
            }
            else
            {
                return a.get(0);
            }
        }
        
        if(personKontroler.getPerson().getSex().equals("k"))
        {
            List <Room> a = new ArrayList<>();
            a = roomKontroler.manager.findFreeBedRoom("she");
            if(a.isEmpty())
            {
                a = roomKontroler.manager.findFreeBedRoom("free");
                if(!a.isEmpty())
                {
                    return a.get(0);
                }
            }
            else
            {
                return a.get(0);
            }
        }
        Room returnRoom = new Room();
        returnRoom.setNumber("-1");
        return returnRoom;
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

    public FurnishingsNameKontroler getFurnishingsNameKontroler() {
        return furnishingsNameKontroler;
    }

    public void setFurnishingsNameKontroler(FurnishingsNameKontroler furnishingsNameKontroler) {
        this.furnishingsNameKontroler = furnishingsNameKontroler;
    }

    public RoomChangeKontroler getRoomChangeKontroler() {
        return roomChangeKontroler;
    }

    public void setRoomChangeKontroler(RoomChangeKontroler roomChangeKontroler) {
        this.roomChangeKontroler = roomChangeKontroler;
    }
    
    
    
    
}

