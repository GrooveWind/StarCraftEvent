package groove.wind.me.event.web.entity.biz.event;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_image")
@Data
public class EventImage {

    @Id
    private String id;

    private String eventHorizontalCoverURL;

    private String eventVerticalCoverURL;

    private String eventGroupImagesURL;

}
