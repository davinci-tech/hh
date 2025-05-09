package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.app.EnvUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.view.ConfigConstants;
import defpackage.kr;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class md {
    public static final String[] c = {"10.1.5.1013151", "10.1.5.1013148"};
    public static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '+', '/'};

    public static final class a implements Runnable {
        public final /* synthetic */ ConditionVariable c;
        public final /* synthetic */ Runnable e;

        public a(Runnable runnable, ConditionVariable conditionVariable) {
            this.e = runnable;
            this.c = conditionVariable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.e.run();
            } finally {
                this.c.open();
            }
        }
    }

    public static final class c implements Runnable {
        public final /* synthetic */ Activity d;

        public c(Activity activity) {
            this.d = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.d.finish();
        }
    }

    public static String a() {
        if (EnvUtils.b()) {
            return "com.eg.android.AlipayGphoneRC";
        }
        try {
            return kg.d.get(0).d;
        } catch (Throwable unused) {
            return "com.eg.android.AlipayGphone";
        }
    }

    public static String c() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String c(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            if (indexOf <= str.length()) {
                return "";
            }
            int indexOf2 = !TextUtils.isEmpty(str2) ? str3.indexOf(str2, indexOf) : 0;
            return indexOf2 < 1 ? str3.substring(indexOf) : str3.substring(indexOf, indexOf2);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String c(lv lvVar, String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            kl.e(lvVar, "biz", "H5PayDataAnalysisError", e);
            return "";
        }
    }

    public static String d(String str) {
        return (EnvUtils.b() && TextUtils.equals(str, "com.eg.android.AlipayGphoneRC")) ? "com.eg.android.AlipayGphoneRC.IAlixPay" : "com.eg.android.AlipayGphone.IAlixPay";
    }

    public static int e(String str) {
        for (int i = 0; i < 64; i++) {
            if (str.equals(String.valueOf(d[i]))) {
                return i;
            }
        }
        return 0;
    }

    public static String f(String str) {
        try {
            Uri parse = Uri.parse(str);
            return String.format("%s%s", parse.getAuthority(), parse.getPath());
        } catch (Throwable th) {
            ma.c(th);
            return Constants.LINK;
        }
    }

    public static JSONObject i(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String j(Context context) {
        return " (" + c() + ";" + e() + ";" + a(context) + ";;" + h(context) + ")(sdk android)";
    }

    public static String a(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String h(Context context) {
        DisplayMetrics bh_ = bh_(context);
        return bh_.widthPixels + "*" + bh_.heightPixels;
    }

    public static String b() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    public static String e() {
        String b = b();
        int indexOf = b.indexOf(Constants.LINK);
        if (indexOf != -1) {
            b = b.substring(0, indexOf);
        }
        int indexOf2 = b.indexOf("\n");
        if (indexOf2 != -1) {
            b = b.substring(0, indexOf2);
        }
        return "Linux " + b;
    }

    public static boolean i(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String d(lv lvVar, String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            kl.c(lvVar, "biz", "rflex", e.getClass().getSimpleName());
            return null;
        }
    }

    public static boolean g() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static Map<String, String> c(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=", 1);
            if (-1 != indexOf) {
                hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
            }
        }
        return hashMap;
    }

    public static boolean h() {
        try {
            String[] split = kr.a().f().split("\\|");
            String str = Build.MODEL;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            for (String str2 : split) {
                if (TextUtils.equals(str, str2) || TextUtils.equals(str2, "all")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            ma.c(th);
            return false;
        }
    }

    public static boolean g(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static final class d {
        public final String b;
        public final int c;
        public final PackageInfo e;

        public d(PackageInfo packageInfo, int i, String str) {
            this.e = packageInfo;
            this.c = i;
            this.b = str;
        }

        public boolean c(lv lvVar) {
            Signature[] signatureArr = this.e.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String d = md.d(lvVar, signature.toByteArray());
                if (d != null && !TextUtils.equals(d, this.b)) {
                    kl.c(lvVar, "biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", d, this.b));
                    return true;
                }
            }
            return false;
        }

        public boolean e() {
            return this.e.versionCode < this.c;
        }
    }

    public static String d(lv lvVar, byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e) {
            kl.e(lvVar, "auth", "GetPublicKeyFromSignEx", e);
            return null;
        }
    }

    public static String d(Context context) {
        String a2 = mf.a(context);
        return a2.substring(0, a2.indexOf("://"));
    }

    public static int a(String str) {
        try {
            String o = kr.a().o();
            if (TextUtils.isEmpty(o)) {
                return 0;
            }
            return (c(o, "").contains(str) ? 2 : 0) | 1;
        } catch (Throwable unused) {
            return 61440;
        }
    }

    public static String h(String str) {
        return c(str, true);
    }

    public static int f() {
        try {
            return Process.myUid();
        } catch (Throwable th) {
            ma.c(th);
            return -200;
        }
    }

    public static Map<String, String> b(lv lvVar, String str) {
        HashMap hashMap = new HashMap(4);
        int indexOf = str.indexOf(63);
        if (indexOf != -1 && indexOf < str.length() - 1) {
            for (String str2 : str.substring(indexOf + 1).split("&")) {
                int indexOf2 = str2.indexOf(61, 1);
                if (indexOf2 != -1 && indexOf2 < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf2), c(lvVar, str2.substring(indexOf2 + 1)));
                }
            }
        }
        return hashMap;
    }

    public static d a(lv lvVar, Context context, List<kr.e> list) {
        d a2;
        if (list == null) {
            return null;
        }
        for (kr.e eVar : list) {
            if (eVar != null && (a2 = a(lvVar, context, eVar.d, eVar.c, eVar.b)) != null && !a2.c(lvVar) && !a2.e()) {
                return a2;
            }
        }
        return null;
    }

    public static d a(lv lvVar, Context context, String str, int i, String str2) {
        PackageInfo packageInfo;
        if (EnvUtils.b() && "com.eg.android.AlipayGphone".equals(str)) {
            str = "com.eg.android.AlipayGphoneRC";
        }
        try {
            packageInfo = bb_(context, str);
        } catch (Throwable th) {
            kl.c(lvVar, "auth", "GetPackageInfoEx", th.getMessage());
            packageInfo = null;
        }
        if (bf_(lvVar, packageInfo)) {
            return bc_(packageInfo, i, str2);
        }
        return null;
    }

    public static DisplayMetrics bh_(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int d(int i) {
        return i / 100000;
    }

    public static String c(String str, String str2) {
        String string = Settings.Secure.getString(((Application) lw.c().d()).getContentResolver(), str);
        return string != null ? string : str2;
    }

    public static boolean e(lv lvVar, String str) {
        int a2;
        try {
            a2 = a(str);
            kl.a(lvVar, "biz", "bindExt", "" + a2);
        } catch (Throwable unused) {
        }
        return kr.a().p() && (a2 & 2) == 2;
    }

    public static boolean e(lv lvVar) {
        if (lvVar == null || TextUtils.isEmpty(lvVar.f)) {
            return false;
        }
        return lvVar.f.toLowerCase().contains("auth");
    }

    public static boolean bf_(lv lvVar, PackageInfo packageInfo) {
        String str;
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                str = "info.signatures == null";
            } else if (signatureArr.length <= 0) {
                str = "info.signatures.length <= 0";
            } else {
                str = "";
                z = true;
            }
        }
        if (!z) {
            kl.c(lvVar, "auth", "NotIncludeSignatures", str);
        }
        return z;
    }

    public static PackageInfo bb_(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    public static d bc_(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new d(packageInfo, i, str);
    }

    public static long b(String str) {
        return e(str, 6);
    }

    public static long e(String str, int i) {
        int pow = (int) Math.pow(2.0d, i);
        int length = str.length();
        long j = 0;
        int i2 = length;
        for (int i3 = 0; i3 < length; i3++) {
            j += Integer.parseInt(String.valueOf(e(str.substring(i3, r5)))) * ((long) Math.pow(pow, i2 - 1));
            i2--;
        }
        return j;
    }

    public static int d() {
        String e = lw.c().e();
        if (TextUtils.isEmpty(e)) {
            return -1;
        }
        String replaceAll = e.replaceAll("=", "");
        if (replaceAll.length() >= 5) {
            replaceAll = replaceAll.substring(0, 5);
        }
        int b = (int) (b(replaceAll) % PreConnectManager.CONNECT_INTERNAL);
        return b < 0 ? b * (-1) : b;
    }

    public static boolean e(lv lvVar, Context context, List<kr.e> list, boolean z) {
        try {
            for (kr.e eVar : list) {
                if (eVar != null) {
                    String str = eVar.d;
                    if (EnvUtils.b() && "com.eg.android.AlipayGphone".equals(str)) {
                        str = "com.eg.android.AlipayGphoneRC";
                    }
                    try {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
                        if (packageInfo != null) {
                            if (!z) {
                                return true;
                            }
                            kl.a(lvVar, "biz", "PgWltVer", packageInfo.packageName + "|" + packageInfo.versionName);
                            return true;
                        }
                        continue;
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "CheckLaunchAppExistEx", th);
            return false;
        }
    }

    public static boolean be_(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            String[] strArr = c;
            if (!TextUtils.equals(str, strArr[0])) {
                if (!TextUtils.equals(str, strArr[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
            } else if (nextInt == 1) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
            } else if (nextInt == 2) {
                sb.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return sb.toString();
    }

    public static boolean bg_(lv lvVar, String str, Activity activity) {
        int parseInt;
        String substring;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (!str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) && !str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
            if (!TextUtils.equals(str, "sdklite://h5quit") && !TextUtils.equals(str, d("http", "://m.alipay.com/?action=h5quit"))) {
                if (!str.startsWith("sdklite://h5quit?result=")) {
                    return false;
                }
                try {
                    String substring2 = str.substring(str.indexOf("sdklite://h5quit?result=") + 24);
                    parseInt = Integer.parseInt(substring2.substring(substring2.lastIndexOf("&end_code=") + 10));
                } catch (Exception unused) {
                    kh.a(kh.b());
                }
                if (parseInt != com.alipay.sdk.m.j.c.SUCCEEDED.b() && parseInt != com.alipay.sdk.m.j.c.PAY_WAITTING.b()) {
                    com.alipay.sdk.m.j.c b = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.FAILED.b());
                    kh.a(kh.c(b.b(), b.a(), ""));
                    activity.runOnUiThread(new c(activity));
                    return true;
                }
                if (kj.b) {
                    StringBuilder sb = new StringBuilder();
                    String decode = URLDecoder.decode(str);
                    String decode2 = URLDecoder.decode(decode);
                    String str2 = decode2.substring(decode2.indexOf("sdklite://h5quit?result=") + 24, decode2.lastIndexOf("&end_code=")).split("&return_url=")[0];
                    int indexOf = decode.indexOf("&return_url=") + 12;
                    sb.append(str2);
                    sb.append("&return_url=");
                    sb.append(decode.substring(indexOf, decode.indexOf("&", indexOf)));
                    sb.append(decode.substring(decode.indexOf("&", indexOf)));
                    substring = sb.toString();
                } else {
                    String decode3 = URLDecoder.decode(str);
                    substring = decode3.substring(decode3.indexOf("sdklite://h5quit?result=") + 24, decode3.lastIndexOf("&end_code="));
                }
                com.alipay.sdk.m.j.c b2 = com.alipay.sdk.m.j.c.b(parseInt);
                kh.a(kh.c(b2.b(), b2.a(), substring));
                activity.runOnUiThread(new c(activity));
                return true;
            }
            kh.a(kh.a());
            activity.finish();
            return true;
        }
        try {
            d a2 = a(lvVar, activity, kg.d);
            if (a2 != null && !a2.e() && !a2.c(lvVar)) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                }
                activity.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
            }
        } catch (Throwable unused2) {
        }
        return true;
    }

    public static String c(String str, boolean z) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            if (z && digest.length > 16) {
                byte[] bArr = new byte[16];
                System.arraycopy(digest, 0, bArr, 0, 16);
                return c(bArr);
            }
            return c(digest);
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(Character.forDigit((b & 240) >> 4, 16));
            sb.append(Character.forDigit(b & BaseType.Obj, 16));
        }
        return sb.toString();
    }

    public static ActivityInfo ba_(Context context) {
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                    if (TextUtils.equals(activityInfo.name, activity.getClass().getName())) {
                        return activityInfo;
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            ma.c(th);
            return null;
        }
    }

    public static String b(lv lvVar) {
        return d(lvVar, "ro.build.fingerprint");
    }

    public static <T> T b(WeakReference<T> weakReference) {
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static boolean a(lv lvVar, String str) {
        try {
            String host = new URL(str).getHost();
            if (host.endsWith(ConfigConstants.ALIPAY_URL)) {
                return true;
            }
            return host.endsWith("alipay.net");
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "ckUrlErr", th);
            return false;
        }
    }

    public static JSONObject bd_(Intent intent) {
        Bundle extras;
        JSONObject jSONObject = new JSONObject();
        if (intent != null && (extras = intent.getExtras()) != null) {
            for (String str : extras.keySet()) {
                try {
                    jSONObject.put(str, String.valueOf(extras.get(str)));
                } catch (Throwable unused) {
                }
            }
        }
        return jSONObject;
    }

    public static Map<String, String> e(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, jSONObject.optString(next));
            } catch (Throwable th) {
                ma.c(th);
            }
        }
        return hashMap;
    }

    public static boolean b(long j, Runnable runnable, String str) {
        if (runnable == null) {
            return false;
        }
        ConditionVariable conditionVariable = new ConditionVariable();
        Thread thread = new Thread(new a(runnable, conditionVariable));
        if (!TextUtils.isEmpty(str)) {
            thread.setName(str);
        }
        thread.start();
        if (j <= 0) {
            conditionVariable.block();
            return true;
        }
        return conditionVariable.block(j);
    }

    public static String d(String str, String str2) {
        return str + str2;
    }

    public static String a(lv lvVar, Context context) {
        try {
            String d2 = mb.d(lvVar, context, "alipay_cashier_ap_fi", "");
            if (!TextUtils.isEmpty(d2)) {
                return d2;
            }
            try {
                mb.c(lvVar, context, "alipay_cashier_ap_fi", jy.c("FU", System.currentTimeMillis(), new jv(), (short) 0, new kc()).d());
                String d3 = mb.d(lvVar, context, "alipay_cashier_ap_fi", "");
                if (!TextUtils.isEmpty(d3)) {
                    return d3;
                }
                kl.c(lvVar, "biz", "e_regen_empty", "");
                return "";
            } catch (Exception e) {
                kl.c(lvVar, "biz", "e_gen", e.getClass().getSimpleName());
                return "";
            }
        } catch (Exception e2) {
            kl.e(lvVar, "biz", "e_gen_err", e2);
            return "";
        }
    }

    public static void c(String str, String str2, Context context, lv lvVar) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || e(lvVar) || !kr.a().x()) {
            return;
        }
        try {
            Intent intent = new Intent("android.app.intent.action.APP_EXCEPTION_OCCUR");
            intent.putExtra("bizType", str);
            intent.putExtra("exName", str2);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
            kl.a(lvVar, "biz", "AppNotify", str + "|" + str2);
        } catch (Exception unused) {
        }
    }

    public static String b(Context context) {
        return "-1;-1";
    }
}
