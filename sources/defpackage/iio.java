package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class iio {
    private igv d;

    private iio() {
        this.d = igv.e();
    }

    static class c {
        private static final iio c = new iio();
    }

    public static iio c() {
        return c.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(com.huawei.hihealth.HiAccountInfo r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Debug_AccountInfoManager"
            r1 = 1
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            java.lang.String r4 = "insertOrUpdateAccountInfo"
            r3[r2] = r4     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            boolean r3 = defpackage.ivu.i(r3, r2)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            if (r3 != 0) goto L20
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            boolean r3 = defpackage.ivu.e(r3, r2)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            goto L21
        L20:
            r3 = r2
        L21:
            boolean r7 = r6.e(r7)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L59
            if (r3 == 0) goto L2e
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L59
            defpackage.ivu.j(r4, r2)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L59
        L2e:
            if (r3 == 0) goto L37
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r2)
        L37:
            return r7
        L38:
            r7 = move-exception
            goto L3f
        L3a:
            r7 = move-exception
            r3 = r2
            goto L5a
        L3d:
            r7 = move-exception
            r3 = r2
        L3f:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L59
            java.lang.String r5 = "insertOrUpdateAccountInfo: "
            r4[r2] = r5     // Catch: java.lang.Throwable -> L59
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L59
            r4[r1] = r7     // Catch: java.lang.Throwable -> L59
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r4)     // Catch: java.lang.Throwable -> L59
            if (r3 == 0) goto L58
            android.content.Context r7 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r7, r2)
        L58:
            return r2
        L59:
            r7 = move-exception
        L5a:
            if (r3 == 0) goto L63
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r2)
        L63:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iio.b(com.huawei.hihealth.HiAccountInfo):boolean");
    }

    private boolean e(HiAccountInfo hiAccountInfo) {
        long c2;
        if (e(hiAccountInfo.getAppId(), hiAccountInfo.getHuid())) {
            c2 = a(hiAccountInfo);
        } else {
            c2 = c(hiAccountInfo);
        }
        return iil.a(c2);
    }

    public boolean e(int i, String str) {
        LogUtil.c("Debug_AccountInfoManager", "queryAccountInfoExist");
        if (HiCommonUtil.b(str) || i <= 0) {
            LogUtil.h("Debug_AccountInfoManager", "queryAccountInfoExist parameter is wrong");
            return false;
        }
        return iih.bAv_(bBh_(i, str));
    }

    private Cursor bBh_(int i, String str) {
        LogUtil.c("Debug_AccountInfoManager", "queryAccountInfo");
        if (HiCommonUtil.b(str) || i <= 0) {
            LogUtil.h("Debug_AccountInfoManager", "queryAccountInfo parameter is wrong!");
            return null;
        }
        return this.d.query("app_id =? and huid =?", new String[]{String.valueOf(i), str}, null, null, null);
    }

    private int a(HiAccountInfo hiAccountInfo) {
        if (hiAccountInfo == null) {
            ReleaseLogUtil.d("Debug_AccountInfoManager", "updateAccountInfo accountInfo is null!");
            return 0;
        }
        String huid = hiAccountInfo.getHuid();
        if (HiCommonUtil.b(huid) || !huid.matches("[a-zA-Z0-9._]+")) {
            ReleaseLogUtil.d("Debug_AccountInfoManager", "updateAccountInfo huid invalid");
            return 0;
        }
        ReleaseLogUtil.e("Debug_AccountInfoManager", "updateAccountInfo to loginStatus: ", Integer.valueOf(hiAccountInfo.getIsLogin()));
        int appId = hiAccountInfo.getAppId();
        ContentValues bzh_ = iid.bzh_(hiAccountInfo);
        bzh_.remove("create_time");
        return this.d.update(bzh_, "app_id =? and huid =?", new String[]{String.valueOf(appId), hiAccountInfo.getHuid()});
    }

    public long c(HiAccountInfo hiAccountInfo) {
        if (hiAccountInfo == null) {
            ReleaseLogUtil.d("Debug_AccountInfoManager", "insertAccountInfo accountInfo is null!");
            return 0L;
        }
        ReleaseLogUtil.e("Debug_AccountInfoManager", "insertAccountInfo insert new accountInfo to loginStatus: ", Integer.valueOf(hiAccountInfo.getIsLogin()));
        return this.d.insert(iid.bzh_(hiAccountInfo));
    }

    public HiAccountInfo d(int i) {
        LogUtil.c("Debug_AccountInfoManager", "getAccountInfoByAppId");
        if (i < 0) {
            LogUtil.h("Debug_AccountInfoManager", "getAccountInfoByAppId appId is not right!");
            return null;
        }
        return iih.bzX_(this.d.query("app_id =? and is_login =? ", new String[]{Integer.toString(i), Integer.toString(1)}, null, null, null));
    }

    public HiAccountInfo b(int i, String str) {
        return iih.bzX_(bBh_(i, str));
    }

    public String a(int i) {
        LogUtil.c("Debug_AccountInfoManager", "getHuidByAppId");
        if (i <= 0) {
            LogUtil.h("Debug_AccountInfoManager", "getHuidByAppId appId is not right!");
            return null;
        }
        return iih.bAM_(this.d.query("app_id =? and is_login =? ", new String[]{Integer.toString(i), Integer.toString(1)}, null, null, null), "huid");
    }

    public int b(int i) {
        ReleaseLogUtil.e("Debug_AccountInfoManager", "logoutByAppId appId = ", Integer.valueOf(i));
        HiAccountInfo d = d(i);
        if (d == null) {
            ReleaseLogUtil.d("Debug_AccountInfoManager", "logoutByAppId the account is not exist");
            return -1;
        }
        d.setLogin(0);
        return a(d);
    }
}
