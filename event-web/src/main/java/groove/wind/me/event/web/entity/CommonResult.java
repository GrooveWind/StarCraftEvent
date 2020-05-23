package groove.wind.me.event.web.entity;

import groove.wind.me.event.web.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private int code;
    private String message;
    private T body;

    public static <T> CommonResult<T> success(T body) {
        return build(StatusEnum.SUCCESS, body);
    }

    public static <T> CommonResult<T> fail(T body) {
        return build(StatusEnum.FAIL, body);
    }

    public static <T> CommonResult<T> err() {
        return build(StatusEnum.ERROR, null);
    }

    public static <T> CommonResult<T> build(StatusEnum status) {
        return build(status, null);
    }

    public static <T> CommonResult<T> build(StatusEnum status, T body) {
        return new CommonResult<>(status.getCode(), status.getMessage(), body);
    }

    public static <T> CommonResult<T> build(StatusEnum status, String errMsg, T body) {
        return new CommonResult<>(status.getCode(), errMsg, body);
    }

}
