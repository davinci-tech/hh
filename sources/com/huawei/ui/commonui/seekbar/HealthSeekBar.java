package com.huawei.ui.commonui.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.uikit.hwseekbar.widget.HwSeekBar;
import com.huawei.uikit.phone.hwseekbar.widget.HwSeekBar;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthSeekBar extends HwSeekBar {

    /* renamed from: a, reason: collision with root package name */
    private int f8947a;
    private boolean b;
    private Paint c;
    private List<Float> d;
    private float e;
    private int g;
    private HwSeekBar.OnSeekBarChangeListener h;
    private OnSeekBarChangeListener j;

    public interface OnSeekBarChangeListener {
        void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z);

        void onStartTrackingTouch(HealthSeekBar healthSeekBar);

        void onStopTrackingTouch(HealthSeekBar healthSeekBar);
    }

    public HealthSeekBar(Context context) {
        this(context, null);
    }

    public HealthSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.d = new ArrayList();
        this.c = new Paint();
        this.h = new HwSeekBar.OnSeekBarChangeListener() { // from class: com.huawei.ui.commonui.seekbar.HealthSeekBar.4
            @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(com.huawei.uikit.hwseekbar.widget.HwSeekBar hwSeekBar, int i2, boolean z) {
                if (HealthSeekBar.this.j != null) {
                    HealthSeekBar.this.j.onProgressChanged(HealthSeekBar.this, i2, z);
                }
            }

            @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(com.huawei.uikit.hwseekbar.widget.HwSeekBar hwSeekBar) {
                if (HealthSeekBar.this.j != null) {
                    HealthSeekBar.this.j.onStartTrackingTouch(HealthSeekBar.this);
                }
            }

            @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(com.huawei.uikit.hwseekbar.widget.HwSeekBar hwSeekBar) {
                if (HealthSeekBar.this.j != null) {
                    HealthSeekBar.this.j.onStopTrackingTouch(HealthSeekBar.this);
                }
                ViewClickInstrumentation.clickOnView(hwSeekBar);
            }
        };
        cGb_(context, attributeSet);
    }

    private void cGb_(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            LogUtil.h("CommonUI_HealthSeekBar", "initAttributes, the input parameter is incorrect.");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthSeekBar);
        this.e = obtainStyledAttributes.getDimension(R$styleable.HealthSeekBar_seekBarNodeRadius, 0.0f);
        this.c.setColor(obtainStyledAttributes.getColor(R$styleable.HealthSeekBar_seekBarNodeColor, 0));
        obtainStyledAttributes.recycle();
        this.c.setAntiAlias(true);
    }

    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar, android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setTouchable(boolean z) {
        this.b = z;
    }

    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar
    public void setTip(boolean z, int i, boolean z2) {
        super.setTip(z, i, z2);
    }

    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar
    public void setTipText(String str) {
        super.setTipText(str);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.j = onSeekBarChangeListener;
        super.setOnSeekBarChangeListener(this.h);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.g = (i - getPaddingStart()) - getPaddingEnd();
        this.f8947a = i2;
    }

    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (koq.b(this.d) || this.d.size() < 2) {
            return;
        }
        for (Float f : this.d) {
            if (f.floatValue() != 0.0f) {
                canvas.drawCircle((this.g * f.floatValue()) + getPaddingStart(), (((this.f8947a - getPaddingBottom()) - getPaddingTop()) / 2) + getPaddingTop(), this.e, this.c);
            }
        }
    }

    public void setNodeList(List<Float> list) {
        if (koq.b(list)) {
            LogUtil.h("CommonUI_HealthSeekBar", "setMotions nodePercentageList is empty.");
            return;
        }
        this.d.clear();
        this.d = list;
        invalidate();
    }
}
