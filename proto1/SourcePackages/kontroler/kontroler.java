/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import laczenie_z_baza.DBkontroler;
import laczenie_z_baza.Messenger;
import osoba.Pearson;


@ManagedBean
@SessionScoped
public class kontroler implements Serializable{

    //KONSTRUKTORY ###################################################
    public kontroler() {
    }
    private DBkontroler service = new DBkontroler();
    private List<Pearson>listG = new ArrayList<>();
    private Pearson osoba=new Pearson();
    
    //BAZA ###################################################
    
    @PostConstruct
    public void create()
    {
       service.insterCreate("create table OSOBA (id int, imie varchar(32), nazwisko varchar(32), login varchar(32), haslo varchar(32),"
               + " mail varchar(32), status varchar(32), numerPokoju int)");
       
       Messenger.addSuccessMessage("Tabela została utworzona");
    }
    
    public String add()
    {
        String IdString=checkId();
        service.insterCreate("insert into OSOBA values(1, '"+osoba.getImie()+"', '"+osoba.getNazwisko()+"', '"+osoba.getLogin()+"', '"+osoba.getHaslo()+"', '"+osoba.getMail()+"', '"+osoba.getStatus()+"', 1)");
        Messenger.addSuccessMessage("Wstawiono dane testowe :D");
        return przenies(osoba.getStatus());
    }
    
    private String checkId()
    {
        listG=service.printTable("select * from OSOBA");
        int temp = listG.size();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  "+temp+"    @@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "1";
    }
    
    
    //GETERY && SETERY ####################################

    public Pearson getOsoba() {
        return osoba;
    }

    public void setOsoba(Pearson osoba){
        this.osoba = osoba;
    }
    

    public List<Pearson> getListG() {
        return listG;
    }

    public void setListG(List<Pearson> listG) {
        this.listG = listG;
    }
    
    //ZALOGUJ && WYLOGUJ ###################################################
    
    //BRAK
    
    //FUNKCJE POMOCNICZE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
    //gdzie przeniesc po logowaniu/rejestracji
    private String przenies(String status)
    {
        if(status.equals("Kierownik"))
            return "StartKierownik";
        if(status.equals("Ksiegowa"))
            return "StartKsiegowa";
        if(status.equals("Pracownik"))
            return "StartPracownikZatrudniony";
        if(status.equals("Ciec"))
            return "StartPracownikNaUmowie";
        if(status.equals("Mieszkaniec"))
            return "StartMieszkaniec";
        if(status.equals("CzlonekRady"))
            return "StartCzlonek";
        
        return "home";
    }
}
