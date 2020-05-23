package groove.wind.me.event.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class BaseMongoEntity implements Serializable {

    protected Date createdDate;

    protected String createdBy;

    protected Date updatedDate;

    protected String updatedBy;


}
