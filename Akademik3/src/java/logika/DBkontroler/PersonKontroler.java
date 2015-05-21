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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import logika.bean.PersonBean;
import logika.entity.Person;

/**
 *
 * @author marekszymanski
 */
@ManagedBean (name = "personkontroler")
@SessionScoped
public class PersonKontroler implements Serializable{
    private Person person;
    private List <Person> personList;
    @EJB
    public PersonBean manager;
    

    public PersonKontroler() {
        person = new Person();
        personList = new ArrayList<>();
    }
    
    public String createNew()
    {
        if(manager.findByLogin(person.getLogin()).isEmpty())
        {
            manager.createOsoba(person);
            person = new Person();
            return "/Administrator/administratorUsers";
        }   
        else
        {
            System.out.println("Login zajęty");
            return "/Administrator/pop-up/newUser";
        }
    }
    
    public boolean createNew(int rooms)
    {
        if(manager.allPeople().isEmpty() || manager.allPeople().size() == 1)
        {
            person.setStatus("Administrator");
        }
        else
        {
            if(manager.findByStatus("Mieszkaniec").size() < rooms)
                person.setStatus("Mieszkaniec");
            else    
            {
                System.out.println("Brak miejsc");
                return false;
            }
                
        }
        if(manager.findByLogin(person.getLogin()).isEmpty())
        {
            manager.createOsoba(person);
            person = new Person();
            return true;
        }   
        else
        {
            System.out.println("Login zajęty");
            return false;
        }
    }
    
    public boolean logIn()
    {
        if(manager.findByLogin(person.getLogin()).isEmpty())
        {
            return false;
        }
        else
        {
            Person temp = manager.findByLogin(person.getLogin()).get(0);
            if(person.getPassword().equals(temp.getPassword()))
            {
                person = temp;
                return true;
            } 
            else
                return false;
        }
    }
    
    public void edit()
    {
        manager.update(person);
    }
    
    public void fillListAll()
    {
        personList = manager.allPeople();
    }
    
    public void fillListNameNameSurname(String name, String surname)
    {
        List <Person> tempList = manager.findBySurName(surname);
        if(tempList.isEmpty())
            System.out.println("Nie ma nikogo o takich danych");
        else
        {
            personList = new ArrayList<>();
            for(int i = 0; i<tempList.size();i++)
            {
                if(tempList.get(i).getName().equals(name))
                    personList.add(tempList.get(i));
            }
        }
    }


    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
    
    
}
