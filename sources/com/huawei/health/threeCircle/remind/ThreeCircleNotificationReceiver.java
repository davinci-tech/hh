package com.huawei.health.threeCircle.remind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.operation.utils.Constants;
import defpackage.eme;
import defpackage.gnm;
import defpackage.ixx;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class ThreeCircleNotificationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ReleaseLogUtil.b("R_TC_ThreeCircleNotificationReceiver", "onReceive click notification");
        e(intent.getStringExtra("name"), intent.getStringExtra("remindType"), intent.getStringExtra("subRemindType"));
    }

    public static void e(String str, String str2, String str3) {
        Intent intent = new Intent();
        if (b(str2, str3)) {
            e(str3);
        } else {
            intent = aNi_(str2, str3);
            if (intent != null) {
                gnm.aPB_(BaseApplication.e(), intent);
            }
        }
        aNk_(str, intent, str2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Intent aNi_(String str, String str2) {
        char c;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("TC_ThreeCircleNotificationReceiver", "getIntentByType remindType is empty");
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra("type", 1);
        intent.setFlags(268435456);
        Context e = BaseApplication.e();
        str.hashCode();
        switch (str.hashCode()) {
            case -818058693:
                if (str.equals("LagEncourage")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -520901247:
                if (str.equals("PerfectWeek")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -257459142:
                if (str.equals("ActiveWeek")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1023002259:
                if (str.equals("PerfectMonth")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            aNj_(e, intent, str2);
        } else {
            if (c == 1 || c == 2) {
                intent.setClassName(e, "com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity");
                intent.putExtra("sources", RemoteMessageConst.NOTIFICATION);
                LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to KnitActiveRecordActivity");
                e();
                return intent;
            }
            if (c == 3) {
                intent.setClassName(e, "com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity");
                intent.putExtra("is_open_calendar", true);
                intent.putExtra("sources", RemoteMessageConst.NOTIFICATION);
                LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to KnitActiveRecordActivity");
                e();
                return intent;
            }
            intent.setClassName(e, "com.huawei.health.MainActivity");
            intent.putExtra(Constants.HOME_TAB_NAME, Constants.HOME);
            LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to mainActivity");
        }
        return intent;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void e(String str) {
        char c;
        int i;
        Context e = BaseApplication.e();
        switch (str.hashCode()) {
            case -1858675706:
                if (str.equals("LotIntensity_Walk")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 485246380:
                if (str.equals("LotCalorie_Walk")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1325511854:
                if (str.equals("LotIntensity_Run")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1539669576:
                if (str.equals("LotCalorie_Run")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to walk");
            i = 257;
        } else {
            LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to run");
            i = 258;
        }
        eme.b().startRunCourseSport(e, i, -1, -1.0f, new Bundle());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean b(String str, String str2) {
        char c;
        if (!"LagEncourage".equals(str)) {
            return false;
        }
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1858675706:
                if (str2.equals("LotIntensity_Walk")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 485246380:
                if (str2.equals("LotCalorie_Walk")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1325511854:
                if (str2.equals("LotIntensity_Run")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1539669576:
                if (str2.equals("LotCalorie_Run")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c == 0 || c == 1 || c == 2 || c == 3;
    }

    private static void e() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", "1");
        hashMap.put("from", 3);
        hashMap.put("status", new ArrayList());
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040091.value(), hashMap, 0);
    }

    private static void aNj_(Context context, Intent intent, String str) {
        str.hashCode();
        if (str.equals("LotActiveHour")) {
            intent.setClassName(context, "com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity");
            LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to KnitActiveHoursActivity");
        } else {
            intent.setClassName(context, "com.huawei.health.MainActivity");
            intent.putExtra(Constants.HOME_TAB_NAME, Constants.HOME);
            LogUtil.c("TC_ThreeCircleNotificationReceiver", "go to mainActivity");
        }
    }

    private static void aNk_(String str, Intent intent, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", 1);
        hashMap.put("name", str);
        hashMap.put("url", (intent == null || intent.getData() == null) ? "" : intent.getData().toString());
        hashMap.put("messageScenario", Integer.valueOf(c(str2)));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_PUSH_OPEN_APP_2050008.value(), hashMap, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int c(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("TC_ThreeCircleNotificationReceiver", "remindType is empty");
            return -1;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -818058693:
                if (str.equals("LagEncourage")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -520901247:
                if (str.equals("PerfectWeek")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -257459142:
                if (str.equals("ActiveWeek")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 47413582:
                if (str.equals("TodayAchievement")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 593365543:
                if (str.equals("OverGoal")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1023002259:
                if (str.equals("PerfectMonth")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1757487076:
                if (str.equals("YesterdaySummary")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 5;
            case 1:
                return 7;
            case 2:
                return 6;
            case 3:
                return 0;
            case 4:
                return 1;
            case 5:
                return 8;
            case 6:
                return 2;
            default:
                LogUtil.a("TC_ThreeCircleNotificationReceiver", "remindType does not match, remindType is ", str);
                return -1;
        }
    }
}
