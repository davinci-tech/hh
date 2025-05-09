package defpackage;

import android.database.Cursor;
import com.huawei.hihealth.HiThirdIdentity;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;

/* loaded from: classes7.dex */
public class ijw {

    /* renamed from: a, reason: collision with root package name */
    private ihd f13401a;

    private ijw() {
        this.f13401a = ihd.c();
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final ijw f13402a = new ijw();
    }

    public static ijw d() {
        return b.f13402a;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(com.huawei.hihealth.HiThirdIdentity r5) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            if (r1 != 0) goto L14
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            goto L15
        L14:
            r1 = r0
        L15:
            boolean r5 = r4.d(r5)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            if (r1 == 0) goto L22
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            defpackage.ivu.j(r2, r0)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
        L22:
            if (r1 == 0) goto L2b
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r1, r0)
        L2b:
            return r5
        L2c:
            r5 = move-exception
            goto L33
        L2e:
            r5 = move-exception
            r1 = r0
            goto L51
        L31:
            r5 = move-exception
            r1 = r0
        L33:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = "insertOrUpdateThirdIdentity: "
            r2[r0] = r3     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L50
            r3 = 1
            r2[r3] = r5     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "HiH_ThirdIdentiyManager"
            com.huawei.hihealth.util.ReleaseLogUtil.d(r5, r2)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L4f
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r5, r0)
        L4f:
            return r0
        L50:
            r5 = move-exception
        L51:
            if (r1 == 0) goto L5a
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r1, r0)
        L5a:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijw.c(com.huawei.hihealth.HiThirdIdentity):boolean");
    }

    private boolean d(HiThirdIdentity hiThirdIdentity) {
        long b2;
        LogUtil.c("Debug_ThirdIdentiyManager", "insertOrUpdateThirdIdentitySync");
        if (c(hiThirdIdentity.getPackageName())) {
            LogUtil.c("Debug_ThirdIdentiyManager", "insertAccountInfo update thirdIdentiy");
            b2 = a(hiThirdIdentity);
        } else {
            LogUtil.c("Debug_ThirdIdentiyManager", "insertAccountInfo insert new thirdIdentiy");
            b2 = b(hiThirdIdentity);
        }
        return iil.a(b2);
    }

    private boolean c(String str) {
        LogUtil.c("Debug_ThirdIdentiyManager", "queryThirdIdentityExist");
        if (HiCommonUtil.b(str)) {
            LogUtil.h("Debug_ThirdIdentiyManager", "queryThirdIdentityExist parameter is wrong");
            return false;
        }
        return iih.bAv_(bBp_(str));
    }

    public long b(HiThirdIdentity hiThirdIdentity) {
        LogUtil.c("Debug_ThirdIdentiyManager", "insertThirdIdentiyData()");
        return this.f13401a.insert(iid.bzC_(hiThirdIdentity, true));
    }

    private Cursor bBp_(String str) {
        if (HiCommonUtil.b(str)) {
            return null;
        }
        return this.f13401a.query(c(), b(str), null, null, null);
    }

    public int a(HiThirdIdentity hiThirdIdentity) {
        return this.f13401a.update(iid.bzC_(hiThirdIdentity, false), c(), b(hiThirdIdentity.getPackageName()));
    }

    private String[] b(String str) {
        return new String[]{str};
    }

    private String c() {
        return "package_name =? ";
    }
}
