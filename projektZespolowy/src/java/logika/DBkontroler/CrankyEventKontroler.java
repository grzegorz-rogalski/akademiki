/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.DBkontroler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import logika.bean.CrankyEventBean;
import logika.entity.CrankyEvent;



@ManagedBean(name = "crankyeventkontroler")
@SessionScoped
public class CrankyEventKontroler implements Serializable {

    private CrankyEvent event;
    private List<CrankyEvent> eventList;
    @EJB
    public CrankyEventBean manager;

    public CrankyEventKontroler() {
        event = new CrankyEvent();
        eventList = new ArrayList<>();
    }
    
    public String createNewByUser() {
        event.setCreatedBy(PersonKontroler.currentlyLogged.getName()+"."
                +PersonKontroler.currentlyLogged.getSurname()+"."+
                PersonKontroler.currentlyLogged.getStudentCardNumber());
        event.setDate(new Date());
        event.setIsFinished(false);
        manager.createEvent(event);
        return "inhabitantStart";
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
        eventList = manager.allEvents();
    }

    public CrankyEvent getEvent() {
        return event;
    }

    public void setEvent(CrankyEvent event) {
        this.event = event;
    }

    public List<CrankyEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<CrankyEvent> eventList) {
        this.eventList = eventList;
    }

}
