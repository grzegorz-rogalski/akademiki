/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logika.entity.Event;

@Stateless
public class EventBean {

    @PersistenceContext
    private EntityManager manager;
    private Event temp = new Event();
    //messageKontroler message;
    
    public void createEvent(Event event) 
    {
        manager.persist(event);
    }
    
    public List<Event> allEvents() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Event.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public Event findByID(int id)
    {
        return manager.find(Event.class, id);
    }
    
    public List<Event> findBy(boolean status)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Event.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("isAccepted"), status));
        Query query = manager.createQuery(cq);
        List<Event> result = query.getResultList();
        return result;
    }
    
    public void update(Event newEvent)
    {
        manager.merge(newEvent);
    }
}
