package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class cgs {
    public static int e(int i, int i2) {
        if (i <= 0 || i2 >= 18) {
            return i2;
        }
        int d = d(i, 0, 4);
        int d2 = d(i, 4, 6);
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(1) - d;
        return (calendar.get(2) + 1) - d2 < 0 ? i3 - 1 : i3;
    }

    public static int a(int i) {
        if (i <= 0) {
            return 0;
        }
        int d = d(i, 4, 6);
        int i2 = Calendar.getInstance().get(2);
        int i3 = (i2 + 1) - d;
        return i3 < 0 ? (i2 + 13) - d : i3;
    }

    private static int d(int i, int i2, int i3) {
        try {
            return Integer.parseInt(String.valueOf(i).substring(i2, i3));
        } catch (NumberFormatException unused) {
            LogUtil.b("HealthChildrenUitl", "dataConversion exception");
            return 0;
        }
    }

    public static boolean d(String str) {
        return cji.a(str, 20);
    }

    public static boolean e(String str, int i) {
        return !d(str) && i < 18;
    }
}
