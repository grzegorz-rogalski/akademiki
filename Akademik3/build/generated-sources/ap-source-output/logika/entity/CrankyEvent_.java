package logika.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-26T10:32:18")
@StaticMetamodel(CrankyEvent.class)
public class CrankyEvent_ { 

    public static volatile SingularAttribute<CrankyEvent, String> createdBy;
    public static volatile SingularAttribute<CrankyEvent, String> brokenThingName;
    public static volatile SingularAttribute<CrankyEvent, String> description;
    public static volatile SingularAttribute<CrankyEvent, Integer> id;
    public static volatile SingularAttribute<CrankyEvent, Boolean> isFinished;
    public static volatile SingularAttribute<CrankyEvent, Date> Date;

}