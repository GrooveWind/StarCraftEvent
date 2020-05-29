package groove.wind.me.event.web.controller.event;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "票务管理api", tags = "票务管理api")
@RequestMapping("/biz/ticket")
@Log4j2
public class TicketController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/coupon")
    @ApiOperation(value = "抢票测试", notes = "抢票测试-限10张")
    public String coupon(String eventId, String personId) {
        if (redisTemplate.opsForValue().increment("t:c:" + eventId + ":" + personId) > 10) {
            return "您来晚了一步～";
        }
        return "优惠就是实惠（请及时付款）";
    }


}
