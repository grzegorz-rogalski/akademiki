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
import logika.entity.Furnishings;
/**
 *
 * @author marekszymanski
 */
@Stateless
public class FurnishingsBean {

    @PersistenceContext
    private EntityManager manager;
    private Furnishings  temp = new Furnishings();
    //messageKontroler message;
    
    public void createFurnishings(Furnishings furnishings) 
    {
        System.out.println("dodanie trwa!!!!!!!!!!!!!!");
        manager.persist(furnishings);
    }
    
    public List<Furnishings> allFurnishings() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Furnishings.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public Furnishings findByID(int id)
    {
        return manager.find(Furnishings.class, id);
    }
    
    public List<Furnishings> findByName(String name)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Furnishings.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("name"), name));
        Query query = manager.createQuery(cq);
        List<Furnishings> result = query.getResultList();
        return result;
    }
    
    public List<Furnishings> findByState(String state)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Furnishings.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("state"), state));
        Query query = manager.createQuery(cq);
        List<Furnishings> result = query.getResultList();
        return result;
    }
    
    public void update(Furnishings newFurnishings)
    {
        manager.merge(newFurnishings);
    }
}
