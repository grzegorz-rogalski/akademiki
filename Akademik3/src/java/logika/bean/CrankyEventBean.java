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
import logika.entity.CrankyEvent;
import logika.entity.Event;

@Stateless
public class CrankyEventBean {

    @PersistenceContext
    private EntityManager manager;
    private CrankyEvent temp = new CrankyEvent();
    //messageKontroler message;
    
    public void createEvent(CrankyEvent event) 
    {
        manager.persist(event);
    }
    
    public List<CrankyEvent> allEvents() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Event.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public CrankyEvent findByID(int id)
    {
        return manager.find(CrankyEvent.class, id);
    }
    
    public List<CrankyEvent> findBy(boolean status)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(CrankyEvent.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("isFinished"), status));
        Query query = manager.createQuery(cq);
        List<CrankyEvent> result = query.getResultList();
        return result;
    }
    
    public void update(CrankyEvent newEvent)
    {
        manager.merge(newEvent);
    }
}
