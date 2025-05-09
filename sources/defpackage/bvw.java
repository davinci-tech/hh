package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.featureuserprofile.todo.datatype.TodoSwitchSync;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bvw {
    public static int d(boolean z) {
        return z ? 1 : 2;
    }

    public static List<String> c() {
        return Arrays.asList("t000", "t100", "t102", "t103", "t105", "t106", "t107", "t108", "t109", "t110", "t111", "t112", "t113", "t114", "t201", "t401", "t301");
    }

    public static TodoSwitchSync a(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogUtil.d("td_switchs", "getTodoSwitchs can't running in MainLooper");
        }
        List<HiUserPreference> userPreferenceList = HiHealthNativeApi.a(context).getUserPreferenceList(Collections.singletonList("td_switchs"));
        TodoSwitchSync todoSwitchSync = null;
        if (koq.b(userPreferenceList)) {
            SharedPreferenceManager.e(context, String.valueOf(10006), "td_switchs", "", (StorageParams) null);
            return null;
        }
        boolean z = false;
        HiUserPreference hiUserPreference = userPreferenceList.get(0);
        if (hiUserPreference == null || TextUtils.isEmpty(hiUserPreference.getValue())) {
            SharedPreferenceManager.e(context, String.valueOf(10006), "td_switchs", "", (StorageParams) null);
            return null;
        }
        try {
            todoSwitchSync = (TodoSwitchSync) new Gson().fromJson(hiUserPreference.getValue(), TodoSwitchSync.class);
        } catch (JsonIOException | JsonSyntaxException unused) {
            LogUtil.d("td_switchs", "JsonSyntaxException or JsonIOException");
        }
        if (todoSwitchSync != null && d(context, todoSwitchSync)) {
            int indexOf = todoSwitchSync.getKeys().indexOf("t000");
            if (indexOf >= 0 && d("t000", todoSwitchSync.getValues().get(indexOf))) {
                z = true;
            }
            c(context, z);
        }
        return todoSwitchSync;
    }

    public static boolean d(Context context, Map<String, Integer> map) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogUtil.d("td_switchs", "uploadTodoSwitchs can't running in MainLooper");
        }
        if (map != null && !map.isEmpty()) {
            TodoSwitchSync d = d(map);
            try {
                String json = new Gson().toJson(d);
                LogUtil.d("td_switchs", json);
                boolean userPreference = HiHealthNativeApi.a(context).setUserPreference(new HiUserPreference("td_switchs", json));
                if (userPreference) {
                    if (d(context, d)) {
                        int indexOf = d.getKeys().indexOf("t000");
                        c(context, indexOf >= 0 && d("t000", d.getValues().get(indexOf)));
                    }
                    HiSyncOption hiSyncOption = new HiSyncOption();
                    hiSyncOption.setSyncModel(2);
                    hiSyncOption.setSyncAction(0);
                    hiSyncOption.setSyncDataType(10026);
                    hiSyncOption.setSyncMethod(2);
                    HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
                }
                return userPreference;
            } catch (JsonIOException unused) {
                LogUtil.d("td_switchs", "uploadTodoSwitchs JsonIOException");
            }
        }
        return false;
    }

    public static TodoSwitchSync d(Context context) {
        String b = SharedPreferenceManager.b(context, String.valueOf(10006), "td_switchs");
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            return (TodoSwitchSync) new Gson().fromJson(b, TodoSwitchSync.class);
        } catch (JsonIOException | JsonSyntaxException unused) {
            LogUtil.d("td_switchs", "getLocalSp JsonIOException or JsonSyntaxException");
            return null;
        }
    }

    public static boolean d(Context context, TodoSwitchSync todoSwitchSync) {
        String str;
        if (todoSwitchSync == null || koq.b(todoSwitchSync.getKeys()) || koq.b(todoSwitchSync.getValues())) {
            return false;
        }
        try {
            str = new Gson().toJson(todoSwitchSync);
        } catch (JsonIOException unused) {
            LogUtil.d("td_switchs", "saveLocalSpSwitchs JsonIOException");
            str = null;
        }
        return !TextUtils.isEmpty(str) && SharedPreferenceManager.e(context, String.valueOf(10006), "td_switchs", str, (StorageParams) null) == 0;
    }

    public static Map<String, Integer> a(TodoSwitchSync todoSwitchSync, Map<String, Integer> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (todoSwitchSync != null && !koq.b(todoSwitchSync.getKeys()) && !koq.b(todoSwitchSync.getValues())) {
            List<String> keys = todoSwitchSync.getKeys();
            List<Integer> values = todoSwitchSync.getValues();
            int size = keys.size();
            for (int i = 0; i < size; i++) {
                map.put(keys.get(i), Integer.valueOf(koq.b(values, i) ? 0 : values.get(i).intValue()));
            }
        }
        return map;
    }

    public static TodoSwitchSync d(Map<String, Integer> map) {
        List<String> c = c();
        ArrayList arrayList = new ArrayList(c.size());
        for (String str : c) {
            arrayList.add(Integer.valueOf(a(str, map.get(str))));
        }
        TodoSwitchSync todoSwitchSync = new TodoSwitchSync();
        todoSwitchSync.setKeys(c);
        todoSwitchSync.setValues(arrayList);
        return todoSwitchSync;
    }

    public static boolean b(Context context) {
        String b = SharedPreferenceManager.b(context, String.valueOf(10006), "t000");
        return TextUtils.isEmpty(b) || "true".equalsIgnoreCase(b);
    }

    public static void c(Context context, boolean z) {
        SharedPreferenceManager.e(context, String.valueOf(10006), "t000", String.valueOf(z), (StorageParams) null);
    }

    public static boolean d(String str, Integer num) {
        return num == null || num.intValue() != 2;
    }

    public static boolean d(Integer num) {
        return num == null || num.intValue() != 2;
    }

    private static int a(String str, Integer num) {
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static boolean e(Context context) {
        if (!CommonUtil.bu() && Utils.i()) {
            LoginInit loginInit = LoginInit.getInstance(context);
            if (loginInit.getIsLogined() && !loginInit.isKidAccount()) {
                return true;
            }
        }
        return false;
    }

    public static void c(Context context, AnalyticsValue analyticsValue, String str, int i, int i2) {
        HashMap hashMap = new HashMap(3);
        int i3 = AnonymousClass4.d[analyticsValue.ordinal()];
        if (i3 == 1) {
            hashMap.put(ParsedFieldTag.TASK_NAME, str);
            hashMap.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(i2));
        } else if (i3 == 2) {
            hashMap.put("taskNumber", Integer.valueOf(i));
        } else if (i3 == 3) {
            hashMap.put("from", Integer.valueOf(i2));
        } else if (i3 == 4) {
            hashMap.put("taskNumber", Integer.valueOf(i));
        } else {
            if (i3 != 5) {
                return;
            }
            hashMap.put(ParsedFieldTag.TASK_NAME, str);
            hashMap.put("taskNumber", Integer.valueOf(i));
        }
        hashMap.put("click", 1);
        ixx.d().d(context, analyticsValue.value(), hashMap, 0);
    }

    /* renamed from: bvw$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[AnalyticsValue.values().length];
            d = iArr;
            try {
                iArr[AnalyticsValue.HEALTH_LIFE_TODO_TASK_CLICK_2040172.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[AnalyticsValue.HEALTH_LIFE_TODO_TASK_CLICK_2040173.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[AnalyticsValue.HEALTH_LIFE_TODO_SETTINGS_ENTER_2040174.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[AnalyticsValue.HEALTH_LIFE_TODO_ICON_EXPOSE_2040177.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[AnalyticsValue.HEALTH_LIFE_TODO_LIST_EXPOSE_2040178.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        int i = z ? 2 : 1;
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("switchName", str);
        hashMap.put("switchType", str2);
        hashMap.put("switchStatus", Integer.valueOf(i));
        ixx.d().d(context, AnalyticsValue.HEALTH_LIFE_TODO_SETTINGS_SWITCH_2040175.value(), hashMap, 0);
    }

    public static void a(Context context, String str, int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("changeNumber", Integer.valueOf(i));
        hashMap.put("switchType", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_LIFE_TODO_SETTINGS_EXIT_2040176.value(), hashMap, 0);
    }

    public static void b(List<gka> list, TodoSwitchSync todoSwitchSync, boolean z) {
        if (koq.b(list)) {
            return;
        }
        Map<String, Integer> a2 = a(todoSwitchSync, (Map<String, Integer>) null);
        Iterator<gka> it = list.iterator();
        while (it.hasNext()) {
            if (!e(it.next(), a2, z)) {
                it.remove();
            }
        }
    }

    public static boolean e(gka gkaVar, Map<String, Integer> map, boolean z) {
        if (gkaVar == null) {
            return false;
        }
        if (z && gkaVar.i() == 1) {
            LogUtil.d("TodoSettingUtils", "isTodoTaskNeedShow task is finished, title = ", gkaVar.n());
            return false;
        }
        int k = gkaVar.k();
        if ((k == 1536 || k == 512) && d("t201", map.get("t201"))) {
            return true;
        }
        if (k == 1792 && d("t301", map.get("t301"))) {
            return true;
        }
        if (k == 768 && d("t401", map.get("t401"))) {
            return true;
        }
        HealthLifeTaskBean healthLifeTaskBean = gkaVar.o() instanceof HealthLifeTaskBean ? (HealthLifeTaskBean) gkaVar.o() : null;
        if (k != 256 || !d("t100", map.get("t100")) || healthLifeTaskBean == null) {
            return false;
        }
        String a2 = a(healthLifeTaskBean.getId());
        return d(a2, map.get(a2));
    }

    public static String d(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            return jSONObject.has(str) ? jSONObject.getString(str) : "";
        } catch (JSONException unused) {
            LogUtil.e("TodoSettingUtils", "parseJsonKey Exception");
            return "";
        }
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("TodoSettingUtils", "startDeepLink url is null");
        } else {
            LogUtil.d("TodoSettingUtils", "startDeepLink with deepLink");
            AppRouter.zi_(Uri.parse(str)).c(BaseApplication.e());
        }
    }

    public static void a(gka gkaVar) {
        int[] iArr;
        int m = CommonUtil.m(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), d("planType", gkaVar.m()));
        if (m == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            iArr = new int[]{5, 1, 1};
        } else {
            if (m == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
                gge.e(AnalyticsValue.HEALTH_HOME_CARE_CARD_DATA_2010088.value());
            } else if (m == IntPlan.PlanType.FIT_PLAN.getType()) {
                gge.e(AnalyticsValue.HEALTH_HOME_CARE_CARD_DATA_2010087.value());
            } else {
                LogUtil.c("TodoSettingUtils", "jumpToShowPlan, error planType == ", Integer.valueOf(m));
            }
            iArr = null;
        }
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi == null) {
            LogUtil.c("TodoSettingUtils", "jumpToShowPlan, fitnessAdviceApi is null");
            return;
        }
        if (gkaVar.o() instanceof IntPlan) {
            if (iArr != null) {
                ase.d((IntPlan) gkaVar.o(), "1", iArr);
            } else {
                ase.d((IntPlan) gkaVar.o(), "1", new int[0]);
            }
        }
        fitnessAdviceApi.launchPlanTab();
    }

    public static void c(String str, ceb cebVar) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("activityName", cebVar.b());
        hashMap.put("activityId", cebVar.c());
        ixx.d().d(BaseApplication.e(), str, hashMap, 0);
    }

    public static String d(String str) {
        Map<String, Integer> a2 = a(a(BaseApplication.e()), (Map<String, Integer>) null);
        if (!a2.containsKey(str)) {
            LogUtil.d("TodoSettingUtils", "getTodoSwitchStatus switchType error");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("t000", d(a2.get("t000")));
            if (!"t000".equals(str)) {
                jSONObject.put(str, d(a2.get(str)));
            }
        } catch (JSONException e) {
            LogUtil.e("TodoSettingUtils", ExceptionUtils.d(e));
        }
        LogUtil.d("TodoSettingUtils", "getTodoSwitchStatus switch status = ", jSONObject.toString());
        return jSONObject.toString();
    }

    public static boolean b(String str, boolean z, int i) {
        Map<String, Integer> a2 = a(a(BaseApplication.e()), (Map<String, Integer>) null);
        if (!a2.containsKey(str)) {
            LogUtil.c("TodoSettingUtils", "changeTodoSubSwitch switchType error");
            return false;
        }
        boolean d = d(a2.get("t000"));
        boolean d2 = d(a2.get(str));
        LogUtil.d("TodoSettingUtils", "openTodoSubSwitch switchType=", str, " isSubSwitchOn=", Boolean.valueOf(d2), " isMainSwitchOn=", Boolean.valueOf(d));
        if (z != d2) {
            a2.put(str, Integer.valueOf(z ? 1 : 2));
        }
        if (!"t000".equals(str) && !d && z) {
            a2.put("t000", 1);
        }
        boolean d3 = d(BaseApplication.e(), a2);
        if (d3 && z) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            hashMap.put("from", Integer.valueOf(i));
            ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_PAGE_TODO_2040226.value(), hashMap, 0);
        }
        return d3;
    }

    public static String a(int i) {
        switch (i) {
            case 2:
                return "t102";
            case 3:
                return "t103";
            case 4:
            default:
                return "";
            case 5:
                return "t105";
            case 6:
                return "t106";
            case 7:
                return "t107";
            case 8:
                return "t108";
            case 9:
                return "t109";
            case 10:
                return "t110";
            case 11:
                return "t111";
            case 12:
                return "t112";
            case 13:
                return "t113";
            case 14:
                return "t114";
        }
    }
}
