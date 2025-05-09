package com.huawei.hms.push;

import android.content.Context;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes9.dex */
public class u {
    private static final String[] c = {"url", "app", "cosa", "rp"};

    /* renamed from: a, reason: collision with root package name */
    private Context f5690a;
    private o b;

    public u(Context context, o oVar) {
        this.f5690a = context;
        this.b = oVar;
    }

    public static boolean a(String str) {
        for (String str2 : c) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        if (r3 != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00c2, code lost:
    
        if (com.huawei.hms.push.e.a(r6.f5690a, r6.b.c(), r2).booleanValue() != false) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00c7 A[Catch: Exception -> 0x00e9, TryCatch #0 {Exception -> 0x00e9, blocks: (B:3:0x0007, B:12:0x00c7, B:15:0x00cd, B:17:0x00d8, B:18:0x00e3, B:20:0x00de, B:6:0x009f, B:8:0x00a7, B:29:0x0089, B:22:0x0041, B:24:0x0055, B:25:0x0060), top: B:2:0x0007, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00cd A[Catch: Exception -> 0x00e9, TryCatch #0 {Exception -> 0x00e9, blocks: (B:3:0x0007, B:12:0x00c7, B:15:0x00cd, B:17:0x00d8, B:18:0x00e3, B:20:0x00de, B:6:0x009f, B:8:0x00a7, B:29:0x0089, B:22:0x0041, B:24:0x0055, B:25:0x0060), top: B:2:0x0007, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b() {
        /*
            r6 = this;
            java.lang.String r0 = "run into launchCosaApp"
            java.lang.String r1 = "PushSelfShowLog"
            com.huawei.hms.support.log.HMSLog.i(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = "enter launchExistApp cosa, appPackageName ="
            r0.<init>(r2)     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.c()     // Catch: java.lang.Exception -> Le9
            r0.append(r2)     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = ",and msg.intentUri is "
            r0.append(r2)     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.m()     // Catch: java.lang.Exception -> Le9
            r0.append(r2)     // Catch: java.lang.Exception -> Le9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.support.log.HMSLog.i(r1, r0)     // Catch: java.lang.Exception -> Le9
            android.content.Context r0 = r6.f5690a     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.c()     // Catch: java.lang.Exception -> Le9
            android.content.Intent r0 = com.huawei.hms.push.e.b(r0, r2)     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.m()     // Catch: java.lang.Exception -> Le9
            r3 = 0
            if (r2 == 0) goto L9f
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> L88
            java.lang.String r2 = r2.m()     // Catch: java.lang.Exception -> L88
            android.content.Intent r2 = android.content.Intent.parseUri(r2, r3)     // Catch: java.lang.Exception -> L88
            r4 = 0
            r2.setSelector(r4)     // Catch: java.lang.Exception -> L88
            android.content.ClipData r4 = r2.getClipData()     // Catch: java.lang.Exception -> L88
            if (r4 != 0) goto L60
            java.lang.String r4 = "avoid intent add read permission flags"
            java.lang.String r5 = "avoid"
            android.content.ClipData r4 = android.content.ClipData.newPlainText(r4, r5)     // Catch: java.lang.Exception -> L88
            r2.setClipData(r4)     // Catch: java.lang.Exception -> L88
        L60:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L88
            java.lang.String r5 = "Intent.parseUri(msg.intentUri, 0), action:"
            r4.<init>(r5)     // Catch: java.lang.Exception -> L88
            java.lang.String r5 = r2.getAction()     // Catch: java.lang.Exception -> L88
            r4.append(r5)     // Catch: java.lang.Exception -> L88
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L88
            com.huawei.hms.support.log.HMSLog.i(r1, r4)     // Catch: java.lang.Exception -> L88
            android.content.Context r4 = r6.f5690a     // Catch: java.lang.Exception -> L88
            com.huawei.hms.push.o r5 = r6.b     // Catch: java.lang.Exception -> L88
            java.lang.String r5 = r5.c()     // Catch: java.lang.Exception -> L88
            java.lang.Boolean r4 = com.huawei.hms.push.e.a(r4, r5, r2)     // Catch: java.lang.Exception -> L88
            boolean r3 = r4.booleanValue()     // Catch: java.lang.Exception -> L88
            if (r3 == 0) goto Lc5
            goto Lc4
        L88:
            r2 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Le9
            java.lang.String r5 = "intentUri error."
            r4.<init>(r5)     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Le9
            r4.append(r2)     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.support.log.HMSLog.w(r1, r2)     // Catch: java.lang.Exception -> Le9
            goto Lc5
        L9f:
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.a()     // Catch: java.lang.Exception -> Le9
            if (r2 == 0) goto Lc5
            android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.push.o r4 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r4 = r4.a()     // Catch: java.lang.Exception -> Le9
            r2.<init>(r4)     // Catch: java.lang.Exception -> Le9
            android.content.Context r4 = r6.f5690a     // Catch: java.lang.Exception -> Le9
            com.huawei.hms.push.o r5 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r5 = r5.c()     // Catch: java.lang.Exception -> Le9
            java.lang.Boolean r4 = com.huawei.hms.push.e.a(r4, r5, r2)     // Catch: java.lang.Exception -> Le9
            boolean r4 = r4.booleanValue()     // Catch: java.lang.Exception -> Le9
            if (r4 == 0) goto Lc5
        Lc4:
            r0 = r2
        Lc5:
            if (r0 != 0) goto Lcd
            java.lang.String r0 = "launchCosaApp,intent == null"
            com.huawei.hms.support.log.HMSLog.i(r1, r0)     // Catch: java.lang.Exception -> Le9
            return
        Lcd:
            com.huawei.hms.push.o r2 = r6.b     // Catch: java.lang.Exception -> Le9
            java.lang.String r2 = r2.c()     // Catch: java.lang.Exception -> Le9
            r0.setPackage(r2)     // Catch: java.lang.Exception -> Le9
            if (r3 == 0) goto Lde
            r2 = 268435456(0x10000000, float:2.524355E-29)
            r0.addFlags(r2)     // Catch: java.lang.Exception -> Le9
            goto Le3
        Lde:
            r2 = 805437440(0x30020000, float:4.7293724E-10)
            r0.setFlags(r2)     // Catch: java.lang.Exception -> Le9
        Le3:
            android.content.Context r2 = r6.f5690a     // Catch: java.lang.Exception -> Le9
            r2.startActivity(r0)     // Catch: java.lang.Exception -> Le9
            goto Lff
        Le9:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "launch Cosa App exception."
            r2.<init>(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.huawei.hms.support.log.HMSLog.e(r1, r0)
        Lff:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.push.u.b():void");
    }

    public void c() {
        o oVar;
        HMSLog.d("PushSelfShowLog", "enter launchNotify()");
        if (this.f5690a == null || (oVar = this.b) == null) {
            HMSLog.d("PushSelfShowLog", "launchNotify  context or msg is null");
            return;
        }
        if ("app".equals(oVar.h())) {
            a();
            return;
        }
        if ("cosa".equals(this.b.h())) {
            b();
            return;
        }
        if ("rp".equals(this.b.h())) {
            HMSLog.w("PushSelfShowLog", this.b.h() + " not support rich message.");
            return;
        }
        if ("url".equals(this.b.h())) {
            HMSLog.w("PushSelfShowLog", this.b.h() + " not support URL.");
            return;
        }
        HMSLog.d("PushSelfShowLog", this.b.h() + " is not exist in hShowType");
    }

    private void a() {
        try {
            HMSLog.i("PushSelfShowLog", "enter launchApp, appPackageName =" + this.b.c());
            if (e.c(this.f5690a, this.b.c())) {
                b();
            }
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "launchApp error:" + e.toString());
        }
    }
}
