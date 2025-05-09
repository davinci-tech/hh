package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import health.compact.a.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.TreeSet;

/* loaded from: classes4.dex */
public class gic {
    public static float d(float f) {
        return f / 1000.0f;
    }

    public static int e(Object obj) {
        return a(obj, 0);
    }

    public static int a(Object obj, int i) {
        String c = StringUtils.c(obj);
        if ("".equals(c)) {
            return i;
        }
        try {
            return Integer.parseInt(c);
        } catch (NumberFormatException e) {
            LogUtil.e("Suggestion_StrUtil", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }

    public static Long c(Object obj) {
        long j = 0L;
        String c = StringUtils.c(obj);
        if ("".equals(c)) {
            return j;
        }
        try {
            return Long.valueOf(c);
        } catch (NumberFormatException e) {
            LogUtil.e("Suggestion_StrUtil", LogAnonymous.b((Throwable) e));
            return j;
        }
    }

    public static Float b(Object obj) {
        String c = StringUtils.c(obj);
        if ("".equals(c)) {
            return Float.valueOf(0.0f);
        }
        return Float.valueOf(e(c));
    }

    public static boolean d(Object obj) {
        String c = StringUtils.c(obj);
        if ("".equals(c)) {
            return false;
        }
        return Boolean.parseBoolean(c);
    }

    public static int c(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.e("Suggestion_StrUtil", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }

    public static float e(String str) {
        if (str == null) {
            return 0.0f;
        }
        try {
            float parseFloat = Float.parseFloat(str);
            if (Float.isNaN(parseFloat)) {
                return 0.0f;
            }
            return parseFloat;
        } catch (NumberFormatException e) {
            LogUtil.e("Suggestion_StrUtil", LogAnonymous.b((Throwable) e));
            return 0.0f;
        }
    }

    public static String a(String[] strArr) {
        if (strArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String[] b(String str) {
        return str == null ? new String[0] : str.split(",");
    }

    public static String a(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.e("Suggestion_StrUtil", LogAnonymous.b((Throwable) e));
            return str;
        }
    }

    public static String d(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.e("Suggestion_StrUtil", LogAnonymous.b((Throwable) e));
            return str;
        }
    }

    public static TreeSet<Integer> i(String str) {
        if (str == null) {
            return new TreeSet<>();
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (String str2 : str.split(",")) {
            int e = e((Object) str2);
            if (e > 0) {
                treeSet.add(Integer.valueOf(e - 1));
            }
        }
        return treeSet;
    }

    public static String e(float f) {
        return UnitUtil.e(f / 60.0f, 1, 0);
    }

    public static String a(float f) {
        return UnitUtil.e(f, 1, 0);
    }

    public static String d(Context context, int i, Object... objArr) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Suggestion_StrUtil", "getString context == null");
            return "";
        }
        return String.format(context.getApplicationContext().getResources().getString(i), objArr);
    }

    public static String b(int i, int i2, Object... objArr) {
        return BaseApplication.getContext().getResources().getQuantityString(i, i2, objArr);
    }
}
