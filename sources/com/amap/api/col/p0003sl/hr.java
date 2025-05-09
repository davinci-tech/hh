package com.amap.api.col.p0003sl;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Map;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes2.dex */
public final class hr {
    private static String A = "";
    private static String B = "";
    private static boolean C = false;
    private static boolean D = false;
    private static String E = "";
    private static boolean F = false;
    private static boolean G = false;
    private static long H = 0;
    private static int I = 0;
    private static String J = null;
    private static String K = "";
    private static boolean L = true;
    private static boolean M = false;
    private static String N = "";
    private static boolean O = false;
    private static int P = -1;
    private static boolean Q = false;
    private static int R = -1;
    private static boolean S = false;
    private static volatile b T = null;

    /* renamed from: a, reason: collision with root package name */
    static String f1143a = "";
    static String b = "";
    static volatile boolean c = true;
    public static boolean d = false;
    static String e = "";
    static boolean f = false;
    public static a g = null;
    static int h = -1;
    static String i = "";
    static String j = "";
    private static String k = null;
    private static boolean l = false;
    private static volatile boolean m = false;
    private static String n = "";
    private static boolean o = false;
    private static boolean p = false;
    private static boolean q = false;
    private static String r = "";
    private static String s = "";
    private static boolean t = false;
    private static boolean u = false;
    private static String v = "";
    private static boolean w = false;
    private static String x = "";
    private static boolean y = false;
    private static String z = "";

    public interface a {
        ka a(byte[] bArr, Map<String, String> map);

        String a();

        String a(Context context, String str);

        String a(String str, String str2, String str3, String str4);

        Map<String, String> b();
    }

    static /* synthetic */ boolean i() {
        p = true;
        return true;
    }

    public static void a(String str) {
        k = str;
    }

    public static String a() {
        return k;
    }

