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
    
    
    public void setRoomsCounts()
    {
        roomsCountAll = manager.allRooms().size();
        //reszta danych do statystyk
        //trzeba znaleść listę pokoi z zerem wolnych miejsc
        //liste z wolnymi miejscami
        //podzielić je na dwie (razem 4) męskie i żeńskie
        //funkcje wywołać na górze strony z tabelą
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
        Room temp = manager.findByNumber(room.getNumber()).get(0);
        if(temp == room)
        {
            manager.update(room);
        }
        else
        {
            System.out.println("Nie da się zaktualizować pokoju");
        }
    }
    
    public void fillList()
    {
        roomList = manager.allRooms();
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
