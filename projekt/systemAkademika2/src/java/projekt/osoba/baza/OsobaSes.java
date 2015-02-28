/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt.osoba.baza;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import projekt.messages.messageKontroler;


/**
 *
 * @author Komputer
 */
@Stateless
public class OsobaSes {

    @PersistenceContext
    private EntityManager manager;
    private BazaPearson temp = new BazaPearson();
    messageKontroler message;
    
    public void createOsoba(BazaPearson osoba) 
    {
        message= new messageKontroler();
        List<BazaPearson> list = findLog(osoba.getLogin());
        if(list.isEmpty())
        {
            message.rejestracja();
            list=allPeople();
            if(list.isEmpty()||list.size()==1)
            {
                osoba.setStatus("admin");
            }
            else
            {
                osoba.setStatus("brak");
            }
            manager.persist(osoba);
        }
        else
            message.rejestracjaError();
    }
    
    public List<BazaPearson> allPeople() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BazaPearson.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public BazaPearson findID(int id)
    {
        temp=manager.find(BazaPearson.class, id);
        return temp;
    }
    
    public List<BazaPearson> findLog(String login)
    {
        // Select the employees and the mailing addresses that have the same address.
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(BazaPearson.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("login"), login));
        Query query = manager.createQuery(cq);
        List<BazaPearson> result = query.getResultList();
        return result;
    }
    
    public void update(String kto, String co, String na)
    {
        List<BazaPearson> result=findLog(kto);
        temp=result.get(0);
        if(co=="imie")
        {
            manager.getTransaction().begin();
            temp.setImie(na);
            manager.getTransaction().commit();
        }
        if(co=="nazwisko")
        {
            manager.getTransaction().begin();
            temp.setNazwisko(na);
            manager.getTransaction().commit();
        }
        if(co=="mail")
        {
            manager.getTransaction().begin();
            temp.setMail(na);
            manager.getTransaction().commit();
        }
        if(co=="haslo")
        {
            manager.getTransaction().begin();
            temp.setHaslo(na);
            manager.getTransaction().commit();
        }
        if(co=="IdPokoju")
        {
            manager.getTransaction().begin();
            int foo = Integer.parseInt(na);
            temp.setIdPokoju(foo);
            manager.getTransaction().commit();
        }
    }
}
