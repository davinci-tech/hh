package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.ui.commonui.R$color;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class LongMediaProgress extends AppCompatSeekBar {

    /* renamed from: a, reason: collision with root package name */
    private Paint f3183a;
    private List<Motion> b;
    private int c;
    private int d;
    private boolean e;
    private List<VideoSegment> f;
    private float i;

    public LongMediaProgress(Context context) {
        this(context, null);
    }

    public LongMediaProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LongMediaProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList(16);
        this.f = new ArrayList(16);
        this.c = 3;
        this.e = false;
        a();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.c = nsn.c(getContext(), this.c);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = i;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar
    public void setMax(int i) {
        super.setMax(i);
        invalidate();
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setTouchable(boolean z) {
        this.e = z;
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<VideoSegment> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                float startTime = (this.d / this.i) * r1.getStartTime();
                if (startTime != 0.0f) {
                    float f = this.c / 2.0f;
                    canvas.drawCircle(startTime, f, f, this.f3183a);
                }
            }
        }
    }

    public void setMotions(List<Motion> list) {
        if (list == null) {
            return;
        }
        this.i = 0.0f;
        this.b.clear();
        this.f.clear();
        this.b.addAll(list);
        for (Motion motion : list) {
            if (motion != null && motion.getVideoSegments() != null && !koq.b(motion.getVideoSegments(), 0)) {
                VideoSegment videoSegment = motion.getVideoSegments().get(0);
                this.i += videoSegment.getDuration() * 1000;
                this.f.add(videoSegment);
            }
        }
        invalidate();
    }

    public void setTotalTime(float f) {
        this.i = f;
        invalidate();
    }

    private void a() {
        if (this.f3183a == null) {
            this.f3183a = new Paint();
        }
        this.f3183a.setAntiAlias(true);
        this.f3183a.setColor(BaseApplication.getContext().getResources().getColor(R$color.common_white_50alpha));
    }
}
