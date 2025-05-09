package defpackage;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes7.dex */
public class stt {
    private static boolean d = true;
    private static String h;

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f17228a = new byte[0];
    private static final boolean c = b();
    private static byte[] g = new byte[2048];
    private static int b = 0;
    private static int e = 0;

    private static int d(int i) {
        int i2 = i + 1;
        if (i2 == 2048) {
            return 0;
        }
        return i2;
    }

    private static void b(byte b2) {
        int i = b;
        if (i >= 0) {
            byte[] bArr = g;
            if (i < bArr.length) {
                bArr[i] = b2;
            }
        }
        b = d(i);
        int i2 = e;
        e = i2 != 2048 ? i2 + 1 : 2048;
    }

    public static void d(String str) {
        byte[] bArr = new byte[0];
        try {
            bArr = (new SimpleDateFormat("MM-dd HH:mm:ss.SSS").format(new Date()) + " " + str + "\r\n").getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            Log.w("wallet_OverSea", "putStr() UnsupportedEncodingException");
        }
        synchronized (f17228a) {
            for (byte b2 : bArr) {
                b(b2);
            }
        }
    }

    public static String c() {
        int i = e;
        if (i > 0) {
            try {
                byte[] bArr = g;
                if (i > 2048) {
                    i = 2048;
                }
                return new String(bArr, 0, i, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                Log.w("wallet_OverSea", "toString() UnsupportedEncodingException");
            }
        }
        return "";
    }

    static boolean d() {
        boolean z = false;
        if (h == null) {
            e();
            return false;
        }
        if (d) {
            if (e() || c) {
                z = true;
            } else {
                Log.isLoggable("wallet_OverSea", 3);
            }
            d = z;
            Log.i("wallet_OverSea", "isDebugLogEnable" + d);
            return d;
        }
        Log.i("wallet_OverSea", "isDebugLogEnable" + d);
        return false;
    }

    public static boolean e() {
        if (ssz.e() == null || !OverSeaMangerUtil.c(ssz.e()).a()) {
            return false;
        }
        h = "Debug";
        return true;
    }

    private static boolean b() {
        if (e()) {
            return true;
        }
        try {
            return Log.class.getDeclaredField("HWLog").getBoolean(null);
        } catch (IllegalAccessException unused) {
            Log.e("wallet_OverSea", "getHWLog is IllegalAccessException", null);
            return false;
        } catch (IllegalArgumentException unused2) {
            Log.e("wallet_OverSea", "getHWLog is IllegalArgumentException", null);
            return false;
        } catch (NoSuchFieldException unused3) {
            Log.e("wallet_OverSea", "[getHWLog]:  can not find HwLog!");
            return false;
        }
    }

    private static String c(String str) {
        return "wallet_OverSea_" + str;
    }

    private static String d(String str, String str2, Throwable th, boolean z) {
        StringBuilder sb = new StringBuilder(256);
        if (!TextUtils.isEmpty(str2)) {
            if (e()) {
                sb.append(str2);
            } else if (z) {
                sb.append(a(str2));
            } else {
                sb.append(str2);
            }
        }
        if (th != null) {
            sb.append("    ");
            sb.append(Log.getStackTraceString(th));
        }
        if (e()) {
            return sb.toString();
        }
        return sb.toString().replaceAll("(https?|ftp|file):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", "***");
    }

    public static void b(String str, String str2, boolean z) {
        if (d() && !TextUtils.isEmpty(str2)) {
            String d2 = d(str, str2, null, z);
            String c2 = c(str);
            if (e()) {
                str.e(c2, d2);
            }
        }
    }

    public static void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String d2 = d(str, str2, null, z);
        String c2 = c(str);
        Log.i(c2, d2);
        str.e(c2, d2);
    }

    public static void b(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        String d2 = d(str, str2, th, z);
        String c2 = c(str);
        if (e()) {
            Log.e(c2, d2);
        } else {
            Log.e(c2, d(str, str2, null, z));
        }
        str.a(c2, d2);
    }

    @Deprecated
    public static String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? str : " Can not print sensitive information.";
    }
}
