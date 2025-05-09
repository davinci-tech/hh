package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.m.i0.e;
import com.huawei.agconnect.credential.obs.c;

/* loaded from: classes7.dex */
public class ke {
    public static volatile ke d = null;
    public static boolean e = false;
    public BroadcastReceiver h;
    public kb c = new kb("udid");

    /* renamed from: a, reason: collision with root package name */
    public kb f14312a = new kb("oaid");
    public kb f = new kb("vaid");
    public kb b = new kb(c.f1765a);
    public jz j = new jz();

    public final boolean a(Context context, boolean z) {
        if (this.j.a() && !z) {
            return this.j.c();
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        String aQ_ = aQ_(packageManager, "com.meizu.flyme.openidsdk");
        if (TextUtils.isEmpty(aQ_)) {
            return false;
        }
        String aR_ = aR_(packageManager, aQ_);
        if (this.j.a() && this.j.a(aR_)) {
            c("use same version cache, safeVersion : ".concat(String.valueOf(aR_)));
            return this.j.c();
        }
        this.j.c(aR_);
        boolean c = c(context);
        c("query support, result : ".concat(String.valueOf(c)));
        this.j.b(c);
        return c;
    }

    public final String b(Context context, kb kbVar) {
        String str;
        if (kbVar == null) {
            str = "getId, openId = null.";
        } else {
            if (kbVar.a()) {
                return kbVar.d;
            }
            if (a(context, true)) {
                return e(context, kbVar);
            }
            str = "getId, isSupported = false.";
        }
        c(str);
        return null;
    }

    private void e(Context context) {
        synchronized (this) {
            if (this.h != null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.meizu.flyme.openid.ACTION_OPEN_ID_CHANGE");
            e eVar = new e();
            this.h = eVar;
            context.registerReceiver(eVar, intentFilter, "com.meizu.flyme.openid.permission.OPEN_ID_CHANGE", null);
        }
    }

    public static String aR_(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            c("getAppVersion, Exception : " + e2.getMessage());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String e(android.content.Context r10, defpackage.kb r11) {
        /*
            r9 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "queryId : "
            r0.<init>(r1)
            java.lang.String r1 = r11.c
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            c(r0)
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            r0 = 0
            android.content.ContentResolver r1 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            r7 = 1
            java.lang.String[] r5 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            java.lang.String r3 = r11.c     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            r8 = 0
            r5[r8] = r3     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            r3 = 0
            r4 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L99
            if (r1 == 0) goto L77
            ki r2 = aP_(r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r0 = r2.c     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r11.e(r0)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            long r3 = r2.f14383a     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r11.e(r3)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            int r3 = r2.b     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r11.b(r3)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r3.<init>()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r4 = r11.c     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r3.append(r4)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r4 = " errorCode : "
            r3.append(r4)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            int r11 = r11.b     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r3.append(r11)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r11 = r3.toString()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            c(r11)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            int r11 = r2.b     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r11 == r2) goto L8e
            r9.e(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            boolean r11 = r9.a(r10, r8)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            if (r11 != 0) goto L8e
            boolean r10 = r9.a(r10, r7)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r11 = "not support, forceQuery isSupported: "
            goto L87
        L77:
            boolean r11 = r9.a(r10, r8)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            if (r11 == 0) goto L8e
            boolean r10 = r9.a(r10, r7)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r11 = "forceQuery isSupported : "
        L87:
            java.lang.String r10 = r11.concat(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            c(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
        L8e:
            if (r1 == 0) goto Lb9
            goto Lb4
        L91:
            r10 = move-exception
            goto Lbb
        L93:
            r10 = move-exception
            r11 = r0
            r0 = r1
            goto L9b
        L97:
            r10 = move-exception
            goto Lba
        L99:
            r10 = move-exception
            r11 = r0
        L9b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97
            java.lang.String r2 = "queryId, Exception : "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L97
            java.lang.String r10 = r10.getMessage()     // Catch: java.lang.Throwable -> L97
            r1.append(r10)     // Catch: java.lang.Throwable -> L97
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Throwable -> L97
            c(r10)     // Catch: java.lang.Throwable -> L97
            if (r0 == 0) goto Lb8
            r1 = r0
            r0 = r11
        Lb4:
            r1.close()
            goto Lb9
        Lb8:
            r0 = r11
        Lb9:
            return r0
        Lba:
            r1 = r0
        Lbb:
            if (r1 == 0) goto Lc0
            r1.close()
        Lc0:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ke.e(android.content.Context, kb):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
    
        if (r7 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005c, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
    
        if (r7 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0033, code lost:
    
        if ("0".equals(r1.c) != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(android.content.Context r8) {
        /*
            java.lang.String r0 = "querySupport version : 1.0.8"
            c(r0)
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            r0 = 0
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r8 = 1
            java.lang.String[] r5 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r3 = "supported"
            r5[r0] = r3     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r3 = 0
            r4 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r7 == 0) goto L3c
            ki r1 = aP_(r7)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            int r2 = r1.b     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r3 != r2) goto L35
            java.lang.String r2 = "0"
            java.lang.String r1 = r1.c     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            boolean r1 = r2.equals(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r1 == 0) goto L36
        L35:
            r0 = r8
        L36:
            if (r7 == 0) goto L3b
            r7.close()
        L3b:
            return r0
        L3c:
            if (r7 == 0) goto L5c
            goto L59
        L3f:
            r8 = move-exception
            goto L5d
        L41:
            r8 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "querySupport, Exception : "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L3f
            r1.append(r8)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r8 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            c(r8)     // Catch: java.lang.Throwable -> L3f
            if (r7 == 0) goto L5c
        L59:
            r7.close()
        L5c:
            return r0
        L5d:
            if (r7 == 0) goto L62
            r7.close()
        L62:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ke.c(android.content.Context):boolean");
    }

    public static void c(String str) {
        if (e) {
            Log.d("OpenIdManager", str);
        }
    }

    public static String aQ_(PackageManager packageManager, String str) {
        ProviderInfo resolveContentProvider;
        if (packageManager == null || (resolveContentProvider = packageManager.resolveContentProvider(str, 0)) == null || (resolveContentProvider.applicationInfo.flags & 1) == 0) {
            return null;
        }
        return resolveContentProvider.packageName;
    }

    public static final ke b() {
        if (d == null) {
            synchronized (ke.class) {
                if (d == null) {
                    d = new ke();
                }
            }
        }
        return d;
    }

    public static ki aP_(Cursor cursor) {
        String str;
        ki kiVar = new ki(null, 0);
        if (cursor == null) {
            str = "parseValue fail, cursor is null.";
        } else {
            if (!cursor.isClosed()) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex("value");
                if (columnIndex >= 0) {
                    kiVar.c = cursor.getString(columnIndex);
                } else {
                    c("parseValue fail, index < 0.");
                }
                int columnIndex2 = cursor.getColumnIndex("code");
                if (columnIndex2 >= 0) {
                    kiVar.b = cursor.getInt(columnIndex2);
                } else {
                    c("parseCode fail, index < 0.");
                }
                int columnIndex3 = cursor.getColumnIndex("expired");
                if (columnIndex3 >= 0) {
                    kiVar.f14383a = cursor.getLong(columnIndex3);
                } else {
                    c("parseExpired fail, index < 0.");
                }
                return kiVar;
            }
            str = "parseValue fail, cursor is closed.";
        }
        c(str);
        return kiVar;
    }
}
