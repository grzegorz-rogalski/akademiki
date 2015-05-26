/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;


@Entity
public class RoomChange implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Person logged;//osoba zalogowana 
    private Person loggedIn;//osoba logująca się u innego usera
    private boolean isAccepted;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Date;

    public RoomChange() {
        Date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getLogged() {
        return logged;
    }

    public void setLogged(Person logged) {
        this.logged = logged;
    }

    public Person getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Person loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomChange)) {
            return false;
        }
        RoomChange other = (RoomChange) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logika.entity.Event[ id=" + id + " ]";
    }
    
}
