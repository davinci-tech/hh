package health.compact.a;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.usage.NetworkStats;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.gson.Gson;
import com.google.json.JsonSanitizer;
import com.huawei.android.app.LocaleHelperEx;
import com.huawei.android.deviceinfo.HwDeviceInfoEx;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwauthutil.utils.PackageManagerHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.CommonUtil$SnStatus;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class CommonUtil extends CommonUtils {

    /* renamed from: a, reason: collision with root package name */
    private static long f13109a;
    private static String b;
    private static String c;
    private static Boolean d;
    private static String e;
    private static HashMap<String, Integer> j = new HashMap<>();
    private static String g = "unknown";
    private static CommonUtil$SnStatus i = CommonUtil$SnStatus.DEFAULT;
    private static String f = "-1";
    private static BroadcastReceiver h = new BroadcastReceiver() { // from class: health.compact.a.CommonUtil.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "update receive null,deal failed,return");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "updateReceiver receive action null,deal failed,return");
                return;
            }
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "update receive, action:", action);
            String stringExtra = intent.getStringExtra("update_state");
            if (stringExtra == null) {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "updateReceiver receive state null,deal failed,return");
                return;
            }
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "update receive, state:", stringExtra);
            if (action.equals("com.huawei.health.update_commonutils_area")) {
                String unused = CommonUtil.b = stringExtra;
            } else if (action.equals("com.huawei.health.update_commonutils_LOGIN")) {
                String unused2 = CommonUtil.c = stringExtra;
            } else {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "action equals failed");
            }
        }
    };

    public static void a(Context context) {
    }

    public static boolean bb() {
        return false;
    }

    public static boolean bz() {
        return false;
    }

    private CommonUtil() {
    }

    public static Boolean y() {
        return d;
    }

    public static void e(boolean z) {
        d = Boolean.valueOf(z);
    }

    public static String w() {
        return b;
    }

    public static void ab(String str) {
        b = str;
    }

    public static String v() {
        return c;
    }

    public static void ad(String str) {
        c = str;
    }

    public static void ac(String str) {
        e = str;
    }

    public static String i() {
        return e;
    }

    public static void p(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "init context null,init failed,return");
        } else {
            ar(context);
        }
    }

    public static void i(Context context, String str) {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "sendUpdateAreaBroadcast");
        c(context, "com.huawei.health.update_commonutils_area", str);
    }

    public static void l(Context context, String str) {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "sendUpdateAreaBroadcast");
        c(context, "com.huawei.health.update_commonutils_LOGIN", str);
    }

    public static boolean a(byte[] bArr, int i2) {
        if (bArr == null || bArr.length == 0 || i2 >= bArr.length * 8) {
            return false;
        }
        if (i2 < 0) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSupport target : ", Integer.valueOf(i2));
            return false;
        }
        int i3 = bArr[i2 / 8];
        int i4 = 1 << (i2 % 8);
        return (i3 & i4) == i4;
    }

    private static void c(Context context, String str, String str2) {
        Intent intent = new Intent(str);
        intent.setPackage("com.huawei.health");
        intent.putExtra("update_state", str2);
        context.sendBroadcast(intent, SecurityConstant.b);
    }

    private static void ar(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.update_commonutils_area");
        intentFilter.addAction("com.huawei.health.update_commonutils_LOGIN");
        BroadcastManagerUtil.bFA_(context, h, intentFilter, SecurityConstant.b, null);
    }

    public static boolean bu() {
        return com.huawei.hwlogsmodel.common.LogConfig.g();
    }

    public static boolean cg() {
        return com.huawei.hwlogsmodel.common.LogConfig.h();
    }

    public static boolean bo() {
        return cg() || cc();
    }

    public static String c(String str) {
        String d2 = d(str);
        String str2 = null;
        if (TextUtils.isEmpty(d2)) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "legalPath is empty, leave filterFilePath");
            return null;
        }
        try {
            str2 = new File(d2).getCanonicalPath();
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "filterFilePath finished, safePath: ", str2);
            return str2;
        } catch (IOException e2) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "filterFilePath IOException :", e2.getMessage());
            return str2;
        }
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(String.valueOf(str.charAt(i2)))) {
                stringBuffer.append(str.charAt(i2));
            }
        }
        return stringBuffer.toString();
    }

    public static boolean u(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        Configuration configuration = context.getResources().getConfiguration();
        String country = configuration.locale.getCountry();
        String language = configuration.locale.getLanguage();
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isChineseSimplified(): country = ", country, ", language = ", language);
        if (MLAsrConstants.LAN_ZH.equalsIgnoreCase(language) && "cn".equalsIgnoreCase(country)) {
            z = true;
        }
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isChineseSimplified(): result = ", Boolean.valueOf(z));
        return z;
    }

    public static boolean ad(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isFrench(): language = ", language);
        boolean equalsIgnoreCase = "fr".equalsIgnoreCase(language);
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isFrench(): result = ", Boolean.valueOf(equalsIgnoreCase));
        return equalsIgnoreCase;
    }

    public static boolean j(Context context, String str) {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isCnAndEu() enter: ", str);
        if ("cn".equalsIgnoreCase(str)) {
            return true;
        }
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isCnAndEu null");
            return false;
        }
        String[] stringArray = context.getApplicationContext().getResources().getStringArray(R.array._2130968672_res_0x7f040060);
        if (stringArray == null || stringArray.length < 1) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInEuAccountArea() if ((countryList == null) || (countryList.length < 1))");
            return false;
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isCnAndEu() country=", str);
        for (String str2 : stringArray) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isContainCountry country is empty");
            return false;
        }
        try {
            for (String str2 : BaseApplication.getContext().getResources().getStringArray(i2)) {
                if (str.equalsIgnoreCase(str2)) {
                    return true;
                }
            }
        } catch (Resources.NotFoundException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isContainCountry resourceId NotFoundException");
        }
        return false;
    }

    public static int m(Context context, String str) {
        return b(context, str, 0);
    }

    public static int b(Context context, String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "stringToInt str is empty");
            return i2;
        }
        try {
            if (str.indexOf(".") > 0) {
                str = str.substring(0, str.indexOf("."));
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "Exception e = ", e2.getMessage());
            return i2;
        }
    }

    public static long n(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "stringToLong str is empty");
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "Exception e = ", e2.getMessage());
            return 0L;
        }
    }

    public static boolean q(Context context, String str) {
        JarFile jarFile;
        PublicKey[] t;
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifyApkSignature null");
            return false;
        }
        JarFile jarFile2 = null;
        JarFile jarFile3 = null;
        try {
            try {
                t = t(context, context.getPackageName());
            } catch (Throwable th) {
                th = th;
                jarFile = null;
            }
        } catch (IOException unused) {
        } catch (Exception unused2) {
        }
        if (t != null && t.length != 0) {
            jarFile = new JarFile(str);
            try {
                JarEntry jarEntry = jarFile.getJarEntry("classes.dex");
                Certificate[] c2 = jarEntry != null ? c(jarFile, jarEntry) : null;
                if (c2 != null && c2.length > 0) {
                    for (Certificate certificate : c2) {
                        PublicKey publicKey = certificate.getPublicKey();
                        for (PublicKey publicKey2 : t) {
                            if (publicKey.equals(publicKey2)) {
                                try {
                                    jarFile.close();
                                } catch (IOException unused3) {
                                    LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature finaLLY IOException:");
                                }
                                return true;
                            }
                        }
                    }
                }
                try {
                    jarFile.close();
                } catch (IOException unused4) {
                    LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature finaLLY IOException:");
                }
            } catch (IOException unused5) {
                jarFile3 = jarFile;
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature IOException:");
                if (jarFile3 != null) {
                    try {
                        jarFile3.close();
                    } catch (IOException unused6) {
                        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature finaLLY IOException:");
                    }
                }
                return false;
            } catch (Exception unused7) {
                jarFile2 = jarFile;
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature Exception:");
                if (jarFile2 != null) {
                    try {
                        jarFile2.close();
                    } catch (IOException unused8) {
                        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature finaLLY IOException:");
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (IOException unused9) {
                        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature finaLLY IOException:");
                    }
                }
                throw th;
            }
            return false;
        }
        Log.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "verifySignature (installedAppPubKeys == null) || (installedAppPubKeys.length == 0)");
        return false;
    }

    private static PublicKey[] t(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null || packageInfo.versionName == null) {
                return null;
            }
            return bFN_(packageInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        } catch (Exception unused2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getInstalledAppPublicKey Exception:");
            return null;
        }
    }

    private static PublicKey[] bFN_(PackageInfo packageInfo) {
        PublicKey[] publicKeyArr;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = null;
            }
        } catch (CertificateException e2) {
            e = e2;
            publicKeyArr = null;
        } catch (Exception unused) {
            publicKeyArr = null;
        }
        if (packageInfo.signatures != null && packageInfo.signatures.length != 0) {
            publicKeyArr = new PublicKey[packageInfo.signatures.length];
            try {
                Signature[] signatureArr = packageInfo.signatures;
                int length = signatureArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    byte[] byteArray = signatureArr[i2].toByteArray();
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    byteArrayInputStream = new ByteArrayInputStream(byteArray);
                    try {
                        publicKeyArr[i3] = ((X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream)).getPublicKey();
                        i2++;
                        i3++;
                        byteArrayInputStream2 = byteArrayInputStream;
                    } catch (CertificateException e3) {
                        e = e3;
                        byteArrayInputStream2 = byteArrayInputStream;
                        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getPublicKey CertificateException:", e.getMessage());
                        IoUtils.e(byteArrayInputStream2);
                        return publicKeyArr;
                    } catch (Exception unused2) {
                        byteArrayInputStream2 = byteArrayInputStream;
                        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getPublicKey Exception:");
                        IoUtils.e(byteArrayInputStream2);
                        return publicKeyArr;
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.e(byteArrayInputStream);
                        throw th;
                    }
                }
            } catch (CertificateException e4) {
                e = e4;
            } catch (Exception unused3) {
            }
            IoUtils.e(byteArrayInputStream2);
            return publicKeyArr;
        }
        IoUtils.e((Closeable) null);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.security.cert.Certificate[] c(java.util.jar.JarFile r8, java.util.jar.JarEntry r9) {
        /*
            java.lang.String r0 = "CommonUtil"
            java.lang.String r1 = "loadCertificates Exception:"
            r2 = 0
            r3 = 2048(0x800, float:2.87E-42)
            r4 = 0
            byte[] r5 = new byte[r3]     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            java.io.InputStream r8 = r8.getInputStream(r9)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
        Lf:
            int r6 = r8.read(r5, r2, r3)     // Catch: java.io.IOException -> L2d java.lang.Throwable -> L56
            r7 = -1
            if (r6 != r7) goto Lf
            java.security.cert.Certificate[] r9 = r9.getCertificates()     // Catch: java.io.IOException -> L2d java.lang.Throwable -> L56
            if (r8 == 0) goto L2c
            r8.close()     // Catch: java.io.IOException -> L20
            goto L2c
        L20:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()
            java.lang.Object[] r8 = new java.lang.Object[]{r1, r8}
            health.compact.a.LogUtil.e(r0, r8)
        L2c:
            return r9
        L2d:
            r9 = move-exception
            goto L34
        L2f:
            r8 = move-exception
            goto L59
        L31:
            r8 = move-exception
            r9 = r8
            r8 = r4
        L34:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L56
            r3[r2] = r1     // Catch: java.lang.Throwable -> L56
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L56
            r2 = 1
            r3[r2] = r9     // Catch: java.lang.Throwable -> L56
            health.compact.a.LogUtil.e(r0, r3)     // Catch: java.lang.Throwable -> L56
            if (r8 == 0) goto L55
            r8.close()     // Catch: java.io.IOException -> L49
            goto L55
        L49:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()
            java.lang.Object[] r8 = new java.lang.Object[]{r1, r8}
            health.compact.a.LogUtil.e(r0, r8)
        L55:
            return r4
        L56:
            r9 = move-exception
            r4 = r8
            r8 = r9
        L59:
            if (r4 == 0) goto L6b
            r4.close()     // Catch: java.io.IOException -> L5f
            goto L6b
        L5f:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            java.lang.Object[] r9 = new java.lang.Object[]{r1, r9}
            health.compact.a.LogUtil.e(r0, r9)
        L6b:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.CommonUtil.c(java.util.jar.JarFile, java.util.jar.JarEntry):java.security.cert.Certificate[]");
    }

    public static long y(String str) {
        try {
            return Long.parseLong(str, 16);
        } catch (NumberFormatException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "parseLongByRadix exception");
            return -1L;
        }
    }

    public static int w(String str) {
        return a(str, 16);
    }

    public static int a(String str, int i2) {
        try {
            return Integer.parseInt(str, i2);
        } catch (NumberFormatException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "parseIntByRadix exception, input is : ", str);
            return -1;
        }
    }

    public static int d(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getAppVersion null");
            return 0;
        }
        return o(context.getPackageName());
    }

    public static int o(String str) {
        int i2;
        try {
            i2 = BaseApplication.getContext().getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getAppVersion() Exception=", e2.getMessage());
            i2 = 1;
        }
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getAppVersion() return=", Integer.valueOf(i2));
        return i2;
    }

    public static boolean aa(Context context) {
        return NetworkUtil.j();
    }

    public static void q(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "goToNetWorkSettingPage null");
        } else {
            context.startActivity(new Intent("android.settings.SETTINGS"));
        }
    }

    public static String c(Context context) {
        return "and_health_" + e(context);
    }

    public static String e(Context context) {
        return EnvironmentUtils.a();
    }

    public static int d(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        int i2 = 0;
        for (int i3 = 0; i3 < min; i3++) {
            i2 = split[i3].length() - split2[i3].length();
            if (i2 != 0 || (i2 = split[i3].compareTo(split2[i3])) != 0) {
                break;
            }
        }
        return i2 != 0 ? i2 : split.length - split2.length;
    }

    public static int h(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            int phoneType = ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getDeviceType() deviceType = ", Integer.valueOf(phoneType));
            return phoneType;
        } catch (SecurityException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getDeviceType() SecurityException ");
            return -1;
        } catch (Exception unused2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getDeviceType() Exception");
            return -1;
        }
    }

    public static String ag() {
        if (av() || az()) {
            boolean z = BaseApplication.getContext().getPackageManager().checkPermission("com.huawei.android.permission.GET_DEVICE_INFO", BaseApplication.getContext().getPackageName()) == 0;
            ReleaseLogUtil.b("getSnToEmuiAddonsOrSystemProperties  isEmui110 || isHarmony is true, hasPermission is ", Boolean.valueOf(z));
            if (z) {
                return HwDeviceInfoEx.getDeviceInfo(1);
            }
        }
        ReleaseLogUtil.b("getSnToEmuiAddonsOrSystemProperties use ro.boot.serialno get sn", new Object[0]);
        return SystemProperties.b("ro.boot.serialno");
    }

    public static String m(Context context) {
        if (context == null) {
            return "";
        }
        try {
            SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("login_data", 0);
            String string = sharedPreferences.getString(com.huawei.health.messagecenter.model.CommonUtil.IMEI, "");
            if (!TextUtils.isEmpty(string)) {
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getImei from SP");
                return string;
            }
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getImei() imei = ", deviceId);
            if (deviceId == null) {
                return "";
            }
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getImei ok..");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(com.huawei.health.messagecenter.model.CommonUtil.IMEI, deviceId);
            edit.commit();
            return deviceId;
        } catch (SecurityException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getImei() SecurityException ");
            return "";
        } catch (Exception unused2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getImei() Exception");
            return "";
        }
    }

    public static boolean ai(Context context) {
        return d("com.huawei.hwservicesmgr.PhoneService", context);
    }

    public static boolean d(String str, Context context) {
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isServiceRunning null");
            return false;
        }
        try {
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
            if (runningServices != null && runningServices.size() > 0) {
                Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
                while (it.hasNext()) {
                    if (it.next().service.getClassName().equals(str)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (Exception unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isServiceRunning() Exception");
            return false;
        }
    }

    public static boolean bf() {
        return SystemInfo.h();
    }

    public static boolean bd() {
        return bf() || bh();
    }

    public static boolean ao() {
        return EmuiBuild.d;
    }

    public static boolean bj() {
        return MagicBuild.f13130a && MagicBuild.d >= 33;
    }

    public static boolean ak() {
        return MagicBuild.f13130a && MagicBuild.d >= 35;
    }

    public static boolean cq() {
        return EnvironmentInfo.b("ro.radio.noril", false);
    }

    public static boolean bx() {
        if (!bh()) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSupportGms is not huawei system");
            return true;
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "gmsversion ", EnvironmentInfo.a("ro.com.google.gmsversion"));
        if (TextUtils.isEmpty(EnvironmentInfo.a("ro.com.google.gmsversion"))) {
            return ah("com.google.android.gms");
        }
        return true;
    }

    public static String ab() {
        return EnvironmentInfo.a("ro.miui.ui.version.name");
    }

    public static String ad() {
        return EnvironmentInfo.a("ro.miui.ui.version.code");
    }

    public static boolean bm() {
        int i2;
        String ab = ab();
        String ad = ad();
        try {
            i2 = Integer.parseInt(ad);
        } catch (NumberFormatException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isMiui10 string2Int e=", e2.getMessage());
            i2 = 0;
        }
        LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isMiui10 name:", ab, " code:", ad, " versionCode:", Integer.valueOf(i2));
        return i2 >= 8;
    }

    public static String a() {
        return EnvironmentInfo.a("ro.board.platform");
    }

    private static boolean ah(String str) {
        try {
            PackageInfo packageInfo = BaseApplication.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return false;
            }
            if (!bFO_(packageInfo)) {
                if (!bFP_(packageInfo)) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "package not exist");
            return false;
        }
    }

    public static boolean bg() {
        boolean equals = "true".equals(EnvironmentInfo.b("ro.build.hw_emui_lite.enable", ""));
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isLite : ", Boolean.valueOf(equals));
        return equals;
    }

    public static String r() {
        return EmuiBuild.c;
    }

    public static boolean bh() {
        return EmuiBuild.d || HarmonyBuild.d;
    }

    public static boolean az() {
        return HarmonyBuild.d;
    }

    public static boolean bp() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).contains("nubia");
    }

    public static boolean bs() {
        return SystemProperties.b("hw_sc.sub_screen_shape", 0) == 1;
    }

    public static String ai() {
        if (!bh()) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getSystemVersion the system is not huawei system");
            return "default";
        }
        if (az()) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getSystemVersion the system is harmony system");
            return HarmonyBuild.b;
        }
        return EmuiBuild.c;
    }

    public static int ah() {
        if (!bh()) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getSystemVersionCode the system is not huawei system");
            return -1;
        }
        return EmuiBuild.f13113a;
    }

    public static boolean by() {
        String b2 = EnvironmentInfo.b("ro.hardware", "");
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSupportCpuForHiAiFace cpuInfo ", b2);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSupportCpuForHiAiFace cpuInfo is empty");
            return false;
        }
        try {
            List asList = Arrays.asList(BaseApplication.getContext().getResources().getStringArray(R.array._2130968699_res_0x7f04007b));
            if (CollectionUtils.isEmpty(asList)) {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSupportCpuForHiAiFace cpuList is empty");
                return false;
            }
            return asList.contains(b2);
        } catch (Resources.NotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSupportCpuForHiAiFace notFoundException ", ExceptionUtils.d(e2));
            return false;
        }
    }

    public static int l(Context context) {
        if (context == null) {
            ReleaseLogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironment context is null");
            return -1;
        }
        ReleaseLogUtil.b(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironment Build.VERSION.SDK_INT:", Integer.valueOf(Build.VERSION.SDK_INT));
        int aq = aq(context);
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironment networkType:", Integer.valueOf(aq));
        return (aq == 1 || aq == -3 || aq == -2) ? aq : a(aq);
    }

    private static int aq(Context context) {
        if (!NetworkUtil.i()) {
            ReleaseLogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironmentInner network isn't connect");
            return -3;
        }
        if (NetworkUtil.m()) {
            ReleaseLogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironmentInner net work type is wifi");
            return 1;
        }
        if (NetworkUtil.f()) {
            if (Build.VERSION.SDK_INT >= 30 && !PermissionUtil.e(context, new String[]{"android.permission.READ_PHONE_STATE"})) {
                ReleaseLogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironmentInner READ_PHONE_STATE is not GRANTED");
                return -2;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (Build.VERSION.SDK_INT >= 30) {
                return telephonyManager.getDataNetworkType();
            }
            return telephonyManager.getNetworkType();
        }
        ReleaseLogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetEnvironmentInner is other net work type");
        return -1;
    }

    public static boolean ah(Context context) {
        return NetworkUtil.f();
    }

    private static int a(int i2) {
        int i3;
        ReleaseLogUtil.b(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetworkClassByType-start");
        switch (i2) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                i3 = 2;
                break;
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
                i3 = 3;
                break;
            case 13:
            case 18:
                i3 = 4;
                break;
            case 19:
            default:
                i3 = -1;
                break;
            case 20:
                i3 = 5;
                break;
        }
        ReleaseLogUtil.b(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getNetworkClassByType-end");
        return i3;
    }

    public static boolean cc() {
        return BuildTypeConfig.i();
    }

    public static boolean as() {
        return com.huawei.hwlogsmodel.common.LogConfig.d();
    }

    public static boolean ap() {
        return CompileParameterUtil.a("IS_BETA_PAY_VERSION", false);
    }

    public static boolean aj() {
        return BuildConfigProperties.e("IS_HTY_VERSION", false);
    }

    public static boolean bv() {
        return com.huawei.hwlogsmodel.common.LogConfig.i();
    }

    public static boolean bc() {
        return CompileParameterUtil.a("IS_GREEN_VERSION", false);
    }

    public static long b() {
        return aa("2025/01/01 00:00:00");
    }

    public static long al() {
        return b();
    }

    public static long af() {
        return e() + 7776000000L;
    }

    public static long e() {
        return aa("2026/01/01 00:00:00");
    }

    private static long aa(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(str).getTime();
        } catch (ParseException e2) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "ParseException exception = ", e2.getMessage());
            return 0L;
        }
    }

    public static String d(int i2) {
        return EnvironmentUtils.b(i2);
    }

    public static void a(String str, String str2) {
        synchronized (CommonUtil.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            LogUtil.c(str, str2, " cost(ms): ", Long.valueOf(elapsedRealtime - f13109a));
            f13109a = elapsedRealtime;
        }
    }

    public static void u(String str) {
        a(str, "");
    }

    public static double c(double d2, int i2) {
        if (i2 < 0) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "round, parameter ERROR!");
            i2 = 0;
        }
        return new BigDecimal(Double.toString(d2)).divide(new BigDecimal("1"), i2, 4).doubleValue();
    }

    public static String c() {
        return BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
    }

    public static String x() {
        return BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
    }

    public static String u() {
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        if (MLAsrConstants.LAN_ZH.equalsIgnoreCase(language)) {
            String script = configuration.locale.getScript();
            if ("Hans".equalsIgnoreCase(script) || TextUtils.isEmpty(script)) {
                return language + Constants.LINK + country;
            }
            if (!"TW".equalsIgnoreCase(country) && !"HK".equalsIgnoreCase(country)) {
                return "zh-CHT";
            }
        }
        return language + Constants.LINK + country;
    }

    public static String ae() {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getScript() enter");
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        if (configuration.getLocales().isEmpty()) {
            return "";
        }
        Locale locale = configuration.getLocales().get(0);
        if (locale == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "locale is null");
            return "";
        }
        return locale.getScript();
    }

    public static boolean h(Context context, String str) {
        Activity wa_;
        if (context == null || TextUtils.isEmpty(str) || (wa_ = com.huawei.haf.application.BaseApplication.wa_()) == null) {
            return false;
        }
        ComponentName componentName = wa_.getComponentName();
        if (componentName == null || TextUtils.isEmpty(componentName.getClassName())) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isForeground getClassName is empty");
            return false;
        }
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isForeground getClassName = ", componentName.getClassName());
        return str.equals(componentName.getClassName());
    }

    public static boolean x(String str) {
        return !TextUtils.isEmpty(str) && str.length() > 5000;
    }

    public static boolean s(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null || (runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean g(Context context, String str) {
        if (context != null && str != null) {
            try {
                List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(30);
                if (runningTasks != null && runningTasks.size() > 0) {
                    Iterator<ActivityManager.RunningTaskInfo> it = runningTasks.iterator();
                    while (it.hasNext()) {
                        ComponentName componentName = it.next().topActivity;
                        if (componentName != null && str.equals(componentName.getClassName()) && "com.huawei.health".equals(componentName.getPackageName())) {
                            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "Activity in stack:", str);
                            return true;
                        }
                    }
                    LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "Activity not found:", str);
                    return false;
                }
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isActivityInStack activityManager.getRunningTasks(1) is null.");
                return false;
            } catch (SecurityException e2) {
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isActivityInStack exception:", ExceptionUtils.d(e2));
            }
        }
        return false;
    }

    public static boolean af(Context context) {
        if (context == null || !(context.getSystemService("activity") instanceof ActivityManager)) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        return activityManager.getRunningTasks(1) != null && activityManager.getRunningTasks(1).size() > 0 && activityManager.getRunningTasks(1).get(0) != null && activityManager.getRunningTasks(1).get(0).numActivities == 1;
    }

    public static boolean b(Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "checkHuaweiAppMarketIsInstall null");
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.huawei.appmarket", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean e(Context context, String str) {
        PackageInfo packageInfo;
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "checkAppIsInstall null");
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static void d(Context context, String str) {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "downloadApkByPackageName");
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "downloadApkByPackageName null");
            return;
        }
        try {
            Uri parse = Uri.parse("market://details?id=" + str);
            if (parse != null) {
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "downloadApkByPackageName startActivity");
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
                intent.addFlags(268435456);
                context.startActivity(intent);
            } else {
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "uri is null!");
            }
        } catch (ActivityNotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "ActivityNotFoundException e = ", e2.getMessage());
        } catch (Exception unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "downloadApkByPackageName Exception e = ");
        }
    }

    public static void c(Context context, String str) {
        if (f(context, "com.android.vending")) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstalled");
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + str));
                intent.setPackage("com.android.vending");
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "jumpToGooglePlay ActivityNotFoundException");
                return;
            }
        }
        context.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
    }

    public static boolean f(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstalled param is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstalled,PackageManager is null");
            return false;
        }
        try {
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstalled fail ", e2.getMessage());
        }
        return packageManager.getPackageInfo(str, 0) != null;
    }

    public static void aj(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "toggleNotificationListenerService null");
            return;
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "toggleNotificationListenerService enter");
        PackageManager packageManager = context.getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(context, "com.huawei.bone.ui.setting.NotificationPushListener"), 2, 1);
        packageManager.setComponentEnabledSetting(new ComponentName(context, "com.huawei.bone.ui.setting.NotificationPushListener"), 1, 1);
    }

    public static String n(Context context) {
        String o = com.huawei.hwlogsmodel.common.LogConfig.o();
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "pathPre = ", o);
        return o;
    }

    public static void a(Context context, String[] strArr) {
        for (String str : strArr) {
            k(context, str);
        }
    }

    public static void k(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "setPermissionFirstRes null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("permissionList", 0).edit();
        edit.putBoolean(str, false);
        edit.apply();
    }

    public static void o(Context context, String str) {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "setPermissionFirstTrue() in permissionName = ", str);
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "setPermissionFirstRes null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("permissionList", 0).edit();
        edit.putBoolean(str, true);
        edit.apply();
    }

    public static boolean a(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getPermissionFirstRes null");
            return true;
        }
        return context.getSharedPreferences("permissionList", 0).getBoolean(str, true);
    }

    public static void am(Context context) {
        if (context != null) {
            Intent intent = new Intent("com.huawei.hihealth.action_receive_push_restart");
            intent.setPackage("com.huawei.bone");
            intent.setFlags(32);
            context.sendBroadcast(intent);
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "startRemoteService");
            return;
        }
        LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "startRemoteService context is null");
    }

    public static boolean r(Context context) {
        ZipFile zipFile;
        if (context == null) {
            return false;
        }
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(context.getApplicationInfo().sourceDir);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            zipFile = zipFile2;
        }
        try {
        } catch (IOException e3) {
            e = e3;
            zipFile2 = zipFile;
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isApplicationNotSigned IOException 1 = ", e.getMessage());
            FileUtils.d(zipFile2);
            return true;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d(zipFile);
            throw th;
        }
        if (zipFile.getEntry("META-INF/HUAWEI.CER") != null) {
            FileUtils.d(zipFile);
            return false;
        }
        FileUtils.d(zipFile);
        return true;
    }

    public static boolean y(Context context) {
        String a2 = EnvironmentInfo.a("ro.product.locale.region");
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isChinaRom():productRegion = ", a2);
        if (!TextUtils.isEmpty(a2)) {
            return "cn".equalsIgnoreCase(a2);
        }
        String a3 = EnvironmentInfo.a(SystemPropertyValues.PROP_LOCALE_KEY);
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isChinaRom():productLocal = ", a3);
        if (!TextUtils.isEmpty(a3)) {
            return a3.toLowerCase(Locale.ENGLISH).contains("cn");
        }
        String country = Locale.getDefault().getCountry();
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isChinaRom():localCountry = ", country);
        if (TextUtils.isEmpty(country)) {
            return false;
        }
        return "cn".equalsIgnoreCase(country);
    }

    public static boolean bt() {
        return ProductConfigUtil.e();
    }

    public static boolean e(String str, String str2, boolean z) {
        String c2 = c(str);
        String c3 = c(str2);
        if (TextUtils.isEmpty(c2) || TextUtils.isEmpty(c3)) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "copyFile source file is empty or destFileName is empty");
            return false;
        }
        File file = new File(c2);
        if (!file.exists()) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "source file is not exit");
            return false;
        }
        if (!file.isFile()) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "srcFile is not a file");
            return false;
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "srcFile is exists");
        File file2 = new File(c3);
        if (file2.exists()) {
            if (z) {
                try {
                    if (!new File(c3).delete()) {
                        return false;
                    }
                } catch (SecurityException unused) {
                    LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "delete SecurityException");
                } catch (Exception unused2) {
                    LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "delete exception");
                }
            }
        } else if (!file2.getParentFile().exists() && !file2.getParentFile().mkdirs()) {
            return false;
        }
        return e(file, file2);
    }

    private static boolean e(File file, File file2) {
        Throwable th;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            IoUtils.e(fileOutputStream2);
                            IoUtils.e(fileInputStream);
                            return true;
                        }
                    }
                } catch (FileNotFoundException | IOException unused) {
                    fileOutputStream = fileOutputStream2;
                    IoUtils.e(fileOutputStream);
                    IoUtils.e(fileInputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    IoUtils.e(fileOutputStream);
                    IoUtils.e(fileInputStream);
                    throw th;
                }
            } catch (FileNotFoundException | IOException unused2) {
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (FileNotFoundException | IOException unused3) {
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
        }
    }

    public static boolean a(File file, String str) throws IOException {
        if (file == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "validateFileName null");
            return false;
        }
        String canonicalPath = file.getCanonicalPath();
        if (Normalizer.normalize(canonicalPath, Normalizer.Form.NFKC).startsWith(Normalizer.normalize(new File(str).getCanonicalPath(), Normalizer.Form.NFKC))) {
            return true;
        }
        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "File is outside extraction target directory.");
        return false;
    }

    public static final boolean ag(Context context) {
        return bv();
    }

    public static String z(String str) {
        return TextUtils.isEmpty(str) ? "" : JsonSanitizer.sanitize(str);
    }

    public static boolean x(Context context) {
        if (context == null) {
            return false;
        }
        String packageName = context.getPackageName();
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isBackground packageName : ", packageName);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() <= 0) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isBackground (appProcesses == null) || (appProcesses.size() <= 0)");
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isBackground appProcess : ", runningAppProcessInfo.processName);
            if (packageName.equals(runningAppProcessInfo.processName)) {
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isBackground appProcess packageName.equals  ", Integer.valueOf(runningAppProcessInfo.importance));
                return runningAppProcessInfo.importance != 100;
            }
        }
        return false;
    }

    public static boolean ab(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isNotificationListenerRunning null");
            return false;
        }
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
        if (runningServices != null) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "serivce is :", runningServiceInfo.service.getClassName());
                if ("com.huawei.bone.ui.setting.NotificationPushListener".equals(runningServiceInfo.service.getClassName())) {
                    LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "serivce is running!!!");
                    return true;
                }
            }
        }
        return false;
    }

    public static int o(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getRunningServicesSize context is null");
            return 0;
        }
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
        if (runningServices == null || runningServices.isEmpty()) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getRunningServicesSize runningServiceInfos is empty");
            return 0;
        }
        return runningServices.size();
    }

    public static boolean ce() {
        return CompileParameterUtil.a("IS_SUPPORT_WEAR", false);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0076 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean w(android.content.Context r12) {
        /*
            java.lang.String r0 = "com.google.android.webview"
            java.lang.String r1 = "CommonUtil"
            r2 = 1
            if (r12 != 0) goto L8
            return r2
        L8:
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 16
            r3.<init>(r4)
            java.lang.String r4 = "60.0.3112.116"
            r3.add(r4)
            java.lang.String r4 = "60.0.3112.107"
            r3.add(r4)
            java.lang.String r5 = "60.0.3112.78"
            r3.add(r5)
            r5 = 0
            android.content.pm.PackageManager r6 = r12.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L32
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r0, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L32
            boolean r7 = bFP_(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L32
            boolean r6 = bFQ_(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L30
            goto L42
        L30:
            r6 = move-exception
            goto L34
        L32:
            r6 = move-exception
            r7 = r5
        L34:
            java.lang.String r8 = "isErrorWebView: e="
            java.lang.String r6 = r6.getMessage()
            java.lang.Object[] r6 = new java.lang.Object[]{r8, r6}
            health.compact.a.LogUtil.c(r1, r6)
            r6 = r5
        L42:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r7)
            java.lang.String r9 = "isUserApp : "
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)
            java.lang.String r11 = " isSystemUpdateApp : "
            java.lang.Object[] r8 = new java.lang.Object[]{r11, r8, r9, r10}
            health.compact.a.LogUtil.c(r1, r8)
            java.lang.String r12 = b(r12, r0)
            boolean r0 = r3.contains(r12)
            if (r0 == 0) goto L76
            java.lang.String r0 = "listErrorVersion.contains(webviewVersion)"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.c(r1, r0)
            if (r7 != 0) goto L75
            if (r6 != 0) goto L75
            boolean r12 = r4.equals(r12)
            if (r12 == 0) goto L74
            goto L75
        L74:
            r2 = r5
        L75:
            return r2
        L76:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.CommonUtil.w(android.content.Context):boolean");
    }

    public static String b(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getVersion null");
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "NameNotFoundException exception");
            return "";
        } catch (Exception unused2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getVersion Exception");
            return "";
        }
    }

    public static boolean bFO_(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & 1) != 0;
    }

    public static boolean bFP_(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & 128) != 0;
    }

    public static boolean bFQ_(PackageInfo packageInfo) {
        return (bFO_(packageInfo) || bFP_(packageInfo)) ? false : true;
    }

    public static void p(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "uninstallApk null");
            return;
        }
        context.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + str)));
    }

    public static boolean al(Context context) {
        if (context == null) {
            return false;
        }
        int a2 = new PackageManagerHelper(context.getApplicationContext()).a("com.huawei.bone");
        boolean z = a2 >= 210000375;
        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "versionCode:", Integer.valueOf(a2), "  res:", Boolean.valueOf(z));
        return z;
    }

    public static void ak(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "runWearApp null");
            return;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.bone", 0);
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(packageInfo.packageName);
            ResolveInfo next = context.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
            if (next != null) {
                String str = next.activityInfo.packageName;
                String str2 = next.activityInfo.name;
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setComponent(new ComponentName(str, str2));
                intent2.setFlags(270532608);
                intent2.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                context.startActivity(intent2);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "Exception e = ", e2.getMessage());
        }
    }

    public static boolean r(String str) {
        return !TextUtils.isEmpty(str) && (!str.contains(":") || str.length() <= 16);
    }

    public static boolean ae(Context context) {
        String str;
        String str2;
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSameTelephonyNetWorkAndSim null");
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        int simState = telephonyManager.getSimState();
        if (simState == 1) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "simState: ", Integer.valueOf(simState));
            return false;
        }
        if (telephonyManager != null) {
            str = telephonyManager.getSimOperator();
            str2 = telephonyManager.getNetworkOperator();
        } else {
            str = "";
            str2 = "";
        }
        if (TextUtils.isEmpty(str) || str.length() < 3 || TextUtils.isEmpty(str2) || str2.length() < 3) {
            return false;
        }
        String substring = str.substring(0, 3);
        String substring2 = str2.substring(0, 3);
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "strSimMcc = ", substring, " strNetWorkMcc = ", substring2);
        if (EnvironmentInfo.l()) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isSameTelephonyNetWorkAndSim isHasTaiwanSim");
            substring = "466";
        }
        if (!substring.equals(substring2)) {
            return false;
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "equals:return true.");
        return true;
    }

    public static String i(Context context) {
        String[] stringArray;
        String str;
        String str2;
        String str3 = "";
        if (context == null || (stringArray = context.getResources().getStringArray(R.array._2130968633_res_0x7f040039)) == null) {
            return "";
        }
        if (EnvironmentInfo.l()) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getCountryStrByTelephonyMcc isHasTaiwanSim");
            return "TW";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            str2 = telephonyManager.getSimOperator();
            str = telephonyManager.getNetworkOperator();
        } else {
            str = "";
            str2 = str;
        }
        String substring = (TextUtils.isEmpty(str) || str.length() < 3) ? "" : str.substring(0, 3);
        if (!TextUtils.isEmpty(str2) && str2.length() >= 3) {
            substring = str2.substring(0, 3);
        }
        int length = stringArray.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                String[] split = stringArray[i2].split(",");
                if (split != null && split[0].trim().equals(substring.trim())) {
                    str3 = split[1].trim();
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "result = ", str3);
        return str3;
    }

    public static String f(Context context) {
        String[] stringArray;
        String str = "";
        if (context == null || (stringArray = context.getResources().getStringArray(R.array._2130968633_res_0x7f040039)) == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String networkOperator = telephonyManager != null ? telephonyManager.getNetworkOperator() : "";
        String substring = (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) ? "" : networkOperator.substring(0, 3);
        int length = stringArray.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                String[] split = stringArray[i2].split(",");
                if (split != null && split.length >= 2 && split[0].trim().equals(substring.trim())) {
                    str = split[1].trim();
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "result = ", str);
        return str;
    }

    public static String b(String str, String str2, int i2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String[] split = str.split(str2);
            if (i2 >= 0 && i2 < split.length) {
                return split[i2];
            }
        }
        return "";
    }

    public static String s(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("/");
            if (split.length > 3 && (indexOf = str.indexOf(split[3])) != -1) {
                return str.substring(indexOf);
            }
        }
        return "";
    }

    public static String k(Context context) {
        return a(context, true);
    }

    public static String a(Context context, boolean z) {
        return e(context, true, z);
    }

    public static String e(Context context, boolean z, boolean z2) {
        String an;
        String str = "8";
        if (bh()) {
            an = h();
            if (z2) {
                if (an == null) {
                    an = m(context);
                    str = "0";
                } else if ("".equals(an)) {
                    an = an();
                } else {
                    LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "deviceId is not null but equals failed");
                    str = "9";
                }
            } else if (TextUtils.isEmpty(an)) {
                an = an();
            } else {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "deviceId is not null but equals failed");
                str = "9";
            }
        } else {
            an = an();
        }
        if (z && "".equals(an)) {
            an = an(context);
            str = "21";
        }
        if (!TextUtils.isEmpty(an)) {
            f = str;
        }
        LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getDeviceId():", an);
        return an;
    }

    public static String h() {
        try {
            Class<?> cls = Class.forName("com.huawei.android.os.BuildEx");
            String str = (String) cls.getMethod("getUDID", new Class[0]).invoke(null, new Object[0]);
            if (health.compact.a.utils.StringUtils.g(str) && az()) {
                str = (String) cls.getMethod("getHarmonyUDID", new Class[0]).invoke(null, new Object[0]);
            }
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getEmuiDeviceUdid = ", str);
            return str;
        } catch (ClassNotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getEmuiDeviceUdid(),ClassNotFoundException,", e2.getMessage());
            return null;
        } catch (NoSuchMethodException e3) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getEmuiDeviceUdid(),NoSuchMethodException,", e3.getMessage());
            return null;
        } catch (InvocationTargetException e4) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getEmuiDeviceUdid(),InvocationTargetException,", e4.getMessage());
            return "";
        } catch (Exception unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getEmuiDeviceUdid(),others:");
            return "";
        }
    }

    public static String an() {
        try {
            String g2 = g();
            return (TextUtils.isEmpty(g2) || "unknown".equals(g2)) ? "" : HEXUtils.a(Sha256.e(g2.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getUdidBySn Exception");
            return "";
        }
    }

    /* renamed from: health.compact.a.CommonUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[CommonUtil$SnStatus.values().length];
            e = iArr;
            try {
                iArr[CommonUtil$SnStatus.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[CommonUtil$SnStatus.FAIL_PERMISSION_DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static String g() {
        int i2 = AnonymousClass1.e[i.ordinal()];
        if (i2 == 1) {
            return cm();
        }
        if (i2 == 2) {
            if (PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.READ_PHONE_STATE"})) {
                return cm();
            }
            return g;
        }
        return g;
    }

    private static String cm() {
        String str = Build.SERIAL;
        if (PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            try {
                str = Build.getSerial();
                i = CommonUtil$SnStatus.SUCCESS;
            } catch (SecurityException e2) {
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getDeviceSn SecurityException:", e2.getMessage());
                i = CommonUtil$SnStatus.FAIL_SDK_VERSION_GREATER_THAN_28;
            }
        } else {
            i = CommonUtil$SnStatus.FAIL_PERMISSION_DENIED;
        }
        String trim = str.trim();
        if (TextUtils.isEmpty(trim)) {
            g = "unknown";
            return "unknown";
        }
        g = trim;
        ReleaseLogUtil.b("R_CommonUtil", "getDeviceSnImpl status: ", Integer.valueOf(i.value));
        return trim;
    }

    public static boolean br() {
        boolean z;
        if (bh()) {
            if (TextUtils.isEmpty(h())) {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "deviceId is null or empty");
            } else {
                z = true;
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isPhoneSupportUdid: ", Boolean.valueOf(z));
                return z;
            }
        }
        z = false;
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isPhoneSupportUdid: ", Boolean.valueOf(z));
        return z;
    }

    public static String b(double d2, String str) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern(str);
        return decimalFormat.format(d2);
    }

    public static HashMap<String, Double> t(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getTrafficRxTx null");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        long timeInMillis = calendar.getTimeInMillis();
        long currentTimeMillis = System.currentTimeMillis();
        calendar.clear();
        ArrayList<NetworkStats.Bucket> d2 = d(timeInMillis, currentTimeMillis, context, 1);
        ArrayList<NetworkStats.Bucket> d3 = d(timeInMillis, currentTimeMillis, context, 0);
        Iterator<NetworkStats.Bucket> it = d2.iterator();
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (it.hasNext()) {
            NetworkStats.Bucket next = it.next();
            j3 += next.getRxBytes();
            j4 += next.getTxBytes();
        }
        Iterator<NetworkStats.Bucket> it2 = d3.iterator();
        long j5 = 0;
        while (it2.hasNext()) {
            NetworkStats.Bucket next2 = it2.next();
            j2 += next2.getRxBytes();
            j5 += next2.getTxBytes();
        }
        HashMap<String, Double> hashMap = new HashMap<>(16);
        hashMap.put("wifi_rx", Double.valueOf(j3 / 1048576.0d));
        hashMap.put("wifi_tx", Double.valueOf(j4 / 1048576.0d));
        hashMap.put("mobile_rx", Double.valueOf(j2 / 1048576.0d));
        hashMap.put("mobile_tx", Double.valueOf(j5 / 1048576.0d));
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
    
        if (r4 != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
    
        if (0 == 0) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.ArrayList<android.app.usage.NetworkStats.Bucket> d(long r14, long r16, android.content.Context r18, int r19) {
        /*
            java.lang.String r0 = "CommonUtil"
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 16
            r1.<init>(r2)
            r2 = 0
            r3 = 1
            r4 = 0
            java.lang.String r5 = "netstats"
            r6 = r18
            java.lang.Object r5 = r6.getSystemService(r5)     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            android.app.usage.NetworkStatsManager r5 = (android.app.usage.NetworkStatsManager) r5     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            if (r5 != 0) goto L1a
            return r1
        L1a:
            android.content.pm.PackageManager r6 = r18.getPackageManager()     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            java.lang.String r7 = "com.huawei.health"
            android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo(r7, r3)     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            int r13 = r6.uid     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            r8 = 0
            r6 = r5
            r7 = r19
            r9 = r14
            r11 = r16
            android.app.usage.NetworkStats r4 = r6.querySummary(r7, r8, r9, r11)     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            if (r4 != 0) goto L39
            if (r4 == 0) goto L38
            r4.close()
        L38:
            return r1
        L39:
            boolean r5 = r4.hasNextBucket()     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            if (r5 == 0) goto L54
            android.app.usage.NetworkStats$Bucket r5 = new android.app.usage.NetworkStats$Bucket     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            r5.<init>()     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            boolean r6 = r4.getNextBucket(r5)     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            if (r6 == 0) goto L39
            int r6 = r5.getUid()     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            if (r6 != r13) goto L39
            r1.add(r5)     // Catch: java.lang.Throwable -> L57 android.content.pm.PackageManager.NameNotFoundException -> L59 android.os.RemoteException -> L65
            goto L39
        L54:
            if (r4 == 0) goto L73
            goto L70
        L57:
            r0 = move-exception
            goto L74
        L59:
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L57
            java.lang.String r5 = "NameNotFoundException"
            r3[r2] = r5     // Catch: java.lang.Throwable -> L57
            health.compact.a.LogUtil.e(r0, r3)     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L73
            goto L70
        L65:
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L57
            java.lang.String r5 = "RemoteException"
            r3[r2] = r5     // Catch: java.lang.Throwable -> L57
            health.compact.a.LogUtil.e(r0, r3)     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L73
        L70:
            r4.close()
        L73:
            return r1
        L74:
            if (r4 == 0) goto L79
            r4.close()
        L79:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.CommonUtil.d(long, long, android.content.Context, int):java.util.ArrayList");
    }

    public static boolean aq() {
        return com.huawei.hwlogsmodel.common.LogConfig.a();
    }

    public static boolean e(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        long g2 = g(str);
        if (j2 < g2) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(g2);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return (calendar.get(5) == calendar2.get(5) && calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1)) ? false : true;
    }

    public static String n(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        if (length <= 2) {
            return "*" + str.charAt(length - 1);
        }
        if (length <= 4) {
            return b(str, 1, 1);
        }
        if (length <= 8) {
            return b(str, 1, (length / 3) - 1);
        }
        return b(str, 3, 4);
    }

    public static String l(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        if (length <= 2) {
            return "**";
        }
        if (length <= 8) {
            return b(str, 1, (length / 3) - 1);
        }
        return b(str, 3, 4);
    }

    private static String b(String str, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        if (i2 < 0 || i3 < 0 || length <= i2 + i3) {
            return "***";
        }
        StringBuilder sb = new StringBuilder(str);
        StringBuilder sb2 = new StringBuilder(16);
        for (int i4 = 0; i4 < (length - i2) - i3; i4++) {
            sb2.append("*");
        }
        sb.replace(i2, length - i3, sb2.toString());
        return sb.toString();
    }

    public static boolean ck() {
        return EmuiBuild.f13113a > 11;
    }

    public static boolean ax() {
        return EmuiBuild.f13113a > 15;
    }

    public static boolean bi() {
        return EmuiBuild.f13113a > 14;
    }

    public static boolean at() {
        return EmuiBuild.f13113a >= 14;
    }

    public static boolean ba() {
        return EmuiBuild.f13113a >= 19;
    }

    public static boolean cf() {
        return o("com.huawei.synergy") >= 910010;
    }

    public static boolean co() {
        return EmuiBuild.f13113a > 17;
    }

    public static boolean ar() {
        return EmuiBuild.f13113a >= 21;
    }

    public static boolean au() {
        return EmuiBuild.f13113a >= 23;
    }

    public static boolean be() {
        return EmuiBuild.f13113a == 23;
    }

    public static boolean av() {
        return EmuiBuild.f13113a >= 25;
    }

    public static boolean ch() {
        return EmuiBuild.f13113a > 25;
    }

    public static boolean cl() {
        return EmuiBuild.f13113a >= 27;
    }

    public static boolean aw() {
        return EmuiBuild.f13113a >= 11;
    }

    public static boolean ci() {
        return EmuiBuild.f13113a >= 9;
    }

    public static boolean cj() {
        return EmuiBuild.f13113a >= 12;
    }

    public static boolean c(String[] strArr) {
        if ("huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
            return b(strArr);
        }
        return false;
    }

    public static boolean b(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            String upperCase = Build.MODEL.toUpperCase(Locale.ENGLISH);
            for (String str : strArr) {
                if (upperCase.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bn() {
        String str = Build.MANUFACTURER;
        String upperCase = Build.MODEL.toUpperCase(Locale.ENGLISH);
        return "huawei".equalsIgnoreCase(str) && (upperCase.contains("FRO") || upperCase.contains("NEY") || upperCase.contains("ANA") || upperCase.contains("ELS"));
    }

    public static boolean bq() {
        String str = Build.MANUFACTURER;
        String upperCase = Build.MODEL.toUpperCase(Locale.ENGLISH);
        return "huawei".equalsIgnoreCase(str) && (upperCase.contains("DUK") || upperCase.contains("LON") || upperCase.contains("MHA") || upperCase.contains("STF") || upperCase.contains("VKY") || upperCase.contains("VTR"));
    }

    public static boolean bl() {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isModelCanUseIndoorEquip, isHuaweiSystem:", Boolean.valueOf(bh()), "isLargerThanAndroid6:", true, "isLargerThanAndroid8:", true);
        return true;
    }

    public static boolean e(int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if ("BaseApplication".equals(str)) {
            j.put("HomeFragment", Integer.valueOf(i2));
            j.put("SportEntranceFragment", Integer.valueOf(i2));
            j.put("DiscoverFragment", Integer.valueOf(i2));
            return false;
        }
        Integer num = j.get(str);
        if (num == null) {
            j.put(str, Integer.valueOf(i2));
            return false;
        }
        if (Math.abs(i2 - num.intValue()) <= 1000) {
            return false;
        }
        j.put(str, Integer.valueOf(i2));
        return true;
    }

    public static File j(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        File file = null;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            if (PermissionUtil.c()) {
                file = context.getExternalFilesDir(null);
            } else {
                file = Environment.getExternalStorageDirectory();
            }
        }
        if (file != null) {
            return file;
        }
        LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getExternalFilesDirectory:innerContext.getFilesDir");
        return context.getFilesDir();
    }

    public static boolean cd() {
        return bh() && Build.VERSION.SDK_INT > 28;
    }

    public static boolean ca() {
        if (!bh()) {
            return false;
        }
        Context context = BaseApplication.getContext();
        String string = Settings.Secure.getString(context.getContentResolver(), "permission_reason_policy");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        if ("NO_RESTRICTED".equals(string)) {
            return true;
        }
        return Arrays.asList(string.split(";")).contains(context.getPackageName());
    }

    public static boolean v(Context context) {
        return context != null && Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1;
    }

    public static boolean c(File file) {
        if (file == null) {
            return false;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            if (canonicalPath == null) {
                return false;
            }
            return canonicalPath.equals(d(canonicalPath));
        } catch (IOException unused) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isFilePathValid, IOException");
            return false;
        }
    }

    private static String an(Context context) {
        try {
            Object c2 = ReflectionUtils.c(ReflectionUtils.b(ReflectionUtils.d("com.huawei.utils.FoundationCommonUtil"), "getAndroidId", Context.class), (Object) null, context);
            return c2 instanceof String ? (String) c2 : "";
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getAndroidId ", e2.getClass().getSimpleName());
            return "";
        }
    }

    public static String ac() {
        return ag("com.huawei.photos") ? "com.huawei.photos" : "com.android.gallery3d";
    }

    public static String z() {
        return ag("com.huawei.music") ? "com.huawei.music" : "com.android.mediacenter";
    }

    public static String aa() {
        return ag(Constants.MAGEZINE_PKG_NAME) ? Constants.MAGEZINE_PKG_NAME : "com.android.keyguard";
    }

    private static boolean ag(String str) {
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            return packageManager.getPackageInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, ExceptionUtils.d(e2));
            return false;
        }
    }

    public static boolean ac(Context context) {
        if (context == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isKeyguardLocked context is null");
            return false;
        }
        Object systemService = context.getSystemService("keyguard");
        KeyguardManager keyguardManager = systemService instanceof KeyguardManager ? (KeyguardManager) systemService : null;
        if (keyguardManager != null) {
            return keyguardManager.isKeyguardLocked();
        }
        return false;
    }

    public static String m(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace('*', '0').matches("^((\\+86)|(86)|(0086))?[1][1-9]\\d{9}$") ? Pattern.compile("^((\\+86)|(86)|(0086))").matcher(str).replaceAll("").trim() : str;
    }

    public static boolean cb() {
        if (!bh()) {
            return false;
        }
        if (!au() && aw()) {
            return true;
        }
        String a2 = EnvironmentInfo.a("hsdf.keyguard.disable_musicsport_style");
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "disable_musicsport_style", a2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        return "false".equals(a2);
    }

    public static boolean bk() {
        if (bh()) {
            return !cb();
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String k(String str) {
        FileInputStream fileInputStream;
        String str2 = "getFileSha256 IOException";
        String c2 = c(str);
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        if (TextUtils.isEmpty(c2)) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getFileSha256 safePath is null");
            return null;
        }
        if (!new File(str).exists()) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getFileSha256 file length is too long.");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            try {
                fileInputStream = new FileInputStream(c2);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (FileNotFoundException unused) {
        }
        try {
            byte[] e2 = e(fileInputStream);
            int length = e2.length;
            for (byte b2 : e2) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(i2));
            }
            try {
                fileInputStream.close();
                str2 = str2;
                fileInputStream2 = length;
            } catch (IOException unused2) {
                Object[] objArr = {"getFileSha256 IOException"};
                LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, objArr);
                str2 = objArr;
                fileInputStream2 = length;
            }
        } catch (FileNotFoundException unused3) {
            fileInputStream3 = fileInputStream;
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getFileSha256 FileNotFoundException");
            str2 = str2;
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                    str2 = str2;
                    fileInputStream2 = fileInputStream3;
                } catch (IOException unused4) {
                    Object[] objArr2 = {"getFileSha256 IOException"};
                    LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, objArr2);
                    str2 = objArr2;
                    fileInputStream2 = fileInputStream3;
                }
            }
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused5) {
                    LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, str2);
                }
            }
            throw th;
        }
        return sb.toString();
    }

    private static byte[] e(InputStream inputStream) {
        if (inputStream == null) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "digestBytes input is null");
            return new byte[0];
        }
        byte[] bArr = new byte[4096];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            if (digest != null) {
                return digest;
            }
        } catch (IOException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "digestBytes IOException");
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "digestBytes NoSuchAlgorithmException");
        }
        return new byte[0];
    }

    public static boolean z(Context context) {
        if (bh()) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isHmsLiteEnable is Emui");
            return false;
        }
        if (EnvironmentUtils.c() && cp()) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isHmsLiteEnable is from sp");
            return true;
        }
        int g2 = g(context);
        if (g2 == 0) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstallHmsPackage false");
            cn();
            return true;
        }
        if (Build.VERSION.SDK_INT < 29) {
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isHmsLiteEnable Build.VERSION.SDK_INT < Build.VERSION_CODES.Q");
            return false;
        }
        if (bf() && g2 < 50010000) {
            cn();
            return true;
        }
        if (!EnvironmentUtils.c()) {
            String c2 = ThirdLoginFreshAccessToken.c();
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isHmsLiteEnable liteLogout = ", c2);
            return "false".equals(c2);
        }
        if (g2 > 30003300) {
            return false;
        }
        cn();
        return true;
    }

    private static boolean cp() {
        return BaseApplication.getContext().getSharedPreferences("hmslite", 0).getBoolean("use-status", false);
    }

    public static void cn() {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "updateHmsUsableStatus");
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("hmslite", 0).edit();
        edit.putBoolean("use-status", true);
        edit.commit();
    }

    public static int g(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(p(), 0);
            LogUtil.d(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstallHmsPackage packageInfo=", packageInfo);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isInstallHmsPackage NameNotFoundException");
            return 0;
        }
    }

    public static String p() {
        return HMSPackageManager.getInstance(BaseApplication.getContext()).getHMSPackageName();
    }

    public static boolean ay() {
        int a2 = new PackageManagerHelper(BaseApplication.getContext()).a(p());
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "versionCode:" + a2);
        if (a2 <= 0) {
            return false;
        }
        boolean z = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(BaseApplication.getContext()) == 0;
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isHmsAvailable: " + z);
        return z;
    }

    public static int b(int i2, long j2) {
        if (j2 == 0) {
            return 0;
        }
        return Math.round((i2 / ((j2 * 1.0f) / 1000.0f)) * 60.0f);
    }

    public static String am() {
        StringBuilder sb = new StringBuilder(10);
        String format = new SimpleDateFormat("Z", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        sb.append("GMT");
        if (!TextUtils.isEmpty(format) && format.length() >= 5) {
            LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getTimezone gmtStr =", format);
            int i2 = 0;
            while (i2 < 3) {
                int i3 = i2 + 1;
                String substring = format.substring(i2, i3);
                if (Constants.LINK.equals(substring)) {
                    sb.append(Constants.LINK);
                } else if (Marker.ANY_NON_NULL_MARKER.equals(substring)) {
                    sb.append(Marker.ANY_NON_NULL_MARKER);
                } else if (i2 != 1 || !"0".equals(substring)) {
                    sb.append(substring);
                }
                i2 = i3;
            }
        } else {
            sb.append("+8");
        }
        return sb.toString();
    }

    public static String t(String str) {
        String str2;
        FileInputStream fileInputStream;
        str2 = "";
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            fileInputStream = fileInputStream2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, StandardCharsets.UTF_8) : "";
            try {
                fileInputStream.close();
            } catch (IOException e3) {
                LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getStringFile e is ", e3.getMessage());
            }
        } catch (IOException e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getStringFile e is ", e.getMessage());
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                    LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getStringFile e is ", e5.getMessage());
                }
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getStringFile e is ", e6.getMessage());
                }
            }
            throw th;
        }
        return str2;
    }

    public static String q(String str) {
        if ("my".equals(str) && a(Locale.getDefault(), Locale.getDefault(), false).contains("Zawgyi")) {
            return "rZG";
        }
        if ("bo".equals(str)) {
            return "rCN";
        }
        return null;
    }

    public static String a(Locale locale, Locale locale2, boolean z) {
        if (locale == null || locale2 == null) {
            return Locale.getDefault().getDisplayName();
        }
        if (au()) {
            return LocaleHelperEx.getDisplayName(locale, locale2, z);
        }
        return Locale.getDefault().getDisplayName();
    }

    public static String e(Locale locale, Locale locale2) {
        if (locale == null || locale2 == null) {
            return Locale.getDefault().getDisplayCountry();
        }
        if (au()) {
            return LocaleHelperEx.getDisplayCountry(locale, locale2);
        }
        return Locale.getDefault().getDisplayCountry();
    }

    public static boolean c(double d2) {
        return Math.abs(d2) <= 1.0E-6d;
    }

    public static final String d(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (IllegalArgumentException e2) {
            ReleaseLogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "IllegalArgumentException with ", ExceptionUtils.d(e2));
            return "";
        }
    }

    public static int[] b(List<Integer> list) {
        if (list == null) {
            return new int[0];
        }
        int[] iArr = new int[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            iArr[i2] = list.get(i2).intValue();
        }
        return iArr;
    }

    public static void d() {
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "enter createMaintenanceLogTemp");
        String str = null;
        try {
            str = j((Context) null).getCanonicalPath() + "/huaweisystem/com.huawei.health/MaintenanceLogTemp";
        } catch (IOException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "createMaintenanceLogTemp() IOException");
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "createMaintenanceLogTemp :", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(com.huawei.health.messagecenter.model.CommonUtil.TAG, "path is null");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "createMaintenanceLogTemp :", Boolean.valueOf(file.mkdirs()));
    }

    public static boolean v(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "packageName is null.");
            return false;
        }
        String e2 = HsfSignValidator.e(BaseApplication.getContext(), str);
        if (!TextUtils.isEmpty(e2)) {
            return ("com.android.mediacenter".equals(str) && "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05".equals(e2)) || ("com.huawei.music".equals(str) && "3E13F630C77618DE3A580DBBAFFE0AC04A16444633CC0253AFB088D3E3AB6EFE".equals(e2));
        }
        LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "signature is null.");
        return false;
    }

    public static boolean d(long j2) {
        return System.currentTimeMillis() - j2 < 86400000;
    }

    public static String p(String str) {
        if (str == null) {
            return "";
        }
        try {
            return new BigDecimal(str).stripTrailingZeros().toPlainString();
        } catch (ArithmeticException | NumberFormatException unused) {
            LogUtil.e(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getStripedNumber exception num: ", str);
            return "";
        }
    }

    public static int d(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return 0;
        }
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e2) {
            ReleaseLogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getJsonObjectIntValue exception, e is: ", e2.getMessage());
            return 0;
        }
    }

    public static double c(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return 0.0d;
        }
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException e2) {
            ReleaseLogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "getJsonObjectDoubleValue exception, e is: ", e2.getMessage());
            return 0.0d;
        }
    }

    public static boolean bw() {
        try {
            TelephonyManager xE_ = xE_();
            if (xE_ == null) {
                LogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isPhoning telephonyManager is null");
                return false;
            }
            int callState = xE_.getCallState();
            return (callState == 2) | (callState == 1);
        } catch (SecurityException e2) {
            ReleaseLogUtil.c(com.huawei.health.messagecenter.model.CommonUtil.TAG, "isPhoning exception is: ", ExceptionUtils.d(e2));
            return false;
        }
    }

    public static String f() {
        return f;
    }
}
