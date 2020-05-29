package groove.wind.me.event.web.service.event.impl;

import cn.craccd.mongoHelper.utils.MongoHelper;
import groove.wind.me.event.web.entity.event.Person;
import groove.wind.me.event.web.service.event.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    MongoHelper mongoHelper;

    @Override
    public Person insert(Person person) {
        mongoHelper.insert(person);
        return person;
    }

}
