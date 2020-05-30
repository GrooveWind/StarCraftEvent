package groove.wind.me.event.web.service.event;

import groove.wind.me.event.web.entity.biz.event.Person;
import org.springframework.data.domain.Page;

public interface PersonService {

    Page queryWithPage(Integer pageNum, Integer pageSize);

    Person queryById(String id);

    Person saveOrUpdateEvent(Person entity);

    boolean delete(String id);

}
