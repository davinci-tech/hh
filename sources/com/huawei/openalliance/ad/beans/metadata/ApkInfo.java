package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.d;
import java.util.List;

/* loaded from: classes5.dex */
public class ApkInfo {
    private String actName;
    private String afDlBtnText;
    private long allAreaPopDelay;
    private String appDesc;
    private String appName;
    private int autoOpenAfterInstall;
    private String channelInfo;
    private int checkSha256Flag;
    private String contentBtnAction;
    private String contiBtn;
    private String detailUrl;
    private String developerName;
    private String dlBtnText;
    private long fileSize;
    private String fullScrnNotifyText;

    @d(a = "hasper")
    private Integer hasPermission;

    @d(a = "appIcon")
    @a
    private String iconUrl;
    private String insActvNotifyBtnText;
    private InstallConfig installConfig;
    private String installPermiText;
    private String installPureModeText;
    private String intent;
    private String intentPackage;
    private String nextInstallWays;
    private int noAlertTime;
    private String packageName;
    private String permissionUrl;
    private List<Permission> permissions;
    private String pkgNameEncoded;
    private int popUpAfterInstallNew;
    private String popUpAfterInstallText;
    private int popUpStyle;
    private String priorInstallWay;
    private String pureModeText;
    private String reservedPkgName;

    @a
    private String secondUrl;
    private String sha256;
    private int trafficReminder;

    @a
    private String url;
    private String versionCode;
    private String versionName;
    private String permPromptForCard = "1";
    private String permPromptForLanding = "0";
    private int channelInfoSaveLimit = -2;
    private int popNotify = 1;
    private int fullScrnNotify = 0;
    private int insActvNotifyCfg = 1;
    private int appType = 1;

    public void z(String str) {
        this.nextInstallWays = str;
    }

    public String z() {
        return this.afDlBtnText;
    }

    public void y(String str) {
        this.pkgNameEncoded = str;
    }

    public String y() {
        return this.dlBtnText;
    }

    public void x(String str) {
        this.developerName = str;
    }

    public int x() {
        return this.trafficReminder;
    }

    public void w(String str) {
        this.intentPackage = str;
    }

    public int w() {
        return this.noAlertTime;
    }

    public void v(String str) {
        this.intent = str;
    }

    public String v() {
        return this.appDesc;
    }

    public void u(String str) {
        this.insActvNotifyBtnText = str;
    }

    public int u() {
        return this.channelInfoSaveLimit;
    }

    public void t(String str) {
        this.fullScrnNotifyText = str;
    }

    public String t() {
        return this.iconUrl;
    }

    public void s(String str) {
        this.afDlBtnText = str;
    }

    public String s() {
        return this.channelInfo;
    }

    public void r(String str) {
        this.dlBtnText = str;
    }

    public String r() {
        return this.popUpAfterInstallText;
    }

    public void q(String str) {
        this.appDesc = str;
    }

    public int q() {
        return this.popUpAfterInstallNew;
    }

    public void p(String str) {
        this.iconUrl = str;
    }

    public String p() {
        String str = this.permPromptForLanding;
        return str == null ? "0" : str;
    }

    public void o(String str) {
        this.channelInfo = str;
    }

    public String o() {
        String str = this.permPromptForCard;
        return str == null ? "1" : str;
    }

    public void n(String str) {
        this.popUpAfterInstallText = str;
    }

    public InstallConfig n() {
        return this.installConfig;
    }

    public void m(String str) {
        this.permPromptForLanding = str;
    }

    public String m() {
        return TextUtils.isEmpty(this.contentBtnAction) ? this.priorInstallWay : this.contentBtnAction;
    }

    public void l(String str) {
        this.permPromptForCard = str;
    }

    public String l() {
        return this.priorInstallWay;
    }

    public void k(String str) {
        this.contentBtnAction = str;
    }

    public List<Permission> k() {
        return this.permissions;
    }

    public void j(String str) {
        this.priorInstallWay = str;
    }

    public String j() {
        return this.appName;
    }

    public void i(String str) {
        this.appName = str;
    }

    public String i() {
        return this.detailUrl;
    }

    public void h(String str) {
        this.detailUrl = str;
    }

    public void h(int i) {
        this.autoOpenAfterInstall = i;
    }

    public String h() {
        return this.permissionUrl;
    }

    public void g(String str) {
        this.permissionUrl = str;
    }

    public void g(int i) {
        this.appType = i;
    }

    public String g() {
        return this.secondUrl;
    }

    public void f(String str) {
        this.secondUrl = str;
    }

    public void f(int i) {
        this.insActvNotifyCfg = i;
    }

    public String f() {
        return this.sha256;
    }

    public void e(String str) {
        this.sha256 = str;
    }

    public void e(int i) {
        this.fullScrnNotify = i;
    }

    public long e() {
        return this.fileSize;
    }

    public void d(String str) {
        this.url = str;
    }

    public void d(int i) {
        this.popNotify = i;
    }

    public String d() {
        return this.url;
    }

    public void c(String str) {
        this.versionName = str;
    }

    public void c(int i) {
        this.trafficReminder = i;
    }

    public String c() {
        return this.versionName;
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

    public String b() {
        return this.versionCode;
    }

    public void a(List<Permission> list) {
        this.permissions = list;
    }

    public void a(String str) {
        this.packageName = str;
    }

    public void a(Integer num) {
        this.hasPermission = num;
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
        return this.packageName;
    }

    public String V() {
        return this.reservedPkgName;
    }

    public String U() {
        return this.contiBtn;
    }

    public String T() {
        return this.installPureModeText;
    }

    public String S() {
        return this.pureModeText;
    }

    public String R() {
        return this.installPermiText;
    }

    public int Q() {
        return this.popUpStyle;
    }

    public long P() {
        return this.allAreaPopDelay;
    }

    public int O() {
        return this.autoOpenAfterInstall;
    }

    public int N() {
        return this.appType;
    }

    public String M() {
        return this.actName;
    }

    public int L() {
        return this.checkSha256Flag;
    }

    public String K() {
        return this.nextInstallWays;
    }

    public String J() {
        return this.pkgNameEncoded;
    }

    public String I() {
        return this.developerName;
    }

    public Integer H() {
        return this.hasPermission;
    }

    public String G() {
        return this.intentPackage;
    }

    public void F(String str) {
        this.reservedPkgName = str;
    }

    public String F() {
        return this.intent;
    }

    public void E(String str) {
        this.contiBtn = str;
    }

    public int E() {
        return this.insActvNotifyCfg;
    }

    public void D(String str) {
        this.installPureModeText = str;
    }

    public String D() {
        return this.insActvNotifyBtnText;
    }

    public void C(String str) {
        this.pureModeText = str;
    }

    public String C() {
        return this.fullScrnNotifyText;
    }

    public void B(String str) {
        this.installPermiText = str;
    }

    public int B() {
        return this.fullScrnNotify;
    }

    public void A(String str) {
        this.actName = str;
    }

    public int A() {
        return this.popNotify;
    }
}
