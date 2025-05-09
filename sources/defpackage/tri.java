package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.wearengine.auth.HiAppInfo;
import com.huawei.wearengine.repository.AppInfoRepositoryImpl;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes7.dex */
public class tri {
    private static ExecutorService c = Executors.newSingleThreadExecutor();

    public static HiAppInfo a(Context context, String str) {
        if (context == null) {
            tos.e("AppUtil", "getAppInfo context isEmpty!");
            return null;
        }
        String b = b(context, str);
        if (TextUtils.isEmpty(b)) {
            tos.e("AppUtil", "getAppInfo appAuthKey isEmpty!");
            return null;
        }
        AppInfoRepositoryImpl appInfoRepositoryImpl = new AppInfoRepositoryImpl(context);
        HiAppInfo hiAppInfo = appInfoRepositoryImpl.getHiAppInfo(b);
        if (hiAppInfo != null) {
            tos.b("AppUtil", "getAppInfo appInfoRepository hiAppInfo != null, HiAppInfo:" + hiAppInfo.toString());
            return hiAppInfo;
        }
        HiAppInfo h = h(context, str);
        if (h == null) {
            tos.e("AppUtil", "getAppInfo appInfo == null");
            return null;
        }
        tos.b("AppUtil", "getAppInfo HiAppInfo:" + h.toString());
        if (appInfoRepositoryImpl.insertAppInfo(h)) {
            return h;
        }
        tos.e("AppUtil", "getAppInfo insertAppInfo fail!");
        return null;
    }

