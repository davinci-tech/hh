package com.huawei.ui.main.stories.fitness.views.heartrate.card;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.DayCardContainerView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.RestCardView;
import defpackage.nnl;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DayCardContainerView extends CardContainerView {
    private final List<nnl> g;
    private final Paint h;
    private final List<nnl> i;
    private float j;

    public DayCardContainerView(Context context, ObserveredClassifiedView observeredClassifiedView) {
        super(context, observeredClassifiedView);
        this.j = 0.0f;
        this.h = new Paint();
        this.i = new ArrayList(16);
        this.g = new ArrayList(16);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView
    protected CardContainerView.b b(List<ScrollChartObserverView> list) {
        return new a(list);
    }

    class a extends CardContainerView.b {
        a(List<ScrollChartObserverView> list) {
            super(list);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView.b
        public void b() {
            if (!DayCardContainerView.this.b.b || DayCardContainerView.this.b.d == null) {
                return;
            }
            CardContainerView.d.C0264d c0264d = DayCardContainerView.this.b.d;
            if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(c0264d.b.e())) {
                DayCardContainerView.this.mHost.disableManualReferenceLine();
                DayCardContainerView.this.b.b = false;
                DayCardContainerView.this.b.d = null;
            } else if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(c0264d.b.e())) {
                DayCardContainerView.this.mHost.disableFocusArea();
                DayCardContainerView.this.b.b = false;
                DayCardContainerView.this.b.d = null;
            } else {
                if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(c0264d.b.e())) {
                    DayCardContainerView.this.mHost.disableFocusArea();
                    DayCardContainerView.this.b.b = false;
                    DayCardContainerView.this.b.d = null;
                    return;
                }
                super.b();
            }
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView.b
        public void a(int i) throws CardContainerView.c {
            if (i < 0 || DayCardContainerView.this.d.size() <= i) {
                return;
            }
            HwHealthChartHolder.b bVar = DayCardContainerView.this.d.get(i);
            if (DayCardContainerView.this.mHost != null) {
                DayCardContainerView.this.mHost.selectDataLayerId(bVar.e());
            }
            if (!HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
                if (DayCardContainerView.this.mHost != null) {
                    DayCardContainerView.this.mHost.setMarkerTimeShowFlag(true);
                }
                if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
                    DayCardContainerView.this.b.b = true;
                    DayCardContainerView.this.b.d = new CardContainerView.d.C0264d(null, bVar);
                    if (DayCardContainerView.this.mHost.isChartInNatureViewPosition()) {
                        DayCardContainerView.this.a();
                        return;
                    }
                    return;
                }
                if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
                    DayCardContainerView.this.b.b = true;
                    DayCardContainerView.this.b.d = new CardContainerView.d.C0264d(null, bVar);
                    if (DayCardContainerView.this.mHost.isChartInNatureViewPosition()) {
                        DayCardContainerView.this.d();
                        return;
                    }
                    return;
                }
                super.a(i);
                return;
            }
            DayCardContainerView.this.b.b = true;
            DayCardContainerView.this.b.d = new CardContainerView.d.C0264d(null, bVar);
            if (DayCardContainerView.this.mHost != null) {
                DayCardContainerView.this.mHost.setMarkerTimeShowFlag(false);
            }
            DayCardContainerView.this.c();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView.b
        public void a() {
            DayCardContainerView.this.h.setStrokeWidth(nsn.c(BaseApplication.getContext(), 2.0f));
            DayCardContainerView.this.h.setColor(Color.argb(255, 252, 49, 89));
            DayCardContainerView.this.h.setStyle(Paint.Style.STROKE);
            for (HwHealthChartHolder.b bVar : DayCardContainerView.this.d) {
                if (bVar != null && HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
                    HwHealthBaseBarLineDataSet fakeDataLayer = DayCardContainerView.this.mHost.fakeDataLayer(bVar);
                    b(bVar, fakeDataLayer);
                    DayCardContainerView.this.e.put(bVar, fakeDataLayer);
                } else if (bVar != null && HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
                    HwHealthBaseBarLineDataSet fakeDataLayer2 = DayCardContainerView.this.mHost.fakeDataLayer(bVar);
                    b(bVar, fakeDataLayer2);
                    DayCardContainerView.this.e.put(bVar, fakeDataLayer2);
                } else if (bVar != null && HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
                    HwHealthBaseBarLineDataSet fakeDataLayer3 = DayCardContainerView.this.mHost.fakeDataLayer(bVar);
                    b(bVar, fakeDataLayer3);
                    DayCardContainerView.this.e.put(bVar, fakeDataLayer3);
                }
            }
            for (ScrollChartObserverView scrollChartObserverView : this.e) {
                if (scrollChartObserverView instanceof RestCardView) {
                    DayCardContainerView.this.b((RestCardView) scrollChartObserverView);
                } else if (scrollChartObserverView instanceof ReminderCardView) {
                    DayCardContainerView.this.a((ReminderCardView) scrollChartObserverView);
                } else {
                    LogUtil.a("DayCardContainerView", "view not instance of above.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        b(this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(float f) {
        this.j = f;
        if (this.b.b && HwHealthChartHolder.LAYER_ID_REST_HR.equals(this.b.d.b.e())) {
            if (this.j > 0.0f) {
                this.mHost.enableManualReferenceLine(Math.round(this.j), this.h, true);
            } else {
                this.mHost.enableManualReferenceLine(Integer.MIN_VALUE, this.h, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RestCardView restCardView) {
        restCardView.setOnReferenceChangeListener(new RestCardView.OnReferenceChange() { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.card.DayCardContainerView.1
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.RestCardView.OnReferenceChange
            public void onReferenceChange(float f) {
                DayCardContainerView.this.b(f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final ReminderCardView reminderCardView) {
        reminderCardView.setOnFocusAreaChangeListener(new ReminderCardView.OnFocusAreaChangeListener() { // from class: qaw
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView.OnFocusAreaChangeListener
            public final void onFocusAreaChange(List list) {
                DayCardContainerView.this.e(reminderCardView, list);
            }
        });
    }

    public /* synthetic */ void e(ReminderCardView reminderCardView, List list) {
        if (this.mHost.isChartInNatureViewPosition()) {
            if (reminderCardView.b()) {
                c((List<nnl>) list);
            } else {
                e((List<nnl>) list);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        e(this.i);
    }

    private void e(List<nnl> list) {
        List<nnl> list2 = this.i;
        if (list != list2) {
            list2.clear();
            this.i.addAll(list);
        }
        if (this.b.b && HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(this.b.d.b.e())) {
            this.mHost.focusArea(this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        c(this.g);
    }

    private void c(List<nnl> list) {
        List<nnl> list2 = this.g;
        if (list != list2) {
            list2.clear();
            this.g.addAll(list);
        }
        if (this.b.b && HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(this.b.d.b.e())) {
            this.mHost.focusArea(this.g);
        }
    }
}
