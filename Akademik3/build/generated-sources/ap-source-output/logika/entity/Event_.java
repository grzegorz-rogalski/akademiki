package logika.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-26T10:32:18")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, String> createdBy;
    public static volatile SingularAttribute<Event, Date> endDate;
    public static volatile SingularAttribute<Event, Boolean> isAccepted;
    public static volatile SingularAttribute<Event, String> name;
    public static volatile SingularAttribute<Event, String> description;
    public static volatile SingularAttribute<Event, Date> startTime;
    public static volatile SingularAttribute<Event, Integer> id;
    public static volatile SingularAttribute<Event, Date> endTime;
    public static volatile SingularAttribute<Event, Date> startDate;

}