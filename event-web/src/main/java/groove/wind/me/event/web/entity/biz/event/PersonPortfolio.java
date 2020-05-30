package groove.wind.me.event.web.entity.biz.event;

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

    private Date recordDate;

    private String recordName;

    private String recordDetail;

}
