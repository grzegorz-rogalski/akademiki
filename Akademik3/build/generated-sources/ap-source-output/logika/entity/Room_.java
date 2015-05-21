package logika.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logika.entity.Furnishings;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-17T14:39:42")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, String> number;
    public static volatile ListAttribute<Room, Furnishings> furnishings;
    public static volatile SingularAttribute<Room, Integer> numberOfFreeBeds;
    public static volatile SingularAttribute<Room, Integer> numberOfBeds;
    public static volatile SingularAttribute<Room, Integer> id;

}