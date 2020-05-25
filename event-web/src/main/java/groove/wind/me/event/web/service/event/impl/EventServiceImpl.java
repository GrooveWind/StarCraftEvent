package groove.wind.me.event.web.service.event.impl;

import groove.wind.me.event.web.entity.event.*;
import groove.wind.me.event.web.repository.*;
import groove.wind.me.event.web.service.event.EventService;
import groove.wind.me.event.web.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class EventServiceImpl implements EventService {

    private static String[] IGNORE_PATHS = new String[]{"eventDate", "eventCategory", "eventImage", "eventTicket", "eventStatus", "eventParticipant", "eventCoupon"};

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    EventImageRepository eventImageRepository;

    @Autowired
    EventTicketingRepository eventTicketingRepository;

    @Autowired
    EventCouponRepository eventCouponRepository;


    @Override
    public Page<Event> queryEvents(int pageNum, int pageSize, String eventName) {
        PageRequest pageable = PageRequest.of(pageNum - 1 < 0 ? 0 : pageNum - 1, pageSize);
        if (StringUtils.isNotBlank(eventName)) {
            if (StrUtils.hasContainChinese(eventName)) {
                return eventRepository.findByEventChineseNameLike(eventName, pageable);
            } else {
                return eventRepository.findByEventEnglishNameLike(eventName, pageable);
            }
        }
        return eventRepository.findAll(pageable);
    }

    @Override
    public Event queryById(String eventId) {
        Optional<Event> optional = eventRepository.findById(eventId);
        return optional.get();
    }

    @Override
    public Event saveOrUpdateEvent(Event event) {
        List<EventCategory> eventCategory = event.getEventCategory();
        eventCategoryRepository.insert(eventCategory);

        EventImage eventImage = event.getEventImage();
        eventImageRepository.insert(eventImage);

        List<EventTicketing> eventTicket = event.getEventTicket();
        eventTicketingRepository.insert(eventTicket);

        EventCoupon eventCoupon = event.getEventCoupon();
        if (eventCoupon == null) {
            eventCoupon = new EventCoupon();
            eventCoupon.setEventCouponId("couponId");
            eventCouponRepository.insert(eventCoupon);
        }


        if (StringUtils.isBlank(event.getEventId())) {
            event.setCreatedDate(new Date());
            event.setUpdatedDate(new Date());
        } else {
            event.setUpdatedDate(new Date());
        }
        event.setEventStatus(1);

        return eventRepository.insert(event);
    }

    @Override
    public boolean deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
        return true;
    }

}
