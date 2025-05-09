package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.health.healthmodel.bean.PictureBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepJsApi;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class pqg {
    private static int c = BaseApplication.getContext().getResources().getColor(R.color._2131297032_res_0x7f090308);
    private static int e = BaseApplication.getContext().getResources().getColor(R.color._2131299381_res_0x7f090c35);

    /* renamed from: a, reason: collision with root package name */
    private static int f16228a = BaseApplication.getContext().getResources().getColor(R.color._2131296782_res_0x7f09020e);
    private static int d = nsf.c(R.color._2131296523_res_0x7f09010b);
    private static int b = nsf.c(R.color._2131296887_res_0x7f090277);

    public static boolean h() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "CoreSleepUtils") == null && jpu.e("CoreSleepUtils") == null;
    }

    public static boolean j() {
        DeviceCapability d2 = cvs.d();
        return d2 != null && d2.isSupportCoreSleep();
    }

    public static String d(Context context, String str) {
        if (context == null) {
            LogUtil.h("CoreSleepUtils", "getDataFromSharedPreference context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("coreSleepRemindSP", 0);
        return sharedPreferences != null ? sharedPreferences.getString(str, "default_value") : "";
    }

    public static void d(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("CoreSleepUtils", "setDataToSharedPreference context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("coreSleepRemindSP", 0).edit();
        if (edit != null) {
            edit.putString(str, str2);
            edit.apply();
        }
    }

    public static String d(Context context, int i, boolean z) {
        switch (i) {
            case 71:
                if (z) {
                    return context.getString(R$string.IDS_details_sleep_grade_late);
                }
                return context.getString(R$string.IDS_details_sleep_grade_high);
            case 72:
                if (z) {
                    return context.getString(R$string.IDS_details_sleep_grade_early);
                }
                return context.getString(R$string.IDS_details_sleep_grade_low);
            case 73:
                return context.getString(R$string.IDS_details_sleep_grade_normal_1);
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String c(int i, String str) {
        char c2;
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("CoreSleepUtils", "getStatusText type ", str);
            return "";
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1546853722:
                if (str.equals("TYPE_DEEP_SLEEP_CONTINUITY")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1151624095:
                if (str.equals("TYPE_NIGHT_SLEEP_TIME")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -785678506:
                if (str.equals("TYPE_SLEEP_EFFICIENCY")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -490874802:
                if (str.equals("TYPE_WAKE_UP_REGULAR")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -426685062:
                if (str.equals("TYPE_SLEEP_TIME")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -415951927:
                if (str.equals("TYPE_DEEP_SLEEP")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -385938629:
                if (str.equals("TYPE_SLEEP_LATENCY_TIME")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 808690857:
                if (str.equals("TYPE_LIGHT_SLEEP")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1022318430:
                if (str.equals("TRUSLEEP_FIVE_HRV")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 1029392504:
                if (str.equals("TYPE_WAKE_UP_TIMES")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 1252391960:
                if (str.equals("TYPE_SLEEP_BED_TIME")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 1285815879:
                if (str.equals("TRUSLEEP_FIVE_HEART_RATE")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case 1550128305:
                if (str.equals("TYPE_SLUM_SLEEP_RATE")) {
                    c2 = '\f';
                    break;
                }
                c2 = 65535;
                break;
            case 1627456622:
                if (str.equals("TRUSLEEP_FIVE_SpO2")) {
                    c2 = '\r';
                    break;
                }
                c2 = 65535;
                break;
            case 1745853349:
                if (str.equals("TYPE_BREATH_QUALITY")) {
                    c2 = 14;
                    break;
                }
                c2 = 65535;
                break;
            case 1912088818:
                if (str.equals("TYPE_FALL_GO_TO_BED_REGULAR")) {
                    c2 = 15;
                    break;
                }
                c2 = 65535;
                break;
            case 1993820721:
                if (str.equals("TRUSLEEP_FIVE_BREATH_RATE")) {
                    c2 = 16;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
            case 2:
            case 3:
            case 14:
            case 15:
                break;
            case 1:
            case 4:
            case 6:
            case '\n':
                break;
            case 5:
            case 7:
            case '\f':
                break;
            case '\b':
            case 11:
            case '\r':
            case 16:
                break;
            case '\t':
                break;
            default:
                ReleaseLogUtil.b("CoreSleepUtils", "getStatusText type ", str);
                break;
        }
        return "";
    }

    private static String a(int i) {
        switch (i) {
            case 71:
                return nsf.h(R$string.IDS_details_sleep_grade_high);
            case 72:
                return nsf.h(R$string.IDS_details_sleep_grade_low);
            case 73:
                return nsf.h(R$string.IDS_details_sleep_grade_normal);
            default:
                ReleaseLogUtil.b("CoreSleepUtils", "getStatusText status ", Integer.valueOf(i));
                return nsf.h(R$string.IDS_physiological_index_invalid);
        }
    }

    private static String g(int i) {
        switch (i) {
            case 71:
                return nsf.h(R$string.IDS_details_sleep_grade_high_1);
            case 72:
                return nsf.h(R$string.IDS_details_sleep_grade_low_1);
            case 73:
                return nsf.h(R$string.IDS_details_sleep_grade_normal_1);
            default:
                ReleaseLogUtil.b("CoreSleepUtils", "getStatusText1 status ", Integer.valueOf(i));
                return nsf.h(R$string.IDS_physiological_index_invalid);
        }
    }

    private static String h(int i) {
        switch (i) {
            case 71:
                return nsf.h(R$string.IDS_details_sleep_grade_high_2);
            case 72:
                return nsf.h(R$string.IDS_details_sleep_grade_low_2);
            case 73:
                return nsf.h(R$string.IDS_details_sleep_grade_normal);
            default:
                ReleaseLogUtil.b("CoreSleepUtils", "getStatusText2 status ", Integer.valueOf(i));
                return nsf.h(R$string.IDS_physiological_index_invalid);
        }
    }

    private static String j(int i) {
        switch (i) {
            case 71:
                return nsf.h(R$string.IDS_hwh_runningstyle_longer);
            case 72:
                return nsf.h(R$string.IDS_hwh_runningstyle_shorter);
            case 73:
                return nsf.h(R$string.IDS_details_sleep_grade_normal);
            default:
                ReleaseLogUtil.b("CoreSleepUtils", "getStatusText3 status ", Integer.valueOf(i));
                return nsf.h(R$string.IDS_physiological_index_invalid);
        }
    }

    private static String i(int i) {
        switch (i) {
            case 71:
                return nsf.h(R$string.IDS_physiological_index_high);
            case 72:
                return nsf.h(R$string.IDS_physiological_index_low);
            case 73:
                return nsf.h(R$string.IDS_physiological_index_normal);
            default:
                ReleaseLogUtil.b("CoreSleepUtils", "getTruSleepFiveStatusText status ", Integer.valueOf(i));
                return "";
        }
    }

    public static int d(int i) {
        switch (i) {
            case 71:
                return e;
            case 72:
                return f16228a;
            case 73:
                return c;
            default:
                return 0;
        }
    }

    public static int c(int i) {
        switch (i) {
            case 71:
            case 72:
                return b;
            case 73:
                return d;
            default:
                return 0;
        }
    }

    public static String e(CharSequence[] charSequenceArr) {
        Context context = BaseApplication.getContext();
        if (charSequenceArr == null || charSequenceArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (LanguageUtil.q(context)) {
            while (i < charSequenceArr.length - 1) {
                int i2 = i + 1;
                sb.append(context.getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, Integer.valueOf(i2)));
                sb.append(" ");
                sb.append(charSequenceArr[i]);
                sb.append("\n");
                i = i2;
            }
            sb.append(context.getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, Integer.valueOf(charSequenceArr.length)));
            sb.append(" ");
            sb.append(charSequenceArr[charSequenceArr.length - 1]);
            return sb.toString();
        }
        while (i < charSequenceArr.length - 1) {
            int i3 = i + 1;
            sb.append(context.getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, Integer.valueOf(i3)));
            sb.append(charSequenceArr[i]);
            sb.append("\n");
            i = i3;
        }
        sb.append(context.getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, Integer.valueOf(charSequenceArr.length)));
        sb.append(charSequenceArr[charSequenceArr.length - 1]);
        return sb.toString();
    }

    public static String b(String str, String str2) {
        String a2 = scn.a(BaseApplication.getContext());
        String string = BaseApplication.getContext().getString(R$string.IDS_sleep_referece_title_string);
        if (LanguageUtil.bp(BaseApplication.getContext()) || LanguageUtil.y(BaseApplication.getContext())) {
            StringBuilder sb = new StringBuilder(str2);
            Locale locale = Locale.ROOT;
            sb.append(a2);
            sb.append(str);
            return String.format(locale, string, sb);
        }
        if (LanguageUtil.q(BaseApplication.getContext()) || LanguageUtil.aw(BaseApplication.getContext())) {
            return String.format(Locale.ROOT, string + " ", str + a2 + str2);
        }
        return String.format(Locale.ROOT, string, str + a2 + str2);
    }

    public static String e(double d2, int i) {
        Context context = BaseApplication.getContext();
        if (LanguageUtil.bp(context) || LanguageUtil.y(context) || LanguageUtil.bn(context)) {
            StringBuilder sb = new StringBuilder(UnitUtil.e(d2, 1, 0));
            sb.insert(0, "%");
            return sb.toString();
        }
        return UnitUtil.e(d2, 2, i);
    }

    public static String c(int i, double d2, double d3) {
        String e2;
        String e3;
        if (LanguageUtil.bo(BaseApplication.getContext())) {
            e2 = e(d2, 0);
            e3 = UnitUtil.e(d3, 1, 0);
        } else {
            e2 = UnitUtil.e(d2, 1, 0);
            e3 = e(d3, 0);
        }
        return nsf.b(i, e2, e3);
    }

    public static String e(int i, int i2, int i3) {
        if (i3 == 0) {
            return c(i, i2);
        }
        if (i3 == 1) {
            return d(i, i2);
        }
        if (i3 == 2) {
            return e(i, i2);
        }
        if (i3 == 3) {
            return a(i, i2);
        }
        if (i3 == 4) {
            return b(i2);
        }
        LogUtil.b("CoreSleepUtils", "error type: ", Integer.valueOf(i3));
        return "";
    }

    private static String c(int i, int i2) {
        return nsf.b(R$string.IDS_sleep_referece_title_string, nsf.b(R$string.IDS_details_sleep_between_points, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0)));
    }

    private static String d(int i, int i2) {
        return nsf.b(R$string.IDS_sleep_referece_title_string, nsf.b(R$string.IDS_details_sleep_between_times, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0)));
    }

    private static String e(int i, int i2) {
        String b2;
        String e2 = UnitUtil.e(i, 1, 0);
        String e3 = UnitUtil.e(i2, 1, 0);
        if (i == 6 && i2 == 10.5f) {
            b2 = nsf.b(R$string.IDS_details_sleep_between_hours, e2, Integer.valueOf(i2));
        } else {
            b2 = nsf.b(R$string.IDS_member_sleep_add_record, e2, e3);
        }
        return nsf.b(R$string.IDS_sleep_referece_title_string, b2);
    }

    private static String a(int i, int i2) {
        return nsf.b(R$string.IDS_sleep_referece_title_string, c(R$string.IDS_base_line_percent, i, i2));
    }

    private static String b(int i) {
        return nsf.b(R$string.IDS_sleep_referece_title_string, nsf.b(R$string.IDS_sleep_range_less_than_min, UnitUtil.e(i, 1, 0)));
    }

    public static void b(Context context) {
        H5ProClient.getServiceManager().registerService(SleepJsApi.class);
        if (context == null) {
            LogUtil.h("CoreSleepUtils", "jumpToManualEntry: context is null");
        } else {
            bzs.e().loadH5ProApp(context, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/sleepDataInput?pullFrom=1").addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepDetection", mtp.d().getJsModule()).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
        }
    }

    public static ImageBean e() {
        return e(6);
    }

    public static ImageBean b() {
        return e(101);
    }

    public static ImageBean g() {
        return e(100);
    }

    public static Bitmap drZ_() {
        return drY_(e());
    }

    public static Bitmap dsa_() {
        return drY_(b());
    }

    public static Bitmap dsb_() {
        return drY_(g());
    }

    private static Bitmap drY_(ImageBean imageBean) {
        if (imageBean == null) {
            return nrf.cHR_(R$drawable.health_life_background);
        }
        Iterator<PictureBean> it = imageBean.getPictureList().iterator();
        while (it.hasNext()) {
            PictureBean next = it.next();
            if (next.getScenario() == 100) {
                return nrf.cHQ_(next.getPath());
            }
        }
        return nrf.cHR_(R$drawable.health_life_background);
    }

    private static ImageBean e(int i) {
        for (ImageBean imageBean : dsl.e("1")) {
            if (imageBean.getId() == i) {
                return imageBean;
            }
        }
        return null;
    }

    public static List<Integer> i() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(3);
        arrayList.add(2);
        return arrayList;
    }
}
