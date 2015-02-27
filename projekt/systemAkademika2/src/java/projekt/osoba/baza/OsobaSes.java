/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt.osoba.baza;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author Komputer
 */
@Stateless
public class OsobaSes {

    @PersistenceContext
    private EntityManager manager;
    
    public void createOsoba(BazaPearson osoba) {
        if(osoba.getId()==0||osoba.getId()==1)
        {
            osoba.setStatus("Admin");
        }
        else
        {
            osoba.setStatus("BRAK");
        }
        manager.persist(osoba);
    }
    
    public List<BazaPearson> allPeople() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BazaPearson.class));
        return manager.createQuery(cq).getResultList();
    }
}
