package groove.wind.me.event.web.service.event;

import groove.wind.me.event.web.entity.event.EventOrder;

public interface EventOrderService {

    String buildOrderNum() throws Exception;

    EventOrder buildOrder(String orderNum);

}
