/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.DBkontroler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import logika.bean.RoomChangeBean;
import logika.entity.Person;
import logika.entity.RoomChange;



@ManagedBean(name = "roomchangekontroler")
@SessionScoped
public class RoomChangeKontroler implements Serializable {

    private RoomChange roomChange;
    private List<RoomChange> roomChangeList;
    public Person loggedIn;
    public String login,password;
    @EJB
    public RoomChangeBean manager;

    public RoomChangeKontroler() {
        roomChange = new RoomChange();
        roomChangeList = new ArrayList<>();
    }
    
    public String createNewByUser() {
        roomChange.setLogged(PersonKontroler.currentlyLogged);
   
      
        
        roomChange.setDate(new Date());
        roomChange.setIsAccepted(false);
        manager.createRoomChange(roomChange);
        return "inhabitantStart";
    }

        
    public void findAllAccepted()
    {
        roomChangeList=manager.findBy(true);
    }
    
    public void findAllUnAccepted()
    {
        roomChangeList=manager.findBy(false);
    }
    
    public void edit() {
        manager.update(roomChange);
    }

    public void fillListAll() {
        roomChangeList = manager.allRoomChange();
    }

    public RoomChange getRoomChange() {
        return roomChange;
    }

    public void setRoomChange(RoomChange roomChange) {
        this.roomChange = roomChange;
    }

    public List<RoomChange> getRoomChangeList() {
        return roomChangeList;
    }

    public void setRoomChangeList(List<RoomChange> roomChangeList) {
        this.roomChangeList = roomChangeList;
    }

    public Person getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Person loggedIn) {
        this.loggedIn = loggedIn;
    }

    public RoomChangeBean getManager() {
        return manager;
    }

    public void setManager(RoomChangeBean manager) {
        this.manager = manager;
    }

    

}
