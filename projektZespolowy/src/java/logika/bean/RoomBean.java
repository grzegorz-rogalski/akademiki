/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logika.entity.Person;
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
        room.setNumberOfFreeBeds(room.getNumberOfBeds());
        manager.persist(room);
        room = new Room();
    }
    
    public List<Room> allRooms() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Room.class));
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
    
    public List<Room> findFreeBedRoom(String who)
    {
        String queryString = "SELECT a FROM Room a " +
                         "WHERE a.numberOfFreeBeds != 0 AND a.numberOfBeds != 0";
        Query query = manager.createQuery(queryString);
        List <Room> tempList = new ArrayList<>();
        tempList = (List<Room>) query.getResultList();
        if(!tempList.isEmpty())
        {
            System.out.println("1");
            if(who.equals("he") || who.equals("she"))
            {
                System.out.println("2");
                List <Room> returnList = new ArrayList<>();
                for(int i = 0; i < tempList.size(); i++)
                {
                    System.out.println("3");
                    if(tempList.get(i).getNumberOfBeds() != tempList.get(i).getNumberOfFreeBeds())
                    {
                        System.out.println("4");
                        if(who.equals("he") && tempList.get(i).getPeople().get(0).getSex().equals("m"))
                        {
                            System.out.println("5");
                            returnList.add(tempList.get(i));
                        }
                        else if(who.equals("she") && tempList.get(i).getPeople().get(0).getSex().equals("k"))
                        {
                            returnList.add(tempList.get(i));
                        }
                    }
                }
                System.out.println("7");
                return (List<Room>) returnList;
            }
            else if(who.equals("free"))
            {
                System.out.println("8");
                List <Room> returnList = new ArrayList<>();
                for(int i = 0; i < tempList.size(); i++)
                {
                    System.out.println("9");
                    if(tempList.get(i).getNumberOfBeds() == tempList.get(i).getNumberOfFreeBeds())
                    {
                        System.out.println("10");
                        returnList.add(tempList.get(i));
                    }
                }
                System.out.println("11");
                return (List<Room>) returnList;
            }
        }
        System.out.println("12");
        List <Room> returnList = new ArrayList<>();
        return (List<Room>) returnList;
    }
    
    public List<Room> findForHer()
    {
        
        return null;
    }
}
