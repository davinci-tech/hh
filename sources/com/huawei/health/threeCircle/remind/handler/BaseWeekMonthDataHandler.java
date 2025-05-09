package com.huawei.health.threeCircle.remind.handler;

import android.content.res.Resources;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler;
import com.huawei.health.threeCircle.remind.model.DeviceEventData;
import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.threecircle.api.ThreeCircleApi;
import defpackage.gjr;
import defpackage.moj;
import defpackage.nir;
import defpackage.niw;
import defpackage.njb;
import defpackage.njd;
import defpackage.nje;
import defpackage.njf;
import defpackage.njg;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public abstract class BaseWeekMonthDataHandler {
    private static final int ACHIEVE_DAY_FIVE = 5;
    private static final int ACHIEVE_DAY_FOUR = 4;
    private static final int ACHIEVE_DAY_ONE = 1;
    private static final int ACHIEVE_DAY_THREE = 3;
    private static final int ACHIEVE_DAY_TWO = 2;

    public abstract String getLogTag();

    public abstract String getQueryType();

    public void acquireEncourageData(final Map<String, Integer> map, final int i, final gjr gjrVar) {
        if (gjrVar == null) {
            LogUtil.e(getLogTag(), "acquireEncourageData remindDataCallback == null");
            return;
        }
        ThreeCircleApi threeCircleApi = (ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class);
        if (threeCircleApi == null) {
            LogUtil.c(getLogTag(), "acquireEncourageData threeCircleApi == null");
        } else {
            ReleaseLogUtil.e(getLogTag(), "acquireEncourageData date:", Integer.valueOf(i));
            threeCircleApi.queryEncourage(getQueryType(), i, new UiCallback<nje>() { // from class: com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "acquireEncourageData errorCode:", Integer.valueOf(i2), "errorInfo:", str);
                    gjrVar.onResponse(-1, null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(nje njeVar) {
                    if (njeVar == null) {
                        ReleaseLogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "onTrigger data == null");
                        gjrVar.onResponse(0, null);
                        return;
                    }
                    LogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "onSuccess:", moj.e(njeVar));
                    njf goalAchieveEncourage = BaseWeekMonthDataHandler.this.getGoalAchieveEncourage(njeVar);
                    if (goalAchieveEncourage == null) {
                        ReleaseLogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "acquireEncourageData goalInfo == null");
                        return;
                    }
                    ReleaseLogUtil.e(BaseWeekMonthDataHandler.this.getLogTag(), "acquireEncourageData encourageType:", Integer.valueOf(goalAchieveEncourage.b()), "achieveDays:", Integer.valueOf(goalAchieveEncourage.a()));
                    gjrVar.onResponse(0, BaseWeekMonthDataHandler.this.getThreeCircleRemindData(goalAchieveEncourage, map, i));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ThreeCircleRemindData getThreeCircleRemindData(njf njfVar, Map<String, Integer> map, int i) {
        ThreeCircleRemindData.c cVar = new ThreeCircleRemindData.c();
        String encourageRemindType = getEncourageRemindType(njfVar.b());
        cVar.b(encourageRemindType);
        cVar.e(getPriority(map, encourageRemindType));
        String encourageSubRemindType = getEncourageSubRemindType(njfVar, i);
        cVar.c(encourageSubRemindType);
        ReleaseLogUtil.e(getLogTag(), "acquireEncourageData remindType:", encourageRemindType, " subRemindType:", encourageSubRemindType);
        setDeviceEventData(njfVar, cVar);
        cVar.a(getRemindText(encourageRemindType, encourageSubRemindType, njfVar.d(), njfVar.a()));
        return cVar.d();
    }

    private void setDeviceEventData(njf njfVar, ThreeCircleRemindData.c cVar) {
        if (njfVar.b() == 3) {
            DeviceEventData.c cVar2 = new DeviceEventData.c();
            cVar2.a(njfVar.a());
            cVar2.i(njfVar.d());
            cVar2.j(3);
            cVar2.c(System.currentTimeMillis() / 1000);
            cVar.a(cVar2.d());
        }
    }

    private String getRemindText(String str, String str2, int i, int i2) {
        String dayString;
        ThreeCircleApi threeCircleApi = (ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class);
        njg queryRules = threeCircleApi.queryRules(str, str2);
        if (queryRules == null) {
            ReleaseLogUtil.d(getLogTag(), "getRemindText bean == null remindType:", str, " subRemindType:", str2);
            return "";
        }
        if ("Zero".equals(str2) && "ActiveWeek".equals(str)) {
            dayString = String.valueOf(i);
        } else {
            dayString = getDayString(i);
        }
        String dayString2 = getDayString(i2);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str3 : queryRules.e()) {
            if ("remainActiveDay".equals(str3)) {
                linkedHashMap.put(str3, dayString);
            } else if ("reachGoalDay".equals(str3)) {
                linkedHashMap.put(str3, dayString2);
            }
        }
        return threeCircleApi.getPromptMessage(queryRules, linkedHashMap);
    }

    private String getDayString(int i) {
        Resources resources = BaseApplication.e().getResources();
        if (resources == null) {
            LogUtil.d(getLogTag(), "getDayString resources == null");
            return String.valueOf(i);
        }
        return resources.getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i));
    }

    private int getPriority(Map<String, Integer> map, String str) {
        Integer num;
        if (map == null || (num = map.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    private String getEncourageSubRemindType(njf njfVar, int i) {
        int b = njfVar.b();
        if (b == 3) {
            return "Perfect";
        }
        int a2 = njfVar.a();
        String perfectWeekSubRemindType = getPerfectWeekSubRemindType(njfVar, i, b, a2);
        return perfectWeekSubRemindType != null ? perfectWeekSubRemindType : b == 1 ? getActiveWeekSubRemindType(njfVar, a2, i) : "";
    }

    private String getPerfectWeekSubRemindType(njf njfVar, int i, int i2, int i3) {
        if (i2 != 2) {
            return null;
        }
        if (isQueryFriday(i)) {
            if (i3 == 5) {
                return "Five";
            }
            if (i3 == 4 && njfVar.e()) {
                return "Five";
            }
            return null;
        }
        if (i3 == 3) {
            return "Three";
        }
        if (i3 == 2 && njfVar.e()) {
            return "Three";
        }
        return null;
    }

    private String getActiveWeekSubRemindType(njf njfVar, int i, int i2) {
        return i == 3 ? "Three" : (i == 2 && njfVar.e()) ? "Three" : (isQueryWednesday(i2) && i == 2) ? "Two" : (isQueryWednesday(i2) && i == 1) ? "One" : (isQueryWednesday(i2) && i == 0 && njfVar.e()) ? "Zero" : "";
    }

    private String getEncourageRemindType(int i) {
        if (i == 1) {
            return "ActiveWeek";
        }
        if (i == 2) {
            return "PerfectWeek";
        }
        if (i == 3) {
            return "PerfectMonth";
        }
        ReleaseLogUtil.d(getLogTag(), "getRemindType other encourageType:", Integer.valueOf(i));
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public njf getGoalAchieveEncourage(nje njeVar) {
        if ("monthEncourage".equals(getQueryType())) {
            return njeVar.e();
        }
        if ("weekEncourage".equals(getQueryType())) {
            njf b = njeVar.b();
            return b == null ? njeVar.d() : b;
        }
        LogUtil.c(getLogTag(), "getGoalAchieveEncourage error queryType");
        return null;
    }

    private boolean isQueryWednesday(int i) {
        Integer weekDay = getWeekDay(i);
        if (weekDay == null) {
            return false;
        }
        String logTag = getLogTag();
        Object[] objArr = new Object[2];
        objArr[0] = "isQueryWednesday:";
        objArr[1] = Boolean.valueOf(weekDay.intValue() == 4);
        ReleaseLogUtil.d(logTag, objArr);
        return weekDay.intValue() == 4;
    }

    private Integer getWeekDay(int i) {
        Date date;
        try {
            date = DateFormatUtil.d(String.valueOf(i), DateFormatUtil.DateFormatType.DATE_FORMAT_8);
        } catch (ParseException e) {
            LogUtil.e(getLogTag(), "getWeekDay ParseException currentTime:", Integer.valueOf(i), "-----", LogAnonymous.b((Throwable) e));
            date = null;
        }
        if (date == null) {
            ReleaseLogUtil.d(getLogTag(), "getWeekDay date == null");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.valueOf(calendar.get(7));
    }

    private boolean isQueryFriday(int i) {
        Integer weekDay = getWeekDay(i);
        if (weekDay == null) {
            return false;
        }
        String logTag = getLogTag();
        Object[] objArr = new Object[2];
        objArr[0] = "isQueryFriday:";
        objArr[1] = Boolean.valueOf(weekDay.intValue() == 6);
        ReleaseLogUtil.d(logTag, objArr);
        return weekDay.intValue() == 6;
    }

    public void acquireSummaryData(final Map<String, Integer> map, final gjr gjrVar, int i) {
        ThreeCircleApi threeCircleApi = (ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class);
        if (threeCircleApi == null) {
            LogUtil.c(getLogTag(), "acquireSummaryData threeCircleApi == null");
        } else {
            ReleaseLogUtil.e(getLogTag(), "acquireSummaryData date:", Integer.valueOf(i));
            threeCircleApi.querySummary(getQueryType(), i, new UiCallback<njb>() { // from class: com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "acquireSummaryData errorCode:", Integer.valueOf(i2), "errorInfo:", str);
                    gjrVar.onResponse(i2, null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(njb njbVar) {
                    if (njbVar == null) {
                        ReleaseLogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "acquireSummaryData data == null");
                        gjrVar.onResponse(0, null);
                        return;
                    }
                    njd b = njbVar.b();
                    if (b == null) {
                        ReleaseLogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "acquireSummaryData summary == null");
                    } else {
                        LogUtil.d(BaseWeekMonthDataHandler.this.getLogTag(), "onSuccess.", moj.e(njbVar));
                        BaseWeekMonthDataHandler.this.responseThreeCircleRemind(map, b, gjrVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void responseThreeCircleRemind(Map<String, Integer> map, njd njdVar, gjr gjrVar) {
        String summaryRemindType = getSummaryRemindType();
        String summarySubRemindType = getSummarySubRemindType(njdVar.e());
        ReleaseLogUtil.e(getLogTag(), "responseThreeCircleRemind type:", summaryRemindType, "subRemindType:", summarySubRemindType);
        ThreeCircleRemindData.c c = new ThreeCircleRemindData.c().b(summaryRemindType).e(getPriority(map, summaryRemindType)).c(summarySubRemindType);
        if ("monthReport".equals(getQueryType())) {
            gjrVar.onResponse(0, c.d());
        } else {
            c.a(getWeekSummaryDeviceEventData(njdVar));
            gjrVar.onResponse(0, c.d());
        }
    }

    private DeviceEventData getWeekSummaryDeviceEventData(njd njdVar) {
        DeviceEventData.c cVar = new DeviceEventData.c();
        cVar.a(njdVar.e());
        cVar.i(Math.max(7 - njdVar.e(), 0));
        cVar.e(njdVar.d());
        cVar.d(njdVar.c());
        cVar.j(1);
        cVar.b(njdVar.a());
        cVar.c(System.currentTimeMillis() / 1000);
        int smartRecommendCaloriesValue = getSmartRecommendCaloriesValue();
        if (smartRecommendCaloriesValue != 0) {
            cVar.f(smartRecommendCaloriesValue);
        }
        return cVar.d();
    }

    private int getSmartRecommendCaloriesValue() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicInteger atomicInteger = new AtomicInteger();
        niw.b((List<String>) Collections.singletonList("900200007"), 1, new IBaseResponseCallback() { // from class: gjd
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BaseWeekMonthDataHandler.this.m521x5260e7e1(countDownLatch, atomicInteger, i, obj);
            }
        });
        try {
            if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                LogUtil.c(getLogTag(), "getRecommendGoal wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.e(getLogTag(), "interrupted while waiting for getRecommendGoal");
        }
        return atomicInteger.intValue();
    }

    /* renamed from: lambda$getSmartRecommendCaloriesValue$0$com-huawei-health-threeCircle-remind-handler-BaseWeekMonthDataHandler, reason: not valid java name */
    public /* synthetic */ void m521x5260e7e1(CountDownLatch countDownLatch, AtomicInteger atomicInteger, int i, Object obj) {
        if (!(obj instanceof HashMap)) {
            countDownLatch.countDown();
            return;
        }
        nir nirVar = (nir) ((HashMap) obj).get("900200007");
        if (nirVar == null) {
            LogUtil.c(getLogTag(), "recommendGoalModel == null.");
            countDownLatch.countDown();
            return;
        }
        LogUtil.d(getLogTag(), "getRecommendGoal:", Integer.valueOf(nirVar.e()), " state:", Integer.valueOf(nirVar.b()));
        if (nirVar.b() == 0) {
            atomicInteger.set(Integer.MAX_VALUE);
        } else {
            atomicInteger.set(nirVar.e());
        }
        countDownLatch.countDown();
    }

    private String getSummaryRemindType() {
        return "weekReport".equals(getQueryType()) ? "WeekSummary" : "monthReport".equals(getQueryType()) ? "MonthSummary" : "";
    }

    private String getSummarySubRemindType(int i) {
        return "monthReport".equals(getQueryType()) ? "" : i == 7 ? "PerfectWeek" : (i < 5 || i >= 7) ? (i <= 0 || i >= 5) ? "Zero" : "LessFive" : "ActiveWeek";
    }
}
