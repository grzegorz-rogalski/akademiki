/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.room.baza;

import projekt.osoba.baza.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jboss.weld.context.ejb.Ejb;
import projekt.messages.messageKontroler;
import projekt.osoba.baza.BazaPearson;
import projekt.osoba.baza.OsobaSes;



@ManagedBean (name = "roomkontroler")
@SessionScoped
public class RoomControler implements Serializable{
    @EJB
    RoomSes roomManager;
    
    private boolean wyswietl;
    private RoomDatabase pokoj;
    private RoomDatabase pokojTemp;
    private List<RoomDatabase> listaPokoi = new ArrayList<>();
    private List<RoomDatabase> listaPokoiTemp = new ArrayList<>();

    
    private messageKontroler message = new messageKontroler();

    public RoomControler() {
        pokoj=new RoomDatabase();
        pokojTemp = new RoomDatabase();
        wyswietl = false;
    }
    
    public void addRoom()
    {
        roomManager.createRoom(pokoj);
    }

    
     public String wszystkiePokoje()
    {
        listaPokoi=roomManager.allRooms();
        return null;
    }

     public void editAll()
    {
        for(int i = 0; i < listaPokoi.size(); i++) 
        {
            roomManager.update(listaPokoi.get(i));
        }
        wyswietl = false;
    }
    ///////////////////////
    public RoomSes getRoomManager() {
        return roomManager;
    }

    public void setRoomManager(RoomSes roomManager) {
        this.roomManager = roomManager;
    }

    public boolean isWyswietl() {
        return wyswietl;
    }

    public void setWyswietl(boolean wyswietl) {
        this.wyswietl = wyswietl;
    }

    public RoomDatabase getPokoj() {
        return pokoj;
    }

    public void setPokoj(RoomDatabase pokoj) {
        this.pokoj = pokoj;
    }

    public RoomDatabase getPokojTemp() {
        return pokojTemp;
    }

    public void setPokojTemp(RoomDatabase pokojTemp) {
        this.pokojTemp = pokojTemp;
    }

    public List<RoomDatabase> getListaPokoi() {
        return listaPokoi;
    }

    public void setListaPokoi(List<RoomDatabase> listaPokoi) {
        this.listaPokoi = listaPokoi;
    }

    public List<RoomDatabase> getListaPokoiTemp() {
        return listaPokoiTemp;
    }

    public void setListaPokoiTemp(List<RoomDatabase> listaPokoiTemp) {
        this.listaPokoiTemp = listaPokoiTemp;
    }

    public messageKontroler getMessage() {
        return message;
    }

    public void setMessage(messageKontroler message) {
        this.message = message;
    }
    
    
    
    
    
    
    
    
}
