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
import logika.bean.FurnishingsBean;
import logika.bean.PersonBean;
import logika.entity.Furnishings;
import logika.entity.FurnishingsNames;

/**
 *
 * @author marekszymanski
 */
@ManagedBean
@SessionScoped
public class FurnishingsKontroler implements Serializable{
    private Furnishings furnishings;
    private FurnishingsNames FName = new FurnishingsNames();
    private List <FurnishingsNames> FurnishingsNamesList = new ArrayList<>();
    private List <Furnishings> furnishingsList = new ArrayList<>();
    private List <String> allFurnishingsStringList = new ArrayList<>();
    private List <String> selectedFurnishingStringsList = new ArrayList<>();
    @EJB
    public FurnishingsBean manager;

    public FurnishingsKontroler() {
    }
    
    public void newFurnishings()
    {
        manager.createFurnishings(furnishings);
        furnishings = new Furnishings();
    }
    
    public void fillAllFurnishingsList()
    {
        furnishingsList = manager.allFurnishings();
    }
    
    public void fillAllFurnishingsStringList()
    {
        furnishingsList = manager.allFurnishings();
        //selectedFurnishingStringsList = new ArrayList<>();
        for(int i = 0; i<furnishingsList.size();i++)
        {
            allFurnishingsStringList.add(furnishingsList.get(i).getName());
        }
    }

    public Furnishings getFurnishings() {
        return furnishings;
    }

    public void setFurnishings(Furnishings furnishings) {
        this.furnishings = furnishings;
    }

    public List<Furnishings> getFurnishingsList() {
        return furnishingsList;
    }

    public void setFurnishingsList(List<Furnishings> furnishingsList) {
        this.furnishingsList = furnishingsList;
    }

    public List<String> getAllFurnishingsStringList() {
        return allFurnishingsStringList;
    }

    public void setAllFurnishingsStringList(List<String> allFurnishingsStringList) {
        this.allFurnishingsStringList = allFurnishingsStringList;
    }

    public List<String> getSelectedFurnishingStringsList() {
        return selectedFurnishingStringsList;
    }

    public void setSelectedFurnishingStringsList(List<String> selectedFurnishingStringsList) {
        this.selectedFurnishingStringsList = selectedFurnishingStringsList;
    }

    public FurnishingsNames getFName() {
        return FName;
    }

    public void setFName(FurnishingsNames FName) {
        this.FName = FName;
    }

    public List<FurnishingsNames> getFurnishingsNamesList() {
        return FurnishingsNamesList;
    }

    public void setFurnishingsNamesList(List<FurnishingsNames> FurnishingsNamesList) {
        this.FurnishingsNamesList = FurnishingsNamesList;
    }
    
    
}
