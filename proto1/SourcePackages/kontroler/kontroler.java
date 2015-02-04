/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import laczenie_z_baza.polaczenie;
import osoba.Pearson;


@ManagedBean
@SessionScoped
public class kontroler {

    public kontroler() {
    }
    
    private Pearson osoba=new Pearson();
    private String zapytanie;

    public String getZapytanie() {
        return zapytanie;
    }

    public void setZapytanie(String zapytanie) {
        this.zapytanie = zapytanie;
    }
    

    public Pearson getOsoba() {
        return osoba;
    }

    public void setOsoba(Pearson osoba) {
        this.osoba = osoba;
    }
    
    public String rejestracja() throws UnknownHostException
    {
        //zapytanie = new polaczenie().isertData("akademik", "Osoba", osoba);
        return "StartKierownik";
    }
    public String test() throws UnknownHostException
    {
        zapytanie = new polaczenie().isertData("akademik", "Osoba", osoba);
        return "/ksiegowa/KsiegowaStart.xhtml";
    }
}
