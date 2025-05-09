package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.threeCircle.remind.ThreeCircleNotificationReceiver;
import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class gjc {
    private static final int[] c = {R.string.IDS_hw_health_active_record_tips_title_one, R.string.IDS_hw_health_active_record_tips_title_two, R.string.IDS_hw_health_active_record_tips_title_three, R.string.IDS_hw_health_active_record_tips_title_four};

    public static void b(ThreeCircleRemindData threeCircleRemindData) {
        Context e = BaseApplication.e();
        if (CommonUtil.s(e)) {
            ReleaseLogUtil.b("R_TC_3CircNotifMgr", "App is on foreground");
            return;
        }
        String remindType = threeCircleRemindData.getRemindType();
        String subRemindType = threeCircleRemindData.getSubRemindType();
        String remindText = threeCircleRemindData.getRemindText();
        if (TextUtils.isEmpty(remindType) || TextUtils.isEmpty(remindText)) {
            ReleaseLogUtil.a("R_TC_3CircNotifMgr", "remindType is ", remindType, ", content is ", remindText);
        } else {
            e(c(e, remindType, subRemindType), remindText, remindType, subRemindType);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String c(Context context, String str, String str2) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -818058693:
                if (str.equals("LagEncourage")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -520901247:
                if (str.equals("PerfectWeek")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -257459142:
                if (str.equals("ActiveWeek")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 47413582:
                if (str.equals("TodayAchievement")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 593365543:
                if (str.equals("OverGoal")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1023002259:
                if (str.equals("PerfectMonth")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1757487076:
                if (str.equals("YesterdaySummary")) {
                    c2 = 6;
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
                Resources resources = context.getResources();
                int[] iArr = c;
                return resources.getString(iArr[new SecureRandom().nextInt(iArr.length)]);
            case 1:
                return context.getResources().getString(R.string.ids_active_perfect_week);
            case 2:
                return context.getResources().getString(R.string.ids_active_active_week);
            case 3:
                return b(context, str2);
            case 4:
                return context.getResources().getString(R.string.ids_active_done_over);
            case 5:
                return context.getResources().getString(R.string.ids_active_perfect_month);
            case 6:
                return context.getResources().getString(R.string.ids_active_done_yesterday);
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String b(Context context, String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1589111119:
                if (str.equals("DoneIntensity")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1379689205:
                if (str.equals("DoneCalorie")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -791761249:
                if (str.equals("DoneAll")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1204742144:
                if (str.equals("DoneActiveH")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            return context.getResources().getString(R.string.ids_active_done_intensity);
        }
        if (c2 == 1) {
            return context.getResources().getString(R.string.ids_active_done_calorie);
        }
        if (c2 != 2) {
            return c2 != 3 ? "" : context.getResources().getString(R.string.ids_active_done_active_hours);
        }
        return context.getResources().getString(R.string.ids_active_done_all);
    }

    private static void e(String str, String str2, String str3, String str4) {
        PendingIntent aNg_;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.a("R_TC_3CircNotifMgr", "sendNotification, title or content is empty");
            return;
        }
        int e = gjz.e("notificationTimes", System.currentTimeMillis());
        if (e >= 3) {
            ReleaseLogUtil.b("R_TC_3CircNotifMgr", "sendNotification, times is limit, value is ", Integer.valueOf(e));
            return;
        }
        Context e2 = BaseApplication.e();
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentTitle(str);
        xf_.setContentText(str2);
        xf_.setStyle(new Notification.BigTextStyle().bigText(str2));
        if (Build.VERSION.SDK_INT > 33) {
            aNg_ = aNh_(e2, str, str3, str4);
        } else {
            aNg_ = aNg_(e2, str, str3, str4);
        }
        xf_.setContentIntent(aNg_);
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        jdh.c().xh_(20230505, xf_.build());
        int i = e + 1;
        gjz.e("notificationTimes", System.currentTimeMillis(), i);
        ReleaseLogUtil.b("R_TC_3CircNotifMgr", "sendNotification, title is ", str, ", content is ", str2, ", times is ", Integer.valueOf(i));
    }

    private static PendingIntent aNg_(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) ThreeCircleNotificationReceiver.class);
        intent.putExtra("name", str);
        intent.putExtra("remindType", str2);
        intent.putExtra("subRemindType", str3);
        return PendingIntent.getBroadcast(context, 0, intent, 201326592);
    }

    private static PendingIntent aNh_(Context context, String str, String str2, String str3) {
        String str4 = "huaweischeme://healthapp/router/threeCircleNotify?&name=" + str + "&remindType=" + str2 + "&subRemindType=" + str3;
        LogUtil.c("TC_3CircNotifMgr", "deeplink is ", str4);
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str4));
        intent.setPackage(BaseApplication.d());
        return PendingIntent.getActivity(context, 0, intent, 201326592);
    }
}
