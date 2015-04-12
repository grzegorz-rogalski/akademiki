/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt.room.baza;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import projekt.messages.messageKontroler;
import projekt.osoba.baza.BazaPearson;

/**
 *
 * @author gelo9_000
 */
@Stateless
public class RoomSes {

        @PersistenceContext
    private EntityManager manager;
    private RoomDatabase temp = new RoomDatabase();
    messageKontroler message;
    
    public void createRoom(RoomDatabase pokoj)
    {
        message= new messageKontroler();
        List<RoomDatabase> list = findLog(pokoj.getId());
        if(list.isEmpty())
        {
            //message.rejestracja();
            //list=allRooms();
            message.yourMessage("Dodano", "element został dodany do bazy");
            manager.persist(pokoj);
        }
        else
            message.yourMessage("Niepowodzenie", "nie udało się dodać elementu do bazy");
    }
    
    public List<RoomDatabase> allRooms() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RoomDatabase.class));
        return manager.createQuery(cq).getResultList();
    }
    
      public List<RoomDatabase> findLog(Long id)
    {
        // Select the employees and the mailing addresses that have the same address.
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(RoomDatabase.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("id"), id));
        Query query = manager.createQuery(cq);
        List<RoomDatabase> result = query.getResultList();
        return result;
    }
    
      
    public void update(RoomDatabase newRoom)
    {
        System.out.println("Tu działa");
        manager.merge(newRoom);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
