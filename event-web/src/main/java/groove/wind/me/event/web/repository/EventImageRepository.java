package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.biz.event.EventImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventImageRepository extends MongoRepository<EventImage, String> {

}
