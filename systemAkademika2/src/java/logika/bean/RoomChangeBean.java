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
import logika.entity.RoomChange;

@Stateless
public class RoomChangeBean {

    @PersistenceContext
    private EntityManager manager;
    private RoomChange temp = new RoomChange();
    //messageKontroler message;
    
    public void createRoomChange(RoomChange change) 
    {
        manager.persist(change);
    }
    
    public List<RoomChange> allRoomChange() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RoomChange.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public RoomChange findByID(int id)
    {
        return manager.find(RoomChange.class, id);
    }
    
    public List<RoomChange> findBy(boolean status)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(RoomChange.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("isAccepted"), status));
        Query query = manager.createQuery(cq);
        List<RoomChange> result = query.getResultList();
        return result;
    }
    
    public void update(RoomChange newRoomChange)
    {
        manager.merge(newRoomChange);
    }
}
