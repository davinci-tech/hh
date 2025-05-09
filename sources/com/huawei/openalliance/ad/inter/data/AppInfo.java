package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.InstallConfig;
import com.huawei.openalliance.ad.beans.metadata.Permission;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.ph;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class AppInfo implements Serializable {
    private static final String TAG = "AppInfo";
    private static final long serialVersionUID = 30414300;
    private String actName;
    private String afDlBtnText;
    private long allAreaPopDelay;
    private boolean allowedNonWifiNetwork;
    private String appCountry;
    private String appDesc;
    private String appDetailsUrl;
    private String appLanguage;
    private String appName;
    private int appType;
    private int autoOpenAfterInstall;
    private String channelInfo;
    private int channelInfoSaveLimit;
    private boolean checkSha256;
    private String contentInstallBtnAction;
    private String contiBtn;
    private String curInstallWay;
    private String developerName;
    private String dlBtnText;
    private String downloadUrl;
    private long fileSize;
    private int fullScrnNotify;
    private String fullScrnNotifyText;
    private Integer hasPermissions;
    private String iconUrl;
    private String insActvNotifyBtnText;
    private int insActvNotifyCfg;
    private InstallConfig installConfig;
    private String installPermiText;
    private String installPureModeText;
    private String intent;
    private String intentPackage;
    private String intentUri;
    private String nextInstallWays;
    private int noAlertTime;
    private String packageName;
    private boolean permPromptForCard;
    private boolean permPromptForLanding;
    private String permissionUrl;
    private List<PermissionEntity> permissions;
    private int popNotify;
    private int popUpAfterInstallNew;
    private String popUpAfterInstallText;
    private int popUpStyle;
    private String priorInstallWay;
    private String privacyUrl;
    private String pureModeText;
    private transient String remoteAfDlBtnText;
    private transient String remoteDlBtnText;
    private String reservedPkgName;
    private String safeDownloadUrl;
    private String sha256;
    private int trafficReminder;
    private String uniqueId;
    private String versionCode;
    private String versionName;

    /* loaded from: classes9.dex */
    public interface PermissionCallBack {
        void onPermissionCallBack(List<PermissionEntity> list);
    }

    public void z(String str) {
        this.intent = str;
    }

    public int z() {
        return this.appType;
    }

    public void y(String str) {
        this.insActvNotifyBtnText = str;
    }

    public String y() {
        return this.actName;
    }

    public void x(String str) {
        this.fullScrnNotifyText = str;
    }

    public String x() {
        return this.curInstallWay;
    }

    public void w(String str) {
        this.remoteAfDlBtnText = str;
    }

    public String w() {
        return this.appCountry;
    }

    public void v(String str) {
        this.remoteDlBtnText = str;
    }

    public String v() {
        return this.appLanguage;
    }

    public boolean u() {
        Integer num = this.hasPermissions;
        return num != null ? num.intValue() == 1 : !bg.a(this.permissions);
    }

    public void u(String str) {
        this.afDlBtnText = str;
    }

    public void t(String str) {
        this.dlBtnText = str;
    }

    public String t() {
        return this.nextInstallWays;
    }

    public void showPrivacyPolicyInWeb(Context context) {
        b(this.privacyUrl, context);
    }

    public void showPrivacyPolicy(Context context) {
        a(context, this.privacyUrl);
    }

    public void showPermissionPageInWeb(Context context) {
        b(this.permissionUrl, context);
    }

    public void showPermissionPage(Context context) {
        a(context, this.permissionUrl);
    }

    public void setAllowedNonWifiNetwork(boolean z) {
        this.allowedNonWifiNetwork = z;
    }

    public void s(String str) {
        this.uniqueId = str;
    }

    public String s() {
        return this.intentPackage;
    }

    public void requestPermissions(Context context, PermissionCallBack permissionCallBack) {
        if (getPermissions() == null) {
            new ph(context, new a(permissionCallBack)).a(this);
        } else if (permissionCallBack != null) {
            permissionCallBack.onPermissionCallBack(getPermissions());
        }
    }

    public void r(String str) {
        this.channelInfo = str;
    }

    public String r() {
        return this.intent;
    }

    public void q(String str) {
        this.popUpAfterInstallText = str;
    }

    public int q() {
        return this.insActvNotifyCfg;
    }

    public void p(String str) {
        this.contentInstallBtnAction = str;
    }

    public String p() {
        return this.insActvNotifyBtnText;
    }

    public void o(String str) {
        this.priorInstallWay = str;
    }

    public String o() {
        return this.fullScrnNotifyText;
    }

    public void n(String str) {
        this.developerName = str;
    }

    public int n() {
        return this.fullScrnNotify;
    }

    public void m(String str) {
        this.appDesc = str;
    }

    public int m() {
        return this.popNotify;
    }

    public void l(String str) {
        this.appName = str;
    }

    public String l() {
        return this.remoteAfDlBtnText;
    }

    public void k(String str) {
        this.intentUri = str;
    }

    public String k() {
        return this.remoteDlBtnText;
    }

    public void j(String str) {
        this.safeDownloadUrl = str;
    }

    public void j(int i) {
        this.popUpStyle = i;
    }

    public String j() {
        return this.afDlBtnText;
    }

    public boolean isPermPromptForLanding() {
        return this.permPromptForLanding;
    }

    public boolean isPermPromptForCard() {
        return this.permPromptForCard;
    }

    public boolean isCheckSha256() {
        return this.checkSha256;
    }

    public boolean isAllowedNonWifiNetwork() {
        return this.allowedNonWifiNetwork;
    }

    public void i(String str) {
        this.sha256 = str;
    }

    public void i(int i) {
        this.autoOpenAfterInstall = i;
    }

    public String i() {
        return this.dlBtnText;
    }

    public void h(String str) {
        this.privacyUrl = str;
    }

    public void h(int i) {
        this.appType = i;
    }

    public int h() {
        return this.trafficReminder;
    }

    public String getVersionName() {
        String str = this.versionName;
        return str == null ? "" : str;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public String getSha256() {
        return this.sha256;
    }

    public String getSafeDownloadUrl() {
        return this.safeDownloadUrl;
    }

    public String getPrivacyLink() {
        return this.privacyUrl;
    }

    public String getPriorInstallWay() {
        return this.priorInstallWay;
    }

    public List<PermissionEntity> getPermissions() {
        return this.permissions;
    }

    public String getPermissionUrl() {
        return this.permissionUrl;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getIntentUri() {
        return this.intentUri;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getDeveloperName() {
        String str = this.developerName;
        return str == null ? "" : str;
    }

    public String getCta(AppStatus appStatus) {
        int i = AnonymousClass3.f7039a[appStatus.ordinal()];
        if (i == 1) {
            return this.afDlBtnText;
        }
        if (i != 2) {
            return null;
        }
        return this.dlBtnText;
    }

    public String getAppName() {
        String str = this.appName;
        return str == null ? "" : str;
    }

    public String getAppDetailUrl() {
        return this.appDetailsUrl;
    }

    public String getAppDesc() {
        String str = this.appDesc;
        return str == null ? "" : str;
    }

    public void g(String str) {
        this.appDetailsUrl = str;
    }

    public void g(int i) {
        this.insActvNotifyCfg = i;
    }

    public int g() {
        return this.noAlertTime;
    }

    public void f(String str) {
        this.permissionUrl = str;
    }

    public void f(int i) {
        this.fullScrnNotify = i;
    }

    public int f() {
        return this.channelInfoSaveLimit;
    }

    public void e(String str) {
        this.downloadUrl = str;
    }

    public void e(int i) {
        this.popNotify = i;
    }

    public String e() {
        return this.channelInfo;
    }

    public void d(String str) {
        this.iconUrl = str;
    }

    public void d(int i) {
        this.trafficReminder = i;
    }

    public String d() {
        return this.popUpAfterInstallText;
    }

    public void c(boolean z) {
        this.checkSha256 = z;
    }

    public void c(String str) {
        this.versionName = str;
    }

    public void c(Integer num) {
        this.hasPermissions = num;
    }

    public void c(int i) {
        this.noAlertTime = i;
    }

    public int c() {
        return this.popUpAfterInstallNew;
    }

    public void b(boolean z) {
        this.permPromptForLanding = z;
    }

    public void b(List<PermissionEntity> list) {
        this.permissions = list;
    }

    public void b(String str) {
        this.versionCode = str;
    }

    public void b(long j) {
        this.allAreaPopDelay = j;
    }

    public void b(int i) {
        this.channelInfoSaveLimit = i;
    }

    public String b(Integer num) {
        if (num == null) {
            num = 5;
        }
        if (1 == num.intValue() || 14 == num.intValue() || 10005 == num.intValue()) {
            return a();
        }
        String x = x();
        return TextUtils.isEmpty(x) ? getPriorInstallWay() : x;
    }

    public InstallConfig b() {
        return this.installConfig;
    }

    public boolean a(Integer num) {
        if (TextUtils.isEmpty(this.packageName)) {
            return false;
        }
        String b = b(num);
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        return b.equals("8") || b.equals("6") || b.equals("5");
    }

    public void a(boolean z) {
        this.permPromptForCard = z;
    }

    public void a(List<Permission> list) {
        StringBuilder sb;
        if (list == null || list.size() <= 0) {
            return;
        }
        try {
            ArrayMap arrayMap = new ArrayMap();
            for (Permission permission : list) {
                List list2 = (List) arrayMap.get(permission.b());
                if (list2 == null) {
                    list2 = new ArrayList();
                    arrayMap.put(permission.b(), list2);
                }
                list2.add(new PermissionEntity(cz.c(permission.a()), 1));
            }
            this.permissions = new ArrayList();
            for (Map.Entry entry : arrayMap.entrySet()) {
                this.permissions.add(new PermissionEntity(cz.c((String) entry.getKey()), 0));
                this.permissions.addAll((Collection) entry.getValue());
            }
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("parsePermission RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.d(TAG, sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("parsePermission Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.d(TAG, sb.toString());
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("parsePermission Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.d(TAG, sb.toString());
        }
    }

    public void a(String str) {
        this.packageName = str;
    }

    public void a(InstallConfig installConfig) {
        this.installConfig = installConfig;
    }

    public void a(long j) {
        this.fileSize = j;
    }

    public void a(int i) {
        this.popUpAfterInstallNew = i;
    }

    public String a() {
        return this.contentInstallBtnAction;
    }

    public void K(String str) {
        this.reservedPkgName = str;
    }

    public void J(String str) {
        this.contiBtn = str;
    }

    public String J() {
        return this.privacyUrl;
    }

    public void I(String str) {
        this.installPureModeText = str;
    }

    public Integer I() {
        return this.hasPermissions;
    }

    public void H(String str) {
        this.pureModeText = str;
    }

    public String H() {
        return this.reservedPkgName;
    }

    public void G(String str) {
        this.installPermiText = str;
    }

    public String G() {
        return this.contiBtn;
    }

    public void F(String str) {
        this.actName = str;
    }

    public String F() {
        return this.installPureModeText;
    }

    public void E(String str) {
        this.curInstallWay = str;
    }

    public String E() {
        return this.pureModeText;
    }

    public void D(String str) {
        this.appCountry = str;
    }

    public String D() {
        return this.installPermiText;
    }

    public void C(String str) {
        this.appLanguage = str;
    }

    public int C() {
        return this.popUpStyle;
    }

    public void B(String str) {
        this.nextInstallWays = str;
    }

    public long B() {
        return this.allAreaPopDelay;
    }

    public void A(String str) {
        this.intentPackage = str;
    }

    public int A() {
        return this.autoOpenAfterInstall;
    }

    private void b(final String str, final Context context) {
        if (TextUtils.isEmpty(str)) {
            ho.b(TAG, "url is empty.");
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.data.AppInfo.2
                @Override // java.lang.Runnable
                public void run() {
                    bx.a(context, str);
                }
            });
        }
    }

    private void a(final String str, final Context context) {
        if (TextUtils.isEmpty(str)) {
            ho.b(TAG, "url is empty.");
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.data.AppInfo.1
                @Override // java.lang.Runnable
                public void run() {
                    ao.a(context, str);
                }
            });
        }
    }

    /* loaded from: classes9.dex */
    static class a implements ph.a {

        /* renamed from: a, reason: collision with root package name */
        PermissionCallBack f7040a;

        @Override // com.huawei.openalliance.ad.ph.a
        public void a(List<PermissionEntity> list) {
            PermissionCallBack permissionCallBack = this.f7040a;
            if (permissionCallBack != null) {
                permissionCallBack.onPermissionCallBack(list);
            }
        }

        a(PermissionCallBack permissionCallBack) {
            this.f7040a = permissionCallBack;
        }
    }

    private void a(Context context, String str) {
        Boolean o = HiAd.a(context).o();
        if (o == null || o.booleanValue()) {
            a(str, context);
        } else {
            b(str, context);
        }
    }

    /* renamed from: com.huawei.openalliance.ad.inter.data.AppInfo$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7039a;

        static {
            int[] iArr = new int[AppStatus.values().length];
            f7039a = iArr;
            try {
                iArr[AppStatus.INSTALLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7039a[AppStatus.DOWNLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AppInfo(ApkInfo apkInfo) {
        this.priorInstallWay = "3";
        this.contentInstallBtnAction = "3";
        this.permPromptForCard = true;
        this.permPromptForLanding = false;
        this.popUpAfterInstallNew = 0;
        this.channelInfoSaveLimit = -2;
        this.insActvNotifyCfg = 1;
        this.appType = 1;
        if (apkInfo != null) {
            this.appName = cz.c(apkInfo.j());
            this.iconUrl = apkInfo.t();
            this.packageName = apkInfo.a();
            this.versionCode = apkInfo.b();
            this.versionName = apkInfo.c();
            this.downloadUrl = apkInfo.d();
            this.permissionUrl = apkInfo.h();
            this.appDetailsUrl = apkInfo.i();
            this.fileSize = apkInfo.e();
            this.sha256 = apkInfo.f();
            this.checkSha256 = apkInfo.L() == 0;
            this.safeDownloadUrl = apkInfo.g();
            this.channelInfo = apkInfo.s();
            this.channelInfoSaveLimit = apkInfo.u();
            String l = apkInfo.l();
            if (!TextUtils.isEmpty(l)) {
                this.priorInstallWay = l;
            }
            String m = apkInfo.m();
            if (!TextUtils.isEmpty(m)) {
                this.contentInstallBtnAction = m;
            }
            this.installConfig = apkInfo.n();
            this.curInstallWay = this.priorInstallWay;
            this.permPromptForCard = "1".equals(apkInfo.o());
            this.permPromptForLanding = "1".equals(apkInfo.p());
            this.popUpAfterInstallNew = apkInfo.q();
            this.popUpAfterInstallText = apkInfo.r();
            this.dlBtnText = cz.c(apkInfo.y());
            this.afDlBtnText = cz.c(apkInfo.z());
            this.popNotify = apkInfo.A();
            this.fullScrnNotify = apkInfo.B();
            this.fullScrnNotifyText = cz.c(apkInfo.C());
            this.insActvNotifyBtnText = cz.c(apkInfo.D());
            this.insActvNotifyCfg = apkInfo.E();
            a(apkInfo.k());
            this.iconUrl = apkInfo.t();
            this.appDesc = cz.c(apkInfo.v());
            this.developerName = cz.c(apkInfo.I());
            this.noAlertTime = apkInfo.w() > 0 ? apkInfo.w() : 7;
            this.trafficReminder = apkInfo.x();
            this.intent = apkInfo.F();
            this.intentPackage = apkInfo.G();
            this.hasPermissions = apkInfo.H();
            this.nextInstallWays = apkInfo.K();
            this.actName = apkInfo.M();
            this.appType = apkInfo.N();
            this.autoOpenAfterInstall = apkInfo.O();
            this.allAreaPopDelay = apkInfo.P();
            this.popUpStyle = apkInfo.Q();
            this.installPermiText = cz.c(apkInfo.R());
            this.pureModeText = cz.c(apkInfo.S());
            this.installPureModeText = cz.c(apkInfo.T());
            this.contiBtn = cz.c(apkInfo.U());
            this.reservedPkgName = apkInfo.V();
        }
    }

    public AppInfo() {
        this.priorInstallWay = "3";
        this.contentInstallBtnAction = "3";
        this.permPromptForCard = true;
        this.permPromptForLanding = false;
        this.popUpAfterInstallNew = 0;
        this.channelInfoSaveLimit = -2;
        this.insActvNotifyCfg = 1;
        this.appType = 1;
    }
}
