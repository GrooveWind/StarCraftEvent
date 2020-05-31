package groove.wind.me.event.web.exception;

import groove.wind.me.event.web.entity.CommonResult;
import groove.wind.me.event.web.enums.StatusEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@RestControllerAdvice
@Log4j2
public class ApplicationExceptionHandler {

    @ExceptionHandler({EventBizException.class})
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleApplicationException(EventBizException e) {
        return CommonResult.build(e.getStatusEnum());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleConstraintViolationException(ConstraintViolationException e) {
        String[] msg = e.getMessage().split(",");
        String m = msg[0];
        String[] fm = m.split(":");
        String message = fm[1];

        return CommonResult.build(StatusEnum.PARAM_ERR, message, null);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException e) throws Exception {
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        return CommonResult.build(StatusEnum.PARAM_ERR, error.getDefaultMessage(), null);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handleRuntimeException(RuntimeException e) {
        log.error(e);
        return CommonResult.err();
    }

}