    private static HiAppInfo h(Context context, String str) {
        int j;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String a2 = a(context);
        tos.b("AppUtil", "getAppInfo userId:" + a2);
        if (TextUtils.isEmpty(a2) || (j = j(context, str)) == 0) {
            return null;
        }
        HiAppInfo hiAppInfo = new HiAppInfo();
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            int i = applicationInfo.uid;
            hiAppInfo.setKey(e(i, a2, j));
            hiAppInfo.setUserId(a2);
            hiAppInfo.setAppId(j);
            hiAppInfo.setAppUid(i);
            hiAppInfo.setPackageName(str);
            CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            hiAppInfo.setAppName(applicationLabel instanceof String ? (String) applicationLabel : "");
            hiAppInfo.setVersion(packageManager.getPackageInfo(str, 64).versionName);
            hiAppInfo.setSignature(a(c(str), i));
            hiAppInfo.setByteDraw(tre.ffd_(ffb_(str)));
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "getKitAppInfo() NameNotFoundException");
        }
        return hiAppInfo;
    }

    public static String c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            tos.e("AppUtil", "getAppName context or packageName is null:");
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
            return applicationLabel instanceof String ? (String) applicationLabel : "";
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "getAppName() NameNotFoundException");
            return "";
        }
    }

    public static int j(Context context, String str) {
        ApplicationInfo applicationInfo;
        Object obj;
        if (context == null || TextUtils.isEmpty(str)) {
            tos.e("AppUtil", "getMetaDataAppId context or packageName is null:");
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            tos.e("AppUtil", "getMetaDataAppId packageManager is null");
            return 0;
        }
        try {
            applicationInfo = packageManager.getApplicationInfo(str, 128);
        } catch (PackageManager.NameNotFoundException | NumberFormatException unused) {
            tos.e("AppUtil", "getMetaDataAppId has exception");
        }
        if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("com.huawei.hms.client.appid")) != null) {
            String valueOf = String.valueOf(obj);
            if (valueOf.startsWith("appid=")) {
                valueOf = valueOf.substring(6);
            }
            return Integer.parseInt(valueOf);
        }
        tos.e("AppUtil", "getMetaDataAppId metaData is null");
        return 0;
    }

    public static Bitmap ffb_(String str) {
        PackageManager packageManager = tot.a().getApplicationContext().getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            tos.a("AppUtil", "getBitmap");
            Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
            if (applicationIcon == null) {
                return null;
            }
            if (applicationIcon instanceof AdaptiveIconDrawable) {
                tos.b("AppUtil", "AdaptiveIconDrawable getBitmap");
                return ffa_(applicationIcon);
            }
            tos.b("AppUtil", "BitmapDrawable getBitmap");
            if (applicationIcon instanceof BitmapDrawable) {
                return ((BitmapDrawable) applicationIcon).getBitmap();
            }
            return ffa_(applicationIcon);
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "PackageManager.NameNotFoundException:");
            return null;
        }
    }

    private static Bitmap ffa_(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static String a(String str, int i) {
        return str + "##" + String.valueOf(i);
    }

    public static String c(String str) {
        Certificate a2 = a(str);
        if (a2 == null) {
            return null;
        }
        try {
            return trl.d(trt.c(a2.getEncoded()), true);
        } catch (CertificateEncodingException unused) {
            tos.e("AppUtil", "Failed to get application signature certificate fingerprint.");
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005c, code lost:
    
        if (r1 == null) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.security.cert.Certificate a(java.lang.String r5) {
        /*
            java.lang.String r0 = "An exception occurred while closing the 'Closeable' object."
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getPackageCertificate packageName:"
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "AppUtil"
            defpackage.tos.a(r2, r1)
            android.content.Context r1 = defpackage.tot.a()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            r3 = 64
            r4 = 0
            android.content.pm.PackageInfo r5 = r1.getPackageInfo(r5, r3)     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            if (r5 == 0) goto L65
            android.content.pm.Signature[] r1 = r5.signatures     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            int r1 = r1.length     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            if (r1 <= 0) goto L65
            android.content.pm.Signature[] r5 = r5.signatures     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            r1 = 0
            r5 = r5[r1]     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            byte[] r5 = r5.toByteArray()     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L4b java.security.cert.CertificateException -> L4d android.content.pm.PackageManager.NameNotFoundException -> L56
            java.lang.String r5 = "X.509"
            java.security.cert.CertificateFactory r5 = java.security.cert.CertificateFactory.getInstance(r5)     // Catch: java.security.cert.CertificateException -> L4e android.content.pm.PackageManager.NameNotFoundException -> L57 java.lang.Throwable -> L6b
            java.security.cert.Certificate r5 = r5.generateCertificate(r1)     // Catch: java.security.cert.CertificateException -> L4e android.content.pm.PackageManager.NameNotFoundException -> L57 java.lang.Throwable -> L6b
            r1.close()     // Catch: java.io.IOException -> L47
            goto L4a
        L47:
            defpackage.tos.e(r2, r0)
        L4a:
            return r5
        L4b:
            r5 = move-exception
            goto L6d
        L4d:
            r1 = r4
        L4e:
            java.lang.String r5 = "getPackageCertificate CertificateException"
            defpackage.tos.e(r2, r5)     // Catch: java.lang.Throwable -> L6b
            if (r1 == 0) goto L65
            goto L5e
        L56:
            r1 = r4
        L57:
            java.lang.String r5 = "getPackageCertificate PackageManager.NameNotFoundException"
            defpackage.tos.e(r2, r5)     // Catch: java.lang.Throwable -> L6b
            if (r1 == 0) goto L65
        L5e:
            r1.close()     // Catch: java.io.IOException -> L62
            goto L65
        L62:
            defpackage.tos.e(r2, r0)
        L65:
            java.lang.String r5 = "Failed to get application signature certificate."
            defpackage.tos.e(r2, r5)
            return r4
        L6b:
            r5 = move-exception
            r4 = r1
        L6d:
            if (r4 == 0) goto L76
            r4.close()     // Catch: java.io.IOException -> L73
            goto L76
        L73:
            defpackage.tos.e(r2, r0)
        L76:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tri.a(java.lang.String):java.security.cert.Certificate");
    }

    public static String e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            tos.e("AppUtil", "getCallingAppVersion context or packageName is null:");
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "getAppVersion NameNotFoundException");
            return "";
        }
    }

    public static String b(int i, Context context) {
        if (context == null) {
            tos.d("AppUtil", "getCallingPackageName:context is null");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            tos.d("AppUtil", "getCallingPackageName:packageManager is null");
            return "";
        }
        return packageManager.getNameForUid(i);
    }

    public static String c(int i, Context context, String str) {
        if (context == null) {
            tos.d("AppUtil", "getCallingPackageName:context is null");
            return null;
        }
        tos.a("AppUtil", "getCallingPackageNameUid ：" + i + " getCallingPackageNameApplicationId ：" + str);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            tos.d("AppUtil", "getCallingPackageName:packageManager is null");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            tos.d("AppUtil", "getCallingPackageName:applicationId is null");
            return null;
        }
        String[] packagesForUid = packageManager.getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            tos.d("AppUtil", "getCallingPackageName:packageNames is null");
            return null;
        }
        if (Arrays.asList(packagesForUid).contains(str)) {
            tos.b("AppUtil", "getCallingPackageName:packageNames contains applicationId:" + str);
            return str;
        }
        tos.d("AppUtil", "getCallingPackageName:packageNames not contains applicationId");
        return null;
    }

    public static String a() {
        return Locale.getDefault().getCountry();
    }

    public static String b(Context context, String str) {
        int j;
        if (TextUtils.isEmpty(str)) {
            tos.d("AppUtil", "getAppKey packageName is null");
            return null;
        }
        String a2 = a(context);
        if (TextUtils.isEmpty(a2) || (j = j(context, str)) == 0) {
            return null;
        }
        return e(d(context, str), a2, j);
    }

    public static String e(int i, String str, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append("_");
        stringBuffer.append(str);
        stringBuffer.append("_");
        stringBuffer.append(i2);
        return stringBuffer.toString();
    }

    public static String a(final Context context) {
        try {
            return (String) c.submit(new Callable<String>() { // from class: tri.4
                @Override // java.util.concurrent.Callable
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public String call() throws Exception {
                    return trd.d(context);
                }
            }).get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            tos.e("AppUtil", "getUserId has exception");
            return null;
        }
    }

    public static boolean d(final Context context) {
        try {
            return ((Boolean) c.submit(new Callable<Boolean>() { // from class: tri.2
                @Override // java.util.concurrent.Callable
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public Boolean call() throws Exception {
                    return trd.b(context);
                }
            }).get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)).booleanValue();
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            tos.e("AppUtil", "isAuthorizedInHealth has exception");
            return false;
        }
    }

    public static int d(Context context, String str) {
        if (context == null) {
            tos.e("AppUtil", "getAppUid context is null");
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            tos.e("AppUtil", "getAppUid packageName is null or empty");
            return 0;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 128).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "getAppUid NameNotFoundException");
            return 0;
        }
    }

    public static String e(final Context context) {
        try {
            String str = (String) c.submit(new Callable<String>() { // from class: tri.5
                @Override // java.util.concurrent.Callable
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public String call() throws Exception {
                    return trd.c(context);
                }
            }).get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS);
            tos.b("AppUtil", "getGrsUrl url = " + str);
            return str;
        } catch (InterruptedException unused) {
            tos.e("AppUtil", "getGrsUrl InterruptedException");
            return "";
        } catch (ExecutionException unused2) {
            tos.e("AppUtil", "getGrsUrl ExecutionException");
            return "";
        } catch (TimeoutException unused3) {
            tos.e("AppUtil", "getGrsUrl TimeoutException");
            return "";
        }
    }

    public static int b(Context context) {
        if (context == null) {
            tos.d("AppUtil", "getMetaDataApiLevel context is null");
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            tos.d("AppUtil", "getMetaDataApiLevel PackageManager is null");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                int i = applicationInfo.metaData.getInt("com.huawei.wearengine.service.api_level");
                tos.a("AppUtil", "getMetaDataApiLevel apiLevel:" + i);
                return i;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "getMetaDataApiLevel PackageManager.NameNotFoundException");
        }
        return 0;
    }

    public static boolean b() {
        try {
            return ((Boolean) c.submit(new Callable<Boolean>() { // from class: tri.1
                @Override // java.util.concurrent.Callable
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public Boolean call() throws Exception {
                    return Boolean.valueOf(tpe.c());
                }
            }).get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)).booleanValue();
        } catch (InterruptedException unused) {
            tos.e("AppUtil", "hasDeviceConnectedInfoInThread InterruptedException");
            return false;
        } catch (ExecutionException unused2) {
            tos.e("AppUtil", "hasDeviceConnectedInfoInThread ExecutionException");
            return false;
        } catch (TimeoutException unused3) {
            tos.e("AppUtil", "hasDeviceConnectedInfoInThread TimeoutException");
            return false;
        }
    }

    public static int d(String str) {
        Context a2 = tot.a();
        if (a2 == null) {
            tos.d("AppUtil", "getSdkMetaDataApiLevel context is null");
            return 0;
        }
        PackageManager packageManager = a2.getPackageManager();
        if (packageManager == null) {
            tos.d("AppUtil", "getSdkMetaDataApiLevel PackageManager is null");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                int i = applicationInfo.metaData.getInt("com.huawei.wearengine.sdk.api_level");
                tos.a("AppUtil", "getSdkMetaDataApiLevel apiLevel:" + i);
                return i;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            tos.e("AppUtil", "getSdkMetaDataApiLevel PackageManager.NameNotFoundException");
        }
        return 0;
    }

    public static String e(String str) {
        if (str == null) {
            tos.a("AppUtil", "str is null");
            return Constants.NULL;
        }
        if (TextUtils.isEmpty(str) || str.length() <= 8) {
            tos.a("AppUtil", "str is empty or length less than eight");
            return str;
        }
        return str.substring(0, 4) + "***" + str.substring(str.length() - 4);
    }
}
