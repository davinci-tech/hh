package health.compact.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class Utils {
    private static final Charset d = Charset.forName("UTF-8");

    /* renamed from: a, reason: collision with root package name */
    private static int f13145a = 3;
    private static int c = 3;
    private static int e = 1;
    private static String b = "";
    private static long f = 0;

    private Utils() {
    }

    public static void d(int i) {
        f13145a = i;
    }

    public static int b() {
        return f13145a;
    }

    public static int a() {
        return c;
    }

    public static int c() {
        return e;
    }

    public static void e(int i) {
        e = i;
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, d);
    }

    public static String e(Context context) {
        File file = new File(context.getFilesDir() + File.separator + "photos" + File.separator + "avater");
        if (!file.exists() && !file.mkdirs()) {
            com.huawei.hwlogsmodel.LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "getHeadPhotosPath Creat mkdirs failure");
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException unused) {
            com.huawei.hwlogsmodel.LogUtil.b(com.huawei.hianalytics.core.transport.Utils.TAG, "getHeadPhotosPath IOException");
            return "";
        }
    }

    public static boolean f() {
        Boolean bool;
        Boolean y = EnvironmentUtils.c() ? CommonUtil.y() : null;
        if (y != null) {
            com.huawei.hwlogsmodel.LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "isBrowseMode, isBrowseMode = ", y);
            return y.booleanValue();
        }
        Context context = BaseApplication.getContext();
        String e2 = KeyValDbManager.b(context).e("is_browse_mode_or_not");
        if (!TextUtils.isEmpty(e2)) {
            bool = Boolean.valueOf(Boolean.parseBoolean(e2));
        } else {
            Boolean valueOf = Boolean.valueOf(TextUtils.isEmpty(KeyValDbManager.b(context).e("user_id")));
            LoginInit.getInstance(BaseApplication.getContext()).setIsBrowseModeToPd(context, valueOf.booleanValue());
            bool = valueOf;
        }
        com.huawei.hwlogsmodel.LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "isBrowseMode(), isBrowseMode = ", bool);
        return bool.booleanValue();
    }

    public static boolean g() {
        return CloudUtils.d();
    }

    public static boolean o() {
        return g();
    }

    public static boolean i() {
        return CloudUtils.a();
    }

    public static boolean n() {
        String str;
        if (i()) {
            str = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
            com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "Account country :", str);
        } else {
            str = null;
        }
        if ("TW".equalsIgnoreCase(str)) {
            return true;
        }
        String country = Locale.getDefault().getCountry();
        com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "Locale country :", country);
        return "TW".equalsIgnoreCase(country);
    }

    public static int d() {
        return CommonUtil.h(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
    }

    public static boolean h() {
        return d() != -1;
    }

    public static boolean l() {
        return o() && !i();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(android.content.Context r6) {
        /*
            java.lang.String r0 = "isHmsUsableVersion"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Utils"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            if (r6 != 0) goto Lf
            return r0
        Lf:
            boolean r2 = health.compact.a.CommonUtil.bh()
            r3 = 1
            if (r2 == 0) goto L20
            java.lang.String r6 = "isHmsUsableVersion isEmui"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            return r3
        L20:
            int r2 = android.os.Build.VERSION.SDK_INT
            r4 = 29
            if (r2 >= r4) goto L30
            java.lang.String r6 = "isHmsUsableVersion Build.VERSION_CODES.Q"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            return r3
        L30:
            java.lang.String r2 = "com.android.vending"
            boolean r2 = health.compact.a.CommonUtil.f(r6, r2)
            if (r2 != 0) goto L42
            java.lang.String r6 = "isHmsUsableVersion no google play"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            return r3
        L42:
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            java.lang.String r2 = health.compact.a.CommonUtil.p()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r2, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            r2 = 2
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            java.lang.String r5 = "isHmsUsableVersion packageInfo="
            r4[r0] = r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            r4[r3] = r6     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            int r6 = r6.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6c
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            java.lang.String r4 = "isHmsUsableVersion versionCode="
            r2[r0] = r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            r2[r3] = r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            goto L76
        L6c:
            r6 = r0
        L6d:
            java.lang.String r2 = "isHmsUsableVersion NameNotFoundException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r2)
        L76:
            r2 = 30003300(0x1c9d064, float:7.4134765E-38)
            if (r6 <= r2) goto L7c
            return r3
        L7c:
            if (r6 != 0) goto L8e
            boolean r6 = i()
            if (r6 != 0) goto L8e
            java.lang.String r6 = "isHmsUsableVersion no cloud"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            return r3
        L8e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.Utils.b(android.content.Context):boolean");
    }

    public static boolean c(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
        } catch (PackageManager.NameNotFoundException e2) {
            com.huawei.hwlogsmodel.LogUtil.b(com.huawei.hianalytics.core.transport.Utils.TAG, "isApplicationInstalled exception ", e2);
        }
        return context.getPackageManager().getPackageInfo(str, 0) != null;
    }

    public static boolean c(File file) {
        if (file == null || !file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.h(com.huawei.hianalytics.core.transport.Utils.TAG, "deleteFolder folder is null or not exist");
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    c(file2);
                }
            }
        } else {
            com.huawei.hwlogsmodel.LogUtil.h(com.huawei.hianalytics.core.transport.Utils.TAG, "deleteFolder folder is other type");
        }
        return file.delete();
    }

    public static void d(String str, String str2) {
        File file = new File(str);
        if (!CommonUtil.c(file)) {
            com.huawei.hwlogsmodel.LogUtil.h(com.huawei.hianalytics.core.transport.Utils.TAG, "moveFiles source file path invalid");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            com.huawei.hwlogsmodel.LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "moveFiles mkdir results:", Boolean.valueOf(file2.mkdirs()));
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (file3.isDirectory()) {
                d(file3.getPath(), str2 + File.separator + file3.getName());
            }
            if (file3.isFile()) {
                z &= file3.renameTo(new File(str2 + File.separator + file3.getName()));
            }
            z &= file3.delete();
        }
        com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "sourceFile isDelete", Boolean.valueOf(file.delete() & z));
    }

    public static List<String> b(String str) {
        ArrayList arrayList = new ArrayList(16);
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().lastIndexOf(".") != -1) {
                    arrayList.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
                }
            }
        }
        return arrayList;
    }

    public static String c(String str) {
        InputStreamReader inputStreamReader;
        IOException iOException;
        InputStreamReader inputStreamReader2;
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h(com.huawei.hianalytics.core.transport.Utils.TAG, "readJson path is empty");
            return "";
        }
        FileInputStream fileInputStream3 = null;
        try {
            fileInputStream = new FileInputStream(new File(str));
            try {
                inputStreamReader2 = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            } catch (IOException e2) {
                fileInputStream2 = fileInputStream;
                iOException = e2;
                inputStreamReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = null;
                fileInputStream3 = fileInputStream;
                IoUtils.e(fileInputStream3);
                IoUtils.e(inputStreamReader);
                throw th;
            }
            try {
                StringBuilder sb = new StringBuilder(16);
                while (true) {
                    int read = inputStreamReader2.read();
                    if (read == -1) {
                        break;
                    }
                    sb.append((char) read);
                }
                str2 = sb.toString();
            } catch (IOException e3) {
                fileInputStream2 = fileInputStream;
                iOException = e3;
                fileInputStream3 = fileInputStream2;
                try {
                    com.huawei.hwlogsmodel.LogUtil.b(com.huawei.hianalytics.core.transport.Utils.TAG, "readJson ioException is ", ExceptionUtils.d(iOException));
                    fileInputStream = fileInputStream3;
                    IoUtils.e(fileInputStream);
                    IoUtils.e(inputStreamReader2);
                    return str2;
                } catch (Throwable th3) {
                    fileInputStream = fileInputStream3;
                    th = th3;
                    inputStreamReader = inputStreamReader2;
                    th = th;
                    fileInputStream3 = fileInputStream;
                    IoUtils.e(fileInputStream3);
                    IoUtils.e(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = inputStreamReader2;
                th = th;
                fileInputStream3 = fileInputStream;
                IoUtils.e(fileInputStream3);
                IoUtils.e(inputStreamReader);
                throw th;
            }
        } catch (IOException e4) {
            iOException = e4;
            inputStreamReader2 = null;
        } catch (Throwable th5) {
            th = th5;
            inputStreamReader = null;
            IoUtils.e(fileInputStream3);
            IoUtils.e(inputStreamReader);
            throw th;
        }
        IoUtils.e(fileInputStream);
        IoUtils.e(inputStreamReader2);
        return str2;
    }

    public static boolean c(Context context) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            accountInfo = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        }
        com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "isRussiaCountry isInEuAccountArea() country=", accountInfo);
        return "RU".equals(accountInfo);
    }

    public static boolean a(Context context) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            accountInfo = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        }
        com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "isJapanCountry, country=", accountInfo);
        return "JP".equals(accountInfo);
    }

    public static boolean k() {
        return CompileParameterUtil.a("SUPPORT_HEALTH_MODEL", false) && !CompileParameterUtil.a("GOOGLE_PLAY_OEM_BETTERME", false);
    }

    public static boolean j() {
        return LanguageUtil.m(BaseApplication.getContext()) && !o();
    }

    public static String e() {
        if (System.currentTimeMillis() - f > 3600000 || TextUtils.isEmpty(b)) {
            b = GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl");
        }
        return b;
    }

    public static boolean m() {
        String b2 = SystemProperties.b(CountryCodeBean.VENDOR_SYSTEMPROP, "");
        String b3 = SystemProperties.b(CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP, "");
        boolean z = !TextUtils.isEmpty(b2) && "demo".equalsIgnoreCase(b2);
        boolean z2 = !TextUtils.isEmpty(b2) && "demo".equalsIgnoreCase(b3);
        String string = Settings.Secure.getString(BaseApplication.getContext().getContentResolver(), "DemoVersion");
        com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "vendor: ", b2, " country: ", b3, " installedString: ", string);
        return z || z2 || (!TextUtils.isEmpty(string) && string.equals("true"));
    }
}
