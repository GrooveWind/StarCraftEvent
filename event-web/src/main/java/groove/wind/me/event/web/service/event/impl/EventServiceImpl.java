package groove.wind.me.event.web.service.event.impl;

import groove.wind.me.event.web.entity.event.Event;
import groove.wind.me.event.web.repository.EventRepository;
import groove.wind.me.event.web.service.event.EventService;
import groove.wind.me.event.web.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class EventServiceImpl implements EventService {

    private static String[] IGNORE_PATHS = new String[]{"eventDate", "eventCategory", "eventImage", "eventTicket", "eventStatus", "eventParticipant", "eventCoupon"};

    @Autowired
    EventRepository eventRepository;

    @Override
    public Page<Event> queryEvents(int pageNum, int pageSize, String eventName) {
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        if (StrUtils.hasContainChinese(eventName)) {
            return eventRepository.findByEventChineseNameLike(eventName, pageable);
        }
        return eventRepository.findByEventEnglishNameLike(eventName, pageable);
    }

    @Override
    public Event queryById(String eventId) {
        Optional<Event> optional = eventRepository.findById(eventId);
        return optional.get();
    }

    @Override
    public Event saveOrUpdateEvent(Event event) {
        if (StringUtils.isBlank(event.getEventId())) {
            event.setCreatedDate(new Date());
            event.setUpdatedDate(new Date());
        } else {
            event.setUpdatedDate(new Date());
        }

        return eventRepository.insert(event);
    }

}
