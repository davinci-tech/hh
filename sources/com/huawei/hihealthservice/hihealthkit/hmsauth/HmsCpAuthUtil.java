package com.huawei.hihealthservice.hihealthkit.hmsauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealthservice.hihealthkit.hmsauth.HmsCpAuthUtil;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.hihealth.AuthController;
import com.huawei.hms.hihealth.HiHealthOptions;
import com.huawei.hms.hihealth.HuaweiHiHealth;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ijm;
import defpackage.ima;
import defpackage.ioy;
import defpackage.ipq;
import defpackage.iqt;
import defpackage.iqz;
import defpackage.ird;
import defpackage.lqi;
import health.compact.a.CommonUtil;
import health.compact.a.HwEncryptUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class HmsCpAuthUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Long f4196a = 86400000L;
    private Context b;
    private AuthController e;

    public HmsCpAuthUtil(Context context) {
        AuthHuaweiId extendedAuthResult = HuaweiIdAuthManager.getExtendedAuthResult(HiHealthOptions.builder().build());
        this.b = context;
        this.e = HuaweiHiHealth.getAuthController(context, extendedAuthResult);
    }

    private boolean d(String str, final List<String> list) {
        final boolean[] zArr = {false};
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "packageName is empty");
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Task<List<String>> queryAuthInfoByAppId = this.e.queryAuthInfoByAppId(HiScopeUtil.c(this.b, str));
            queryAuthInfoByAppId.addOnSuccessListener(new OnSuccessListener() { // from class: ipn
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    HmsCpAuthUtil.e(list, zArr, countDownLatch, (List) obj);
                }
            });
            queryAuthInfoByAppId.addOnFailureListener(new OnFailureListener() { // from class: com.huawei.hihealthservice.hihealthkit.hmsauth.HmsCpAuthUtil.2
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "queryAuthInfo on Failure: ", exc.getMessage());
                    countDownLatch.countDown();
                }
            });
        } catch (Exception e) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "query auth info error ", LogAnonymous.b((Throwable) e));
        }
        try {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "queryAuthInfo isOnTime: ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
            HuaweiHiHealth.disconnect();
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "Hms auth result: ", Boolean.valueOf(zArr[0]));
            return zArr[0];
        } catch (InterruptedException e2) {
            LogUtil.b("R_HiH_HMSAuth_HmsCpAuthUtil", "queryAuthInfo InterruptedException", LogAnonymous.b((Throwable) e2));
            return false;
        }
    }

    public static /* synthetic */ void e(List list, boolean[] zArr, CountDownLatch countDownLatch, List list2) {
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "queryAuthInfo success, scopeList: ", HiJsonUtil.e(list2));
        Object obj = (String) list.get(0);
        String str = (String) list.get(1);
        String str2 = (String) list.get(2);
        String str3 = (String) list.get(3);
        boolean z = (!TextUtils.isEmpty(str) && list2.contains(str)) || (!TextUtils.isEmpty(str2) && list2.contains(str2));
        boolean contains = list2.contains(obj);
        boolean z2 = !TextUtils.isEmpty(str3) && list2.contains(str3);
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "isAtomicScopePassed:", Boolean.valueOf(z), " isHmsScopePassed:", Boolean.valueOf(contains), " isExtendScopePassed:", Boolean.valueOf(z2));
        if (contains || z || z2) {
            zArr[0] = true;
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "verify scopes pass");
        }
        countDownLatch.countDown();
    }

    private boolean c(String str) {
        final boolean[] zArr = {false};
        String c = HiScopeUtil.c(this.b, str);
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "The cp which packageName is ", str, "authorization failed because appId is null.");
            return zArr[0];
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            Task<String> checkFingerprint = this.e.checkFingerprint(c, str);
            checkFingerprint.addOnSuccessListener(new OnSuccessListener() { // from class: ipk
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    HmsCpAuthUtil.d(zArr, currentTimeMillis, countDownLatch, (String) obj);
                }
            });
            checkFingerprint.addOnFailureListener(new OnFailureListener() { // from class: ipt
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    HmsCpAuthUtil.c(currentTimeMillis, countDownLatch, exc);
                }
            });
        } catch (Exception e) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "check finger error ", LogAnonymous.b((Throwable) e));
        }
        try {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "checkFingerprint isOnTime: ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "isHMSCertFingerAuthorized InterruptedException", LogAnonymous.b((Throwable) e2));
        }
        HuaweiHiHealth.disconnect();
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "checkFingerprint result: ", Boolean.valueOf(zArr[0]));
        return zArr[0];
    }

    public static /* synthetic */ void d(boolean[] zArr, long j, CountDownLatch countDownLatch, String str) {
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "checkFingerprint has response");
        if (str != null && "0".equals(str)) {
            zArr[0] = true;
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "fingerprint authority success");
        }
        LogUtil.c("HMSAuth_HmsCpAuthUtil", "isHMSCertFingerAuthorized needs ", Long.valueOf(System.currentTimeMillis() - j), " ms");
        countDownLatch.countDown();
    }

    public static /* synthetic */ void c(long j, CountDownLatch countDownLatch, Exception exc) {
        ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "fingerprint authority failure");
        LogUtil.c("HMSAuth_HmsCpAuthUtil", "isHMSCertFingerAuthorized needs ", Long.valueOf(System.currentTimeMillis() - j), " ms");
        countDownLatch.countDown();
    }

    public int c(int i, String str, boolean z) {
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "getAuthCode");
        if (CommonUtil.cg()) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "isTestThirdDeviceVersion, Auth success");
            return 0;
        }
        if (iqt.b()) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "package is whiteHap, Auth success");
            return 0;
        }
        if (ioy.e(str, this.b)) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "isWhiteApp, checkDataSharingEnable");
            return ioy.a(str);
        }
        if (ipq.e(i, z)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "dataType is not right");
            return 1001;
        }
        if (str == null || str.length() == 0) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "packageName is empty");
            return 1001;
        }
        if (d(i, z) == 0) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "local permission is passed");
            return 0;
        }
        boolean e = e();
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "isHmsVersionUsable : ", Boolean.valueOf(e), " api level : ", Integer.valueOf(Build.VERSION.SDK_INT));
        if (e) {
            return c(str, i, z);
        }
        return iqz.d(str, 4) ? 32 : 1001;
    }

    public int c(String str, int i, boolean z) {
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "checkHmsPermission");
        String e = e(str);
        if (e.equals("0")) {
            if (!c(str)) {
                ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", str + ": HmsCertFingerAuthorized is failed");
                return 1001;
            }
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", str + ": update certFinger");
            e(str, a(str));
        } else if (!a(str).equals(e)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", str, "old signature is not equal new signature");
            return 1001;
        }
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "HmsCertFingerAuthorized is passed");
        List<String> a2 = ipq.a(i, z);
        if (ipq.e(i, z)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "dataType is not right");
            return 1001;
        }
        LogUtil.a("HMSAuth_HmsCpAuthUtil", "hmsScope:", a2.get(0), " atomicScope:", a2.get(1), " bothScope:", a2.get(2), "new extendScope:", a2.get(3));
        return d(str, a2) ? 8 : 1001;
    }

    private int d(int i, boolean z) {
        boolean d;
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "go to loaclPermission");
        if (!ioy.a(this.b, Binder.getCallingUid())) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "newUid is not equal oldUid");
            return 1001;
        }
        try {
            int d2 = ima.a().d();
            ijm e = ijm.e(this.b);
            if (e != null) {
                if (z) {
                    d = e.c(d2, i);
                } else {
                    d = e.d(d2, i);
                }
                ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "isHasLocalPermission:", Boolean.valueOf(d));
                if (!d) {
                    return 1001;
                }
                lqi.d();
                ird d3 = ird.d(this.b);
                boolean a2 = d3.a(z ? d3.b(i) : d3.a(i));
                ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", " isLocalScopes ", Boolean.valueOf(a2));
                return a2 ? 0 : 1002;
            }
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "hiHealthUserPermissionManager == null");
            return 4;
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "thirdAppId has error", LogAnonymous.b((Throwable) e2));
            return 1001;
        }
    }

    private void e(String str, String str2) {
        String str3;
        SharedPreferences.Editor edit = this.b.getSharedPreferences("thirdAppCertFingerFile", 0).edit();
        try {
            str3 = HwEncryptUtil.c(this.b).b(1, str2 + "/" + Long.valueOf(System.currentTimeMillis()));
        } catch (GeneralSecurityException e) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "saveThirdAppCertFinger data is not right", LogAnonymous.b((Throwable) e));
            str3 = "";
        }
        edit.putString(str, str3);
        edit.apply();
    }

    private String e(String str) {
        String str2;
        String string = this.b.getSharedPreferences("thirdAppCertFingerFile", 0).getString(str, "0");
        LogUtil.a("HMSAuth_HmsCpAuthUtil", "PackageName : ", str);
        if (string.equals("0")) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "CertFinger is no cache, return default value");
            return "0";
        }
        try {
            str2 = HwEncryptUtil.c(this.b).a(1, string);
        } catch (GeneralSecurityException e) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "readThirdAppCertFinger data is not right", LogAnonymous.b((Throwable) e));
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "decryptCertFinger is empty, return default value");
            return "0";
        }
        String[] split = str2.split("/");
        if (Long.valueOf(Long.valueOf(System.currentTimeMillis()).longValue() - Long.valueOf(split[1]).longValue()).longValue() >= f4196a.longValue()) {
            return "0";
        }
        return split[0];
    }

    private String a(String str) {
        String e = HsfSignValidator.e(this.b, str);
        if (e == null) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "thirdAppCertFinger is null");
            return "0";
        }
        LogUtil.a("HMSAuth_HmsCpAuthUtil", "thirdApp : ", str, " thirdAppCertFinger : ", e);
        return e;
    }

    private boolean e() {
        LogUtil.a("HMSAuth_HmsCpAuthUtil", "isHmsUsableVersion");
        Context context = this.b;
        if (context == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(CommonUtil.p(), 0);
            LogUtil.a("HMSAuth_HmsCpAuthUtil", "isHmsUsableVersion packageInfo=", packageInfo);
            int i = packageInfo.versionCode;
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsCpAuthUtil", "isHmsUsableVersion versionCode=", Integer.valueOf(i));
            return i >= 50100300;
        } catch (PackageManager.NameNotFoundException e) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsCpAuthUtil", "HmsUsableVersion NameNotFoundException", LogAnonymous.b((Throwable) e));
            return false;
        }
    }
}
