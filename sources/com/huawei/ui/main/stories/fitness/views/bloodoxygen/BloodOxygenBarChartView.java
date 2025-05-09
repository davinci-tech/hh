package com.huawei.ui.main.stories.fitness.views.bloodoxygen;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.main.stories.BarChartRender;
import com.huawei.ui.main.stories.fitness.views.bloodoxygen.BloodOxygenBarChartRenderer;
import com.huawei.ui.main.stories.fitness.views.bloodoxygen.BloodOxygenBarChartView;
import defpackage.pyk;

/* loaded from: classes9.dex */
public class BloodOxygenBarChartView extends HwHealthBarChart {

    /* renamed from: a, reason: collision with root package name */
    private OnDataChangedListener f9952a;
    private OnMostChangedListener e;

    public interface OnDataChangedListener {
        void onDataChangedListener(float f, float f2);
    }

    public interface OnMostChangedListener {
        void onMostChangedListener(float f, float f2);
    }

    public BloodOxygenBarChartView(Context context) {
        super(context);
        c();
    }

    private void c() {
        LogUtil.b("BloodOxygenBarChartView", "BloodOxygenBarChartView initStyle!");
        this.mRenderer = new BloodOxygenBarChartRenderer(this, this, this.mAnimator, this.mViewPortHandler);
        this.mAxisRendererFirstParty = new pyk(getContext(), this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new pyk(getContext(), this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        ((BloodOxygenBarChartRenderer) this.mRenderer).d(new BarChartRender.OnTimeChangedListener() { // from class: pyj
            @Override // com.huawei.ui.main.stories.BarChartRender.OnTimeChangedListener
            public final void onTimeChangedListener(float f, float f2) {
                BloodOxygenBarChartView.this.e(f, f2);
            }
        });
        ((BloodOxygenBarChartRenderer) this.mRenderer).d(new BloodOxygenBarChartRenderer.OnMostValueChangedListener() { // from class: pyo
            @Override // com.huawei.ui.main.stories.fitness.views.bloodoxygen.BloodOxygenBarChartRenderer.OnMostValueChangedListener
            public final void onMostValueChangedListener(float f, float f2) {
                BloodOxygenBarChartView.this.b(f, f2);
            }
        });
    }

    public /* synthetic */ void e(float f, float f2) {
        OnDataChangedListener onDataChangedListener = this.f9952a;
        if (onDataChangedListener != null) {
            onDataChangedListener.onDataChangedListener(f, f2);
        }
    }

    public /* synthetic */ void b(float f, float f2) {
        OnMostChangedListener onMostChangedListener = this.e;
        if (onMostChangedListener != null) {
            onMostChangedListener.onMostChangedListener(f, f2);
        }
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.f9952a = onDataChangedListener;
    }

    public void setOnMostChangedListener(OnMostChangedListener onMostChangedListener) {
        this.e = onMostChangedListener;
    }
}
