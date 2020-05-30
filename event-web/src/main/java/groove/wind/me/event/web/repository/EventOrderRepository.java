package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.biz.event.EventOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventOrderRepository extends MongoRepository<EventOrder, String> {

}
