package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.biz.event.EventCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventCategoryRepository extends MongoRepository<EventCategory, String> {

}
