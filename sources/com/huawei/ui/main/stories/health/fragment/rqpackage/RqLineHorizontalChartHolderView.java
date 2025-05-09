package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineHorizontalChartHolderView;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsj;
import defpackage.qjd;
import defpackage.qjl;
import defpackage.qjm;
import defpackage.ruf;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class RqLineHorizontalChartHolderView extends RqLineChartHolderView {
    private int aa;
    private RqHorizontalLineChart ab;
    private long ac;
    private boolean ad;
    private HealthTextView ae;
    private int ag;
    private HealthTextView ai;
    private HealthCalendar k;
    private Context l;
    private HwHealthLineDataSet p;
    private HealthTextView q;
    private DataInfos r;
    private HealthTextView s;
    private HwHealthLineDataSet t;
    private boolean u;
    private boolean v;
    private HealthTextView w;
    private HealthTextView x;
    private boolean y;
    private int z;

    public RqLineHorizontalChartHolderView(Context context) {
        this(context, null);
    }

    public RqLineHorizontalChartHolderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RqLineHorizontalChartHolderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = DataInfos.RunningWeekDetail;
        this.aa = 0;
        this.l = context;
        c();
    }

    private void c() {
        if (this.m == null) {
            LogUtil.b("Track_RqLineChartHolderView", "initHorizontalView mView is null.");
            return;
        }
        this.ae = (HealthTextView) this.m.findViewById(R.id.horizontal_state_text);
        this.ai = (HealthTextView) this.m.findViewById(R.id.rq_state_value);
        this.x = (HealthTextView) this.m.findViewById(R.id.horizontal_fitness_text);
        this.w = (HealthTextView) this.m.findViewById(R.id.rq_fitness_value);
        this.q = (HealthTextView) this.m.findViewById(R.id.horizontal_fatigue_text);
        this.s = (HealthTextView) this.m.findViewById(R.id.rq_fatigue_value);
        if (this.d != null) {
            this.d.setVisibility(8);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView
    public void a(String str, List<HwHealthMarkerView.a> list) {
        String str2;
        String b;
        String e;
        d(nom.h((int) this.ab.fetchMarkViewMinuteValue()));
        this.c.setText(str);
        str2 = "--";
        if (koq.b(list)) {
            LogUtil.h("Track_RqLineChartHolderView", "notifyView data is empty");
            e = "--";
            b = e;
        } else {
            String e2 = this.n.e(list.get(0).b);
            b = this.ad ? b(list) : "--";
            str2 = e2;
            e = this.y ? e(list) : "--";
        }
        d(list, str2, b, e);
    }

    private void d(List<HwHealthMarkerView.a> list, String str, String str2, String str3) {
        String str4 = "--";
        if ("--".equals(str)) {
            this.d.setText(str);
            this.f10185a.setVisibility(8);
            this.ai.setText(str);
            this.w.setText(str2);
            this.s.setText(str3);
            return;
        }
        if (list.size() > 0 && list.get(this.aa).b != null) {
            str4 = ruf.g(list.get(this.aa).b.getY());
        }
        this.f10185a.setVisibility(0);
        this.f10185a.setText(str4);
        this.ai.setText(str);
        this.w.setText(str2);
        this.s.setText(str3);
    }

    private String e(List<HwHealthMarkerView.a> list) {
        int i = 1;
        if (list.size() == 2) {
            this.u = true;
            this.v = false;
        }
        if (this.u && list.size() == 3) {
            i = 2;
        }
        return this.e.e(list.get(list.size() - i).b);
    }

    private String b(List<HwHealthMarkerView.a> list) {
        int i = 1;
        if (list.size() == 2) {
            this.v = true;
            this.u = false;
        }
        if (this.v && list.size() == 3) {
            i = 2;
        }
        return this.i.e(list.get(list.size() - i).b);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView, com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void initChart() {
        this.n = new qjd(this.l.getApplicationContext(), this.ag, this.z);
        if (this.z == 0) {
            this.n.a(true);
            this.ae.setVisibility(0);
            this.ai.setVisibility(0);
            this.f10185a.setVisibility(0);
        }
        if (this.z == 1) {
            this.n.d(true);
            this.x.setVisibility(0);
            this.w.setVisibility(0);
            this.f10185a.setVisibility(8);
        }
        if (this.z == 2) {
            this.n.b(true);
            this.q.setVisibility(0);
            this.s.setVisibility(0);
            this.f10185a.setVisibility(8);
        }
        if (this.ab == null) {
            this.ab = new RqHorizontalLineChart(this.l);
        }
        this.ab.setMoveToLastDataStamp(true);
        this.ab.setLayerType(1, null);
        a();
        this.n.addDataLayer((qjd) this.ab, this.r);
        this.o.clear();
        this.o.add(this.ab);
        this.ab.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: qjn
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public final void onRangeShow(int i, int i2) {
                RqLineHorizontalChartHolderView.this.c(i, i2);
            }
        });
        this.j.notifyDataSetChanged();
        this.ab.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: qjk
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list) {
                RqLineHorizontalChartHolderView.this.a(str, (List<HwHealthMarkerView.a>) list);
            }
        });
        setJumpToChartTime(this.ac);
        this.ab.acquireScrollAdapter().setBuffRang(0);
    }

    public /* synthetic */ void c(int i, int i2) {
        if (nsj.a(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
            d(i);
        }
        this.b.setText(this.ab.formatRangeText(i, i2));
    }

    public void setJumpToChartTime(long j) {
        this.ab.setShowRange(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(j)), this.ab.acquireScrollAdapter().acquireRange());
        this.ab.highlightValue(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(j)), false);
    }

    public void e(DataInfos dataInfos, int i, int i2, long j) {
        this.r = dataInfos;
        this.ag = i;
        this.z = i2;
        this.ac = j;
        initChart();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b(boolean z) {
        this.ad = z;
        this.x.setVisibility(z ? 0 : 8);
        this.w.setVisibility(z ? 0 : 8);
        setFitnessLeftMargin(this.ad);
        if (this.ad) {
            this.i = new qjd(this.l.getApplicationContext(), this.ag, 1);
            this.i.d(true);
            this.p = (HwHealthLineDataSet) this.i.addDataLayer((qjd) this.ab, this.r);
        } else {
            this.i.rmDataLayer(this.ab, this.p);
        }
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.ab.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(1 | acquireScrollAdapter.getFlag());
        this.ab.animateBorderYAuto();
        this.ab.refresh();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(boolean z) {
        this.y = z;
        this.q.setVisibility(z ? 0 : 8);
        this.s.setVisibility(z ? 0 : 8);
        setFatigueLeftMargin(z);
        if (this.y) {
            this.e = new qjd(this.l.getApplicationContext(), this.ag, 2);
            this.e.b(true);
            this.t = (HwHealthLineDataSet) this.e.addDataLayer((qjd) this.ab, this.r);
        } else {
            this.e.rmDataLayer(this.ab, this.t);
        }
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.ab.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(1 | acquireScrollAdapter.getFlag());
        this.ab.animateBorderYAuto();
        this.ab.refresh();
    }

    public void setFitnessLeftMargin(boolean z) {
        if (this.x.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.x.getLayoutParams();
            if (z) {
                layoutParams.setMarginStart((int) this.l.getResources().getDimension(R.dimen._2131363191_res_0x7f0a0577));
            } else {
                layoutParams.setMarginStart((int) this.l.getResources().getDimension(R.dimen._2131362481_res_0x7f0a02b1));
            }
            this.x.setLayoutParams(layoutParams);
        }
    }

    public void setFatigueLeftMargin(boolean z) {
        if (this.q.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.q.getLayoutParams();
            if (z) {
                layoutParams.setMarginStart((int) this.l.getResources().getDimension(R.dimen._2131363191_res_0x7f0a0577));
            } else {
                layoutParams.setMarginStart((int) this.l.getResources().getDimension(R.dimen._2131362481_res_0x7f0a02b1));
            }
            this.q.setLayoutParams(layoutParams);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView, com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setLeftClickEvent() {
        RqHorizontalLineChart rqHorizontalLineChart = this.ab;
        Objects.requireNonNull(rqHorizontalLineChart);
        rqHorizontalLineChart.scrollOnePageOlder(new qjm(this, rqHorizontalLineChart));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView, com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setRightClickEvent() {
        RqHorizontalLineChart rqHorizontalLineChart = this.ab;
        Objects.requireNonNull(rqHorizontalLineChart);
        rqHorizontalLineChart.scrollOnePageNewer(new qjl(this, rqHorizontalLineChart));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.ab.canScrollOlderPager()) {
            this.g.setVisibility(0);
            this.g.setClickable(true);
        } else {
            this.g.setVisibility(4);
            this.g.setClickable(false);
        }
        if (this.ab.canScrollNewerPager()) {
            this.f.setVisibility(0);
            this.f.setClickable(true);
        } else {
            this.f.setVisibility(4);
            this.f.setClickable(false);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView
    protected void a() {
        this.ab.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineHorizontalChartHolderView.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                RqLineHorizontalChartHolderView.this.e();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                RqLineHorizontalChartHolderView.this.e();
            }
        });
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView
    public void a(long j, HealthCalendar healthCalendar) {
        this.k = healthCalendar;
        this.ab.reflesh(j);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView
    public void d(int i) {
        LogUtil.a("Track_RqLineChartHolderView", "updateCalendar minute===", Integer.valueOf(i));
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.k == null) {
            this.k = new HealthCalendar();
        }
        this.k = this.k.transformFromCalendar(calendar);
    }
}
