package defpackage;

import android.content.Context;
import android.util.ArrayMap;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.auth.HiUserAuth;
import com.huawei.hihealthservice.store.interfaces.IUserData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class igm implements IUserData {

    /* renamed from: a, reason: collision with root package name */
    private String f13368a;
    private iio d;
    String e;
    private ijz h;
    private static final Context c = BaseApplication.getContext();
    private static Map<Map<Integer, String>, HiAccountInfo> b = new ConcurrentHashMap(16);

    private igm() {
        this.f13368a = "";
        this.e = "";
        this.h = ijz.c();
        this.d = iio.c();
        this.f13368a = a();
    }

    public static igm e() {
        return b.e;
    }

    static class b {
        private static final igm e = new igm();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0063  */
    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long setUserData(com.huawei.hihealth.HiUserInfo r8, defpackage.ikv r9) {
        /*
            r7 = this;
            java.lang.String r0 = "saveUserData, user:"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HiH_HiUserDataStore"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r2 = 0
            if (r8 != 0) goto L19
            java.lang.String r8 = "saveUserData userInfo is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r8)
            return r2
        L19:
            r0 = 0
            android.content.Context r4 = defpackage.igm.c     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            boolean r5 = defpackage.ivu.i(r4, r0)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            if (r5 != 0) goto L27
            boolean r5 = defpackage.ivu.e(r4, r0)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            goto L28
        L27:
            r5 = r0
        L28:
            long r8 = r7.a(r8, r9)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L60
            if (r5 == 0) goto L31
            defpackage.ivu.j(r4, r0)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L60
        L31:
            if (r5 == 0) goto L36
            defpackage.ivu.c(r4, r0)
        L36:
            return r8
        L37:
            r8 = move-exception
            goto L3e
        L39:
            r8 = move-exception
            r5 = r0
            goto L61
        L3c:
            r8 = move-exception
            r5 = r0
        L3e:
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L60
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60
            java.lang.String r6 = "Error to insertBatchList,cause:"
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L60
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L60
            r4.append(r8)     // Catch: java.lang.Throwable -> L60
            java.lang.String r8 = r4.toString()     // Catch: java.lang.Throwable -> L60
            r9[r0] = r8     // Catch: java.lang.Throwable -> L60
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r9)     // Catch: java.lang.Throwable -> L60
            if (r5 == 0) goto L5f
            android.content.Context r8 = defpackage.igm.c
            defpackage.ivu.c(r8, r0)
        L5f:
            return r2
        L60:
            r8 = move-exception
        L61:
            if (r5 == 0) goto L68
            android.content.Context r9 = defpackage.igm.c
            defpackage.ivu.c(r9, r0)
        L68:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.igm.setUserData(com.huawei.hihealth.HiUserInfo, ikv):long");
    }

    private long a(HiUserInfo hiUserInfo, ikv ikvVar) {
        int e = ikvVar.e();
        String huid = hiUserInfo.getHuid();
        if (huid == null) {
            huid = this.d.a(e);
        }
        if (huid == null) {
            ReleaseLogUtil.d("HiH_HiUserDataStore", "saveUserData huid is null");
            return 0L;
        }
        int e2 = this.h.e(huid, 0);
        if (e2 <= 0) {
            ReleaseLogUtil.d("HiH_HiUserDataStore", "saveUserData the main userInfo is not right");
            return 0L;
        }
        hiUserInfo.setHuid(huid);
        if (hiUserInfo.getRelateType() <= 0) {
            hiUserInfo.setRelateType(0);
            if (hiUserInfo.getModifiedIntent() == 268435456 || hiUserInfo.getCreateTime() == 1) {
                LogUtil.c("HiH_HiUserDataStore", "saveUserData before:", Integer.valueOf(hiUserInfo.getBirthday()), " ", Integer.valueOf(hiUserInfo.getGender()), " ", Integer.valueOf(hiUserInfo.getHeight()), " ", Float.valueOf(hiUserInfo.getWeight()), " ", Integer.valueOf(hiUserInfo.getUnitType()));
                int e3 = this.h.e(hiUserInfo, e2);
                HiUserInfo a2 = this.h.a(huid, 0);
                if (a2 != null) {
                    LogUtil.c("HiH_HiUserDataStore", "saveUserData after:", Integer.valueOf(a2.getBirthday()), " ", Integer.valueOf(a2.getGender()), " ", Integer.valueOf(a2.getHeight()), " ", Float.valueOf(a2.getWeight()), " ", Integer.valueOf(a2.getUnitType()));
                }
                return e3;
            }
            return e(hiUserInfo, huid, e2, e);
        }
        return a(hiUserInfo, e2, huid, ikvVar.f());
    }

    private long e(HiUserInfo hiUserInfo, String str, int i, int i2) {
        int b2 = this.h.b(hiUserInfo, i, 0);
        if (b2 > 0) {
            LogUtil.c("HiH_HiUserDataStore", "updateUserInfo saveUserData before:", Integer.valueOf(hiUserInfo.getBirthday()), " ", Integer.valueOf(hiUserInfo.getGender()), " ", Integer.valueOf(hiUserInfo.getHeight()), " ", Float.valueOf(hiUserInfo.getWeight()), " ", Integer.valueOf(hiUserInfo.getUnitType()));
            HiUserInfo a2 = this.h.a(str, 0);
            if (a2 != null) {
                LogUtil.c("HiH_HiUserDataStore", "updateUserInfo saveUserData after:", Integer.valueOf(a2.getBirthday()), " ", Integer.valueOf(a2.getGender()), " ", Integer.valueOf(a2.getHeight()), " ", Float.valueOf(a2.getWeight()), " ", Integer.valueOf(a2.getUnitType()));
                if (a2.getModifiedIntent() != 268435456 && a2.getCreateTime() != 1) {
                    LogUtil.a("HiH_HiUserDataStore", "updateUserInfo saveUserData setIfUserFirstSync false");
                    ism.f().a(Integer.toString(i), false);
                    d(i2, a2);
                }
            }
        }
        return b2;
    }

    private long a(HiUserInfo hiUserInfo, int i, String str, String str2) {
        int ownerId = hiUserInfo.getOwnerId();
        if (i == ownerId) {
            return 0L;
        }
        if (str.equals(str2)) {
            LogUtil.h("HiH_HiUserDataStore", "subUserInfoInsertOrUpdate the default account should not creat userInfo");
            return 0L;
        }
        if (ownerId <= 0) {
            return this.h.c(hiUserInfo, 0);
        }
        HiUserInfo d = this.h.d(ownerId);
        if (d != null && d.getRelateType() == hiUserInfo.getRelateType() && str.equals(d.getHuid())) {
            return this.h.b(hiUserInfo, ownerId, 0);
        }
        return 0L;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    public List<HiUserInfo> fetchUserData(int i) {
        String a2 = this.d.a(i);
        if (a2 == null) {
            return null;
        }
        return this.h.e(a2);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    public boolean hiLogin(HiAccountInfo hiAccountInfo, ikv ikvVar) {
        return b(hiAccountInfo, ikvVar);
    }

    private boolean b(HiAccountInfo hiAccountInfo, ikv ikvVar) {
        LogUtil.a("HiH_HiUserDataStore", "hiLogin accountInfo = ", hiAccountInfo);
        int e = ikvVar.e();
        String a2 = this.d.a(e);
        String huid = hiAccountInfo.getHuid();
        String f = ikvVar.f();
        if (!f.equals(huid) && f.equals(HuaweiHealth.b())) {
            ivo.b(c).a(huid);
        }
        if (huid.equals(a2)) {
            LogUtil.a("HiH_HiUserDataStore", "hiLogin huid is already login huid = ", huid);
            return true;
        }
        hiAccountInfo.setAppId(e);
        hiAccountInfo.setLogin(1);
        int e2 = this.h.e(a2, 0);
        LogUtil.a("HiH_HiUserDataStore", "hiLogin oldHuid = ", a2, ",oldUserID = ", Integer.valueOf(e2));
        this.d.b(e);
        boolean b2 = this.d.b(hiAccountInfo);
        int e3 = this.h.e(huid, 0);
        if (e3 <= 0) {
            HiUserInfo hiUserInfo = new HiUserInfo();
            hiUserInfo.setHuid(huid);
            hiUserInfo.setRelateType(0);
            hiUserInfo.setCreateTime(1L);
            e3 = (int) this.h.c(hiUserInfo, 0);
        }
        ReleaseLogUtil.e("HiH_HiUserDataStore", "hiLogin isInsert = ", Boolean.valueOf(b2), ",newUser = ", Integer.valueOf(e3), ",oldUser = ", Integer.valueOf(e2), ",hiHealthContext = ", HiJsonUtil.e(ikvVar));
        if (HuaweiHealth.b().equals(f)) {
            igl.c(e, e2, e3);
            e().e(a2);
            e().d(huid);
            ima.a().b(a2);
            ima.a().a(huid);
        }
        Context context = c;
        HiBroadcastUtil.b(context, 0);
        HiBroadcastUtil.c(context, 0);
        HiBroadcastUtil.i(context);
        ivg.c().a(200, "hiLogin", ikvVar);
        ivg.c().a(102, "hiLogin", ikvVar);
        HiBroadcastUtil.d(context, 7);
        return b2;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int hiLogout(defpackage.ikv r6) {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = defpackage.igm.c     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23
            boolean r2 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23
            if (r2 != 0) goto Le
            boolean r2 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L23
            goto Lf
        Le:
            r2 = r0
        Lf:
            int r6 = r5.c(r6)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L49
            if (r2 == 0) goto L18
            defpackage.ivu.j(r1, r0)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L49
        L18:
            if (r2 == 0) goto L1d
            defpackage.ivu.c(r1, r0)
        L1d:
            return r6
        L1e:
            r6 = move-exception
            goto L25
        L20:
            r6 = move-exception
            r2 = r0
            goto L4a
        L23:
            r6 = move-exception
            r2 = r0
        L25:
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L49
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L49
            java.lang.String r4 = "Error to insertBatchList,cause:"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L49
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L49
            r3.append(r6)     // Catch: java.lang.Throwable -> L49
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L49
            r1[r0] = r6     // Catch: java.lang.Throwable -> L49
            java.lang.String r6 = "HiH_HiUserDataStore"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r6, r1)     // Catch: java.lang.Throwable -> L49
            if (r2 == 0) goto L48
            android.content.Context r6 = defpackage.igm.c
            defpackage.ivu.c(r6, r0)
        L48:
            return r0
        L49:
            r6 = move-exception
        L4a:
            if (r2 == 0) goto L51
            android.content.Context r1 = defpackage.igm.c
            defpackage.ivu.c(r1, r0)
        L51:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.igm.hiLogout(ikv):int");
    }

    private int c(ikv ikvVar) {
        LogUtil.a("HiH_HiUserDataStore", "hiLogout healthContext = ", ikvVar);
        int e = ikvVar.e();
        String f = ikvVar.f();
        String a2 = this.d.a(e);
        if (f.equals(a2)) {
            LogUtil.h("HiH_HiUserDataStore", "hiLogout old who = ", f);
            return 0;
        }
        int e2 = this.h.e(a2, 0);
        LogUtil.a("HiH_HiUserDataStore", "hiLogout oldwho = ", Integer.valueOf(e2));
        int b2 = this.d.b(e);
        a(ikvVar);
        int e3 = this.h.e(ikvVar.f(), 0);
        LogUtil.a("HiH_HiUserDataStore", "hiLogout result = ", Integer.valueOf(b2), ",newUser = ", Integer.valueOf(e3), ",oldUser = ", Integer.valueOf(e2), ",packageName = ", f);
        if (HuaweiHealth.b().equals(f)) {
            igl.c(e, e2, e3);
        }
        Context context = c;
        HiBroadcastUtil.b(context, 1);
        HiBroadcastUtil.c(context, 0);
        HiBroadcastUtil.i(context);
        ivg.c().a(200, "hiLogout", ikvVar);
        return b2;
    }

    private void a(ikv ikvVar) {
        LogUtil.a("HiH_HiUserDataStore", "loginDefaultAccount");
        int e = ikvVar.e();
        if (e <= 0) {
            return;
        }
        String f = ikvVar.f();
        HiAccountInfo hiAccountInfo = new HiAccountInfo();
        hiAccountInfo.setLogin(1);
        hiAccountInfo.setAppId(e);
        hiAccountInfo.setHuid(f);
        this.d.b(hiAccountInfo);
        if (this.h.e(f, 0) <= 0) {
            HiUserInfo hiUserInfo = new HiUserInfo();
            hiUserInfo.setHuid(hiAccountInfo.getHuid());
            hiUserInfo.setRelateType(0);
            hiUserInfo.setCreateTime(1L);
            this.h.c(hiUserInfo, 0);
        }
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    public HiAccountInfo fetchAccountInfo(ikv ikvVar) {
        HiAccountInfo d = this.d.d(ikvVar.e());
        if (d != null) {
            return d;
        }
        LogUtil.h("HiH_HiUserDataStore", "fetchAccountInfo accountInfo is null");
        return null;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    public boolean setGoalInfo(int i, int i2, HiGoalInfo hiGoalInfo) {
        if (hiGoalInfo == null) {
            LogUtil.h("HiH_HiUserDataStore", "setGoalInfo goalInfo is null");
            return false;
        }
        if (hiGoalInfo.getGoalType() == 0) {
            LogUtil.h("HiH_HiUserDataStore", "setGoalInfo the HiGoalType.GOAL_ALL_READ will not insert or update");
            return false;
        }
        if (i <= 0) {
            int d = ijl.d(c, i2);
            if (d <= 0) {
                LogUtil.h("HiH_HiUserDataStore", "setGoalInfo userId <= 0");
                return false;
            }
            hiGoalInfo.setOwnerId(d);
            return ijg.d().d(hiGoalInfo, 0);
        }
        if (!ijl.c(i, i2, c)) {
            return false;
        }
        hiGoalInfo.setOwnerId(i);
        return ijg.d().d(hiGoalInfo, 0);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IUserData
    public List<HiGoalInfo> getGoalInfo(int i, int i2, int i3) {
        if (i <= 0 && (i = ijl.d(c, i3)) <= 0) {
            LogUtil.h("HiH_HiUserDataStore", "getGoalInfo userId <= 0");
            return null;
        }
        if (!ijl.e(i, i3, c)) {
            return null;
        }
        if (i2 == 0) {
            return ijg.d().a(i);
        }
        return ijg.d().c(i, i2);
    }

    private void d(int i, HiUserInfo hiUserInfo) {
        LogUtil.a("HiH_HiUserDataStore", "start uploadUserToCloud");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10005);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setUserInfo(hiUserInfo);
        ism.f().a(hiSyncOption, i);
    }

    public void a(String str) {
        this.e = str;
    }

    public void e(String str, String str2, HiAccountInfo hiAccountInfo, HiUserAuth hiUserAuth, ikv ikvVar) {
        hiAccountInfo.setHuid(str2);
        hiAccountInfo.setAccessToken(str);
        hiAccountInfo.setThirdOpenId(hiUserAuth.getOpenId());
        hiAccountInfo.setExpiresIn((CommonUtil.g(hiUserAuth.getExpireIn()) * 1000) + System.currentTimeMillis());
        hiAccountInfo.setLogin(1);
        int e = ikvVar.e();
        hiAccountInfo.setAppId(e);
        this.d.b(hiAccountInfo);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(Integer.valueOf(e), str2);
        b.put(arrayMap, hiAccountInfo);
        LogUtil.c("HiH_HiUserDataStore", "requestAuthorization updateAccountInfo accountInfo = ", Integer.valueOf(hiAccountInfo.getAppId()));
    }

    public void b() {
        Map<Map<Integer, String>, HiAccountInfo> map = b;
        if (map != null) {
            map.clear();
        }
    }

    public int c(int i) {
        String a2 = a();
        if (HiCommonUtil.b(a2)) {
            LogUtil.h("HiH_HiUserDataStore", "who is null or empty");
            return 24;
        }
        ArrayMap arrayMap = new ArrayMap(16);
        arrayMap.put(Integer.valueOf(i), a2);
        if (this.f13368a.equals(a2)) {
            HiAccountInfo hiAccountInfo = b.get(arrayMap);
            if (hiAccountInfo == null) {
                return 15;
            }
            LogUtil.c("HiH_HiUserDataStore", "checkHuidAndAccessTokenValidTimeSync appID = ", Integer.valueOf(i));
            if (i == hiAccountInfo.getAppId() && this.e.equals(hiAccountInfo.getAccessToken())) {
                long expiresIn = hiAccountInfo.getExpiresIn();
                LogUtil.c("HiH_HiUserDataStore", "atValidTime expire_in = ", Long.valueOf(expiresIn), " appID = ", Integer.valueOf(i));
                if (System.currentTimeMillis() > expiresIn) {
                    LogUtil.c("HiH_HiUserDataStore", "accessToken is invalid, expire_in = ", Long.valueOf(expiresIn), " appID = ", Integer.valueOf(i));
                    return 23;
                }
            }
        } else {
            LogUtil.c("HiH_HiUserDataStore", "checkhuidAndAT previousWho");
            arrayMap.put(Integer.valueOf(i), this.f13368a);
            HiAccountInfo hiAccountInfo2 = b.get(arrayMap);
            if (hiAccountInfo2 != null) {
                hiAccountInfo2.setLogin(0);
                b.put(arrayMap, hiAccountInfo2);
            }
            HiAccountInfo b2 = this.d.b(i, this.f13368a);
            if (b2 != null) {
                b2.setLogin(0);
                this.d.b(b2);
                e(i, a2, arrayMap);
                this.f13368a = a2;
            } else {
                LogUtil.c("HiH_HiUserDataStore", "checkhuidAndAT errorAccountInfo is null");
                return 15;
            }
        }
        return 0;
    }

    private void e(int i, String str, Map<Integer, String> map) {
        map.put(Integer.valueOf(i), str);
        HiAccountInfo hiAccountInfo = b.get(map);
        if (hiAccountInfo != null) {
            hiAccountInfo.setLogin(1);
            b.put(map, hiAccountInfo);
        }
        HiAccountInfo b2 = this.d.b(i, str);
        if (b2 != null) {
            b2.setLogin(1);
            this.d.b(b2);
        }
    }

    public int d() {
        return ijz.c().e(e().a(), 0);
    }

    public HiAccountInfo c(String str, ikv ikvVar) {
        boolean z;
        HiAccountInfo a2;
        int e = ikvVar.e();
        ArrayMap arrayMap = new ArrayMap(16);
        arrayMap.put(Integer.valueOf(e), str);
        HiAccountInfo hiAccountInfo = new HiAccountInfo();
        if (this.f13368a.equals(str)) {
            HiAccountInfo a3 = a(str, e, arrayMap);
            LogUtil.c("HiH_HiUserDataStore", "requestAuthorization getCurrentAccountInfo mCurrentHuid = huid, accountInfo = ", a3, " at valid time is ", Long.valueOf(a3.getExpiresIn()));
            return a3;
        }
        try {
            LogUtil.a("HiH_HiUserDataStore", "insertOrUpdateAccountInfo");
            Context context = c;
            z = !ivu.i(context, 0) ? ivu.e(context, 0) : false;
            try {
                try {
                    hiAccountInfo = this.d.b(e, this.f13368a);
                    if (hiAccountInfo != null) {
                        hiAccountInfo.setLogin(0);
                        this.d.b(hiAccountInfo);
                    }
                    a2 = a(str, e, arrayMap);
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    this.f13368a = str;
                    LogUtil.c("HiH_HiUserDataStore", "requestAuthorization getCurrentAccountInfo accountInfo = ", Integer.valueOf(a2.getAppId()), " at valid time is ", Long.valueOf(a2.getExpiresIn()));
                    if (z) {
                        ivu.j(context, 0);
                    }
                    if (z) {
                        ivu.c(context, 0);
                    }
                    return a2;
                } catch (Exception e3) {
                    e = e3;
                    hiAccountInfo = a2;
                    ReleaseLogUtil.d("HiH_HiUserDataStore", "getCurrentAccountInfo: ", LogAnonymous.b((Throwable) e));
                    if (z) {
                        ivu.c(c, 0);
                    }
                    return hiAccountInfo;
                }
            } catch (Throwable th) {
                th = th;
                if (z) {
                    ivu.c(c, 0);
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            z = false;
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
    }

    private HiAccountInfo a(String str, int i, Map<Integer, String> map) {
        HiAccountInfo hiAccountInfo = b.get(map);
        if (hiAccountInfo == null) {
            LogUtil.c("HiH_HiUserDataStore", "requestAuthorization getHiAccountInfo not in cache ");
            hiAccountInfo = this.d.b(i, str);
            if (hiAccountInfo == null) {
                hiAccountInfo = new HiAccountInfo();
            }
        }
        LogUtil.c("HiH_HiUserDataStore", "requestAuthorization getHiAccountInfo accountInfo is ", Integer.valueOf(hiAccountInfo.getAppId()));
        return hiAccountInfo;
    }

    public String a() {
        if (this.d == null) {
            return "";
        }
        String h = h();
        if (!HiCommonUtil.b(h)) {
            LogUtil.c("HiH_HiUserDataStore", "getStringHuid() from cache");
            return h;
        }
        int c2 = c();
        String a2 = this.d.a(c2);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(Integer.valueOf(c2), a2);
        HiAccountInfo b2 = this.d.b(c2, a2);
        if (b2 != null) {
            b2.setLogin(1);
            b.put(arrayMap, b2);
            LogUtil.c("HiH_HiUserDataStore", "getStringHuid() from DB");
        }
        return a2;
    }

    private String h() {
        for (Map.Entry<Map<Integer, String>, HiAccountInfo> entry : b.entrySet()) {
            if (entry.getValue().getIsLogin() == 1 && entry.getValue().getAppId() == c()) {
                return entry.getValue().getHuid();
            }
        }
        return null;
    }

    public int c() {
        Integer num = ikn.bBq_().get(HuaweiHealth.b());
        if (num != null) {
            return num.intValue();
        }
        int c2 = c(HuaweiHealth.b());
        ikn.bBq_().put(HuaweiHealth.b(), Integer.valueOf(c2));
        return c2;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int c(java.lang.String r5) {
        /*
            r4 = this;
            iip r0 = defpackage.iip.b()
            int r0 = r0.a(r5)
            if (r0 <= 0) goto Lb
            return r0
        Lb:
            r0 = 0
            android.content.Context r1 = defpackage.igm.c     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            boolean r2 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            if (r2 != 0) goto L19
            boolean r2 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            goto L1a
        L19:
            r2 = r0
        L1a:
            int r5 = r4.b(r5)     // Catch: java.lang.Exception -> L2b java.lang.Throwable -> L4d
            if (r2 == 0) goto L25
            if (r5 <= 0) goto L25
            defpackage.ivu.j(r1, r0)     // Catch: java.lang.Exception -> L2b java.lang.Throwable -> L4d
        L25:
            if (r2 == 0) goto L2a
            defpackage.ivu.c(r1, r0)
        L2a:
            return r5
        L2b:
            r5 = move-exception
            goto L32
        L2d:
            r5 = move-exception
            r2 = r0
            goto L4e
        L30:
            r5 = move-exception
            r2 = r0
        L32:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L4d
            java.lang.String r3 = "queryOrCreateAppId: "
            r1[r0] = r3     // Catch: java.lang.Throwable -> L4d
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L4d
            r3 = 1
            r1[r3] = r5     // Catch: java.lang.Throwable -> L4d
            java.lang.String r5 = "HiH_HiUserDataStore"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r5, r1)     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L4c
            android.content.Context r5 = defpackage.igm.c
            defpackage.ivu.c(r5, r0)
        L4c:
            return r0
        L4d:
            r5 = move-exception
        L4e:
            if (r2 == 0) goto L55
            android.content.Context r1 = defpackage.igm.c
            defpackage.ivu.c(r1, r0)
        L55:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.igm.c(java.lang.String):int");
    }

    public int b(String str) {
        int a2 = iip.b().a(str);
        if (a2 > 0) {
            return a2;
        }
        HiAppInfo b2 = ivw.b(c, str);
        LogUtil.a("HiH_HiUserDataStore", "queryOrCreateAppId() app <= 0, appInfo = ", b2);
        return (int) iip.b().e(b2, 0);
    }

    public void d(String str) {
        if (HiCommonUtil.b(str)) {
            return;
        }
        for (Map.Entry<Map<Integer, String>, HiAccountInfo> entry : b.entrySet()) {
            HiAccountInfo value = entry.getValue();
            if (str.equals(value.getHuid())) {
                value.setLogin(1);
                b.put(entry.getKey(), value);
            }
        }
    }

    public void e(String str) {
        if (HiCommonUtil.b(str)) {
            return;
        }
        for (Map.Entry<Map<Integer, String>, HiAccountInfo> entry : b.entrySet()) {
            HiAccountInfo value = entry.getValue();
            if (str.equals(value.getHuid())) {
                value.setLogin(0);
                b.put(entry.getKey(), value);
            }
        }
    }
}
