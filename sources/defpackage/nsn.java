package defpackage;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.model.RecordAction;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.ui.commonui.R$anim;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.webview.NetworkExceptionShowActivity;
import defpackage.nmn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HarmonyBuild;
import health.compact.a.LanguageUtil;
import health.compact.a.ReflectionUtils;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class nsn {

    /* renamed from: a, reason: collision with root package name */
    private static final String f15468a = "R_Utils";
    private static long d = 0;
    private static final String e = "Utils";

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("^\\d+(\\.\\d+)?$");
    }

    public static int a(Context context, float f) {
        float f2;
        if (context == null) {
            f2 = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        } else {
            f2 = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((f / f2) + 0.5f);
    }

    public static int c(Context context, float f) {
        if (context == null) {
            LogUtil.h(e, "context is null");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int r(Context context) {
        int i = 0;
        if (context == null) {
            LogUtil.b(e, "getStatusBarHeight null");
            return 0;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            i = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NumberFormatException e2) {
            LogUtil.b(e, "Exception, e=", e2.getMessage());
        }
        if (i != 0) {
            return i;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", OsType.ANDROID));
    }

    public static int q(Context context) {
        if (context == null) {
            LogUtil.b(e, "getNavigationBarHeight null");
            return 0;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("navigation_bar_height").get(cls.newInstance()).toString()));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NumberFormatException e2) {
            LogUtil.b(e, "Exception =", e2.getMessage());
            return 0;
        }
    }

    public static int n() {
        Object systemService = BaseApplication.getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h(e, "getScreenWidth WindowManager is null or class cast exception");
            return 0;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private static DisplayMetrics cKY_(Context context) {
        if (!(context instanceof Activity)) {
            LogUtil.h(e, "getCurrentWindowDisplayMetrics context is null getTopActivity");
            context = BaseApplication.getActivity();
        }
        if (context == null) {
            LogUtil.h(e, "getCurrentWindowDisplayMetrics context is null getAppContext");
            context = BaseApplication.getContext();
        }
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h(e, "getCurrentWindowDisplayMetrics object ", systemService);
            return null;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        if (defaultDisplay == null) {
            LogUtil.h(e, "getCurrentWindowDisplayMetrics display is null");
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int h(Context context) {
        DisplayMetrics cKY_ = cKY_(context);
        if (cKY_ == null) {
            LogUtil.h(e, "getCurrentWindowWidth displayMetrics is null");
            return 0;
        }
        return cKY_.widthPixels;
    }

    public static int f(Context context) {
        DisplayMetrics cKY_ = cKY_(context);
        if (cKY_ == null) {
            LogUtil.h(e, "getCurrentWindowHeight displayMetrics is null");
            return 0;
        }
        return cKY_.heightPixels;
    }

    public static int j() {
        Object systemService = BaseApplication.getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h(e, "getScreenHeight WindowManager is null or class cast exception");
            return 0;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int h() {
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        if (configuration == null) {
            LogUtil.h(e, "getScreenOrientation configuration is null");
            return 1;
        }
        return configuration.orientation;
    }

    public static Point cLb_() {
        Point point = new Point();
        Object systemService = BaseApplication.getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            return point;
        }
        ((WindowManager) systemService).getDefaultDisplay().getRealSize(point);
        return point;
    }

    public static void cLA_(LinearLayout linearLayout, int i) {
        if (i == 0 || linearLayout == null) {
            LogUtil.h(e, "ratio = 0 or linearLayout is null");
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = j() / i;
        layoutParams.width = n();
        linearLayout.setLayoutParams(layoutParams);
    }

    public static void cLC_(RelativeLayout relativeLayout, int i) {
        if (i == 0 || relativeLayout == null) {
            LogUtil.h(e, "ratio = 0 or relativeLayout is null");
            return;
        }
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.height = j() / i;
        layoutParams.width = n();
        relativeLayout.setLayoutParams(layoutParams);
    }

    public static boolean ab(Context context) {
        if (context != null) {
            return Settings.Global.getInt(context.getContentResolver(), Constants.NAVIGATIONBAR_IS_MIN, 0) == 0;
        }
        LogUtil.b(e, "isNavigationBarShow null");
        return false;
    }

    public static float cLa_(Paint paint, String str) {
        return paint.measureText(str);
    }

    public static float cKZ_(Paint paint) {
        if (paint == null) {
            return 0.0f;
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }

    private static String a(Context context, int i) {
        if (context != null) {
            try {
                return context.getResources().getString(i);
            } catch (Resources.NotFoundException e2) {
                LogUtil.a(e, "NotFoundException ", e2.getMessage());
            }
        }
        return "";
    }

    public static int e(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            LogUtil.b(e, "string2Int e=", e2.getMessage());
            return 0;
        }
    }

    public static long h(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            LogUtil.b(e, "string2Long e=", e2.getMessage());
            return 0L;
        }
    }

    public static float f(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e2) {
            LogUtil.b(e, "stringToFloat exception ", e2.getMessage());
            return 0.0f;
        }
    }

    public static double j(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e2) {
            LogUtil.b(e, "stringToDouble exception ", e2.getMessage());
            return 0.0d;
        }
    }

    public static Integer d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            LogUtil.b(e, "cant get int from string", new Object[0]);
            return null;
        }
    }

    public static Double b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            LogUtil.b(e, "cant get double from string", new Object[0]);
            return null;
        }
    }

    public static String t(Context context) {
        String string;
        String str = e;
        LogUtil.a(str, "enter getMetisProductName:");
        if (context == null) {
            return "";
        }
        String country = context.getResources().getConfiguration().locale.getCountry();
        if ("CN".equals(country) || "RU".equals(country) || FaqConstants.OPEN_TYPE_IN.equals(country)) {
            string = context.getResources().getString(R$string.IDS_device_metis_name_honor_watch_s1);
        } else {
            string = context.getResources().getString(R$string.IDS_device_metis_name_title_1);
        }
        LogUtil.a(str, RecordAction.ACT_NAME_TAG, string);
        return string;
    }

    public static int s(Context context) {
        String str = e;
        LogUtil.a(str, "enter getMetisProductType:");
        if (context != null) {
            String country = context.getResources().getConfiguration().locale.getCountry();
            r1 = ("CN".equals(country) || "RU".equals(country) || FaqConstants.OPEN_TYPE_IN.equals(country)) ? 2 : 1;
            LogUtil.a(str, "manuType=", Integer.valueOf(r1));
        }
        return r1;
    }

    public static String d(Context context) {
        String string;
        String str = e;
        LogUtil.a(str, "enter getA2ProductName:");
        if (context == null) {
            return "";
        }
        String country = context.getResources().getConfiguration().locale.getCountry();
        if ("CN".equals(country)) {
            string = context.getResources().getString(R$string.IDS_select_device_talkband_a1);
        } else if (FaqConstants.OPEN_TYPE_IN.equals(country)) {
            string = context.getResources().getString(R$string.IDS_select_device_talkband_a2overseas);
        } else {
            string = context.getResources().getString(R$string.IDS_select_device_talkband_a1overseas);
        }
        LogUtil.a(str, RecordAction.ACT_NAME_TAG, string);
        return string;
    }

    public static int c(Context context) {
        String str = e;
        LogUtil.a(str, "enter getA2ManuType:");
        if (context != null) {
            String country = context.getResources().getConfiguration().locale.getCountry();
            r1 = ("CN".equals(country) || FaqConstants.OPEN_TYPE_IN.equals(country)) ? 2 : 1;
            LogUtil.a(str, "manuType=", Integer.valueOf(r1));
        }
        return r1;
    }

    public static String a(Context context) {
        char c;
        String string;
        String str = e;
        LogUtil.a(str, "enter getB0ProductName:");
        if (context == null) {
            return "";
        }
        String country = context.getResources().getConfiguration().locale.getCountry();
        country.hashCode();
        int hashCode = country.hashCode();
        if (hashCode == 2155) {
            if (country.equals("CN")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 2252) {
            if (country.equals("FR")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 2341) {
            if (country.equals(FaqConstants.OPEN_TYPE_IN)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == 2476) {
            if (country.equals("MY")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 2627) {
            if (hashCode == 2718 && country.equals("US")) {
                c = 5;
            }
            c = 65535;
        } else {
            if (country.equals("RU")) {
                c = 4;
            }
            c = 65535;
        }
        if (c == 0) {
            string = context.getResources().getString(R$string.IDS_app_display_name_b0);
        } else if (c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
            string = context.getResources().getString(R$string.IDS_app_display_name_b0_1);
        } else {
            string = context.getResources().getString(R$string.IDS_app_display_name_b0);
        }
        LogUtil.a(str, "name:", string);
        return string;
    }

    public static String e(Context context) {
        String string;
        String str = e;
        LogUtil.a(str, "enter getB0ProductName:");
        if (context == null) {
            return "";
        }
        if ("CN".equals(context.getResources().getConfiguration().locale.getCountry())) {
            string = context.getResources().getString(R$string.IDS_select_device_talkband_a1);
        } else {
            string = context.getResources().getString(R$string.IDS_select_device_talkband_a1overseas);
        }
        LogUtil.a(str, "name:", string);
        return string;
    }

    public static String l(Context context) {
        LogUtil.a(e, "enter getJunasProductName:");
        return a(context, R$string.IDS_app_display_name_janus);
    }

    public static String x(Context context) {
        LogUtil.a(e, "enter GetTerraProductName:");
        return a(context, R$string.IDS_app_display_name_terra);
    }

    public static String b(Context context) {
        LogUtil.a(e, "enter GetCriusProductName:");
        return a(context, R$string.IDS_app_display_name_crius_new);
    }

    public static String o(Context context) {
        LogUtil.a(e, "enter GetHWAW70ProductName:");
        return a(context, R$string.IDS_app_display_name_aw);
    }

    public static String m(Context context) {
        LogUtil.a(e, "enter GetHNAW70ProductName:");
        return a(context, R$string.IDS_app_display_name_honor_aw);
    }

    public static String n(Context context) {
        LogUtil.a(e, "enter Get Huawei AW70proProductName:");
        return a(context, R$string.IDS_hw_motiontrack_aw70_pro);
    }

    public static String k(Context context) {
        LogUtil.a(e, "enter Get Honor AW70proProductName:");
        return a(context, R$string.IDS_hw_motiontrack_aw70);
    }

    public static String u(Context context) {
        LogUtil.a(e, "enter GetTalosProductName:");
        return a(context, R$string.IDS_app_display_name_talos);
    }

    public static String j(Context context) {
        LogUtil.a(e, "enter GetFortunaProductName:");
        return a(context, R$string.IDS_app_display_name_fortuna);
    }

    public static void cLB_(Notification.Builder builder) {
        if (builder == null) {
            return;
        }
        if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            builder.setSmallIcon(R$drawable.ic_wear_notification);
        } else {
            builder.setSmallIcon(R$drawable.healthlogo_ic_notification);
        }
    }

    public static void a(Context context, boolean z) {
        if (context == null) {
            return;
        }
        try {
            c(context, context.getString(z ? R$string.IDS_hwh_motiontrack_permission_guide_location : i()));
        } catch (Resources.NotFoundException unused) {
            LogUtil.a(e, "showPermissionSettingGuide Resources NotFound");
        }
    }

    public static void c(Context context, String str) {
        if (context == null) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        new CustomTextAlertDialog.Builder(context).b(R$string.IDS_hwh_home_other_permissions_title).e(str).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: nsn.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c(nsn.e, "setNegativeButton onclick called String");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R$string.IDS_hwh_motiontrack_permission_guide_go_set, new View.OnClickListener() { // from class: nsn.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c(nsn.e, "setPositiveButton onclick called String");
                nsn.ak(applicationContext);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    public static int d(PermissionUtil.PermissionType permissionType) {
        boolean a2 = PermissionUtil.a(permissionType);
        boolean g = PermissionUtil.g();
        boolean e2 = PermissionUtil.e();
        boolean z = (CommonUtil.az() && HarmonyBuild.c >= 6) || EnvironmentInfo.k();
        switch (AnonymousClass8.b[permissionType.ordinal()]) {
            case 1:
                if (g) {
                    return R$string.IDS_manage_external_storage;
                }
            case 2:
                if (g) {
                    return R$string.IDS_hw_permission_guide_image;
                }
            case 3:
                if (z) {
                    return R$string.IDS_harmony_permission_torage;
                }
                if (g) {
                    return R$string.IDS_android_tiramisu_permission_torage;
                }
                return R$string.IDS_hw_feedback_permission_guide_torage;
            case 4:
            case 5:
                if (e2) {
                    return R$string.IDS_hwh_motiontrack_permission_guide_location_precise;
                }
                return R$string.IDS_hwh_motiontrack_permission_guide_location;
            case 6:
                if (z) {
                    return R$string.IDS_harmony_location_storage_permission;
                }
                if (g) {
                    return R$string.IDS_android_tiramisu_location_storage_permission;
                }
                return R$string.IDS_location_storage_permission;
            case 7:
            case 8:
                return R$string.IDS_contact_have_no_permission_to_read_health;
            case 9:
                return R$string.IDS_hw_permission_guide_camera;
            case 10:
                return R$string.IDS_hw_permission_distribute;
            case 11:
                if (a2) {
                    return R$string.IDS_hw_permission_guide_camera_image;
                }
                if (g) {
                    return R$string.IDS_android_tiramisu_healthshop_permission_str;
                }
                return R$string.IDS_hwh_home_healthshop_permission_str;
            case 12:
                return R$string.IDS_int_plan_calendar_permission_guide;
            case 13:
                return R$string.IDS_sleep_record_permission_guide;
            case 14:
                return R$string.IDS_hw_bluetooth_permission_guide;
            case 15:
                return R$string.IDS_hw_permission_guide_phone;
            default:
                LogUtil.h(e, "getPermissionDescription permissionType ", permissionType);
                return i();
        }
    }

    /* renamed from: nsn$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[PermissionUtil.PermissionType.values().length];
            b = iArr;
            try {
                iArr[PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[PermissionUtil.PermissionType.STORAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[PermissionUtil.PermissionType.LOCATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[PermissionUtil.PermissionType.STORAGE_LOCATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[PermissionUtil.PermissionType.READ_CONTACT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[PermissionUtil.PermissionType.READ_WRITE_CONTACT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[PermissionUtil.PermissionType.CAMERA.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[PermissionUtil.PermissionType.DISTRIBUTED_DATASYNC.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[PermissionUtil.PermissionType.CAMERA_IMAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[PermissionUtil.PermissionType.CALENDAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[PermissionUtil.PermissionType.RECORD_AUDIO.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[PermissionUtil.PermissionType.SCAN.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[PermissionUtil.PermissionType.PHONE_STATE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public static int i() {
        if ((CommonUtil.az() && HarmonyBuild.c >= 6) || EnvironmentInfo.k()) {
            return R$string.IDS_harmony_guide_location_and_storage_permission;
        }
        return R$string.IDS_hwh_motiontrack_permission_guide_location_and_storage;
    }

    public static void cLJ_(Context context, PermissionUtil.PermissionType permissionType, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        cLK_(context, permissionType, null, onClickListener, onClickListener2);
    }

    public static void cKS_(final Activity activity, final int i) {
        PermissionUtil.b(activity, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(activity) { // from class: nsn.9
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                ezd.aue_(activity, i);
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                super.onDenied(str);
                LogUtil.a(nsn.e, "checkImagePermission Permission onDenied");
                ezd.aue_(activity, i);
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                nsn.cLL_(permissionType, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cLL_(PermissionUtil.PermissionType permissionType, final Activity activity) {
        cLJ_(activity, permissionType, new View.OnClickListener() { // from class: nsn.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nsn.ak(activity);
                activity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }, new View.OnClickListener() { // from class: nsn.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                activity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private static void cLx_(View.OnClickListener onClickListener, View view) {
        LogUtil.c(e, "showPermissionSettingGuide cancel");
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public static void e(Context context, PermissionUtil.PermissionType permissionType) {
        cLJ_(context, permissionType, null, null);
    }

    public static void cLK_(final Context context, final PermissionUtil.PermissionType permissionType, CustomTextAlertDialog customTextAlertDialog, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        if (!(context instanceof Activity)) {
            LogUtil.h(e, "showHealthAppSettingGuide context is not activity context.");
            return;
        }
        String string = context.getResources().getString(d(permissionType));
        if (customTextAlertDialog == null) {
            customTextAlertDialog = new CustomTextAlertDialog.Builder(context).b(R$string.IDS_hwh_home_other_permissions_title).e(string).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: nsq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    nsn.cLp_(onClickListener2, view);
                }
            }).cyU_(R$string.IDS_hwh_motiontrack_permission_guide_go_set, new View.OnClickListener() { // from class: nst
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    nsn.cLq_(PermissionUtil.PermissionType.this, context, onClickListener, view);
                }
            }).a();
            customTextAlertDialog.setCancelable(false);
        }
        customTextAlertDialog.show();
    }

    static /* synthetic */ void cLp_(View.OnClickListener onClickListener, View view) {
        LogUtil.c(e, "showPermissionSettingGuide cancel");
        cLx_(onClickListener, view);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cLq_(PermissionUtil.PermissionType permissionType, Context context, View.OnClickListener onClickListener, View view) {
        LogUtil.c(e, "showPermissionSettingGuide cancel");
        if (PermissionUtil.g() && PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE.equals(permissionType)) {
            y();
        } else {
            ak(context);
        }
        cLx_(onClickListener, view);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void ak(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", applicationContext.getPackageName(), null));
        if (intent.resolveActivity(applicationContext.getPackageManager()) == null) {
            return;
        }
        try {
            applicationContext.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(e, "startAppSettingActivity ActivityNotFoundException e = ", e2.getMessage());
        }
    }

    public static void cKT_(final Activity activity, final int i) {
        if (activity == null) {
            LogUtil.b(e, "choosePic: activity is null");
        } else if (PermissionUtil.g()) {
            cKU_(activity, i);
        } else {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(activity) { // from class: nsn.14
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    nsn.cKU_(activity, i);
                }
            });
        }
    }

    public static void cKU_(Activity activity, int i) {
        try {
            Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, com.huawei.operation.utils.Constants.IMAGE_TYPE);
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(e, "open system gallery fail:", e2.getMessage());
        } catch (SecurityException e3) {
            LogUtil.b(e, "open system gallery fail securityException: ", e3.getMessage());
        }
    }

    private static void a(Context context, String str) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSION");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", context.getPackageName());
        intent.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", str);
        intent.putExtra("android.intent.extra.PERMISSION_NAME", str);
        intent.putExtra("android.intent.extra.USER", Process.myUserHandle());
        context.startActivity(intent);
    }

    public static void cLP_(final Context context, final boolean z, final View.OnClickListener onClickListener) {
        int i;
        if (!(context instanceof Activity)) {
            LogUtil.h(e, "showLocationOpenDialog context is not activity context.");
            return;
        }
        if (PermissionUtil.e()) {
            i = R$string.IDS_hw_need_access_precise_location_permissions_tips;
        } else {
            i = R$string.IDS_hw_need_access_location_permissions_tips;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(i)).czz_(R$string.IDS_settings_button_cancal, z ? onClickListener : new View.OnClickListener() { // from class: nss
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLs_(view);
            }
        }).czC_(R$string.IDS_common_enable_button, new View.OnClickListener() { // from class: nsv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLt_(context, z, onClickListener, view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    static /* synthetic */ void cLs_(View view) {
        LogUtil.a(e, "showLocationOpenDialog cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cLt_(Context context, boolean z, View.OnClickListener onClickListener, View view) {
        LogUtil.a(e, "showLocationOpenDialog onClick");
        a((Activity) context, "android.permission-group.LOCATION");
        if (z) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void cLQ_(final Context context, final boolean z, final View.OnClickListener onClickListener) {
        if (!(context instanceof Activity)) {
            LogUtil.h(e, "showStorageOpenDialog context is not activity context.");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R$string.IDS_hw_need_access_storage_permissions_tips)).czz_(R$string.IDS_settings_button_cancal, z ? onClickListener : new View.OnClickListener() { // from class: nsr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLu_(view);
            }
        }).czC_(R$string.IDS_common_enable_button, new View.OnClickListener() { // from class: nsu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLv_(context, z, onClickListener, view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    static /* synthetic */ void cLu_(View view) {
        LogUtil.a(e, "showStorageOpenDialog cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cLv_(Context context, boolean z, View.OnClickListener onClickListener, View view) {
        LogUtil.a(e, "showStorageOpenDialog onClick");
        a((Activity) context, "android.permission-group.STORAGE");
        if (z) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static String b(Context context, long j) {
        int i;
        if (context == null) {
            LogUtil.h(e, "formatFileSize context is null");
            return "";
        }
        double d2 = j;
        if (d2 > 900.0d) {
            d2 /= 1024.0d;
            i = R.plurals._2130903229_res_0x7f0300bd;
        } else {
            i = R.plurals._2130903228_res_0x7f0300bc;
        }
        if (d2 > 900.0d) {
            d2 /= 1024.0d;
            i = R.plurals._2130903230_res_0x7f0300be;
        }
        if (d2 > 900.0d) {
            d2 /= 1024.0d;
            i = R.plurals._2130903231_res_0x7f0300bf;
        }
        if (d2 < 100.0d && d2 != 0.0d) {
            double a2 = UnitUtil.a(d2, 2);
            return context.getResources().getQuantityString(i, UnitUtil.e(a2), UnitUtil.e(a2, 1, 2));
        }
        double a3 = (int) UnitUtil.a(d2, 0);
        return context.getResources().getQuantityString(i, UnitUtil.e(a3), UnitUtil.e(a3, 1, 0));
    }

    public static Drawable cLd_(int i) {
        Context context = BaseApplication.getContext();
        if (i <= 5) {
            return context.getResources().getDrawable(R.mipmap._2131820994_res_0x7f1101c2);
        }
        if (i <= 10) {
            return context.getResources().getDrawable(R.mipmap._2131820995_res_0x7f1101c3);
        }
        if (i <= 20) {
            return context.getResources().getDrawable(R.mipmap._2131820997_res_0x7f1101c5);
        }
        if (i <= 30) {
            return context.getResources().getDrawable(R.mipmap._2131820998_res_0x7f1101c6);
        }
        if (i <= 40) {
            return context.getResources().getDrawable(R.mipmap._2131820999_res_0x7f1101c7);
        }
        if (i <= 50) {
            return context.getResources().getDrawable(R.mipmap._2131821000_res_0x7f1101c8);
        }
        if (i <= 60) {
            return context.getResources().getDrawable(R.mipmap._2131821001_res_0x7f1101c9);
        }
        if (i <= 70) {
            return context.getResources().getDrawable(R.mipmap._2131821002_res_0x7f1101ca);
        }
        if (i <= 80) {
            return context.getResources().getDrawable(R.mipmap._2131821003_res_0x7f1101cb);
        }
        if (i <= 90) {
            return context.getResources().getDrawable(R.mipmap._2131821004_res_0x7f1101cc);
        }
        return context.getResources().getDrawable(R.mipmap._2131820996_res_0x7f1101c4);
    }

    public static Drawable cKX_(int i) {
        Context context = BaseApplication.getContext();
        if (i <= 5) {
            return context.getResources().getDrawable(R.mipmap._2131820887_res_0x7f110157);
        }
        if (i <= 10) {
            return context.getResources().getDrawable(R.mipmap._2131820894_res_0x7f11015e);
        }
        if (i <= 20) {
            return context.getResources().getDrawable(R.mipmap._2131820888_res_0x7f110158);
        }
        if (i <= 30) {
            return context.getResources().getDrawable(R.mipmap._2131820889_res_0x7f110159);
        }
        if (i <= 40) {
            return context.getResources().getDrawable(R.mipmap._2131820890_res_0x7f11015a);
        }
        if (i <= 50) {
            return context.getResources().getDrawable(R.mipmap._2131820891_res_0x7f11015b);
        }
        if (i <= 60) {
            return context.getResources().getDrawable(R.mipmap._2131820892_res_0x7f11015c);
        }
        if (i <= 70) {
            return context.getResources().getDrawable(R.mipmap._2131820893_res_0x7f11015d);
        }
        if (i <= 80) {
            return context.getResources().getDrawable(R.mipmap._2131820895_res_0x7f11015f);
        }
        if (i <= 90) {
            return context.getResources().getDrawable(R.mipmap._2131820896_res_0x7f110160);
        }
        return context.getResources().getDrawable(R.mipmap._2131820897_res_0x7f110161);
    }

    public static boolean o() {
        return a(1500);
    }

    public static boolean a(int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - d < i) {
            return true;
        }
        d = elapsedRealtime;
        return false;
    }

    public static void a() {
        d = 0L;
    }

    public static boolean cLk_(View view) {
        return cLj_(1500, view);
    }

    public static boolean cLj_(int i, View view) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (view.getTag(-1) == null) {
            view.setTag(-1, Long.valueOf(elapsedRealtime));
            return false;
        }
        if (elapsedRealtime - ((Long) view.getTag(-1)).longValue() < i) {
            return true;
        }
        view.setTag(-1, Long.valueOf(elapsedRealtime));
        return false;
    }

    public static void c(final HealthButton healthButton, final HealthButton healthButton2) {
        if (healthButton == null || healthButton2 == null) {
            return;
        }
        healthButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: nsn.15
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HealthButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (HealthButton.this.getLineCount() > 1) {
                    nsn.e(HealthButton.this, healthButton2);
                }
            }
        });
        healthButton2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: nsn.12
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HealthButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (HealthButton.this.getLineCount() > 1) {
                    nsn.e(healthButton, HealthButton.this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(HealthButton healthButton, HealthButton healthButton2) {
        LogUtil.c(e, "getLineCount > 1");
        healthButton.setTextSize(1, 9.0f);
        healthButton.setMaxLines(1);
        healthButton.setEllipsize(TextUtils.TruncateAt.END);
        healthButton2.setTextSize(1, 9.0f);
        healthButton2.setMaxLines(1);
        healthButton2.setEllipsize(TextUtils.TruncateAt.END);
    }

    public static void a(final HealthButton healthButton) {
        if (healthButton == null) {
            return;
        }
        healthButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: nsn.18
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HealthButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (HealthButton.this.getLineCount() > 1) {
                    LogUtil.c(nsn.e, "getLineCount more than the 1");
                    HealthButton.this.setTextSize(1, 9.0f);
                    HealthButton.this.setMaxLines(1);
                    HealthButton.this.setEllipsize(TextUtils.TruncateAt.END);
                }
            }
        });
    }

    public static boolean l() {
        return EnvironmentInfo.e();
    }

    public static boolean ah(Context context) {
        if (context == null) {
            LogUtil.b(e, "isTelephonyEnable() context is null!!");
            return false;
        }
        Object systemService = context.getSystemService("phone");
        return (systemService instanceof TelephonyManager) && ((TelephonyManager) systemService).getPhoneType() != 0;
    }

    public static boolean ad(Context context) {
        if (ag(BaseApplication.getContext()) || h() == 2 || !aa(context)) {
            return false;
        }
        float j = j();
        if (j <= 0.0f) {
            LogUtil.h(e, "isFloatingWindowMode screenHeight ", Float.valueOf(j));
            return false;
        }
        int f = f(context);
        LogUtil.a(e, "isFloatingWindowMode screenHeight ", Float.valueOf(j), " windowHeight ", Integer.valueOf(f));
        return Float.compare(((float) f) / j, 0.7f) != -1;
    }

    public static boolean cLh_(Activity activity) {
        if (activity == null) {
            LogUtil.h(e, "isInMultiWindowMode activity is null");
            return false;
        }
        return activity.isInMultiWindowMode();
    }

    public static boolean aa(Context context) {
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null) {
            activity = BaseApplication.getActivity();
        }
        return cLh_(activity);
    }

    public static boolean y(Context context) {
        if (aa(context)) {
            boolean ag = ag(BaseApplication.getContext());
            if (ag && h() == 1) {
                return true;
            }
            if (!ag && !ad(context)) {
                return true;
            }
        }
        return false;
    }

    public static void a(Context context, BaseDialog baseDialog) {
        if (aa(context)) {
            Window window = baseDialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = c(context, context.getResources().getConfiguration().screenHeightDp * 0.8f);
            window.setAttributes(attributes);
        }
    }

    public static boolean ag(Context context) {
        if (context == null) {
            LogUtil.b(e, "isWidescreen() context is null!!");
            return false;
        }
        if ((context.getResources().getConfiguration().screenLayout & 15) < 3) {
            return false;
        }
        if (!(context instanceof Activity) && (context = BaseApplication.getActivity()) == null) {
            context = BaseApplication.getContext();
        }
        return new HealthColumnSystem(context).f() > 4;
    }

    public static boolean z(Context context) {
        if (context == null) {
            LogUtil.b(e, "isHWPad() context is null!!");
            return false;
        }
        return SystemInfo.b(true);
    }

    public static boolean ae(Context context) {
        return z(context) || !ah(context);
    }

    public static String i(Context context) {
        if (ae(context)) {
            return context.getString(R$string.IDS_device_type_pad);
        }
        return context.getString(R$string.IDS_device_type_phone);
    }

    public static boolean ac(Context context) {
        if (context == null) {
            LogUtil.b(e, "isInMagicWindow() context is null!!");
            return false;
        }
        String configuration = context.getResources().getConfiguration().toString();
        return configuration.contains("hwMultiwindow-magic") || configuration.contains("hw-magic-windows");
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static String d(Context context, int i) {
        return context == null ? "" : e(context, context.getResources().getString(i));
    }

    public static String e(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        if (Utils.o()) {
            if (LanguageUtil.r(context)) {
                if (str.contains("Wi-Fi")) {
                    return str.replace("Wi-Fi", "WLAN");
                }
            } else if (str.contains("WLAN")) {
                return str.replace("WLAN", "Wi-Fi");
            }
        } else if (str.contains("Wi-Fi")) {
            return str.replace("Wi-Fi", "WLAN");
        }
        return str;
    }

    public static void cLz_(Activity activity) {
        if (activity == null) {
            LogUtil.a(e, "setHideSystemOverlayWindows activity null");
        } else {
            cLy_(activity.getWindow());
        }
    }

    public static void cLy_(Window window) {
        try {
            Method declaredMethod = Class.forName("android.view.Window").getDeclaredMethod("addPrivateFlags", Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(window, 524288);
        } catch (ClassNotFoundException unused) {
            LogUtil.b(e, "setHideNonSystemOverlayWindows ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            LogUtil.b(e, "setHideNonSystemOverlayWindows IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            LogUtil.b(e, "setHideNonSystemOverlayWindows NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            LogUtil.b(e, "setHideNonSystemOverlayWindows InvocationTargetException");
        }
    }

    public static void cKR_(Window window) {
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = attributes.getClass().getDeclaredField("privateFlags");
            declaredField.setInt(attributes, declaredField.getInt(attributes) & (-524289));
            window.setAttributes(attributes);
        } catch (IllegalAccessException unused) {
            LogUtil.b(e, "allowShowNonSystemOverlayWindows IllegalAccessException");
        } catch (NoSuchFieldException unused2) {
            LogUtil.b(e, "allowShowNonSystemOverlayWindows NoSuchFieldException");
        } catch (Throwable unused3) {
        }
    }

    public static boolean v(Context context) {
        return context != null && (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static void cLF_(Context context, boolean z, boolean z2, View... viewArr) {
        if (viewArr == null) {
            LogUtil.b(e, "views is null");
            return;
        }
        if (context == null) {
            LogUtil.b(e, "context is null");
            return;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            LogUtil.b(e, "resources is null");
            return;
        }
        for (View view : viewArr) {
            if (view == null || !(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                LogUtil.b(e, "view is null or view.getLayoutParams() isn't instance of ViewGroup.MarginLayoutParams");
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                if (z) {
                    marginLayoutParams.setMarginStart(resources.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b));
                }
                if (z2) {
                    marginLayoutParams.setMarginEnd(resources.getDimensionPixelOffset(R.dimen._2131364634_res_0x7f0a0b1a));
                }
                view.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public static void cLG_(Context context, boolean z, boolean z2, View... viewArr) {
        if (viewArr == null) {
            LogUtil.b(e, "views is null");
            return;
        }
        if (context == null) {
            LogUtil.b(e, "context is null");
            return;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            LogUtil.b(e, "resources is null");
            return;
        }
        for (View view : viewArr) {
            if (view == null) {
                LogUtil.b(e, "current view is null");
            } else {
                int paddingStart = view.getPaddingStart();
                int paddingEnd = view.getPaddingEnd();
                if (z) {
                    paddingStart = resources.getDimensionPixelOffset(R.dimen._2131364635_res_0x7f0a0b1b);
                }
                if (z2) {
                    paddingEnd = resources.getDimensionPixelOffset(R.dimen._2131364634_res_0x7f0a0b1a);
                }
                view.setPaddingRelative(paddingStart, view.getPaddingTop(), paddingEnd, view.getPaddingBottom());
            }
        }
    }

    public static void a(final Context context, final String str, final String str2, final boolean z) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: nsn.4
                @Override // java.lang.Runnable
                public void run() {
                    nsn.a(context, str, str2, z);
                }
            });
            return;
        }
        if (!(context instanceof Activity)) {
            LogUtil.h(e, "processNetworkServerException context is not activity context");
            if (BaseApplication.getActivity() != null) {
                context = BaseApplication.getActivity();
            }
        }
        Intent intent = new Intent(context, (Class<?>) NetworkExceptionShowActivity.class);
        intent.putExtra("extraTitleBarText", str2);
        intent.putExtra("className", str);
        intent.putExtra("server_exception_flag", z);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(e, "NetworkExceptionShowActivity exception", e2.getMessage());
        }
    }

    public static void cLw_(final ImageView imageView, String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            RequestOptions bitmapTransform = RequestOptions.bitmapTransform(new CircleCrop());
            CustomTarget<Drawable> customTarget = new CustomTarget<Drawable>() { // from class: nsn.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cLS_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    imageView.setImageDrawable(drawable);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h(nsn.e, "loadHeadImage onLoadFailed");
                }
            };
            if (CommonUtil.bu()) {
                nrf.b("file:///android_asset/suggestion/img/" + str, bitmapTransform, customTarget);
                return;
            }
            nrf.b(str, bitmapTransform, customTarget);
            return;
        }
        imageView.setImageDrawable(nmn.cBh_(BaseApplication.getContext().getResources(), new nmn.c(null, String.valueOf(j), true)));
    }

    public static String e(int i, Context context) {
        if (context == null) {
            LogUtil.b(e, "Context is null");
            return "";
        }
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903289_res_0x7f0300f9, i, Integer.valueOf(i));
    }

    public static float g(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static void e(Context context, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(context, AnalyticsValue.HEALTH_VARIOUS_TAB_2030073.value(), hashMap, 0);
    }

    public static int b() {
        if (CommonUtil.bh()) {
            return 1;
        }
        if (CommonUtil.bf()) {
            return 2;
        }
        return ae(BaseApplication.getContext()) ? 5 : 3;
    }

    public static float c() {
        return BaseApplication.getContext().getResources().getConfiguration().fontScale;
    }

    public static boolean m() {
        return ((double) (c() - 1.45f)) > Math.pow(10.0d, -6.0d);
    }

    public static boolean k() {
        return c() - 1.45f >= 0.0f;
    }

    public static boolean r() {
        return e(1.74f);
    }

    public static boolean e(float f) {
        return c() >= f;
    }

    public static void b(HealthTextView healthTextView) {
        if (healthTextView == null) {
            return;
        }
        healthTextView.setMaxLines(1);
        healthTextView.setSingleLine(true);
        healthTextView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public static boolean t() {
        return c() > 1.1f;
    }

    public static boolean p() {
        return c() > 1.29f;
    }

    public static void d() {
        if (u()) {
            LogUtil.a(e, "show large font adjust");
            nrh.e(BaseApplication.getContext(), R$string.IDS_large_font_adjust_remind);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HAS_SHOW_FONT_ADJUST_KEY", CommonUtil.e(BaseApplication.getContext()), new StorageParams());
        }
    }

    private static boolean u() {
        return t() && LanguageUtil.m(BaseApplication.getContext()) && !v();
    }

    private static boolean v() {
        return TextUtils.equals(CommonUtil.e(BaseApplication.getContext()), SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "HAS_SHOW_FONT_ADJUST_KEY"));
    }

    public static boolean s() {
        return c() >= 2.9f;
    }

    public static boolean cLi_(Drawable drawable) {
        Bitmap bitmap;
        return (drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && bitmap.isRecycled();
    }

    public static void cLH_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.a(e, "activity is null");
            return;
        }
        Window window = activity.getWindow();
        if (window == null) {
            LogUtil.a(e, "window is null");
        } else {
            window.setBackgroundDrawableResource(i);
        }
    }

    public static int[] d(int[]... iArr) {
        if (iArr == null || iArr.length == 0) {
            return new int[0];
        }
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int[] iArr2 = iArr[i2];
            i += iArr2 != null ? iArr2.length : 0;
        }
        if (i == 0) {
            return new int[0];
        }
        int[] iArr3 = new int[i];
        int i3 = 0;
        for (int[] iArr4 : iArr) {
            if (iArr4 != null && iArr4.length != 0) {
                int i4 = 0;
                while (i4 < iArr4.length) {
                    iArr3[i3] = iArr4[i4];
                    i4++;
                    i3++;
                }
            }
        }
        return iArr3;
    }

    public static int c(int[] iArr, int i) {
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public static int p(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int b = nrr.b(context);
        int i = context.getResources().getDisplayMetrics().widthPixels;
        if (ag(context)) {
            return ((i - dimensionPixelSize) - dimensionPixelSize2) - b;
        }
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        return (((i - dimensionPixelSize) - dimensionPixelSize2) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
    }

    public static int d(Context context, int i, boolean z) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        return ag(context) ? i % 2 == 0 ? dimensionPixelSize : nrr.b(context) / 2 : z ? dimensionPixelSize + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue() : dimensionPixelSize;
    }

    public static int c(Context context, int i, boolean z) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        return ag(context) ? i % 2 == 0 ? nrr.b(context) / 2 : dimensionPixelSize : z ? dimensionPixelSize + ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() : dimensionPixelSize;
    }

    public static String a(int i, StringBuilder sb, StringBuilder sb2, boolean z) {
        int abs = Math.abs(i);
        if (z) {
            if (abs % 2 == 0) {
                return e(abs, sb, sb2);
            }
            return e(abs - 1, sb, sb2);
        }
        if (abs % 2 == 0) {
            return a(abs, sb, sb2);
        }
        return a(abs - 1, sb, sb2);
    }

    private static String e(int i, StringBuilder sb, StringBuilder sb2) {
        for (int i2 = 0; i2 < i / 2; i2++) {
            sb.append("  ");
        }
        sb2.insert(0, "  ");
        sb2.insert(0, (CharSequence) sb);
        sb2.append((CharSequence) sb);
        sb2.append("  ");
        return sb2.toString();
    }

    private static String a(int i, StringBuilder sb, StringBuilder sb2) {
        for (int i2 = 0; i2 < i / 2; i2++) {
            sb.append("  ");
        }
        sb2.insert(0, " ");
        sb2.insert(0, (CharSequence) sb);
        sb2.append((CharSequence) sb);
        return sb2.toString();
    }

    public static Fragment c(List<Fragment> list, int i, String str) {
        if (koq.b(list, i)) {
            LogUtil.h(str, "Parameter position of method getItem should not be out of mSubTabs bounds.");
            return null;
        }
        return list.get(i);
    }

    public static void a(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, final HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency, int i) {
        hwHealthBaseBarLineChart.getTransformer(hwHealthAxisDependency).a(new HwHealthTransformer.BusinessMatrixGenerator() { // from class: nsn.3
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthTransformer.BusinessMatrixGenerator
            public Matrix generateBusinessMatrix(Matrix matrix) {
                if (matrix != null) {
                    matrix.reset();
                }
                return matrix;
            }
        });
        if (i == 1) {
            hwHealthBaseBarLineChart.getAxis(hwHealthAxisDependency).setValueFormatter(new IAxisValueFormatter() { // from class: nsn.5
                @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
                public String getFormattedValue(float f, AxisBase axisBase) {
                    return nsn.b(f, HwHealthBaseBarLineDataSet.this);
                }
            });
        } else if (i == 2) {
            hwHealthBaseBarLineChart.getAxis(hwHealthAxisDependency).setValueFormatter(null);
        } else {
            hwHealthBaseBarLineChart.getAxis(hwHealthAxisDependency).setValueFormatter(new nmv());
        }
    }

    public static String b(float f, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        int b = b(hwHealthBaseBarLineDataSet.getYMax(), hwHealthBaseBarLineDataSet.getYMin());
        int d2 = d(hwHealthBaseBarLineDataSet.getYMax(), hwHealthBaseBarLineDataSet.getYMin());
        int i = (int) f;
        int i2 = (b - d2) / 4;
        Context context = BaseApplication.getContext();
        String string = context.getString(R$string.IDS_bolt_balance_left_touches);
        String string2 = context.getString(R$string.IDS_bolt_balance_right_touches);
        int round = Math.round(((b - 50) * 1.0f) / 2.0f) + 50;
        String b2 = i == d2 ? b(b, string2, context) : "";
        int i3 = d2 + i2;
        if (i == i3) {
            b2 = b(round, string2, context);
        }
        if (i == i3 + i2) {
            double d3 = 50;
            b2 = context.getResources().getString(R$string.IDS_chart_title_two, UnitUtil.e(d3, 1, 0), UnitUtil.e(d3, 1, 0));
        }
        if (i == b - i2) {
            b2 = b(round, string, context);
        }
        return i == b ? b(b, string, context) : b2;
    }

    public static int b(float f, float f2) {
        int round = Math.round(f);
        int i = round - 50;
        int floor = (int) Math.floor(f2);
        if (i < 50 - floor) {
            round = 100 - floor;
        }
        return round % 2 == 0 ? round : round + 1;
    }

    public static int d(float f, float f2) {
        int round = Math.round(f) - 50;
        int floor = 50 - ((int) Math.floor(f2));
        int i = 50 - round;
        if (round < floor) {
            i = 50 - floor;
        }
        return i % 2 == 0 ? i : i - 1;
    }

    private static String b(int i, String str, Context context) {
        return context.getResources().getString(R$string.IDS_run_plan_calendar_title, UnitUtil.e(i, 2, 0), str);
    }

    public static void c(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency, boolean z) {
        hwHealthBaseBarLineChart.getTransformer(hwHealthAxisDependency).a(new HwHealthTransformer.BusinessMatrixGenerator() { // from class: nsn.7
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthTransformer.BusinessMatrixGenerator
            public Matrix generateBusinessMatrix(Matrix matrix) {
                if (matrix != null) {
                    matrix.reset();
                }
                return matrix;
            }
        });
        if (!z) {
            hwHealthBaseBarLineChart.getAxis(hwHealthAxisDependency).setValueFormatter(new IAxisValueFormatter() { // from class: nsn.6
                @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
                public String getFormattedValue(float f, AxisBase axisBase) {
                    return UnitUtil.e(f, 2, 0);
                }
            });
        } else {
            hwHealthBaseBarLineChart.getAxis(hwHealthAxisDependency).setValueFormatter(null);
        }
    }

    public static int a(String[] strArr) {
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    public static Object c(int i, String[] strArr) {
        if (i < 0 || i >= a(strArr)) {
            return null;
        }
        return strArr[i];
    }

    public static void cKW_(DataRenderer dataRenderer, Canvas canvas, Chart chart, int i, List<Highlight> list, Highlight[] highlightArr) {
        Object lineData;
        if (dataRenderer instanceof nnb) {
            lineData = ((nnb) dataRenderer).b().getBarData();
        } else {
            lineData = dataRenderer instanceof nov ? ((nov) dataRenderer).e().getLineData() : null;
        }
        int indexOf = (lineData == null || !(chart.getData() instanceof nnd)) ? i : ((nnd) chart.getData()).d().indexOf(lineData);
        list.clear();
        for (Highlight highlight : highlightArr) {
            if (highlight.getDataIndex() == indexOf || highlight.getDataIndex() == i) {
                list.add(highlight);
            }
        }
        dataRenderer.drawHighlighted(canvas, (Highlight[]) list.toArray(new Highlight[list.size()]));
    }

    public static boolean af(Context context) {
        return Build.VERSION.SDK_INT <= 28 && v(context) && BaseActivity.isSumsung();
    }

    public static void d(HealthSearchView healthSearchView) {
        View findViewById;
        if (healthSearchView == null) {
            LogUtil.a(e, "applyDarkMode search view is null");
            return;
        }
        if (af(BaseApplication.getContext()) && (findViewById = healthSearchView.findViewById(R.id.search_plate)) != null) {
            findViewById.setBackgroundResource(R$drawable.hwsearchview_selector_search_bg);
        }
        if (BaseActivity.isFlyme() && v(BaseApplication.getContext())) {
            LogUtil.a(e, "applySearchViewDarkMode");
            a(healthSearchView, R.id.hwsearchview_back_button, R$drawable.health_navbar_back_selector);
            a(healthSearchView, R.id.hwsearchview_search_src_icon, R$drawable.health_searchview_ic_public_search);
            a(healthSearchView, R.id.search_close_btn, R$drawable.ic_public_select_cancel);
        }
    }

    public static void a(HealthSearchView healthSearchView, int i, int i2) {
        View findViewById = healthSearchView.findViewById(i);
        if (findViewById instanceof AppCompatImageView) {
            ((AppCompatImageView) findViewById).setImageResource(i2);
        }
    }

    public static boolean c(CharSequence... charSequenceArr) {
        if (charSequenceArr == null) {
            return false;
        }
        for (CharSequence charSequence : charSequenceArr) {
            if (TextUtils.isEmpty(charSequence)) {
                return false;
            }
        }
        return true;
    }

    public static void q() {
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h(e, "activity is null. return");
        } else {
            cLE_(activity);
        }
    }

    public static void ai(Context context) {
        if (context instanceof Activity) {
            cLE_((Activity) context);
        } else {
            LogUtil.a(e, "setShortTransAnimation context is not an activity");
        }
    }

    private static void cLE_(Activity activity) {
        if (activity != null) {
            activity.overridePendingTransition(R$anim.left_enter_short, R$anim.activity_no_animation_short);
        }
    }

    public static void cLD_(View view) {
        if (view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(marginLayoutParams.getMarginStart() + ((Integer) safeRegionWidth.first).intValue());
            marginLayoutParams.setMarginEnd(marginLayoutParams.getMarginEnd() + ((Integer) safeRegionWidth.second).intValue());
        }
    }

    public static String d(String str, List<String> list) {
        if (str == null && koq.b(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(list.get(0) != null ? list.get(0) : com.huawei.operation.utils.Constants.NULL);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            sb.append(str);
            sb.append(list.get(i) != null ? list.get(i) : com.huawei.operation.utils.Constants.NULL);
        }
        return sb.toString();
    }

    public static float f() {
        return BaseApplication.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static boolean a(float f) {
        return f() >= f;
    }

    public static boolean e(Context context, float f) {
        return g(context) >= f;
    }

    public static RelativeLayout.LayoutParams cLc_(Context context) {
        int r = r(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, r);
        Activity activity = BaseApplication.getActivity();
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        if (activity == null) {
            LogUtil.b(e, "getStatusBarLayoutParams activity is null");
            return layoutParams;
        }
        boolean isInMultiWindowMode = activity.isInMultiWindowMode();
        boolean cLg_ = cLg_(activity);
        if (isInMultiWindowMode && cLg_) {
            layoutParams.topMargin = -r;
        }
        return layoutParams;
    }

    public static boolean cLg_(Activity activity) {
        try {
            return ((Integer) ReflectionUtils.b(ReflectionUtils.d("com.huawei.android.app.ActivityManagerEx"), "getActivityWindowMode", Activity.class).invoke(null, activity)).intValue() == 102;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            LogUtil.a(e, "isFreeformMode reflect fail");
            return false;
        }
    }

    public static void cLf_(Context context, Bundle bundle) {
        if (bundle == null || context == null) {
            return;
        }
        if ((29 == Build.VERSION.SDK_INT || 28 == Build.VERSION.SDK_INT) && context.getClassLoader() != null) {
            bundle.setClassLoader(context.getClassLoader());
            Bundle bundle2 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            if (bundle2 == null || koq.b(bundle2.keySet())) {
                return;
            }
            for (String str : bundle2.keySet()) {
                if (bundle2.getBundle(str) != null) {
                    bundle2.getBundle(str).setClassLoader(context.getClassLoader());
                }
            }
        }
    }

    public static void cLe_(Intent intent, String str, Context context) {
        if (jdm.b(context, str) || !jdm.b(context, "com.huawei.appmarket") || CommonUtil.bf()) {
            return;
        }
        cLM_(intent, "com.huawei.appmarket", context, nsf.h(R$string.IDS_device_fragment_application_market));
    }

    public static void cLM_(final Intent intent, String str, final Context context, String str2) {
        LogUtil.a(e, "showLaunchAgreeDialog pkgName:", str, "defaultName:", str2);
        cKV_(str, str2, context, new View.OnClickListener() { // from class: nsm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLr_(context, intent, view);
            }
        }, null);
    }

    static /* synthetic */ void cLr_(Context context, Intent intent, View view) {
        cLR_(context, intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void cLN_(String str, Context context, String str2, View.OnClickListener onClickListener) {
        LogUtil.a(e, "showLaunchAgreeDialogNotIntent pkgName:", str, "defaultName:", str2);
        cLO_(str, context, str2, onClickListener, null);
    }

    public static void cLO_(String str, Context context, String str2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        cKV_(str, str2, context, onClickListener, onClickListener2);
    }

    private static void cKV_(String str, String str2, Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        int c = c(str, context, str2);
        if (c == -1) {
            if (onClickListener2 != null) {
                onClickListener2.onClick(null);
                return;
            }
            return;
        }
        if (c == 0) {
            cLI_(str, str2, context, onClickListener, onClickListener2);
        } else if (c == 1) {
            nrh.d(context, nsf.b(R$string.IDS_thirty_app_toast, a(str2, str)));
            cLl_(onClickListener, null, true);
            return;
        } else if (c == 2) {
            cLl_(onClickListener, null, false);
            return;
        }
        LogUtil.h(e, "other ways");
    }

    private static int c(String str, Context context, String str2) {
        if (context == null) {
            LogUtil.h(e, "context is null");
            return -1;
        }
        if ("com.huawei.health".equals(str) || BaseApplication.APP_PACKAGE_GOOGLE_HEALTH.equals(str)) {
            return 2;
        }
        String a2 = a(str2, str);
        if (nsd.e(str)) {
            LogUtil.a(e, "already told user");
            return 1;
        }
        LogUtil.a(e, "first jump to ", a2);
        return 0;
    }

    private static void cLl_(final View.OnClickListener onClickListener, final View view, boolean z) {
        if (onClickListener == null) {
            ReleaseLogUtil.a(f15468a, "jumpAppCustomWays listener is null");
        } else if (z && Build.VERSION.SDK_INT < 28 && BaseActivity.isOppoSystem()) {
            HandlerExecutor.d(new Runnable() { // from class: nsx
                @Override // java.lang.Runnable
                public final void run() {
                    onClickListener.onClick(view);
                }
            }, 500L);
        } else {
            onClickListener.onClick(view);
        }
    }

    private static String a(String str, String str2) {
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str2, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b(e, "NameNotFoundException");
            return str;
        }
    }

    private static void cLI_(final String str, String str2, Context context, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        final String a2 = a(str2, str);
        if ((context instanceof Application) && (context = BaseApplication.getActivity()) == null) {
            ReleaseLogUtil.a(f15468a, "topActivity == null ");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(nsf.b(R$string.IDS_thirty_app_dialog, a2)).czC_(R$string.IDS_compatibility_note_open, new View.OnClickListener() { // from class: nso
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLn_(str, a2, onClickListener, view);
            }
        }).czz_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: nsp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nsn.cLo_(onClickListener2, view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    static /* synthetic */ void cLn_(String str, String str2, View.OnClickListener onClickListener, View view) {
        LogUtil.a(e, "jump to other app by user agree");
        nsd.b(str);
        nrh.d(BaseApplication.getContext(), nsf.b(R$string.IDS_thirty_app_toast, str2));
        cLl_(onClickListener, view, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cLo_(View.OnClickListener onClickListener, View view) {
        LogUtil.a(e, "cancel the dialog");
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void cLR_(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.h(e, "startActivity intent is null.");
            return;
        }
        if (!(context instanceof Activity)) {
            Activity activity = BaseApplication.getActivity();
            if (activity != null) {
                context = activity;
            } else {
                intent.addFlags(268435456);
            }
        }
        if (context == null) {
            LogUtil.h(e, "startActivity context is null.");
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(e, "startActivity exception: ActivityNotFoundException");
        } catch (SecurityException unused2) {
            LogUtil.b(e, "startActivity exception: SecurityException");
        }
    }

    public static void y() {
        Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
        intent.setData(Uri.parse("package:" + BaseApplication.getAppPackage()));
        cLR_(BaseApplication.getActivity(), intent);
    }
}
