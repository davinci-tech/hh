package defpackage;

import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class ijx {
    private ihv e;

    private ijx() {
        this.e = ihv.d();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final ijx f13403a = new ijx();
    }

    public static ijx e() {
        return a.f13403a;
    }

    private long e(igt igtVar) {
        if (igtVar == null) {
            LogUtil.h("Debug_SyncCacheManager", "insertSyncCache syncCacheTable is null!");
            return 0L;
        }
        return this.e.insert(iid.bzB_(igtVar));
    }

    public boolean d(int i, int i2, String str, long j) {
        igt igtVar = new igt();
        igtVar.e(i);
        igtVar.a(i2);
        igtVar.c(str);
        igtVar.d(j);
        long e = e(igtVar);
        LogUtil.c("Debug_SyncCacheManager", "insert syncCacheTable count is", Long.valueOf(e));
        return e > 0;
    }

    public Cursor bBo_(int i, int i2) {
        LogUtil.c("Debug_SyncCacheManager", "querySyncCachebyType");
        if (i < 0) {
            LogUtil.h("Debug_SyncCacheManager", "querySyncCachebyType userID is not right!");
            return null;
        }
        return this.e.query("userId =? and isDone =? and dataType =? ", new String[]{Integer.toString(i), Integer.toString(0), Integer.toString(i2)}, null, null, "dataTime DESC ");
    }
}
