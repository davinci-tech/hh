package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.main.stories.fitness.views.heartrate.DayHeartRateDoubleViewHorizontalDataObserverView;
import com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverBradycardiaAlarmView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverRestHRView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverWarningHRView;
import defpackage.koq;
import defpackage.nnl;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DayHeartRateDoubleViewHorizontalDataObserverView extends MultiViewHorizontalDataObserverView {
    private List<nnl> f;
    private Paint h;
    private float k;
    private List<nnl> m;

    public DayHeartRateDoubleViewHorizontalDataObserverView(Context context) {
        super(context);
        this.k = 0.0f;
        this.h = new Paint();
        this.m = new ArrayList(10);
        this.f = new ArrayList(10);
    }

    public DayHeartRateDoubleViewHorizontalDataObserverView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = 0.0f;
        this.h = new Paint();
        this.m = new ArrayList(10);
        this.f = new ArrayList(10);
    }

    public DayHeartRateDoubleViewHorizontalDataObserverView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = 0.0f;
        this.h = new Paint();
        this.m = new ArrayList(10);
        this.f = new ArrayList(10);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView
    protected MultiViewHorizontalDataObserverView.h d(List<ScrollChartHorizontalObserverHRView> list) {
        return new d(list);
    }

    class d extends MultiViewHorizontalDataObserverView.h {
        d(List<? extends View> list) {
            super(list);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView.h
        public void d() {
            if (e()) {
                return;
            }
            if (!DayHeartRateDoubleViewHorizontalDataObserverView.this.c(HwHealthChartHolder.LAYER_ID_REST_HR)) {
                if (!DayHeartRateDoubleViewHorizontalDataObserverView.this.c(HwHealthChartHolder.LAYER_ID_WARNING_HR)) {
                    if (DayHeartRateDoubleViewHorizontalDataObserverView.this.c(HwHealthChartHolder.LAYER_ID_BRADYCARDIA)) {
                        DayHeartRateDoubleViewHorizontalDataObserverView.this.c.disableFocusArea();
                        a();
                        return;
                    } else {
                        super.d();
                        return;
                    }
                }
                DayHeartRateDoubleViewHorizontalDataObserverView.this.c.disableFocusArea();
                a();
                return;
            }
            DayHeartRateDoubleViewHorizontalDataObserverView.this.c.disableManualReferenceLine();
            a();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView.h
        public void b(int i) throws MultiViewHorizontalDataObserverView.a {
            if (koq.b(DayHeartRateDoubleViewHorizontalDataObserverView.this.i, i)) {
                return;
            }
            HwHealthChartHolder.b bVar = DayHeartRateDoubleViewHorizontalDataObserverView.this.i.get(i);
            if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
                DayHeartRateDoubleViewHorizontalDataObserverView.this.j.c(true);
                DayHeartRateDoubleViewHorizontalDataObserverView.this.j.b(new MultiViewHorizontalDataObserverView.c.d(null, bVar));
                DayHeartRateDoubleViewHorizontalDataObserverView.this.c.setMarkerTimeShowFlag(false);
                DayHeartRateDoubleViewHorizontalDataObserverView.this.d();
                return;
            }
            DayHeartRateDoubleViewHorizontalDataObserverView.this.c.setMarkerTimeShowFlag(true);
            if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
                DayHeartRateDoubleViewHorizontalDataObserverView.this.j.c(true);
                DayHeartRateDoubleViewHorizontalDataObserverView.this.j.b(new MultiViewHorizontalDataObserverView.c.d(null, bVar));
                DayHeartRateDoubleViewHorizontalDataObserverView.this.c();
            } else {
                if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.j.c(true);
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.j.b(new MultiViewHorizontalDataObserverView.c.d(null, bVar));
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.e();
                    return;
                }
                super.b(i);
            }
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView.h
        public void b() {
            DayHeartRateDoubleViewHorizontalDataObserverView.this.h.setStrokeWidth(nsn.c(BaseApplication.getContext(), 2.0f));
            DayHeartRateDoubleViewHorizontalDataObserverView.this.h.setColor(Color.argb(255, 252, 49, 89));
            DayHeartRateDoubleViewHorizontalDataObserverView.this.h.setStyle(Paint.Style.STROKE);
            for (HwHealthChartHolder.b bVar : DayHeartRateDoubleViewHorizontalDataObserverView.this.i) {
                if (bVar != null && HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
                    HwHealthBaseBarLineDataSet fakeDataLayer = DayHeartRateDoubleViewHorizontalDataObserverView.this.c.fakeDataLayer(bVar);
                    c(bVar, fakeDataLayer);
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.b.put(bVar, fakeDataLayer);
                } else if (bVar != null && HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
                    HwHealthBaseBarLineDataSet fakeDataLayer2 = DayHeartRateDoubleViewHorizontalDataObserverView.this.c.fakeDataLayer(bVar);
                    c(bVar, fakeDataLayer2);
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.b.put(bVar, fakeDataLayer2);
                } else if (bVar != null && HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
                    HwHealthBaseBarLineDataSet fakeDataLayer3 = DayHeartRateDoubleViewHorizontalDataObserverView.this.c.fakeDataLayer(bVar);
                    c(bVar, fakeDataLayer3);
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.b.put(bVar, fakeDataLayer3);
                }
            }
            for (View view : this.d) {
                if (view instanceof ScrollChartHorizontalObserverRestHRView) {
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.e((ScrollChartHorizontalObserverRestHRView) view);
                }
                if (view instanceof ScrollChartHorizontalObserverWarningHRView) {
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.d((ScrollChartHorizontalObserverWarningHRView) view);
                }
                if (view instanceof ScrollChartHorizontalObserverBradycardiaAlarmView) {
                    DayHeartRateDoubleViewHorizontalDataObserverView.this.a((ScrollChartHorizontalObserverBradycardiaAlarmView) view);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        a(this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ScrollChartHorizontalObserverRestHRView scrollChartHorizontalObserverRestHRView) {
        scrollChartHorizontalObserverRestHRView.setOnReferenceChangeListener(new ScrollChartHorizontalObserverRestHRView.OnReferenceChange() { // from class: qao
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverRestHRView.OnReferenceChange
            public final void onReferenceChange(float f) {
                DayHeartRateDoubleViewHorizontalDataObserverView.this.a(f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ScrollChartHorizontalObserverWarningHRView scrollChartHorizontalObserverWarningHRView) {
        scrollChartHorizontalObserverWarningHRView.setOnFocusAreaChangeListener(new ScrollChartHorizontalObserverWarningHRView.OnFocusAreaChangeListener() { // from class: qas
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverWarningHRView.OnFocusAreaChangeListener
            public final void onFocusAreaChange(List list) {
                DayHeartRateDoubleViewHorizontalDataObserverView.this.a(list);
            }
        });
    }

    public /* synthetic */ void a(List list) {
        if (this.c == null || !this.c.isChartInNatureViewPosition()) {
            return;
        }
        e((List<nnl>) list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ScrollChartHorizontalObserverBradycardiaAlarmView scrollChartHorizontalObserverBradycardiaAlarmView) {
        scrollChartHorizontalObserverBradycardiaAlarmView.setOnFocusAreaChangeListener(new ScrollChartHorizontalObserverBradycardiaAlarmView.OnFocusAreaChangeListener() { // from class: qap
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverBradycardiaAlarmView.OnFocusAreaChangeListener
            public final void onFocusAreaChange(List list) {
                DayHeartRateDoubleViewHorizontalDataObserverView.this.c(list);
            }
        });
    }

    public /* synthetic */ void c(List list) {
        if (this.c == null || !this.c.isChartInNatureViewPosition()) {
            return;
        }
        b((List<nnl>) list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(float f) {
        this.k = f;
        if (this.j.d() && c(HwHealthChartHolder.LAYER_ID_REST_HR)) {
            if (this.k > 0.0f) {
                this.c.enableManualReferenceLine(Math.round(this.k), this.h, true);
            } else {
                this.c.enableManualReferenceLine(Integer.MIN_VALUE, this.h, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        e(this.m);
    }

    private void e(List<nnl> list) {
        List<nnl> list2 = this.m;
        if (list != list2) {
            list2.clear();
            this.m.addAll(list);
        }
        if (this.j.d() && c(HwHealthChartHolder.LAYER_ID_WARNING_HR)) {
            this.c.focusArea(this.m);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        b(this.f);
    }

    private void b(List<nnl> list) {
        List<nnl> list2 = this.f;
        if (list != list2) {
            list2.clear();
            this.f.addAll(list);
        }
        if (this.j.d() && c(HwHealthChartHolder.LAYER_ID_BRADYCARDIA)) {
            this.c.focusArea(this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        if (this.j == null || TextUtils.isEmpty(str) || this.j.c() == null || this.j.c().e() == null) {
            return false;
        }
        return str.equals(this.j.c().e().e());
    }
}