    public static String b() {
        try {
            if (!TextUtils.isEmpty(e)) {
                return e;
            }
            a aVar = g;
            return aVar == null ? "" : aVar.a();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void a(a aVar) {
        if (g == null) {
            g = aVar;
        }
    }

    public static a c() {
        return g;
    }

    public static String a(final Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (context == null) {
            return "";
        }
        String Q2 = Q(context);
        b = Q2;
        if (!TextUtils.isEmpty(Q2)) {
            return b;
        }
        if (c() == null || m) {
            return "";
        }
        m = true;
        la.a().a(new lb() { // from class: com.amap.api.col.3sl.hr.1
            @Override // com.amap.api.col.p0003sl.lb
            public final void runTask() {
                try {
                    Map<String, String> b2 = hr.g.b();
                    String a2 = hr.g.a(hr.h(context), "", "", hr.y(context));
                    if (TextUtils.isEmpty(a2)) {
                        return;
                    }
                    jt.a();
                    String a3 = hr.g.a(context, new String(jt.a(hr.g.a(a2.getBytes(), b2)).f1250a));
                    if (TextUtils.isEmpty(a3)) {
                        return;
                    }
                    hr.b = a3;
                } catch (Throwable unused) {
                }
            }
        });
        return "";
    }

    public static String b(Context context) {
        try {
            return L(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String c(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            String y2 = y(context);
            if (y2 != null && y2.length() >= 5) {
                return y2.substring(3, 5);
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int d(Context context) {
        try {
            return O(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static int e(Context context) {
        try {
            return M(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String f(Context context) {
        try {
            return K(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String C(Context context) {
        try {
            String b2 = ji.b(context, "Alvin2", "UTDID2", "");
            return TextUtils.isEmpty(b2) ? ji.b(context, "Alvin2", "UTDID", "") : b2;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String D(Context context) {
        FileInputStream fileInputStream = null;
        try {
            if (ia.a(context, "android.permission.READ_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                XmlPullParser newPullParser = Xml.newPullParser();
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    newPullParser.setInput(fileInputStream2, "utf-8");
                    boolean z2 = false;
                    for (int eventType = newPullParser.getEventType(); 1 != eventType; eventType = newPullParser.next()) {
                        if (eventType != 2) {
                            if (eventType == 3) {
                                z2 = false;
                            } else if (eventType == 4 && z2) {
                                String text = newPullParser.getText();
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable unused) {
                                }
                                return text;
                            }
                        } else if (newPullParser.getAttributeCount() > 0) {
                            int attributeCount = newPullParser.getAttributeCount();
                            for (int i2 = 0; i2 < attributeCount; i2++) {
                                String attributeValue = newPullParser.getAttributeValue(i2);
                                if ("UTDID2".equals(attributeValue) || "UTDID".equals(attributeValue)) {
                                    z2 = true;
                                }
                            }
                        }
                    }
                    fileInputStream = fileInputStream2;
                } catch (Throwable unused2) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream == null) {
                        return "";
                    }
                    fileInputStream.close();
                    return "";
                }
            }
            if (fileInputStream == null) {
                return "";
            }
        } catch (Throwable unused3) {
        }
        try {
            fileInputStream.close();
            return "";
        } catch (Throwable unused4) {
            return "";
        }
    }

    static final class c implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        private static String f1149a;
        private Context b;
        private int c;

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }

        c(Context context, int i) {
            this.b = context;
            this.c = i;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            int i;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                i = this.c;
            } finally {
                try {
                } finally {
                }
            }
            if (i == 2) {
                obtain.writeInterfaceToken(ia.c("UY29tLnVvZGlzLm9wZW5kZXZpY2UuYWlkbC5PcGVuRGV2aWNlSWRlbnRpZmllclNlcnZpY2U"));
            } else if (i == 4) {
                obtain.writeInterfaceToken(ia.c("UY29tLnNhbXN1bmcuYW5kcm9pZC5kZXZpY2VpZHNlcnZpY2UuSURldmljZUlkU2VydmljZQ"));
            } else {
                if (i != 5) {
                }
                obtain.writeInterfaceToken(ia.c("KY29tLmhleXRhcC5vcGVuaWQuSU9wZW5JRA"));
                obtain.writeString(this.b.getPackageName());
                obtain.writeString(a());
                obtain.writeString(ia.c("IT1VJRA"));
            }
            iBinder.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            String unused = hr.n = obtain2.readString();
        }

        private String a() {
            try {
                if (!TextUtils.isEmpty(f1149a)) {
                    return f1149a;
                }
                byte[] digest = MessageDigest.getInstance(ia.c("IU0hBMQ")).digest(this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 64).signatures[0].toByteArray());
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    stringBuffer.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                }
                String stringBuffer2 = stringBuffer.toString();
                if (!TextUtils.isEmpty(stringBuffer2)) {
                    f1149a = stringBuffer2;
                }
                return stringBuffer2;
            } catch (Throwable unused) {
                return "";
            }
        }
    }

    private static String a(Context context, int i2) {
        try {
            Intent intent = new Intent();
            if (i2 == 2) {
                intent.setAction(ia.c("WY29tLnVvZGlzLm9wZW5kZXZpY2UuT1BFTklEU19TRVJWSUNF"));
                intent.setPackage(ia.c("UY29tLmh1YXdlaS5od2lk"));
            } else if (i2 == 4) {
                intent.setClassName(ia.c("WY29tLnNhbXN1bmcuYW5kcm9pZC5kZXZpY2VpZHNlcnZpY2U"), ia.c("QY29tLnNhbXN1bmcuYW5kcm9pZC5kZXZpY2VpZHNlcnZpY2UuRGV2aWNlSWRTZXJ2aWNl"));
            } else if (i2 == 5) {
                intent.setClassName(ia.c("YY29tLmhleXRhcC5vcGVuaWQ"), ia.c("SY29tLmhleXRhcC5vcGVuaWQuSWRlbnRpZnlTZXJ2aWNl"));
                intent.setAction(ia.c("EYWN0aW9uLmNvbS5oZXl0YXAub3BlbmlkLk9QRU5fSURfU0VSVklDRQ"));
            } else {
                o = true;
                return n;
            }
            c cVar = new c(context, i2);
            if (context.bindService(intent, cVar, 1)) {
                int i3 = 0;
                while (i3 < 100 && TextUtils.isEmpty(n)) {
                    i3++;
                    Thread.sleep(15L);
                }
                context.unbindService(cVar);
            }
            return n;
        } catch (Throwable th) {
            is.a(th, "oa", String.valueOf(i2));
            o = true;
            return n;
        }
    }

    private static String E(Context context) {
        try {
            Class<?> cls = Class.forName(ia.c("WY29tLmFuZHJvaWQuaWQuaW1wbC5JZFByb3ZpZGVySW1wbA"));
            Object invoke = cls.getMethod(ia.c("MZ2V0T0FJRA"), Context.class).invoke(cls.newInstance(), context);
            if (invoke != null) {
                String str = (String) invoke;
                n = str;
                return str;
            }
        } catch (Throwable th) {
            is.a(th, "oa", "xm");
            o = true;
        }
        return n;
    }

    private static String F(Context context) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(ia.c("QY29udGVudDovL2NvbS52aXZvLnZtcy5JZFByb3ZpZGVyL0lkZW50aWZpZXJJZC9PQUlE")), null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    int columnCount = query.getColumnCount();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= columnCount) {
                            break;
                        }
                        if (ia.c("IdmFsdWU").equals(query.getColumnName(i2))) {
                            n = query.getString(i2);
                            break;
                        }
                        i2++;
                    }
                }
                query.close();
            }
        } catch (Throwable th) {
            o = true;
            is.a(th, "oa", "vivo");
        }
        return n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String G(Context context) {
        if (ia.c("IeGlhb21p").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("IeGlhb21p").equalsIgnoreCase(Build.BRAND) || ia.c("IUkVETUk=").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("IUkVETUk=").equalsIgnoreCase(Build.BRAND)) {
            return E(context);
        }
        if (ia.c("Idml2bw").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("Idml2bw").equalsIgnoreCase(Build.BRAND)) {
            return F(context);
        }
        if (ia.c("IaHVhd2Vp").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("IaHVhd2Vp").equalsIgnoreCase(Build.BRAND) || ia.c("ISE9OT1I=").equalsIgnoreCase(Build.MANUFACTURER)) {
            return a(context, 2);
        }
        if (ia.c("Mc2Ftc3VuZw").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("Mc2Ftc3VuZw").equalsIgnoreCase(Build.BRAND)) {
            return a(context, 4);
        }
        if (ia.c("IT1BQTw").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("IT1BQTw").equalsIgnoreCase(Build.BRAND) || ia.c("MT25lUGx1cw").equalsIgnoreCase(Build.MANUFACTURER) || ia.c("MT25lUGx1cw").equalsIgnoreCase(Build.BRAND) || ia.c("IUkVBTE1F").equalsIgnoreCase(Build.BRAND)) {
            return a(context, 5);
        }
        o = true;
        return n;
    }

    public static String g(final Context context) {
        if (o) {
            return "";
        }
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        if (p) {
            return n;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.hr.2
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    hr.G(context);
                    hr.i();
                }
            });
            return n;
        }
        p = true;
        return G(context);
    }

    public static String h(Context context) {
        String str;
        if (q) {
            String str2 = f1143a;
            return str2 == null ? "" : str2;
        }
        try {
            str = f1143a;
        } catch (Throwable unused) {
        }
        if (str != null && !"".equals(str)) {
            return f1143a;
        }
        if (c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFVFRJTkdT"))) {
            f1143a = Settings.System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        if (!TextUtils.isEmpty(f1143a)) {
            q = true;
            return f1143a;
        }
        try {
            String C2 = C(context);
            f1143a = C2;
            if (!TextUtils.isEmpty(C2)) {
                q = true;
                return f1143a;
            }
        } catch (Throwable unused2) {
        }
        try {
            f1143a = D(context);
            q = true;
        } catch (Throwable unused3) {
        }
        String str3 = f1143a;
        return str3 == null ? "" : str3;
    }

    public static String i(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (!TextUtils.isEmpty(s)) {
            return s;
        }
        if (t) {
            return s;
        }
        if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return "";
        }
        try {
            String str = (String) ia.a(Build.class, "MZ2V0U2VyaWFs", (Class<?>[]) new Class[0]).invoke(Build.class, new Object[0]);
            t = true;
            return str;
        } catch (Throwable unused) {
            String str2 = s;
            return str2 == null ? "" : str2;
        }
    }

    public static String j(Context context) {
        if (!TextUtils.isEmpty(r)) {
            return r;
        }
        if (u) {
            return r;
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), ia.c(new String(io.a(13))));
            r = string;
            u = true;
            return string == null ? "" : string;
        } catch (Throwable unused) {
            return r;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String k(Context context) {
        if (Build.VERSION.SDK_INT < 30 || context.getApplicationInfo().targetSdkVersion < 30) {
            try {
                String str = v;
                if (str != null && !"".equals(str)) {
                    return v;
                }
                if (w) {
                    return v;
                }
                if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                    return v;
                }
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                v = wifiManager.getConnectionInfo().getMacAddress();
                if (ia.c("YMDI6MDA6MDA6MDA6MDA6MDA").equals(v) || ia.c("YMDA6MDA6MDA6MDA6MDA6MDA").equals(v)) {
                    v = H(context);
                }
                w = true;
            } catch (Throwable unused) {
            }
        }
        return v;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e A[Catch: Exception -> 0x007d, TryCatch #0 {Exception -> 0x007d, blocks: (B:3:0x0002, B:4:0x000e, B:6:0x0014, B:9:0x0026, B:11:0x002c, B:18:0x003e, B:21:0x0048, B:23:0x005a, B:25:0x005f, B:28:0x006a, B:30:0x0070, B:31:0x0078, B:34:0x0037), top: B:2:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String H(android.content.Context r7) {
        /*
            java.lang.String r0 = ""
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.lang.Exception -> L7d
            java.util.ArrayList r1 = java.util.Collections.list(r1)     // Catch: java.lang.Exception -> L7d
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L7d
        Le:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Exception -> L7d
            if (r2 == 0) goto L7d
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Exception -> L7d
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2     // Catch: java.lang.Exception -> L7d
            java.lang.String r3 = r2.getName()     // Catch: java.lang.Exception -> L7d
            java.lang.String r4 = "wlan0"
            boolean r3 = r3.equalsIgnoreCase(r4)     // Catch: java.lang.Exception -> L7d
            if (r3 == 0) goto Le
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L7d
            r3 = 30
            if (r1 < r3) goto L37
            android.content.pm.ApplicationInfo r7 = r7.getApplicationInfo()     // Catch: java.lang.Exception -> L7d
            int r7 = r7.targetSdkVersion     // Catch: java.lang.Exception -> L7d
            if (r7 >= r3) goto L35
            goto L37
        L35:
            r7 = 0
            goto L3b
        L37:
            byte[] r7 = r2.getHardwareAddress()     // Catch: java.lang.Exception -> L7d
        L3b:
            if (r7 != 0) goto L3e
            return r0
        L3e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7d
            r1.<init>()     // Catch: java.lang.Exception -> L7d
            int r2 = r7.length     // Catch: java.lang.Exception -> L7d
            r3 = 0
        L45:
            r4 = 1
            if (r3 >= r2) goto L6a
            r5 = r7[r3]     // Catch: java.lang.Exception -> L7d
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = java.lang.Integer.toHexString(r5)     // Catch: java.lang.Exception -> L7d
            java.lang.String r5 = r5.toUpperCase()     // Catch: java.lang.Exception -> L7d
            int r6 = r5.length()     // Catch: java.lang.Exception -> L7d
            if (r6 != r4) goto L5f
            java.lang.String r4 = "0"
            r1.append(r4)     // Catch: java.lang.Exception -> L7d
        L5f:
            r1.append(r5)     // Catch: java.lang.Exception -> L7d
            java.lang.String r4 = ":"
            r1.append(r4)     // Catch: java.lang.Exception -> L7d
            int r3 = r3 + 1
            goto L45
        L6a:
            int r7 = r1.length()     // Catch: java.lang.Exception -> L7d
            if (r7 <= 0) goto L78
            int r7 = r1.length()     // Catch: java.lang.Exception -> L7d
            int r7 = r7 - r4
            r1.deleteCharAt(r7)     // Catch: java.lang.Exception -> L7d
        L78:
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Exception -> L7d
            return r7
        L7d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.hr.H(android.content.Context):java.lang.String");
    }

    static String[] d() {
        return new String[]{"", ""};
    }

    static String l(Context context) {
        try {
            TelephonyManager P2 = P(context);
            if (P2 == null) {
                return "";
            }
            String networkOperator = P2.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                return networkOperator.substring(0, 3);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    static String m(Context context) {
        TelephonyManager P2;
        if (y) {
            return x;
        }
        try {
            U(context);
            P2 = P(context);
        } catch (Throwable unused) {
        }
        if (P2 == null) {
            return x;
        }
        String networkOperator = P2.getNetworkOperator();
        if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
            x = networkOperator.substring(3);
            y = true;
            return x;
        }
        y = true;
        return x;
    }

    public static int n(Context context) {
        try {
            return O(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int o(Context context) {
        try {
            return M(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static NetworkInfo p(Context context) {
        ConnectivityManager N2;
        if (c(context, ia.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) && (N2 = N(context)) != null) {
            return N2.getActiveNetworkInfo();
        }
        return null;
    }

    static String q(Context context) {
        try {
            NetworkInfo p2 = p(context);
            if (p2 == null) {
                return null;
            }
            return p2.getExtraInfo();
        } catch (Throwable unused) {
            return null;
        }
    }

    static String r(Context context) {
        String str;
        StringBuilder sb;
        try {
            str = z;
        } catch (Throwable unused) {
        }
        if (str != null && !"".equals(str)) {
            return z;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (i3 > i2) {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("*");
            sb.append(i3);
        } else {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append("*");
            sb.append(i2);
        }
        z = sb.toString();
        return z;
    }

    public static String s(Context context) {
        try {
            if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return K;
            }
            TelephonyManager P2 = P(context);
            if (P2 == null) {
                return "";
            }
            return P2.getNetworkOperatorName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            if (!TextUtils.isEmpty(i)) {
                return i;
            }
            if (D) {
                return i;
            }
            TelephonyManager P2 = P(context);
            if (h == -1) {
                Method a2 = ia.a(TelephonyManager.class, "UZ2V0UGhvbmVDb3VudA=", (Class<?>[]) new Class[0]);
                if (a2 != null) {
                    try {
                        h = ((Integer) a2.invoke(P2, new Object[0])).intValue();
                    } catch (Throwable unused) {
                    }
                }
                h = 0;
            }
            Method a3 = ia.a(TelephonyManager.class, "MZ2V0SW1laQ=", (Class<?>[]) new Class[]{Integer.TYPE});
            if (a3 == null) {
                h = 0;
                D = true;
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < h; i2++) {
                try {
                    sb.append((String) a3.invoke(P2, Integer.valueOf(i2)));
                    sb.append(str);
                } catch (Throwable unused2) {
                }
            }
            String sb2 = sb.toString();
            if (sb2.length() == 0) {
                h = 0;
                D = true;
                return "";
            }
            String substring = sb2.substring(0, sb2.length() - 1);
            i = substring;
            D = true;
            return substring;
        } catch (Throwable unused3) {
            return "";
        }
    }

    public static String u(Context context) {
        try {
            String v2 = v(context);
            try {
                if (TextUtils.isEmpty(v2)) {
                    v2 = a(context);
                }
                if (TextUtils.isEmpty(v2)) {
                    v2 = h(context);
                }
                if (TextUtils.isEmpty(v2)) {
                    v2 = g(context);
                }
                if (TextUtils.isEmpty(v2)) {
                    v2 = j(context);
                }
                return TextUtils.isEmpty(v2) ? I(context) : v2;
            } catch (Throwable unused) {
                return v2;
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    private static String I(Context context) {
        if (!TextUtils.isEmpty(E)) {
            return E;
        }
        try {
            String b2 = ji.b(context, "open_common", "a1", "");
            if (TextUtils.isEmpty(b2)) {
                E = "amap" + UUID.randomUUID().toString().replace("_", "").toLowerCase();
                SharedPreferences.Editor a2 = ji.a(context, "open_common");
                ji.a(a2, "a1", ia.b(E));
                ji.a(a2);
            } else {
                E = ia.c(b2);
            }
        } catch (Throwable unused) {
        }
        return E;
    }

    public static String v(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        String str = A;
        if (str != null && !"".equals(str)) {
            return A;
        }
        if (F) {
            return A;
        }
        if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return A;
        }
        TelephonyManager P2 = P(context);
        if (P2 == null) {
            return "";
        }
        ia.a(P2.getClass(), "QZ2V0RGV2aWNlSWQ", (Class<?>[]) new Class[0]);
        Method a2 = ia.a(P2.getClass(), "QZ2V0SW1laQ==", (Class<?>[]) new Class[0]);
        if (a2 != null) {
            A = (String) a2.invoke(P2, new Object[0]);
        }
        if (A == null) {
            A = "";
        }
        F = true;
        return A;
    }

    public static void e() {
        try {
            iq.a();
        } catch (Throwable unused) {
        }
    }

    public static String w(Context context) {
        return v(context) + "#" + a(context) + "#" + u(context);
    }

    public static String x(Context context) {
        String str;
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            str = B;
        } catch (Throwable unused) {
        }
        if (str != null && !"".equals(str)) {
            return B;
        }
        if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return B;
        }
        TelephonyManager P2 = P(context);
        if (P2 == null) {
            return "";
        }
        if (G) {
            return B;
        }
        Method a2 = ia.a(P2.getClass(), "QZ2V0TWVpZA==", (Class<?>[]) new Class[0]);
        if (a2 != null) {
            B = (String) a2.invoke(P2, new Object[0]);
        }
        if (B == null) {
            B = "";
        }
        G = true;
        return B;
    }

    public static String y(Context context) {
        try {
            return K(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static long f() {
        long j2 = H;
        if (j2 != 0) {
            return j2;
        }
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            H = ((statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1048576) + ((statFs2.getBlockCountLong() * statFs2.getBlockSizeLong()) / 1048576);
        } catch (Throwable unused) {
        }
        return H;
    }

    public static int z(Context context) {
        int i2 = I;
        if (i2 != 0) {
            return i2;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return 0;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        int i3 = ((int) (memoryInfo.totalMem / 1024)) / 1024;
        I = i3;
        return i3;
    }

    public static String g() {
        if (!TextUtils.isEmpty(J)) {
            return J;
        }
        String property = System.getProperty("os.arch");
        J = property;
        return property;
    }

    static String A(Context context) {
        try {
            return L(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean J(Context context) {
        int simState;
        TelephonyManager P2 = P(context);
        return (P2 == null || (simState = P2.getSimState()) == 0 || simState == 1) ? false : true;
    }

    private static String K(Context context) throws InvocationTargetException, IllegalAccessException {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        boolean J2 = J(context);
        if (L != J2) {
            if (J2) {
                K = "";
                M = false;
            }
            L = J2;
        }
        String str = K;
        if (str != null && !"".equals(str)) {
            return K;
        }
        if (M) {
            return K;
        }
        if (!L) {
            return "";
        }
        if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return K;
        }
        TelephonyManager P2 = P(context);
        if (P2 == null) {
            return "";
        }
        Method a2 = ia.a(P2.getClass(), "UZ2V0U3Vic2NyaWJlcklk", (Class<?>[]) new Class[0]);
        if (a2 != null) {
            K = (String) a2.invoke(P2, new Object[0]);
        }
        if (K == null) {
            K = "";
        }
        M = true;
        return K;
    }

    private static String L(Context context) {
        if (O) {
            return N;
        }
        U(context);
        if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return N;
        }
        TelephonyManager P2 = P(context);
        if (P2 == null) {
            return N;
        }
        String simOperatorName = P2.getSimOperatorName();
        N = simOperatorName;
        if (TextUtils.isEmpty(simOperatorName)) {
            N = P2.getNetworkOperatorName();
        }
        O = true;
        return N;
    }

    private static int M(Context context) {
        if (Q) {
            return P;
        }
        U(context);
        if (context == null || !c(context, ia.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF"))) {
            return P;
        }
        ConnectivityManager N2 = N(context);
        if (N2 == null) {
            return P;
        }
        NetworkInfo activeNetworkInfo = N2.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            Q = true;
            return P;
        }
        int type = activeNetworkInfo.getType();
        P = type;
        Q = true;
        return type;
    }

    private static ConnectivityManager N(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static int O(Context context) {
        if (S) {
            return R;
        }
        U(context);
        if (!c(context, ia.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return R;
        }
        TelephonyManager P2 = P(context);
        if (P2 == null) {
            return R;
        }
        int networkType = P2.getNetworkType();
        R = networkType;
        S = true;
        return networkType;
    }

    private static TelephonyManager P(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static String Q(Context context) {
        String str;
        if (!c) {
            return "";
        }
        try {
            str = R(context);
        } catch (Throwable unused) {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] bytes = ia.c("MAAAAAAAAAAAAAAAAAAAAAA").getBytes("UTF-8");
                return new String(hs.a(ia.c("HYW1hcGFkaXVhbWFwYWRpdWFtYXBhZGl1YW1hcGFkaXU").getBytes("UTF-8"), hs.b(str), bytes), "UTF-8");
            } catch (Throwable unused2) {
                c = false;
                return "";
            }
        }
        c = false;
        return "";
    }

    private static String R(Context context) {
        String str;
        try {
            str = S(context);
        } catch (Throwable unused) {
            str = "";
        }
        return !TextUtils.isEmpty(str) ? str : context == null ? "" : context.getSharedPreferences(ia.c("SU2hhcmVkUHJlZmVyZW5jZUFkaXU"), 0).getString(hv.b(ia.c("RYW1hcF9kZXZpY2VfYWRpdQ")), "");
    }

    private static String S(Context context) {
        RandomAccessFile randomAccessFile;
        ByteArrayOutputStream byteArrayOutputStream;
        String str;
        String[] split;
        if (!c(context, ia.c("EYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfRVhURVJOQUxfU1RPUkFHRQ=="))) {
            return "";
        }
        String b2 = hv.b(ia.c("LYW1hcF9kZXZpY2VfYWRpdQ"));
        String T2 = T(context);
        if (TextUtils.isEmpty(T2)) {
            return "";
        }
        File file = new File(T2 + File.separator + ia.c("KYmFja3Vwcw"), ia.c("MLmFkaXU"));
        if (file.exists() && file.canRead()) {
            if (file.length() == 0) {
                file.delete();
                return "";
            }
            ByteArrayOutputStream byteArrayOutputStream2 = null;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    byte[] bArr = new byte[1024];
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = randomAccessFile.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        } catch (Throwable unused) {
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            a(byteArrayOutputStream2);
                            a(randomAccessFile);
                            return "";
                        }
                    }
                    str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                } catch (Throwable unused2) {
                }
            } catch (Throwable unused3) {
                randomAccessFile = null;
            }
            if (!TextUtils.isEmpty(str) && str.contains(ia.c("SIw")) && (split = str.split(ia.c("SIw"))) != null && split.length == 2 && TextUtils.equals(b2, split[0])) {
                String str2 = split[1];
                a(byteArrayOutputStream);
                a(randomAccessFile);
                return str2;
            }
            a(byteArrayOutputStream);
            a(randomAccessFile);
        }
        return "";
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    private static String T(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            Class<?> cls = Class.forName(ia.c("SYW5kcm9pZC5vcy5zdG9yYWdlLlN0b3JhZ2VWb2x1bWU"));
            Method method = storageManager.getClass().getMethod(ia.c("MZ2V0Vm9sdW1lTGlzdA"), new Class[0]);
            Method method2 = cls.getMethod(ia.c("FZ2V0UGF0aA"), new Class[0]);
            Method method3 = cls.getMethod(ia.c("DaXNSZW1vdmFibGU"), new Class[0]);
            Object invoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(invoke);
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = Array.get(invoke, i2);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (!((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void h() {
        P = -1;
        Q = false;
        R = -1;
        S = false;
        N = "";
        O = false;
        x = "";
        y = false;
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private static Context f1146a;
        private static BroadcastReceiver b;
        private static ConnectivityManager c;
        private static NetworkRequest d;
        private static ConnectivityManager.NetworkCallback e;

        /* renamed from: com.amap.api.col.3sl.hr$b$1, reason: invalid class name */
        final class AnonymousClass1 extends BroadcastReceiver {
            AnonymousClass1() {
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if (ia.c("WYW5kcm9pZC5uZXQuY29ubi5DT05ORUNUSVZJVFlfQ0hBTkdF").equals(intent.getAction())) {
                    hr.h();
                }
            }
        }

        public final void a(Context context) {
            if (hr.c(context, ia.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) && context != null && c == null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                c = connectivityManager;
                if (connectivityManager != null) {
                    d = new NetworkRequest.Builder().addCapability(0).addCapability(1).build();
                    ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.amap.api.col.3sl.hr.b.2
                        @Override // android.net.ConnectivityManager.NetworkCallback
                        public final void onLost(Network network) {
                            super.onLost(network);
                            hr.h();
                        }

                        @Override // android.net.ConnectivityManager.NetworkCallback
                        public final void onAvailable(Network network) {
                            super.onAvailable(network);
                            hr.h();
                        }
                    };
                    e = networkCallback;
                    c.registerNetworkCallback(d, networkCallback);
                    f1146a = context;
                }
            }
        }
    }

    private static b U(Context context) {
        synchronized (hr.class) {
            if (T == null) {
                if (context == null) {
                    return null;
                }
                b bVar = new b();
                T = bVar;
                bVar.a(context.getApplicationContext());
            }
            return T;
        }
    }

    public static String t(Context context) {
        ConnectivityManager N2;
        NetworkInfo activeNetworkInfo;
        try {
            if (!c(context, ia.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) || (N2 = N(context)) == null || (activeNetworkInfo = N2.getActiveNetworkInfo()) == null) {
                return "";
            }
            return activeNetworkInfo.getTypeName();
        } catch (Throwable unused) {
            return "";
        }
    }
}
