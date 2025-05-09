package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.function.ToIntFunction;

/* loaded from: classes7.dex */
public class ijr {
    private ihu d;

    private ijr() {
        this.d = ihu.a();
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final ijr f13398a = new ijr();
    }

    public static ijr d() {
        return e.f13398a;
    }

    public boolean b(int i, long j, int i2) {
        return iih.bAv_(this.d.query(c(), d(i, j, i2), null, null, null));
    }

    public igq c(int i, long j, int i2) {
        return iih.bAZ_(this.d.query(c(), d(i, j, i2), null, null, null));
    }

    public List<igq> b(int i, long j, List<Integer> list) {
        int[] iArr = new int[list.size()];
        int[] array = list.stream().mapToInt(new ToIntFunction() { // from class: ijv
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int intValue;
                intValue = ((Integer) obj).intValue();
                return intValue;
            }
        }).toArray();
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[list.size() + 2];
        stringBuffer.append("main_user_id =? and cloud_code =? ");
        strArr[0] = Long.toString(i);
        strArr[1] = Long.toString(j);
        iil.a("sync_data_type", array, stringBuffer, strArr, 2);
        return iih.bAx_(this.d.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public igq e(int i, long j, int i2) {
        return iih.bBa_(this.d.query(c(), d(i, j, i2), null, null, null));
    }

    private long a(igq igqVar) {
        LogUtil.c("Debug_SyncAnchorManager", "insertSyncAnchor()");
        return this.d.insert(iid.bzA_(igqVar));
    }

    private int c(igq igqVar) {
        int update = this.d.update(iid.bzA_(igqVar), c(), d(igqVar.c(), igqVar.b(), igqVar.e()));
        LogUtil.c("Debug_SyncAnchorManager", "updateClientData() updateResult  updateResult = ", Integer.valueOf(update));
        return update;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(defpackage.igq r5) {
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
            boolean r5 = r4.e(r5)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
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
            java.lang.String r3 = "insertOrUpdateSyncAnchor: "
            r2[r0] = r3     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L50
            r3 = 1
            r2[r3] = r5     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "HiH_SyncAnchorManager"
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijr.b(igq):boolean");
    }

    private boolean e(igq igqVar) {
        long a2;
        LogUtil.c("Debug_SyncAnchorManager", "saveVersionToDB syncAnchorTable is", igqVar);
        if (b(igqVar.c(), igqVar.b(), igqVar.e())) {
            a2 = c(igqVar);
        } else {
            LogUtil.c("Debug_SyncAnchorManager", "!!saveVersionToDB syncAnchorTable is", igqVar);
            a2 = a(igqVar);
        }
        return iil.a(a2);
    }

    public boolean d(int i, int i2, long j, long j2) {
        igq igqVar = new igq();
        igqVar.c(j);
        igqVar.b(i);
        igqVar.a(i2);
        igqVar.d(j2);
        LogUtil.c("Debug_SyncAnchorManager", "saveVersionToDB syncAnchorTable is", igqVar);
        return b(igqVar);
    }

    public boolean a(int i, int i2, int i3, long j) {
        igq igqVar = new igq();
        igqVar.c(i3);
        igqVar.b(i);
        igqVar.a(i2);
        igqVar.d(j);
        LogUtil.c("Debug_SyncAnchorManager", "saveSyncTimeToDB syncAnchorTable is ", igqVar);
        return b(igqVar);
    }

    private String[] d(int i, long j, int i2) {
        return new String[]{Integer.toString(i), Long.toString(j), Integer.toString(i2)};
    }

    private String c() {
        return "main_user_id =? and cloud_code =? and sync_data_type =? ";
    }
}
