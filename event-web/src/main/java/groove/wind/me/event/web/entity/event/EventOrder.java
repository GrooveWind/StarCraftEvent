package groove.wind.me.event.web.entity.event;


import groove.wind.me.event.web.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "dance_style")
@Data
public class EventOrder extends BaseMongoEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String orderNum;

    // 1-创建订单 2-支付中 3-支付成功 4-支付失败
    private Integer orderState;

    private Date createdDate;

}
