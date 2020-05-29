package groove.wind.me.event.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import groove.wind.me.event.web.entity.CommonResult;
import groove.wind.me.event.web.utils.UrlUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Log4j2
@ControllerAdvice
public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String url = serverHttpRequest.getURI().getPath();

        if (UrlUtils.checkUrl(url)) {
            return body;
        }

        Object result;
        if (body instanceof CommonResult) {
            result = body;
        } else if (body instanceof String) {
            result = JSONObject.toJSONString(CommonResult.success(body));
        } else {
            result = JSONObject.toJSONString(CommonResult.success(body));
        }

        return result;
    }

}
