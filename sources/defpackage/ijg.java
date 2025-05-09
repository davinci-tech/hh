package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.List;

/* loaded from: classes7.dex */
public class ijg {

    /* renamed from: a, reason: collision with root package name */
    private ihb f13394a;

    private ijg() {
        this.f13394a = ihb.c();
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final ijg f13395a = new ijg();
    }

    public static ijg d() {
        return b.f13395a;
    }

    private Cursor bBn_(int i, int i2) {
        LogUtil.c("Debug_GoalInfoManager", "queryGoalInfoCursor");
        if (i <= 0) {
            LogUtil.h("Debug_GoalInfoManager", "no such userId");
            return null;
        }
        return this.f13394a.query("user_id =? and goal_type=?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
    }

    private Cursor bBm_(int i) {
        LogUtil.c("Debug_GoalInfoManager", "queryGoalInfoCursor");
        if (i <= 0) {
            LogUtil.h("Debug_GoalInfoManager", "no such userId");
            return null;
        }
        return this.f13394a.query("user_id =?", new String[]{Integer.toString(i)}, null, null, null);
    }

    public List<HiGoalInfo> c(int i, int i2) {
        LogUtil.c("Debug_GoalInfoManager", "queryGoalInfoCursor");
        Cursor bBn_ = bBn_(i, i2);
        if (bBn_ == null) {
            LogUtil.h("Debug_GoalInfoManager", "no such userId");
            return null;
        }
        return iih.bAu_(bBn_);
    }

    public List<HiGoalInfo> a(int i) {
        LogUtil.c("Debug_GoalInfoManager", "queryGoalInfoCursor");
        Cursor bBm_ = bBm_(i);
        if (bBm_ == null) {
            LogUtil.h("Debug_GoalInfoManager", "no such userId");
            return null;
        }
        return iih.bAu_(bBm_);
    }

    public List<HiGoalInfo> b(int i, int i2) {
        LogUtil.c("Debug_GoalInfoManager", "queryGoalInfoCursor");
        if (i <= 0) {
            LogUtil.h("Debug_GoalInfoManager", "no such userId");
            return null;
        }
        return iih.bAu_(this.f13394a.query("user_id =? and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null));
    }

    public boolean e(int i, int i2) {
        LogUtil.c("Debug_GoalInfoManager", "queryGoalInfoExist");
        return iih.bAv_(bBn_(i, i2));
    }

    private int j(HiGoalInfo hiGoalInfo, int i) {
        LogUtil.c("Debug_GoalInfoManager", "updateGoalInfo");
        if (hiGoalInfo == null) {
            LogUtil.h("Debug_GoalInfoManager", "updateGoalInfo goalInfo is null");
            return 0;
        }
        int ownerId = hiGoalInfo.getOwnerId();
        return this.f13394a.update(iid.bzo_(hiGoalInfo, i), "user_id =? and goal_type = ?", new String[]{Integer.toString(ownerId), Integer.toString(hiGoalInfo.getGoalType())});
    }

    public int c(int i, int i2, int i3) {
        LogUtil.c("Debug_GoalInfoManager", "updateSyncGoalInfo");
        if (i <= 0) {
            LogUtil.h("Debug_GoalInfoManager", "updateSyncGoalInfo who is ", Integer.valueOf(i));
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i3));
        return this.f13394a.update(contentValues, "user_id =? and goal_type = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
    }

    private long b(HiGoalInfo hiGoalInfo, int i) {
        LogUtil.c("Debug_GoalInfoManager", "insertGoalInfo");
        if (hiGoalInfo == null) {
            LogUtil.h("Debug_GoalInfoManager", "insertGoalInfo goalInfo is null");
            return 0L;
        }
        return this.f13394a.insert(iid.bzo_(hiGoalInfo, i));
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(com.huawei.hihealth.HiGoalInfo r5, int r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Debug_GoalInfoManager"
            r1 = 0
            if (r5 != 0) goto Lf
            java.lang.String r5 = "insertOrUpdateGoalInfo goalInfo is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
            return r1
        Lf:
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            boolean r2 = defpackage.ivu.i(r2, r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            if (r2 != 0) goto L22
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            boolean r2 = defpackage.ivu.e(r2, r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            goto L23
        L22:
            r2 = r1
        L23:
            boolean r5 = r4.g(r5, r6)     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L5c
            if (r2 == 0) goto L30
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L5c
            defpackage.ivu.j(r6, r1)     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L5c
        L30:
            if (r2 == 0) goto L39
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r6, r1)
        L39:
            return r5
        L3a:
            r5 = move-exception
            goto L41
        L3c:
            r5 = move-exception
            r2 = r1
            goto L5d
        L3f:
            r5 = move-exception
            r2 = r1
        L41:
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = "insertOrUpdateGoalInfo: "
            r6[r1] = r3     // Catch: java.lang.Throwable -> L5c
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L5c
            r3 = 1
            r6[r3] = r5     // Catch: java.lang.Throwable -> L5c
            com.huawei.hihealth.util.ReleaseLogUtil.d(r0, r6)     // Catch: java.lang.Throwable -> L5c
            if (r2 == 0) goto L5b
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r5, r1)
        L5b:
            return r1
        L5c:
            r5 = move-exception
        L5d:
            if (r2 == 0) goto L66
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r6, r1)
        L66:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijg.d(com.huawei.hihealth.HiGoalInfo, int):boolean");
    }

    private boolean g(HiGoalInfo hiGoalInfo, int i) {
        long b2;
        LogUtil.c("Debug_GoalInfoManager", "insertOrUpdateGoalInfo");
        if (e(hiGoalInfo.getOwnerId(), hiGoalInfo.getGoalType())) {
            b2 = j(hiGoalInfo, i);
        } else {
            b2 = b(hiGoalInfo, i);
        }
        return iil.a(b2);
    }

    public boolean a(HiGoalInfo hiGoalInfo, int i) {
        return e(hiGoalInfo, i);
    }

    private boolean e(HiGoalInfo hiGoalInfo, int i) {
        boolean z;
        if (hiGoalInfo == null) {
            LogUtil.h("Debug_GoalInfoManager", "insertDefaultAccountGoals goalInfo is null");
            return false;
        }
        boolean e = e(hiGoalInfo.getOwnerId(), hiGoalInfo.getGoalType());
        try {
            if (e) {
                LogUtil.a("Debug_GoalInfoManager", "insertDefaultAccountGoals goalInfo exist, do not need move");
                return true;
            }
            try {
                z = !ivu.i(BaseApplication.e(), 0) ? ivu.e(BaseApplication.e(), 0) : false;
                try {
                    boolean c = c(hiGoalInfo, i);
                    if (z) {
                        ivu.j(BaseApplication.e(), 0);
                    }
                    if (z) {
                        ivu.c(BaseApplication.e(), 0);
                    }
                    return c;
                } catch (Exception e2) {
                    e = e2;
                    ReleaseLogUtil.d("Debug_GoalInfoManager", "insertDefaultAccountGoalsSync: ", LogAnonymous.b((Throwable) e));
                    if (z) {
                        ivu.c(BaseApplication.e(), 0);
                    }
                    return false;
                }
            } catch (Exception e3) {
                e = e3;
                z = false;
            } catch (Throwable th) {
                th = th;
                e = false;
                if (e) {
                    ivu.c(BaseApplication.e(), 0);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean c(HiGoalInfo hiGoalInfo, int i) {
        if (e(hiGoalInfo.getOwnerId(), hiGoalInfo.getGoalType())) {
            LogUtil.a("Debug_GoalInfoManager", "insertDefaultAccountGoals goalInfo exist, do not need move");
            return true;
        }
        LogUtil.a("Debug_GoalInfoManager", "insertDefaultAccountGoals start move goals");
        return iil.a(b(hiGoalInfo, i));
    }
}
