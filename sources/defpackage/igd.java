package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.auth.HiAuthorization;
import com.huawei.hihealthservice.auth.HiUserAuth;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* loaded from: classes7.dex */
public class igd {

    /* renamed from: a, reason: collision with root package name */
    private static final Context f13359a = BaseApplication.getContext();
    private iip e;

    private igd() {
        this.e = iip.b();
    }

    static class b {
        private static final igd e = new igd();
    }

    public static igd b() {
        return b.e;
    }

    void d(String str, HiAuthorization hiAuthorization, String str2) {
        String certFingerprint = hiAuthorization.getCertFingerprint();
        HiAppInfo b2 = ivw.b(f13359a, str2);
        b2.setCloudCode(CommonUtil.g(str));
        b2.setSignature(certFingerprint);
        this.e.c(b2);
    }

    void d(String str, List<igs> list, HiUserAuth hiUserAuth, ikv ikvVar) {
        int e = ijz.c().e(str, 0);
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        iko a2 = iko.a();
        for (igs igsVar : list) {
            iju.c().c(igsVar);
            int c = iju.c().c(igsVar.a());
            igr igrVar = new igr();
            igrVar.a(ikvVar.e());
            igrVar.b(e);
            int c2 = ioy.c(igsVar.j(), hiUserAuth);
            igrVar.d(c2);
            igrVar.d(igsVar);
            igrVar.c(c);
            arrayList.add(igrVar);
            if (c2 == 1) {
                arrayList2.add(igrVar);
            }
        }
        a2.b(ikvVar.e(), e, arrayList2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            iiq.d().e((igr) it.next());
        }
    }

    int a() throws RemoteException {
        return c(ivw.a(f13359a));
    }

    int c(String str) throws RemoteException {
        Integer num = ikn.bBq_().get(str);
        if (num != null) {
            return num.intValue();
        }
        int f = f(str);
        if (f <= 0) {
            LogUtil.h("HiH_HiHealthBinderHelper", "initAppId() app <= 0 packageName = ", str, ",app = ", Integer.valueOf(f));
            throw new RemoteException("initAppId() app <= 0 packageName = " + str + ",app = " + f);
        }
        ikn.bBq_().put(str, Integer.valueOf(f));
        return f;
    }

    ikv e() throws RemoteException {
        return a(ivw.a(f13359a));
    }

    ikv a(String str) throws RemoteException {
        if (b(str) == 0) {
            LogUtil.a("HiH_HiHealthBinderHelper", "getAppContext() isAppValid health or wear, packageName = ", str);
            return new ikv(c(str), str);
        }
        return new ikv(c(), str);
    }

    ikv c(String str, int i) {
        return new ikv(i, str);
    }

    int d() {
        return b(ivw.a(f13359a));
    }

    int b(String str) {
        if (ivw.a(f13359a, str)) {
            if (!HuaweiHealth.b().equals(str) && !"com.huawei.bone".equals(str)) {
                return 1;
            }
            LogUtil.c("HiH_HiHealthBinderHelper", "getAppType() isAppValid true needn't to Auth, packageName = ", str);
            return 0;
        }
        LogUtil.c("HiH_HiHealthBinderHelper", "getAppType() is thirdParty, packageName = ", str);
        return -1;
    }

    int c() throws RemoteException {
        return g(ivw.a(f13359a));
    }

    int e(String str) throws RemoteException {
        return g(str);
    }

    int d(String str) throws RemoteException {
        if (!Utils.i()) {
            return c(str);
        }
        return g(ivw.e(f13359a, str));
    }

    public boolean i() {
        return Utils.i();
    }

    boolean h() {
        return Utils.o();
    }

