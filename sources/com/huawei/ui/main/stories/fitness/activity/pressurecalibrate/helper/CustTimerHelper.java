package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.psm;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class CustTimerHelper {

    /* renamed from: a, reason: collision with root package name */
    private Context f9875a = BaseApplication.getContext();
    private List<Long> b = null;
    private CountDownTimer d;
    private OnFinishListener e;

    public interface OnFinishListener {
        void finish();
    }

    public CustTimerHelper(int i, int i2) {
        this.d = new CountDownTimer((i * 1000) + 15, i2 * 1000) { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper.CustTimerHelper.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                LogUtil.a("PressureMeasureMessage", "CustTimerHelper current = ");
                if (CustTimerHelper.this.b == null) {
                    CustTimerHelper.this.b = new ArrayList(16);
                    LogUtil.a("PressureMeasureMessage", "mTimeFlagList instance");
                }
                CustTimerHelper.this.b.add(Long.valueOf(System.currentTimeMillis()));
                if (psm.e().y() > 0) {
                    LogUtil.a("PressureMeasureMessage", "first time !!!");
                    psm.e().f(0);
                    return;
                }
                CustTimerHelper custTimerHelper = CustTimerHelper.this;
                if (custTimerHelper.a((List<Long>) custTimerHelper.b)) {
                    CustTimerHelper.this.e();
                    return;
                }
                LogUtil.a("PressureMeasureMessage", "this is last time ,time = " + System.currentTimeMillis());
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (CustTimerHelper.this.b != null) {
                    CustTimerHelper.this.b.clear();
                    CustTimerHelper.this.b = null;
                }
                if (CustTimerHelper.this.e != null) {
                    CustTimerHelper.this.e.finish();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int size = psm.e().m().size();
        LogUtil.a("PressureMeasureMessage", "PressureUtil.getInstance().mListBack.size() == " + size);
        int p = psm.e().p();
        LogUtil.a("PressureMeasureMessage", "PressureUtil.getInstance().mListBack.size() sum == " + p);
        if (size <= p) {
            if (psm.e().q() > 0) {
                psm.e().h(0);
                Intent intent = new Intent("com.huawei.ui.pressure.measure.suddenness");
                intent.setPackage(this.f9875a.getPackageName());
                this.f9875a.sendOrderedBroadcast(intent, LocalBroadcast.c);
                Intent intent2 = new Intent("com.huawei.ui.pressure.measure.calibrate.stop");
                intent2.setPackage(this.f9875a.getPackageName());
                this.f9875a.sendOrderedBroadcast(intent2, LocalBroadcast.c);
                LogUtil.a("PressureMeasureMessage", "data is lose ,Timer is STOP");
                psm.e().d(true);
                psm.e().h(false);
                psm.e().g(false);
                psm.e().a(true);
                return;
            }
            return;
        }
        psm.e().a(size);
    }

    public CountDownTimer dtu_() {
        return this.d;
    }

    public void dtv_(CountDownTimer countDownTimer) {
        this.d = countDownTimer;
    }

    public void c() {
        this.d.start();
    }

    public void a(OnFinishListener onFinishListener) {
        this.e = onFinishListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<Long> list) {
        if (list != null && list.size() > 1) {
            long longValue = list.get(list.size() - 1).longValue();
            long longValue2 = list.get(list.size() - 2).longValue();
            int i = (int) (longValue - longValue2);
            LogUtil.a("PressureMeasureMessage", "lastTime = ", Long.valueOf(longValue), ", lastTwoTime = ", Long.valueOf(longValue2), ", duringTime = ", Integer.valueOf(i));
            if (i > 11500) {
                return true;
            }
        }
        return false;
    }
}
