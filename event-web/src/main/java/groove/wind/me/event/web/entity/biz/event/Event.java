package groove.wind.me.event.web.entity.biz.event;


import com.alibaba.fastjson.annotation.JSONField;
import groove.wind.me.event.web.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Document(collection = "event")
@Data
public class Event extends BaseMongoEntity {

    @Id
    private String eventId;


    private String eventEnglishName;

    private String eventChineseName;

    @NotNull(message = "请填写赛事时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date eventDate;

    @DBRef
    @NotNull(message = "请填写赛事项目")
    private List<EventCategory> eventCategory;

    @NotBlank(message = "请填写赛事地点")
    private String eventLocation;

    @NotNull(message = "请完善相关宣传图片")
    private EventImage eventImage;

    @DBRef
    @NotNull(message = "请完善相关票务信息")
    private List<EventTicketing> eventTicket;

    // 1-初始化 2-开放报名 3-截止报名 4-活动开始 5-活动结束
    private Integer eventStatus;

    private List<String> eventParticipant;

    @DBRef
    private EventCoupon eventCoupon;


}
