package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class iis {

    /* renamed from: a, reason: collision with root package name */
    private ikp f13387a;
    private ihc e;

    private iis() {
        this.e = ihc.a();
        this.f13387a = new ikp();
    }

    static class e {
        private static final iis c = new iis();
    }

    public static iis d() {
        return e.c;
    }

    public int h(int i) {
        Object b = this.f13387a.b(i);
        if (b != null) {
            return ((Integer) b).intValue();
        }
        return n(i);
    }

    public int b(int i, int i2, int i3) {
        return a(i, i2, i3);
    }

    public int d(ikv ikvVar) {
        return e(ikvVar);
    }

    public int a(int i) {
        return iih.bAH_(this.e.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null), "device_id");
    }

    public int d(int i) {
        return iih.bAH_(this.e.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null), "app_id");
    }

    public List<Integer> g(int i) {
        return iih.bAp_(this.e.query("user_id =? and device_id >?  and app_id >? ", e(i, 0, 0), null, null, null));
    }

    public List<Integer> a(int i, int i2) {
        return iih.bAp_(this.e.query("user_id =? and device_id =? and app_id >? ", e(i, i2, 0), null, null, null));
    }

    public List<Integer> e(int i, int i2) {
        return iih.bAp_(this.e.query("user_id =? and device_id >=? and app_id =? ", e(i, 0, i2), null, null, null));
    }

    public List<Integer> i(int i) {
        return iih.bAp_(this.e.query("user_id =? and device_id >?  and app_id >?  and sync_status =? and need_uploaddata =? ", new String[]{Integer.toString(i), Integer.toString(0), Integer.toString(0), Integer.toString(1), Integer.toString(1)}, null, null, null));
    }

    public List<Integer> e(int i) {
        return iih.bAp_(this.e.query("user_id =? and device_id >?  and app_id >?  and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(0), Integer.toString(0), Integer.toString(1)}, null, null, null));
    }

    public List<Integer> c(int i, int i2) {
        return iih.bAp_(this.e.query("user_id =? and app_id =? and device_id >?  and app_id >?  and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(i2), Integer.toString(0), Integer.toString(0), Integer.toString(1)}, null, null, null));
    }

    public List<Integer> c(int i) {
        return iih.bAJ_(this.e.query("user_id =? and device_id >?  and app_id >? ", new String[]{Integer.toString(i), Integer.toString(0), Integer.toString(0)}, null, null, null), "device_id");
    }

    public List<ikv> a(int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("user_id =? ");
        String[] strArr = new String[size + 1];
        strArr[0] = Integer.toString(i);
        iil.a("_id", list, stringBuffer, strArr, 1);
        return iih.bAq_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public long a(ikv ikvVar) {
        if (ikvVar == null) {
            return 0L;
        }
        return this.e.update(iid.bzE_(ikvVar), e(), e(ikvVar.g(), ikvVar.d(), ikvVar.e()));
    }

    public long b(ikv ikvVar) {
        if (ikvVar == null) {
            return 0L;
        }
        return this.e.update(iid.bzF_(ikvVar), e(), e(ikvVar.g(), ikvVar.d(), ikvVar.e()));
    }

    public List<ikv> b(int i, int i2) {
        return iih.bAq_(this.e.query("user_id =? and device_id >?  and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(0), Integer.toString(i2)}, null, null, null));
    }

    public ikv f(int i) {
        return iih.bAo_(this.e.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null));
    }

    public ikv d(int i, int i2, int i3) {
        return iih.bAo_(this.e.query(e(), e(i, i3, i2), null, null, null));
    }

    public int b(long j, int i) {
        return iih.bAH_(this.e.query("cloud_device =? and app_id =? ", new String[]{Long.toString(j), Integer.toString(i)}, null, null, null), "device_id");
    }

    public List<Integer> b(long j) {
        return iih.bAp_(this.e.query("cloud_device =? ", new String[]{Long.toString(j)}, null, null, null));
    }

    private long h(ikv ikvVar) {
        LogUtil.c("Debug_ClientManager", "insertClientData()");
        long insert = this.e.insert(iid.bzm_(ikvVar));
        LogUtil.c("Debug_ClientManager", "insertClientData() add  insert = ", Long.valueOf(insert));
        d(insert, ikvVar);
        return insert;
    }

    private void d(long j, ikv ikvVar) {
        if (j <= 0) {
            return;
        }
        LogUtil.c("Debug_ClientManager", "insertClientData() addCache  clientID = ", Long.valueOf(j), ", hiHealthContext = ", ikvVar);
        ikvVar.e((int) j);
        int g = ikvVar.g();
        int d = ikvVar.d();
        int e2 = ikvVar.e();
        iks e3 = iks.e();
        e3.d(g);
        e3.e(e2, g);
        e3.b(g, d);
        List<Integer> c = e3.c(ikvVar.g(), ikvVar.d());
        LogUtil.a("Debug_ClientManager", "insertClientData() add  insert = ", Long.valueOf(j), ", cache clients = ", HiJsonUtil.e(c));
        HiDeviceInfo a2 = ijf.e().a(d);
        if (a2 != null) {
            LogUtil.a("Debug_ClientManager", "add cache by key is deviceUuid ,clients = ", HiJsonUtil.e(c));
            e3.a(g, a2.getDeviceUniqueCode(), c);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int n(int r5) {
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
            int r5 = r4.o(r5)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
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
            java.lang.String r3 = "getStatClient "
            r2[r0] = r3     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L50
            r3 = 1
            r2[r3] = r5     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "HiH_ClientManager"
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iis.n(int):int");
    }

    private int o(int i) {
        int bAH_ = iih.bAH_(this.e.query(e(), e(i, 0, 0), null, null, null), "_id");
        if (bAH_ <= 0) {
            ikv ikvVar = new ikv(i, 0, 0);
            ikvVar.i(0);
            ikvVar.b(1);
            bAH_ = (int) h(ikvVar);
        }
        LogUtil.c("Debug_ClientManager", "getStatClientByUser userClient = ", Integer.valueOf(bAH_));
        this.f13387a.a(i, Integer.valueOf(bAH_));
        return bAH_;
    }

    private boolean m(int i) {
        List<Integer> e2 = iip.b().e();
        if (e2 == null) {
            LogUtil.c("Debug_ClientManager", "isKitAppId kitAppIds = null");
            return false;
        }
        LogUtil.c("Debug_ClientManager", "isKitAppId appId = ", Integer.valueOf(i), " kitAppIds = ", e2.toString());
        Iterator<Integer> it = e2.iterator();
        while (it.hasNext()) {
            if (i == it.next().intValue()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(int r9, int r10, int r11) {
        /*
            r8 = this;
            java.lang.Integer r0 = java.lang.Integer.valueOf(r10)
            java.lang.String r1 = ",userID = "
            java.lang.Integer r2 = java.lang.Integer.valueOf(r9)
            java.lang.String r3 = "getStatClientByUser deviceID = "
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0, r1, r2}
            java.lang.String r1 = "Debug_ClientManager"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            ihc r2 = r8.e
            java.lang.String r3 = r8.e()
            java.lang.String[] r4 = r8.e(r9, r10, r11)
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)
            java.lang.String r1 = "_id"
            int r0 = defpackage.iih.bAH_(r0, r1)
            if (r0 <= 0) goto L2f
            return r0
        L2f:
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
            if (r1 != 0) goto L43
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
            goto L44
        L43:
            r1 = r0
        L44:
            int r9 = r8.c(r9, r10, r11)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L7f
            if (r1 == 0) goto L51
            android.content.Context r10 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L7f
            defpackage.ivu.j(r10, r0)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L7f
        L51:
            if (r1 == 0) goto L5a
            android.content.Context r10 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r10, r0)
        L5a:
            return r9
        L5b:
            r9 = move-exception
            goto L62
        L5d:
            r9 = move-exception
            r1 = r0
            goto L80
        L60:
            r9 = move-exception
            r1 = r0
        L62:
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> L7f
            java.lang.String r11 = "getClient: "
            r10[r0] = r11     // Catch: java.lang.Throwable -> L7f
            java.lang.String r9 = health.compact.a.LogAnonymous.b(r9)     // Catch: java.lang.Throwable -> L7f
            r11 = 1
            r10[r11] = r9     // Catch: java.lang.Throwable -> L7f
            java.lang.String r9 = "HiH_ClientManager"
            com.huawei.hihealth.util.ReleaseLogUtil.d(r9, r10)     // Catch: java.lang.Throwable -> L7f
            if (r1 == 0) goto L7e
            android.content.Context r9 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r9, r0)
        L7e:
            return r0
        L7f:
            r9 = move-exception
        L80:
            if (r1 == 0) goto L89
            android.content.Context r10 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r10, r0)
        L89:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iis.a(int, int, int):int");
    }

    private int c(int i, int i2, int i3) {
        int bAH_ = iih.bAH_(this.e.query(e(), e(i, i2, i3), null, null, null), "_id");
        if (bAH_ <= 0) {
            ikv ikvVar = new ikv(i, i2, i3);
            if (m(i3)) {
                ikvVar.i(4);
                ikvVar.b(0);
            } else {
                ikvVar.i(0);
                ikvVar.b(1);
            }
            bAH_ = (int) h(ikvVar);
        }
        LogUtil.c("Debug_ClientManager", "getStatClientByDevice client = ", Integer.valueOf(bAH_));
        return bAH_;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int e(defpackage.ikv r7) {
        /*
            r6 = this;
            ihc r0 = r6.e
            java.lang.String r1 = r6.e()
            int r2 = r7.g()
            int r3 = r7.d()
            int r4 = r7.e()
            java.lang.String[] r2 = r6.e(r2, r3, r4)
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)
            java.lang.String r1 = "_id"
            int r0 = defpackage.iih.bAH_(r0, r1)
            if (r0 <= 0) goto L26
            return r0
        L26:
            r0 = 1
            r1 = 2
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            java.lang.String r4 = "getClientByAllSync hiHealthContext = "
            r3[r2] = r4     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            r3[r0] = r7     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            java.lang.String r4 = "Debug_ClientManager"
            com.huawei.hwlogsmodel.LogUtil.c(r4, r3)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            boolean r3 = defpackage.ivu.i(r3, r2)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            if (r3 != 0) goto L49
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            boolean r3 = defpackage.ivu.e(r3, r2)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            goto L4a
        L49:
            r3 = r2
        L4a:
            int r7 = r6.c(r7)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L83
            if (r3 == 0) goto L57
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L83
            defpackage.ivu.j(r4, r2)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> L83
        L57:
            if (r3 == 0) goto L60
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r2)
        L60:
            return r7
        L61:
            r7 = move-exception
            goto L68
        L63:
            r7 = move-exception
            r3 = r2
            goto L84
        L66:
            r7 = move-exception
            r3 = r2
        L68:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L83
            java.lang.String r4 = "getSyncClient: "
            r1[r2] = r4     // Catch: java.lang.Throwable -> L83
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L83
            r1[r0] = r7     // Catch: java.lang.Throwable -> L83
            java.lang.String r7 = "HiH_ClientManager"
            com.huawei.hihealth.util.ReleaseLogUtil.d(r7, r1)     // Catch: java.lang.Throwable -> L83
            if (r3 == 0) goto L82
            android.content.Context r7 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r7, r2)
        L82:
            return r2
        L83:
            r7 = move-exception
        L84:
            if (r3 == 0) goto L8d
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r2)
        L8d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iis.e(ikv):int");
    }

    private int c(ikv ikvVar) {
        int bAH_ = iih.bAH_(this.e.query(e(), e(ikvVar.g(), ikvVar.d(), ikvVar.e()), null, null, null), "_id");
        if (bAH_ <= 0) {
            ikvVar.b(1);
            bAH_ = (int) h(ikvVar);
        }
        LogUtil.c("Debug_ClientManager", "getClientByAllSync client = ", Integer.valueOf(bAH_));
        return bAH_;
    }

    private String[] e(int i, int i2, int i3) {
        return new String[]{Integer.toString(i), Integer.toString(i2), Integer.toString(i3)};
    }

    public void d(int i, int i2) {
        c(i, c(i, i2));
    }

    private void c(int i, List<Integer> list) {
        if (list == null) {
            LogUtil.b("Debug_ClientManager", "clientid list is null");
            return;
        }
        new ContentValues().put("need_uploaddata", (Integer) 0);
        Hashtable<Integer, List<Integer>> g = ism.g();
        if (g != null) {
            List<Integer> list2 = g.get(Integer.valueOf(i));
            if (!HiCommonUtil.d(list2)) {
                list.removeAll(list2);
            }
            ism.e(i);
        }
        for (Integer num : list) {
            if (0 >= this.e.update(r1, "_id =? ", new String[]{Integer.toString(num.intValue())})) {
                LogUtil.a("Debug_ClientManager", "updateClientNeedFlag false clientid=", num);
            }
        }
    }

    public int j(int i) {
        return iih.bAH_(this.e.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null), "user_id");
    }

    public List<ikv> b(int i) {
        return iih.bAq_(this.e.query("user_id =? and device_id >? ", new String[]{Integer.toString(i), Integer.toString(0)}, null, null, null));
    }

    public long l(int i) {
        new ContentValues().put("need_uploaddata", (Integer) 1);
        return this.e.update(r0, "_id =? ", new String[]{Integer.toString(i)});
    }

    public void c() {
        this.f13387a.a();
    }

    private String e() {
        return "user_id =? and device_id =? and app_id =? ";
    }
}
