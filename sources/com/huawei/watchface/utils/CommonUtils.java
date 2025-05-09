package com.huawei.watchface.utils;

import android.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.ViewCompat;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.health.tradeservice.pay.PayActivity;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.detect.RootDetect;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import com.huawei.secure.android.common.util.FileUtil;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.uikit.hwbutton.widget.HwButton;
import com.huawei.watchface.R$dimen;
import com.huawei.watchface.R$string;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.cm;
import com.huawei.watchface.cu;
import com.huawei.watchface.cv;
import com.huawei.watchface.da;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.utils.permission.PermissionUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class CommonUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final String f11190a = "CommonUtils";
    private static boolean b = true;
    private static int c = -1;
    private static ArrayList<String> d = new ArrayList<>(Arrays.asList("file:///android_asset/stressGame/html/twoVideoPlay.html", "file:///android_asset/stressGame/html/twoVideoPlay_old.html"));
    private static ArrayList<String> e = new ArrayList<>(Arrays.asList("file:///android_asset/operation/MessageH5/html/recommendIndex1.html", "file:///android_asset/operation/MessageH5/html/recommendBohee.html", "file:///android_asset/operation/MessageH5/html/honorBracelet.html", "file:///android_asset/operation/MessageH5/html/recommendIndex2.html", "file:///android_asset/operation/MessageH5/html/huaweiBracelet.html", "file:///android_asset/operation/activity/web/html/activityShare.html?activityId=110", "file:///android_asset/operation/activity/web/html/activityShare.html?activityId=166"));
    private static Boolean f;

    public static boolean z() {
        return true;
    }

    public static String filterFilePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (!a(str) && !FileUtil.filePathIsValidNew(str)) {
                HwLog.e(f11190a, "filterFilePath sourcePath is not Valid");
                return null;
            }
            return new File(str).getCanonicalPath();
        } catch (IOException e2) {
            HwLog.w(f11190a, "filterFilePath IOException :" + HwLog.printException((Exception) e2));
            return null;
        } catch (Exception e3) {
            HwLog.w(f11190a, "filterFilePath Exception :" + HwLog.printException(e3));
            return null;
        }
    }

    public static boolean a(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return TextUtils.isEmpty(str.substring(0, str.lastIndexOf(new File(str.trim()).getName())));
    }

    public static int b(String str) {
        return a(str, 0);
    }

    public static int a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            HwLog.d(f11190a, "stringToInt str is empty");
            return i;
        }
        try {
            if (str.contains(".")) {
                str = SafeString.substring(str, 0, str.indexOf("."));
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            HwLog.e(f11190a, "stringToInt Exception e = " + HwLog.printException((Exception) e2));
            return i;
        }
    }

    public static byte[] a(byte[] bArr) {
        return SHA.shaEncryptByte(bArr, "SHA-256");
    }

    public static String a() {
        return a(false);
    }

    public static String b() {
        return a(true);
    }

    public static String a(boolean z) {
        HwLog.e(f11190a, "getAppIdHex39Above14() is39Above14: " + z);
        if (z) {
            return da.a(123) + da.a(2) + da.a(0) + da.a(4);
        }
        return c();
    }

    public static String c() {
        HwLog.e(f11190a, "getAppIdHex() enter.");
        if (!Environment.sIsAarInTheme) {
            return "";
        }
        return da.a(123) + da.a(2) + da.a(0) + da.a(5);
    }

    public static int a(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static File b(Context context) {
        if (context == null) {
            context = Environment.getApplicationContext();
        }
        File file = null;
        if ("mounted".equals(android.os.Environment.getExternalStorageState()) || !android.os.Environment.isExternalStorageRemovable()) {
            if (PermissionUtil.isAndroidQ()) {
                file = context.getExternalFilesDir(null);
            } else {
                file = android.os.Environment.getExternalStorageDirectory();
            }
        }
        if (file != null) {
            return file;
        }
        HwLog.w(f11190a, "getExternalFilesDirectory:innerContext.getFilesDir");
        return context.getFilesDir();
    }

    public static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean d() {
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext != null) {
            return (applicationContext.getResources().getConfiguration().screenLayout & 15) >= 3;
        }
        HwLog.e(f11190a, "isWidescreen() context is null!!");
        return false;
    }

    public static boolean e() {
        return (a("ro.config.hw_fold_disp", "").isEmpty() && a("persist.sys.fold.disp.size", "").isEmpty()) ? false : true;
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class).invoke(null, str, str2);
            if (invoke instanceof String) {
                str2 = (String) invoke;
            }
        } catch (ClassNotFoundException unused) {
            HwLog.e(f11190a, "getSystemProperties ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            HwLog.e(f11190a, "getSystemProperties IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            HwLog.e(f11190a, "getSystemProperties NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            HwLog.e(f11190a, "getSystemProperties InvocationTargetException");
        }
        HwLog.d(f11190a, "emui get system properties value = " + str2);
        return str2;
    }

    public static void a(final HwButton hwButton) {
        if (hwButton == null) {
            return;
        }
        hwButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.watchface.utils.CommonUtils.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (HwButton.this.getLineCount() > 1) {
                    HwLog.d(CommonUtils.f11190a, "getLineCount more than the 1");
                    HwButton.this.setTextSize(1, 9.0f);
                    HwButton.this.setMaxLines(1);
                    HwButton.this.setEllipsize(TextUtils.TruncateAt.END);
                }
            }
        });
    }

    public static void a(final HwButton hwButton, final HwButton hwButton2) {
        if (hwButton == null || hwButton2 == null) {
            return;
        }
        hwButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.watchface.utils.CommonUtils.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (HwButton.this.getLineCount() > 1) {
                    CommonUtils.c(HwButton.this, hwButton2);
                }
            }
        });
        hwButton2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.watchface.utils.CommonUtils.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (HwButton.this.getLineCount() > 1) {
                    CommonUtils.c(hwButton, HwButton.this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(HwButton hwButton, HwButton hwButton2) {
        HwLog.d(f11190a, "getLineCount > 1");
        hwButton.setTextSize(1, 9.0f);
        hwButton.setMaxLines(1);
        hwButton.setEllipsize(TextUtils.TruncateAt.END);
        hwButton2.setTextSize(1, 9.0f);
        hwButton2.setMaxLines(1);
        hwButton2.setEllipsize(TextUtils.TruncateAt.END);
    }

    public static String a(double d2, int i, int i2) {
        if (i != 1) {
            if (i != 2) {
                return "";
            }
            NumberFormat percentInstance = NumberFormat.getPercentInstance();
            try {
                percentInstance.setRoundingMode(RoundingMode.HALF_UP);
            } catch (Exception e2) {
                HwLog.e(f11190a, "getNumberFormat " + HwLog.printException(e2));
            }
            if (i2 >= 0) {
                percentInstance.setMaximumFractionDigits(i2);
                percentInstance.setMinimumFractionDigits(i2);
            }
            return percentInstance.format(d2 / 100.0d);
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        try {
            numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        } catch (Exception e3) {
            HwLog.e(f11190a, "getNumberFormat Exception" + HwLog.printException(e3));
        }
        if (i2 >= 0) {
            numberFormat.setMaximumFractionDigits(i2);
            numberFormat.setMinimumFractionDigits(i2);
        }
        if (LanguageUtils.c()) {
            numberFormat.setMinimumFractionDigits(0);
        }
        return numberFormat.format(d2);
    }

    public static HashMap<String, WatchResourcesInfo> a(Object obj) throws IllegalArgumentException {
        HashMap<String, WatchResourcesInfo> hashMap = new HashMap<>(32);
        if (!(obj instanceof HashMap)) {
            return hashMap;
        }
        for (Map.Entry entry : ((HashMap) obj).entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (!(value instanceof WatchResourcesInfo)) {
                throw new IllegalArgumentException();
            }
            hashMap.put(str, (WatchResourcesInfo) value);
        }
        return hashMap;
    }

    public static void showPermissionSettingGuide(Context context, String str) {
        if (context == null) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        new cm.a(context).a(R$string.watch_face_permission_required).b(str).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.utils.CommonUtils$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonUtils.lambda$onClick$hianalytics1(view);
            }
        }).a(R$string.IDS_hwh_motiontrack_permission_guide_go_set, new View.OnClickListener() { // from class: com.huawei.watchface.utils.CommonUtils$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonUtils.lambda$onClick$hianalytics2(applicationContext, view);
            }
        }).a().show();
    }

    private static /* synthetic */ void a(View view) {
        HwLog.d(f11190a, "setNegativeButton onclick called String");
    }

    private static /* synthetic */ void a(Context context, View view) {
        HwLog.d(f11190a, "setPositiveButton onclick called String");
        c(context);
    }

    public static void c(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", applicationContext.getPackageName(), null));
        if (b(context, intent)) {
            a(applicationContext, intent);
        }
    }

    public static void a(Context context, Intent intent) {
        if (context == null || intent == null) {
            HwLog.e(f11190a, "startActivity params is null");
            return;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            HwLog.e(f11190a, "startActivity Exception: " + HwLog.printException((Exception) e2));
        } catch (Exception e3) {
            HwLog.e(f11190a, "startActivity Exception: " + HwLog.printException(e3));
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i) {
        if (activity == null || intent == null) {
            HwLog.e(f11190a, "startActivityForResult params is null");
            return;
        }
        try {
            activity.startActivityForResult(intent, i);
        } catch (Exception e2) {
            HwLog.e(f11190a, "startActivityForResult Exception: " + HwLog.printException(e2));
        }
    }

    public static boolean b(Context context, Intent intent) {
        ResolveInfo resolveActivity;
        if (context == null) {
            HwLog.w(f11190a, "setIntentComponent context is null");
            return false;
        }
        if (intent == null) {
            HwLog.w(f11190a, "setIntentComponent intent is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null && (resolveActivity = packageManager.resolveActivity(intent, 65536)) != null) {
            intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
            HwLog.i(f11190a, "setIntentComponent intent true");
            return true;
        }
        HwLog.i(f11190a, "setIntentComponent intent false");
        return false;
    }

    public static void a(Window window, int i, boolean z) {
        if (window == null) {
            return;
        }
        window.setStatusBarColor(i);
        if (z) {
            window.setStatusBarColor(Color.argb(0, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE));
            window.getDecorView().setSystemUiVisibility(9216);
        } else {
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    public static boolean f() {
        String str = f11190a;
        HwLog.d(str, "isNetworkConnected enter");
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext == null) {
            HwLog.w(str, "isNetworkConnected null");
            return false;
        }
        NetworkInfo networkInfo = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            networkInfo = ((ConnectivityManager) applicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
            countDownLatch.countDown();
            if (!countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                HwLog.w(str, "isNetworkConnected time out!");
            }
        } catch (Exception e2) {
            HwLog.e(f11190a, "dealOnPageFinish: InterruptedException " + HwLog.printException(e2));
        }
        HwLog.d(f11190a, "isNetworkConnected end");
        return networkInfo != null && networkInfo.isConnected();
    }

    public static int d(Context context) {
        int i = -1;
        if (context == null) {
            HwLog.w(f11190a, "getNetEnvironment null");
            return -1;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return 1;
            }
            if (type == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
                    i = telephonyManager.getNetworkType();
                }
            } else {
                HwLog.w(f11190a, "type match failed");
            }
        }
        HwLog.d(f11190a, "telephone networkType:" + i);
        return c(i);
    }

    private static int c(int i) {
        int i2;
        String str = f11190a;
        HwLog.d(str, "getNetworkClassByType-start");
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                i2 = 2;
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
                i2 = 3;
                break;
            case 13:
                i2 = 4;
                break;
            default:
                i2 = -1;
                break;
        }
        HwLog.d(str, "getNetworkClassByType-end");
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean e(android.content.Context r10) {
        /*
            java.lang.String r0 = "com.google.android.webview"
            r1 = 1
            if (r10 != 0) goto L6
            return r1
        L6:
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 32
            r2.<init>(r3)
            java.lang.String r3 = "60.0.3112.116"
            r2.add(r3)
            java.lang.String r3 = "60.0.3112.107"
            r2.add(r3)
            java.lang.String r4 = "60.0.3112.78"
            r2.add(r4)
            r4 = 0
            android.content.pm.PackageManager r5 = r10.getPackageManager()     // Catch: java.lang.RuntimeException -> L32 android.content.pm.PackageManager.NameNotFoundException -> L4c
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r0, r1)     // Catch: java.lang.RuntimeException -> L32 android.content.pm.PackageManager.NameNotFoundException -> L4c
            boolean r6 = a(r5)     // Catch: java.lang.RuntimeException -> L32 android.content.pm.PackageManager.NameNotFoundException -> L4c
            boolean r5 = b(r5)     // Catch: java.lang.RuntimeException -> L2e android.content.pm.PackageManager.NameNotFoundException -> L30
            goto L66
        L2e:
            r5 = move-exception
            goto L34
        L30:
            r5 = move-exception
            goto L4e
        L32:
            r5 = move-exception
            r6 = r4
        L34:
            java.lang.String r7 = com.huawei.watchface.utils.CommonUtils.f11190a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "runtimeException "
            r8.<init>(r9)
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            com.huawei.watchface.utils.HwLog.e(r7, r5)
            goto L65
        L4c:
            r5 = move-exception
            r6 = r4
        L4e:
            java.lang.String r7 = com.huawei.watchface.utils.CommonUtils.f11190a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "isErrorWebView: e="
            r8.<init>(r9)
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            com.huawei.watchface.utils.HwLog.i(r7, r5)
        L65:
            r5 = r4
        L66:
            java.lang.String r7 = com.huawei.watchface.utils.CommonUtils.f11190a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = " isSystemUpdateApp : "
            r8.<init>(r9)
            r8.append(r6)
            java.lang.String r9 = "isUserApp : "
            r8.append(r9)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            com.huawei.watchface.utils.HwLog.i(r7, r8)
            java.lang.String r10 = a(r10, r0)
            boolean r0 = r2.contains(r10)
            if (r0 == 0) goto L9b
            java.lang.String r0 = "listErrorVersion.contains(webviewVersion)"
            com.huawei.watchface.utils.HwLog.i(r7, r0)
            if (r6 != 0) goto L9a
            if (r5 != 0) goto L9a
            boolean r10 = r3.equals(r10)
            if (r10 == 0) goto L9b
        L9a:
            return r1
        L9b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.CommonUtils.e(android.content.Context):boolean");
    }

    public static boolean a(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & 128) != 0;
    }

    public static boolean b(PackageInfo packageInfo) {
        return (c(packageInfo) || a(packageInfo)) ? false : true;
    }

    public static boolean c(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & 1) != 0;
    }

    public static String a(Context context, String str) {
        if (context == null || str == null) {
            HwLog.w(f11190a, "getVersion null");
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 16384).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            HwLog.e(f11190a, "NameNotFoundException exception");
            return "";
        } catch (Exception unused2) {
            HwLog.e(f11190a, "getVersion Exception");
            return "";
        }
    }

    public static int getVersionCode() {
        try {
            Application applicationContext = Environment.getApplicationContext();
            return applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 16384).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            HwLog.e(f11190a, "getVersionCode -- NameNotFoundException exception:" + HwLog.printException((Exception) e2));
            return 0;
        } catch (Exception e3) {
            HwLog.e(f11190a, "getVersionCode - Exception:" + HwLog.printException(e3));
            return 0;
        }
    }

    public static int g() {
        int i = c;
        if (i != -1) {
            return i;
        }
        try {
            PackageInfo packageInfo = Environment.getApplicationContext().getPackageManager().getPackageInfo("com.huawei.hwid", 16384);
            if (packageInfo != null) {
                int i2 = packageInfo.versionCode;
                c = i2;
                return i2;
            }
        } catch (Exception e2) {
            HwLog.e(f11190a, "ILocalAccountService getHMSVersion com.huawei.hwid exception " + HwLog.printException(e2));
        }
        return c;
    }

    public static void b(Context context, String str) {
        if (context == null || str == null) {
            HwLog.w(f11190a, "uninstallApk null");
            return;
        }
        Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + str));
        if (b(context, intent)) {
            a(context, intent);
        }
    }

    public static boolean h() {
        return i() && Build.VERSION.SDK_INT > 28;
    }

    public static boolean i() {
        return cu.a();
    }

    public static void f(Context context) {
        if (context == null) {
            HwLog.w(f11190a, "goToNetWorkSettingPage null");
        } else {
            a(context, new Intent("android.settings.SETTINGS"));
        }
    }

    public static boolean j() {
        return cu.b() >= 21;
    }

    public static boolean k() {
        return cu.b() >= 19;
    }

    public static String l() {
        return cu.c();
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("file:///data/data/com.huawei.health/files/plugins/")) {
            str = FileHelper.c(str);
            if (str.startsWith("file:///data/data/com.huawei.health/files/plugins/")) {
                return true;
            }
        }
        return d.contains(str) || j(str);
    }

    private static boolean j(String str) {
        return e.contains(str);
    }

    public static String getAuthorityByUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return uri.getAuthority();
        } catch (Exception e2) {
            HwLog.e(f11190a, "getAuthorityByUri exception: " + HwLog.printException(e2));
            return null;
        }
    }

    public static String a(int i) {
        return String.format(Locale.ENGLISH, "#%06X", Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004a A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            if (r3 != 0) goto Lb
            java.lang.String r3 = com.huawei.watchface.utils.CommonUtils.f11190a
            java.lang.String r4 = "checkAppIsInstall null"
            com.huawei.watchface.utils.HwLog.w(r3, r4)
            return r0
        Lb:
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: java.lang.RuntimeException -> L16 android.content.pm.PackageManager.NameNotFoundException -> L2f
            r1 = 16384(0x4000, float:2.2959E-41)
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r1)     // Catch: java.lang.RuntimeException -> L16 android.content.pm.PackageManager.NameNotFoundException -> L2f
            goto L48
        L16:
            r3 = move-exception
            java.lang.String r4 = com.huawei.watchface.utils.CommonUtils.f11190a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "runtimeException "
            r1.<init>(r2)
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.huawei.watchface.utils.HwLog.e(r4, r3)
            goto L47
        L2f:
            r3 = move-exception
            java.lang.String r4 = com.huawei.watchface.utils.CommonUtils.f11190a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "NameNotFoundException "
            r1.<init>(r2)
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.huawei.watchface.utils.HwLog.e(r4, r3)
        L47:
            r3 = 0
        L48:
            if (r3 == 0) goto L4b
            r0 = 1
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.CommonUtils.c(android.content.Context, java.lang.String):boolean");
    }

    public static void d(Context context, String str) {
        String str2 = f11190a;
        HwLog.i(str2, "downloadApkByPackageName");
        if (context == null || str == null) {
            HwLog.w(str2, "downloadApkByPackageName null");
            return;
        }
        try {
            Uri parse = Uri.parse("market://details?id=" + str);
            if (parse != null) {
                HwLog.i(str2, "downloadApkByPackageName startActivity");
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
                intent.addFlags(268435456);
                if (b(context, intent)) {
                    context.startActivity(intent);
                }
            } else {
                HwLog.e(str2, "uri is null!");
            }
        } catch (ActivityNotFoundException e2) {
            HwLog.e(f11190a, "ActivityNotFoundException e = " + HwLog.printException((Exception) e2));
        } catch (Exception unused) {
            HwLog.e(f11190a, "downloadApkByPackageName Exception e = ");
        }
    }

    public static int b(String str, String str2) {
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
        int i = 0;
        for (int i2 = 0; i2 < min; i2++) {
            i = split[i2].length() - split2[i2].length();
            if (i != 0 || (i = split[i2].compareTo(split2[i2])) != 0) {
                break;
            }
        }
        return i != 0 ? i : split.length - split2.length;
    }

    public static String g(Context context) {
        return "and_health_" + m();
    }

    public static String m() {
        return cv.b();
    }

    public static int[] n() {
        DisplayMetrics displayMetrics = Environment.getApplicationContext().getResources().getDisplayMetrics();
        return new int[]{displayMetrics.heightPixels, displayMetrics.widthPixels};
    }

    public static int h(Context context) {
        if (context == null) {
            HwLog.e(f11190a, " updateNotchInfos context is null !");
            return 0;
        }
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int i(Context context) {
        Resources resources;
        int a2 = DensityUtil.a(R$dimen.dp_48);
        if (context == null) {
            return a2;
        }
        try {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            if (theme != null && theme.resolveAttribute(R.attr.actionBarSize, typedValue, true) && (resources = context.getResources()) != null) {
                int complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue.data, resources.getDisplayMetrics());
                if (complexToDimensionPixelSize > 0) {
                    return complexToDimensionPixelSize;
                }
            }
        } catch (Exception e2) {
            HwLog.e(f11190a, "getToolBarHeight Exception: " + HwLog.printException(e2));
        }
        return a2;
    }

    public static String o() {
        return DensityUtil.getStringById(R$string.is_pad);
    }

    public static int p() {
        if (q()) {
            TypedValue typedValue = new TypedValue();
            if (Environment.a() == null) {
                return DensityUtil.a(R$dimen.dp_16);
            }
            if (Environment.a().getTheme() == null) {
                return DensityUtil.a(R$dimen.dp_16);
            }
            Environment.a().getTheme().resolveAttribute(33620168, typedValue, true);
            int i = typedValue.resourceId;
            if (i == 0 || Environment.a().getResources() == null) {
                return DensityUtil.a(R$dimen.dp_16);
            }
            return Environment.a().getResources().getDimensionPixelSize(i);
        }
        return DensityUtil.a(R$dimen.dp_16);
    }

    public static boolean q() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static int j(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static byte[] d(String str) {
        if (str == null) {
            return new byte[0];
        }
        int length = str.length();
        if (length % 2 != 0) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.getDefault());
        for (int i = 0; i < length; i++) {
            char charAt = upperCase.charAt(i);
            if (('0' > charAt || charAt > '9') && ('A' > charAt || charAt > 'F')) {
                return new byte[0];
            }
        }
        int i2 = length / 2;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + 1;
            bArr2[0] = (byte) upperCase.charAt(i3);
            i3 += 2;
            bArr2[1] = (byte) upperCase.charAt(i5);
            for (int i6 = 0; i6 < 2; i6++) {
                byte b2 = bArr2[i6];
                bArr2[i6] = (byte) (b2 - ((65 > b2 || b2 > 70) ? (byte) 48 : (byte) 55));
            }
            bArr[i4] = (byte) ((bArr2[0] << 4) | bArr2[1]);
        }
        return bArr;
    }

    public static String b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        char[] cArr2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr[i] = cArr2[(b2 >>> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b2 & BaseType.Obj];
        }
        return new String(cArr);
    }

    public static byte[] e(String str) {
        if (str == null) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            HwLog.e(f11190a, "stringAsBytes fail");
            return new byte[0];
        }
    }

    public static void a(Activity activity, int i) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType(Constants.IMAGE_TYPE);
        startActivityForResult(activity, intent, i);
    }

    public static Bitmap f(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                int lastIndexOf = str.lastIndexOf(",");
                String substring = SafeString.substring(str, lastIndexOf + 1);
                HwLog.e(f11190a, "convertStr2Bitmap indexOf：" + lastIndexOf);
                byte[] decode = Base64.getDecoder().decode(substring);
                return a(decode, 0, decode.length);
            } catch (OutOfMemoryError e2) {
                HwLog.e(f11190a, "convertStr2Bitmap OutOfMemoryError " + HwLog.printException((Error) e2));
            } catch (RuntimeException e3) {
                HwLog.e(f11190a, "convertStr2Bitmap ：" + HwLog.printException((Exception) e3));
            } catch (Exception e4) {
                HwLog.e(f11190a, "convertStr2Bitmap Exception：" + HwLog.printException(e4));
            }
        }
        return null;
    }

    public static Bitmap a(byte[] bArr, int i, int i2) {
        try {
            return a(bArr, i, i2, (BitmapFactory.Options) null);
        } catch (Exception e2) {
            HwLog.e(f11190a, "getSafeDecodeByteArray Exception" + HwLog.printException(e2));
            return null;
        } catch (OutOfMemoryError e3) {
            HwLog.e(f11190a, "getSafeDecodeByteArray OutOfMemoryError" + HwLog.printException((Error) e3));
            return null;
        }
    }

    public static Bitmap a(byte[] bArr, int i, int i2, BitmapFactory.Options options) {
        try {
            return BitmapFactory.decodeByteArray(bArr, i, i2, options);
        } catch (Exception e2) {
            HwLog.e(f11190a, "getSafeDecodeByteArray Exception " + HwLog.printException(e2));
            return null;
        } catch (OutOfMemoryError e3) {
            HwLog.e(f11190a, "getSafeDecodeByteArray OutOfMemoryError " + HwLog.printException((Error) e3));
            if (options == null) {
                options = new BitmapFactory.Options();
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, i, i2, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            options.inJustDecodeBounds = false;
            DisplayMetrics displayMetrics = Environment.getApplicationContext().getResources().getDisplayMetrics();
            options.inSampleSize = a(i3, i4, displayMetrics.widthPixels, displayMetrics.heightPixels);
            return BitmapFactory.decodeByteArray(bArr, i, i2, options);
        }
    }

    private static int a(int i, int i2, int i3, int i4) {
        int i5 = 1;
        while (true) {
            if (i2 / i5 <= i4 && i / i5 <= i3) {
                return i5;
            }
            i5 *= 2;
        }
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                HwLog.e(f11190a, "create folder failed.");
            }
            return mkdirs;
        } catch (Exception e2) {
            HwLog.e(f11190a, "create folder exception:" + HwLog.printException(e2));
            return false;
        }
    }

    public static void b(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            if (!file.delete()) {
                HwLog.e(f11190a, "delete file false:" + c(file));
                return;
            }
            HwLog.w(f11190a, "delete file success");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                HwLog.e(f11190a, ": delete file false :" + c(file2));
            }
        }
        if (!file.exists() || file.delete()) {
            return;
        }
        HwLog.e(f11190a, "FatherFile delete false");
    }

    public static String c(File file) {
        if (file == null) {
            return null;
        }
        return g(file.getPath());
    }

    public static String g(String str) {
        int lastIndexOf;
        return (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : SafeString.substring(str, lastIndexOf + 1);
    }

    public static boolean k(Context context) {
        return 1 == r();
    }

    public static int r() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) Environment.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    public static boolean l(Context context) {
        if (!(context instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) context;
        return (activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static void b(boolean z) {
        b = z;
    }

    public static boolean s() {
        return b;
    }

    public static boolean t() {
        return !b;
    }

    public static String u() {
        Object a2 = Environment.a("activity");
        if (!(a2 instanceof ActivityManager)) {
            return "";
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) a2).getRunningTasks(1);
        String className = !ArrayUtils.isEmpty(runningTasks) ? runningTasks.get(0).topActivity.getClassName() : null;
        return className == null ? "" : className;
    }

    public static String v() {
        try {
            PackageInfo packageInfo = Environment.getApplicationContext().getPackageManager().getPackageInfo("com.huawei.hwid", 16384);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception e2) {
            HwLog.e(f11190a, "ILocalAccountService getHMSVersion com.huawei.hwid exception " + HwLog.printException(e2));
            return "";
        }
    }

    public static String getAuthority() {
        return Environment.b() + ".fileprovider";
    }

    public static int w() {
        return Environment.sIsAarInTheme ? 12 : 4;
    }

    public static int b(int i) {
        HwLog.w(f11190a, "getTlvHeadLengthNew() commandId: " + i);
        if (i < 14) {
            return w();
        }
        return 12;
    }

    public static boolean d(File file) {
        String[] list;
        if (file == null) {
            HwLog.w(f11190a, "deleteFiles null");
            return false;
        }
        if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
            for (String str : list) {
                if (!d(new File(file, str))) {
                    return false;
                }
            }
        }
        try {
            return file.delete();
        } catch (SecurityException e2) {
            HwLog.e(f11190a, "SecurityException=" + HwLog.printException((Exception) e2));
            return false;
        } catch (Exception unused) {
            HwLog.e(f11190a, "deleteFiles Exception");
            return false;
        }
    }

    public static boolean x() {
        return cu.b() > 17;
    }

    public static boolean y() {
        return NotificationManagerCompat.from(Environment.getApplicationContext()).areNotificationsEnabled();
    }

    public static String h(String str) {
        return TextUtils.isEmpty(str) ? str : str.trim();
    }

    public static boolean m(Context context) {
        try {
            context.getPackageManager().getApplicationInfo(HMSPackageManager.getInstance(context).getHMSPackageName(), 128);
            HwLog.i(f11190a, "checkIsInstallHuaweiAccount() result is true.");
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            HwLog.i(f11190a, "checkIsInstallHuaweiAccount() NameNotFoundException.");
            return false;
        } catch (RuntimeException unused2) {
            HwLog.i(f11190a, "checkIsInstallHuaweiAccount() RuntimeException.");
            return false;
        }
    }

    public static boolean a(String str, Object... objArr) {
        if (objArr == null) {
            HwLog.i(str, "checkObjectIsNull objects is null");
            return true;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static int A() {
        return B() ? 1 : 0;
    }

    public static boolean B() {
        return !BaseApplication.APP_PACKAGE_GOOGLE_HEALTH.equals(Environment.b()) && "1".equals(NewSystemParamManager.a("client_support_membership"));
    }

    public static String C() {
        String systemParam = NewSystemParamManager.getSystemParam("client_support_freetab", "1");
        HwLog.i(f11190a, "isCloudConfigSupported: " + systemParam);
        return systemParam;
    }

    public static boolean D() {
        String systemParam = NewSystemParamManager.getSystemParam("client_support_weardial", "0");
        HwLog.i(f11190a, "isCloudConfigSupportedWear: " + systemParam);
        return systemParam.equalsIgnoreCase("1");
    }

    public static boolean E() {
        String systemParam = NewSystemParamManager.getSystemParam("client_support_humanportrait", "0");
        HwLog.i(f11190a, "isCloudConfigSupportedHumanPortrait: " + systemParam);
        return systemParam.equalsIgnoreCase("1");
    }

    public static boolean F() {
        boolean z;
        Boolean bool = f;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            z = RootDetect.isRoot();
        } catch (Throwable th) {
            HwLog.e(f11190a, "isRoot throwable" + HwLog.printException(th));
            z = false;
        }
        Boolean valueOf = Boolean.valueOf(z);
        f = valueOf;
        return valueOf.booleanValue();
    }

    public static void a(String str, Activity activity) {
        if (activity == null) {
            HwLog.i(f11190a, "setFontColor() mActivity == null");
            return;
        }
        try {
            if ("1".equalsIgnoreCase(str)) {
                activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                HwLog.i(f11190a, "setFontColor() isDark true");
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(1280);
                HwLog.i(f11190a, "setFontColor() isDark false");
            }
        } catch (Throwable th) {
            HwLog.i(f11190a, "setFontColor Exception，" + HwLog.printException(th));
        }
    }

    public static boolean isAndroid11() {
        return Build.VERSION.SDK_INT > 29;
    }

    public static boolean isAndroid13() {
        return Build.VERSION.SDK_INT >= 33;
    }

    public static boolean isAndroid14() {
        return Build.VERSION.SDK_INT >= 34;
    }

    public static boolean G() {
        return Build.VERSION.SDK_INT >= 31;
    }

    public static int b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        String trim = str.trim();
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e2) {
            HwLog.e(f11190a, "getInteger ex=" + e2.getMessage());
            return (int) a(trim, i, true);
        }
    }

    private static float a(String str, float f2, boolean z) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e2) {
            if (!z) {
                HwLog.e(f11190a, "getFloat ex=" + e2.getMessage());
            }
            return f2;
        }
    }

    public static <T> ArrayList<T> a(Parcelable.Creator<T> creator, Parcel parcel) {
        String str = f11190a;
        HwLog.w(str, "createTypedArrayList list");
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        if (readInt >= 1024) {
            HwLog.w(str, "createTypedArrayList list is over 1024");
            readInt = 1024;
        }
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList(readInt);
        while (readInt > 0) {
            anonymousClass6.add(parcel.readTypedObject(creator));
            readInt--;
        }
        return anonymousClass6;
    }

    public static final <T extends Parcelable> void a(Parcel parcel, List<T> list) {
        if (list != null) {
            if (list.size() > 1024) {
                list = null;
            }
            parcel.writeTypedList(list);
            return;
        }
        parcel.writeTypedList(null);
    }

    public static boolean i(String str) {
        Double d2;
        try {
            HwLog.i(f11190a, "amountStr : " + str);
            d2 = Double.valueOf(str);
        } catch (NumberFormatException e2) {
            HwLog.i(f11190a, "NumberFormatException " + HwLog.printException((Exception) e2));
            d2 = null;
        }
        boolean z = d2 != null && Double.compare(d2.doubleValue(), 0.0d) == 0;
        HwLog.i(f11190a, "isZero:" + z);
        return z;
    }

    public static String a(Throwable th) {
        String str = "";
        if (th != null && !HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            try {
                StringWriter stringWriter = new StringWriter();
                try {
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    try {
                        th.printStackTrace(printWriter);
                        str = stringWriter.toString();
                        printWriter.close();
                        stringWriter.close();
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e2) {
                HwLog.i(f11190a, "getStackTrace Exception : " + HwLog.printException(e2));
            }
        }
        return str;
    }

    static void lambda$onClick$hianalytics2(Context context, View view) {
        a(context, view);
        ViewClickInstrumentation.clickOnView(view);
    }

    static void lambda$onClick$hianalytics1(View view) {
        a(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
