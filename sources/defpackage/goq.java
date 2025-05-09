package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class goq {

    /* renamed from: a, reason: collision with root package name */
    private static ConnectivityManager f12888a;
    private static TelephonyManager b;

    public static boolean a(Context context) {
        return d() && ((b(context) && c(context)) || (!b(context) && g(context) && d(context)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0053, code lost:
    
        if (android.text.TextUtils.isEmpty(r0) == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x005d, code lost:
    
        return java.util.Locale.getDefault().getCountry();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
    
        if (r7 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
    
        if (r7 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String e(android.content.Context r8) {
        /*
            java.lang.String r0 = "content://com.huawei.appmarket.commondata/item/1"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            java.lang.String r0 = ""
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L2a java.lang.IllegalArgumentException -> L2c
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2a java.lang.IllegalArgumentException -> L2c
            if (r7 == 0) goto L27
            boolean r8 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L2a java.lang.IllegalArgumentException -> L2c
            if (r8 == 0) goto L27
            java.lang.String r8 = "homecountry"
            int r8 = r7.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L2a java.lang.IllegalArgumentException -> L2c
            java.lang.String r0 = r7.getString(r8)     // Catch: java.lang.Throwable -> L2a java.lang.IllegalArgumentException -> L2c
        L27:
            if (r7 == 0) goto L4f
            goto L4c
        L2a:
            r8 = move-exception
            goto L5e
        L2c:
            r8 = move-exception
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L2a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2a
            java.lang.String r3 = "close cursor error: "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2a
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2a
            r2.append(r8)     // Catch: java.lang.Throwable -> L2a
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L2a
            r2 = 0
            r1[r2] = r8     // Catch: java.lang.Throwable -> L2a
            java.lang.String r8 = ".NetAndSimUtil"
            com.huawei.hwlogsmodel.LogUtil.a(r8, r1)     // Catch: java.lang.Throwable -> L2a
            if (r7 == 0) goto L4f
        L4c:
            r7.close()
        L4f:
            boolean r8 = android.text.TextUtils.isEmpty(r0)
            if (r8 == 0) goto L5d
            java.util.Locale r8 = java.util.Locale.getDefault()
            java.lang.String r0 = r8.getCountry()
        L5d:
            return r0
        L5e:
            if (r7 == 0) goto L63
            r7.close()
        L63:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.goq.e(android.content.Context):java.lang.String");
    }

    public static boolean d(Context context) {
        boolean equalsIgnoreCase = "TW".equalsIgnoreCase(e(context));
        LogUtil.a(".NetAndSimUtil", "isTwNet" + equalsIgnoreCase);
        return equalsIgnoreCase;
    }

    public static boolean d() {
        String e = e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.a(".NetAndSimUtil", "isRom countryVersion is empty");
            return false;
        }
        if (e.contains("C636")) {
            LogUtil.a(".NetAndSimUtil", "isYaTaiRom countryVersion is c636");
            return true;
        }
        LogUtil.a(".NetAndSimUtil", "isYaTaiRom countryVersion is not c636");
        return false;
    }

    public static boolean b(Context context) {
        if (context.getSystemService("phone") instanceof TelephonyManager) {
            b = (TelephonyManager) context.getSystemService("phone");
        }
        int simState = b.getSimState();
        return (simState == 0 || simState == 1) ? false : true;
    }

    public static boolean c(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        b = telephonyManager;
        return telephonyManager.getSimOperator().startsWith("466");
    }

    public static String e() {
        return Build.DISPLAY;
    }

    public static boolean g(Context context) {
        if (context.getSystemService("connectivity") instanceof ConnectivityManager) {
            f12888a = (ConnectivityManager) context.getSystemService("connectivity");
        }
        NetworkInfo networkInfo = f12888a.getNetworkInfo(1);
        return networkInfo != null && networkInfo.isAvailable();
    }
}
