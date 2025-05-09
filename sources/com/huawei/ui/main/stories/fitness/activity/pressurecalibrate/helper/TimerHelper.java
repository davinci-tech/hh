package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper;

import android.os.CountDownTimer;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class TimerHelper {

    /* renamed from: a, reason: collision with root package name */
    private OnFinishListener f9876a;
    private CountDownTimer e;

    public interface OnFinishListener {
        void finish();
    }

    public TimerHelper(int i, int i2, final int i3) {
        this.e = new CountDownTimer(i * 1000, i2 * 1000) { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper.TimerHelper.5
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                LogUtil.a("PressureMeasureMessage", "flag=" + i3);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (TimerHelper.this.f9876a != null) {
                    TimerHelper.this.f9876a.finish();
                }
            }
        };
    }

    public void dtx_(CountDownTimer countDownTimer) {
        this.e = countDownTimer;
    }

    public CountDownTimer dtw_() {
        return this.e;
    }

    public void d() {
        LogUtil.a("PressureMeasureMessage", "TimerHelper start");
        this.e.start();
    }

    public void e(OnFinishListener onFinishListener) {
        LogUtil.a("PressureMeasureMessage", "TimerHelper setOnFinishListener");
        this.f9876a = onFinishListener;
    }
}
