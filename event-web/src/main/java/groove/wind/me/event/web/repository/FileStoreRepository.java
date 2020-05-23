package groove.wind.me.event.web.repository;

import groove.wind.me.event.web.entity.FileStore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileStoreRepository extends MongoRepository<FileStore, String> {

}
