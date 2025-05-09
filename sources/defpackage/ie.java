package defpackage;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import androidx.core.app.NotificationCompat;
import com.alipay.sdk.m.a0.f;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.network.embedded.c0;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class ie {
    public static ie b = new ie();

    /* renamed from: a, reason: collision with root package name */
    public f f13326a;

    public final String q(Context context) {
        try {
            String k = k(context);
            String y = y();
            if (mq.b(k) && mq.b(y)) {
                return k + ":" + y();
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public final String s(Context context) {
        synchronized (this) {
            String androidId = this.f13326a.getAndroidId();
            if (androidId != null) {
                return androidId;
            }
            String b2 = ib.b("ANDROIDID");
            if (b2 != null) {
                return b2;
            }
            if (this.f13326a.isBackgroundRunning()) {
                return "";
            }
            try {
                b2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable unused) {
            }
            if (b2 == null) {
                b2 = "";
            }
            ib.c("ANDROIDID", b2);
            return b2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0034, code lost:
    
        if (r0.length() == 0) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String m(android.content.Context r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "SimSerial"
            java.lang.String r0 = defpackage.ib.b(r0)     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto Lb
            monitor-exit(r2)
            return r0
        Lb:
            com.alipay.sdk.m.a0.f r1 = r2.f13326a     // Catch: java.lang.Throwable -> L3f
            boolean r1 = r1.isBackgroundRunning()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L17
        L13:
            monitor-exit(r2)
            java.lang.String r3 = ""
            return r3
        L17:
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            boolean r1 = d(r3, r1)     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L20
            goto L13
        L20:
            java.lang.String r1 = "phone"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch: java.lang.Throwable -> L38
            android.telephony.TelephonyManager r3 = (android.telephony.TelephonyManager) r3     // Catch: java.lang.Throwable -> L38
            java.lang.String r0 = r3.getSimSerialNumber()     // Catch: java.lang.Throwable -> L38
            if (r0 == 0) goto L36
            if (r0 == 0) goto L38
            int r3 = r0.length()     // Catch: java.lang.Throwable -> L38
            if (r3 != 0) goto L38
        L36:
            java.lang.String r0 = ""
        L38:
            java.lang.String r3 = "SimSerial"
            defpackage.ib.c(r3, r0)     // Catch: java.lang.Throwable -> L3f
            monitor-exit(r2)
            return r0
        L3f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.m(android.content.Context):java.lang.String");
    }

    public final String n(Context context) {
        synchronized (this) {
            String b2 = ib.b("NetworkOperatorName");
            if (b2 != null) {
                return b2;
            }
            if (context != null) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        b2 = telephonyManager.getNetworkOperatorName();
                    }
                } catch (Throwable unused) {
                }
            }
            if (b2 == null || Constants.NULL.equals(b2)) {
                b2 = "";
            }
            ib.c("NetworkOperatorName", b2);
            return b2;
        }
    }

    public final String l(Context context) {
        synchronized (this) {
            String subscriberId = this.f13326a.getSubscriberId();
            if (subscriberId != null) {
                return subscriberId;
            }
            String b2 = ib.b("imsi");
            if (b2 != null) {
                return b2;
            }
            if (this.f13326a.isBackgroundRunning() || d(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            if (context != null) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        b2 = telephonyManager.getSubscriberId();
                    }
                } catch (Throwable unused) {
                }
            }
            if (b2 == null) {
                b2 = "";
            }
            ib.c("imsi", b2);
            return b2;
        }
    }

    public final String v() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new ic(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }

    private String y() {
        String b2 = ib.b(c0.h);
        if (b2 != null) {
            return b2;
        }
        if (this.f13326a.isBackgroundRunning()) {
            return "";
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements() && b2 == null) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            b2 = nextElement.getHostAddress().toString();
                            break;
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        String str = b2 != null ? b2 : "";
        ib.c(c0.h, str);
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0034, code lost:
    
        r0 = r1[1].trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x003a, code lost:
    
        r2.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String t() {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L43
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3f
            r4 = 8192(0x2000, float:1.148E-41)
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L3f
        L11:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L3a
            boolean r4 = defpackage.mq.e(r1)     // Catch: java.lang.Throwable -> L3e
            if (r4 != 0) goto L11
            java.lang.String r4 = ":"
            java.lang.String[] r1 = r1.split(r4)     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L11
            int r4 = r1.length     // Catch: java.lang.Throwable -> L3e
            r5 = 1
            if (r4 <= r5) goto L11
            r4 = 0
            r4 = r1[r4]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch: java.lang.Throwable -> L3e
            if (r4 == 0) goto L11
            r1 = r1[r5]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r0 = r1.trim()     // Catch: java.lang.Throwable -> L3e
        L3a:
            r2.close()     // Catch: java.lang.Throwable -> L4c
            goto L4c
        L3e:
            r1 = r3
        L3f:
            r7 = r2
            r2 = r1
            r1 = r7
            goto L44
        L43:
            r2 = r1
        L44:
            if (r1 == 0) goto L49
            r1.close()     // Catch: java.lang.Throwable -> L49
        L49:
            if (r2 == 0) goto L4f
            r3 = r2
        L4c:
            r3.close()     // Catch: java.lang.Throwable -> L4f
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.t():java.lang.String");
    }

    public static String s() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
                try {
                    readLine = bufferedReader.readLine();
                } catch (Throwable unused) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    if (fileReader == null) {
                        return "";
                    }
                    try {
                        fileReader.close();
                        return "";
                    } catch (Throwable unused3) {
                        return "";
                    }
                }
            } catch (Throwable unused4) {
            }
        } catch (Throwable unused5) {
            fileReader = null;
        }
        if (mq.e(readLine)) {
            try {
                bufferedReader.close();
            } catch (Throwable unused6) {
            }
            fileReader.close();
            return "";
        }
        String trim = readLine.trim();
        try {
            bufferedReader.close();
        } catch (Throwable unused7) {
        }
        try {
            fileReader.close();
        } catch (Throwable unused8) {
        }
        return trim;
    }

    public static String p() {
        StringBuilder sb = new StringBuilder("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put(SystemUtils.PRODUCT_BRAND, "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            String str2 = (String) linkedHashMap.get(str);
            String b2 = mq.b(str, "");
            sb.append((b2 == null || !b2.contains(str2)) ? '0' : '1');
        }
        return sb.toString();
    }

    public static String r() {
        char c;
        String str;
        String lowerCase;
        StringBuilder sb = new StringBuilder("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str2 : linkedHashMap.keySet()) {
            try {
                String str3 = (String) Build.class.getField(str2).get(null);
                str = (String) linkedHashMap.get(str2);
                lowerCase = str3 != null ? str3.toLowerCase() : null;
            } catch (Throwable unused) {
            }
            if (lowerCase != null && lowerCase.contains(str)) {
                c = '1';
                sb.append(c);
            }
            c = '0';
            sb.append(c);
        }
        return sb.toString();
    }

    public static String k(Context context) {
        if (d(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    return (subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11) ? "2G" : (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) ? "3G" : subtype == 13 ? "4G" : "UNKNOW";
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static String q() {
        LineNumberReader lineNumberReader;
        StringBuilder sb = new StringBuilder("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("/system/build.prop", "ro.product.name=sdk");
        linkedHashMap.put("/proc/tty/drivers", "goldfish");
        linkedHashMap.put("/proc/cpuinfo", "goldfish");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                lineNumberReader = new LineNumberReader(new InputStreamReader(new FileInputStream(str)));
                while (true) {
                    try {
                        String readLine = lineNumberReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (readLine.toLowerCase().contains((CharSequence) linkedHashMap.get(str))) {
                            c = '1';
                            break;
                        }
                    } catch (Throwable unused) {
                        sb.append('0');
                        if (lineNumberReader == null) {
                        }
                        lineNumberReader.close();
                    }
                }
                sb.append(c);
            } catch (Throwable unused2) {
                lineNumberReader = null;
            }
            try {
                lineNumberReader.close();
            } catch (Throwable unused3) {
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String o(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3f
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch: java.lang.Throwable -> L3f
            r1 = 2
            if (r3 == r1) goto L22
            r1 = 5
            if (r3 != r1) goto L20
            goto L22
        L20:
            r3 = 0
            goto L23
        L22:
            r3 = 1
        L23:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r1.<init>()     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L2d
            java.lang.String r3 = "1"
            goto L2f
        L2d:
            java.lang.String r3 = "0"
        L2f:
            r1.append(r3)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch: java.lang.Throwable -> L3f
            r1.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            return r3
        L3f:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.o(android.content.Context):java.lang.String");
    }

    public static String m() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder("00:");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public static String h(Context context) {
        long j;
        try {
            if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                return "0:0";
            }
            String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
            long j2 = 0;
            for (int i = 0; i < 5; i++) {
                try {
                    j = new File(strArr[i]).lastModified();
                } catch (Throwable unused) {
                    j = -1;
                }
                j2 = Math.max(j, j2);
            }
            return "1:" + j2;
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String k() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : "0");
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:12:0x001b
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String g(android.content.Context r3) {
        /*
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()
            int r3 = r3.targetSdkVersion
            java.lang.String r0 = ""
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L1b
            r2 = 29
            if (r1 < r2) goto Lf
            goto L1b
        Lf:
            r1 = 28
            if (r3 < r1) goto L18
            java.lang.String r3 = android.os.Build.getSerial()     // Catch: java.lang.Throwable -> L1b
            goto L1c
        L18:
            java.lang.String r3 = android.os.Build.SERIAL     // Catch: java.lang.Throwable -> L1b
            goto L1c
        L1b:
            r3 = r0
        L1c:
            if (r3 != 0) goto L1f
            goto L20
        L1f:
            r0 = r3
        L20:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.g(android.content.Context):java.lang.String");
    }

    public static String l() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String f(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String n() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis - (currentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String o() {
        String str;
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String h() {
        String str;
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String a(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String f() {
        String str;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String e(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put("vendor", sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return jSONArray.toString();
    }

    public static String j() {
        long j;
        try {
            StatFs statFs = new StatFs("/sdcard");
            j = statFs.getBlockSize() * statFs.getBlockCount();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0050 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(android.content.Context r3) {
        /*
            if (r3 == 0) goto L4d
            java.lang.String r0 = "sensor"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch: java.lang.Throwable -> L4d
            android.hardware.SensorManager r3 = (android.hardware.SensorManager) r3     // Catch: java.lang.Throwable -> L4d
            if (r3 == 0) goto L4d
            r0 = -1
            java.util.List r3 = r3.getSensorList(r0)     // Catch: java.lang.Throwable -> L4d
            if (r3 == 0) goto L4d
            int r0 = r3.size()     // Catch: java.lang.Throwable -> L4d
            if (r0 <= 0) goto L4d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4d
            r0.<init>()     // Catch: java.lang.Throwable -> L4d
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L4d
        L22:
            boolean r1 = r3.hasNext()     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L44
            java.lang.Object r1 = r3.next()     // Catch: java.lang.Throwable -> L4d
            android.hardware.Sensor r1 = (android.hardware.Sensor) r1     // Catch: java.lang.Throwable -> L4d
            java.lang.String r2 = r1.getName()     // Catch: java.lang.Throwable -> L4d
            r0.append(r2)     // Catch: java.lang.Throwable -> L4d
            int r2 = r1.getVersion()     // Catch: java.lang.Throwable -> L4d
            r0.append(r2)     // Catch: java.lang.Throwable -> L4d
            java.lang.String r1 = r1.getVendor()     // Catch: java.lang.Throwable -> L4d
            r0.append(r1)     // Catch: java.lang.Throwable -> L4d
            goto L22
        L44:
            java.lang.String r3 = r0.toString()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r3 = defpackage.mq.c(r3)     // Catch: java.lang.Throwable -> L4d
            goto L4e
        L4d:
            r3 = 0
        L4e:
            if (r3 != 0) goto L52
            java.lang.String r3 = ""
        L52:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.c(android.content.Context):java.lang.String");
    }

    public static String g() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put(NotificationCompat.CATEGORY_CALL, String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompat.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String i() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        try {
            FileReader fileReader3 = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader3, 8192);
                try {
                    r0 = bufferedReader.readLine() != null ? Integer.parseInt(r2.split("\\s+")[1]) : 0L;
                    try {
                        fileReader3.close();
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                    fileReader2 = bufferedReader;
                    fileReader = fileReader2;
                    fileReader2 = fileReader3;
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (Throwable unused3) {
                        }
                    }
                    if (fileReader != null) {
                        bufferedReader = fileReader;
                        bufferedReader.close();
                    }
                    return String.valueOf(r0);
                }
            } catch (Throwable unused4) {
            }
        } catch (Throwable unused5) {
            fileReader = null;
        }
        try {
            bufferedReader.close();
        } catch (Throwable unused6) {
        }
        return String.valueOf(r0);
    }

    public static String d(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 ? "1" : "0";
        } catch (Throwable unused) {
            return "0";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        String[] split;
        FileReader fileReader3 = null;
        try {
            fileReader2 = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader2);
                try {
                    split = bufferedReader.readLine().split(":\\s+", 2);
                } catch (Throwable unused) {
                    fileReader3 = bufferedReader;
                    fileReader = fileReader3;
                    fileReader3 = fileReader2;
                    if (fileReader3 != null) {
                        try {
                            fileReader3.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    if (fileReader == null) {
                        return "";
                    }
                    bufferedReader = fileReader;
                    try {
                        bufferedReader.close();
                        return "";
                    } catch (Throwable unused3) {
                        return "";
                    }
                }
            } catch (Throwable unused4) {
            }
        } catch (Throwable unused5) {
            fileReader = null;
        }
        if (split == null || split.length <= 1) {
            try {
                fileReader2.close();
            } catch (Throwable unused6) {
            }
            bufferedReader.close();
            return "";
        }
        String str = split[1];
        try {
            fileReader2.close();
        } catch (Throwable unused7) {
        }
        try {
            bufferedReader.close();
        } catch (Throwable unused8) {
        }
        return str;
    }

    public static String e() {
        String s = s();
        return !mq.e(s) ? s : t();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        if (r2 == null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c() {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4f
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L4f
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4f
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4d
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L4d
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L51
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L51
            r1 = 1
            r5 = r1
        L1b:
            r6 = 100
            if (r5 >= r6) goto L46
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L44
            if (r6 == 0) goto L46
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch: java.lang.Throwable -> L44
            if (r7 < 0) goto L41
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch: java.lang.Throwable -> L44
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch: java.lang.Throwable -> L44
            java.lang.String r1 = r6.substring(r5, r1)     // Catch: java.lang.Throwable -> L44
            java.lang.String r0 = r1.trim()     // Catch: java.lang.Throwable -> L44
            goto L46
        L41:
            int r5 = r5 + 1
            goto L1b
        L44:
            r1 = r4
            goto L51
        L46:
            r4.close()     // Catch: java.lang.Throwable -> L49
        L49:
            r3.close()     // Catch: java.lang.Throwable -> L5d
            goto L5d
        L4d:
            r3 = r1
            goto L51
        L4f:
            r2 = r1
            r3 = r2
        L51:
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.lang.Throwable -> L56
        L56:
            if (r3 == 0) goto L5b
            r3.close()     // Catch: java.lang.Throwable -> L5b
        L5b:
            if (r2 == 0) goto L60
        L5d:
            r2.close()     // Catch: java.lang.Throwable -> L60
        L60:
            if (r0 != 0) goto L64
            java.lang.String r0 = ""
        L64:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.c():java.lang.String");
    }

    public static String d() {
        long j;
        try {
            StatFs statFs = new StatFs("/sdcard");
            j = statFs.getBlockSize() * statFs.getAvailableBlocks();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String b() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static boolean d(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) != 0;
    }

    public static ie e(f fVar) {
        ie ieVar = b;
        ieVar.f13326a = fVar;
        return ieVar;
    }
}
