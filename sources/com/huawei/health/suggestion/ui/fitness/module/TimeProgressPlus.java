package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.util.StaticHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class TimeProgressPlus extends RelativeLayout {
    private static final String e = "Suggestion_TimeProgressPlus";

    /* renamed from: a, reason: collision with root package name */
    private d f3191a;
    private float b;
    private HealthTextView c;
    private HealthTextView d;
    private TimeProgress f;
    private HealthTextView g;
    private OnTimeCountFinishListener i;

    public interface OnTimeCountFinishListener {
        void onTimeCountFinished();

        void setCountDownResidueSec(int i);
    }

    static /* synthetic */ float b(TimeProgressPlus timeProgressPlus) {
        float f = timeProgressPlus.b;
        timeProgressPlus.b = f - 1.0f;
        return f;
    }

    static class d extends StaticHandler<TimeProgressPlus> {
        d(TimeProgressPlus timeProgressPlus) {
            super(timeProgressPlus);
        }

        @Override // com.huawei.health.suggestion.util.StaticHandler
        /* renamed from: aEv_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(TimeProgressPlus timeProgressPlus, Message message) {
            if (timeProgressPlus == null) {
                LogUtil.h(TimeProgressPlus.e, "handleMessage TimeProgressPlus is null");
                return;
            }
            TimeProgressPlus.b(timeProgressPlus);
            timeProgressPlus.setAutoProgress((int) timeProgressPlus.b);
            if (timeProgressPlus.b >= 0.0f) {
                timeProgressPlus.i.setCountDownResidueSec((int) timeProgressPlus.b);
                sendEmptyMessageDelayed(1, 1000L);
                return;
            }
            timeProgressPlus.b = -1.0f;
            if (timeProgressPlus.i != null) {
                timeProgressPlus.i.setCountDownResidueSec(0);
                timeProgressPlus.i.onTimeCountFinished();
            }
        }
    }

    public TimeProgressPlus(Context context) {
        super(context);
        this.b = -1.0f;
        this.f3191a = new d(this);
    }

    public TimeProgressPlus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = -1.0f;
        this.f3191a = new d(this);
    }

    public TimeProgressPlus(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = -1.0f;
        this.f3191a = new d(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater from = LayoutInflater.from(getContext());
        setGravity(17);
        from.inflate(R.layout.sug_coach_time_progress, this);
        e();
    }

    private void e() {
        this.d = (HealthTextView) findViewById(R.id.sug_coach_fg_);
        this.f = (TimeProgress) findViewById(R.id.sug_round_tp);
        this.c = (HealthTextView) findViewById(R.id.sug_coach_motionc);
        this.g = (HealthTextView) findViewById(R.id.sug_coach_motion_totle);
    }

    private void h() {
        if (this.d.getVisibility() == 0) {
            this.d.setVisibility(8);
            this.g.setVisibility(8);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void d(OnTimeCountFinishListener onTimeCountFinishListener) {
        this.i = onTimeCountFinishListener;
    }

    public TimeProgressPlus b(int i) {
        this.f.setVisibility(8);
        h();
        float f = i;
        this.b = f;
        this.c.setText(UnitUtil.e(i, 1, 0));
        this.f.b(f);
        this.f.d(f);
        this.f3191a.sendEmptyMessageDelayed(1, 1000L);
        e(1.0f);
        return this;
    }

    public void setAutoProgress(int i) {
        e(1.0f);
        this.f.setVisibility(8);
        float f = i;
        this.b = f;
        this.f.d(f);
        this.c.setText(UnitUtil.e(i, 1, 0));
    }

    public TimeProgressPlus b() {
        this.f3191a.removeMessages(1);
        return this;
    }

    public TimeProgressPlus d() {
        this.f3191a.removeMessages(1);
        return this;
    }

    public TimeProgressPlus c() {
        this.f3191a.sendEmptyMessageDelayed(1, 1000L);
        return this;
    }

    private void e(float f) {
        this.c.setTextSize(1, 18.0f * f);
        this.g.setTextSize(1, f * 13.0f);
    }
}
