/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.DBkontroler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import logika.bean.RoomBean;
import logika.entity.Furnishings;
import logika.entity.Room;

/**
 *
 * @author marekszymanski
 */
@ManagedBean(name = "roomkontroler")
@SessionScoped
public class RoomKontroler implements Serializable{
    
    private Room room;
    private int tempIndex;
    private int roomsCountAll;
    private int roomsCountAllFree;
    private int roomsCountFreeMan;
    private int roomsCountFreeWomen;
    private int roomsCountFullMan;
    private int roomsCountFullWoman;
    
    @EJB
    public RoomBean manager;
    
    private List<Room> roomList = new ArrayList<>();

    public RoomKontroler() {
        room = new Room();
    }
    
    public boolean createNewRoom()
    {
        if(manager.findByNumber(room.getNumber()).isEmpty())
        {
            manager.createRoom(room);
            room = new Room();
            return true;
        }
        else
        {
            System.out.println("Pokój już istnieje");
            return false;
        }
    }
    
    public void findByTempIndex()
    {
        room = manager.findByID(tempIndex);
        
    }
    
    public void editRoom()
    {
        manager.update(room);
    }
    
    public void fillList()
    {
        roomList = manager.allRooms();
    }
    
    
    public void generateStatictic()
    {
        roomsCountAll = manager.allRooms().size();
        roomsCountAllFree = manager.findFreeBedRoom("free").size();
        roomsCountFreeMan = manager.findFreeBedRoom("he").size();
        roomsCountFreeWomen = manager.findFreeBedRoom("she").size();
        roomsCountFullMan = manager.findFreeBedRoom("he").size();
        roomsCountFullWoman = manager.findFreeBedRoom("she").size();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public int getRoomsCountAll() {
        return roomsCountAll;
    }

    public void setRoomsCountAll(int roomsCountAll) {
        this.roomsCountAll = roomsCountAll;
    }

    public int getRoomsCountAllFree() {
        return roomsCountAllFree;
    }

    public void setRoomsCountAllFree(int roomsCountAllFree) {
        this.roomsCountAllFree = roomsCountAllFree;
    }

    public int getRoomsCountFreeMan() {
        return roomsCountFreeMan;
    }

    public void setRoomsCountFreeMan(int roomsCountFreeMan) {
        this.roomsCountFreeMan = roomsCountFreeMan;
    }

    public int getRoomsCountFreeWomen() {
        return roomsCountFreeWomen;
    }

    public void setRoomsCountFreeWomen(int roomsCountFreeWomen) {
        this.roomsCountFreeWomen = roomsCountFreeWomen;
    }

    public int getRoomsCountFullMan() {
        return roomsCountFullMan;
    }

    public void setRoomsCountFullMan(int roomsCountFullMan) {
        this.roomsCountFullMan = roomsCountFullMan;
    }

    public int getRoomsCountFullWoman() {
        return roomsCountFullWoman;
    }

    public void setRoomsCountFullWoman(int roomsCountFullWoman) {
        this.roomsCountFullWoman = roomsCountFullWoman;
    }

    public int getTempIndex() {
        return tempIndex;
    }

    public void setTempIndex(int tempIndex) {
        this.tempIndex = tempIndex;
    }

    
}
