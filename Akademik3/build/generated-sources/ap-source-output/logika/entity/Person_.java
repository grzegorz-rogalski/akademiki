package logika.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logika.entity.Room;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-17T14:39:42")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> mail;
    public static volatile SingularAttribute<Person, String> studentCardNumber;
    public static volatile SingularAttribute<Person, String> sex;
    public static volatile SingularAttribute<Person, String> login;
    public static volatile SingularAttribute<Person, Date> birthDate;
    public static volatile SingularAttribute<Person, Room> room;
    public static volatile SingularAttribute<Person, String> password;
    public static volatile SingularAttribute<Person, String> phoneNumber;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Integer> id;
    public static volatile SingularAttribute<Person, String> obrazek;
    public static volatile SingularAttribute<Person, String> status;

}