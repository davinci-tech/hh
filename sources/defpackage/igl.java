package defpackage;

import android.content.Context;
import android.util.SparseIntArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class igl {
    private static SparseIntArray e = new SparseIntArray(2);

    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(int r3, int r4, int r5) {
        /*
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            if (r1 != 0) goto L14
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            goto L15
        L14:
            r1 = r0
        L15:
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L4c
            b(r2, r3, r4, r5)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L4c
            if (r1 == 0) goto L25
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L4c
            defpackage.ivu.j(r3, r0)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L4c
        L25:
            if (r1 == 0) goto L4b
            goto L44
        L28:
            r3 = move-exception
            goto L2f
        L2a:
            r3 = move-exception
            r1 = r0
            goto L4d
        L2d:
            r3 = move-exception
            r1 = r0
        L2f:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L4c
            java.lang.String r5 = "moveTodaySportDataToLoginUser Exception"
            r4[r0] = r5     // Catch: java.lang.Throwable -> L4c
            java.lang.String r3 = health.compact.a.LogAnonymous.b(r3)     // Catch: java.lang.Throwable -> L4c
            r5 = 1
            r4[r5] = r3     // Catch: java.lang.Throwable -> L4c
            java.lang.String r3 = "HiH_HiAccountSwitch"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r3, r4)     // Catch: java.lang.Throwable -> L4c
            if (r1 == 0) goto L4b
        L44:
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r3, r0)
        L4b:
            return
        L4c:
            r3 = move-exception
        L4d:
            if (r1 == 0) goto L56
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L56:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.igl.c(int, int, int):void");
    }

    private static void b(Context context, int i, int i2, int i3) {
        long j;
        int i4;
        ikr ikrVar;
        int i5;
        LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser app = ", Integer.valueOf(i), ",oldUser = ", Integer.valueOf(i2), ",newUser = ", Integer.valueOf(i3));
        if (i2 == i3 || i3 <= 0 || i2 <= 0) {
            LogUtil.h("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser oldUser = newUser return");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long t = HiDateUtil.t(currentTimeMillis);
        long f = HiDateUtil.f(currentTimeMillis);
        iks e2 = iks.e();
        ikr b = ikr.b(context);
        int b2 = ijl.b(context, FoundationCommonUtil.getAndroidId(context));
        List<Integer> c = e2.c(i3, b2);
        int e3 = b.e(0, i3, b2);
        LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser newClients = ", c, ",newStatClient = ", Integer.valueOf(e3));
        if (c == null || c.isEmpty()) {
            j = currentTimeMillis;
            i4 = b2;
            ikrVar = b;
            i5 = 0;
        } else {
            j = currentTimeMillis;
            i5 = 0;
            LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser deletePoint = ", Integer.valueOf(iiy.e(context).b(t, f, HiHealthDataType.m(), c)));
            i4 = b2;
            ikrVar = b;
            LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser deleteSession = ", Integer.valueOf(ijc.d(context).c(t, f, 20001, 21000, c)));
        }
        LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser deleteStat = ", Integer.valueOf(ijd.c(context).d(t, f, 40001, 41000, e3)));
        LogUtil.a("HiH_HiAccountSwitch", "doMoveTodayActiveHourToLoginUser deleteStat = ", Integer.valueOf(ijd.c(context).d(t, f, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value(), DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value(), e3)));
        List<Integer> c2 = iks.e().c(i2, i4);
        LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser oldClients = ", c2);
        if (c2 != null && !c2.isEmpty()) {
            int e4 = ikrVar.e(i5, i2, i4);
            int e5 = ikrVar.e(i, i3, i4);
            LogUtil.c("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser oldStatClient = ", Integer.valueOf(e4), ",newClient = ", Integer.valueOf(e5), ",deviceID = ", Integer.valueOf(i4));
            ijk b3 = ijk.b();
            b3.d(t, f, c2, e5);
            b3.b(t, f, c2, e5);
            b3.a(t, f, i3, e4, e3);
            b3.a(t, f, c2, e5);
            b3.e(t, f, i3, e4, e3);
        }
        LogUtil.a("HiH_HiAccountSwitch", "doMoveTodaySportDataToLoginUser end totalTime = ", Long.valueOf(System.currentTimeMillis() - j));
    }

    public static int b(String str, String str2, boolean z) {
        return e(BaseApplication.e(), str, str2, z);
    }

    private static int e(Context context, String str, String str2, boolean z) {
        LogUtil.c("HiH_HiAccountSwitch", "copyHealthData huidA = ", str, ",huidB = ", str2);
        if (str == null || str2 == null || str.equals(str2)) {
            LogUtil.h("HiH_HiAccountSwitch", "copyHealthData A or B is null or A equals B");
            return 1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ijz c = ijz.c();
        int e2 = c.e(str, 0);
        if (e2 <= 0) {
            LogUtil.h("HiH_HiAccountSwitch", "copyHealthData userA is not exist");
            return 2;
        }
        int e3 = c.e(str2, 0);
        if (e3 <= 0) {
            HiUserInfo hiUserInfo = new HiUserInfo();
            hiUserInfo.setHuid(str2);
            hiUserInfo.setCreateTime(1L);
            e3 = (int) c.c(hiUserInfo, 0);
        }
        if (HuaweiHealth.b().equals(str)) {
            LogUtil.a("HiH_HiAccountSwitch", "copyHealthData move default user personal info to target userB!");
            a(context, e2, e3);
        }
        LogUtil.a("HiH_HiAccountSwitch", "copyHealthData userA = ", Integer.valueOf(e2), " userB = ", Integer.valueOf(e3));
        ikr b = ikr.b(context);
        int e4 = b.e(0, e2, 0);
        int e5 = b.e(0, e3, 0);
        LogUtil.a("HiH_HiAccountSwitch", "copyHealthData statClientA = ", Integer.valueOf(e4), ",statClientB = ", Integer.valueOf(e5));
        e eVar = new e();
        eVar.c(context);
        eVar.d(e3);
        eVar.a(z);
        a(eVar, e4, e5, 2000);
        List<Integer> a2 = iks.e().a(e2);
        LogUtil.a("HiH_HiAccountSwitch", "copyHealthData clientsA = ", a2);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("HiH_HiAccountSwitch", "copyHealthData clientsA is null");
            HiHealthManager.d(context).initHiHealth(null);
            return 3;
        }
        b(context, a2, e3, z);
        e.clear();
        LogUtil.a("HiH_HiAccountSwitch", "copyHealthData totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        HiHealthManager.d(context).initHiHealth(null);
        return 0;
    }

    private static void b(Context context, List<Integer> list, int i, boolean z) {
        e eVar = new e();
        eVar.c(context);
        eVar.a(z);
        eVar.d(i);
        e(eVar, 5, list, 2000, false);
        e(eVar, 1, list, 2000, false);
        e(eVar, 2, list, 2000, true);
        e(eVar, 3, list, 10, true);
        e(eVar, 4, list, 2000, true);
        e(eVar, 6, list, 2000, true);
        e(eVar, 7, list, 2000, false);
    }

    private static void a(Context context, int i, int i2) {
        ijz c = ijz.c();
        c.d(c.d(i), i2, 0);
        d(context, i, i2);
        e(context, i, i2);
    }

    private static void e(Context context, int i, int i2) {
        ijy c = ijy.c(context);
        List<HiUserPreference> e2 = c.e(i2);
        if (e2 != null && !e2.isEmpty()) {
            LogUtil.a("HiH_HiAccountSwitch", "copyUserPre userB has info, no copy");
            return;
        }
        List<HiUserPreference> e3 = c.e(i);
        if (e3 == null || e3.isEmpty()) {
            return;
        }
        HiBroadcastUtil.d(context);
        for (HiUserPreference hiUserPreference : e3) {
            hiUserPreference.setUserId(i2);
            hiUserPreference.setSyncStatus(0);
            c.b(hiUserPreference);
        }
    }

    private static void d(Context context, int i, int i2) {
        ijg d = ijg.d();
        List<HiGoalInfo> a2 = d.a(i);
        if (koq.b(a2)) {
            return;
        }
        for (HiGoalInfo hiGoalInfo : a2) {
            hiGoalInfo.setOwnerId(i2);
            d.a(hiGoalInfo, 0);
        }
    }

    private static void a(e eVar, int i, int i2, int i3) {
        Context e2 = eVar.e();
        int b = eVar.b();
        boolean a2 = eVar.a();
        LogUtil.a("HiH_HiAccountSwitch", "copyStatData statClientA = ", Integer.valueOf(i), " userB = ", Integer.valueOf(b), " statClientB = ", Integer.valueOf(i2), " count = ", Integer.valueOf(i3));
        long currentTimeMillis = System.currentTimeMillis();
        ijd c = ijd.c(e2);
        ijb b2 = ijb.b();
        isf a3 = isf.a(e2);
        int i4 = 0;
        while (true) {
            List<igo> c2 = c.c(i, 1, i4, i3);
            List<igo> c3 = b2.c(i, 1, i4, i3);
            if (c2 != null) {
                c2.addAll(c3);
            } else {
                c2 = c3;
            }
            e(c2, b, i2, a2);
            a3.transferHealthStatData(c2);
            if (c2 == null || c2.size() < i3) {
                break;
            } else {
                i4 += i3;
            }
        }
        LogUtil.h("HiH_HiAccountSwitch", "copyStatData dayStatTables is null anchor = ", Integer.valueOf(i4), " count = ", Integer.valueOf(i3));
        LogUtil.a("HiH_HiAccountSwitch", "copyStatData totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private static void e(e eVar, int i, List<Integer> list, int i2, boolean z) {
        Context e2 = eVar.e();
        int b = eVar.b();
        boolean a2 = eVar.a();
        LogUtil.a("HiH_HiAccountSwitch", "copyDataFromOnTable table = ", Integer.valueOf(i), ",clientsA = ", list, ",userB = ", Integer.valueOf(b), ",count = ", Integer.valueOf(i2), ",isRealTime = ", Boolean.valueOf(z));
        long currentTimeMillis = System.currentTimeMillis();
        isf a3 = isf.a(e2);
        int i3 = 0;
        while (true) {
            List<HiHealthData> d = d(e2, i, list, i3, i2);
            c(e2, d, b, a2);
            a3.transferHealthDetailData(d, b);
            if (z) {
                a3.prepareRealTimeHealthDataStat(d);
            }
            if (d == null || d.size() < i2) {
                break;
            } else {
                i3 += i2;
            }
        }
        LogUtil.h("HiH_HiAccountSwitch", "copyDataFromOnTable copy health data from A to B data is null table = ", Integer.valueOf(i), ",anchor = ", Integer.valueOf(i3), ",count = ", Integer.valueOf(i2));
        if (z) {
            a3.doRealTimeHealthDataStat();
        }
        ReleaseLogUtil.e("HiH_HiAccountSwitch", "copyDataFromOnTable table = ", Integer.valueOf(i), ",totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private static void c(Context context, List<HiHealthData> list, int i, boolean z) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiAccountSwitch", "setDataSource dataList is null or empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            int c = c(context, hiHealthData.getClientId(), i);
            if (c > 0) {
                hiHealthData.setClientId(c);
                hiHealthData.setUserId(i);
                if (!z) {
                    hiHealthData.setSyncStatus(0);
                }
            }
        }
    }

    private static void e(List<igo> list, int i, int i2, boolean z) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiAccountSwitch", "setStatSource tableList is null or empty");
            return;
        }
        for (igo igoVar : list) {
            igoVar.e(igoVar, i, i2, z);
        }
    }

    private static int c(Context context, int i, int i2) {
        int i3 = e.get(i);
        if (i3 > 0) {
            return i3;
        }
        ikv f = iis.d().f(i);
        if (f == null) {
            LogUtil.h("HiH_HiAccountSwitch", "getClientB hiHealthContext = null, clientA = ", Integer.valueOf(i));
            return 0;
        }
        int e2 = ikr.b(context).e(f.e(), i2, f.d());
        e.append(i, e2);
        LogUtil.c("HiH_HiAccountSwitch", "getClientB 2 clientA = ", Integer.valueOf(i), ", userB = ", Integer.valueOf(i2), ",clientB = ", Integer.valueOf(e2));
        return e2;
    }

    private static List<HiHealthData> d(Context context, int i, List<Integer> list, int i2, int i3) {
        switch (i) {
            case 1:
                return iiy.e(context).d(list, 1, i2, i3);
            case 2:
                return ijc.d(context).a(list, 1, i2, i3);
            case 3:
                return iiz.a(context).c(list, 1, i2, i3);
            case 4:
                return ijn.a(context).a(list, 1, i2, i3);
            case 5:
                List<HiHealthData> b = ijj.a(context).b(list, 1, i2, i3);
                b.addAll(iji.b().b(list, 1, i2, i3));
                return b;
            case 6:
                return iix.a(context).b(list, 1, i2, i3);
            case 7:
                return ijo.b(context).d(list, 1, i2, i3);
            default:
                return null;
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private Context f13367a;
        private boolean d;
        private int e;

        private e() {
        }

        public Context e() {
            return this.f13367a;
        }

        public void c(Context context) {
            this.f13367a = context;
        }

        public int b() {
            return this.e;
        }

        public void d(int i) {
            this.e = i;
        }

        public boolean a() {
            return this.d;
        }

        public void a(boolean z) {
            this.d = z;
        }
    }
}
