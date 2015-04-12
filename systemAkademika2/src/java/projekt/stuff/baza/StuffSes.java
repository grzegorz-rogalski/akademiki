/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt.stuff.baza;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import projekt.messages.messageKontroler;
import projekt.room.baza.RoomDatabase;

/**
 *
 * @author gelo9_000
 */
@Stateless
public class StuffSes {

          @PersistenceContext
    private EntityManager manager;
    private StuffDatabase temp = new StuffDatabase();
    messageKontroler message;
    
    public void createStuff(StuffDatabase przedmiot)
    {
        message= new messageKontroler();
        List<StuffDatabase> list = findLog(przedmiot.getId());
        if(list.isEmpty())
        {
            message.rejestracja();
            //list=allRooms();

            manager.persist(przedmiot);
        }
        else
            message.rejestracjaError();
    }
    
    public List<StuffDatabase> allStuff() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(StuffDatabase.class));
        return manager.createQuery(cq).getResultList();
    }
    
      public List<StuffDatabase> findLog(Long id)
    {
        // Select the employees and the mailing addresses that have the same address.
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(StuffDatabase.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("id"), id));
        Query query = manager.createQuery(cq);
        List<StuffDatabase> result = query.getResultList();
        return result;
    }
      
   public void update(StuffDatabase newStuff)
    {
        System.out.println("Tu działa");
        manager.merge(newStuff);
    }
          
}
