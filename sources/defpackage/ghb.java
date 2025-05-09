package defpackage;

import com.google.gson.Gson;
import health.compact.a.CommonUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class ghb {
    public static <T> T a(String str, Class<T> cls) {
        if (str == null || str.isEmpty()) {
            str = "{}";
        }
        return (T) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
    }

    public static <T> List<T> c(String str, Class<T[]> cls) {
        if (str == null || str.trim().isEmpty()) {
            str = "[]";
        }
        return Arrays.asList((Object[]) new Gson().fromJson(CommonUtil.z(str), (Class) cls));
    }

    public static String e(Object obj) {
        return new Gson().toJson(obj);
    }
}
