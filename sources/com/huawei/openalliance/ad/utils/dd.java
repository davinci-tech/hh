package com.huawei.openalliance.ad.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jk;
import com.huawei.up.utils.ErrorCode;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class dd {
    public static int a(boolean z, boolean z2) {
        return z2 ? z ? R.drawable._2131428573_res_0x7f0b04dd : R.drawable._2131428594_res_0x7f0b04f2 : z ? R.drawable._2131428574_res_0x7f0b04de : R.drawable._2131428598_res_0x7f0b04f6;
    }

    public static boolean a() {
        return true;
    }

    public static boolean a(int i) {
        return 1 == i || 9 == i;
    }

    private static int b(int i) {
        return (i == 1 || i == 2) ? 9 : 1;
    }

    private static int c(int i) {
        return (i == 2 || i == 3) ? 8 : 0;
    }

    public static Activity z(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static int y(Context context) {
        try {
            int identifier = context.getResources().getIdentifier("hw_multiwindow_height_of_drag_bar", "dimen", "androidhwext");
            if (identifier > 0) {
                return context.getResources().getDimensionPixelSize(identifier);
            }
        } catch (Throwable th) {
            ho.c("SystemUtil", "getMultiWindowDragBarHeight " + th.getClass().getSimpleName());
        }
        return 0;
    }

    public static boolean x(Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            return cd.a(context, Constants.POST_NOTIFICATIONS);
        }
        return true;
    }

    public static int w(Context context) {
        String str;
        if (!com.huawei.openalliance.ad.bz.a(context).r()) {
            return 0;
        }
        try {
            return 1 - Settings.Secure.getInt(context.getContentResolver(), "pure_mode_state");
        } catch (Settings.SettingNotFoundException unused) {
            str = "get pureModeState error, setting not found.";
            ho.d("SystemUtil", str);
            return 0;
        } catch (Throwable unused2) {
            str = "get pureModeState error.";
            ho.d("SystemUtil", str);
            return 0;
        }
    }

    public static boolean v(Context context) {
        try {
            return context.getPackageManager().canRequestPackageInstalls();
        } catch (Throwable th) {
            ho.c("SystemUtil", "canInstallPackage exception %s", th.getClass().getSimpleName());
            return true;
        }
    }

    public static String u(Context context) {
        if (context == null) {
            return "";
        }
        try {
            if (TextUtils.isEmpty(t(context))) {
                return G(context) + Constants.LINK + H(context).toUpperCase(Locale.getDefault());
            }
            return G(context) + Constants.LINK + t(context) + Constants.LINK + H(context).toUpperCase(Locale.getDefault());
        } catch (Throwable unused) {
            ho.c("SystemUtil", " getLanguageCode error");
            return "";
        }
    }

    public static String t(Context context) {
        return context.getResources().getConfiguration().locale.getScript();
    }

    public static String s(Context context) {
        if (context == null) {
            return null;
        }
        try {
            int color = context.getResources().getColor(R.color._2131297934_res_0x7f09068e);
            StringBuffer stringBuffer = new StringBuffer("#");
            String hexString = Integer.toHexString(Color.alpha(color));
            String hexString2 = Integer.toHexString(Color.red(color));
            String hexString3 = Integer.toHexString(Color.green(color));
            String hexString4 = Integer.toHexString(Color.blue(color));
            String b = b(hexString);
            stringBuffer.append(b).append(b(hexString2)).append(b(hexString3)).append(b(hexString4));
            ho.a("SystemUtil", " color=%s", stringBuffer.toString());
            return stringBuffer.toString().toUpperCase(Locale.ENGLISH);
        } catch (Exception e) {
            ho.b("SystemUtil", "catch theme color exception:" + e.getClass().getName());
            return null;
        }
    }

    public static boolean r(Context context) {
        return (Build.VERSION.SDK_INT > 28 && F(context) == 2) || q(context);
    }

    public static boolean q(Context context) {
        return context != null && Constants.DARK_THEME_COLOR.equalsIgnoreCase(s(context));
    }

    public static boolean p(Context context) {
        return Build.VERSION.SDK_INT == 28 && q(context);
    }

    public static boolean o(Context context) {
        if (context == null) {
            return false;
        }
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(10);
            if (runningTasks == null || runningTasks.size() <= 0) {
                return false;
            }
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            boolean equals = runningTaskInfo.topActivity.getPackageName().equals(context.getPackageName());
            if (!equals) {
                return false;
            }
            String runningTaskInfo2 = runningTaskInfo.toString();
            if (runningTaskInfo2.contains("isVisible=true")) {
                return true;
            }
            if (runningTaskInfo2.contains("isVisible=false")) {
                return false;
            }
            return equals;
        } catch (Throwable th) {
            ho.c("SystemUtil", "judge Foreground err, %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean n(Context context) {
        if (context == null) {
            return false;
        }
        return !o(context);
    }

    public static boolean m(Context context) {
        try {
            return com.huawei.openalliance.ad.cb.a(context).b();
        } catch (Throwable th) {
            ho.c("SystemUtil", "isInMultiWindowMode " + th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean l(Context context) {
        return a(k(context));
    }

    public static int k(Context context) {
        Display defaultDisplay;
        if (context == null) {
            return 1;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            ho.d("SystemUtil", "Failed to get display orientation info.");
            return context.getResources().getConfiguration().orientation == 2 ? 0 : 1;
        }
        int abs = Math.abs(windowManager.getDefaultDisplay().getWidth());
        int abs2 = Math.abs(windowManager.getDefaultDisplay().getHeight());
        int rotation = defaultDisplay.getRotation();
        return abs < abs2 ? b(rotation) : c(rotation);
    }

    public static int j(Context context) {
        int g = g(context);
        if (g > 0) {
            return g / 2;
        }
        return 36;
    }

    public static boolean i(Context context) {
        StringBuilder sb;
        try {
        } catch (Settings.SettingNotFoundException e) {
            e = e;
            sb = new StringBuilder("isNotchEnable error:");
            sb.append(e.getClass().getSimpleName());
            ho.b("SystemUtil", sb.toString());
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("isNotchEnable Throwable:");
            sb.append(e.getClass().getSimpleName());
            ho.b("SystemUtil", sb.toString());
        }
        return Settings.Secure.getInt(context.getContentResolver(), "display_notch_status") == 0;
    }

    public static boolean h() {
        return c() && !Constants.URDU_LANG.equalsIgnoreCase(Locale.getDefault().getLanguage());
    }

    public static void h(Context context) {
        String str;
        if (Build.VERSION.SDK_INT >= 28) {
            String e = e();
            ho.b("SystemUtil", "setWebDataDir processName: " + e);
            try {
                if (TextUtils.isEmpty(e) || e.equals(context.getPackageName())) {
                    return;
                }
                WebView.setDataDirectorySuffix(e);
            } catch (IllegalStateException unused) {
                str = "setDataDirectorySuffix already initialized";
                ho.d("SystemUtil", str);
            } catch (Exception unused2) {
                str = "setDataDirectorySuffix Exception";
                ho.d("SystemUtil", str);
            }
        }
    }

    public static boolean g() {
        String a2 = a("ro.product.locale.region");
        if (!TextUtils.isEmpty(a2)) {
            return "cn".equalsIgnoreCase(a2);
        }
        String a3 = a(SystemPropertyValues.PROP_LOCALE_KEY);
        if (!TextUtils.isEmpty(a3)) {
            return a3.toLowerCase(Locale.ENGLISH).contains("cn");
        }
        String d = d();
        if (TextUtils.isEmpty(d)) {
            return false;
        }
        return "cn".equalsIgnoreCase(d);
    }

    public static int g(Context context) {
        Resources resources;
        int identifier;
        if (context != null && (identifier = (resources = context.getResources()).getIdentifier("navigation_bar_height", "dimen", OsType.ANDROID)) > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static int[] f(View view) {
        int[] iArr = new int[2];
        if (view != null) {
            view.getLocationOnScreen(iArr);
        }
        return iArr;
    }

    public static long f() {
        try {
            return SystemClock.elapsedRealtimeNanos() / 1000;
        } catch (Throwable th) {
            ho.b("SystemUtil", "elapsedRealtimeMillis " + th.getClass().getSimpleName());
            return 0L;
        }
    }

    public static int f(Context context) {
        Resources resources;
        int identifier;
        if (context != null && (identifier = (resources = context.getResources()).getIdentifier("navigation_bar_height", "dimen", OsType.ANDROID)) > 0 && C(context) && E(context)) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static String e(View view) {
        int[] f = f(view);
        return String.format("%s,%s", Integer.valueOf(f[0]), Integer.valueOf(f[1]));
    }

    public static String e(Context context) {
        String str;
        if (context == null) {
            str = "getLocalCountry context is null";
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String simCountryIso = telephonyManager.getSimCountryIso();
                return TextUtils.isEmpty(simCountryIso) ? "" : simCountryIso.toUpperCase(Locale.ENGLISH);
            }
            str = "getLocalCountry tm is null";
        }
        ho.b("SystemUtil", str);
        return "";
    }

    public static String e() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            ho.c("SystemUtil", "get pro name " + th.getClass().getSimpleName());
            return "";
        }
    }

    public static String d() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.getCountry() : "";
    }

    public static Context d(Context context) {
        try {
            int identifier = context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
            return identifier > 0 ? new ContextThemeWrapper(context, identifier) : context;
        } catch (RuntimeException unused) {
            ho.c("SystemUtil", "getEMUIAppContext exception");
            return context;
        }
    }

    public static Activity d(View view) {
        if (view == null) {
            return null;
        }
        return z(view.getContext());
    }

    public static int[] c(View view) {
        return !a(view) ? new int[0] : new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    public static boolean c(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public static boolean c() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int[] b(jk jkVar) {
        View view;
        if (jkVar instanceof View) {
            view = (View) jkVar;
        } else {
            view = null;
        }
        return f(view);
    }

    public static int[] b(View view) {
        if (!a(view)) {
            return new int[0];
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr;
    }

    public static boolean b(Context context, Intent intent) {
        try {
            return !bg.a(context.getPackageManager().queryIntentActivities(intent, 0));
        } catch (Throwable th) {
            ho.c("SystemUtil", "isIntentAvailable: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean b(Context context) {
        KeyguardManager keyguardManager;
        if (context == null || (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) == null) {
            return false;
        }
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean b(Activity activity) {
        if (activity == null) {
            return false;
        }
        try {
            return com.huawei.openalliance.ad.cb.a(activity.getApplicationContext()).a(activity);
        } catch (Throwable unused) {
            ho.c("SystemUtil", "isFreedomWindowMode error");
            return false;
        }
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static void b(Activity activity, int i) {
        if (activity == null) {
            return;
        }
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        try {
            attributes.getClass().getDeclaredField(ParamConstants.Param.LAYOUT_IN_DISPLAY_CUTOUT_MODE).setInt(attributes, i);
            activity.getWindow().setAttributes(attributes);
        } catch (Throwable unused) {
            ho.c("SystemUtil", "setLayoutMode error");
        }
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() != 1) {
            return str;
        }
        return "0" + str;
    }

    public static boolean a(View view, boolean z) {
        int i = z ? 0 : 8;
        if (view == null || view.getVisibility() == i) {
            return false;
        }
        view.setVisibility(i);
        return true;
    }

    public static boolean a(View view, int i) {
        return Cdo.a(view, i, 300, 0);
    }

    public static boolean a(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static boolean a(Context context) {
        PowerManager powerManager;
        if (context == null || (powerManager = (PowerManager) context.getSystemService("power")) == null) {
            return true;
        }
        try {
            return powerManager.isInteractive();
        } catch (Exception unused) {
            ho.c("SystemUtil", "isScreenInteractive has exception");
            return true;
        }
    }

    public static boolean a(Activity activity) {
        return m(activity) && !b(activity);
    }

    public static boolean a(int i, int i2, int i3, int i4, int i5) {
        return Math.abs(i - i3) > i5 || Math.abs(i2 - i4) > i5;
    }

    public static void a(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setScaleX(c() ? -1.0f : 1.0f);
    }

    public static void a(final View view, Activity activity) {
        String str;
        String str2;
        if (activity == null) {
            str2 = "has no activity";
        } else if (!com.huawei.openalliance.ad.bz.b(activity)) {
            str2 = "not huawei phone";
        } else if (view == null) {
            str2 = "has no rootview";
        } else if (b(activity)) {
            str2 = "freedom window";
        } else {
            Window window = activity.getWindow();
            if (window != null) {
                try {
                    final com.huawei.openalliance.ad.cq a2 = com.huawei.openalliance.ad.cb.a(activity);
                    a2.a(window.getAttributes());
                    window.getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.huawei.openalliance.ad.utils.dd.1
                        @Override // android.view.View.OnApplyWindowInsetsListener
                        public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                            String str3;
                            View view3;
                            try {
                                Rect a3 = com.huawei.openalliance.ad.cq.this.a(windowInsets);
                                if (a3 != null && (view3 = view) != null) {
                                    view3.setPadding(a3.left, 0, a3.right, 0);
                                }
                            } catch (NoSuchMethodError unused) {
                                str3 = "initOnApplyWindowInsets NoSuchMethodError getDisplaySideRegion";
                                ho.c("SystemUtil", str3);
                                return windowInsets;
                            } catch (Throwable th) {
                                str3 = "initOnApplyWindowInsets error:" + th.getClass().getSimpleName();
                                ho.c("SystemUtil", str3);
                                return windowInsets;
                            }
                            return windowInsets;
                        }
                    });
                    return;
                } catch (NoSuchMethodError unused) {
                    str = "adaptRingScreen NoSuchMethodError setDisplaySideMode";
                    ho.c("SystemUtil", str);
                    return;
                } catch (Throwable th) {
                    str = "adaptRingScreen error:" + th.getClass().getSimpleName();
                    ho.c("SystemUtil", str);
                    return;
                }
            }
            str2 = "has no window";
        }
        ho.c("SystemUtil", str2);
    }

    public static void a(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        try {
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
        } catch (Throwable unused) {
            ho.c("SystemUtil", "start activity error");
        }
    }

    public static void a(Context context, int i, View view) {
        if (context == null || view == null) {
            return;
        }
        try {
            if (com.huawei.openalliance.ad.bz.a(context).a(context) || !a(i)) {
                return;
            }
            int h = ao.h(context);
            ho.b("SystemUtil", "top:%s", Integer.valueOf(h));
            view.setPadding(0, h, 0, 0);
        } catch (Throwable th) {
            ho.c("SystemUtil", "adaptStatusBar error:" + th.getClass().getSimpleName());
        }
    }

    public static void a(Activity activity, final com.huawei.openalliance.ad.views.ac acVar) {
        if (activity == null || !com.huawei.openalliance.ad.bz.b(activity)) {
            return;
        }
        Window window = activity.getWindow();
        if (window == null) {
            ho.c("SystemUtil", "get safe padding, window is null");
            return;
        }
        try {
            final com.huawei.openalliance.ad.cq a2 = com.huawei.openalliance.ad.cb.a(activity);
            a2.a(window.getAttributes());
            window.getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.huawei.openalliance.ad.utils.dd.2
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    String str;
                    com.huawei.openalliance.ad.views.ac acVar2;
                    try {
                        Rect a3 = com.huawei.openalliance.ad.cq.this.a(windowInsets);
                        if (ho.a()) {
                            Object[] objArr = new Object[1];
                            objArr[0] = Integer.valueOf(a3 == null ? 0 : a3.right);
                            ho.a("SystemUtil", "got safe padding: %s", objArr);
                        }
                        if (a3 != null && (acVar2 = acVar) != null) {
                            acVar2.a(a3.right);
                        }
                    } catch (NoSuchMethodError unused) {
                        str = "getRingScreenSafePadding NoSuchMethodError getDisplaySideRegion";
                        ho.c("SystemUtil", str);
                        return windowInsets;
                    } catch (Throwable th) {
                        str = "getRingScreenSafePadding error:" + th.getClass().getSimpleName();
                        ho.c("SystemUtil", str);
                        return windowInsets;
                    }
                    return windowInsets;
                }
            });
        } catch (Throwable th) {
            ho.c("SystemUtil", "getSafePadding ex: %s", th.getClass().getSimpleName());
        }
    }

    public static void a(Activity activity, Context context) {
        if (context == null || activity == null) {
            ho.c("SystemUtil", "para is null");
            return;
        }
        Activity z = z(context);
        if (z == null) {
            ho.c("SystemUtil", "parent activity is null");
            return;
        }
        Window window = z.getWindow();
        Window window2 = activity.getWindow();
        if (window == null || window2 == null) {
            ho.c("SystemUtil", "window is null");
            return;
        }
        WindowManager.LayoutParams attributes = window2.getAttributes();
        WindowManager.LayoutParams attributes2 = window.getAttributes();
        attributes.flags = attributes2.flags;
        if (!WhiteListPkgList.isHwBrowserPkgName(context.getPackageName())) {
            attributes.flags |= AppRouterExtras.COLDSTART;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            attributes.layoutInDisplayCutoutMode = attributes2.layoutInDisplayCutoutMode;
        }
        window2.setAttributes(attributes);
        window2.setNavigationBarColor(window.getNavigationBarColor());
        View decorView = window.getDecorView();
        View decorView2 = window2.getDecorView();
        if (decorView == null || decorView2 == null) {
            ho.c("SystemUtil", "decorView is null");
        } else {
            decorView2.setSystemUiVisibility(decorView.getSystemUiVisibility());
        }
    }

    public static void a(Activity activity, int i) {
        if (activity == null) {
            return;
        }
        try {
            activity.setRequestedOrientation(i);
        } catch (Throwable th) {
            ho.c("SystemUtil", "set Requested Orientation Exception: " + th.getClass().getSimpleName());
        }
    }

    public static String a(String str) {
        StringBuilder sb;
        Class<?> cls;
        try {
            if (Build.VERSION.SDK_INT >= 27) {
                try {
                    cls = Class.forName(d.d() ? "com.hihonor.android.os.SystemPropertiesEx" : "com.huawei.android.os.SystemPropertiesEx");
                } catch (ClassNotFoundException unused) {
                }
                return (String) cls.getMethod("get", String.class).invoke(cls, str);
            }
            cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("getSystemProperties RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("SystemUtil", sb.toString());
            return null;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getSystemProperties Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("SystemUtil", sb.toString());
            return null;
        }
    }

    public static String a(jk jkVar) {
        int[] b = b(jkVar);
        return String.format("%s,%s", Integer.valueOf(b[0]), Integer.valueOf(b[1]));
    }

    public static int a(boolean z) {
        return a(false, z);
    }

    public static int a(Context context, String str) {
        if (context == null || cz.b(str)) {
            return -1;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return -1;
            }
            return packageManager.getApplicationInfo(str, 0).uid;
        } catch (Throwable th) {
            ho.c("SystemUtil", "getUidByPackageName err: %s", th.getClass().getSimpleName());
            return -1;
        }
    }

    public static int a(int i, boolean z) {
        return z ? i > 2 ? R.drawable._2131428592_res_0x7f0b04f0 : a(false, z) : i > 2 ? R.drawable._2131428591_res_0x7f0b04ef : a(false, z);
    }

    private static String H(Context context) {
        String lowerCase = context.getResources().getConfiguration().locale.getCountry().toLowerCase(Locale.getDefault());
        ho.b("SystemUtil", " countryStr:" + lowerCase);
        return lowerCase;
    }

    private static String G(Context context) {
        String lowerCase = context.getResources().getConfiguration().locale.getLanguage().toLowerCase(Locale.getDefault());
        ho.b("SystemUtil", " languageStr:" + lowerCase);
        return lowerCase;
    }

    private static int F(Context context) {
        if (context == null) {
            return 1;
        }
        Object systemService = context.getSystemService("uimode");
        if (systemService instanceof UiModeManager) {
            return ((UiModeManager) systemService).getNightMode();
        }
        return 1;
    }

    private static boolean E(Context context) {
        WindowManager windowManager;
        if (context == null || (windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) == null) {
            return false;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
    }

    private static boolean D(Context context) {
        String[] strArr = {"force_fsg_nav_bar", "navigation_gesture_on", Constants.NAVIGATIONBAR_IS_MIN, "hide_navigationbar_enable", "force_fsg_nav_bar", "navigation_gesture_on", Constants.NAVIGATIONBAR_IS_MIN, "hide_navigationbar_enable"};
        ContentResolver contentResolver = context.getContentResolver();
        for (int i = 0; i < 8; i++) {
            if (Settings.Global.getInt(contentResolver, strArr[i], 0) != 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean C(Context context) {
        if (context == null) {
            return false;
        }
        boolean z = true;
        if (Build.VERSION.SDK_INT < 28 || !com.huawei.openalliance.ad.bz.b(context)) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", OsType.ANDROID);
            boolean z2 = identifier > 0 ? resources.getBoolean(identifier) : false;
            String a2 = a("qemu.hw.mainkeys");
            if ("1".equals(a2)) {
                z = false;
            } else if (!"0".equals(a2)) {
                z = z2;
            }
            if (D(context)) {
                ho.a("SystemUtil", "is gesture");
                return false;
            }
        } else {
            int u = d.u(context);
            ho.a("SystemUtil", "isGesture: %s", Integer.valueOf(u));
            if (u != 0) {
                return false;
            }
        }
        return z;
    }

    public static boolean B(Context context) {
        boolean z;
        if (context != null) {
            try {
                if (!cz.b(context.getPackageName())) {
                    String packageName = context.getPackageName();
                    if (a("hw_sc.build.platform.version") == null) {
                        return true;
                    }
                    AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
                    Class<?> cls = Class.forName("com.huawei.android.app.AppOpsManagerEx");
                    Method method = cls.getMethod("checkHwOpNoThrow", AppOpsManager.class, Integer.TYPE, Integer.TYPE, String.class);
                    if (method == null) {
                        ho.c("SystemUtil", "judgeHwOpIsAllow, method is null.");
                        return true;
                    }
                    int intValue = ((Integer) method.invoke(cls, appOpsManager, Integer.valueOf(ErrorCode.HWID_IS_STOPED), Integer.valueOf(a(context, packageName)), packageName)).intValue();
                    if (intValue != 0 && intValue != 3) {
                        z = false;
                        ho.b("SystemUtil", "judgeHwOpIsAllow, result is %s, per is %s", Integer.valueOf(intValue), Boolean.valueOf(z));
                        return z;
                    }
                    z = true;
                    ho.b("SystemUtil", "judgeHwOpIsAllow, result is %s, per is %s", Integer.valueOf(intValue), Boolean.valueOf(z));
                    return z;
                }
            } catch (Throwable th) {
                ho.c("SystemUtil", "JudgeHwOpIsAllow err: %s", th.getClass().getSimpleName());
                return true;
            }
        }
        ho.c("SystemUtil", "judgeHwOpIsAllow, param is invalid");
        return true;
    }

    public static void A(Context context) {
        if (context != null) {
            try {
                if (context instanceof Activity) {
                    Window window = ((Activity) context).getWindow();
                    if (window == null) {
                        ho.b("SystemUtil", "window is null");
                        return;
                    } else {
                        window.getDecorView().setSystemUiVisibility((window.getDecorView().getSystemUiVisibility() & (-5)) | 7936);
                        return;
                    }
                }
            } catch (Throwable th) {
                ho.c("SystemUtil", "showContentInNaviBar err: %s", th.getClass().getSimpleName());
                return;
            }
        }
        ho.b("SystemUtil", "para err");
    }
}
