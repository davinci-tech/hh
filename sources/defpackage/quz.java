package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.health.weight.notification.receiver.FastingLiteReceiver;
import health.compact.a.CommonUtils;
import java.util.List;

/* loaded from: classes7.dex */
public class quz {
    public int b(int i) {
        return i * 10;
    }

    private quz() {
    }

    public static quz e() {
        return b.d;
    }

    public void b() {
        b(false);
        b(true);
    }

    public void d(List<quy> list, qvc qvcVar) {
        if (qvcVar == null) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "fastingLiteSetting is null!");
            return;
        }
        b(false);
        e(list);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (!koq.b(list)) {
            for (quy quyVar : list) {
                if (currentTimeMillis < quyVar.c()) {
                    currentTimeMillis = quyVar.c();
                }
            }
        }
        c(currentTimeMillis, qvcVar);
        if (qvcVar.j() == 1) {
            e(20210707, e(qve.e(qvcVar)) / 1000, false, qvcVar.a());
        }
    }

    private long e(long j) {
        int m = jdl.m(j);
        return (m < 8 || m >= 22) ? jdl.e(jdl.h(System.currentTimeMillis()), 8, 0) : j;
    }

    private void e(List<quy> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "updateNonRepeatNotices nonRepeatEpoch is isEmpty!");
            return;
        }
        for (quy quyVar : list) {
            if (quyVar != null) {
                e(quyVar.a(), quyVar.c());
            }
        }
    }

    private void c(long j, qvc qvcVar) {
        if (qvcVar == null) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "setting is null!");
            return;
        }
        Pair<Integer, Integer> dJb_ = qvcVar.dJb_();
        Pair<Integer, Integer> dJa_ = qvcVar.dJa_();
        b(true);
        long t = jdl.t(System.currentTimeMillis()) / 1000;
        long b2 = qvcVar.b();
        long e = jdl.e(((t + b2) + qvcVar.c()) * 1000, ((Integer) dJa_.first).intValue(), ((Integer) dJa_.second).intValue()) / 1000;
        long j2 = e;
        for (int i = 1; j2 < j && i < 3; i++) {
            j2 = jdl.e((qvcVar.a() + e) * 1000, ((Integer) dJa_.first).intValue(), ((Integer) dJa_.second).intValue()) / 1000;
        }
        long j3 = j2 * 1000;
        LogUtil.a("HealthWeight_FastingLiteNoticeManager", "resetRegularNotice start = ", DateFormatUtil.b(j * 1000, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), " nextFastingLiteStartTime ", DateFormatUtil.b(j3, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
        long e2 = jdl.e(((qvcVar.a() - qvcVar.c()) + j2) * 1000, ((Integer) dJb_.first).intValue(), ((Integer) dJb_.second).intValue()) / 1000;
        c(j2, e2, true, qvcVar.a());
        LogUtil.a("HealthWeight_FastingLiteNoticeManager", "resetRegularNotice: startTime=", DateFormatUtil.b(j3, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), ", endTime = ", DateFormatUtil.b(e2 * 1000, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
    }

    private void e(long j, long j2) {
        LogUtil.a("HealthWeight_FastingLiteNoticeManager", " updateNonRepeatNotices ", DateFormatUtil.b(j * 1000, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), " endTime ", DateFormatUtil.b(1000 * j2, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
        c(j, j2, false, 1L);
    }

    private void c(long j, long j2, boolean z, long j3) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (j > currentTimeMillis) {
            e(20100410, j, z, j3);
        }
        if (j2 > currentTimeMillis) {
            e(20100413, j2, z, j3);
        }
    }

    private void b(boolean z) {
        a(20100410, z);
        a(20210707, z);
        a(20100412, z);
        a(20100413, z);
    }

    public void e(int i, long j, boolean z, long j2) {
        int b2 = z ? i : b(i);
        dIZ_(BaseApplication.e(), j, PendingIntent.getBroadcast(BaseApplication.e(), b2, dIY_(BaseApplication.e(), i, j), 201326592), z, j2);
        LogUtil.a("HealthWeight_FastingLiteNoticeManager", "registerFastingLiteNotice: ", Integer.valueOf(b2), ", time = ", DateFormatUtil.b(1000 * j, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
    }

    public void a(int i, boolean z) {
        if (!z) {
            i = b(i);
        }
        dIX_(BaseApplication.e(), i, new Intent(BaseApplication.e(), (Class<?>) FastingLiteReceiver.class));
        LogUtil.a("HealthWeight_FastingLiteNoticeManager", "cancelFastingLiteNotice: requestCode= " + i);
    }

    private void dIZ_(Context context, long j, PendingIntent pendingIntent, boolean z, long j2) {
        if (context == null || pendingIntent == null) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "context or pendingIntent is null!");
            return;
        }
        AlarmManager xy_ = CommonUtils.xy_();
        if (xy_ == null) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "manager is null!");
        } else if (z) {
            xy_.setRepeating(0, j * 1000, j2 * 1000, pendingIntent);
        } else {
            sqa.ekB_(xy_, 0, j * 1000, pendingIntent, "HealthWeight_FastingLiteNoticeManagerregisterFastingLiteReminder");
        }
    }

    private void dIX_(Context context, int i, Intent intent) {
        if (context == null || intent == null) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "context or intent is null!");
            return;
        }
        AlarmManager xy_ = CommonUtils.xy_();
        if (xy_ == null) {
            LogUtil.h("HealthWeight_FastingLiteNoticeManager", "manager is null!");
            return;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, 201326592);
        if (broadcast != null) {
            broadcast.cancel();
            xy_.cancel(broadcast);
        }
    }

    private Intent dIY_(Context context, int i, long j) {
        Intent intent = new Intent(context, (Class<?>) FastingLiteReceiver.class);
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        intent.putExtra("fastingLiteId", i);
        intent.putExtra("fastingLiteUid", accountInfo);
        long j2 = j * 1000;
        intent.putExtra("fastingLiteTime", j2);
        LogUtil.a("HealthWeight_FastingLiteNoticeManager", "reminderId ", Integer.valueOf(i), " FASTING_LITE_UID ", accountInfo, " FASTING_LITE_TIME ", Long.valueOf(j2));
        return intent;
    }

    static class b {
        private static final quz d = new quz();
    }
}
