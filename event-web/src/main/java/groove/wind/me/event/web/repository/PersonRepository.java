package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.event.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

}
