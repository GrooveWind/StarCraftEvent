package groove.wind.me.event.web.entity.event;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_image")
@Data
public class EventImage {

    private String eventHorizontalCoverURL;

    private String eventVerticalCoverURL;

    private String eventGroupImagesURL;

}
