package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.biz.event.PersonPortfolio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonPortfolioRepository extends MongoRepository<PersonPortfolio, String> {
}
