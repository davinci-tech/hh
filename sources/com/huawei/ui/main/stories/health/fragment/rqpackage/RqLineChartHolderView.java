package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView;
import com.huawei.up.model.UserInfomation;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsj;
import defpackage.qac;
import defpackage.qjd;
import defpackage.qjf;
import defpackage.qjj;
import defpackage.qrv;
import defpackage.ruf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class RqLineChartHolderView extends LinearLayout implements RqViewInterface, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    protected HealthTextView f10185a;
    protected HealthTextView b;
    protected HealthTextView c;
    protected HealthTextView d;
    protected qjd e;
    protected ImageView f;
    protected ImageView g;
    protected HealthViewPager h;
    protected qjd i;
    protected qac j;
    private HealthCalendar k;
    private Context l;
    protected View m;
    protected qjd n;
    protected ArrayList<View> o;
    private int p;
    private long q;
    private DataInfos r;
    private StartCalendarViewListener s;
    private HwHealthCommonLineChart t;
    private int v;

    public interface StartCalendarViewListener {
        void onStartCalendarView();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setDateRange(String str, String str2, int i) {
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setExactDateAndData(String str, int i, int i2) {
    }

    public RqLineChartHolderView(Context context) {
        this(context, null);
    }

    public RqLineChartHolderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RqLineChartHolderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = DataInfos.RunningMonthDetail;
        this.s = null;
        this.l = context;
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.l).inflate(R.layout.rq_line_chart_view, this);
        this.m = inflate;
        this.h = (HealthViewPager) inflate.findViewById(R.id.rq_viewpager);
        this.g = (ImageView) this.m.findViewById(R.id.rq_line_arrow_left);
        this.f = (ImageView) this.m.findViewById(R.id.rq_line_arrow_right);
        this.g.setOnClickListener(this);
        this.f.setOnClickListener(this);
        HealthTextView healthTextView = (HealthTextView) this.m.findViewById(R.id.rq_date_range);
        this.b = healthTextView;
        healthTextView.setOnClickListener(this);
        this.f10185a = (HealthTextView) this.m.findViewById(R.id.rq_cursor_describe);
        this.d = (HealthTextView) this.m.findViewById(R.id.rq_cursor_value);
        this.c = (HealthTextView) this.m.findViewById(R.id.rq_cursor_time);
        setLeftAndRightImage();
        initViewPager();
    }

    public void setWidthMatchParent() {
        ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.h.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setLeftAndRightImage() {
        if (LanguageUtil.bc(this.l)) {
            this.g.setBackground(ContextCompat.getDrawable(this.l, R.drawable._2131427831_res_0x7f0b01f7));
            this.f.setBackground(ContextCompat.getDrawable(this.l, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.g.setBackground(ContextCompat.getDrawable(this.l, R.drawable._2131427825_res_0x7f0b01f1));
            this.f.setBackground(ContextCompat.getDrawable(this.l, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    public void a(String str, List<HwHealthMarkerView.a> list) {
        String e;
        d(nom.h((int) this.t.fetchMarkViewMinuteValue()));
        this.c.setText(str);
        String str2 = "--";
        if (koq.b(list)) {
            LogUtil.h("Track_RqLineChartHolderView", "notifyView data is empty");
            e = "--";
        } else {
            e = this.n.e(list.get(list.size() - 1).b);
        }
        if ("--".equals(e)) {
            this.d.setText(e);
            this.f10185a.setVisibility(8);
            return;
        }
        float a2 = (float) UnitUtil.a(list.get(list.size() - 1).b.getY(), 1);
        if (a2 != 0.0f && this.v == 2) {
            str2 = c((int) a2);
        }
        if (this.v == 1) {
            str2 = ruf.g(a2);
        }
        if (a2 != 0.0f && this.v == 0) {
            if (getGender() == 0) {
                str2 = ruf.f(a2);
            } else {
                str2 = ruf.e(a2);
            }
        }
        if (this.p == 0) {
            this.f10185a.setVisibility(0);
        } else {
            this.f10185a.setVisibility(8);
        }
        this.f10185a.setText(str2);
        this.d.setText(e);
    }

    private int getGender() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        return userInfo.getGender();
    }

    private String c(int i) {
        return qrv.d(qrv.b(i, qrv.b()));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void initViewPager() {
        this.o = new ArrayList<>();
        this.j = new qac(this.o);
        this.h.setIsCompatibleScrollView(true);
        this.h.setIsScroll(false);
        this.h.setAdapter(this.j);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void initChart() {
        this.n = new qjd(this.l.getApplicationContext(), this.v, this.p);
        if (this.t == null) {
            HwHealthCommonLineChart hwHealthCommonLineChart = new HwHealthCommonLineChart(this.l);
            this.t = hwHealthCommonLineChart;
            hwHealthCommonLineChart.setLayerType(1, null);
            this.t.setMoveToLastDataStamp(true);
            a();
            this.n.addDataLayer((qjd) this.t, this.r);
            this.o.add(this.t);
            this.j.notifyDataSetChanged();
            this.t.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: qjh
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                public final void onRangeShow(int i, int i2) {
                    RqLineChartHolderView.this.a(i, i2);
                }
            });
            this.t.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: qji
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                public final void onTextChanged(String str, List list) {
                    RqLineChartHolderView.this.a(str, (List<HwHealthMarkerView.a>) list);
                }
            });
            this.t.reflesh(System.currentTimeMillis());
            if (this.v != 2) {
                this.t.acquireScrollAdapter().setBuffRang(0);
            }
        }
    }

    public /* synthetic */ void a(int i, int i2) {
        long j = i;
        if (nsj.a(TimeUnit.MINUTES.toMillis(j), TimeUnit.MINUTES.toMillis(i2))) {
            d(i);
        }
        this.q = TimeUnit.MINUTES.toMillis(j);
        this.b.setText(this.t.formatRangeText(i, i2));
    }

    public long getStartTime() {
        return this.q;
    }

    private void c() {
        this.n = new qjd(this.l.getApplicationContext(), this.v, this.p);
        if (this.t == null) {
            LogUtil.a("Track_RqLineChartHolderView", "initChartByType mLineChart is null");
            this.t = new HwHealthCommonLineChart(this.l);
        }
        this.t.setMoveToLastDataStamp(true);
        this.t.setLayerType(1, null);
        this.t.clearValues();
        this.n.addDataLayer((qjd) this.t, this.r);
        this.o.add(this.t);
        this.j.notifyDataSetChanged();
        this.t.reflesh(System.currentTimeMillis());
        this.t.setWillNotDraw(false);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.t.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(1 | acquireScrollAdapter.getFlag());
        this.t.resetYaxisAnimateValue();
    }

    public void d(DataInfos dataInfos, int i, int i2) {
        this.r = dataInfos;
        this.v = i;
        this.p = i2;
        initChart();
    }

    public void c(DataInfos dataInfos, int i, int i2) {
        this.r = dataInfos;
        this.v = i;
        this.p = i2;
        c();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setLeftClickEvent() {
        if (this.t.isAnimating()) {
            return;
        }
        HwHealthCommonLineChart hwHealthCommonLineChart = this.t;
        Objects.requireNonNull(hwHealthCommonLineChart);
        hwHealthCommonLineChart.scrollOnePageOlder(new qjj(this, hwHealthCommonLineChart));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setRightClickEvent() {
        if (this.t.isAnimating()) {
            return;
        }
        HwHealthCommonLineChart hwHealthCommonLineChart = this.t;
        Objects.requireNonNull(hwHealthCommonLineChart);
        hwHealthCommonLineChart.scrollOnePageNewer(new qjf(this, hwHealthCommonLineChart));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.t.canScrollOlderPager()) {
            this.g.setVisibility(0);
            this.g.setClickable(true);
        } else {
            this.g.setVisibility(4);
            this.g.setClickable(false);
        }
        if (this.t.canScrollNewerPager()) {
            this.f.setVisibility(0);
            this.f.setClickable(true);
        } else {
            this.f.setVisibility(4);
            this.f.setClickable(false);
        }
    }

    protected void a() {
        this.t.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView.5
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                RqLineChartHolderView.this.e();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                RqLineChartHolderView.this.e();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.f) {
            setRightClickEvent();
        } else if (view == this.g) {
            setLeftClickEvent();
        } else if (view == this.b) {
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void d() {
        StartCalendarViewListener startCalendarViewListener = this.s;
        if (startCalendarViewListener != null) {
            startCalendarViewListener.onStartCalendarView();
        }
    }

    public void a(long j, HealthCalendar healthCalendar) {
        this.k = healthCalendar;
        this.t.reflesh(j);
    }

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

    public void setStartCalendarViewListener(StartCalendarViewListener startCalendarViewListener) {
        this.s = startCalendarViewListener;
    }

    public qjd getRqChartLineViewHolder() {
        return this.n;
    }
}
