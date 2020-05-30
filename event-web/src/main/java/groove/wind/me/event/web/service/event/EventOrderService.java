package groove.wind.me.event.web.service.event;

import groove.wind.me.event.web.entity.biz.event.EventOrder;

public interface EventOrderService {

    EventOrder buildOrder(double amount, String item, String ip);

}
