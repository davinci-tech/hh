package defpackage;

import com.alipay.sdk.m.j.c;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class mc {
    public static String b(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }

    public static Map<String, String> c(lv lvVar, String str) {
        Map<String, String> a2 = a();
        try {
            return b(str);
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "FormatResultEx", th);
            return a2;
        }
    }

    public static Map<String, String> a() {
        c b = c.b(c.CANCELED.b());
        HashMap hashMap = new HashMap();
        hashMap.put("resultStatus", Integer.toString(b.b()));
        hashMap.put("memo", b.a());
        hashMap.put("result", "");
        return hashMap;
    }

    public static Map<String, String> b(String str) {
        String[] split = str.split(";");
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            hashMap.put(substring, b(str2, substring));
        }
        return hashMap;
    }

    public static String d(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile("(^|;)" + str2 + "=\\{([^}]*?)\\}").matcher(str);
            if (matcher.find()) {
                return matcher.group(2);
            }
        } catch (Throwable th) {
            ma.c(th);
        }
        return "?";
    }
}