    private int g(String str) throws RemoteException {
        int c;
        Integer num = ikn.bBq_().get(str);
        if (num != null) {
            return num.intValue();
        }
        if (HuaweiHealth.b().equals(str)) {
            c = f(str);
        } else {
            c = igm.e().c(str);
        }
        LogUtil.c("HiH_HiHealthBinderHelper", "initCurrentAppId() app = ", Integer.valueOf(c), ", packageName = ", str);
        if (c <= 0) {
            LogUtil.h("HiH_HiHealthBinderHelper", "initCurrentAppId() app <= 0 packageName = ", str, ",app = ", Integer.valueOf(c));
            throw new RemoteException("initCurrentAppId() app <= 0 packageName = " + str + ",app = " + c);
        }
        ikn.bBq_().put(str, Integer.valueOf(c));
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int f(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "HiH_HiHealthBinderHelper"
            r1 = 2
            r2 = 1
            r3 = 0
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            boolean r4 = defpackage.ivu.i(r4, r3)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            if (r4 != 0) goto L18
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            boolean r4 = defpackage.ivu.e(r4, r3)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            goto L19
        L18:
            r4 = r3
        L19:
            iip r5 = r9.e     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            int r5 = r5.a(r10)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            if (r5 > 0) goto L39
            android.content.Context r5 = defpackage.igd.f13359a     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            com.huawei.hihealth.HiAppInfo r5 = defpackage.ivw.b(r5, r10)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.String r7 = "initBinder() app <= 0, appInfo = "
            r6[r3] = r7     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6[r2] = r5     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            iip r6 = r9.e     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            long r5 = r6.e(r5, r3)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            int r5 = (int) r5     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
        L39:
            r6 = 4
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.String r7 = "initBinder() app = "
            r6[r3] = r7     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6[r2] = r7     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.String r7 = ", packageName = "
            r6[r1] = r7     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r7 = 3
            r6[r7] = r10     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            com.huawei.hwlogsmodel.LogUtil.c(r0, r6)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            iio r6 = defpackage.iio.c()     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.String r6 = r6.a(r5)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            if (r6 != 0) goto L96
            com.huawei.hihealth.HiAccountInfo r6 = new com.huawei.hihealth.HiAccountInfo     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.<init>()     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.setAppId(r5)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.setHuid(r10)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.setLogin(r2)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            iio r7 = defpackage.iio.c()     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r7.b(r6)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            com.huawei.hihealth.HiUserInfo r6 = new com.huawei.hihealth.HiUserInfo     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.<init>()     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.setHuid(r10)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r6.setRelateType(r3)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r7 = 1
            r6.setCreateTime(r7)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            ijz r10 = defpackage.ijz.c()     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            long r6 = r10.c(r6, r3)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.Object[] r10 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.String r8 = "initBinder() who = "
            r10[r3] = r8     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            r10[r2] = r6     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            com.huawei.hwlogsmodel.LogUtil.c(r0, r10)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
        L96:
            if (r4 == 0) goto L9f
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
            defpackage.ivu.j(r10, r3)     // Catch: java.lang.Exception -> La9 java.lang.Throwable -> Lc9
        L9f:
            if (r4 == 0) goto La8
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r10, r3)
        La8:
            return r5
        La9:
            r10 = move-exception
            goto Lb0
        Lab:
            r10 = move-exception
            r4 = r3
            goto Lca
        Lae:
            r10 = move-exception
            r4 = r3
        Lb0:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r5 = "insertUserInfoSync: "
            r1[r3] = r5     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r10 = health.compact.a.LogAnonymous.b(r10)     // Catch: java.lang.Throwable -> Lc9
            r1[r2] = r10     // Catch: java.lang.Throwable -> Lc9
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r1)     // Catch: java.lang.Throwable -> Lc9
            if (r4 == 0) goto Lc8
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r10, r3)
        Lc8:
            return r3
        Lc9:
            r10 = move-exception
        Lca:
            if (r4 == 0) goto Ld3
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r0, r3)
        Ld3:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.igd.f(java.lang.String):int");
    }

    void c(final List<HiHealthData> list, final ikv ikvVar, final ism ismVar, ExecutorService executorService) {
        final long currentTimeMillis = System.currentTimeMillis();
        final int c = igm.e().c();
        if (executorService.isShutdown()) {
            LogUtil.b("HiH_HiHealthBinderHelper", "startInsertBackground backgroudThread is closed!");
        } else {
            executorService.execute(new Runnable() { // from class: igd.2
                @Override // java.lang.Runnable
                public void run() {
                    ismVar.a(c, list);
                    LogUtil.c("HiH_HiHealthBinderHelper", "startInsertBackground() hiContext = ", ikvVar, ", healthAppId is ", Integer.valueOf(c), ", time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            });
        }
    }

    int[] a(List<Integer> list) {
        int[] iArr = new int[list.size()];
        if (!HiCommonUtil.d(list)) {
            for (int i = 0; i < list.size(); i++) {
                iArr[i] = list.get(i).intValue();
            }
        }
        return iArr;
    }

    public boolean j() {
        return HuaweiHealth.b().equals(ivw.a(f13359a));
    }
}
