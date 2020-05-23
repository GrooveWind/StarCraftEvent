package groove.wind.me.event.web.entity.event;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "event_category")
@Data
public class EventCategory {

    @Id
    private String id;

    private String eventCategory;

    private List<String> eventJudge;

}
