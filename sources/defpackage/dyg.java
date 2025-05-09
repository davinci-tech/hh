package defpackage;

import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class dyg {
    public static Set<String> c() {
        HashSet hashSet = new HashSet();
        hashSet.add("BMI");
        hashSet.add("weightCardClicks3Month");
        hashSet.add("weightUpload3Month");
        hashSet.add("sleepCardClicks3Month");
        hashSet.add("sleepUpload3Month");
        hashSet.add("heartRateCardClicks3Month");
        hashSet.add("heartRateUpload3Month");
        hashSet.add("bloodOxygenCardClicks3Month");
        hashSet.add("bloodOxygenUpload3Month");
        return hashSet;
    }

    public static boolean d(String str) {
        if (str.contains("&&")) {
            for (String str2 : str.split("&&")) {
                if (!b(str2)) {
                    return false;
                }
            }
            return true;
        }
        if (str.contains("||")) {
            for (String str3 : str.split("\\|\\|")) {
                if (b(str3)) {
                    return true;
                }
            }
            return false;
        }
        return b(str);
    }

    private static boolean b(String str) {
        if (str.contains("==")) {
            dxb c = c(str, "==");
            if (c == null) {
                return false;
            }
            return d(c.c(), c.d());
        }
        if (str.contains("!=")) {
            dxb c2 = c(str, "!=");
            if (c2 == null) {
                return false;
            }
            return h(c2.c(), c2.d());
        }
        if (str.contains(HiDataFilter.DataFilterExpression.BIGGER_EQUAL)) {
            dxb c3 = c(str, HiDataFilter.DataFilterExpression.BIGGER_EQUAL);
            if (c3 == null) {
                return false;
            }
            return f(c3.c(), c3.d());
        }
        if (str.contains(HiDataFilter.DataFilterExpression.LESS_EQUAL)) {
            dxb c4 = c(str, HiDataFilter.DataFilterExpression.LESS_EQUAL);
            if (c4 == null) {
                return false;
            }
            return c(c4.c(), c4.d());
        }
        if (str.contains(HiDataFilter.DataFilterExpression.BIGGER_THAN)) {
            dxb c5 = c(str, HiDataFilter.DataFilterExpression.BIGGER_THAN);
            if (c5 == null) {
                return false;
            }
            return b(c5.c(), c5.d());
        }
        if (str.contains(HiDataFilter.DataFilterExpression.LESS_THAN)) {
            dxb c6 = c(str, HiDataFilter.DataFilterExpression.LESS_THAN);
            if (c6 == null) {
                return false;
            }
            return a(c6.c(), c6.d());
        }
        LogUtil.a("UserLabelCloudRuleUtil", "is not match rule");
        return false;
    }

    private static dxb c(String str, String str2) {
        String[] split = str.split(str2);
        if (split.length < 2) {
            return null;
        }
        try {
            return new dxb(split[0], Float.parseFloat(split[1]));
        } catch (NumberFormatException unused) {
            LogUtil.b("UserLabelCloudRuleUtil", split[1], "splitRule is invalid");
            return null;
        }
    }

    private static boolean d(String str, float f) {
        float e = e(str);
        return ((double) Math.abs(e - 0.0f)) >= 1.0E-6d && ((double) Math.abs(e - f)) < 1.0E-6d;
    }

    private static boolean h(String str, float f) {
        float e = e(str);
        return ((double) Math.abs(e - 0.0f)) >= 1.0E-6d && ((double) Math.abs(e - f)) > 1.0E-6d;
    }

    private static boolean f(String str, float f) {
        float e = e(str);
        return ((double) Math.abs(e - 0.0f)) >= 1.0E-6d && e >= f;
    }

    private static boolean c(String str, float f) {
        float e = e(str);
        return ((double) Math.abs(e - 0.0f)) >= 1.0E-6d && e <= f;
    }

    private static boolean b(String str, float f) {
        float e = e(str);
        return ((double) Math.abs(e - 0.0f)) >= 1.0E-6d && e > f;
    }

    private static boolean a(String str, float f) {
        float e = e(str);
        return ((double) Math.abs(e - 0.0f)) >= 1.0E-6d && e < f;
    }

    public static void c(int i, int i2, int i3, int i4) {
        LogUtil.a("UserLabelCloudRuleUtil", "enter saveClickCount ", Integer.valueOf(i), ", ", Integer.valueOf(i2), ", ", Integer.valueOf(i3), ", ", Integer.valueOf(i4));
        e("weightCardClicks3Month", i);
        e("sleepCardClicks3Month", i2);
        e("heartRateCardClicks3Month", i3);
        e("bloodOxygenCardClicks3Month", i4);
    }

    public static void e(int i, int i2) {
        LogUtil.a("UserLabelCloudRuleUtil", "type ", Integer.valueOf(i), ", count ", Integer.valueOf(i2));
        if (i == 2004) {
            e("weightUpload3Month", i2);
            return;
        }
        if (i == 44004) {
            e("sleepUpload3Month", i2);
        } else if (i == 46016) {
            e("heartRateUpload3Month", i2);
        } else {
            if (i != 47201) {
                return;
            }
            e("bloodOxygenUpload3Month", i2);
        }
    }

    public static void e(String str, float f) {
        SharedPreferenceManager.e("userLabelValue", str, f);
    }

    private static float e(String str) {
        if (SharedPreferenceManager.e("userLabelValue", str)) {
            return SharedPreferenceManager.b("userLabelValue", str, 0.0f);
        }
        return 0.0f;
    }
}
