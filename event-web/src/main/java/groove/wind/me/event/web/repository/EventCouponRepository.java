package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.event.EventCoupon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventCouponRepository extends MongoRepository<EventCoupon, String> {
}
