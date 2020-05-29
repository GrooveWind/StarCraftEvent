package groove.wind.me.event.web.entity.event;

import groove.wind.me.event.web.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
@Data
public class WechatInfo extends BaseMongoEntity {

    @Id
    private String id;

    private String phone;

}
