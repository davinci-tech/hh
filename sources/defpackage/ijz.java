package defpackage;

import android.content.ContentValues;
import android.util.LruCache;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.utils.Constants;
import health.compact.a.HiCommonUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class ijz {

    /* renamed from: a, reason: collision with root package name */
    private LruCache<String, Integer> f13404a;
    private iia c;

    private ijz() {
        this.c = iia.b();
        this.f13404a = new LruCache<>(10);
    }

    static class b {
        private static final ijz c = new ijz();
    }

    public static ijz c() {
        return b.c;
    }

    public long c(HiUserInfo hiUserInfo, int i) {
        return b(hiUserInfo, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long b(com.huawei.hihealth.HiUserInfo r7, int r8) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r2 = "HiH_userInfoManager"
            if (r7 != 0) goto L10
            java.lang.String r7 = "insertUserInfo() userInfo is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.c(r2, r7)
            return r0
        L10:
            java.lang.String r3 = r7.getHuid()
            int r4 = r7.getRelateType()
            int r3 = r6.e(r3, r4)
            if (r3 <= 0) goto L20
            long r7 = (long) r3
            return r7
        L20:
            r3 = 0
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            boolean r4 = defpackage.ivu.i(r4, r3)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            if (r4 != 0) goto L34
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            boolean r4 = defpackage.ivu.e(r4, r3)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            goto L35
        L34:
            r4 = r3
        L35:
            long r7 = r6.d(r7, r8)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L6e
            if (r4 == 0) goto L42
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L6e
            defpackage.ivu.j(r5, r3)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L6e
        L42:
            if (r4 == 0) goto L4b
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r0, r3)
        L4b:
            return r7
        L4c:
            r7 = move-exception
            goto L53
        L4e:
            r7 = move-exception
            r4 = r3
            goto L6f
        L51:
            r7 = move-exception
            r4 = r3
        L53:
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L6e
            java.lang.String r5 = "insertUserInfoSync: "
            r8[r3] = r5     // Catch: java.lang.Throwable -> L6e
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L6e
            r5 = 1
            r8[r5] = r7     // Catch: java.lang.Throwable -> L6e
            com.huawei.hihealth.util.ReleaseLogUtil.d(r2, r8)     // Catch: java.lang.Throwable -> L6e
            if (r4 == 0) goto L6d
            android.content.Context r7 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r7, r3)
        L6d:
            return r0
        L6e:
            r7 = move-exception
        L6f:
            if (r4 == 0) goto L78
            android.content.Context r8 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r8, r3)
        L78:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijz.b(com.huawei.hihealth.HiUserInfo, int):long");
    }

    private long d(HiUserInfo hiUserInfo, int i) {
        LogUtil.c("HiH_userInfoManager", "insertUserInfo()");
        int e = e(hiUserInfo.getHuid(), hiUserInfo.getRelateType());
        if (e > 0) {
            return e;
        }
        return this.c.insert(iid.bzT_(hiUserInfo, i));
    }

    public int b(HiUserInfo hiUserInfo, int i, int i2) {
        return d(hiUserInfo, i, i2, true);
    }

    public int e(HiUserInfo hiUserInfo, int i) {
        return d(hiUserInfo, i, 0, false);
    }

    private int d(HiUserInfo hiUserInfo, int i, int i2, boolean z) {
        ContentValues bzS_;
        LogUtil.c("HiH_userInfoManager", Constants.METHOD_UPDATE_USER_INFO);
        if (hiUserInfo == null || i <= 0) {
            LogUtil.h("HiH_userInfoManager", "updateUserInfo values is null");
            return 0;
        }
        if (hiUserInfo.getCreateTime() < c(i)) {
            LogUtil.a("HiH_userInfoManager", "updateUserInfo do not need update userInfo , createTime is not new");
            return 0;
        }
        if (z) {
            bzS_ = iid.bzT_(hiUserInfo, i2);
        } else {
            bzS_ = iid.bzS_(hiUserInfo);
        }
        return this.c.update(bzS_, "_id =? ", new String[]{Integer.toString(i)});
    }

    public int d(HiUserInfo hiUserInfo, int i, int i2) {
        LogUtil.c("HiH_userInfoManager", Constants.METHOD_UPDATE_USER_INFO);
        if (hiUserInfo == null || i <= 0) {
            LogUtil.h("HiH_userInfoManager", "updateUserInfo values is null");
            return 0;
        }
        if (hiUserInfo.getCreateTime() < c(i)) {
            LogUtil.a("HiH_userInfoManager", "updateUserInfo do not need update userInfo , createTime is not new");
            return 0;
        }
        Object[] objArr = new Object[6];
        objArr[0] = "userInfo cloud sync:download height:";
        objArr[1] = Integer.valueOf(hiUserInfo.getHeight());
        objArr[2] = " weight:";
        objArr[3] = Float.valueOf(hiUserInfo.getWeight());
        objArr[4] = " UnitType:";
        objArr[5] = hiUserInfo.getUnitType() == 0 ? "UNIT_CM_KG" : "UNIT_FT_LB";
        LogUtil.c("HiH_userInfoManager", objArr);
        hiUserInfo.setModifiedIntent(i2 == 1 ? HiUserInfo.DATA_CLOUD : 536870912);
        ContentValues bzT_ = iid.bzT_(hiUserInfo, i2);
        bzT_.remove("huid");
        bzT_.remove("relate_type");
        return this.c.update(bzT_, "_id =? ", new String[]{Integer.toString(i)});
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.huawei.hihealth.HiUserInfo r3, java.lang.String r4, int r5) {
        /*
            r2 = this;
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
            boolean r3 = r2.c(r3, r4, r5)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            if (r1 == 0) goto L22
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            defpackage.ivu.j(r4, r0)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
        L22:
            if (r1 == 0) goto L2b
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L2b:
            return r3
        L2c:
            r3 = move-exception
            goto L33
        L2e:
            r3 = move-exception
            r1 = r0
            goto L51
        L31:
            r3 = move-exception
            r1 = r0
        L33:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "insertOrUpdateUserInfo: "
            r4[r0] = r5     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = health.compact.a.LogAnonymous.b(r3)     // Catch: java.lang.Throwable -> L50
            r5 = 1
            r4[r5] = r3     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = "HiH_userInfoManager"
            com.huawei.hihealth.util.ReleaseLogUtil.d(r3, r4)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L4f
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r3, r0)
        L4f:
            return r0
        L50:
            r3 = move-exception
        L51:
            if (r1 == 0) goto L5a
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L5a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijz.a(com.huawei.hihealth.HiUserInfo, java.lang.String, int):boolean");
    }

    private boolean c(HiUserInfo hiUserInfo, String str, int i) {
        long b2;
        LogUtil.c("HiH_userInfoManager", "insertOrUpdateUserInfoDb");
        if (hiUserInfo == null || HiCommonUtil.b(str)) {
            LogUtil.h("HiH_userInfoManager", "insertOrUpdateUserInfo values is null");
            return false;
        }
        int e = e(str, 0);
        if (e > 0) {
            b2 = b(hiUserInfo, e, i);
        } else {
            b2 = b(hiUserInfo, i);
        }
        return iil.a(b2);
    }

    public HiUserInfo a(String str, int i) {
        LogUtil.c("HiH_userInfoManager", "queryUserInfo");
        if (HiCommonUtil.b(str)) {
            LogUtil.h("HiH_userInfoManager", "queryUserInfo() huid = null");
            return null;
        }
        return iih.bBb_(this.c.query("huid =? and relate_type =?", new String[]{str, String.valueOf(i)}, null, null, null));
    }

    public List<HiUserInfo> e(String str) {
        LogUtil.c("HiH_userInfoManager", "queryUserInfo");
        if (HiCommonUtil.b(str)) {
            LogUtil.h("HiH_userInfoManager", "queryUserInfo() huid = null");
            return null;
        }
        return iih.bBc_(this.c.query("huid =? ", new String[]{str}, null, null, null));
    }

    public HiUserInfo d(int i) {
        if (i <= 0) {
            LogUtil.h("HiH_userInfoManager", "queryUserInfo id <=0");
            return null;
        }
        return iih.bBb_(this.c.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null));
    }

    public HiUserInfo c(int i, int i2) {
        if (i <= 0) {
            LogUtil.h("HiH_userInfoManager", "queryUserInfo id <=0");
            return null;
        }
        return iih.bBb_(this.c.query("_id =? and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null));
    }

    public String b(int i) {
        LogUtil.c("HiH_userInfoManager", "queryHuidById");
        if (i <= 0) {
            LogUtil.h("HiH_userInfoManager", "queryHuidById id <=0");
            return null;
        }
        return iih.bAM_(this.c.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null), "huid");
    }

    public long c(int i) {
        if (i <= 0) {
            return 0L;
        }
        return iih.bAK_(this.c.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null), "create_time");
    }

    public int e(String str, int i) {
        String str2;
        LogUtil.c("HiH_userInfoManager", "queryUserInfoForUserId");
        if (HiCommonUtil.b(str)) {
            LogUtil.h("HiH_userInfoManager", "queryUserInfoForUserId() huid = null");
            return 0;
        }
        if (i == 0) {
            str2 = str;
        } else {
            str2 = str + com.huawei.openalliance.ad.constant.Constants.LINK + i;
        }
        Integer num = this.f13404a.get(str2);
        if (num != null) {
            return num.intValue();
        }
        int bAH_ = iih.bAH_(this.c.query("huid =? and relate_type =?", new String[]{str, Integer.toString(i)}, null, null, null), "_id");
        if (bAH_ > 0) {
            this.f13404a.put(str2, Integer.valueOf(bAH_));
        }
        return bAH_;
    }
}
