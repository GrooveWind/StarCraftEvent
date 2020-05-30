package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.biz.event.WechatInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WechatInfoRepository extends MongoRepository<WechatInfo, String> {
}
