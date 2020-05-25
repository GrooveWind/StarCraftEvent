package groove.wind.me.event.web.service.event;


import groove.wind.me.event.web.entity.event.Event;
import org.springframework.data.domain.Page;

public interface EventService {

    Page<Event> queryEvents(int pageNum, int pageSize, String eventName);

    Event queryById(String eventId);

    Event saveOrUpdateEvent(Event event);

    boolean deleteEvent(String eventId);

}
