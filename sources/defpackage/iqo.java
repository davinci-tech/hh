package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.suggestion.ResultCallback;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;

/* loaded from: classes7.dex */
public class iqo {
    private b b;
    private b c;
    private AlarmManager d;
    private AlarmManager e;
    private PendingIntent j;

    /* renamed from: a, reason: collision with root package name */
    private final int f13542a = 5000;
    private final int i = 300000;

    public void a(ResultCallback resultCallback) {
        if (this.d == null) {
            ReleaseLogUtil.e("HiH_DataObservableAlarmManager", "start step alarm manager");
            if (this.c == null) {
                this.c = new b(resultCallback);
            }
            BroadcastManagerUtil.bFA_(BaseApplication.e(), this.c, new IntentFilter("com.huawei.hihealth.action.get.step"), LocalBroadcast.c, null);
            Intent intent = new Intent("com.huawei.hihealth.action.get.step");
            intent.setPackage(BaseApplication.d());
            this.j = PendingIntent.getBroadcast(BaseApplication.e(), 0, intent, AppRouterExtras.COLDSTART);
            AlarmManager alarmManager = (AlarmManager) BaseApplication.e().getSystemService(NotificationCompat.CATEGORY_ALARM);
            this.d = alarmManager;
            alarmManager.setRepeating(1, System.currentTimeMillis() + 5000, 300000L, this.j);
            return;
        }
        ReleaseLogUtil.e("HiH_DataObservableAlarmManager", "alarm manager has started before");
    }

    public boolean d() {
        return this.d != null;
    }

    public void e() {
        if (this.d != null && this.j != null) {
            ReleaseLogUtil.e("HiH_DataObservableAlarmManager", "cancel step alarm manager");
            this.d.cancel(this.j);
            this.d = null;
        }
        if (this.c != null) {
            try {
                BaseApplication.e().unregisterReceiver(this.c);
            } catch (IllegalArgumentException e) {
                ReleaseLogUtil.c("HiH_DataObservableAlarmManager", "unregisterReceiver exception: ", e.getMessage());
            }
        }
    }

    public void b(ResultCallback resultCallback) {
        if (this.e == null) {
            ReleaseLogUtil.e("HiH_DataObservableAlarmManager", "start tomorrow alarm");
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.add(5, 1);
            if (this.b == null) {
                this.b = new b(resultCallback);
            }
            BroadcastManagerUtil.bFA_(BaseApplication.e(), this.b, new IntentFilter("com.huawei.hihealth.action.subscribe.data"), LocalBroadcast.c, null);
            Intent intent = new Intent("com.huawei.hihealth.action.subscribe.data");
            intent.setPackage(BaseApplication.d());
            PendingIntent broadcast = PendingIntent.getBroadcast(BaseApplication.e(), 0, intent, AppRouterExtras.COLDSTART);
            AlarmManager alarmManager = (AlarmManager) BaseApplication.e().getSystemService(NotificationCompat.CATEGORY_ALARM);
            this.e = alarmManager;
            alarmManager.set(1, calendar.getTimeInMillis(), broadcast);
        }
    }

    public void a() {
        this.e = null;
        if (this.b != null) {
            BaseApplication.e().unregisterReceiver(this.b);
        }
    }

    static class b extends BroadcastReceiver {
        private final ResultCallback b;

        b(ResultCallback resultCallback) {
            this.b = resultCallback;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ReleaseLogUtil.e("HiH_DataObservableAlarmManager", "StepBroadcastReceiver receive action ", intent.getAction());
            this.b.onResult(0, intent);
        }
    }
}
