package groove.wind.me.event.web.entity.biz.event;

import groove.wind.me.event.web.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "person")
@Data
public class Person extends BaseMongoEntity {

    @Id
    private String uniqueID;

    @NotBlank(message = "请填写真实姓名")
    private String realName;
    private String nickName;

    @NotBlank(message = "请填写性别")
    private String sex;

    @NotBlank(message = "请填写国籍")
    private String nationality;

    @DBRef
    private WechatInfo wechatInfo;

    private List<String> workshop;

    @DBRef
    @NotNull(message = "请完善个人经历")
    private List<PersonPortfolio> portfolio;

    private List<String> eventRecord;


}
