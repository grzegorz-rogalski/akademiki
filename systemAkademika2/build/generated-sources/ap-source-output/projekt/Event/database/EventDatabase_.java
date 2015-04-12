package projekt.Event.database;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-12T12:38:05")
@StaticMetamodel(EventDatabase.class)
public class EventDatabase_ { 

    public static volatile SingularAttribute<EventDatabase, Long> id;
    public static volatile SingularAttribute<EventDatabase, Date> startDate;
    public static volatile SingularAttribute<EventDatabase, Boolean> accepted;
    public static volatile SingularAttribute<EventDatabase, String> description;
    public static volatile SingularAttribute<EventDatabase, Integer> poepleId;
    public static volatile SingularAttribute<EventDatabase, String> name;
    public static volatile SingularAttribute<EventDatabase, Date> endDate;
    public static volatile SingularAttribute<EventDatabase, String> place;

}