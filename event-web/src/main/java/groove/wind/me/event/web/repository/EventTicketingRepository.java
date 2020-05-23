package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.event.EventTicketing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventTicketingRepository extends MongoRepository<EventTicketing, String> {
}
