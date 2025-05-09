package defpackage;

import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class kpw {
    public static int a(int i) {
        switch (i) {
            case 287:
            case 288:
            case ComponentInfo.PluginPay_A_N /* 289 */:
                return 287;
            default:
                return i;
        }
    }

    public static int d(double d, int i) {
        int i2;
        int i3 = 0;
        if (0.0d == d) {
            return 0;
        }
        long j = (long) d;
        String format = new SimpleDateFormat("HH").format(new Date(j));
        String format2 = new SimpleDateFormat("mm").format(new Date(j));
        try {
            i2 = Integer.parseInt(format);
        } catch (NumberFormatException unused) {
            i2 = 0;
        }
        try {
            i3 = Integer.parseInt(format2);
        } catch (NumberFormatException unused2) {
            LogUtil.a("HWHealthDataMgrUtil", "calTime NumberFormatException");
            LogUtil.a("HWHealthDataMgrUtil", ",hour = " + i2 + ",min = " + i3);
            if (1 == i) {
                i2 += 24;
            }
            int i4 = i3 + (i2 * 60);
            LogUtil.a("HWHealthDataMgrUtil", "time = " + i4);
            return i4;
        }
        LogUtil.a("HWHealthDataMgrUtil", ",hour = " + i2 + ",min = " + i3);
        if (1 == i && i2 < 12) {
            i2 += 24;
        }
        int i42 = i3 + (i2 * 60);
        LogUtil.a("HWHealthDataMgrUtil", "time = " + i42);
        return i42;
    }
}
