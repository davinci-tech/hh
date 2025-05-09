package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.ads.dynamic.DynamicModule;

/* loaded from: classes4.dex */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4395a = "ads_KitLoadStrategy";
    private static final String b = "content://com.huawei.hwid.pps.apiprovider/check_uiengine";
    private static final int c = 30472100;
    private static final byte[] d = new byte[0];

    private static void c(Context context, String str, int i, String str2) {
        int i2;
        long j;
        int i3;
        try {
            af.b(f4395a, "start queryModule: ".concat(String.valueOf(str)));
            c.a(1, Integer.valueOf(i), null);
            if (DynamicModule.getSpHandler() != null) {
                i2 = DynamicModule.getSpHandler().getLoaderEngin2KitUpdate(str2);
            } else {
                af.d("LoaderHandler", "DynamicModule.spHandler is null");
                i2 = 60000;
            }
            af.a(f4395a, "interval: ".concat(String.valueOf(i2)));
        } catch (Throwable th) {
            c.a(5, Integer.valueOf(i), null);
            af.c(f4395a, "checkRemoteVersion error: " + th.getMessage());
            af.c(f4395a, "start call HMSLoadStrategy");
            t.a(context, str, i);
        }
        synchronized (d) {
            if (DynamicModule.getSpHandler() != null) {
                j = DynamicModule.getSpHandler().getKitloaderLastCheckTime();
            } else {
                af.d("LoaderHandler", "DynamicModule.spHandler is null");
                j = 0;
            }
            af.a(f4395a, "lastCheckTime: ".concat(String.valueOf(j)));
            if (j <= 0 || System.currentTimeMillis() - j >= i2) {
                long currentTimeMillis = System.currentTimeMillis();
                if (DynamicModule.getSpHandler() != null) {
                    DynamicModule.getSpHandler().setKitloaderLastCheckTime(currentTimeMillis);
                } else {
                    af.d("LoaderHandler", "DynamicModule.spHandler is null");
                }
                if (DynamicModule.getSpHandler() != null) {
                    i3 = DynamicModule.getSpHandler().getLoaderEngineInterval(str2);
                } else {
                    af.d("LoaderHandler", "DynamicModule.spHandler is null");
                    i3 = 10080000;
                }
                af.a(f4395a, "engineInterval: ".concat(String.valueOf(i3)));
                c.a(2, Integer.valueOf(i), null);
                Bundle a2 = a(context, str, i3);
                Integer valueOf = Integer.valueOf(a2.getInt("module_version"));
                if (valueOf.intValue() == 0) {
                    af.c(f4395a, "the query module:" + str + " is not existed in PPSKit.");
                    af.c(f4395a, "start call HMSLoadStrategy");
                    t.a(context, str, i);
                    return;
                }
                if (i >= valueOf.intValue()) {
                    af.b(f4395a, "no update,localVersion: " + i + " reomoteVersion: " + valueOf);
                    return;
                }
                c.a(3, Integer.valueOf(i), valueOf);
                af.b(f4395a, "Ready to cp module.");
                boolean a3 = x.a(context, a2);
                af.b(f4395a, "cp remote version by module name:" + str + " ,result:" + a3);
                if (!a3) {
                    throw new com.huawei.hms.ads.dynamicloader.j("KitLoadStrategy copy module error");
                }
                c.a(4, Integer.valueOf(i), valueOf);
                af.a(f4395a, "bundle info: moduleName:" + str + ", moduleVersion:" + valueOf);
                af.b(f4395a, "end queryModule: ".concat(String.valueOf(str)));
            }
        }
    }

    static /* synthetic */ void b(Context context, String str, int i, String str2) {
        int i2;
        long j;
        int i3;
        try {
            af.b(f4395a, "start queryModule: ".concat(String.valueOf(str)));
            c.a(1, Integer.valueOf(i), null);
            if (DynamicModule.getSpHandler() != null) {
                i2 = DynamicModule.getSpHandler().getLoaderEngin2KitUpdate(str2);
            } else {
                af.d("LoaderHandler", "DynamicModule.spHandler is null");
                i2 = 60000;
            }
            af.a(f4395a, "interval: ".concat(String.valueOf(i2)));
        } catch (Throwable th) {
            c.a(5, Integer.valueOf(i), null);
            af.c(f4395a, "checkRemoteVersion error: " + th.getMessage());
            af.c(f4395a, "start call HMSLoadStrategy");
            t.a(context, str, i);
        }
        synchronized (d) {
            if (DynamicModule.getSpHandler() != null) {
                j = DynamicModule.getSpHandler().getKitloaderLastCheckTime();
            } else {
                af.d("LoaderHandler", "DynamicModule.spHandler is null");
                j = 0;
            }
            af.a(f4395a, "lastCheckTime: ".concat(String.valueOf(j)));
            if (j <= 0 || System.currentTimeMillis() - j >= i2) {
                long currentTimeMillis = System.currentTimeMillis();
                if (DynamicModule.getSpHandler() != null) {
                    DynamicModule.getSpHandler().setKitloaderLastCheckTime(currentTimeMillis);
                } else {
                    af.d("LoaderHandler", "DynamicModule.spHandler is null");
                }
                if (DynamicModule.getSpHandler() != null) {
                    i3 = DynamicModule.getSpHandler().getLoaderEngineInterval(str2);
                } else {
                    af.d("LoaderHandler", "DynamicModule.spHandler is null");
                    i3 = 10080000;
                }
                af.a(f4395a, "engineInterval: ".concat(String.valueOf(i3)));
                c.a(2, Integer.valueOf(i), null);
                Bundle a2 = a(context, str, i3);
                Integer valueOf = Integer.valueOf(a2.getInt("module_version"));
                if (valueOf.intValue() == 0) {
                    af.c(f4395a, "the query module:" + str + " is not existed in PPSKit.");
                    af.c(f4395a, "start call HMSLoadStrategy");
                    t.a(context, str, i);
                    return;
                }
                if (i >= valueOf.intValue()) {
                    af.b(f4395a, "no update,localVersion: " + i + " reomoteVersion: " + valueOf);
                    return;
                }
                c.a(3, Integer.valueOf(i), valueOf);
                af.b(f4395a, "Ready to cp module.");
                boolean a3 = x.a(context, a2);
                af.b(f4395a, "cp remote version by module name:" + str + " ,result:" + a3);
                if (!a3) {
                    throw new com.huawei.hms.ads.dynamicloader.j("KitLoadStrategy copy module error");
                }
                c.a(4, Integer.valueOf(i), valueOf);
                af.a(f4395a, "bundle info: moduleName:" + str + ", moduleVersion:" + valueOf);
                af.b(f4395a, "end queryModule: ".concat(String.valueOf(str)));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.huawei.hms.ads.uiengineloader.v$1] */
    public static void a(final Context context, final String str, final int i, final String str2) {
        new Thread() { // from class: com.huawei.hms.ads.uiengineloader.v.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                if (e.a(context) < v.c) {
                    af.b(v.f4395a, "PPSKit is below need version");
                    t.a(context, str, i);
                    return;
                }
                String str3 = str2;
                if (DynamicModule.getSpHandler() == null) {
                    af.d("LoaderHandler", "DynamicModule.spHandler is null");
                } else if (DynamicModule.getSpHandler().getLoaderEngineUpdate(str3)) {
                    v.b(context, str, i, str2);
                    return;
                }
                af.b(v.f4395a, "engineUpdate is close");
                t.a(context, str, i);
            }
        }.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0049 A[Catch: Exception -> 0x00bf, TryCatch #0 {Exception -> 0x00bf, blocks: (B:3:0x0005, B:6:0x000d, B:9:0x001e, B:11:0x0022, B:13:0x0033, B:15:0x003e, B:20:0x0049, B:22:0x0060, B:24:0x0066, B:25:0x006f, B:27:0x0079, B:29:0x00a0, B:32:0x00aa, B:33:0x00b1, B:35:0x00b2, B:36:0x00b7, B:37:0x00be), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0079 A[Catch: Exception -> 0x00bf, TryCatch #0 {Exception -> 0x00bf, blocks: (B:3:0x0005, B:6:0x000d, B:9:0x001e, B:11:0x0022, B:13:0x0033, B:15:0x003e, B:20:0x0049, B:22:0x0060, B:24:0x0066, B:25:0x006f, B:27:0x0079, B:29:0x00a0, B:32:0x00aa, B:33:0x00b1, B:35:0x00b2, B:36:0x00b7, B:37:0x00be), top: B:2:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.os.Bundle a(android.content.Context r15, java.lang.String r16, int r17) throws com.huawei.hms.ads.dynamicloader.j {
        /*
            r0 = r15
            java.lang.String r1 = "ads_KitLoadStrategy"
            java.lang.String r2 = "content://com.huawei.hwid.pps.apiprovider/check_uiengine"
            android.net.Uri r3 = android.net.Uri.parse(r2)     // Catch: java.lang.Exception -> Lbf
            if (r0 == 0) goto Lb7
            if (r3 == 0) goto Lb7
            android.content.pm.PackageManager r4 = r15.getPackageManager()     // Catch: java.lang.Exception -> Lbf
            java.lang.String r3 = r3.getAuthority()     // Catch: java.lang.Exception -> Lbf
            r5 = 0
            android.content.pm.ProviderInfo r3 = r4.resolveContentProvider(r3, r5)     // Catch: java.lang.Exception -> Lbf
            java.lang.String r6 = "HiAdTools"
            if (r3 == 0) goto Lb2
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo     // Catch: java.lang.Exception -> Lbf
            if (r3 == 0) goto Lb7
            java.lang.String r7 = r3.packageName     // Catch: java.lang.Exception -> Lbf
            java.lang.String r8 = "Target provider service's package name is : "
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch: java.lang.Exception -> Lbf
            java.lang.String r8 = r8.concat(r9)     // Catch: java.lang.Exception -> Lbf
            com.huawei.hms.ads.uiengineloader.af.b(r6, r8)     // Catch: java.lang.Exception -> Lbf
            if (r7 == 0) goto Lb7
            java.lang.String r8 = r15.getPackageName()     // Catch: java.lang.Exception -> Lbf
            int r4 = r4.checkSignatures(r8, r7)     // Catch: java.lang.Exception -> Lbf
            r8 = 1
            if (r4 == 0) goto L46
            int r3 = r3.flags     // Catch: java.lang.Exception -> Lbf
            r3 = r3 & r8
            if (r3 != r8) goto L44
            goto L46
        L44:
            r3 = r5
            goto L47
        L46:
            r3 = r8
        L47:
            if (r3 != 0) goto L77
            java.lang.String r4 = com.huawei.hms.ads.uiengineloader.aa.b(r15, r7)     // Catch: java.lang.Exception -> Lbf
            boolean r9 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> Lbf
            java.lang.String r10 = "is sign empty: "
            java.lang.String r11 = java.lang.String.valueOf(r9)     // Catch: java.lang.Exception -> Lbf
            java.lang.String r10 = r10.concat(r11)     // Catch: java.lang.Exception -> Lbf
            com.huawei.hms.ads.uiengineloader.af.b(r6, r10)     // Catch: java.lang.Exception -> Lbf
            if (r9 != 0) goto L77
            com.huawei.hms.ads.common.inter.LoaderCommonInter r3 = com.huawei.hms.ads.dynamic.DynamicModule.getCommonInter()     // Catch: java.lang.Exception -> Lbf
            if (r3 == 0) goto L6f
            com.huawei.hms.ads.common.inter.LoaderCommonInter r3 = com.huawei.hms.ads.dynamic.DynamicModule.getCommonInter()     // Catch: java.lang.Exception -> Lbf
            boolean r3 = r3.isTrustApp(r7, r4)     // Catch: java.lang.Exception -> Lbf
            goto L77
        L6f:
            java.lang.String r0 = "LoaderHandler"
            java.lang.String r2 = "DynamicModule.commonInter is null"
            com.huawei.hms.ads.uiengineloader.af.d(r0, r2)     // Catch: java.lang.Exception -> Lbf
            goto Lb7
        L77:
            if (r3 == 0) goto Lb7
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lbf
            r3.<init>()     // Catch: java.lang.Exception -> Lbf
            java.lang.String r4 = "engineInterval"
            r6 = r17
            r3.put(r4, r6)     // Catch: java.lang.Exception -> Lbf
            android.content.ContentResolver r9 = r15.getContentResolver()     // Catch: java.lang.Exception -> Lbf
            android.net.Uri r10 = android.net.Uri.parse(r2)     // Catch: java.lang.Exception -> Lbf
            java.lang.String[] r13 = new java.lang.String[r8]     // Catch: java.lang.Exception -> Lbf
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> Lbf
            r13[r5] = r0     // Catch: java.lang.Exception -> Lbf
            r11 = 0
            java.lang.String r14 = ""
            r12 = r16
            android.database.Cursor r0 = r9.query(r10, r11, r12, r13, r14)     // Catch: java.lang.Exception -> Lbf
            if (r0 == 0) goto Laa
            java.lang.String r2 = "query success."
            com.huawei.hms.ads.uiengineloader.af.b(r1, r2)     // Catch: java.lang.Exception -> Lbf
            android.os.Bundle r0 = r0.getExtras()     // Catch: java.lang.Exception -> Lbf
            return r0
        Laa:
            com.huawei.hms.ads.dynamicloader.j r0 = new com.huawei.hms.ads.dynamicloader.j     // Catch: java.lang.Exception -> Lbf
            java.lang.String r2 = "query ret is null"
            r0.<init>(r2)     // Catch: java.lang.Exception -> Lbf
            throw r0     // Catch: java.lang.Exception -> Lbf
        Lb2:
            java.lang.String r0 = "Invalid param"
            com.huawei.hms.ads.uiengineloader.af.d(r6, r0)     // Catch: java.lang.Exception -> Lbf
        Lb7:
            com.huawei.hms.ads.dynamicloader.j r0 = new com.huawei.hms.ads.dynamicloader.j     // Catch: java.lang.Exception -> Lbf
            java.lang.String r2 = "apiProvider uri is invalid"
            r0.<init>(r2)     // Catch: java.lang.Exception -> Lbf
            throw r0     // Catch: java.lang.Exception -> Lbf
        Lbf:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed to call checkNewModule: "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.huawei.hms.ads.uiengineloader.af.c(r1, r0)
            com.huawei.hms.ads.dynamicloader.j r0 = new com.huawei.hms.ads.dynamicloader.j
            java.lang.String r1 = "call PPSKit checkNewModule error"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengineloader.v.a(android.content.Context, java.lang.String, int):android.os.Bundle");
    }
}
