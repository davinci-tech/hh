package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class iku {
    private static Context c;
    private ikm d;

    private iku() {
        this.d = new ikm();
    }

    static class b {
        private static final iku b = new iku();
    }

    public static iku a(Context context) {
        if (context != null) {
            c = context.getApplicationContext();
        }
        return b.b;
    }

    public ikv a(int i, int i2, long j) throws iut {
        return c(i, i2, j, false);
    }

    public ikv e(int i, int i2, long j, boolean z) throws iut {
        return c(i, i2, j, z);
    }

    public ikv e(int i, int i2, int i3) {
        ikv ikvVar = new ikv();
        ikvVar.a(i);
        ikvVar.d(i3);
        ikvVar.i(0);
        ikvVar.f(i2);
        ikvVar.e(iis.d().d(ikvVar));
        return ikvVar;
    }

    private HiAppInfo a(long j) throws iut {
        DeviceInfo c2;
        String l = Long.toString(j);
        HiAppInfo c3 = ikn.c(l);
        if (c3 != null || (c2 = iuz.c(c, j)) == null) {
            return c3;
        }
        HiAppInfo hiAppInfo = new HiAppInfo();
        String packageName = c2.getPackageName();
        String thirdAppInfo = c2.getThirdAppInfo();
        hiAppInfo.setPackageName(packageName);
        hiAppInfo.setAppName(thirdAppInfo);
        ikn.b(l, hiAppInfo);
        return hiAppInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private defpackage.ikv c(int r14, int r15, long r16, boolean r18) throws defpackage.iut {
        /*
            r13 = this;
            r8 = r13
            boolean r0 = r13.c(r14, r15, r16)
            r1 = 0
            java.lang.String r9 = "Debug_SynClientCache"
            if (r0 != 0) goto L14
            java.lang.String r0 = "syncHiHealthContext checkInput  = false"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r9, r0)
            return r1
        L14:
            java.lang.String r0 = java.lang.Integer.toString(r14)
            java.lang.String r2 = java.lang.Integer.toString(r15)
            java.lang.String r3 = java.lang.Long.toString(r16)
            java.lang.String r7 = defpackage.ikq.e(r0, r2, r3)
            ikm r0 = r8.d
            ikv r10 = r0.e(r7)
            if (r10 == 0) goto L2d
            return r10
        L2d:
            ikm r0 = r8.d
            boolean r0 = r0.a(r7)
            if (r0 == 0) goto L36
            return r1
        L36:
            r11 = 0
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            boolean r0 = defpackage.ivu.i(r0, r11)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            if (r0 != 0) goto L4b
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            boolean r0 = defpackage.ivu.e(r0, r11)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            r12 = r0
            goto L4c
        L4b:
            r12 = r11
        L4c:
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r6 = r18
            ikv r10 = r1.a(r2, r3, r4, r6, r7)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            if (r12 == 0) goto L60
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            defpackage.ivu.j(r0, r11)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
        L60:
            if (r12 == 0) goto L69
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r11)
        L69:
            return r10
        L6a:
            r0 = move-exception
            goto L71
        L6c:
            r0 = move-exception
            r12 = r11
            goto L8d
        L6f:
            r0 = move-exception
            r12 = r11
        L71:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L8c
            java.lang.String r2 = "getSyncContext exception: "
            r1[r11] = r2     // Catch: java.lang.Throwable -> L8c
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L8c
            r2 = 1
            r1[r2] = r0     // Catch: java.lang.Throwable -> L8c
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r9, r1)     // Catch: java.lang.Throwable -> L8c
            if (r12 == 0) goto L8b
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r11)
        L8b:
            return r10
        L8c:
            r0 = move-exception
        L8d:
            if (r12 == 0) goto L96
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r1, r11)
        L96:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iku.c(int, int, long, boolean):ikv");
    }

    private ikv a(int i, int i2, long j, boolean z, String str) throws iut {
        int e;
        HiAppInfo a2 = z ? a(j) : null;
        if (a2 != null && !TextUtils.isEmpty(a2.getPackageName())) {
            e = a(a2.getPackageName(), a2.getAppName());
        } else {
            e = e(i, j);
        }
        int i3 = e;
        if (i3 <= 0) {
            this.d.c(str);
            LogUtil.h("Debug_SynClientCache", "syncHiHealthContext appID <= 0 key =  ", str);
            return null;
        }
        int d = d(i3, j);
        if (d <= 0) {
            this.d.c(str);
            ReleaseLogUtil.d("Debug_SynClientCache", "syncHiHealthContext deviceID is ", Integer.valueOf(d), " key ", str);
            return null;
        }
        ikv b2 = b(i3, i2, d, j);
        if (b2.b() <= 0) {
            LogUtil.h("Debug_SynClientCache", "syncHiHealthContext clientID <= 0  key = ", str);
            return null;
        }
        this.d.c(str, b2);
        iks.e().b(i2, d);
        return b2;
    }

    private int e(int i, long j) throws iut {
        int a2 = iip.b().a(ivw.d(i));
        if (a2 > 0) {
            return a2;
        }
        HiAppInfo a3 = a(j);
        if (a3 == null) {
            return 0;
        }
        return (int) iip.b().e(a3, 0);
    }

    private int a(String str, String str2) {
        int a2 = iip.b().a(str);
        if (a2 > 0) {
            return a2;
        }
        HiAppInfo hiAppInfo = new HiAppInfo();
        hiAppInfo.setPackageName(str);
        if (!TextUtils.isEmpty(str2)) {
            hiAppInfo.setAppName(str2);
        } else {
            hiAppInfo.setAppName(ivw.a(str));
        }
        return (int) iip.b().e(hiAppInfo, 0);
    }

    private int d(int i, long j) throws iut {
        int b2 = iis.d().b(j, i);
        if (b2 > 0 || !Utils.i()) {
            return b2;
        }
        HiDeviceInfo b3 = iuz.b(c, j);
        LogUtil.c("Debug_SynClientCache", "syncOneDeviceByVersion hiDeviceInfo is", b3);
        if (b3 == null) {
            return b2;
        }
        ijf.d(c).e(b3);
        return ijl.b(c, b3.getDeviceUniqueCode());
    }

    private ikv b(int i, int i2, int i3, long j) {
        ikv ikvVar = new ikv();
        ikvVar.a(i);
        ikvVar.c(j);
        ikvVar.d(i3);
        ikvVar.i(1);
        ikvVar.f(i2);
        ikvVar.e(iis.d().d(ikvVar));
        return ikvVar;
    }

    private boolean c(int i, int i2, long j) {
        if (i >= 0 && i2 > 0 && j > 0) {
            return true;
        }
        LogUtil.h("Debug_SynClientCache", "checkInput no such input appType is ", Integer.valueOf(i), " deviceCode is ", Long.valueOf(j));
        return false;
    }

    public void e() {
        this.d.b();
        ikn.e();
    }
}
