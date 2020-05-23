package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

    Page<Event> findByEventChineseNameLike(String eventName, Pageable pageable);

    Page<Event> findByEventEnglishNameLike(String eventName, Pageable pageable);

}
