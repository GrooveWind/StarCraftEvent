package groove.wind.me.event.web.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseMongoEntity implements Serializable {

    protected Date createdDate;

    protected String createdBy;

    protected Date updatedDate;

    protected String updatedBy;

}
