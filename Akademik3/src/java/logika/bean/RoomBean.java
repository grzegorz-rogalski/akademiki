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
import logika.entity.Room;


/**
 *
 * @author marekszymanski
 */
@Stateless
public class RoomBean {

    @PersistenceContext
    private EntityManager manager;
    private Room temp = new Room();
    //messageKontroler message;
    
    public void createRoom(Room room) 
    {
        manager.persist(room);
    }
    
    public List<Room> allRooms() {
        System.out.println("######### TU DZIAŁA 1");
        
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Room.class));
        System.out.println("######### TU DZIAŁA 2");
        return manager.createQuery(cq).getResultList();
    }
    
    public Room findByID(int id)
    {
        return manager.find(Room.class, id);
    }
    
    public List<Room> findByNumber(String number)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Room.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("number"), number));
        Query query = manager.createQuery(cq);
        List<Room> result = query.getResultList();
        return result;
    }
    
    public void update(Room newRoom)
    {
        manager.merge(newRoom);
    }
}
