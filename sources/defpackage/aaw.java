package defpackage;

import android.content.Context;
import android.net.Uri;

/* loaded from: classes8.dex */
public class aaw extends aav {
    private static final Uri d = Uri.parse("content://com.huawei.android.sync.syncProvider/switchState");

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f152a = Uri.parse("content://com.huawei.android.sync.syncProvider/isSyncRecycle");
    private static final Uri b = Uri.parse("content://com.huawei.android.sync.syncProvider/setSwitchState");
    private static final Uri c = Uri.parse("content://com.huawei.android.sync.syncProvider/reportPrepare");
    private static final Uri e = Uri.parse("content://com.huawei.android.hicloud.loginProvider/login_status");

    public static boolean d(Context context) {
        try {
            return (aav.d(context, "syncFeatureSwitch") & 2) == 2;
        } catch (Exception e2) {
            abd.b("QueryHuaweiCloud", "isSupportSyncExtensionData error: " + e2.toString());
            return false;
        }
    }

    public static boolean e(Context context) {
        try {
            return aav.c(context) >= 1102;
        } catch (Exception e2) {
            abd.b("QueryHuaweiCloud", "isSupportQueryRecycleByDataType: " + e2.toString());
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ba  */
    /* JADX WARN: Type inference failed for: r10v1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean e(java.lang.String r9, android.content.Context r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Query switch status, syncType = "
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "QueryHuaweiCloud"
            defpackage.abd.c(r1, r0)
            boolean r0 = defpackage.abl.c(r10)
            r2 = 0
            if (r0 != 0) goto L20
            java.lang.String r9 = "Query switch status error: checkHiCloudValidate is false"
            defpackage.abd.b(r1, r9)
            return r2
        L20:
            r0 = 0
            android.content.ContentResolver r10 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            if (r10 != 0) goto L2d
            java.lang.String r9 = "Query switch status error: contentResolver is null"
            defpackage.abd.b(r1, r9)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            return r2
        L2d:
            android.net.Uri r4 = defpackage.aaw.d     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            android.content.ContentProviderClient r10 = r10.acquireUnstableContentProviderClient(r4)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            if (r10 != 0) goto L40
            java.lang.String r9 = "Query switch status error: cpClient is null"
            defpackage.abd.b(r1, r9)     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            if (r10 == 0) goto L3f
            r10.release()
        L3f:
            return r2
        L40:
            java.lang.String[] r7 = new java.lang.String[]{r9}     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            r5 = 0
            java.lang.String r6 = "switchState = ?"
            r8 = 0
            r3 = r10
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
        L4d:
            if (r0 == 0) goto L79
            boolean r9 = r0.moveToNext()     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            if (r9 == 0) goto L79
            java.lang.String r9 = "switchState"
            int r9 = r0.getColumnIndex(r9)     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            java.lang.String r9 = r0.getString(r9)     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            boolean r3 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            if (r3 != 0) goto L4d
            java.lang.String r3 = "true"
            boolean r9 = r9.contains(r3)     // Catch: java.lang.Exception -> L84 java.lang.Throwable -> Lb2
            if (r9 == 0) goto L4d
            if (r0 == 0) goto L72
            r0.close()
        L72:
            if (r10 == 0) goto L77
            r10.release()
        L77:
            r9 = 1
            return r9
        L79:
            if (r0 == 0) goto L7e
            r0.close()
        L7e:
            if (r10 == 0) goto L83
            r10.release()
        L83:
            return r2
        L84:
            r9 = move-exception
            goto L8b
        L86:
            r9 = move-exception
            r10 = r0
            goto Lb3
        L89:
            r9 = move-exception
            r10 = r0
        L8b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb2
            r3.<init>()     // Catch: java.lang.Throwable -> Lb2
            java.lang.String r4 = "Query switch status error: "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lb2
            java.lang.Class r9 = r9.getClass()     // Catch: java.lang.Throwable -> Lb2
            java.lang.String r9 = r9.getName()     // Catch: java.lang.Throwable -> Lb2
            r3.append(r9)     // Catch: java.lang.Throwable -> Lb2
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> Lb2
            defpackage.abd.b(r1, r9)     // Catch: java.lang.Throwable -> Lb2
            if (r0 == 0) goto Lac
            r0.close()
        Lac:
            if (r10 == 0) goto Lb1
            r10.release()
        Lb1:
            return r2
        Lb2:
            r9 = move-exception
        Lb3:
            if (r0 == 0) goto Lb8
            r0.close()
        Lb8:
            if (r10 == 0) goto Lbd
            r10.release()
        Lbd:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aaw.e(java.lang.String, android.content.Context):boolean");
    }

    public static boolean h(Context context) {
        try {
            return (aav.d(context, "syncFeatureSwitch") & 4) == 4;
        } catch (Exception e2) {
            abd.b("QueryHuaweiCloud", "isSupportSyncRequire error: " + e2.toString());
            return false;
        }
    }

    public static boolean i(Context context) {
        try {
            return aav.c(context) >= 1105;
        } catch (Exception e2) {
            abd.b("QueryHuaweiCloud", "isSupportSyncRefundWithLost: " + e2.toString());
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.lang.String r10, java.lang.String r11, android.content.Context r12) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Query support recycle status, syncType = "
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "QueryHuaweiCloud"
            defpackage.abd.c(r1, r0)
            r0 = 0
            if (r12 != 0) goto L1c
            java.lang.String r10 = "Query support recycle status error: context is null"
            defpackage.abd.b(r1, r10)
            return r0
        L1c:
            r2 = 0
            android.content.ContentResolver r3 = r12.getContentResolver()     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            if (r3 != 0) goto L29
            java.lang.String r10 = "Query switch status error: contentResolver is null"
            defpackage.abd.b(r1, r10)     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            return r0
        L29:
            android.net.Uri r4 = defpackage.aaw.f152a     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            android.content.ContentProviderClient r9 = r3.acquireUnstableContentProviderClient(r4)     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            if (r9 != 0) goto L3c
            java.lang.String r10 = "Query support recycle status error: cpClient is null"
            defpackage.abd.b(r1, r10)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            if (r9 == 0) goto L3b
            r9.release()
        L3b:
            return r0
        L3c:
            boolean r12 = e(r12)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            if (r12 == 0) goto L4f
            java.lang.String[] r7 = new java.lang.String[]{r10, r11}     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            r5 = 0
            r6 = 0
            r8 = 0
            r3 = r9
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            goto L5b
        L4f:
            java.lang.String[] r7 = new java.lang.String[]{r10}     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            r5 = 0
            r6 = 0
            r8 = 0
            r3 = r9
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
        L5b:
            r2 = r10
        L5c:
            if (r2 == 0) goto L82
            boolean r10 = r2.moveToNext()     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            if (r10 == 0) goto L82
            java.lang.String r10 = "isSyncRecycle"
            int r10 = r2.getColumnIndex(r10)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            java.lang.String r10 = r2.getString(r10)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            java.lang.String r11 = "true"
            boolean r10 = r10.contains(r11)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> Lbb
            if (r10 == 0) goto L5c
            if (r2 == 0) goto L7b
            r2.close()
        L7b:
            if (r9 == 0) goto L80
            r9.release()
        L80:
            r10 = 1
            return r10
        L82:
            if (r2 == 0) goto L87
            r2.close()
        L87:
            if (r9 == 0) goto L8c
            r9.release()
        L8c:
            return r0
        L8d:
            r10 = move-exception
            goto L94
        L8f:
            r10 = move-exception
            r9 = r2
            goto Lbc
        L92:
            r10 = move-exception
            r9 = r2
        L94:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r11.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r12 = "Query support recycle status error: "
            r11.append(r12)     // Catch: java.lang.Throwable -> Lbb
            java.lang.Class r10 = r10.getClass()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r10 = r10.getName()     // Catch: java.lang.Throwable -> Lbb
            r11.append(r10)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r10 = r11.toString()     // Catch: java.lang.Throwable -> Lbb
            defpackage.abd.b(r1, r10)     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto Lb5
            r2.close()
        Lb5:
            if (r9 == 0) goto Lba
            r9.release()
        Lba:
            return r0
        Lbb:
            r10 = move-exception
        Lbc:
            if (r2 == 0) goto Lc1
            r2.close()
        Lc1:
            if (r9 == 0) goto Lc6
            r9.release()
        Lc6:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aaw.c(java.lang.String, java.lang.String, android.content.Context):boolean");
    }

    public static Uri eX_() {
        return c;
    }
}
