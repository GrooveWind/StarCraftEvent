package groove.wind.me.event.web.controller.event;


import groove.wind.me.event.web.entity.event.Event;
import groove.wind.me.event.web.enums.StatusEnum;
import groove.wind.me.event.web.exception.EventBizException;
import groove.wind.me.event.web.service.event.EventService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "赛事活动api", tags = "赛事活动api")
@RequestMapping("/biz/event")
@Log4j2
public class EventController {

    @Autowired
    EventService eventService;


    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveOrUpdateEvent(event);
    }

    @PostMapping("/modify")
    public Event modifyEvent(@RequestBody Event event) {
        if (StringUtils.isBlank(event.getEventId())) {
            throw new EventBizException(StatusEnum.MODIFY_LOSE_ID);
        }
        return eventService.saveOrUpdateEvent(event);
    }

    @GetMapping("/detail/{eventId}")
    public Event detail(@PathVariable("eventId") String eventId) {
        return eventService.queryById(eventId);
    }


}
