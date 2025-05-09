package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.ixw;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ixx {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f13654a = {AnalyticsValue.HEALTH_HOME_STEP_DETAIL_2010002.value(), AnalyticsValue.BI_TRACK_ENTER_SPORT_SECOND_PAGE_1040044.value(), AnalyticsValue.MOTION_TRACK_1040023.value(), AnalyticsValue.MOTION_TRACK_1040027.value(), AnalyticsValue.BI_TRACK_SPORT_ADD_SPORT_KEY.value(), AnalyticsValue.HEALTH_MINE_SETTINGS_OFFINE_MAP_2040016.value(), AnalyticsValue.HEALTH_MINE_SETTINGS_HELP_2040049.value(), AnalyticsValue.BI_TRACK_SHOW_STAT_1040035.value(), AnalyticsValue.BI_TRACK_HISTORY_CHANGE_SPORT_TYPE_1040034.value(), AnalyticsValue.MOTION_TRACK_1040014.value(), AnalyticsValue.EVENT_ENTER_FITNESS_HISTORY.value(), AnalyticsValue.EVENT_CLICK_FITNESS_COURSE_MORE.value(), AnalyticsValue.HEALTH_HOME_CAL_DETAIL_2010006.value(), AnalyticsValue.HEALTH_HOME_STEP_DETAIL_2010002.value(), AnalyticsValue.HEALTH_HOME_STEP_CLIMB_2010047.value(), AnalyticsValue.EVENT_START_FITNESS_WORKOUT.value(), AnalyticsValue.HEALTH_SHOP_FEATURE_TAB_CLICK_2120017.value(), AnalyticsValue.HEALTH_DISCOVER_SCROLL_ACT_2020021.value(), AnalyticsValue.HEALTH_WONDERFUL_EVENT_SCROLL_2020025.value(), AnalyticsValue.HEALTH_MINE_MY_MEDAL_2040012.value(), AnalyticsValue.SUCCESSES_SHARE_1100011.value(), AnalyticsValue.SHARE_1140001.value(), AnalyticsValue.OPEN_SERVICE_MORE_20100060.value(), AnalyticsValue.HEALTH_HOME_SMART_CARD_MSG_CLICK_2010051.value(), AnalyticsValue.HEALTH_MINE_NOTICE_MSG_2040003.value(), AnalyticsValue.HEALTH_HOME_SHOW__CHIEVEMENT_CARD_KAKA_2010080.value(), AnalyticsValue.HEALTH_MINE_MY_KAKA_2040011.value(), AnalyticsValue.Group_1070010.value(), AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075.value(), AnalyticsValue.HEALTH_MINE_MY_INFO_2040007.value(), AnalyticsValue.HEALTH_MINE_MY_REPORT_2040010.value(), AnalyticsValue.ACHIEVE_REPORT_1100030.value(), AnalyticsValue.SUCCESSES_REPORT_1100009.value(), AnalyticsValue.HEALTH_HOME_SHOW__CHIEVEMENT_CARD_CUMULATE_DATA_2010079.value(), AnalyticsValue.HEALTH_MINE_MY_RANK_2040005.value(), AnalyticsValue.HEALTH_HOME_SLEEP_DETAIL_2010011.value(), AnalyticsValue.SLEEP_DAY_1150002.value(), AnalyticsValue.HEALTH_HOME_SLEEP_DETAIL_TAB_2010012.value(), AnalyticsValue.HEALTH_PRESSUER_CARD_CLICK_2160001.value(), AnalyticsValue.HEALTH_PRESSUER_MEASUREMENT_CLICK_2160009.value(), AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_CLICK_2160017.value(), AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_RETRY_2160023.value(), AnalyticsValue.HEALTH_PRESSUER_NODATA_BREATHEPARENT_CLICK_2160011.value(), AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_DETAIL_2010027.value(), AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_RECODE_IMPUT_2010028.value(), AnalyticsValue.HEALTH_HOME_BLOOD_PRESSURE_DETAIL_2010025.value(), AnalyticsValue.HEALTH_HOME_BLOOD_PRESSURE_RECODE_IMPUT_2010026.value(), AnalyticsValue.HEALTH_HOME_WIGHT_DETAIL_2010023.value(), AnalyticsValue.HEALTH_HOME_WIGHT_RECODE_IMPUT_2010024.value(), AnalyticsValue.HEALTH_HEALTH_MY_DEVICE_2030030.value(), AnalyticsValue.HEALTH_HEALTH_HEALTHSERVICE_HEALTHDATA_2030045.value(), AnalyticsValue.HEALTH_MINE_SETTINGS_2040013.value(), AnalyticsValue.HEALTH_MINE_TARGET_2040004.value(), AnalyticsValue.HEALTH_ACCESS_CARD_2060036.value(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), AnalyticsValue.HEALTH_FAMILY_ZONE_2040078.value(), AnalyticsValue.HEALTH_HOME_NPS_COMMIT_SUCCESS_2010096.value(), AnalyticsValue.NPS_COMMIT_QUESTION_1090039.value(), AnalyticsValue.INVOICE_APPLY_2041099.value(), AnalyticsValue.INVOICE_SUCCESSFUL_2041100.value()};
    private static final String[] b = {AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), AnalyticsValue.HEALTH_LOGIN_APP_2050009.value(), AnalyticsValue.HEALTH_GYM_EQUIP_CONNECT_RESULT_WHITE_2170017.value(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_WHITE_2060087.value(), AnalyticsValue.FA_ENTER_APP.value(), AnalyticsValue.SHARE_1140001.value(), AnalyticsValue.MARKETING_RESOURCE.value(), AnalyticsValue.TRADE_OFFERING_DETAIL.value(), AnalyticsValue.TRADE_BUY.value(), AnalyticsValue.TRADE_PAY_SUCCESS.value(), AnalyticsValue.HEALTH_MINE_SETTINGS_IMPROVE_PLAN_2040021.value()};
    private static String c = String.valueOf(System.currentTimeMillis());
    private static ixx d;

    public static ixx d() {
        if (d == null) {
            iyj.b("BIAnalyticsUtil", "getInstance()");
            d = new ixx();
        }
        return d;
    }

    public void a(String str) {
        iyj.b("BIAnalyticsUtil", "setUserInfo()");
        ixw.e(str);
    }

    public void b(ixz... ixzVarArr) {
        iyj.b("BIAnalyticsUtil", "setBatchDeviceInfo()");
        ixw.a(ixzVarArr);
    }

    public void a(ixz... ixzVarArr) {
        iyj.b("BIAnalyticsUtil", "removeDeviceInfo()");
        ixw.e(ixzVarArr);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ixw.d(str);
    }

    public void a(String str, String str2) {
        ixw.a(str, str2);
    }

    public void c(final Context context, final String str, final Map<String, Object> map, final String str2, final int i) {
        ThreadPoolManager.d().c("BIAnalyticsUtil", new Runnable() { // from class: iyd
            @Override // java.lang.Runnable
            public final void run() {
                ixx.this.b(i, str, map, str2, context);
            }
        });
    }

    /* synthetic */ void b(int i, String str, Map map, String str2, Context context) {
        int b2;
        if (!Utils.i()) {
            iyj.a("BIAnalyticsUtil", "setEvent() SUPPORT_ANALYTICS = true");
            return;
        }
        if (e(i)) {
            return;
        }
        if (b(str)) {
            if (!Utils.o() && b(str, f13654a)) {
                OpAnalyticsUtil.getInstance().setEvent2nd(str, d((Map<String, Object>) map));
            }
            if (TextUtils.equals(str2, "noAbTest")) {
                b2 = ixw.a(context, str, map);
            } else {
                b2 = ixw.b(context, str, map, str2);
            }
            b(context, str);
            iyj.b("BIAnalyticsUtil", "setEventResult = ", Integer.valueOf(b2));
            return;
        }
        iyj.a("BIAnalyticsUtil", "setEvent() AnalyticsStatus is false");
    }

    public void d(final Context context, final String str, final iyb iybVar, final String str2, final int i) {
        ThreadPoolManager.d().c("BIAnalyticsUtil", new Runnable() { // from class: iyc
            @Override // java.lang.Runnable
            public final void run() {
                ixx.this.b(i, iybVar, str, str2, context);
            }
        });
    }

    /* synthetic */ void b(int i, iyb iybVar, String str, String str2, Context context) {
        int d2;
        if (!Utils.i()) {
            iyj.a("BIAnalyticsUtil", "setEvent() SUPPORT_ANALYTICS = true");
            return;
        }
        if (e(i)) {
            return;
        }
        if (iybVar.a() != null) {
            iybVar.e(iybVar.a(), c);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("sessionId", c);
            iybVar.e(hashMap);
        }
        if (b(str)) {
            if (!Utils.o() && b(str, f13654a)) {
                OpAnalyticsUtil.getInstance().setEvent2nd(str, b(iybVar));
            }
            if (TextUtils.equals(str2, "noAbTest")) {
                d2 = ixw.e(context, str, iybVar);
            } else {
                d2 = ixw.d(context, str, iybVar, str2);
            }
            b(context, str);
            iyj.b("BIAnalyticsUtil", "setEventResult = ", Integer.valueOf(d2));
            return;
        }
        iyj.a("BIAnalyticsUtil", "setEvent() AnalyticsStatus is false");
    }

    public static void d(long j) {
        c = String.valueOf(j);
    }

    private boolean e(int i) {
        if (i == 0) {
            return false;
        }
        iyj.b("BIAnalyticsUtil", "setEvent() level =" + i + " BUILD_TYPE = release");
        return true;
    }

    public int d(Context context, String str, Map<String, Object> map, int i) {
        c(context, str, map, "noAbTest", i);
        return 0;
    }

    public int a(Context context, String str, iyb iybVar, int i) {
        d(context, str, iybVar, "noAbTest", i);
        return 0;
    }

    private boolean b(String str) {
        if (!b(str, b)) {
            return e() && AuthorizationUtils.a(null);
        }
        if (!AuthorizationUtils.a(null)) {
            return false;
        }
        LogUtil.a("BIAnalyticsUtil", "white key: ", str);
        return true;
    }

    public void c(Context context) {
        if (Utils.i()) {
            if (e()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: ixy
                    @Override // java.lang.Runnable
                    public final void run() {
                        ixw.a();
                    }
                });
            } else {
                iyj.a("BIAnalyticsUtil", "onReport() AnalyticsStatus is false");
            }
        }
    }

    private void b(Context context, String str) {
        if (Utils.i()) {
            if (b(str, b)) {
                LogUtil.a("BIAnalyticsUtil", "onReportWithWhiteList getAnalyticsStatusWithWhiteList true. Key:", str);
                ixw.a();
            } else {
                iyj.a("BIAnalyticsUtil", "onReportWithWhiteList() AnalyticsStatus is false");
            }
        }
    }

    public void e(final String str, final LinkedHashMap<String, String> linkedHashMap) {
        if (Utils.i()) {
            if (e()) {
                ThreadPoolManager.d().c("BIAnalyticsUtil", new Runnable() { // from class: iye
                    @Override // java.lang.Runnable
                    public final void run() {
                        ixw.c(str, linkedHashMap);
                    }
                });
            } else {
                iyj.a("BIAnalyticsUtil", "onReport() AnalyticsStatus is false");
            }
        }
    }

    public void d(final Context context) {
        if (Utils.i()) {
            if (e()) {
                ThreadPoolManager.d().c("BIAnalyticsUtil", new Runnable() { // from class: com.huawei.hwbimodel.impl.BIAnalyticsUtil$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        ixw.c(context);
                    }
                });
            } else {
                iyj.a("BIAnalyticsUtil", "onResume() AnalyticsStatus is false");
            }
        }
    }

    public void a(final String str, final LinkedHashMap<String, String> linkedHashMap) {
        if (Utils.i()) {
            if (e()) {
                ThreadPoolManager.d().c("BIAnalyticsUtil", new Runnable() { // from class: iyf
                    @Override // java.lang.Runnable
                    public final void run() {
                        ixw.b(str, (LinkedHashMap<String, String>) linkedHashMap);
                    }
                });
            } else {
                iyj.a("BIAnalyticsUtil", "onPause() AnalyticsStatus is false");
            }
        }
    }

    public void b(final Context context) {
        if (Utils.i()) {
            if (e()) {
                ThreadPoolManager.d().c("BIAnalyticsUtil", new Runnable() { // from class: com.huawei.hwbimodel.impl.BIAnalyticsUtil$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        ixw.b(context);
                    }
                });
            } else {
                iyj.a("BIAnalyticsUtil", "onPause() AnalyticsStatus is false");
            }
        }
    }

    public static boolean b() {
        return Utils.i() && e();
    }

    public void c(iyk iykVar) {
        LogUtil.a("BIAnalyticsUtil", "doBiFromOpenAppByPush type= ", Integer.valueOf(iykVar.d()), " name= ", iykVar.b(), " url= ", iykVar.f(), " pushId = ", iykVar.e(), " serviceId = ", iykVar.c());
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", 0);
        d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
        hashMap.put("type", Integer.valueOf(iykVar.d()));
        hashMap.put("name", iykVar.b());
        hashMap.put("url", iykVar.f());
        hashMap.put("pushID", iykVar.e());
        hashMap.put("serviceID", iykVar.c());
        hashMap.put("details", iykVar.a());
        d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PUSH_OPEN_APP_2050008.value(), hashMap, 0);
    }

    public LinkedHashMap<String, String> d(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return new LinkedHashMap<>();
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            linkedHashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> b(iyb iybVar) {
        if (iybVar == null) {
            return new LinkedHashMap<>();
        }
        HashMap hashMap = new HashMap();
        if (!CollectionUtils.e(iybVar.e())) {
            hashMap.putAll(iybVar.e());
        }
        if (!CollectionUtils.d(iybVar.d())) {
            hashMap.put("pageProperties", iybVar.d());
        }
        if (!CollectionUtils.e(iybVar.a())) {
            hashMap.putAll(iybVar.a());
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry entry : hashMap.entrySet()) {
            linkedHashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return linkedHashMap;
    }

    public boolean b(String str, String[] strArr) {
        if (strArr == null) {
            return false;
        }
        if (str == null) {
            for (String str2 : strArr) {
                if (str2 == null) {
                    return true;
                }
            }
        } else {
            for (String str3 : strArr) {
                if (str.equals(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean e() {
        return (knx.e() || Utils.f()) && AuthorizationUtils.a(null);
    }

    public JSONObject c() {
        return ixw.d();
    }
}
