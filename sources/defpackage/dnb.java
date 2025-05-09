package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.rules.activityinfo.ActivityInfo;
import com.huawei.health.featuremarketing.rules.activityinfo.ActivityInfoApi;
import com.huawei.health.featuremarketing.rules.activityinfo.ActivityInfoFactory;
import com.huawei.health.featuremarketing.rules.activityinfo.ActivityInfoReq;
import com.huawei.health.marketing.datatype.TriggerEventBase;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dnb {
    public static boolean b(int i, Map<String, ?> map, List<TriggerEventBase> list) {
        if (koq.b(list)) {
            LogUtil.c("MarketingEventsMgr", "no triggerEvent found in cloud resource, match in default");
            return true;
        }
        TriggerEventBase b = b(i, list, map);
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("isMatch: ");
        sb.append(b != null);
        sb.append(", eventType: ");
        sb.append(i);
        sb.append(", triggerEventParams: ");
        sb.append(map);
        sb.append(", triggerEventBases: ");
        sb.append(list);
        objArr[0] = sb.toString();
        LogUtil.c("MarketingEventsMgr", objArr);
        return b != null;
    }

    public static int e(int i, Map<String, ?> map, List<TriggerEventBase> list) {
        LogUtil.c("MarketingEventsMgr", "matching and delay... eventType:" + i + ", triggerEventParams: " + map + ", triggerEvent:" + list);
        if (koq.b(list)) {
            LogUtil.c("MarketingEventsMgr", "no triggerEvent found in cloud resource, match in default");
            return 0;
        }
        TriggerEventBase b = b(i, list, map);
        if (b == null) {
            LogUtil.c("MarketingEventsMgr", "isMatchAndDelay eventBase is null.");
            return -1;
        }
        return b.getDelay();
    }

    private static TriggerEventBase b(int i, List<TriggerEventBase> list, Map<String, ?> map) {
        if (i == 2) {
            return d(list, 2);
        }
        if (i == 34) {
            return c(list, map);
        }
        if (i == 45) {
            return d(list, 45);
        }
        if (i == 61) {
            return d(list, map);
        }
        if (i == 100) {
            return d(list, map, 6, 7, "intensity_total_time");
        }
        if (i == 200) {
            return e(list, map);
        }
        if (i == 300) {
            return d(list, map, 14, 15, "sleep_score");
        }
        if (i == 400) {
            return b(list, map);
        }
        switch (i) {
            case 49:
                return a(list, map);
            case 50:
                return d(list, 50);
            case 51:
                return c(list, 51);
            case 52:
                return a(list, 52);
            default:
                return e(list, i);
        }
    }

    private static TriggerEventBase e(List<TriggerEventBase> list, int i) {
        for (TriggerEventBase triggerEventBase : list) {
            if (triggerEventBase.getEventType() == i) {
                return triggerEventBase;
            }
        }
        return null;
    }

    private static TriggerEventBase e(List<TriggerEventBase> list, Map<String, ?> map) {
        float f;
        LogUtil.c("MarketingEventsMgr", "get Running Distance Event.");
        if (map == null) {
            LogUtil.c("MarketingEventsMgr", "keyValue is null.");
            return null;
        }
        try {
            f = Float.parseFloat(String.valueOf(map.get("running_distance_data")));
        } catch (NumberFormatException e) {
            LogUtil.e("MarketingEventsMgr", "set runningDistance exception", e.getMessage());
            f = 0.0f;
        }
        LogUtil.c("MarketingEventsMgr", "runningDistance: ", Float.valueOf(f));
        for (TriggerEventBase triggerEventBase : list) {
            int eventType = triggerEventBase.getEventType();
            if (eventType == 29 || eventType == 32) {
                LogUtil.c("MarketingEventsMgr", "RUNNING_FINISH_SHORT_TYPE. EventValue: ", triggerEventBase.getEventValue());
                if (f < Integer.parseInt(triggerEventBase.getEventValue())) {
                    return triggerEventBase;
                }
            } else if (eventType == 28 || eventType == 31) {
                try {
                    LogUtil.c("MarketingEventsMgr", "RUNNING_FINISH_LONG_TYPE. EventValue: ", triggerEventBase.getEventValue());
                    if (f > Integer.parseInt(triggerEventBase.getEventValue())) {
                        return triggerEventBase;
                    }
                } catch (NumberFormatException unused) {
                    LogUtil.e("MarketingEventsMgr", "runningDistance: ", Float.valueOf(f));
                }
            }
        }
        return null;
    }

    private static TriggerEventBase d(List<TriggerEventBase> list, Map<String, ?> map, int i, int i2, String str) {
        if (map == null) {
            LogUtil.c("MarketingEventsMgr", "keyValue is null.");
            return null;
        }
        try {
            int parseInt = Integer.parseInt(String.valueOf(map.get(str)));
            for (TriggerEventBase triggerEventBase : list) {
                int eventType = triggerEventBase.getEventType();
                if (eventType == i2) {
                    if (parseInt < Integer.parseInt(triggerEventBase.getEventValue())) {
                        return triggerEventBase;
                    }
                } else if (eventType == i && parseInt > Integer.parseInt(triggerEventBase.getEventValue())) {
                    return triggerEventBase;
                }
            }
        } catch (NumberFormatException e) {
            LogUtil.e("MarketingEventsMgr", "parseInt exception", e.getMessage());
        }
        return null;
    }

    private static TriggerEventBase b(List<TriggerEventBase> list, Map<String, ?> map) {
        if (map == null) {
            LogUtil.c("MarketingEventsMgr", "getFitnessCourseFinishedEvent, triggerEventParams is null, will not show");
            return null;
        }
        String str = (String) map.get("fitness_finish_training");
        for (TriggerEventBase triggerEventBase : list) {
            int eventType = triggerEventBase.getEventType();
            if (eventType == 33) {
                LogUtil.c("MarketingEventsMgr", "getFitnessCourseFinishedEvent 33, will show to workoutID: ", str);
                return triggerEventBase;
            }
            if (eventType == 34) {
                LogUtil.c("MarketingEventsMgr", "getFitnessCourseFinishedEvent, whiteListEventBase ");
                if (triggerEventBase.getEventValue() == null || str == null || !triggerEventBase.getEventValue().contains(str)) {
                    return null;
                }
                LogUtil.c("MarketingEventsMgr", "getFitnessCourseFinishedEvent 44, will show to workoutID: ", str);
                return triggerEventBase;
            }
            if (eventType == 61) {
                LogUtil.c("MarketingEventsMgr", "getFitnessCourseFinishedEvent, blackListEventBase ");
                String eventValue = triggerEventBase.getEventValue();
                if (eventValue != null && str != null && eventValue.contains(str)) {
                    LogUtil.c("MarketingEventsMgr", "hide to specific, matched! will hide to: " + str);
                    return null;
                }
            }
        }
        return new TriggerEventBase.Builder().build();
    }

    private static TriggerEventBase c(List<TriggerEventBase> list, Map<String, ?> map) {
        if (map == null) {
            LogUtil.c("MarketingEventsMgr", "show to specific, triggerEventParams is null, will not show");
            return null;
        }
        String str = (String) map.get("fitness_finish_training");
        for (TriggerEventBase triggerEventBase : list) {
            if (triggerEventBase.getEventType() == 34 && triggerEventBase.getEventValue() != null && str != null && triggerEventBase.getEventValue().contains(str)) {
                LogUtil.c("MarketingEventsMgr", "show to specific, will show to workoutID: ", str);
                return triggerEventBase;
            }
        }
        LogUtil.c("MarketingEventsMgr", "show to specific, will NOT show to workoutID: ", str);
        return null;
    }

    private static TriggerEventBase d(List<TriggerEventBase> list, Map<String, ?> map) {
        if (map == null) {
            LogUtil.c("MarketingEventsMgr", "hide to specific, triggerEventParams is null, will show...");
            return null;
        }
        String str = (String) map.get("fitness_finish_training");
        for (TriggerEventBase triggerEventBase : list) {
            if (triggerEventBase.getEventType() == 61) {
                String eventValue = triggerEventBase.getEventValue();
                if (eventValue != null && str != null && eventValue.contains(str)) {
                    LogUtil.c("MarketingEventsMgr", "hide to specific, matched! will hide to: " + str);
                    return null;
                }
                LogUtil.c("MarketingEventsMgr", "hide to specific, found but NOT matched! will not hide to: " + str);
                return new TriggerEventBase.Builder().build();
            }
        }
        LogUtil.c("MarketingEventsMgr", "hide to specific, no rule found, nothing to hide");
        return null;
    }

    private static TriggerEventBase d(List<TriggerEventBase> list, int i) {
        LogUtil.c("MarketingEventsMgr", "enter getDataFinishEvent");
        for (TriggerEventBase triggerEventBase : list) {
            if (triggerEventBase.getEventType() == i) {
                String eventValue = triggerEventBase.getEventValue();
                String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
                if (!TextUtils.isEmpty(eventValue) && accountInfo != null) {
                    return e(triggerEventBase);
                }
            }
        }
        return null;
    }

    private static TriggerEventBase e(TriggerEventBase triggerEventBase) {
        Map<String, String> headers;
        String eventValue = triggerEventBase.getEventValue();
        ActivityInfoReq activityInfoReq = new ActivityInfoReq();
        activityInfoReq.setActivityId(eventValue);
        ActivityInfoApi activityInfoApi = (ActivityInfoApi) lqi.d().b(ActivityInfoApi.class);
        ActivityInfoFactory activityInfoFactory = new ActivityInfoFactory(BaseApplication.e());
        try {
            headers = activityInfoFactory.getHeaders();
        } catch (IOException e) {
            LogUtil.e("MarketingEventsMgr", "getActivityInfo fail, ", e.getMessage());
        }
        if (headers.get("x-huid") == null) {
            LogUtil.e("MarketingEventsMgr", "getActivityInfo fail, x-huid is null");
            return null;
        }
        Response<ActivityInfo> execute = activityInfoApi.getActivityInfo(activityInfoReq.getUrl(), headers, lql.b(activityInfoFactory.getBody(activityInfoReq))).execute();
        if (execute.isOK()) {
            LogUtil.c("MarketingEventsMgr", "response is OK.");
            ActivityInfo body = execute.getBody();
            LogUtil.c("MarketingEventsMgr", "activityInfo.getResultCode(): ", body.getResultCode());
            if (body.getResultCode().equals("0")) {
                int joinStatus = body.getUserActivityInfo().getJoinStatus();
                LogUtil.c("MarketingEventsMgr", "joinStatus: ", Integer.valueOf(joinStatus));
                return b(triggerEventBase, joinStatus);
            }
        }
        return null;
    }

    private static TriggerEventBase b(TriggerEventBase triggerEventBase, int i) {
        int eventType = triggerEventBase.getEventType();
        if (eventType == 50) {
            if (i < 0 || i > 5) {
                return null;
            }
            LogUtil.c("MarketingEventsMgr", "EventType ", Integer.valueOf(triggerEventBase.getEventType()), ",joinStatus is ", Integer.valueOf(i));
            return triggerEventBase;
        }
        if (eventType != 2 && eventType != 45) {
            LogUtil.a("MarketingEventsMgr", "Other EventType ", Integer.valueOf(triggerEventBase.getEventType()), ",joinStatus is ", Integer.valueOf(i));
            return null;
        }
        if (i != 1) {
            return null;
        }
        LogUtil.c("MarketingEventsMgr", "EventType ", Integer.valueOf(triggerEventBase.getEventType()), ",joinStatus is ", Integer.valueOf(i));
        return triggerEventBase;
    }

    private static TriggerEventBase a(List<TriggerEventBase> list, Map<String, ?> map) {
        for (TriggerEventBase triggerEventBase : list) {
            if (triggerEventBase.getEventType() == 49 && Arrays.asList(triggerEventBase.getEventValue().split(",")).contains(String.valueOf(map.get("open_specific_page")))) {
                return triggerEventBase;
            }
        }
        return null;
    }

    private static TriggerEventBase c(List<TriggerEventBase> list, int i) {
        for (TriggerEventBase triggerEventBase : list) {
            if (triggerEventBase.getEventType() == i) {
                return c(triggerEventBase);
            }
        }
        return null;
    }

    private static TriggerEventBase a(List<TriggerEventBase> list, int i) {
        for (TriggerEventBase triggerEventBase : list) {
            int eventType = triggerEventBase.getEventType();
            triggerEventBase.getEventValue();
            if (eventType == i) {
                return a(triggerEventBase);
            }
        }
        return null;
    }

    private static TriggerEventBase c(TriggerEventBase triggerEventBase) {
        String d = d(BaseApplication.e(), "_week_report_no_genera_flag");
        String b = DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_S);
        LogUtil.c("MarketingEventsMgr", "reportPop getFinishWeekReportEvent popTime ", d, " currentDate ", b);
        if (b.equals(d)) {
            return triggerEventBase;
        }
        return null;
    }

    private static TriggerEventBase a(TriggerEventBase triggerEventBase) {
        String d = d(BaseApplication.e(), "_month_report_no_genera_flag");
        String b = DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YEAR_MONTH_S);
        LogUtil.c("MarketingEventsMgr", "reportPop getFinishMonthReportEvent popTime ", d, " currentDate ", b);
        if (b.equals(d)) {
            return triggerEventBase;
        }
        return null;
    }

    private static String d(Context context, String str) {
        if (str == null) {
            return "";
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.c("SharedPreferenceUtil", "getSharedPreference huid null");
            return "";
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(20003), accountInfo + str);
        return TextUtils.isEmpty(b) ? "" : b;
    }
}
