package groove.wind.me.event.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StrUtils {

    public static boolean hasContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]]");
        Matcher m = p.matcher(str);
        return m.find();
    }

}
