package com.huawei.ui.main.stories.messageremind.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity;
import com.huawei.ui.main.stories.messageremind.receiver.BloodPressureReminderReceiver;
import defpackage.gib;
import defpackage.jdh;
import defpackage.koq;
import defpackage.nsn;
import defpackage.qgt;
import defpackage.qgx;
import defpackage.qif;
import defpackage.qkg;
import defpackage.sqa;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class BloodPressureReminderReceiver extends BroadcastReceiver {
    private Context d;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, final Intent intent) {
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        boolean isSystemBarNoticeSwitchOnOrDefault = CommonUtil.isSystemBarNoticeSwitchOnOrDefault(applicationContext);
        qif.e(isSystemBarNoticeSwitchOnOrDefault);
        boolean ekw_ = sqa.ekw_(intent);
        ReleaseLogUtil.b("BloodPressureReminderReceiver", "onReceive isOpenSwitch ", Boolean.valueOf(isSystemBarNoticeSwitchOnOrDefault), " isDifferentTimeZone ", Boolean.valueOf(ekw_));
        if (!ekw_ && isSystemBarNoticeSwitchOnOrDefault) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rix
                @Override // java.lang.Runnable
                public final void run() {
                    BloodPressureReminderReceiver.this.dPt_(intent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dPs_, reason: merged with bridge method [inline-methods] */
    public void dPt_(Intent intent) {
        String accountInfo = LoginInit.getInstance(this.d).getAccountInfo(1011);
        int intExtra = intent.getIntExtra("reminderId", -1);
        final long longExtra = intent.getLongExtra("sportReminderTime", 0L);
        LogUtil.a("BloodPressureReminderReceiver", "processRemindNotify, reminderType is ", Integer.valueOf(intExtra), "reminderTime is ", Long.valueOf(longExtra));
        if (TextUtils.isEmpty(accountInfo) || intExtra != 1100) {
            return;
        }
        final long b = gib.b(System.currentTimeMillis()) - 1123200000;
        new qgx().a(true, b, System.currentTimeMillis(), new IBaseResponseCallback() { // from class: riv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BloodPressureReminderReceiver.this.d(b, longExtra, i, obj);
            }
        });
    }

    public /* synthetic */ void d(long j, long j2, int i, Object obj) {
        List<qkg> emptyList = obj instanceof List ? (List) obj : Collections.emptyList();
        if (koq.b(emptyList)) {
            LogUtil.a("BloodPressureReminderReceiver", "processRemindNotify, bloodPressureList is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (qkg qkgVar : emptyList) {
            LogUtil.a("BloodPressureReminderReceiver", "processRemindNotify, bloodPressure is ", qkgVar);
            if (qkgVar.h() > 604800000 + j) {
                arrayList.add(qkgVar);
            } else {
                arrayList2.add(qkgVar);
            }
        }
        if (c(arrayList) && c(arrayList2)) {
            LogUtil.a("BloodPressureReminderReceiver", "processRemindNotify, show compare");
            d(arrayList, arrayList2, j2);
        } else if (c(arrayList)) {
            LogUtil.a("BloodPressureReminderReceiver", "processRemindNotify, lastWeekData size is not match");
            a(1, 6, j2);
        } else {
            LogUtil.a("BloodPressureReminderReceiver", "processRemindNotify, thisWeekData size is not match");
        }
    }

    private void d(List<qkg> list, List<qkg> list2, long j) {
        qgt b = b(list);
        qgt b2 = b(list2);
        int c = c(b.a());
        int c2 = c(b.d());
        int c3 = c(b2.a());
        int c4 = c(b2.d());
        if (c > c3 && c2 > c4) {
            a(2, 7, j);
        } else if (c < c3 && c2 < c4) {
            a(3, 7, j);
        } else {
            a(1, 7, j);
        }
    }

    private int c(double d) {
        return (int) UnitUtil.a(d, 0);
    }

    private boolean c(List<qkg> list) {
        return koq.c(list) && list.size() >= 3;
    }

    private qgt b(List<qkg> list) {
        int size = list.size();
        double d = 0.0d;
        double d2 = 0.0d;
        for (qkg qkgVar : list) {
            d += qkgVar.o();
            d2 += qkgVar.m();
        }
        double d3 = size;
        return new qgt(d / d3, d2 / d3);
    }

    private void a(int i, int i2, long j) {
        String string;
        LogUtil.a("BloodPressureReminderReceiver", "enter sendNotification ", Integer.valueOf(i));
        SharedPreferenceManager.e("BloodPressure", "update_time", System.currentTimeMillis());
        SharedPreferenceManager.b("BloodPressure", "content_type", i);
        SharedPreferenceManager.e("BloodPressure", "is_update_message", true);
        jdh.c().a(20220731);
        if (i == 1) {
            string = this.d.getResources().getString(R.string._2130846656_res_0x7f0223c0);
        } else if (i == 2) {
            string = this.d.getResources().getString(R.string._2130846657_res_0x7f0223c1);
        } else {
            string = i != 3 ? "" : this.d.getResources().getString(R.string._2130846658_res_0x7f0223c2);
        }
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("BloodPressureReminderReceiver", "sendNotification, content is empty");
            return;
        }
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setContentTitle(this.d.getResources().getString(R.string._2130846397_res_0x7f0222bd));
        xf_.setContentText(string);
        xf_.setStyle(new Notification.BigTextStyle().bigText(string));
        Intent intent = new Intent(this.d, (Class<?>) KnitBloodPressureActivity.class);
        intent.setPackage(this.d.getPackageName());
        intent.addFlags(268435456);
        intent.putExtra(KnitHealthDetailActivity.KEY_SUB_PAGE_INDEX, 1);
        intent.putExtra("biMessageType", i2);
        intent.putExtra("key_bundle_health_last_data_time", j);
        xf_.setContentIntent(PendingIntent.getActivity(this.d, 0, intent, 201326592));
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        jdh.c().xh_(20220731, xf_.build());
        LogUtil.a("BloodPressureReminderReceiver", "sendNotification, notificationId: ", 20220731);
    }
}
