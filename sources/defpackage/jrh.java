package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class jrh {
    public static String b(String str) {
        return "rimSize" + bgv.e(str);
    }

    public static String c(String str, String str2) {
        return str + "," + str2;
    }

    public static jns a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwWheelSizeUtil", "parseString input isEmpty");
            return new jns();
        }
        jns jnsVar = new jns();
        List asList = Arrays.asList(str.split(","));
        if (asList.size() > 3) {
            try {
                jnsVar.b(Long.parseLong((String) asList.get(0)));
                jnsVar.b(Integer.parseInt((String) asList.get(1)));
                jnsVar.e(Integer.parseInt((String) asList.get(2)));
                jnsVar.a(Integer.parseInt((String) asList.get(3)));
            } catch (NumberFormatException unused) {
                LogUtil.b("parseString NumberFormatException", new Object[0]);
            }
        }
        return jnsVar;
    }
}
