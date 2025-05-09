package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.operator;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jmp;
import defpackage.jmq;
import defpackage.jmu;
import defpackage.jmv;
import defpackage.jmy;
import defpackage.jna;
import defpackage.jnc;
import defpackage.jnd;
import defpackage.jnh;
import defpackage.jni;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class HwTagCardOperator extends BaseOperator {

    /* renamed from: a, reason: collision with root package name */
    private jmq f6315a;

    public HwTagCardOperator(Context context) {
        super(context);
        this.f6315a = new jmq();
        b();
    }

    public int d() {
        this.d = this.e.b();
        jmv a2 = a();
        if (a2.e() != 0) {
            e();
            LogUtil.a("R_HwTagCardOperator", "download InstallApplet fail errorCode:", Integer.valueOf(a2.e()));
            if (a2.e() != -2) {
                return -6;
            }
            return a2.e();
        }
        jmv i = i();
        if (i.e() != 0) {
            e();
            LogUtil.a("R_HwTagCardOperator", "PrePersonalize Applet fail errorCode:", Integer.valueOf(i.e()));
            if (i.e() != -2) {
                return -7;
            }
            return i.e();
        }
        e();
        return 0;
    }

    public jmv a() {
        LogUtil.a("HwTagCardOperator", "downloadAndInstallAppletWithRetry Applet Begin.");
        jmv c = c();
        if (c.e() != 0) {
            LogUtil.a("HwTagCardOperator", "Retry downloadAndInstall Begin.");
            if (c.e() == -8) {
                LogUtil.a("HwTagCardOperator", "Retry downloadAndInstall End initEseInfoErrorCode:", Integer.valueOf(h().e()));
            }
            c = c();
        }
        if (c.e() != 0) {
            LogUtil.a("HwTagCardOperator", "Download And Install Applet Failed.");
            return c;
        }
        LogUtil.a("HwTagCardOperator", "Download And Install Applet Success.");
        return c;
    }

    public jmv c() {
        jmv jmvVar = new jmv();
        jnd downloadAndInstallApplet = this.b.downloadAndInstallApplet(new jna("HwSportHealth", this.d, "D2760000850101"));
        if (downloadAndInstallApplet.a() != 0) {
            LogUtil.a("HwTagCardOperator", "downloadAndInstall fail:", Integer.valueOf(downloadAndInstallApplet.a()));
            jmvVar.c(downloadAndInstallApplet.a());
            return jmvVar;
        }
        LogUtil.a("HwTagCardOperator", "downloadAndInstallApplet success");
        String b = downloadAndInstallApplet.b();
        List<jmu> d = downloadAndInstallApplet.d();
        if (d == null || d.isEmpty()) {
            LogUtil.a("HwTagCardOperator", "downloadAndInstallApplet apduList success");
            jmvVar.c(downloadAndInstallApplet.a());
            return jmvVar;
        }
        LogUtil.a("HwTagCardOperator", "downloadAndInstallApplet: executeCommand start");
        jmv d2 = d(d(b, "download.install.app"), d, downloadAndInstallApplet.e());
        LogUtil.a("HwTagCardOperator", "downloadAndInstallApplet: executeCommand : result:", Integer.valueOf(d2.e()));
        return d2;
    }

    public jmv i() {
        LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry Applet Begin.");
        jmv g = g();
        if (g.e() != 0) {
            LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry install Applet Begin.");
            jmv a2 = a();
            if (a2.e() != 0) {
                LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry install Applet Failed.");
                return a2;
            }
            LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry prePersonalizeWithDeleteWhenError Begin.");
            g = g();
            LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry prePersonalizeWithDeleteWhenError End.");
        }
        if (g.e() == 0) {
            LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry Applet Success:", Integer.valueOf(g.e()));
            return g;
        }
        LogUtil.a("HwTagCardOperator", "prePersonalizeWithRetry Applet Failed.");
        return g;
    }

    public jmv g() {
        jmv j = j();
        if (j.e() != 0) {
            LogUtil.a("HwTagCardOperator", "prePersonalizeWithDeleteWhenError Failed Delete it.");
            LogUtil.a("HwTagCardOperator", "prePersonalizeWithDeleteWhenError Delete Applet success:", Boolean.valueOf(f()));
            if (j.e() == -8) {
                LogUtil.a("HwTagCardOperator", "prePersonalizeWithDeleteWhenError initEseInfoErrorCode:", Integer.valueOf(h().e()));
            }
            return j;
        }
        LogUtil.a("HwTagCardOperator", "prePersonalizeWithDeleteWhenError Applet Success:", Integer.valueOf(j.e()));
        return j;
    }

    public jmv j() {
        LogUtil.a("HwTagCardOperator", " PrePersonalize Begin.");
        jmv jmvVar = new jmv();
        jnh personalizeApplet = this.b.personalizeApplet(new jmy("HwSportHealth", this.d, "D2760000850101"));
        if (personalizeApplet.a() != 0) {
            LogUtil.a("HwTagCardOperator", "PrePersonalize fail detail:", Integer.valueOf(personalizeApplet.a()));
            jmvVar.c(personalizeApplet.a());
            return jmvVar;
        }
        LogUtil.a("HwTagCardOperator", " Get prePersonalize Applet: success");
        String b = personalizeApplet.b();
        List<jmu> d = personalizeApplet.d();
        if (d == null || d.isEmpty()) {
            LogUtil.a("HwTagCardOperator", "PrePersonalize Empty mean success");
            jmvVar.c(personalizeApplet.a());
            return jmvVar;
        }
        jmv d2 = d(d(b, "pass.mod.param"), d, personalizeApplet.e());
        LogUtil.a("HwTagCardOperator", "PrePersonalize: executeCommand : result:", Integer.valueOf(d2.e()));
        return d2;
    }

    private Map<String, String> d(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("appletId", "D2760000850101");
        hashMap.put("issuerid", "HwSportHealth");
        hashMap.put("cplc", this.d);
        hashMap.put("transactionId", str);
        hashMap.put("commandId", str2);
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0069, code lost:
    
        if (r0.e() == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f() {
        /*
            r5 = this;
            jmz r0 = new jmz
            java.lang.String r1 = r5.d
            java.lang.String r2 = "D2760000850101"
            java.lang.String r3 = "HwSportHealth"
            r0.<init>(r3, r1, r2)
            com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardServer r1 = r5.b
            jne r0 = r1.deleteApplet(r0)
            int r1 = r0.a()
            java.lang.String r2 = "HwTagCardOperator"
            if (r1 == 0) goto L23
            java.lang.String r0 = "deleteApplet download fail"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            goto L6c
        L23:
            java.lang.String r1 = "deleteApplet download success"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            java.lang.String r1 = r0.b()
            java.util.List r3 = r0.d()
            if (r3 == 0) goto L6e
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L3d
            goto L6e
        L3d:
            java.lang.String r4 = "deleteApplet: executeCommand"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r4)
            java.lang.String r4 = "delete.app"
            java.util.Map r1 = r5.d(r1, r4)
            java.lang.String r0 = r0.c()
            jmv r0 = r5.d(r1, r3, r0)
            int r1 = r0.e()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "deleteApplet: executeCommand : result:"
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            int r0 = r0.e()
            if (r0 != 0) goto L6c
            goto L6e
        L6c:
            r0 = 0
            goto L6f
        L6e:
            r0 = 1
        L6f:
            java.lang.String r1 = "deleteApplet executeCommand success deleteSuccess:"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.operator.HwTagCardOperator.f():boolean");
    }

    private jmv h() {
        jmv jmvVar = new jmv();
        jnc jncVar = new jnc(this.d);
        LogUtil.a("HwTagCardOperator", "create paramQueryRequest");
        jni queryInfoInitTsmParam = this.b.queryInfoInitTsmParam(jncVar);
        if (queryInfoInitTsmParam.a() != 0) {
            LogUtil.a("HwTagCardOperator", "initEse fail detail:", Integer.valueOf(queryInfoInitTsmParam.a()));
            jmvVar.c(queryInfoInitTsmParam.a());
            return jmvVar;
        }
        jmp jmpVar = new jmp();
        jmpVar.c(this.d);
        jmpVar.d(queryInfoInitTsmParam.e());
        jmpVar.a(queryInfoInitTsmParam.c());
        int executeTsmCommand = this.f6315a.executeTsmCommand(jmpVar);
        LogUtil.a("HwTagCardOperator", "tsm return executeTsmCommand:", Integer.valueOf(executeTsmCommand));
        if (executeTsmCommand == 100000) {
            jmvVar.c(0);
        } else {
            jmvVar.c(-1);
        }
        return jmvVar;
    }
}
