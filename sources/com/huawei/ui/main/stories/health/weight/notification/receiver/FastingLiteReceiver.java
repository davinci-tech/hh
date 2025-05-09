package com.huawei.ui.main.stories.health.weight.notification.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.weight.notification.receiver.FastingLiteReceiver;
import defpackage.jdh;
import defpackage.jec;
import defpackage.nsn;
import defpackage.qlc;
import defpackage.qui;
import defpackage.quz;
import defpackage.qve;
import defpackage.qvf;
import defpackage.sdk;
import java.util.Calendar;

/* loaded from: classes7.dex */
public class FastingLiteReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!intent.hasExtra("fastingLiteUid")) {
            LogUtil.h("HealthWeight_FastingLiteReceiver", "has no extra ", "fastingLiteUid");
            return;
        }
        String stringExtra = intent.getStringExtra("fastingLiteUid");
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        LogUtil.a("HealthWeight_FastingLiteReceiver", "huid ", stringExtra, " currentUserId", accountInfo);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(accountInfo) || !stringExtra.equals(accountInfo)) {
            return;
        }
        final int intExtra = intent.getIntExtra("fastingLiteId", -1);
        long longExtra = intent.getLongExtra("fastingLiteTime", 0L);
        LogUtil.a("HealthWeight_FastingLiteReceiver", "onReceive fastingLiteType = ", Integer.valueOf(intExtra), "; fastingLiteTime = ", jec.e(longExtra));
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(b(currentTimeMillis) - b(longExtra)) > 20 || a(currentTimeMillis, intExtra)) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: qvh
            @Override // java.lang.Runnable
            public final void run() {
                FastingLiteReceiver.this.a(intExtra);
            }
        });
    }

    public /* synthetic */ void a(int i) {
        qui c = qve.c();
        if (c == null || c.d() == null || c.d().b() == null) {
            LogUtil.h("HealthWeight_FastingLiteReceiver", "sendNotification getSetting fail");
            return;
        }
        if (c.d().b().g() > 0) {
            LogUtil.a("HealthWeight_FastingLiteReceiver", "sendNotification lower version");
            quz.e().b();
            return;
        }
        qvf a2 = qlc.b().a();
        if (a2 != null) {
            a2.v();
        }
        if (i == 20210707) {
            qlc.b().j();
        }
        if (!sdk.c().e()) {
            LogUtil.h("HealthWeight_FastingLiteReceiver", "notice not enable");
        } else {
            c(i);
        }
    }

    private boolean a(long j, int i) {
        String d = sdk.c().d(i);
        LogUtil.a("HealthWeight_FastingLiteReceiver", "isRepeatedNotification mLastRemindMap: ", d);
        if (TextUtils.isEmpty(d) || Math.abs(j - nsn.h(d)) >= 1200000) {
            return false;
        }
        LogUtil.a("HealthWeight_FastingLiteReceiver", "isRepeatedNotification reminderId:", d);
        return true;
    }

    private int b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = (calendar.get(11) * 60) + calendar.get(12);
        LogUtil.a("HealthWeight_FastingLiteReceiver", "getMinute : ", Integer.valueOf(i));
        return i;
    }

    private void c(int i) {
        String string;
        int i2;
        if (i == 20100410) {
            string = BaseApplication.e().getResources().getString(R$string.IDS_wl_food_notice_b_status);
            i2 = 20100415;
        } else {
            if (i != 20100413) {
                if (i != 20210707) {
                    return;
                }
                String string2 = BaseApplication.e().getResources().getString(R$string.IDS_weight_fasting_notice);
                c(string2, string2, 20210707, i);
                return;
            }
            string = BaseApplication.e().getResources().getString(R$string.IDS_wl_food_notice_e_status);
            i2 = 20100417;
        }
        if (qlc.b().c()) {
            LogUtil.a("HealthWeight_FastingLiteReceiver", "sendNotification isRestDay");
        } else {
            c(string, string, i2, i);
        }
    }

    private void c(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        if (TextUtils.isEmpty(charSequence2)) {
            LogUtil.h("HealthWeight_FastingLiteReceiver", "sendNotification content is null");
            return;
        }
        jdh.c().a(i);
        LogUtil.a("HealthWeight_FastingLiteReceiver", "setNotify reminderId = ", Integer.valueOf(i2));
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setTicker(charSequence);
        xf_.setContentTitle(BaseApplication.e().getResources().getString(R$string.IDS_wl_food_entrance_light_f));
        xf_.setContentText(charSequence2);
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) NotificationReceiver.class);
        if (i2 == 20210707) {
            intent.setAction("fasting_report");
        }
        xf_.setContentIntent(PendingIntent.getBroadcast(BaseApplication.e(), i2, intent, 201326592));
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        xf_.setStyle(new Notification.BigTextStyle().bigText(charSequence2));
        jdh.c().xh_(i, xf_.build());
        sdk.c().e(i2, String.valueOf(System.currentTimeMillis()));
        LogUtil.a("HealthWeight_FastingLiteReceiver", "sendNotification, notificationId: ", Integer.valueOf(i), ", reminderId: ", Integer.valueOf(i2));
    }
}
