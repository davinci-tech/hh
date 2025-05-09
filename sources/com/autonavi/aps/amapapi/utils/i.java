package com.autonavi.aps.amapapi.utils;

import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.view.MotionEventCompat;
import com.amap.api.col.p0003sl.hn;
import com.amap.api.col.p0003sl.hr;
import com.amap.api.col.p0003sl.hs;
import com.amap.api.col.p0003sl.ia;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.huawei.hianalytics.core.transport.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    static WifiManager f1652a;
    private static int b;
    private static String[] c;
    private static String d;

    public static float a(float f) {
        return (float) (((long) (f * 100.0d)) / 100.0d);
    }

    public static double b(double d2) {
        return ((long) (d2 * 1000000.0d)) / 1000000.0d;
    }

    public static double c(double d2) {
        return ((long) (d2 * 100.0d)) / 100.0d;
    }

    public static boolean a(com.autonavi.aps.amapapi.model.a aVar) {
        if (aVar == null || "8".equals(aVar.d()) || "5".equals(aVar.d()) || "6".equals(aVar.d())) {
            return false;
        }
        return b(aVar);
    }

    public static boolean a(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            return b(aMapLocation);
        }
        return false;
    }

    public static boolean b(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0051, code lost:
    
        if (java.lang.Integer.parseInt(r1[0]) == 0) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String[] a(android.telephony.TelephonyManager r8) {
        /*
            if (r8 == 0) goto L7
            java.lang.String r8 = r8.getNetworkOperator()
            goto L8
        L7:
            r8 = 0
        L8:
            java.lang.String r0 = "0"
            java.lang.String[] r1 = new java.lang.String[]{r0, r0}
            boolean r2 = android.text.TextUtils.isEmpty(r8)
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L17
            goto L4b
        L17:
            boolean r2 = android.text.TextUtils.isDigitsOnly(r8)
            if (r2 != 0) goto L1e
            goto L4b
        L1e:
            int r2 = r8.length()
            r5 = 4
            if (r2 > r5) goto L26
            goto L4b
        L26:
            r2 = 3
            java.lang.String r5 = r8.substring(r4, r2)
            r1[r4] = r5
            java.lang.String r5 = r8.substring(r2)
            char[] r5 = r5.toCharArray()
            r6 = r4
        L36:
            int r7 = r5.length
            if (r6 >= r7) goto L44
            char r7 = r5[r6]
            boolean r7 = java.lang.Character.isDigit(r7)
            if (r7 == 0) goto L44
            int r6 = r6 + 1
            goto L36
        L44:
            int r6 = r6 + r2
            java.lang.String r8 = r8.substring(r2, r6)
            r1[r3] = r8
        L4b:
            r8 = r1[r4]     // Catch: java.lang.Throwable -> L54
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.Throwable -> L54
            if (r8 != 0) goto L5e
            goto L5c
        L54:
            r8 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r5 = "getMccMnc"
            com.autonavi.aps.amapapi.utils.b.a(r8, r2, r5)
        L5c:
            r1[r4] = r0
        L5e:
            r8 = r1[r4]
            boolean r8 = r0.equals(r8)
            if (r8 != 0) goto L71
            r8 = r1[r3]
            boolean r8 = r0.equals(r8)
            if (r8 != 0) goto L71
            com.autonavi.aps.amapapi.utils.i.c = r1
            goto L86
        L71:
            r8 = r1[r4]
            boolean r8 = r0.equals(r8)
            if (r8 == 0) goto L86
            r8 = r1[r3]
            boolean r8 = r0.equals(r8)
            if (r8 == 0) goto L86
            java.lang.String[] r8 = com.autonavi.aps.amapapi.utils.i.c
            if (r8 == 0) goto L86
            r1 = r8
        L86:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.i.a(android.telephony.TelephonyManager):java.lang.String[]");
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static long b() {
        return SystemClock.elapsedRealtime();
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (c() < 17) {
                return d(context, "android.provider.Settings$System");
            }
            return d(context, "android.provider.Settings$Global");
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean d(Context context, String str) throws Throwable {
        return ((Integer) e.a(str, "getInt", new Object[]{context.getContentResolver(), ((String) e.a(str, "AIRPLANE_MODE_ON")).toString()}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static float a(double[] dArr) {
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static float a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        return a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()});
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        return a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), dPoint2.getLatitude(), dPoint2.getLongitude()});
    }

    public static Object a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getApplicationContext().getSystemService(str);
        } catch (Throwable th) {
            b.a(th, Utils.TAG, "getServ");
            return null;
        }
    }

    public static int c() {
        int i = b;
        if (i > 0) {
            return i;
        }
        try {
            try {
                return e.b("android.os.Build$VERSION", "SDK_INT");
            } catch (Throwable unused) {
                return 0;
            }
        } catch (Throwable unused2) {
            return Integer.parseInt(e.a("android.os.Build$VERSION", "SDK").toString());
        }
    }

    public static byte[] a(byte[] bArr) {
        return ia.b(bArr);
    }

    public static String b(Context context) {
        PackageInfo packageInfo;
        if (!TextUtils.isEmpty(b.j)) {
            return b.j;
        }
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(hn.c(context), 64);
        } catch (Throwable th) {
            b.a(th, Utils.TAG, "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(b.k)) {
                b.k = null;
            }
        } catch (Throwable th2) {
            b.a(th2, Utils.TAG, "getAppName");
        }
        StringBuilder sb = new StringBuilder();
        if (packageInfo != null) {
            CharSequence loadLabel = packageInfo.applicationInfo != null ? packageInfo.applicationInfo.loadLabel(context.getPackageManager()) : null;
            if (loadLabel != null) {
                sb.append(loadLabel.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                sb.append(packageInfo.versionName);
            }
        }
        String c2 = hn.c(context);
        if (!TextUtils.isEmpty(c2)) {
            sb.append(",");
            sb.append(c2);
        }
        if (!TextUtils.isEmpty(b.k)) {
            sb.append(",");
            sb.append(b.k);
        }
        String sb2 = sb.toString();
        b.j = sb2;
        return sb2;
    }

    public static NetworkInfo c(Context context) {
        try {
            return hr.p(context);
        } catch (Throwable th) {
            b.a(th, Utils.TAG, "getNetWorkInfo");
            return null;
        }
    }

    public static int d() {
        return new Random().nextInt(65536) - 32768;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return ia.a(jSONObject, str);
    }

    public static boolean a(String str) {
        return (TextUtils.isEmpty(str) || "00:00:00:00:00:00".equals(str) || "02:00:00:00:00:00".equals(str) || str.contains(" :")) ? false : true;
    }

    public static String e() {
        try {
            return hs.b("S128DF1572465B890OE3F7A13167KLEI".getBytes("UTF-8")).substring(20);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    public static boolean d(Context context) {
        try {
            NetworkInfo c2 = c(context);
            if (c2 != null) {
                return c2.isConnectedOrConnecting();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0030 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0033 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:4:0x001b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0021 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r1, android.telephony.TelephonyManager r2) {
        /*
            if (r2 == 0) goto L17
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()     // Catch: java.lang.Throwable -> L17
            int r1 = r1.targetSdkVersion     // Catch: java.lang.Throwable -> L17
            r0 = 29
            if (r1 >= r0) goto L17
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L17
            r0 = 30
            if (r1 >= r0) goto L17
            int r1 = r2.getNetworkType()     // Catch: java.lang.Throwable -> L17
            goto L18
        L17:
            r1 = 0
        L18:
            switch(r1) {
                case 1: goto L48;
                case 2: goto L45;
                case 3: goto L42;
                case 4: goto L3f;
                case 5: goto L3c;
                case 6: goto L39;
                case 7: goto L36;
                case 8: goto L33;
                case 9: goto L30;
                case 10: goto L2d;
                case 11: goto L2a;
                case 12: goto L27;
                case 13: goto L24;
                case 14: goto L21;
                case 15: goto L1e;
                default: goto L1b;
            }
        L1b:
            java.lang.String r1 = "UNKWN"
            return r1
        L1e:
            java.lang.String r1 = "HSPAP"
            return r1
        L21:
            java.lang.String r1 = "EHRPD"
            return r1
        L24:
            java.lang.String r1 = "LTE"
            return r1
        L27:
            java.lang.String r1 = "EVDO_B"
            return r1
        L2a:
            java.lang.String r1 = "IDEN"
            return r1
        L2d:
            java.lang.String r1 = "HSPA"
            return r1
        L30:
            java.lang.String r1 = "HSUPA"
            return r1
        L33:
            java.lang.String r1 = "HSDPA"
            return r1
        L36:
            java.lang.String r1 = "1xRTT"
            return r1
        L39:
            java.lang.String r1 = "EVDO_A"
            return r1
        L3c:
            java.lang.String r1 = "EVDO_0"
            return r1
        L3f:
            java.lang.String r1 = "CDMA"
            return r1
        L42:
            java.lang.String r1 = "UMTS"
            return r1
        L45:
            java.lang.String r1 = "EDGE"
            return r1
        L48:
            java.lang.String r1 = "GPRS"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.i.a(android.content.Context, android.telephony.TelephonyManager):java.lang.String");
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            bArr = new byte[2];
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(long r3, java.lang.String r5) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L8
            java.lang.String r5 = "yyyy-MM-dd HH:mm:ss"
        L8:
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch: java.lang.Throwable -> L15
            java.util.Locale r1 = java.util.Locale.CHINA     // Catch: java.lang.Throwable -> L15
            r0.<init>(r5, r1)     // Catch: java.lang.Throwable -> L15
            r0.applyPattern(r5)     // Catch: java.lang.Throwable -> L13
            goto L1e
        L13:
            r5 = move-exception
            goto L17
        L15:
            r5 = move-exception
            r0 = 0
        L17:
            java.lang.String r1 = "Utils"
            java.lang.String r2 = "formatUTC"
            com.autonavi.aps.amapapi.utils.b.a(r5, r1, r2)
        L1e:
            r1 = 0
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 > 0) goto L28
            long r3 = a()
        L28:
            if (r0 != 0) goto L2d
            java.lang.String r3 = "NULL"
            return r3
        L2d:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r3 = r0.format(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.i.a(long, java.lang.String):java.lang.String");
    }

    public static byte[] b(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            bArr = new byte[4];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static double a(double d2) {
        return b(d2);
    }

    public static int b(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            i |= (bArr[i2] & 255) << ((1 - i2) * 8);
        }
        return i;
    }

    public static boolean e(Context context) {
        if (context.getApplicationInfo().targetSdkVersion >= 23) {
            Application application = (Application) context;
            for (String str : com.autonavi.aps.amapapi.b.F) {
                if (e.b(application.getBaseContext(), "checkSelfPermission", str) != 0) {
                    return false;
                }
            }
        } else {
            for (String str2 : com.autonavi.aps.amapapi.b.F) {
                if (context.checkCallingOrSelfPermission(str2) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean f(Context context) {
        if (context.getApplicationInfo().targetSdkVersion < 29 || Build.VERSION.SDK_INT < 29) {
            return true;
        }
        try {
            return e.b(((Application) context).getBaseContext(), "checkSelfPermission", com.autonavi.aps.amapapi.b.G) == 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean a(Location location, int i) {
        try {
            if (location.isFromMockProvider()) {
                return true;
            }
        } catch (Throwable unused) {
        }
        try {
            Bundle extras = location.getExtras();
            if (extras != null && extras.getInt("satellites") > 0) {
                if (i != 0 || location.getAltitude() != 0.0d || location.getBearing() != 0.0f) {
                    return false;
                }
                if (location.getSpeed() != 0.0f) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor;
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String replace = "2.0.201501131131".replace(".", "");
        if (sQLiteDatabase != null) {
            try {
                if (sQLiteDatabase.isOpen()) {
                    cursor = sQLiteDatabase.query("sqlite_master", new String[]{"count(*) as c"}, "type = 'table' AND name = '" + str.trim() + replace + "'", null, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                if (cursor.getInt(0) > 0) {
                                    z = true;
                                }
                            }
                        } catch (Throwable unused) {
                            if (cursor == null) {
                                return true;
                            }
                            cursor.close();
                            return true;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return z;
                }
            } catch (Throwable unused2) {
                cursor = null;
            }
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            ArrayList<String> b2 = b(str);
            String[] split = str2.toString().split("#");
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < split.length; i3++) {
                if (split[i3].contains(",nb") || split[i3].contains(",access")) {
                    i2++;
                    if (b2.contains(split[i3])) {
                        i++;
                    }
                }
            }
            if (i * 2 >= (b2.size() + i2) * 0.618d) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> b(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("#");
            for (int i = 0; i < split.length; i++) {
                if (split[i].contains(",nb") || split[i].contains(",access")) {
                    arrayList.add(split[i]);
                }
            }
        }
        return arrayList;
    }

    public static boolean g(Context context) {
        boolean z;
        if (context == null) {
            return true;
        }
        if (f1652a == null) {
            f1652a = (WifiManager) a(context, "wifi");
        }
        if (c(context, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF")) {
            z = f1652a.isWifiEnabled();
            if (z && c() > 17) {
                try {
                    return "true".equals(String.valueOf(e.a(f1652a, "isScanAlwaysAvailable", new Object[0])));
                } catch (Throwable unused) {
                    return z;
                }
            }
        }
        b.a(new Exception("n_aws"), "OPENSDK_UTS", "iwfal_n_aws");
        z = false;
        return z ? z : z;
    }

    public static String h(Context context) {
        NetworkInfo c2 = c(context);
        if (c2 == null || !c2.isConnectedOrConnecting()) {
            return "DISCONNECTED";
        }
        int type = c2.getType();
        if (type == 1) {
            return "WIFI";
        }
        if (type == 0) {
            String subtypeName = c2.getSubtypeName();
            switch (c2.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    if (!"GSM".equalsIgnoreCase(subtypeName)) {
                        if (!"TD-SCDMA".equalsIgnoreCase(subtypeName) && !"WCDMA".equalsIgnoreCase(subtypeName) && !"CDMA2000".equalsIgnoreCase(subtypeName)) {
                            return subtypeName;
                        }
                        return "3G";
                    }
                    return "2G";
            }
        }
        return "UNKNOWN";
    }

    public static String i(Context context) {
        String k = hr.k(context);
        if (TextUtils.isEmpty(k) || k.equals("00:00:00:00:00:00")) {
            k = h.a(context);
        }
        return TextUtils.isEmpty(k) ? "00:00:00:00:00:00" : k;
    }

    public static double c(String str) throws NumberFormatException {
        return Double.parseDouble(str);
    }

    public static float d(String str) throws NumberFormatException {
        return Float.parseFloat(str);
    }

    public static int e(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    public static int f(String str) throws NumberFormatException {
        return Integer.parseInt(str, 16);
    }

    public static byte g(String str) throws NumberFormatException {
        return Byte.parseByte(str);
    }

    public static boolean j(Context context) {
        return Build.VERSION.SDK_INT >= 28 && context.getApplicationInfo().targetSdkVersion >= 28;
    }

    public static boolean k(Context context) {
        ServiceInfo serviceInfo;
        try {
            serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, "com.amap.api.location.APSService"), 128);
        } catch (Throwable unused) {
            serviceInfo = null;
        }
        return serviceInfo != null;
    }

    public static boolean b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(str, 256);
        } catch (Throwable unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0028, code lost:
    
        if (r4 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002a, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        if (r4 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.lang.String> a(java.io.File r4) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r4 = b(r4)     // Catch: java.lang.Throwable -> L32
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L30
            java.nio.charset.Charset r3 = java.nio.charset.Charset.defaultCharset()     // Catch: java.lang.Throwable -> L30
            r2.<init>(r4, r3)     // Catch: java.lang.Throwable -> L30
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L34
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L34
        L18:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L2e
            if (r1 == 0) goto L22
            r0.add(r1)     // Catch: java.lang.Throwable -> L2e
            goto L18
        L22:
            r3.close()     // Catch: java.io.IOException -> L3a
            r2.close()     // Catch: java.io.IOException -> L3a
            if (r4 == 0) goto L47
        L2a:
            r4.close()     // Catch: java.io.IOException -> L3a
            goto L47
        L2e:
            r1 = r3
            goto L34
        L30:
            r2 = r1
            goto L34
        L32:
            r4 = r1
            r2 = r4
        L34:
            if (r1 == 0) goto L3c
            r1.close()     // Catch: java.io.IOException -> L3a
            goto L3c
        L3a:
            r4 = move-exception
            goto L44
        L3c:
            if (r2 == 0) goto L41
            r2.close()     // Catch: java.io.IOException -> L3a
        L41:
            if (r4 == 0) goto L47
            goto L2a
        L44:
            r4.printStackTrace()
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.i.a(java.io.File):java.util.List");
    }

    private static FileInputStream b(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
            return new FileInputStream(file);
        }
        throw new FileNotFoundException("File '" + file + "' does not exist");
    }

    public static void a(File file, String str) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = c(file);
                if (str != null) {
                    fileOutputStream.write(str.getBytes());
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    private static FileOutputStream c(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                    throw new IOException("Directory '" + parentFile + "' could not be created");
                }
                file.createNewFile();
            }
        }
        return new FileOutputStream(file, false);
    }

    public static String l(Context context) {
        if (d == null) {
            d = com.autonavi.aps.amapapi.security.a.a("MD5", hn.c(context));
        }
        return d;
    }

    public static boolean m(Context context) {
        if (!o(context)) {
            if (!n(context)) {
                return false;
            }
        }
        return true;
    }

    private static boolean n(Context context) {
        return h("vivo") && p(context) && q(context);
    }

    private static boolean o(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 31 && context != null && context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                if (context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean c(Context context, String str) {
        if (context != null) {
            try {
                if (context.checkSelfPermission(ia.c(str)) == 0) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static boolean p(Context context) {
        try {
            int i = Build.VERSION.SDK_INT;
            int i2 = context.getApplicationInfo().targetSdkVersion;
            return ((i == 30) && (i2 >= 23)) || ((i == 31) && (i2 <= 30 && i2 >= 23));
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean h(String str) {
        String str2;
        String str3;
        try {
            str2 = Build.MANUFACTURER;
            str3 = Build.BRAND;
        } catch (Throwable unused) {
        }
        if (!str2.equalsIgnoreCase(str)) {
            if (!str3.toLowerCase().contains(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean q(android.content.Context r9) {
        /*
            r0 = 0
            java.lang.String r1 = r9.getPackageName()     // Catch: java.lang.Throwable -> L3f
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r9 = "content://com.vivo.permissionmanager.provider.permission/fuzzy_location_apps"
            android.net.Uri r3 = android.net.Uri.parse(r9)     // Catch: java.lang.Throwable -> L3f
            r9 = 2
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> L3f
            java.lang.String r9 = "package_name"
            r4[r0] = r9     // Catch: java.lang.Throwable -> L3f
            java.lang.String r9 = "selected_fuzzy"
            r8 = 1
            r4[r8] = r9     // Catch: java.lang.Throwable -> L3f
            java.lang.String[] r6 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = "package_name=?"
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L3f
            r1 = r0
        L27:
            if (r9 == 0) goto L41
            boolean r2 = r9.moveToNext()     // Catch: java.lang.Throwable -> L3d
            if (r2 == 0) goto L41
            java.lang.String r2 = r9.getString(r0)     // Catch: java.lang.Throwable -> L3d
            if (r2 == 0) goto L27
            int r2 = r9.getInt(r8)     // Catch: java.lang.Throwable -> L3d
            if (r2 != r8) goto L27
            r1 = r8
            goto L27
        L3d:
            r0 = r1
            goto L40
        L3f:
            r9 = 0
        L40:
            r1 = r0
        L41:
            if (r9 == 0) goto L46
            r9.close()
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.i.q(android.content.Context):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(int r1) {
        /*
            r0 = 33
            if (r1 == r0) goto L44
            switch(r1) {
                case 0: goto L35;
                case 1: goto L32;
                case 2: goto L2f;
                case 3: goto L2c;
                case 4: goto L29;
                case 5: goto L26;
                case 6: goto L23;
                case 7: goto L20;
                case 8: goto L41;
                case 9: goto L1d;
                case 10: goto L1a;
                case 11: goto L17;
                case 12: goto L14;
                case 13: goto L11;
                case 14: goto Le;
                case 15: goto Lb;
                default: goto L7;
            }
        L7:
            switch(r1) {
                case 18: goto L3e;
                case 19: goto L3b;
                case 20: goto L38;
                default: goto La;
            }
        La:
            goto L41
        Lb:
            java.lang.String r1 = "当前返回位置为模拟软件返回，请关闭模拟软件，或者在option中设置允许模拟"
            goto L46
        Le:
            java.lang.String r1 = "GPS 定位失败，由于设备当前 GPS 状态差,建议持设备到相对开阔的露天场所再次尝试"
            goto L46
        L11:
            java.lang.String r1 = "网络定位失败，请检查设备是否插入sim卡，是否开启移动网络或开启了wifi模块"
            goto L46
        L14:
            java.lang.String r1 = "缺少定位权限"
            goto L46
        L17:
            java.lang.String r1 = "错误的基站信息，请检查是否插入SIM卡"
            goto L46
        L1a:
            java.lang.String r1 = "定位服务启动失败"
            goto L46
        L1d:
            java.lang.String r1 = "初始化异常"
            goto L46
        L20:
            java.lang.String r1 = "KEY错误"
            goto L46
        L23:
            java.lang.String r1 = "定位结果错误"
            goto L46
        L26:
            java.lang.String r1 = "解析数据异常"
            goto L46
        L29:
            java.lang.String r1 = "网络连接异常"
            goto L46
        L2c:
            java.lang.String r1 = "请求参数获取出现异常"
            goto L46
        L2f:
            java.lang.String r1 = "WIFI信息不足"
            goto L46
        L32:
            java.lang.String r1 = "重要参数为空"
            goto L46
        L35:
            java.lang.String r1 = "success"
            goto L46
        L38:
            java.lang.String r1 = "模糊定位失败，具体可查看错误信息/详细信息描述"
            goto L46
        L3b:
            java.lang.String r1 = "定位失败，没有检查到SIM卡，并且关闭了WIFI开关，请打开WIFI开关或者插入SIM卡"
            goto L46
        L3e:
            java.lang.String r1 = "定位失败，飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关"
            goto L46
        L41:
            java.lang.String r1 = "其他错误"
            goto L46
        L44:
            java.lang.String r1 = "补偿定位失败，未命中缓存"
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.utils.i.a(int):java.lang.String");
    }
}
