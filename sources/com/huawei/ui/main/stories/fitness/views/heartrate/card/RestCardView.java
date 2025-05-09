package com.huawei.ui.main.stories.fitness.views.heartrate.card;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import defpackage.pxz;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class RestCardView extends HeartRateCardView implements IFocusObserverItem {
    private OnReferenceChange b;
    private DetailView d;
    private ICustomCalculator e;

    public interface OnReferenceChange {
        void onReferenceChange(float f);
    }

    public RestCardView(Context context, ObserveredClassifiedView observeredClassifiedView, String str) {
        super(context, observeredClassifiedView, str, context.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.b = null;
        this.d = null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        float calculate;
        ICustomCalculator iCustomCalculator = this.e;
        if (iCustomCalculator == null) {
            calculate = hwHealthBaseScrollBarLineChart.getShowDataAverage();
        } else {
            calculate = iCustomCalculator.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
        }
        int i3 = 0;
        if (calculate > 0.0f) {
            double d = calculate;
            setContentText(UnitUtil.e(d, 1, 0));
            i3 = Integer.parseInt(UnitUtil.e(d, 1, 0));
        } else {
            setContentText("--");
        }
        DetailView detailView = this.d;
        if (detailView != null) {
            detailView.c(i3);
        }
        OnReferenceChange onReferenceChange = this.b;
        if (onReferenceChange != null) {
            onReferenceChange.onReferenceChange(calculate);
        }
    }

    public void d(ICustomCalculator iCustomCalculator) {
        this.e = iCustomCalculator;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        if (this.d == null) {
            this.d = new DetailView(getContext());
        }
        return this.d;
    }

    public void setOnReferenceChangeListener(OnReferenceChange onReferenceChange) {
        this.b = onReferenceChange;
    }

    static class DetailView extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9999a;
        private ConstraintLayout b;
        private HealthTextView c;
        private HealthTextView e;

        DetailView(Context context) {
            super(context);
            b();
        }

        private void b() {
            inflate(getContext(), R.layout.focus_view_detail_rest_hr, this);
            this.f9999a = (HealthTextView) findViewById(R.id.text_rest_heartrate_tip);
            this.e = (HealthTextView) findViewById(R.id.text_ecg_measurement_reminder);
            this.b = (ConstraintLayout) findViewById(R.id.ecg_measurement_reminder_layout);
            this.c = (HealthTextView) findViewById(R.id.point1);
            this.f9999a.setText(getResources().getString(R$string.IDS_resting_heart_rate_details_string_new, 60, 100, 60));
        }

        public void c(int i) {
            if (i != 0 && ((i < 60 || i > 100) && pxz.d())) {
                this.e.setVisibility(0);
                this.b.setVisibility(0);
                this.c.setVisibility(0);
                String string = getResources().getString(R$string.IDS_heart_rate_ecg_view);
                pxz.a(getResources().getString(R$string.IDS_heart_rate_rest_ecg_tips, string), string, this.e, 0, pxz.j() ? 2 : 1);
                return;
            }
            this.e.setVisibility(8);
            this.b.setVisibility(8);
            this.c.setVisibility(8);
        }
    }
}
