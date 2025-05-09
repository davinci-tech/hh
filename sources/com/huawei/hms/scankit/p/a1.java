package com.huawei.hms.scankit.p;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsApp;
import java.util.Locale;

/* loaded from: classes4.dex */
public class a1 {
    private static final String c = "a1";

    /* renamed from: a, reason: collision with root package name */
    private String f5728a = "UNKNOWN";
    private String b;

    public a1(Context context, boolean z) {
        this.b = "UNKNOWN";
        a(context, z);
        this.b = this.b.toUpperCase(Locale.ENGLISH);
    }

    private boolean b() {
        return !"UNKNOWN".equals(this.b);
    }

    public String a() {
        return this.b;
    }

    private void a(Context context, boolean z) {
        if (context != null) {
            try {
                this.b = GrsApp.getInstance().getIssueCountryCode(context);
                if (b()) {
                    Logger.i(c, "getCountryCode unknown");
                }
            } catch (NullPointerException unused) {
                Logger.w(c, "get CountryCode error");
            } catch (Exception unused2) {
                Logger.w(c, "get CountryCode error");
            }
        }
    }
}
