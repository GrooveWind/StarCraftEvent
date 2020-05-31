package groove.wind.me.event.web.entity.biz.event;

import com.alibaba.fastjson.annotation.JSONField;
import groove.wind.me.event.web.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "person")
@Data
public class PersonPortfolio extends BaseMongoEntity {

    @Id
    private String id;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date recordDate;

    private String recordName;

    private String recordDetail;

}
