package groove.wind.me.event.web.entity.event;


import groove.wind.me.event.web.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_order")
@Data
public class EventOrder extends BaseMongoEntity {

    @Id
    private String orderNum;

    // 1-创建订单 2-支付中 3-支付成功 4-支付失败
    private Integer orderState;

}
