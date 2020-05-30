package groove.wind.me.event.web.service.event.impl;

import groove.wind.me.event.web.entity.biz.event.Person;
import groove.wind.me.event.web.entity.biz.event.PersonPortfolio;
import groove.wind.me.event.web.entity.biz.event.WechatInfo;
import groove.wind.me.event.web.repository.PersonPortfolioRepository;
import groove.wind.me.event.web.repository.PersonRepository;
import groove.wind.me.event.web.repository.WechatInfoRepository;
import groove.wind.me.event.web.service.event.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    WechatInfoRepository wechatInfoRepository;

    @Autowired
    PersonPortfolioRepository personPortfolioRepository;


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
        WechatInfo wci = entity.getWechatInfo();
        wechatInfoRepository.insert(wci);

        List<PersonPortfolio> portfolio = entity.getPortfolio();
        personPortfolioRepository.insert(portfolio);

        if (StringUtils.isBlank(entity.getUniqueID())) {
            entity.setCreatedDate(new Date());
            entity.setUpdatedDate(new Date());
        } else {
            entity.setUpdatedDate(new Date());
        }

        return personRepository.insert(entity);
    }

    @Override
    public boolean delete(String id) {
        personRepository.deleteById(id);
        return true;
    }

}
