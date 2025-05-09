package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.networkclient.cache.ICacheStrategy;
import com.huawei.networkclient.repository.DataConverterRepository;
import health.compact.a.ReleaseLogUtil;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public final class eac {

    /* renamed from: a, reason: collision with root package name */
    private final DataConverterRepository f11923a;
    private lqs d;

    public static boolean e(int i) {
        return i == 1;
    }

    private eac() {
        DataConverterRepository dataConverterRepository = new DataConverterRepository();
        this.f11923a = dataConverterRepository;
        dataConverterRepository.e(String.class, new lre());
        this.d = new lqs("knit_data_cache", dataConverterRepository);
    }

    public static eac e() {
        return a.d;
    }

    public boolean e(String str, String str2) {
        return a(str, new c(), str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r5, com.huawei.networkclient.cache.ICacheStrategy r6, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.String r0 = "R_KnitDataCacheMgr"
            if (r5 == 0) goto L27
            if (r7 == 0) goto L27
            lqs r1 = r4.d     // Catch: java.lang.Exception -> L19
            com.huawei.networkclient.repository.DataConverterRepository r2 = r4.f11923a     // Catch: java.lang.Exception -> L19
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            com.huawei.networkclient.repository.DataConverterRepository$DataConverter r2 = r2.a(r3)     // Catch: java.lang.Exception -> L19
            java.lang.String r7 = r2.revert(r7)     // Catch: java.lang.Exception -> L19
            boolean r6 = r1.e(r5, r6, r7)     // Catch: java.lang.Exception -> L19
            goto L28
        L19:
            r6 = move-exception
            java.lang.String r7 = "saveDiskCache failed with exception:"
            java.lang.String r6 = com.huawei.haf.common.exception.ExceptionUtils.d(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r5, r6}
            health.compact.a.ReleaseLogUtil.a(r0, r6)
        L27:
            r6 = 0
        L28:
            if (r6 == 0) goto L34
            java.lang.String r7 = "saveDiskCache success. key="
            java.lang.Object[] r5 = new java.lang.Object[]{r7, r5}
            health.compact.a.ReleaseLogUtil.b(r0, r5)
            goto L3d
        L34:
            java.lang.String r7 = "saveDiskCache failed. key="
            java.lang.Object[] r5 = new java.lang.Object[]{r7, r5}
            health.compact.a.ReleaseLogUtil.a(r0, r5)
        L3d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eac.a(java.lang.String, com.huawei.networkclient.cache.ICacheStrategy, java.lang.String):boolean");
    }

    public String b(String str) {
        return e(str, new c());
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0051 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String e(java.lang.String r6, com.huawei.networkclient.cache.ICacheStrategy r7) {
        /*
            r5 = this;
            java.lang.String r0 = "R_KnitDataCacheMgr"
            if (r6 == 0) goto L20
            if (r7 == 0) goto L20
            lqs r1 = r5.d     // Catch: java.lang.Exception -> L11
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            java.lang.Object r7 = r1.e(r6, r2, r7)     // Catch: java.lang.Exception -> L11
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Exception -> L11
            goto L2c
        L11:
            r7 = move-exception
            java.lang.String r1 = "getDiskCache failed with exception:"
            java.lang.String r7 = com.huawei.haf.common.exception.ExceptionUtils.d(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r1, r6, r7}
            health.compact.a.ReleaseLogUtil.a(r0, r7)
            goto L2b
        L20:
            java.lang.String r1 = "getDiskCache failed key: "
            java.lang.String r2 = " cacheStrategy:"
            java.lang.Object[] r7 = new java.lang.Object[]{r1, r6, r2, r7}
            health.compact.a.ReleaseLogUtil.a(r0, r7)
        L2b:
            r7 = 0
        L2c:
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "getDiskCache key: "
            r3 = 0
            r1[r3] = r2
            r2 = 1
            r1[r2] = r6
            r6 = 2
            java.lang.String r4 = " result:"
            r1[r6] = r4
            if (r7 == 0) goto L45
            boolean r6 = r7.isEmpty()
            if (r6 != 0) goto L45
            r3 = r2
        L45:
            r6 = 3
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r3)
            r1[r6] = r2
            health.compact.a.ReleaseLogUtil.a(r0, r1)
            if (r7 != 0) goto L53
            java.lang.String r7 = ""
        L53:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eac.e(java.lang.String, com.huawei.networkclient.cache.ICacheStrategy):java.lang.String");
    }

    public static String d(List<Integer> list) {
        if (!CollectionUtils.d(list)) {
            return jdq.e(BaseApplication.e(), nrv.a(list));
        }
        return jdq.e(BaseApplication.e(), "emptyKey");
    }

    public static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return jdq.e(BaseApplication.e(), str);
        }
        return jdq.e(BaseApplication.e(), "emptyKey");
    }

    public void c() {
        ReleaseLogUtil.b("R_KnitDataCacheMgr", "clearCache.");
        this.d.a();
        this.d = new lqs("knit_data_cache", this.f11923a);
    }

    static class c implements ICacheStrategy {
        @Override // com.huawei.networkclient.cache.ICacheStrategy
        public int getCacheStrategy() {
            return 7;
        }

        @Override // com.huawei.networkclient.cache.ICacheStrategy
        public String getKey() {
            return null;
        }

        @Override // com.huawei.networkclient.cache.ICacheStrategy
        public boolean needEncrypt() {
            return false;
        }

        private c() {
        }

        @Override // com.huawei.networkclient.cache.ICacheStrategy
        public Long getDiskTimeOut() {
            return 30L;
        }

        @Override // com.huawei.networkclient.cache.ICacheStrategy
        public TimeUnit getDiskTimeUnit() {
            return TimeUnit.DAYS;
        }
    }

    static class a {
        private static final eac d = new eac();
    }
}
