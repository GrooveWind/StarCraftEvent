package groove.wind.me.event.web.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class WechatPayResultMap extends HashMap<String, Object> {

    public WechatPayResultMap() {
        put("state", true);
        put("code", 0);
        put("msg", "success");
    }

    public static WechatPayResultMap error(int code, String msg) {
        WechatPayResultMap r = new WechatPayResultMap();
        r.put("state", false);
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static WechatPayResultMap error(String msg) {
        return error(HttpStatus.METHOD_NOT_ALLOWED.value(), msg);
    }

    public static WechatPayResultMap error() {
        return error(HttpStatus.METHOD_NOT_ALLOWED.value(), "未知异常，请联系管理员");
    }

    public static WechatPayResultMap ok(String msg) {
        WechatPayResultMap r = new WechatPayResultMap();
        r.put("msg", msg);
        return r;
    }

    public static WechatPayResultMap ok(Map<String, Object> par) {
        WechatPayResultMap r = new WechatPayResultMap();
        r.putAll(par);
        return r;
    }

    public static WechatPayResultMap ok() {
        return new WechatPayResultMap();
    }

    public WechatPayResultMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
