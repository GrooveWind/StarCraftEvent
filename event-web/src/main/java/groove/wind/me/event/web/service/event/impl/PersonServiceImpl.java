package groove.wind.me.event.web.service.event.impl;

import groove.wind.me.event.web.entity.biz.event.Person;
import groove.wind.me.event.web.repository.PersonRepository;
import groove.wind.me.event.web.service.event.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Page queryWithPage(Integer pageNum, Integer pageSize) {
        return personRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Person queryById(String id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person saveOrUpdateEvent(Person entity) {
        return personRepository.insert(entity);
    }

    @Override
    public boolean delete(String id) {
        personRepository.deleteById(id);
        return true;
    }

}
