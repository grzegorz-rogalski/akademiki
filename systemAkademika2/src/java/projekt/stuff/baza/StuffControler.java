/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt.stuff.baza;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import projekt.messages.messageKontroler;
import projekt.room.baza.RoomDatabase;
import projekt.room.baza.RoomSes;

/**
 *
 * @author gelo9_000
 */
@ManagedBean
@RequestScoped
public class StuffControler {


     @EJB
    StuffSes stuffManager;
    
    private boolean wyswietl;
    private StuffDatabase przedmiot;
    private StuffDatabase przedmiotTemp;
    private List<StuffDatabase> listaPrzedmiotow = new ArrayList<>();
    private List<StuffDatabase> listaPrzedmiotowTemp = new ArrayList<>();

    
    private messageKontroler message = new messageKontroler();


    public StuffControler() {
        przedmiot=new StuffDatabase();
        przedmiotTemp = new StuffDatabase();
        wyswietl = false;
    }
    
    public void addRoom()
    {
        stuffManager.createStuff(przedmiot);
    }

    
    public String wszystkiePrzedmioty()
    {
        listaPrzedmiotow=stuffManager.allStuff();
        return null;
    }
    
        public void editAll()
    {
        for(int i = 0; i < listaPrzedmiotow.size(); i++) 
        {
            stuffManager.update(listaPrzedmiotow.get(i));
        }
        wyswietl = false;
    
    }
    
    /////////////////////

    public StuffSes getStuffManager() {
        return stuffManager;
    }

    public void setStuffManager(StuffSes stuffManager) {
        this.stuffManager = stuffManager;
    }

    public boolean isWyswietl() {
        return wyswietl;
    }

    public void setWyswietl(boolean wyswietl) {
        this.wyswietl = wyswietl;
    }

    public StuffDatabase getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(StuffDatabase przedmiot) {
        this.przedmiot = przedmiot;
    }

    public StuffDatabase getPrzedmiotTemp() {
        return przedmiotTemp;
    }

    public void setPrzedmiotTemp(StuffDatabase przedmiotTemp) {
        this.przedmiotTemp = przedmiotTemp;
    }

    public List<StuffDatabase> getListaPrzedmiotow() {
        return listaPrzedmiotow;
    }

    public void setListaPrzedmiotow(List<StuffDatabase> listaPrzedmiotow) {
        this.listaPrzedmiotow = listaPrzedmiotow;
    }

    public List<StuffDatabase> getListaPrzedmiotowTemp() {
        return listaPrzedmiotowTemp;
    }

    public void setListaPrzedmiotowTemp(List<StuffDatabase> listaPrzedmiotowTemp) {
        this.listaPrzedmiotowTemp = listaPrzedmiotowTemp;
    }

    public messageKontroler getMessage() {
        return message;
    }

    public void setMessage(messageKontroler message) {
        this.message = message;
    }
    
    
    
}
