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
import logika.entity.Person;

/**
 *
 * @author marekszymanski
 */
@Stateless
public class PersonBean {

    @PersistenceContext
    private EntityManager manager;
    private Person temp = new Person();
    //messageKontroler message;
    
    public void createOsoba(Person person) 
    {
        manager.persist(person);
    }
    
    public List<Person> allPeople() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Person.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public Person findByID(int id)
    {
        return manager.find(Person.class, id);
    }
    
    public List<Person> findByLogin(String login)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Person.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("login"), login));
        Query query = manager.createQuery(cq);
        List<Person> result = query.getResultList();
        return result;
    }
    
    public List<Person> findBySurName(String surname)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Person.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("surname"), surname));
        Query query = manager.createQuery(cq);
        List<Person> result = query.getResultList();
        return result;
    }
    
    public List<Person> findByStudentCardNumber(String number)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Person.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("studentCardNumber"), number));
        Query query = manager.createQuery(cq);
        List<Person> result = query.getResultList();
        return result;
    }
    
    public List<Person> findByStatus(String status)
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(Person.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("status"), status));
        Query query = manager.createQuery(cq);
        List<Person> result = query.getResultList();
        return result;
    }
    
    public void update(Person newPerson)
    {
        manager.merge(newPerson);
    }
}
