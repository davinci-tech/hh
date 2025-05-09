package com.huawei.updatesdk.b.e;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.appgallery.markethomecountrysdk.api.HomeCountryApi;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.updatesdk.service.otaupdate.f;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* loaded from: classes7.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private String f10837a;
    private String b;

    public abstract int a(Context context, String str);

    abstract String a();

    abstract String a(Context context);

    public void a(String str) {
    }

    public abstract void a(List<String> list);

    public abstract String b();

    public abstract boolean c(Context context);

    public String b(Context context) {
        if (!TextUtils.equals("SECURITY", com.huawei.updatesdk.a.a.c.a.a.b.a())) {
            return d(context);
        }
        com.huawei.updatesdk.a.a.a.b("AbstractTaskInit", "UpdateSDK Get url is security url");
        return a(context);
    }

    private void e(Context context) {
        String issueCountryCode = GrsApp.getInstance().getIssueCountryCode(context);
        com.huawei.updatesdk.a.a.a.b("AbstractTaskInit", "UpdateSDK use grs issue country code");
        f.e().a(issueCountryCode);
    }

    private String d(Context context) {
        c();
        String a2 = f.e().a();
        if (!TextUtils.isEmpty(this.f10837a) && TextUtils.equals(this.f10837a, a2) && !TextUtils.isEmpty(this.b)) {
            com.huawei.updatesdk.a.a.a.b("AbstractTaskInit", "UpdateSDK Get url from cache!" + b(this.b));
            return this.b;
        }
        this.f10837a = a2;
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setSerCountry(a2);
        this.b = new GrsClient(context, grsBaseInfo).synGetGrsUrl(a(), "ROOT");
        com.huawei.updatesdk.a.a.a.b("AbstractTaskInit", "UpdateSDK Get url from GRS_SDK Success!" + b(this.b));
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        com.huawei.updatesdk.a.a.a.b("AbstractTaskInit", "UpdateSDK Get url is default url");
        return a(context);
    }

    private void c() {
        String str;
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        try {
            String str2 = (String) Tasks.await(HomeCountryApi.getHomeCountry(a2, "UpdateSDK", false));
            if (TextUtils.isEmpty(str2)) {
                e(a2);
            } else {
                f.e().a(str2);
            }
        } catch (InterruptedException unused) {
            str = "get country code InterruptedException";
            com.huawei.updatesdk.a.a.a.a("AbstractTaskInit", str);
            e(a2);
        } catch (ExecutionException unused2) {
            str = "get country code ExecutionException.";
            com.huawei.updatesdk.a.a.a.a("AbstractTaskInit", str);
            e(a2);
        }
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.substring(0, str.indexOf(46));
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("AbstractTaskInit", e.toString());
            return null;
        }
    }
}
