package groove.wind.me.event.web.service.event.impl;

import groove.wind.me.event.web.entity.event.EventOrder;
import groove.wind.me.event.web.repository.EventOrderRepository;
import groove.wind.me.event.web.service.event.EventOrderService;
import groove.wind.me.event.web.utils.UUIDUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@Log4j2
public class EventOrderServiceImpl implements EventOrderService {

    @Autowired
    EventOrderRepository eventOrderRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String buildOrderNum() throws Exception {
        for (int i = 0; i < 5; i++) {
            String orderNum = UUIDUtils.getUUID();
            Query query = new Query(Criteria.where("orderNum").is(orderNum));
            if (!mongoTemplate.exists(query, EventOrder.class)) {
                return orderNum;
            }
        }
        log.warn("重试5次未能成功生成唯一订单号 at {}", LocalDateTime.now().toString());
        throw new Exception("系统异常，请联系管理员");
    }

    @Override
    public EventOrder buildOrder(String orderNum) {
        EventOrder order = new EventOrder();
        order.setOrderNum(orderNum);
        order.setOrderState(1);
        order.setCreatedDate(new Date());
        order.setUpdatedDate(new Date());
        eventOrderRepository.insert(order);
        return order;
    }


}
