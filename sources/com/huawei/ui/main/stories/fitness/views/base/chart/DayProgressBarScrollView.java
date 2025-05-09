package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity;
import com.huawei.ui.main.stories.fitness.views.base.chart.DayProgressBarScrollView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import defpackage.efa;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.pvm;
import health.compact.a.UnitUtil;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class DayProgressBarScrollView extends RelativeLayout implements ScrollChartObserverTotalDataView.OnDataChange {

    /* renamed from: a, reason: collision with root package name */
    private int f9935a;
    private int b;
    private final Context c;
    private final HealthProgressBar d;
    private View e;
    private final AtomicInteger f;
    private float g;

    public DayProgressBarScrollView(Context context) {
        super(context);
        this.f = new AtomicInteger();
        this.g = -1.0f;
        this.f9935a = -1;
        this.c = context;
        View inflate = View.inflate(context, R.layout.day_step_progress_layout, this);
        this.e = inflate.findViewById(R.id.day_progress_layout);
        HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.day_step_progressbar);
        this.d = healthProgressBar;
        findViewById(R.id.kaka_bubble).setVisibility(4);
        findViewById(R.id.kaka_list_layout).setVisibility(8);
        if (efa.c()) {
            ViewGroup.LayoutParams layoutParams = healthProgressBar.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMarginStart(((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                marginLayoutParams.setMarginEnd(((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
            }
        }
        healthProgressBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: pyi
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                DayProgressBarScrollView.this.d();
            }
        });
        LogUtil.a("DayProgressBarScrollView", "DayProgressBarScrollView mActivity ", context);
    }

    public /* synthetic */ void d() {
        if (this.f9935a > 0) {
            return;
        }
        int width = this.d.getWidth();
        this.f9935a = width;
        if (width > 0) {
            this.b = Math.round((this.d.getHeight() * 100.0f) / this.f9935a);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.OnDataChange
    public void onChange(float f) {
        e(f);
    }

    public void e(int i) {
        this.f.set(i);
        e(this.g);
    }

    private void e(float f) {
        int i;
        this.g = f;
        int i2 = this.f.get();
        LogUtil.a("DayProgressBarScrollView", "update mValue ", Float.valueOf(this.g), " target ", Integer.valueOf(i2));
        int i3 = 0;
        if (this.c instanceof FitnessSportIntensityDetailActivity) {
            this.d.setProgressDrawable(nsf.cKq_(R.drawable._2131431145_res_0x7f0b0ee9));
            FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity = (FitnessSportIntensityDetailActivity) this.c;
            fitnessSportIntensityDetailActivity.a((int) this.g, i2);
            fitnessSportIntensityDetailActivity.d((int) this.g, i2);
            i = R.plurals._2130903525_res_0x7f0301e5;
        } else {
            i = 0;
        }
        if (this.c instanceof FitnessCalorieDetailActivity) {
            this.d.setProgressDrawable(nsf.cKq_(R.drawable._2131431144_res_0x7f0b0ee8));
            FitnessCalorieDetailActivity fitnessCalorieDetailActivity = (FitnessCalorieDetailActivity) this.c;
            fitnessCalorieDetailActivity.a(this.g, i2);
            fitnessCalorieDetailActivity.c(this.g, i2);
            i = R.plurals._2130903523_res_0x7f0301e3;
        }
        if (i2 > 0) {
            float f2 = this.g;
            if (f2 > 0.0f) {
                int d = pvm.d(f2, i2);
                LogUtil.a("DayProgressBarScrollView", "update percent ", Integer.valueOf(d));
                i3 = Math.max(d, this.b);
            }
        }
        this.d.setProgress(i3);
        c(i, i2, i3);
    }

    private void c(int i, int i2, int i3) {
        jcf.bEB_(this.e, nsf.a(i, i2, Integer.valueOf((int) this.g), Integer.valueOf(i2), UnitUtil.e(i3, 2, 0)), false);
    }
}
