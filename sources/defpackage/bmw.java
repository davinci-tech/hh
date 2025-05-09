package defpackage;

import com.huawei.haf.common.utils.NetworkUtil;
import health.compact.a.LogUtil;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class bmw {
    private static LinkedHashMap<String, String> b = new LinkedHashMap<>(16);
    private static final Object d = new Object();

    public static void e(int i, String str, String str2, String str3) {
        if (!NetworkUtil.j()) {
            LogUtil.a("DeviceOperationUtil", "reportMonitorResult monitorType is unknown.");
            return;
        }
        if (i == -1) {
            LogUtil.a("DeviceOperationUtil", "reportMonitorResult monitorType is unknown.");
            return;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        a(i, str, str2, str3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a0, code lost:
    
        if (java.lang.Integer.parseInt((java.lang.String) r5.get("monitorType")) != 100042) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c3, code lost:
    
        if (r11.contains("?") == false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(int r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bmw.a(int, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
