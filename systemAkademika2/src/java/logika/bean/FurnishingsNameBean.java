/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logika.entity.FurnishingsNames;
import logika.entity.Person;

/**
 *
 * @author marekszymanski
 */
@Stateless
public class FurnishingsNameBean implements Serializable{

    @PersistenceContext
    private EntityManager manager;
    
    //messageKontroler message;
    
    public void createFName(FurnishingsNames name) 
    {
        manager.persist(name);
    }
    
    public List<FurnishingsNames> allFNames() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(FurnishingsNames.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public FurnishingsNames findByID(int id)
    {
        return manager.find(FurnishingsNames.class, id);
    }
    
    public List<Person> findByLogin(String name)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Person.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("name"), name));
        Query query = manager.createQuery(cq);
        List<Person> result = query.getResultList();
        return result;
    }
    
    public void update(FurnishingsNames newFName)
    {
        manager.merge(newFName);
    }
}
