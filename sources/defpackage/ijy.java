package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ijy {
    private ihx e;

    private ijy() {
        this.e = ihx.e();
    }

    static class d {
        private static final ijy c = new ijy();
    }

    public static ijy c(Context context) {
        return d.c;
    }

    public HiUserPreference a(int i, String str) {
        return iih.bBe_(this.e.query(a(), c(i, str), null, null, null));
    }

    public List<HiUserPreference> d(int i, int i2) {
        return iih.bBf_(this.e.query(c(), c(i, i2), null, null, null));
    }

    public List<HiUserPreference> e(int i) {
        return iih.bBf_(this.e.query(e(), b(i), null, null, null));
    }

    private long c(HiUserPreference hiUserPreference) {
        LogUtil.c("Debug_UserPreferenceManager", "insertUserPreference()");
        return this.e.insert(iid.bzV_(hiUserPreference));
    }

    private int a(HiUserPreference hiUserPreference) {
        int update = this.e.update(iid.bzU_(hiUserPreference), a(), c(hiUserPreference.getUserId(), hiUserPreference.getKey()));
        LogUtil.c("Debug_UserPreferenceManager", "updateUserPreference() updateResult  updateResult = ", Integer.valueOf(update));
        return update;
    }

    public int a(HiUserPreference hiUserPreference, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        int update = this.e.update(contentValues, a(), c(hiUserPreference.getUserId(), hiUserPreference.getKey()));
        LogUtil.c("Debug_UserPreferenceManager", "updateUserPreference() update  update = ", Integer.valueOf(update));
        return update;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(com.huawei.hihealth.HiUserPreference r5) {
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
            java.lang.String r3 = "insertOrUpdateUserPreference: "
            r2[r0] = r3     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L50
            r3 = 1
            r2[r3] = r5     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "Debug_UserPreferenceManager"
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijy.b(com.huawei.hihealth.HiUserPreference):boolean");
    }

    private boolean d(HiUserPreference hiUserPreference) {
        long a2;
        LogUtil.c("Debug_UserPreferenceManager", "insertOrUpdateUserPreference userPreference is", hiUserPreference);
        if (HiCommonUtil.b(hiUserPreference.getKey()) || hiUserPreference.getValue() == null) {
            LogUtil.h("Debug_UserPreferenceManager", "insertOrUpdateUserPreference key empty or value null");
            return false;
        }
        if (a(hiUserPreference.getUserId(), hiUserPreference.getKey()) == null) {
            a2 = c(hiUserPreference);
        } else {
            a2 = a(hiUserPreference);
        }
        return iil.a(a2);
    }

    private String[] c(int i, String str) {
        return new String[]{Integer.toString(i), str};
    }

    private String[] c(int i, int i2) {
        return new String[]{Integer.toString(i), Integer.toString(i2)};
    }

    private String[] b(int i) {
        return new String[]{Integer.toString(i)};
    }

    private String c() {
        return "user_id =? and sync_status =? ";
    }

    private String a() {
        return "user_id =? and key =? ";
    }

    private String e() {
        return "user_id =? ";
    }
}
