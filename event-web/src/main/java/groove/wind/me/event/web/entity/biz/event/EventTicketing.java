package groove.wind.me.event.web.entity.biz.event;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "event_ticketing")
@Data
public class EventTicketing {

    @Id
    private String id;

    private Double eventTicketingPrice;

    private String eventTicketingCategory;

    private Integer eventTicketRemain;

    private Date eventTicketDueTime;

}
