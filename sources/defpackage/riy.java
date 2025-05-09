package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.messageremind.receiver.BloodPressureReminderReceiver;
import com.huawei.ui.main.stories.messageremind.receiver.SportReminderReceiver;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes7.dex */
public class riy {
    private static int a(int i) {
        return i - 1000;
    }

    public static void b(int i, long j, long j2) {
        Context e = BaseApplication.e();
        Intent intent = new Intent(e, (Class<?>) SportReminderReceiver.class);
        String accountInfo = LoginInit.getInstance(e).getAccountInfo(1011);
        intent.putExtra("reminderId", i);
        intent.putExtra("sportReminderHuid", accountInfo);
        intent.putExtra("sportReminderTime", j);
        int a2 = a(i);
        dPr_(j, a2, intent, j2, true);
        LogUtil.a("Track_ReminderHelper", "registerRepeatingReminder: ", Integer.valueOf(a2), ", time = ", DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
    }

    public static void d(int i, long j, long j2) {
        if (Math.abs(j - SharedPreferenceManager.b("BloodPressure", "register_time", 0L)) <= 3600000) {
            LogUtil.a("Track_ReminderHelper", "registerBloodPressureReminder, the alarm has been set");
            return;
        }
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) BloodPressureReminderReceiver.class);
        intent.putExtra("reminderId", i);
        intent.putExtra("sportReminderTime", j);
        dPr_(j, i, intent, j2, false);
        SharedPreferenceManager.e("BloodPressure", "register_time", j);
        LogUtil.a("Track_ReminderHelper", "registerBloodPressureReminder: ", Integer.valueOf(i), ", time = ", DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
    }

    private static void dPr_(long j, int i, Intent intent, long j2, boolean z) {
        if (z) {
            sqa.ekC_(i, intent, 201326592, 0, j, j2);
        } else {
            sqa.ekx_(i, intent, 201326592, 0, j);
        }
        ReleaseLogUtil.b("Track_ReminderHelper", "registerReminder isRepeat ", Boolean.valueOf(z), " requestCode ", Integer.valueOf(i), " time ", Long.valueOf(j), " interval ", Long.valueOf(j2));
    }

    public static void c(int i) {
        LogUtil.a("Track_ReminderHelper", "cancelReminder enter ", Integer.valueOf(i));
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) SportReminderReceiver.class);
        intent.setPackage(BaseApplication.d());
        sqa.ekn_(a(i), intent, 201326592);
    }

    public static void e(int i) {
        LogUtil.a("Track_ReminderHelper", "cancelBloodPressureReminder enter ", Integer.valueOf(i));
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) BloodPressureReminderReceiver.class);
        intent.setPackage(BaseApplication.d());
        sqa.ekn_(a(i), intent, 201326592);
    }
}
