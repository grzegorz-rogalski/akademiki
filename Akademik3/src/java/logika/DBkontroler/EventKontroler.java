/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.DBkontroler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import logika.bean.EventBean;
import logika.entity.Event;



@ManagedBean(name = "eventkontroler")
@SessionScoped
public class EventKontroler implements Serializable {

    private Event event;
    private List<Event> eventList;
    @EJB
    public EventBean manager;

    public EventKontroler() {
        event = new Event();
        eventList = new ArrayList<>();
    }

    public String createNew() {
        event.setCreatedBy(PersonKontroler.currentlyLogged.getName()+"."
                +PersonKontroler.currentlyLogged.getSurname());
        manager.createEvent(event);
        return "administratorEvent";
    }
    
        public String createNewByUser() {
        event.setCreatedBy(PersonKontroler.currentlyLogged.getName()+"."
                +PersonKontroler.currentlyLogged.getSurname());
        event.setIsAccepted(false);
        manager.createEvent(event);
        return "inhabitantEvent";
    }

    public void findAllAccepted()
    {
        eventList=manager.findBy(true);
    }
    
    public void findAllUnAccepted()
    {
        eventList=manager.findBy(false);
    }
        
    public void edit() {
        manager.update(event);
    }

    public void fillListAll() {
        eventList = manager.allPeople();
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

}
