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
import logika.bean.FurnishingsNameBean;
import logika.entity.FurnishingsNames;

/**
 *
 * @author marekszymanski
 */
@ManagedBean(name = "furnishingsnamekontroler")
@SessionScoped
public class FurnishingsNameKontroler implements Serializable{
private FurnishingsNames name  = new FurnishingsNames();
    private List<FurnishingsNames> list = new ArrayList<>();
    
    @EJB
    FurnishingsNameBean manager;
    
    public void add()
    {
        manager.createFName(name);
        name  = new FurnishingsNames();
    }
    
    public void findAll()
    {
        list = new ArrayList<>();
        list = manager.allFNames();
    }

    public FurnishingsNames getName() {
        return name;
    }

    public void setName(FurnishingsNames name) {
        this.name = name;
    }

    public List<FurnishingsNames> getList() {
        return list;
    }

    public void setList(List<FurnishingsNames> list) {
        this.list = list;
    }
    
    
}