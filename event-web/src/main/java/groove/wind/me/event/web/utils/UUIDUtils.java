package groove.wind.me.event.web.utils;

import java.util.UUID;

public abstract class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
