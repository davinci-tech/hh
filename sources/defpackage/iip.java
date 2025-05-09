package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HuaweiHealth;
import java.util.List;

/* loaded from: classes4.dex */
public class iip {

    /* renamed from: a, reason: collision with root package name */
    private int f13386a;
    private igu b;

    private iip() {
        this.f13386a = 0;
        this.b = igu.b();
    }

    static class a {
        private static final iip d = new iip();
    }

    public static iip b() {
        return a.d;
    }

    public long e(HiAppInfo hiAppInfo, int i) {
        LogUtil.c("Debug_AppInfoManager", "insertAppInfoData()");
        return this.b.insert(iid.bzi_(hiAppInfo, i));
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long c(com.huawei.hihealth.HiAppInfo r6, int r7) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto L5
            return r0
        L5:
            r2 = 0
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            boolean r3 = defpackage.ivu.i(r3, r2)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            if (r3 != 0) goto L19
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            boolean r3 = defpackage.ivu.e(r3, r2)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            goto L1a
        L19:
            r3 = r2
        L1a:
            java.lang.String r4 = r6.getPackageName()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L6a
            int r4 = r5.a(r4)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L6a
            if (r4 <= 0) goto L2f
            long r6 = (long) r4
            if (r3 == 0) goto L2e
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r2)
        L2e:
            return r6
        L2f:
            long r6 = r5.e(r6, r7)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L6a
            if (r3 == 0) goto L3c
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L6a
            defpackage.ivu.j(r4, r2)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L6a
        L3c:
            if (r3 == 0) goto L45
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r2)
        L45:
            return r6
        L46:
            r6 = move-exception
            goto L4d
        L48:
            r6 = move-exception
            r3 = r2
            goto L6b
        L4b:
            r6 = move-exception
            r3 = r2
        L4d:
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = "insertAppInfoDataIfNoExist: "
            r7[r2] = r4     // Catch: java.lang.Throwable -> L6a
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L6a
            r4 = 1
            r7[r4] = r6     // Catch: java.lang.Throwable -> L6a
            java.lang.String r6 = "HiH_AppInfoManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r6, r7)     // Catch: java.lang.Throwable -> L6a
            if (r3 == 0) goto L69
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r6, r2)
        L69:
            return r0
        L6a:
            r6 = move-exception
        L6b:
            if (r3 == 0) goto L74
            android.content.Context r7 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r7, r2)
        L74:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iip.c(com.huawei.hihealth.HiAppInfo, int):long");
    }

    public HiAppInfo b(String str) {
        if (HiCommonUtil.b(str)) {
            LogUtil.h("Debug_AppInfoManager", "queryAppInfoData packageName is null or empty");
            return null;
        }
        return iih.bAk_(this.b.query(f(), c(str), null, null, null));
    }

    public HiAppInfo c(int i) {
        return iih.bAk_(this.b.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null));
    }

    public List<HiAppInfo> c() {
        return iih.bAj_(this.b.query(null, null, null, null, null));
    }

    public int c(HiAppInfo hiAppInfo) {
        return this.b.update(iid.bzi_(hiAppInfo, 0), f(), c(hiAppInfo.getPackageName()));
    }

    public int a(String str) {
        if (HuaweiHealth.b().equals(str)) {
            if (this.f13386a <= 0) {
                this.f13386a = d(str);
            }
            return this.f13386a;
        }
        return d(str);
    }

    private int d(String str) {
        LogUtil.c("Debug_AppInfoManager", "getAppIdFromDb() packageName is ", str, " current is ", BaseApplication.d());
        if (HiCommonUtil.b(str)) {
            LogUtil.h("Debug_AppInfoManager", "getAppIdFromDb() packageName is null or empty!");
            return 0;
        }
        return iih.bAH_(this.b.query(f(), c(str), null, null, null), "_id");
    }

    public List<Integer> e() {
        return iih.bAJ_(this.b.query(a(), d(), null, null, null), "_id");
    }

    private String[] d() {
        return new String[]{"com.huawei.health", "com.huawei.bone", "com.huawei.health.ecg.collection", "com.huawei.ah100", "com.huawei.health.device", com.huawei.hwcommonmodel.application.BaseApplication.APP_PACKAGE_GOOGLE_HEALTH, "com.huawei.ohos.health.device", "hw.health.ecganalysis", "com.huawei.health.h5.sleep-apnea"};
    }

    private String[] c(String str) {
        return new String[]{str};
    }

    private String f() {
        return "package_name =? ";
    }

    private String a() {
        return "package_name !=? and package_name !=? and package_name !=? and package_name !=? and package_name !=? and package_name !=? and package_name !=? and package_name !=? and package_name !=? ";
    }
}
