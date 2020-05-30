package groove.wind.me.event.web.utils;

public final class UrlUtils {

    public static boolean checkUrl(String url) {
        return url.contains("swagger")
                || url.startsWith("/v2/api-docs")
                || url.startsWith("/biz/pay");
    }

}
