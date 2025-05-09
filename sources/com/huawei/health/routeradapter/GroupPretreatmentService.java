package com.huawei.health.routeradapter;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bzs;

/* loaded from: classes.dex */
public class GroupPretreatmentService implements PretreatmentService {
    private boolean c;

    /* renamed from: a, reason: collision with root package name */
    private Uri f2961a = null;
    private String e = null;
    private String b = null;

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.h("GroupPretreatmentService", "onPretreatment uri is null!");
            return false;
        }
        this.f2961a = zN_;
        boolean a2 = a();
        boolean c = c();
        if ((a2 || c) && c) {
            if (this.c) {
                LogUtil.a("GroupPretreatmentService", "jump to vip interview");
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addPath("#/vipinterview?verifyCode=" + this.b);
                bzs.e().loadH5ProApp(context, "com.huawei.health.h5.vip", builder);
                this.c = false;
            } else {
                LogUtil.a("GroupPretreatmentService", "jump to group");
                H5ProLaunchOption.Builder builder2 = new H5ProLaunchOption.Builder();
                builder2.addPath("#/?joinGroupQr=" + this.b);
                bzs.e().loadH5ProApp(context, "com.huawei.health.h5.groups", builder2);
            }
        }
        return false;
    }

    private boolean a() {
        boolean z;
        Uri uri = this.f2961a;
        if (uri == null) {
            LogUtil.c("GroupPretreatmentService", "handleCommand(Intent intent) schemeData == null");
            return false;
        }
        try {
            this.e = uri.getQueryParameter("address");
            z = true;
        } catch (IllegalArgumentException | UnsupportedOperationException unused) {
            LogUtil.b("GroupPretreatmentService", "addressParam: exception");
            z = false;
        }
        LogUtil.c("GroupPretreatmentService", "schemeData queryParameter = ", this.e);
        if (this.e != null) {
            return z;
        }
        LogUtil.h("GroupPretreatmentService", "The schemeData queryParameter is unsafe! ");
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c() {
        /*
            r6 = this;
            java.lang.String r0 = "verifyCode"
            java.lang.String r1 = "healthgroup"
            android.net.Uri r2 = r6.f2961a
            java.lang.String r3 = "GroupPretreatmentService"
            r4 = 0
            if (r2 != 0) goto L16
            java.lang.String r0 = "handleCommand(Intent intent) schemeData == null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r3, r0)
            return r4
        L16:
            boolean r2 = r2.getBooleanQueryParameter(r1, r4)     // Catch: java.lang.Throwable -> L48
            if (r2 == 0) goto L51
            android.net.Uri r2 = r6.f2961a     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r2.getQueryParameter(r1)     // Catch: java.lang.Throwable -> L48
            r6.b = r1     // Catch: java.lang.Throwable -> L48
            r2 = 1
            if (r1 == 0) goto L52
            boolean r1 = r1.contains(r0)     // Catch: java.lang.Throwable -> L48
            if (r1 == 0) goto L52
            r6.c = r2     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r6.b     // Catch: java.lang.Throwable -> L48
            java.lang.String[] r0 = r1.split(r0)     // Catch: java.lang.Throwable -> L48
            int r1 = r0.length     // Catch: java.lang.Throwable -> L48
            r5 = 2
            if (r1 != r5) goto L3e
            r0 = r0[r2]     // Catch: java.lang.Throwable -> L48
            r6.b = r0     // Catch: java.lang.Throwable -> L48
            goto L52
        L3e:
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = "Error string split"
            r0[r4] = r1     // Catch: java.lang.Throwable -> L48
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)     // Catch: java.lang.Throwable -> L48
            goto L52
        L48:
            java.lang.String r0 = "groupIdParam: exception"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)
        L51:
            r2 = r4
        L52:
            java.lang.String r0 = "schemeData groupParameter = "
            java.lang.String r1 = r6.b
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            com.huawei.hwlogsmodel.LogUtil.c(r3, r0)
            java.lang.String r0 = r6.b
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L70
            java.lang.String r0 = "The schemeData groupParameter is unsafe! "
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r3, r0)
            goto L71
        L70:
            r4 = r2
        L71:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.routeradapter.GroupPretreatmentService.c():boolean");
    }
}
