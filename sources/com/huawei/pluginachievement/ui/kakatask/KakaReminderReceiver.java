package com.huawei.pluginachievement.ui.kakatask;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.ui.AchieveKaKaActivity;
import defpackage.gmz;
import defpackage.jdh;
import defpackage.mct;
import defpackage.mcx;
import defpackage.nsn;
import defpackage.sqa;
import java.util.Calendar;

/* loaded from: classes9.dex */
public class KakaReminderReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (sqa.ekw_(intent)) {
            LogUtil.h("KakaReminderReceiver", "KakaReminderReceiver isDifferentTimeZone.");
            return;
        }
        String stringExtra = intent.getStringExtra("reminderHuid");
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        LogUtil.a("KakaReminderReceiver", "onReceive time ", Long.valueOf(System.currentTimeMillis()));
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(accountInfo) || !stringExtra.equals(accountInfo)) {
            return;
        }
        String c = gmz.d().c(403);
        LogUtil.a("KakaReminderReceiver", "onReceive: kaKaReminderStatus -> " + c);
        if (String.valueOf(false).equals(c)) {
            return;
        }
        if (!mcx.d(mct.b(context, "kakaLastCheckInTime")) || TextUtils.isEmpty(mct.b(context, "kakaLastCheckInTime"))) {
            e(context);
        }
    }

    private void e(Context context) {
        int i = Calendar.getInstance().get(11);
        LogUtil.a("KakaReminderReceiver", "setNotify hour ", Integer.valueOf(i));
        if (i <= 9 || i >= 22) {
            return;
        }
        c(context);
    }

    private void c(Context context) {
        jdh.c().a(20204000);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        Resources resources = BaseApplication.getContext().getResources();
        xf_.setContentTitle(resources.getString(R.string._2130841008_res_0x7f020db0)).setContentText(resources.getString(R.string._2130841009_res_0x7f020db1));
        Intent intent = new Intent(context, (Class<?>) AchieveKaKaActivity.class);
        intent.putExtra(KakaConstants.KAKA_FROM_NOTIFICATION_KEY, true);
        xf_.setContentIntent(PendingIntent.getActivity(context, 0, intent, 201326592));
        xf_.setAutoCancel(true);
        jdh.c().xh_(20204000, xf_.build());
        LogUtil.a("KakaReminderReceiver", "setNotify success");
    }
}
