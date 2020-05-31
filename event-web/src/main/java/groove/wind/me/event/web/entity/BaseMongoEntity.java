package groove.wind.me.event.web.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class BaseMongoEntity implements Serializable {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date createdDate;

    protected String createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date updatedDate;

    protected String updatedBy;


}
