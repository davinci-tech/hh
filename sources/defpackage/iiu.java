package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class iiu {
    private igz d;

    private iiu() {
        this.d = igz.d();
    }

    /* loaded from: classes7.dex */
    static class c {
        private static final iiu d = new iiu();
    }

    public static iiu c() {
        return c.d;
    }

    public static String[] e() {
        return new String[]{"_id", "date", "hihealth_type", "stat_type", "value", "user_id", DBPointCommon.COLUMN_UNIT_ID, "client_id", "timeZone", "sync_status", "modified_time"};
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table  IF NOT EXISTS " + str + Constants.LEFT_BRACKET_ONLY);
        sb.append("_id integer primary key not null,date integer not null,hihealth_type integer not null,stat_type integer not null,value double not null,user_id integer,unit_id integer not null,client_id integer,timeZone text not null,sync_status integer not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE INDEX IF NOT EXISTS ConfigStatIndex ON " + str + Constants.LEFT_BRACKET_ONLY);
        sb.append("date,stat_type,client_id)");
        return sb.toString();
    }

    private long a(igo igoVar) {
        return this.d.insert(iid.bzy_(igoVar));
    }

    public igo e(String str, int i, int i2, int i3) {
        return iih.bAL_(this.d.bxu_(str, a(), d(i, i2, i3), null, null, null));
    }

    public List<HiHealthData> b(String str, HiDataReadOption hiDataReadOption, int i, int i2) {
        String a2 = iil.a("date", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        return iih.bAW_(this.d.bxu_(str, d(), new String[]{Integer.toString(HiDateUtil.c(hiDataReadOption.getStartTime())), Integer.toString(HiDateUtil.c(hiDataReadOption.getEndTime())), Integer.toString(i), Integer.toString(i2)}, null, null, a2));
    }

    private long e(String str, igo igoVar) {
        if (igoVar.e() == HiDateUtil.c(System.currentTimeMillis())) {
            ReleaseLogUtil.e("HiH_ConfigDataStatManager", "updateStatData today stat need to upload.");
            LogUtil.a("Debug_ConfigDataStatManager", "updateStatData today stat need to upload, statTable is ", igoVar);
            igoVar.g(0);
        }
        ContentValues bzR_ = iid.bzR_(igoVar);
        if (igoVar.g() == 1) {
            bzR_.remove("sync_status");
        }
        return this.d.bxv_(str, bzR_, a(), d(igoVar.e(), igoVar.f(), igoVar.d()));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(java.lang.String r4, defpackage.igo r5) {
        /*
            r3 = this;
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
            boolean r4 = r3.c(r4, r5)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            if (r1 == 0) goto L22
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            defpackage.ivu.j(r5, r0)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
        L22:
            if (r1 == 0) goto L2b
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r5, r0)
        L2b:
            return r4
        L2c:
            r4 = move-exception
            goto L33
        L2e:
            r4 = move-exception
            r1 = r0
            goto L51
        L31:
            r4 = move-exception
            r1 = r0
        L33:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = "insertOrUpdateStatData exception: "
            r5[r0] = r2     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = health.compact.a.LogAnonymous.b(r4)     // Catch: java.lang.Throwable -> L50
            r2 = 1
            r5[r2] = r4     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = "HiH_ConfigDataStatManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r4, r5)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L4f
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L4f:
            return r0
        L50:
            r4 = move-exception
        L51:
            if (r1 == 0) goto L5a
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r5, r0)
        L5a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iiu.d(java.lang.String, igo):boolean");
    }

    private boolean c(String str, igo igoVar) {
        boolean z = HiDateUtil.c(System.currentTimeMillis()) == igoVar.e();
        if (igoVar.c() <= 0) {
            igoVar.a(System.currentTimeMillis());
        }
        double l = igoVar.l();
        igo e = e(str, igoVar.e(), igoVar.f(), igoVar.d());
        if (e != null) {
            if (z) {
                LogUtil.a("HiH_ConfigDataStatManager", "insertStatData() new date =", Integer.valueOf(igoVar.e()), ", type=", Integer.valueOf(igoVar.f()), ",", Double.valueOf(igoVar.l()), ",old=", Double.valueOf(e.l()));
            }
            if (ijp.d(e, igoVar)) {
                return iil.a(e(str, igoVar));
            }
            return false;
        }
        if (l <= 0.0d) {
            LogUtil.h("Debug_ConfigDataStatManager", "insertStatData() newStat value <= 0 ");
            return false;
        }
        if (z) {
            LogUtil.a("HiH_ConfigDataStatManager", "insertStatData() new date =", Integer.valueOf(igoVar.e()), ", type=", Integer.valueOf(igoVar.f()), ",", Double.valueOf(igoVar.l()), ",old=null");
        }
        return iil.a(a(igoVar));
    }

    private String[] d(int i, int i2, int i3) {
        return new String[]{Integer.toString(i), Long.toString(i2), Integer.toString(i3)};
    }

    private String a() {
        return "date =? and stat_type =? and client_id =? ";
    }

    private String d() {
        return "date >=? and date <=? and stat_type =? and client_id =? ";
    }
}
