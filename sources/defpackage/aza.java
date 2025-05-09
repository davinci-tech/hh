package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class aza {
    private static void a(AnalyticsValue analyticsValue, Map<String, Object> map) {
        if (analyticsValue == null || map == null) {
            LogUtil.h("R_HealthLife_BasicHealthModelBiUtils", "setEvent analyticsValue ", analyticsValue, " map ", map);
        } else {
            map.put("click", 1);
            ixx.d().d(BaseApplication.e(), analyticsValue.value(), map, 0);
        }
    }

    public static void d(AnalyticsValue analyticsValue, int i, int i2) {
        if (analyticsValue == null) {
            LogUtil.h("R_HealthLife_BasicHealthModelBiUtils", "setEvent analyticsValue is null from ", Integer.valueOf(i), " type ", Integer.valueOf(i2));
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("type", Integer.valueOf(i2));
        a(analyticsValue, hashMap);
    }

    public static void a(AnalyticsValue analyticsValue, int i) {
        if (analyticsValue == null) {
            LogUtil.h("R_HealthLife_BasicHealthModelBiUtils", "setEventForFrom analyticsValue is null from ", Integer.valueOf(i));
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("from", Integer.valueOf(i));
        a(analyticsValue, hashMap);
    }

    public static void d(AnalyticsValue analyticsValue, int i) {
        if (analyticsValue == null) {
            LogUtil.h("R_HealthLife_BasicHealthModelBiUtils", "setEventForType analyticsValue is null type ", Integer.valueOf(i));
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", Integer.valueOf(i));
        a(analyticsValue, hashMap);
    }

    public static void e(int i, int i2) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("reportType", Integer.valueOf(i2));
        a(AnalyticsValue.BP_Management_Program_Weekly_Report_2040167, hashMap);
    }

    public static void d(int i, int i2) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        int h = CommonUtil.h(bao.e("health_model_challenge_id"));
        hashMap.put("planType", Integer.valueOf(h));
        hashMap.put("completeType", Integer.valueOf(i2));
        hashMap.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(i));
        Context e = BaseApplication.e();
        if (h == 200006 || h == 200007 || h == 200008) {
            hashMap.put("from", 2);
            hashMap.put("type", Integer.valueOf(i));
            ixx.d().d(e, AnalyticsValue.BLOOD_PRESSURE_JUMP_TO_HEALTH_LIFE_2040208.value(), hashMap, 0);
        }
        ixx.d().d(e, AnalyticsValue.Health_Management_Plan_Tasks_2040166.value(), hashMap, 0);
    }

    public static void a(String str, String str2, int i) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("from", str);
        hashMap.put("status", Integer.valueOf(i));
        hashMap.put("uuid", str2);
        hashMap.put("time", Long.valueOf(System.currentTimeMillis()));
        a(AnalyticsValue.HEALTH_MODEL_PLUGIN_RESOURCES_2119087, hashMap);
    }

    public static void c(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("reminderType", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_MODEL_NOTIFICATION_MESSAGE_CLICK_2119029, hashMap);
    }

    public static void d(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("taskId", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_MODEL_TASK_RULES_CLICK_2119005, hashMap);
    }

    public static void b(int i, String str) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("taskId", Integer.valueOf(i));
        hashMap.put("button", str);
        a(AnalyticsValue.HEALTH_MODEL_MISSION_POPUP_CLICK_2119006, hashMap);
    }

    public static void e(int i, boolean z) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(i));
        hashMap.put("isChecked", Boolean.valueOf(z));
        hashMap.put("planType", Integer.valueOf(azi.m()));
        a(AnalyticsValue.HEALTH_MODEL_TASK_CHECKBOX_CLICK_2119033, hashMap);
    }

    public static void b() {
        HashMap hashMap = new HashMap(5);
        hashMap.put("from", 5);
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 1);
        hashMap.put(FunctionSetBeanReader.BI_HAS_DATA, 0);
        hashMap.put("pageDate", DateFormatUtil.a(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
        a(AnalyticsValue.INPUT_SLEEP_ENTER_2030105, hashMap);
    }

    public static void d(Calendar calendar, Calendar calendar2) {
        HashMap hashMap = new HashMap(11);
        hashMap.put("from", 5);
        hashMap.put("type", 1);
        hashMap.put("event", 1);
        hashMap.put("sleepTime", "");
        hashMap.put("after_bedTime", "");
        hashMap.put("before_bedTime", "");
        hashMap.put("recordDate", DateFormatUtil.a(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
        if (calendar != null && calendar2 != null) {
            hashMap.put("bedTime", Long.valueOf((calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / 60000));
            hashMap.put("time_out_bed", DateFormatUtil.a(calendar.getTime().getTime(), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
            hashMap.put("time_to_bed", DateFormatUtil.a(calendar2.getTime().getTime(), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
        }
        a(AnalyticsValue.INPUT_SLEEP_COMPLETE_2030106, hashMap);
    }

    public static void c(String str, String str2, String str3, List<String> list) {
        HashMap hashMap = new HashMap(6);
        hashMap.put("PhysicianSource", str);
        hashMap.put("lastChallengeDay", str2);
        hashMap.put("lastChallengeId", str3);
        hashMap.put(ParsedFieldTag.TASK_TYPE, list);
        hashMap.put("challengeId", Integer.valueOf(azi.m()));
        a(AnalyticsValue.HEALTH_MODEL_CHALLENGE_JOIN_2119078, hashMap);
    }
}
