package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class mlk {
    private static long d;

    public static boolean a(UserAchieveWrapper userAchieveWrapper) {
        RecentWeekRecord acquireRecentWeekRecord;
        return userAchieveWrapper != null && userAchieveWrapper.getContentType() == 2 && (acquireRecentWeekRecord = userAchieveWrapper.acquireRecentWeekRecord()) != null && acquireRecentWeekRecord.acquireMinReportNo() == 0;
    }

    public static void b(Context context) {
        if (context == null || Utils.o()) {
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport isOversea!");
            return;
        }
        if (b()) {
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport isDuplicateClick!");
            return;
        }
        if (Calendar.getInstance().get(7) - 1 == 1) {
            String b = mct.b(context, "_alarmStartFlag");
            String e = e();
            if (e.equals(b)) {
                LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport is duplicate！ date = ", e);
                return;
            } else {
                e(context);
                return;
            }
        }
        LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport is not Monday！");
    }

    private static void e(final Context context) {
        final String b = mct.b(context, "_weekReportNo");
        final String b2 = mct.b(context, "_weekMinReportNo");
        ThreadPoolManager.d().execute(new Runnable() { // from class: mlk.4
            @Override // java.lang.Runnable
            public void run() {
                mlk.b(context, b2, b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str, String str2) {
        int i;
        HashMap hashMap = new HashMap(16);
        hashMap.put("reportNo", "0");
        mcz d2 = meh.c(context).d(3, hashMap);
        if (!(d2 instanceof RecentWeekRecord)) {
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport no weekRecord！");
            return;
        }
        RecentWeekRecord recentWeekRecord = (RecentWeekRecord) d2;
        int acquireReportNo = recentWeekRecord.acquireReportNo();
        int acquireMinReportNo = recentWeekRecord.acquireMinReportNo();
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "ReportNo=", Integer.valueOf(acquireReportNo), "minReportNo=", Integer.valueOf(acquireMinReportNo));
        int i2 = 0;
        if (TextUtils.isEmpty(str2)) {
            i = acquireReportNo;
        } else {
            try {
                i = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                LogUtil.b("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "acquireReportNo error");
                i = 0;
            }
        }
        if (TextUtils.isEmpty(str)) {
            i2 = acquireMinReportNo;
        } else {
            try {
                i2 = Integer.parseInt(str);
            } catch (NumberFormatException unused2) {
                LogUtil.b("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "acquireReportNo error");
            }
        }
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "MaxNo=", Integer.valueOf(i), "minNo=", Integer.valueOf(i2));
        int max = Math.max(i2, acquireMinReportNo);
        int max2 = Math.max(i, acquireReportNo);
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "handler mMinReportNo=", Integer.valueOf(max), "mMaxReportNo", Integer.valueOf(max2));
        a(context, max, max2);
    }

    private static void a(Context context, int i, int i2) {
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgTitle(context.getString(R.string._2130840727_res_0x7f020c97));
        messageObject.setMsgContent(context.getString(R.string._2130840730_res_0x7f020c9a));
        messageObject.setFlag(0);
        messageObject.setMsgType(2);
        messageObject.setWeight(0);
        messageObject.setReadFlag(0);
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setDetailUri("messagecenter://sportReport?report_stype=1&min_report_no=" + i + "&max_report_no=" + i2);
        messageObject.setPosition(2);
        messageObject.setMsgPosition(0);
        String a2 = a(context);
        LogUtil.c("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "getReportMsg mUserProfile.getHuid()=", a2);
        messageObject.setHuid(a2);
        String b = mct.b(context, "_achieve_msg_id_week");
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "processWeekly msgId=", b);
        if (TextUtils.isEmpty(b)) {
            b = "136";
        }
        messageObject.setMsgId(b);
        b(context, messageObject);
    }

    private static void b(Context context, MessageObject messageObject) {
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "ready to show message");
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(7) - 1 == 1) {
            int i = calendar.get(11);
            if (i > 9 && i < 21) {
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "message type is ", messageObject.getType(), " isNotified: ", Integer.valueOf(messageObject.getNotified()));
                d(context);
                String e = e();
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "date is ", e);
                mct.b(context, "_alarmStartFlag", e);
                mct.b(context, "_week_report_no_genera_flag", e);
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "reportPop showNotificationMsg weekReport general! date ", e);
                SharedPreferenceManager.e(String.valueOf(20003), "new_report", true);
                c(context, messageObject);
                return;
            }
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport hour is not in (9, 21)！");
            return;
        }
        LogUtil.h("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport Today isn't Monday!!");
    }

    private static void c(Context context, MessageObject messageObject) {
        messageObject.setMsgPosition(11);
        messageObject.setModule("16");
        messageObject.setMsgTitle(messageObject.getMsgTitle());
        messageObject.setPosition(1);
        mcv.d(context).getAdapter().generateMessage(messageObject);
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "notificationWeekReport is success!");
    }

    private static void d(final Context context) {
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_WeekReportStrBuilder", "refreshWeeklyReport!");
        ThreadPoolManager.d().execute(new Runnable() { // from class: mlk.1
            @Override // java.lang.Runnable
            public void run() {
                mcv.d(context).i();
            }
        });
    }

    private static String a(Context context) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        LogUtil.a("getHuid", "getSharedPreference huid null");
        return "";
    }

    private static String e() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(2);
        return (i + 1) + Constants.LINK + calendar.get(5);
    }

    public static boolean b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - d < 3500) {
            return true;
        }
        d = elapsedRealtime;
        return false;
    }
}
