package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.appgallery.coreservice.h;
import com.huawei.hms.framework.network.grs.GrsApp;

/* loaded from: classes2.dex */
public class afr {
    private static afr b;
    private static final Object c = new Object();
    private String e = "";

    /* renamed from: a, reason: collision with root package name */
    private String f194a = "";

    public void d(Context context, String str) {
        if (str == null) {
            str = "";
        }
        if (this.e.equals(str)) {
            return;
        }
        this.e = str;
        new h(context).b("hc", str);
    }

    public String c(Context context) {
        String a2 = new h(context).a("hc", "");
        this.e = a2;
        if (TextUtils.isEmpty(a2)) {
            this.e = GrsApp.getInstance().getIssueCountryCode(context);
        }
        return this.e;
    }

    public void e(Context context, String str) {
        if (str == null) {
            str = "";
        }
        if (this.f194a.equals(str)) {
            return;
        }
        this.f194a = str;
        new h(context).b("grs_app_name", str);
    }

    public String d(Context context) {
        String a2 = new h(context).a("grs_app_name", "");
        this.f194a = a2;
        return a2;
    }

    public static afr e() {
        afr afrVar;
        synchronized (c) {
            if (b == null) {
                b = new afr();
            }
            afrVar = b;
        }
        return afrVar;
    }

    private afr() {
    }
}
