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
            if(personKontroler.getPerson().getStatus().equals("Mieszkaniec"))
            {
                roomKontroler.setRoom(personKontroler.getPerson().getRoom());
            }
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
        else if(personKontroler.createNewPerson())
        {
            if(personKontroler.getPerson().getStatus().equals("Administrator"))
            personKontroler.manager.createOsoba(personKontroler.getPerson());
        }
        return "home";
    }
    
    public String changeRoom()
    {
        Person personTemp1 = personKontroler.getPerson();
        Person personTemp2 = personKontroler.getTempPerson();
        Room room1;
        Room room2;
        if(!personKontroler.manager.findByLogin(personTemp2.getLogin()).isEmpty())
        {
            Person personTemp3 = personKontroler.manager.findByLogin(personTemp2.getLogin()).get(0);
            if(personTemp3.getPassword().equals(personTemp2.getPassword()))
            {
                personTemp2 = personTemp3;
                room1 = personTemp1.getRoom();
                room2 = personTemp2.getRoom();
                
                for(int i = 0; i < room1.getPeople().size(); i++)
                {
                    if(room1.getPeople().get(i).getName().equals(personTemp1.getName()))
                    {
                        room1.getPeople().remove(i);
                    }
                }
                for(int i = 0; i < room2.getPeople().size(); i++)
                {
                    if(room2.getPeople().get(i).getName().equals(personTemp2.getName()))
                    {
                        room2.getPeople().remove(i);
                    }
                }
                room1.getPeople().add(personTemp2);
                room2.getPeople().add(personTemp1);
                personTemp1.setRoom(room2);
                personTemp2.setRoom(room1);
                roomKontroler.manager.update(room1);
                roomKontroler.manager.update(room2);
                personKontroler.manager.update(personTemp1);
                personKontroler.manager.update(personTemp2);
                roomKontroler.setRoom(room2);
            }
        }
        else
        {
            System.out.println("Zamiana nieudana");
        }
        personKontroler.setTempPerson(new Person());
        return "inhabitantRoom.xhtml";
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

