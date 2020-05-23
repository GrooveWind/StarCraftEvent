package groove.wind.me.event.web.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "file_store_info")
@Data
public class FileStore extends BaseMongoEntity {

    @Id
    private String id;

    private String realName;
    private String storeName;


}
