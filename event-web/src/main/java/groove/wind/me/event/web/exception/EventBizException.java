package groove.wind.me.event.web.exception;

import groove.wind.me.event.web.enums.StatusEnum;
import lombok.Getter;

@Getter
public class EventBizException extends RuntimeException {

    private StatusEnum statusEnum;

    public EventBizException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.statusEnum = statusEnum;
    }

}
