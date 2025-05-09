package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.server.PermissionRsp;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.PermissionEntity;
import java.util.List;

/* loaded from: classes5.dex */
public class ph {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7437a = "ph";
    private Context b;
    private fx c;
    private a d;

    public interface a {
        void a(List<PermissionEntity> list);
    }

    public void a(final AppInfo appInfo) {
        if (appInfo != null && com.huawei.openalliance.ad.utils.bg.a(appInfo.getPermissions()) && appInfo.u()) {
            final com.huawei.openalliance.ad.utils.cc a2 = com.huawei.openalliance.ad.utils.cc.a();
            final String c = c(appInfo);
            r0 = TextUtils.isEmpty(c) ? null : a2.a(c);
            if (com.huawei.openalliance.ad.utils.bg.a(r0)) {
                com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.ph.1
                    @Override // java.lang.Runnable
                    public void run() {
                        List<PermissionEntity> b = ph.this.b(appInfo);
                        if (!com.huawei.openalliance.ad.utils.bg.a(b)) {
                            a2.a(c, b);
                        }
                        ph.this.a(b);
                    }
                });
                return;
            }
            appInfo.b(r0);
        }
        a(r0);
    }

    private String c(AppInfo appInfo) {
        if (appInfo == null) {
            return "";
        }
        return appInfo.getPackageName() + "_" + appInfo.v() + "_" + appInfo.w() + "_" + com.huawei.openalliance.ad.utils.d.a() + "_" + com.huawei.openalliance.ad.utils.dd.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PermissionEntity> b(AppInfo appInfo) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.getPackageName())) {
            ho.c(f7437a, "empty request parameters");
            return null;
        }
        PermissionRsp a2 = this.c.a(appInfo.getPackageName(), appInfo.v(), appInfo.w(), appInfo.z(), com.huawei.openalliance.ad.utils.x.k(this.b));
        if (a2 != null) {
            ho.b(f7437a, "request permissions, retCode: %s", Integer.valueOf(a2.a()));
            appInfo.a(a2.b());
        }
        return appInfo.getPermissions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<PermissionEntity> list) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(list);
        }
    }

    public ph(Context context, a aVar) {
        this(context);
        this.d = aVar;
    }

    public ph(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.c = fb.a(applicationContext);
    }
}
