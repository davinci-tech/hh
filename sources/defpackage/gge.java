package defpackage;

import android.text.TextUtils;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class gge {
    public static void e(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(arx.b(), str, hashMap, 0);
        ixx.d().c(arx.b());
    }

    public static void e(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("from", str2);
        e(str, hashMap);
    }

    public static void b(String str, int i, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("content", str2);
        e(str, hashMap);
    }

    public static void b(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("entrance", str);
        hashMap.put("position", Integer.valueOf(i));
        e("1130015", hashMap);
    }

    public static void c(String str, String str2) {
        iyb iybVar = (iyb) moj.e(str2, iyb.class);
        if (iybVar == null) {
            LogUtil.h("Suggestion_BiUtil", "info is null");
        } else {
            ixx.d().a(BaseApplication.getContext(), str, iybVar, 0);
        }
    }

    public static void e(String str, Map map) {
        String str2 = map.get(x2.AB_INFO) instanceof String ? (String) map.get(x2.AB_INFO) : "";
        if (TextUtils.isEmpty(str2)) {
            ixx.d().d(arx.b(), str, map, 0);
        } else {
            ixx.d().c(arx.b(), str, map, str2, 0);
        }
        ixx.d().c(arx.b());
    }

    public static void d(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put("planName", str);
        }
        e("1120013", hashMap);
    }

    public static void d(String str, int i, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("plan_name", str);
            jSONObject.put("plan_type", i);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put(HwExerciseConstants.JSON_NAME_PLAN_ID, str3);
            }
            if (c()) {
                jSONObject.put("end_time", System.currentTimeMillis());
                if (str2 != null) {
                    jSONObject.put("finishRate", a(str2));
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONObject.toString());
            e("1120004", hashMap);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_BiUtil", "JSONException = ", e.getMessage());
        }
    }

    public static void e(float f, String str, int i, int i2) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("finishRate", Float.valueOf(f));
        hashMap.put("plan_name", str);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put(ParamConstants.Param.END_MODE, Integer.valueOf(i2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120044.value(), hashMap, 0);
    }

    public static void b(int i, int i2, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (c()) {
                jSONObject.put("create_time", System.currentTimeMillis());
                jSONObject.put("movementTimes", i2);
            }
            jSONObject.put("difficulty", i);
            jSONObject.put("excludedDate", gic.i(str));
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONObject.toString());
            e("1130002", hashMap);
        } catch (JSONException unused) {
            LogUtil.b("Suggestion_BiUtil", "JSONException");
        }
    }

    public static void a(int i, int i2, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (c()) {
                jSONObject.put("create_time", System.currentTimeMillis());
                jSONObject.put("movementTimes", i2);
            }
            jSONObject.put("difficulty", i);
            jSONObject.put("excludedDate", gic.i(str));
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONObject.toString());
            e("1130003", hashMap);
        } catch (JSONException unused) {
            LogUtil.b("Suggestion_BiUtil", "JSONException");
        }
    }

    private static String a(String str) {
        try {
            return c(Float.parseFloat(str));
        } catch (NumberFormatException unused) {
            LogUtil.b("Suggestion_BiUtil", "NumberFormatException");
            return "0";
        }
    }

    public static boolean c() {
        return !Utils.o();
    }

    public static int b(String str) {
        return "RUNNING_COURSE".equals(str) ? AwarenessConstants.ERROR_NO_PERMISSION_CODE : "FITNESS_COURSE".equals(str) ? AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE : AwarenessConstants.REGISTER_SUCCESS_CODE;
    }

    public static int c(String str) {
        if ("RUNNING_COURSE".equals(str)) {
            return 2;
        }
        return "FITNESS_COURSE".equals(str) ? 1 : 3;
    }

    public static void d(int i, String str, int i2, int i3) {
        final HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("planStatus", Integer.valueOf(i));
        hashMap.put("planName", str);
        hashMap.put("finishRate", Integer.valueOf(i3));
        hashMap.put("event", Integer.valueOf(i2));
        ThreadPoolManager.d().execute(new Runnable() { // from class: gga
            @Override // java.lang.Runnable
            public final void run() {
                gge.e("2120150", hashMap);
            }
        });
    }

    public static void e(int i, final Map<String, Object> map) {
        map.put("click", 1);
        map.put("event", Integer.valueOf(i));
        ThreadPoolManager.d().execute(new Runnable() { // from class: ggj
            @Override // java.lang.Runnable
            public final void run() {
                gge.e("2120151", map);
            }
        });
    }

    public static void b(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ggh
            @Override // java.lang.Runnable
            public final void run() {
                gge.d(i);
            }
        });
    }

    static /* synthetic */ void d(int i) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        hashMap.put("enter", Integer.valueOf(i));
        e(AnalyticsValue.INT_PLAN_2040150.value(), hashMap);
    }

    public static void b(final int i, final int i2, final List<Integer> list, final boolean z) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ggd
            @Override // java.lang.Runnable
            public final void run() {
                gge.e(i, i2, list, z);
            }
        });
    }

    static /* synthetic */ void e(int i, int i2, List list, boolean z) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("goalNum", Integer.valueOf(i));
        hashMap.put("finishNum", Integer.valueOf(i2));
        hashMap.put(BleConstants.SPORT_TYPE, list);
        hashMap.put("hasAICourse", String.valueOf(z));
        e(AnalyticsValue.INT_PLAN_1120038.value(), hashMap);
    }

    public static void a(final int i, final boolean z, final Map<String, Object> map) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ggi
            @Override // java.lang.Runnable
            public final void run() {
                gge.c(map, i, z);
            }
        });
    }

    static /* synthetic */ void c(Map map, int i, boolean z) {
        HashMap hashMap;
        if (map == null || map.size() == 0) {
            hashMap = new HashMap(3);
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        if (z) {
            hashMap.put("ControlMode", PutDataRequest.WEAR_URI_SCHEME);
        } else {
            hashMap.put("ControlMode", "app");
        }
        e("1130067", hashMap);
    }

    public static int e() {
        return SharedPreferenceManager.a("Suggestion_BiUtil", AnalyticsValue.INT_PLAN_1120039.value(), 0);
    }

    public static void e(int i) {
        SharedPreferenceManager.b("Suggestion_BiUtil", AnalyticsValue.INT_PLAN_1120039.value(), i);
    }

    public static void a(IntPlan intPlan, boolean z) {
        LogUtil.a("Suggestion_BiUtil", "doPlanScheduleBi:", Integer.valueOf(e()), " user visible:", Boolean.valueOf(z));
        if (intPlan == null || intPlan.getMetaInfo() == null || e() == 0 || !z) {
            return;
        }
        HashMap hashMap = new HashMap(6);
        hashMap.put("meetGoal", 1);
        hashMap.put("type", Integer.valueOf(intPlan.getPlanType().getType()));
        hashMap.put("plan_name", intPlan.getMetaInfo().getName());
        if (intPlan.getStat("progress") != null) {
            hashMap.put("finishRate", intPlan.getStat("progress").getValue());
        }
        hashMap.put("dayProgress", Integer.valueOf((int) (((gib.b(System.currentTimeMillis()) / 86400000) - ((intPlan.getPlanTimeInfo().getBeginDate() * 1000) / 86400000)) + 1)));
        hashMap.put("completeMode", Integer.valueOf(e()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120039.value(), hashMap, 0);
        e(0);
    }

    public static String c(float f) {
        if (f < 0.1f) {
            return "0";
        }
        return f < 10.0f ? "5" : f < 20.0f ? "15" : f < 30.0f ? "25" : f < 40.0f ? HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY : f < 50.0f ? "45" : f < 60.0f ? "55" : f < 70.0f ? "65" : f < 80.0f ? "75" : f < 90.0f ? "85" : "95";
    }
}
