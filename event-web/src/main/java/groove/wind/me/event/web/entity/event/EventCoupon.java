package groove.wind.me.event.web.entity.event;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_coupon")
@Data
public class EventCoupon {

    @Id
    private String eventCouponId;

}
