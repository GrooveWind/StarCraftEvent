package groove.wind.me.event.web.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class RefererInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String host = request.getServerName();
        String referer = request.getHeader("referer");
        log.info("host: {}, referer: {}", host, referer);

        return true;
    }

}
