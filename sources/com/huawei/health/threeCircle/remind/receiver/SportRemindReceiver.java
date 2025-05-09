package com.huawei.health.threeCircle.remind.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.threeCircle.remind.receiver.SportRemindReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gja;
import defpackage.gjz;
import defpackage.msp;
import defpackage.sqa;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Calendar;

/* loaded from: classes4.dex */
public class SportRemindReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("TC_SprtRmdRecv", "Receive sport remind broadcast-----------");
        if (intent != null) {
            LogUtil.a("TC_SprtRmdRecv", "intent action:", intent.getAction());
            final String[] stringArrayExtra = intent.getStringArrayExtra("remindType");
            if (stringArrayExtra == null || stringArrayExtra.length <= 0) {
                ReleaseLogUtil.d("R_TC_SprtRmdRecv", "remind type is empty.");
                return;
            }
            String str = stringArrayExtra[0];
            gja.a().d(str);
            boolean ekw_ = sqa.ekw_(intent);
            ReleaseLogUtil.e("TC_SprtRmdRecv", "onReceive isDifferentTimeZone ", Boolean.valueOf(ekw_), " type ", str);
            if (ekw_) {
                return;
            }
            final int intExtra = intent.getIntExtra("reportDate", 0);
            final long c = c(stringArrayExtra);
            ReleaseLogUtil.e("R_TC_SprtRmdRecv", "Receive remind:", Arrays.toString(stringArrayExtra), " reportDate:", Integer.valueOf(intExtra), "delayTime:", Long.valueOf(c));
            ThreadPoolManager.d().execute(new Runnable() { // from class: gjx
                @Override // java.lang.Runnable
                public final void run() {
                    SportRemindReceiver.this.a(stringArrayExtra, intExtra, c);
                }
            });
            return;
        }
        LogUtil.h("TC_SprtRmdRecv", "intent == null");
    }

    public /* synthetic */ void a(String[] strArr, final int i, long j) {
        for (final String str : strArr) {
            if (b(str)) {
                LogUtil.a("TC_SprtRmdRecv", "send remindType is ", str);
                HandlerCenter.d().e(new Runnable() { // from class: gju
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportRemindReceiver.d(i, str);
                    }
                }, j);
            }
        }
    }

    public static /* synthetic */ void d(int i, String str) {
        if (i == 0) {
            gja.a().c(str);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("reportDate", i);
        gja.a().aNf_(str, bundle);
    }

    private long c(String[] strArr) {
        for (String str : strArr) {
            if ("TodayAchievement".equals(str) || "LagEncourage".equals(str) || "OverGoal".equals(str)) {
                return 0L;
            }
        }
        int nextInt = msp.e().nextInt(170);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(12);
        int i2 = calendar.get(11);
        if ((i2 != 8 || i < 50) && (i2 != 9 || i > 10)) {
            return 0L;
        }
        return nextInt * 60000;
    }

    private boolean b(String str) {
        if (CommonUtil.cc()) {
            return true;
        }
        if ("ActiveWeek".equals(str) || "PerfectWeek".equals(str)) {
            return gjz.a();
        }
        if ("PerfectMonth".equals(str)) {
            return gjz.b();
        }
        return true;
    }
}
