package groove.wind.me.event.web.controller.event;


import groove.wind.me.event.web.entity.biz.event.Event;
import groove.wind.me.event.web.enums.StatusEnum;
import groove.wind.me.event.web.exception.EventBizException;
import groove.wind.me.event.web.service.event.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "赛事管理api", tags = "赛事管理api")
@RequestMapping("/biz/event")
@Log4j2
public class EventController {

    @Autowired
    EventService eventService;


    @PostMapping("/eventList/pageNum/{pageNum}/pageSize/{pageSize}")
    @ApiOperation(value = "查看赛事列表", notes = "查看赛事列表")
    public Page<Event> eventList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return eventService.queryEvents(pageNum, pageSize, "");
    }


    @GetMapping("/detailEvent/{eventId}")
    @ApiOperation(value = "查看赛事详情", notes = "查看赛事详情")
    public Event detailEvent(@PathVariable("eventId") String eventId) {
        return eventService.queryById(eventId);
    }

    @PostMapping("/createEvent")
    @ApiOperation(value = "创建赛事", notes = "创建赛事", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveOrUpdateEvent(event);
    }

    @PostMapping("/modifyEvent")
    @ApiOperation(value = "更新赛事", notes = "更新赛事", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event modifyEvent(@RequestBody Event event) {
        if (StringUtils.isBlank(event.getEventId())) {
            throw new EventBizException(StatusEnum.MODIFY_LOSE_ID);
        }
        return eventService.saveOrUpdateEvent(event);
    }

    @PostMapping("/deleteEvent/{eventId}")
    @ApiOperation(value = "删除赛事", notes = "删除赛事")
    public boolean deleteEvent(@PathVariable("eventId") String eventId) {
        return eventService.deleteEvent(eventId);
    }


}
