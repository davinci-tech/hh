package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private volatile String f10913a;
    private GrsClient b;

    public static b a() {
        return a.f10914a;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static b f10914a = new b();
    }

    private b() {
    }

    public String a(String str, String str2, String str3) {
        synchronized (this) {
            String str4 = "";
            try {
            } catch (Exception e) {
                HwLog.i("GRSSdk", "getServerUrl Exception:" + HwLog.printException(e));
            }
            if (TextUtils.isEmpty(str)) {
                HwLog.e("GRSSdk", "getServerUrl country is empty");
                return "";
            }
            if (TextUtils.isEmpty(str3)) {
                HwLog.e("GRSSdk", "getServerUrl serviceName is empty");
                return "";
            }
            if (!str.equalsIgnoreCase(this.f10913a) || this.b == null) {
                HwLog.i("GRSSdk", "getServerUrl !country.equalsIgnoreCase(lastInitCountry)");
                GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
                grsBaseInfo.setSerCountry(str);
                grsBaseInfo.setCountrySource("APP");
                this.b = new GrsClient(Environment.getApplicationContext(), grsBaseInfo);
            }
            str4 = this.b.synGetGrsUrl(str3, str2);
            this.f10913a = str;
            FlavorConfig.safeHwLog("GRSSdk", "key:" + str2 + ",url:" + str4);
            return str4;
        }
    }

    public String a(String str, String str2) {
        return a(str2, str, c.a(str));
    }
}
