package groove.wind.me.event.web.service.event.impl;

import groove.wind.me.event.web.entity.biz.event.EventOrder;
import groove.wind.me.event.web.repository.EventOrderRepository;
import groove.wind.me.event.web.service.event.EventOrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class EventOrderServiceImpl implements EventOrderService {

    @Autowired
    EventOrderRepository eventOrderRepository;

    @Override
    public EventOrder buildOrder(double amount, String item, String ip) {
        EventOrder order = new EventOrder();
        order.setAmount((int) amount * 100);
        order.setItem(item);
        order.setIpAddr(ip);
        order.setOrderState(1);
        order.setCreatedDate(new Date());
        order.setUpdatedDate(new Date());
        eventOrderRepository.insert(order);
        return order;
    }


}
