package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.AudioManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import com.amap.api.location.AMapLocation;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class gwg {
    private static String b = "WGS84";
    private static int d = -1;
    private static int e = 1;
    private static int h = 1;

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f12969a = new ArrayList(10);
    private static List<String> g = new ArrayList(10);
    private static List<String> o = new ArrayList(10);
    private static boolean m = true;
    private static boolean i = false;
    private static boolean f = true;
    private static volatile Boolean j = null;
    private static volatile Boolean c = null;
    private static String n = null;

    public static double a(long j2) {
        if (j2 < 0) {
            return 0.0d;
        }
        return j2 / 1000.0d;
    }

    public static int b(int i2) {
        return (int) ((i2 * 0.6213712d) / 1000.0d);
    }

    public static float c(int i2, float f2) {
        return (i2 == 0 || i2 == 1) ? f2 * 1000.0f : f2;
    }

    public static double g(int i2) {
        return i2 * 0.6213712d;
    }

    public static boolean h(int i2) {
        return i2 == 264 || i2 == 258;
    }

    public static boolean i(int i2) {
        return i2 == 0 || i2 == 1 || i2 == 2;
    }

    public static boolean j(int i2) {
        return i2 == 258 || i2 == 257 || i2 == 259 || i2 == 264;
    }

    static {
        f12969a.add("IR");
        g.add("432");
    }

    public static String c(float f2, int i2) {
        return String.valueOf(new BigDecimal(f2).setScale(i2, 4).doubleValue());
    }

    public static String a(int i2) {
        int i3 = i2 % 100;
        if (i3 >= 10) {
            return String.valueOf(i3);
        }
        return "0" + i3;
    }

    public static String e() {
        String format = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date());
        StringBuilder sb = new StringBuilder(16);
        sb.append("gps_maptracking_");
        sb.append(format);
        return sb.toString();
    }

    public static boolean n(Context context) {
        return NetworkUtil.m();
    }

    public static boolean f() {
        return "com.huawei.health".equals(BaseApplication.getAppPackage());
    }

    public static void b(Context context) {
        gww gwwVar = new gww(context, new StorageParams(), Integer.toString(20002));
        h = gwwVar.v();
        d = gwwVar.j();
    }

    public static int e(Context context) {
        b(context);
        int a2 = a();
        ReleaseLogUtil.e("Track_MapTrackingUtils", "getSportMapType auto map", Integer.valueOf(a2));
        return ((a2 == -1 && (h() || gwe.d(context))) || a2 == 0) ? aUP_(context, gwe.aUH_(context), false) : a2;
    }

    public static int a() {
        return h;
    }

    public static int d() {
        return d;
    }

    public static int b() {
        int i2 = h;
        return i2 != 0 ? i2 : d;
    }

    public static int d(Context context) {
        return new gww(context, new StorageParams(), Integer.toString(20002)).k();
    }

    public static int aUP_(Context context, Location location, boolean z) {
        int b2 = location != null ? ktl.b(location.getLatitude(), location.getLongitude()) : -1;
        if (b2 == 1) {
            b = AMapLocation.COORD_TYPE_GCJ02;
        } else {
            b = AMapLocation.COORD_TYPE_WGS84;
        }
        t(context);
        int i2 = h;
        if (i2 != 0) {
            d = i2;
            LogUtil.a("Track_MapTrackingUtils", "MapType ", Integer.valueOf(i2));
        } else if (CommonUtil.bh()) {
            d = aUS_(context, b2, location, z);
        } else {
            d = c(context, b2);
        }
        LogUtil.a("Track_MapTrackingUtils", "MapType ", Integer.valueOf(d));
        w(context);
        return d;
    }

    private static void t(Context context) {
        int i2 = h;
        if (i2 == -1) {
            r(context);
            return;
        }
        if (i2 == 0) {
            o(context);
            return;
        }
        if (i2 == 1) {
            l(context);
        } else if (i2 == 2) {
            k(context);
        } else {
            if (i2 != 3) {
                return;
            }
            p(context);
        }
    }

    private static void o(Context context) {
        boolean d2 = gwe.d(context);
        boolean h2 = h();
        if (!Utils.o()) {
            if (h2 || d2) {
                return;
            }
            h = 1;
            nrw.b(context, 1);
            return;
        }
        if (!h2 && !d2) {
            h = -1;
            nrw.b(context, -1);
        } else if (!h2) {
            h = 3;
            nrw.b(context, 3);
        } else {
            if (d2) {
                return;
            }
            h = 2;
            nrw.b(context, 2);
        }
    }

    private static void l(Context context) {
        if (Utils.o()) {
            boolean h2 = h();
            boolean d2 = gwe.d(context);
            if (!h2 && !d2) {
                h = -1;
                nrw.b(context, -1);
            } else if (!h2) {
                h = 3;
                nrw.b(context, 3);
            } else if (!d2) {
                h = 2;
                nrw.b(context, 2);
            } else {
                h = 0;
                nrw.b(context, 0);
            }
        }
    }

    private static void k(Context context) {
        boolean h2 = h();
        boolean d2 = gwe.d(context);
        if (Utils.o()) {
            if (!h2 && !d2) {
                h = -1;
                nrw.b(context, -1);
                return;
            } else {
                if (h2) {
                    return;
                }
                h = 3;
                nrw.b(context, 3);
                return;
            }
        }
        if (!h2 && !d2) {
            h = 1;
            nrw.b(context, 1);
        } else {
            if (h2) {
                return;
            }
            h = 0;
            nrw.b(context, 0);
        }
    }

    private static void p(Context context) {
        boolean h2 = h();
        boolean d2 = gwe.d(context);
        if (Utils.o()) {
            if (!h2 && !d2) {
                h = -1;
                nrw.b(context, -1);
                return;
            } else {
                if (d2) {
                    return;
                }
                h = 2;
                nrw.b(context, 2);
                return;
            }
        }
        if (!h2 && !d2) {
            h = 1;
            nrw.b(context, 1);
        } else {
            if (d2) {
                return;
            }
            h = 0;
            nrw.b(context, 0);
        }
    }

    private static void r(Context context) {
        boolean h2 = h();
        boolean d2 = gwe.d(context);
        if (!Utils.o()) {
            if (!h2 && !d2) {
                h = 1;
                nrw.b(context, 1);
                return;
            } else {
                if (h2 || d2) {
                    h = 0;
                    nrw.b(context, 0);
                    return;
                }
                return;
            }
        }
        if (h2 && d2) {
            h = 0;
            nrw.b(context, 0);
        } else if (h2) {
            h = 2;
            nrw.b(context, 2);
        } else if (d2) {
            h = 3;
            nrw.b(context, 3);
        }
    }

    private static int aUS_(Context context, int i2, Location location, boolean z) {
        if (!Utils.o()) {
            return aUQ_(context, i2, location, z);
        }
        return aUR_(context, i2, location, z);
    }

    private static int aUQ_(Context context, int i2, Location location, boolean z) {
        int a2 = nrw.a(context);
        if (a2 == 0) {
            if (i2 == 1 || location == null) {
                d = 1;
            } else if (h()) {
                x(context);
                d = 2;
            } else {
                d = 3;
            }
            return d;
        }
        if (a2 == 1) {
            d = 1;
            return 1;
        }
        if (a2 == 2) {
            if (h() && (!nrw.e(context) || n(context))) {
                x(context);
                d = 2;
            } else if (gwe.d(context)) {
                if (!h(context)) {
                    d(context, z);
                }
                d = 3;
            } else {
                d(context, z);
                x(context);
                d = 2;
            }
            return d;
        }
        d = 1;
        return 1;
    }

    private static int aUR_(Context context, int i2, Location location, boolean z) {
        int a2 = nrw.a(context);
        if (a2 == 0) {
            if (i2 == 1) {
                d(context, z);
                d = 3;
            } else {
                x(context);
                d = 2;
            }
            return d;
        }
        if (a2 == 1) {
            if (h() && !nrw.c(context) && !nrw.e(context)) {
                x(context);
                d = 2;
            } else if (gwe.d(context)) {
                d(context, z);
                d = 3;
            } else {
                d(context, z);
                x(context);
                d = 2;
            }
            return d;
        }
        if (a2 == 2) {
            if (h() && (!nrw.e(context) || n(context))) {
                x(context);
                d = 2;
            } else {
                if (!h(context)) {
                    d(context, z);
                }
                d = 3;
            }
            return d;
        }
        if (h()) {
            x(context);
            d = 2;
        } else {
            if (!h(context)) {
                d(context, z);
            }
            d = 3;
        }
        return d;
    }

    private static int c(Context context, int i2) {
        int a2 = nrw.a(context);
        if (a2 == 0) {
            if (i2 == 1) {
                d = 1;
            } else if (i2 == 2 || i2 == 3) {
                x(context);
                d = 2;
            } else {
                d = 1;
            }
            return d;
        }
        if (a2 == 1) {
            d = 1;
            return 1;
        }
        if (a2 == 2) {
            x(context);
            d = 2;
            return 2;
        }
        d = 1;
        return 1;
    }

    private static void w(Context context) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "auto_map_setting", Integer.toString(d), new StorageParams());
    }

    private static void x(Context context) {
        if (c(context)) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(context.getString(R.string._2130837688_res_0x7f0200b8)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: gwg.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    private static void d(Context context, boolean z) {
        if (!z) {
            LogUtil.a("Track_MapTrackingUtils", "not showing dialog");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(context.getString(R$string.IDS_hwh_motiontrack_map_type_area_remind_auto)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: gwg.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    public static boolean g(Context context) {
        int a2 = nrw.a(context);
        if (a2 != 0) {
            return a2 == 2;
        }
        return Utils.o();
    }

    public static boolean j(Context context) {
        boolean o2;
        int a2 = nrw.a(context);
        if (a2 == 0) {
            o2 = Utils.o();
        } else {
            if (a2 != 1) {
                return false;
            }
            o2 = Utils.o();
        }
        return !o2;
    }

    public static boolean f(Context context) {
        if (nrw.a(context) == 2) {
            return !h(context);
        }
        return false;
    }

    public static int e(Context context, double d2, double d3) {
        int b2 = ktl.b(d2, d3);
        b(context);
        t(context);
        int i2 = h;
        if (i2 != 0) {
            e = i2;
        } else if (Utils.o()) {
            d(context, d2, d3, b2);
        } else {
            b(context, d2, d3, b2);
        }
        LogUtil.a("Track_MapTrackingUtils", "pickTrackAutoMap mAutoTrackMapType: ", Integer.valueOf(e));
        v(context);
        return e;
    }

    private static void v(Context context) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "auto_track_map_setting", Integer.toString(e), new StorageParams());
    }

    private static void b(Context context, double d2, double d3, int i2) {
        if (i2 == 1) {
            e = 1;
            return;
        }
        if (CommonUtil.bh()) {
            if (h() && !nrw.e(context)) {
                e = 2;
                return;
            }
            if (gwe.d(context)) {
                e = 3;
                return;
            } else if (h()) {
                e = 2;
                return;
            } else {
                e = 1;
                return;
            }
        }
        if (h()) {
            e = 2;
        } else {
            e = 1;
        }
    }

    private static void d(Context context, double d2, double d3, int i2) {
        if (!nrw.e(context)) {
            e = 2;
        } else if (nrw.a(context) != 1 && n(context)) {
            e = 2;
        } else {
            e = 3;
        }
    }

    public static boolean h(Context context) {
        if (!gwe.d(context)) {
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return true;
        }
        if (TextUtils.isEmpty(telephonyManager.getNetworkOperator())) {
            return true;
        }
        return !g.contains(r4.substring(0, 3));
    }

    public static boolean d(Context context, double d2, double d3) {
        if (!gwe.d(context)) {
            return false;
        }
        if (gwe.d(d2) || gwe.a(d3)) {
            LogUtil.h("Track_MapTrackingUtils", "isSupportTomTom: data is invalid");
            return false;
        }
        try {
            List<Address> fromLocation = new Geocoder(context).getFromLocation(d2, d3, 1);
            if (fromLocation == null || fromLocation.size() <= 0) {
                return true;
            }
            return !f12969a.contains(fromLocation.get(0).getCountryCode());
        } catch (IOException e2) {
            LogUtil.b("Track_MapTrackingUtils", LogAnonymous.b((Object) e2));
            return true;
        }
    }

    public static boolean h() {
        return CommonUtil.bx();
    }

    public static boolean g() {
        b(BaseApplication.getContext());
        if (h == 2 || (!CommonUtil.bh() && Utils.o())) {
            return !c(BaseApplication.getContext());
        }
        return false;
    }

    public static boolean i() {
        return (BaseApplication.getContext().getSystemService("sensor") instanceof SensorManager) && ((SensorManager) BaseApplication.getContext().getSystemService("sensor")).getDefaultSensor(19) != null;
    }

    public static String c(long j2) {
        return new SimpleDateFormat("yyyyMMdd HHmmss", Locale.ENGLISH).format(Long.valueOf(j2)).substring(9, 15);
    }

    public static boolean c(Context context) {
        if (context != null) {
            return GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0;
        }
        LogUtil.h("Track_MapTrackingUtils", "isInstallGooglePlayServicesNoDialog context == null");
        return false;
    }

    public static int m(Context context) {
        if (context == null) {
            LogUtil.h("Track_MapTrackingUtils", "obtainPhoneState context == null");
            return 3;
        }
        if (Build.VERSION.SDK_INT >= 31 && !PermissionUtil.e(context, new String[]{"android.permission.READ_PHONE_STATE"})) {
            return 3;
        }
        Object systemService = context.getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.a("Track_MapTrackingUtils", "object is not instanceof TelephonyManager");
            return 3;
        }
        int callState = ((TelephonyManager) systemService).getCallState();
        if (callState == 0) {
            return 0;
        }
        if (callState == 2) {
            return 1;
        }
        return callState == 1 ? 2 : 3;
    }

    public static int d(int i2) {
        return i2 / 1000;
    }

    public static String e(Context context, int i2) {
        if (context == null) {
            return "";
        }
        HwSportTypeInfo d2 = hln.c(context).d(i2);
        int b2 = d2 != null ? gxz.b(d2.getSportTypeRes(), context) : 0;
        if (b2 == 0) {
            return context.getResources().getString(R.string.IDS_start_track_sport_type_run);
        }
        return context.getResources().getString(b2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String c(Context context, String str) {
        char c2;
        if (context == null) {
            return "";
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 77206636:
                if (str.equals("R008R")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 77206667:
                if (str.equals("R009R")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 77207349:
                if (str.equals("R010R")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 77207380:
                if (str.equals("R011R")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 77207411:
                if (str.equals("R012R")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 77207442:
                if (str.equals("R013R")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 77207473:
                if (str.equals("R014R")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 77207504:
                if (str.equals("R015R")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 77207535:
                if (str.equals("R016R")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 77207566:
                if (str.equals("R017R")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 77207597:
                if (str.equals("R018R")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 77207628:
                if (str.equals("R019R")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case 77208310:
                if (str.equals("R020R")) {
                    c2 = '\f';
                    break;
                }
                c2 = 65535;
                break;
            case 77208341:
                if (str.equals("R021R")) {
                    c2 = '\r';
                    break;
                }
                c2 = 65535;
                break;
            case 77208372:
                if (str.equals("R022R")) {
                    c2 = 14;
                    break;
                }
                c2 = 65535;
                break;
            case 77208403:
                if (str.equals("R023R")) {
                    c2 = 15;
                    break;
                }
                c2 = 65535;
                break;
            case 77208434:
                if (str.equals("R024R")) {
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
        }
        return "";
    }

    public static String d(Context context, String str, String str2) {
        if (context == null) {
            return "";
        }
        String c2 = !TextUtils.isEmpty(str) ? c(context, str) : "";
        return c2.equals("") ? str2 : c2;
    }

    public static boolean a(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null || motionPathSimplify.requestExtendDataMap() == null || motionPathSimplify.requestSportType() != 258) {
            return false;
        }
        Map<String, String> requestExtendDataMap = motionPathSimplify.requestExtendDataMap();
        String str = requestExtendDataMap.containsKey(HwExerciseConstants.JSON_NAME_RECORD_FLAG) ? requestExtendDataMap.get(HwExerciseConstants.JSON_NAME_RECORD_FLAG) : "";
        if (!TextUtils.isEmpty(str) && "7".equals(str)) {
            LogUtil.a("Track_MapTrackingUtils", "isLacticAcidData");
            return true;
        }
        return false;
    }

    public static String e(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify == null ? "" : e(motionPathSimplify.requestExtendDataMap());
    }

    public static String e(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        Context context = BaseApplication.getContext();
        if (!LanguageUtil.h(context)) {
            return "";
        }
        if (LanguageUtil.m(context) && map.containsKey("expNameCN")) {
            return map.get("expNameCN");
        }
        return map.containsKey("expNameHK") ? map.get("expNameHK") : "";
    }

    public static final boolean b(MotionPathSimplify motionPathSimplify) {
        Map<String, Integer> requestSportData;
        if (motionPathSimplify == null || (requestSportData = motionPathSimplify.requestSportData()) == null) {
            return false;
        }
        int requestSportType = motionPathSimplify.requestSportType();
        return (requestSportType == 262 || requestSportType == 266) && requestSportData.get(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH) == null && requestSportData.get(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES) == null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean u(android.content.Context r4) {
        /*
            java.lang.String r0 = "Track_MapTrackingUtils"
            r1 = 0
            if (r4 != 0) goto L6
            return r1
        L6:
            boolean r2 = health.compact.a.Utils.o()
            if (r2 == 0) goto Ld
            return r1
        Ld:
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            java.lang.String r2 = defpackage.gwh.s     // Catch: java.lang.RuntimeException -> L1a android.content.pm.PackageManager.NameNotFoundException -> L27
            r3 = 128(0x80, float:1.8E-43)
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo(r2, r3)     // Catch: java.lang.RuntimeException -> L1a android.content.pm.PackageManager.NameNotFoundException -> L27
            goto L34
        L1a:
            r4 = move-exception
            java.lang.String r4 = health.compact.a.LogAnonymous.b(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
            goto L33
        L27:
            r4 = move-exception
            java.lang.String r4 = health.compact.a.LogAnonymous.b(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
        L33:
            r4 = 0
        L34:
            if (r4 == 0) goto L43
            android.os.Bundle r0 = r4.metaData
            if (r0 == 0) goto L43
            android.os.Bundle r4 = r4.metaData
            java.lang.String r0 = "app-meta-ex-api-support-health"
            boolean r4 = r4.getBoolean(r0)
            return r4
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gwg.u(android.content.Context):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean s(android.content.Context r7) {
        /*
            java.lang.String r0 = "supportFlag"
            java.lang.String r1 = "Track_MapTrackingUtils"
            boolean r2 = defpackage.gwg.i
            if (r2 != 0) goto L58
            r2 = 0
            r3 = 1
            java.lang.String r4 = "content://com.android.mediacenter.healthprovider"
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch: java.lang.Exception -> L42
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L42
            java.lang.String r6 = "enter healthprovider"
            r5[r2] = r6     // Catch: java.lang.Exception -> L42
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)     // Catch: java.lang.Exception -> L42
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch: java.lang.Exception -> L42
            android.os.Bundle r5 = new android.os.Bundle     // Catch: java.lang.Exception -> L42
            r5.<init>()     // Catch: java.lang.Exception -> L42
            r6 = 0
            android.os.Bundle r7 = r7.call(r4, r0, r6, r5)     // Catch: java.lang.Exception -> L42
            if (r7 == 0) goto L50
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L42
            java.lang.String r5 = "supportFlag Value "
            r4[r2] = r5     // Catch: java.lang.Exception -> L42
            int r5 = r7.getInt(r0)     // Catch: java.lang.Exception -> L42
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L42
            r4[r3] = r5     // Catch: java.lang.Exception -> L42
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)     // Catch: java.lang.Exception -> L42
            int r7 = r7.getInt(r0)     // Catch: java.lang.Exception -> L42
            goto L51
        L42:
            r7 = move-exception
            java.lang.String r0 = "isSupportSportMusicInControlVersion"
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r0, r7}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r7)
        L50:
            r7 = r2
        L51:
            defpackage.gwg.i = r3
            if (r7 != r3) goto L56
            r2 = r3
        L56:
            defpackage.gwg.f = r2
        L58:
            boolean r7 = defpackage.gwg.f
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gwg.s(android.content.Context):boolean");
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(n) || !n.equals(str)) {
            n = str;
            i = false;
        }
    }

    private static boolean q(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(gwh.s, 128);
            try {
                if (applicationInfo.metaData != null) {
                    return applicationInfo.metaData.getInt("supportHealthRun") == 1;
                }
                return false;
            } catch (NumberFormatException e2) {
                LogUtil.b("Track_MapTrackingUtils", LogAnonymous.b((Throwable) e2));
                return false;
            }
        } catch (PackageManager.NameNotFoundException e3) {
            LogUtil.b("Track_MapTrackingUtils", LogAnonymous.b((Throwable) e3));
            return false;
        } catch (Exception unused) {
            LogUtil.b("Track_MapTrackingUtils", "Dead System Exception is happened catch it ");
            return false;
        }
    }

    public static final boolean i(Context context) {
        if (context == null) {
            return false;
        }
        if (!gvv.a()) {
            ReleaseLogUtil.e("Track_MapTrackingUtils", "isSupportSportMusic not isSupportMusicPresetHealthApp()");
            return false;
        }
        if (q(context)) {
            if (Utils.o()) {
                return s(context);
            }
            return true;
        }
        return u(context);
    }

    public static final boolean a(Context context) {
        if (j != null) {
            return j.booleanValue();
        }
        if (context == null) {
            j = false;
            return j.booleanValue();
        }
        if (!gvv.a()) {
            ReleaseLogUtil.e("Track_MapTrackingUtils", "isSupportMusicControl not isSupportMusicPresetHealthApp()");
            j = false;
            return j.booleanValue();
        }
        if (q(context)) {
            if (Utils.o()) {
                j = Boolean.valueOf(s(context));
                return j.booleanValue();
            }
            j = true;
            return j.booleanValue();
        }
        j = false;
        return j.booleanValue();
    }

    public static boolean c() {
        if (c != null) {
            return c.booleanValue();
        }
        boolean z = true;
        if (gww.a(BaseApplication.getContext()) != 1 && !i(com.huawei.haf.application.BaseApplication.e())) {
            z = false;
        }
        c = Boolean.valueOf(z);
        return c.booleanValue();
    }

    public static String aUO_(Resources resources, int i2) {
        if (resources == null) {
            return "";
        }
        if (i2 == 1) {
            return resources.getString(R.string._2130839815_res_0x7f020907);
        }
        if (i2 == 2) {
            return resources.getString(R.string._2130839813_res_0x7f020905);
        }
        if (i2 == 3) {
            return resources.getString(R.string._2130839816_res_0x7f020908);
        }
        if (i2 == 4) {
            return resources.getString(R.string._2130839814_res_0x7f020906);
        }
        if (i2 == 5) {
            return resources.getString(R.string._2130839817_res_0x7f020909);
        }
        return resources.getString(R.string._2130839813_res_0x7f020905);
    }

    public static MotionPath d(Map<Long, double[]> map, ArrayList<HeartRateData> arrayList, ArrayList<StepRateData> arrayList2, Map<Integer, Float> map2, Map<Integer, Float> map3, ArrayList<knz> arrayList3, ArrayList<HeartRateData> arrayList4, ArrayList<ffs> arrayList5) {
        TreeMap treeMap = new TreeMap();
        if (map != null && map.size() > 0) {
            synchronized (gtx.f12936a) {
                for (Map.Entry<Long, double[]> entry : map.entrySet()) {
                    treeMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        MotionPath motionPath = new MotionPath();
        motionPath.saveHeartRateList(arrayList);
        motionPath.saveLbsDataMap(treeMap);
        motionPath.savePaceMap(map2);
        motionPath.saveBritishPaceMap(map3);
        motionPath.saveStepRateList(arrayList2);
        motionPath.saveAltitudeList(arrayList3);
        motionPath.saveInvalidHeartRateList(arrayList4);
        motionPath.saveRunningPostureList(arrayList5);
        return motionPath;
    }

    public static boolean j() {
        if (BaseApplication.getContext() != null) {
            b(GRSManager.a(BaseApplication.getContext()).getMccCountryCode());
        }
        return m;
    }

    private static void b(String str) {
        o();
        if (o.size() == 0) {
            return;
        }
        m = o.contains(str);
    }

    private static void o() {
        try {
            JSONArray jSONArray = new JSONArray(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "hms_forbidden_key"));
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.getString(i2));
            }
            o = arrayList;
        } catch (JSONException e2) {
            LogUtil.b("Track_MapTrackingUtils", "setHmsForbiddenCountry:", LogAnonymous.b((Throwable) e2));
        }
    }

    public static int aUT_(AudioManager audioManager) {
        if (audioManager == null) {
            return 0;
        }
        return audioManager.requestAudioFocus(null, 3, 3);
    }

    public static int aUN_(AudioManager audioManager) {
        if (audioManager == null) {
            return 0;
        }
        return audioManager.abandonAudioFocus(null);
    }

    public static boolean e(int i2) {
        return i2 == 283 || SportSupportUtil.e(i2) || i2 == 700001;
    }

    public static boolean l() {
        int i2 = h;
        return (i2 == 0 && d == 2) || i2 == 2;
    }

    public static String c(int i2) {
        if (i2 != 0) {
            switch (i2) {
                case 257:
                    return "SPORT_TYPE_WALK";
                case 258:
                    return "SPORT_TYPE_RUN";
                case 259:
                    return "SPORT_TYPE_BIKE";
                case 260:
                    return "SPORT_TYPE_CLIMB_HILL";
                case 261:
                    return "SPORT_TYPE_CLIMB_STAIRS";
                case 262:
                    return "SPORT_TYPE_SWIM";
                case 263:
                    return "SPORT_TYPE_GOLF";
            }
        }
        return "SPORT_TYPE_DEFAULT";
    }
}
