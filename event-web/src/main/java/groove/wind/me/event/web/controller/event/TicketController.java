package groove.wind.me.event.web.controller.event;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "票务管理api", tags = "票务管理api")
@RequestMapping("/biz/ticket")
@Log4j2
public class TicketController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/coupon")
    @ApiOperation(value = "抢票测试", notes = "抢票测试-限10张")
    public String coupon(@RequestParam("eventId") String eventId,
                         @RequestParam(value = "personId", required = false) String personId) {

        String key = "t:c" + eventId;
        personId = StringUtils.isNotBlank(personId) ? personId : "";
        if (redisTemplate.hasKey(key)) {
            return personId + "请勿重复抢票";
        }

        if (redisTemplate.opsForValue().increment("t:c:" + eventId) > 10) {
            return "您来晚了一步～";
        }

        return personId + "抢票成功，请及时付款";
    }


}
