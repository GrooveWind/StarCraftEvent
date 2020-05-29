package groove.wind.me.event.web.utils;

import groove.wind.me.event.web.entity.BaseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;

@Log4j2
public class EventReflectionUtils {

    public static <T extends BaseEntity> boolean checkIdExists(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean fieldHasAnno = field.isAnnotationPresent(Id.class);
            if (fieldHasAnno) {
                try {
                    field.setAccessible(true);
                    Object idValue = field.get(entity);
                    return idValue != null && !"".equals((idValue + "").trim());
                } catch (Exception e) {
                    log.error("获取id值失败", e);
                    break;
                }
            }
        }
        return false;
    }

}
